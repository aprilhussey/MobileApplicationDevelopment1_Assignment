package com.example.primaryschoolapplication;

import static androidx.core.content.ContextCompat.startActivity;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;

public class FileAdapter extends RecyclerView.Adapter<FileAdapter.ViewHolder>
{
    private final Context context;
    private final ArrayList<FileModel> fileModelArrayList;

    public FileAdapter(Context context, ArrayList<FileModel> fileModelArrayList)
    {
        this.context = context;
        this.fileModelArrayList = fileModelArrayList;
    }

    @NonNull
    @Override
    public FileAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType)
    {
        // Inflate the layout of each item of recycler view
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.card_file, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FileAdapter.ViewHolder holder, int position)
    {
        // Set data of TextViews of each card layout
        FileModel model = fileModelArrayList.get(position);
        holder.txtFileTitle.setText(model.getFileTitle());
        holder.txtFileBlock.setText(model.getFileBlock());
        // Get the title of current file
        String fileTitle = fileModelArrayList.get(position).getFileTitle();

        // Set onLongClickListener on the cardView
        holder.cardView.setOnLongClickListener(new View.OnLongClickListener()
        {
            @Override
            public boolean onLongClick(View view)
            {
                // Create an AlertDialog to confirm deletion
                new AlertDialog.Builder(context)
                        .setTitle("Delete File")
                        .setMessage("Are you sure you want to delete the file with the title: " + fileTitle + "?")
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener()
                        {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i)
                            {
                                // Delete the file with given title
                                deleteFile(fileTitle);
                                // Remove the file from the recycler view
                                fileModelArrayList.remove(position);
                                notifyItemRemoved(position);
                            }
                        })
                        .setNegativeButton("No", null)
                        .show();
                return true;
            }
        });
    }

    @Override
    public int getItemCount()
    {
        // Used to show the number of card views in recycler view
        return fileModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        CardView cardView;
        private final TextView txtFileTitle;
        private final TextView txtFileBlock;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            cardView = itemView.findViewById(R.id.cardView);
            txtFileTitle = itemView.findViewById(R.id.txtFileTitle);
            txtFileBlock = itemView.findViewById(R.id.txtFileBlock);

            cardView.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view)
                {
                    Intent intentFile = new Intent(view.getContext(), FileActivity.class);
                    intentFile.putExtra("fileTitle", txtFileTitle.getText().toString());
                    intentFile.putExtra("fileBlock", txtFileBlock.getText().toString());
                    view.getContext().startActivity(intentFile);
                }
            });
        }
    }

    private void deleteFile(String fileTitle)
    {
        File dir = context.getFilesDir();
        File file = new File(dir, fileTitle);
        boolean deleted = file.delete();

        if (deleted)
        {
            Toast.makeText(context, "File deleted", Toast.LENGTH_SHORT).show();
        }
        else
        {
            Toast.makeText(context, "File could not be deleted", Toast.LENGTH_SHORT).show();
        }
    }
}

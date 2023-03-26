package com.example.primaryschoolapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
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
    }

    @Override
    public int getItemCount()
    {
        // Used to show the number of card views in recycler view
        return fileModelArrayList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder
    {
        private final TextView txtFileTitle;
        private final TextView txtFileBlock;

        public ViewHolder(@NonNull View itemView)
        {
            super(itemView);

            txtFileTitle = itemView.findViewById(R.id.txtFileTitle);
            txtFileBlock = itemView.findViewById(R.id.txtFileBlock);
        }
    }
}

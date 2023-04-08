package com.example.primaryschoolapplication;

public class FileModel
{
    //Declare variables
    private String fileTitle;
    private String fileBlock;

    public FileModel(String fileTitle, String fileBlock)
    {
        // Initialise variables
        this.fileTitle = fileTitle;
        this.fileBlock = fileBlock;
    }

    // Getter and Setter functions
    public String getFileTitle() {
        return fileTitle;
    }

    public void setFileTitle(String fileTitle) {
        this.fileTitle = fileTitle;
    }

    public String getFileBlock() {
        return fileBlock;
    }

    public void setFileBlock(String fileBlock) {
        this.fileBlock = fileBlock;
    }
}

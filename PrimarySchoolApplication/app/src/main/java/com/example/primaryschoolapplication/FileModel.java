package com.example.primaryschoolapplication;

public class FileModel
{
    private String fileTitle;
    private String fileBlock;

    public FileModel(String fileTitle, String fileBlock)
    {
        this.fileTitle = fileTitle;
        this.fileBlock = fileBlock;
    }

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

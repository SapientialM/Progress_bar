package com.miniproject.progress;

public class ChildItem {
    private int progressBar;
    private String ChildName;
    private String progressInt;
    public ChildItem(int progressBar,String ChildName,String progressInt){
        this.progressBar=progressBar;
        this.ChildName=ChildName;
        this.progressInt=progressInt;
    }

    public ChildItem() {

    }

    public int getProgressBar() {
        return progressBar;
    }

    public String getProgressInt() {
        return progressInt;
    }

    public String getChildName() {
        return ChildName;
    }

    public void setChildName(String childName) {
        ChildName = childName;
    }

    public void setProgressBar(int progressBar) {
        this.progressBar = progressBar;
    }

    public void setProgressInt(String progressInt) {
        this.progressInt = progressInt;
    }
}

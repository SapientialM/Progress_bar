package com.miniproject.progress;

import java.util.List;

public class Task {
    private List<ChildItem>subTask;
    public Task(List<ChildItem>subTask){
        this.subTask=subTask;
    }

    public List<ChildItem> getSubTask() {
        return subTask;
    }

    public void setSubTask(List<ChildItem> subTask) {
        this.subTask = subTask;
    }
}

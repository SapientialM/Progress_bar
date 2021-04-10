package com.miniproject.progress;

public class listItem {
    private String task_name;
    private Boolean task_done;
    public listItem(String task_name,Boolean task_done){
        this.task_done=task_done;
        this.task_name=task_name;
    }

    public String getTask_name() {
        return task_name;
    }

    public void setTask_name(String task_name) {
        this.task_name = task_name;
    }

    public Boolean getTask_done() {
        return task_done;
    }

    public void setTask_done(Boolean task_done) {
        this.task_done = task_done;
    }
}

package com.miniproject.progress;

import java.util.List;

public class MainTask {
    public List<Task>mainTask;
    public void AddToMainTask(Task task){
        mainTask.add(task);
    }

    public List<Task> getMainTask() {
        return mainTask;
    }

    public void setMainTask(List<Task> mainTask) {
        this.mainTask = mainTask;
    }
}

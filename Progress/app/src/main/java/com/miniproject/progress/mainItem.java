package com.miniproject.progress;

public class mainItem {
    private int id;
    private int task_progress;
    private int time_progress;
    private String Task_name;
    private int subTask_num;
    private String date;
    private int tot_task;
    private int tot_time;
    private int tot_sub;
    public mainItem(int id,int task_progress,int time_progress,String Task_name,int subTask_num,String date,int tot_task,int tot_time,int tot_sub)
    {
        this.id=id;
        this.task_progress=task_progress;
        this.time_progress=time_progress;
        this.Task_name=Task_name;
        this.subTask_num=subTask_num;
        this.date=date;
        this.tot_task=tot_task;
        this.tot_time=tot_time;
        this.tot_sub=tot_sub;
    }

    public mainItem() {

    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setTot_sub(int tot_sub) {
        this.tot_sub = tot_sub;
    }

    public void setTot_task(int tot_task) {
        this.tot_task = tot_task;
    }

    public void setTot_time(int tot_time) {
        this.tot_time = tot_time;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setTask_name(String task_name) {
        Task_name = task_name;
    }

    public void setSubTask_num(int subTask_num) {
        this.subTask_num = subTask_num;
    }

    public void setTask_progress(int task_progress) {
        this.task_progress = task_progress;
    }

    public void setTime_progress(int time_progress) {
        this.time_progress = time_progress;
    }

    public int getSubTask_num() {
        return subTask_num;
    }

    public int getTask_progress() {
        return task_progress;
    }

    public int getTime_progress() {
        return time_progress;
    }

    public String getTask_name() {
        return Task_name;
    }

    public String getDate() {
        return date;
    }

    public int getTot_task() {
        return tot_task;
    }

    public int getTot_time() {
        return tot_time;
    }

    public int getTot_sub() {
        return tot_sub;
    }
}

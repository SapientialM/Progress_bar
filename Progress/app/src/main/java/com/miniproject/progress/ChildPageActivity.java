package com.miniproject.progress;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


public class ChildPageActivity extends AppCompatActivity {
    //private ProgressBar progressBar;
    //private TextView progressText;
    public int end = 50;
    public Button btnPlus;
    public Button btnDone;
    public Button btnMinus;
    private ListView childList;
    private int ID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Intent recIntent=this.getIntent();
       // String id=recIntent.getStringExtra("ID");
        //ID=Integer.parseInt(id);
        setContentView(R.layout.child_page);
        btnPlus=findViewById(R.id.ChildPlus);
        childList=findViewById(R.id.child_list);
        btnDone=findViewById(R.id.TaskComp);
        btnMinus=findViewById(R.id.ChildMinus);
        //List<Task>main=new MainTask().getMainTask();
        //Iterator<Task>it=main.iterator();
        //Task task=main.get(ID);
        List<ChildItem>childItems=new ArrayList<>();
        //start(getWindow().getDecorView());
        Adapter3 adapter3=new Adapter3(this,R.layout.child_item,childItems);

        btnPlus.setOnClickListener(v -> {
            ChildItem item=new ChildItem(0,"Subject","");
            childItems.add(item);
            adapter3.setAdapter3(childItems);
            adapter3.notifyDataSetChanged();
            //task.setSubTask(childItems);
            //main.set(ID, task);

        });
        ChildItem item=new ChildItem(0,"Subject","");
        childItems.add(item);
        childList.setAdapter(adapter3);

        btnDone.setOnClickListener(v -> {

        });
        btnMinus.setOnClickListener(v -> {
            if(childItems.size()>0)
            childItems.remove(childItems.size()-1);
            adapter3.setAdapter3(childItems);
            adapter3.notifyDataSetChanged();
        });
    }


    /*public void setProgressBar(int progress) {
        progressBar.setProgress(progress);
        progressText.setText(progress+"%");
//        int mup = 9;
        //这里我用RelativeLayout布局为列，其他布局设置方法一样，只需改变布局名就行
//        RelativeLayout.LayoutParams layout=(RelativeLayout.LayoutParams)progressText.getLayoutParams();
//        layout.setMargins(progressText.getLeft()+progress*mup,progressText.getTop(),progressText.getRight()-progress*mup,progressText.getBottom());
//        progressText.setLayoutParams(layout);
    }*/

    /*public void start(View view){
        new ProgressBarThread().start();
    }
    // 进度条更新 接受消息的进程
    Handler handler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            setProgressBar(i);
        }
    };

    class ProgressBarThread extends Thread{
        @Override
        public void run() {
            super.run();
            for (int i = 0; i <= end; i++) {
                handler.sendEmptyMessage(i);
                try {
                    sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }*/

}
package com.miniproject.progress;

import android.content.res.Resources;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;


public class ChildPageActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TextView progressText;
    public int end = 50;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_page);
        progressBar = findViewById(R.id.progress_bar);
        progressText = findViewById(R.id.progress_text);
        start(getWindow().getDecorView());

    }

    public void setProgressBar(int progress) {
        progressBar.setProgress(progress);
        progressText.setText(progress+"%");
//        int mup = 9;
        //这里我用RelativeLayout布局为列，其他布局设置方法一样，只需改变布局名就行
//        RelativeLayout.LayoutParams layout=(RelativeLayout.LayoutParams)progressText.getLayoutParams();
//        layout.setMargins(progressText.getLeft()+progress*mup,progressText.getTop(),progressText.getRight()-progress*mup,progressText.getBottom());
//        progressText.setLayoutParams(layout);
    }

    public void start(View view){
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
    }

}
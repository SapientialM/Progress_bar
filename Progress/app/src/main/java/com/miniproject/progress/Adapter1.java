package com.miniproject.progress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class Adapter1 extends ArrayAdapter {
    private List<mainItem> mainItemList;
    private final  int ID;
    public Adapter1(Context context,int Id,List<mainItem> object){
        super(context,Id,object);
        ID=Id;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        mainItem Main=(mainItem)getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(ID,null);
        TextView task_progress=(TextView)view.findViewById(R.id.Tv1_2);
        //TextView time_progress=(TextView)view.findViewById(R.id.Tv1_4);
        TextView Object_name=(TextView)view.findViewById(R.id.Object_Name);
        TextView date=(TextView)view.findViewById(R.id.date);
        TextView complete_progress=(TextView)view.findViewById(R.id.Tv1_1);
        task_progress.setText("Task Progress:"+Main.getTask_progress()+"/"+Main.getTot_task());
        //time_progress.setText("Time Progress:"+Main.getTime_progress()+"/"+Main.getTot_time());
        Object_name.setText("task_id: "+String.valueOf(Main.getId()));
        date.setText("Expected end of "+Main.getDate());
        complete_progress.setText(Main.getTask_name());
        return view;
    }
    public void setAdapter1(List<mainItem>mainItemList){
        this.mainItemList=mainItemList;
    }
}

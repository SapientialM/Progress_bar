package com.miniproject.progress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class Adapter3 extends ArrayAdapter {
    private List<ChildItem>childItemList;
    private final  int ID;

    public Adapter3(Context context, int Id, List<ChildItem> object){
        super(context,Id,object);
        ID=Id;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        ChildItem childItem=(ChildItem)getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(ID,null);
        EditText ChildName=view.findViewById(R.id.child_subject);
        ProgressBar progressBar=view.findViewById(R.id.progress_bar);
        TextView progressInt=view.findViewById(R.id.progress_text);
        ChildName.setText(childItem.getChildName());
        progressBar.setProgress(childItem.getProgressBar());
        progressInt.setText(childItem.getProgressInt());
        return view;
    }

    public void setAdapter3(List<ChildItem>childItems){
        this.childItemList=childItems;
    }
}

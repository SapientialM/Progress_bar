package com.miniproject.progress;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class Adapter2 extends ArrayAdapter {
    private List<listItem> mainItemList;
    private final  int ID;
    public Adapter2(Context context, int Id, List<listItem> object){
        super(context,Id,object);
        ID=Id;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        listItem list=(listItem)getItem(position);
        View view= LayoutInflater.from(getContext()).inflate(ID,null);
        EditText task_name=(EditText)view.findViewById(R.id.TName);
        RadioButton task_done=(RadioButton)view.findViewById(R.id.tDone);
        task_done.setChecked(false);
        task_name.setText("");
        return view;
    }
}

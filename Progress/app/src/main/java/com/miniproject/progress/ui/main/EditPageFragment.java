package com.miniproject.progress.ui.main;

import androidx.fragment.app.Fragment;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.miniproject.progress.R;

import java.util.Calendar;

public class EditPageFragment extends Fragment implements DatePickerDialog.OnDateSetListener {

    private String title;
    private Button btnTime;
    public EditPageFragment(String title){
        this.title = title;
    }
    public EditPageFragment(){};
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if(title.equals("Data"))
            return inflater.inflate(R.layout.frag1, container, false);
        else
            return inflater.inflate(R.layout.frag2, container, false);

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if(title.equals("Data")) {
            btnTime = view.findViewById(R.id.btnTime);
            btnTime.setOnClickListener(v -> {
                initCalendar();
            });
        }
    }

    private void initCalendar(){
        //获取日历的一个实例，里面包含了当前的时分秒
        Calendar calendar = Calendar.getInstance();
        //构建一个日期对话框，该对话框已经集成了日期选择器
        //DatePickerDialog的第二个构造参数指定了日期监听器
        DatePickerDialog dialog = new DatePickerDialog(getActivity(),this
                ,calendar.get(Calendar.YEAR)//年份
                ,calendar.get(Calendar.MONTH)//月份
                ,calendar.get(Calendar.DAY_OF_MONTH));//日子
        //把日期对话框显示在界面上
        dialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String desc = String.format("%s/%s/%s",year,month+1,dayOfMonth);
        btnTime.setText(desc);
    }
}



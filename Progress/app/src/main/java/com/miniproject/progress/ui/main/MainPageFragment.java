package com.miniproject.progress.ui.main;

import android.content.Intent;
import android.database.Cursor;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.BinderThread;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.miniproject.progress.Adapter1;
import com.miniproject.progress.ChildPageActivity;
import com.miniproject.progress.LoginActivity;
import com.miniproject.progress.PageEditActivity;
import com.miniproject.progress.R;
import com.miniproject.progress.WebViewActivity;
import com.miniproject.progress.mainItem;
import com.miniproject.progress.sqliteDB.DatabaseHelper;
import com.miniproject.progress.sqliteDB.DatabaseHelper2;
import com.miniproject.progress.sqliteDB.DatabaseHelper3;

import java.util.ArrayList;
import java.util.List;

public class MainPageFragment extends Fragment {
    private String title;
    private Button btnPlus;
    private View taskLayout;
    private TextView text;
    private ListView compList;
    private ListView mainList;

    private Button help_manual;
    private Button back_up;
    private Button recover_data;
    private Button login;
    private Button btnMinus;
    private Button btnDone;
    private boolean isFirstLoading=true;
    private Adapter1 adapter1;
    private Adapter1 adapter2;
    private int ID;

    private DatabaseHelper2 databaseHelper;
    private DatabaseHelper data;
    private DatabaseHelper3 dataDone;
    private List<mainItem>listMain;
    private List<mainItem>list;

    public MainPageFragment(String tab) {
        this.title = tab;

    }
    public MainPageFragment(){}

    @Override
    public void onResume() {
        super.onResume();
        if(isFirstLoading==false){
            //adapter1.clear();
            listMain.clear();
            if(!list.isEmpty())
            list.clear();
            Cursor cursor=data.readData();
            if(cursor.getCount()!=0){
                while(cursor.moveToNext()) {
                    mainItem items = new mainItem();
                    items.setId(cursor.getInt(0));
                    items.setTask_progress(cursor.getInt(1));
                    items.setTime_progress(cursor.getInt(2));
                    items.setTask_name(cursor.getString(3));
                    items.setSubTask_num(cursor.getInt(4));
                    items.setDate(cursor.getString(5));
                    items.setTot_task(cursor.getInt(6));
                    items.setTot_time(cursor.getInt(7));
                    items.setTot_sub(cursor.getInt(8));
                    listMain.add(items);
                }
            }
            adapter1.setAdapter1(listMain);
            adapter1.notifyDataSetChanged();

            cursor=dataDone.readData();
            if(cursor.getCount()!=0){
                while(cursor.moveToNext()){
                    mainItem items = new mainItem();
                    items.setId(cursor.getInt(0));
                    items.setTask_progress(cursor.getInt(1));
                    items.setTime_progress(cursor.getInt(2));
                    items.setTask_name(cursor.getString(3));
                    items.setSubTask_num(cursor.getInt(4));
                    items.setDate(cursor.getString(5));
                    items.setTot_task(cursor.getInt(6));
                    items.setTot_time(cursor.getInt(7));
                    items.setTot_sub(cursor.getInt(8));
                    list.add(items);
                }

            }

            adapter2.setAdapter1(list);
            adapter2.notifyDataSetChanged();
        }
        isFirstLoading=false;

    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);

        if(title.equals("tab1"))
            return inflater.inflate(R.layout.fragment_main, container, false);
        else if(title.equals("tab2"))
            return inflater.inflate(R.layout.fragment_complete, container, false);
        else if(title.equals("tab3"))
            return inflater.inflate(R.layout.fragment_home, container, false);
        else
            return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dataDone=new DatabaseHelper3(getActivity());
        data=new DatabaseHelper(getActivity());
        list = new ArrayList<>();
        listMain=new ArrayList<>();
        adapter2 = new Adapter1(getActivity(),R.layout.main_list_item,list);
        adapter1=new Adapter1(getActivity(),R.layout.main_list_item,listMain);
        if(title.equals("tab1")) {
            btnPlus = view.findViewById(R.id.btn_plus);
            btnMinus=view.findViewById(R.id.btn_minus);
            btnDone=view.findViewById(R.id.btn_done);
            taskLayout = view.findViewById(R.id.task1);
            mainList=view.findViewById(R.id.ListMain);
            /*taskLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), ChildPageActivity.class);
                    startActivity(intent);
                }
            });*/
            Cursor cursor=data.readData();
            //if(cursor.getCount()==0)
            //data.insertData("0","0","data0","0","2021/4/10","1","0","0");
            if(cursor.getCount()!=0){
                while(cursor.moveToNext()) {
                    mainItem items = new mainItem();
                    items.setId(cursor.getInt(0));
                    items.setTask_progress(cursor.getInt(1));
                    items.setTime_progress(cursor.getInt(2));
                    items.setTask_name(cursor.getString(3));
                    items.setSubTask_num(cursor.getInt(4));
                    items.setDate(cursor.getString(5));
                    items.setTot_task(cursor.getInt(6));
                    items.setTot_time(cursor.getInt(7));
                    items.setTot_sub(cursor.getInt(8));
                    listMain.add(items);
                }
            }
            mainList.setAdapter(adapter1);
            //adapter1.setAdapter1(listMain);
            //adapter1.notifyDataSetChanged();
            btnPlus.setOnClickListener(v -> {
                //data=new DatabaseHelper(getActivity());
                //Intent intent = new Intent(getActivity(), PageEditActivity.class);
                //startActivity(intent);
                //listMain.clear();
                data.insertData("0","0","","0","","1","0","0");
                onResume();
            });
            btnMinus.setOnClickListener(v -> {
                data.deleteData();
                onResume();
            });

            btnDone.setOnClickListener(v -> {
                Cursor cursor2=data.removeData();

                //List<mainItem>tList=new ArrayList<>();
                if(cursor2.getCount()!=0) {
                    while (cursor2.moveToNext()) {
                        mainItem items = new mainItem();
                        items.setId(cursor2.getInt(0));
                        String Task_progress = String.valueOf(cursor2.getInt(1));
                        String Time_progress = String.valueOf(cursor2.getInt(2));
                        String Task_name = cursor2.getString(3);
                        String SubTask_num = String.valueOf(cursor2.getInt(4));
                        String Date = (cursor2.getString(5));
                        String Tot_task = String.valueOf(cursor2.getInt(6));
                        String Tot_time = String.valueOf(cursor2.getInt(7));
                        String Tot_sub = String.valueOf(cursor2.getInt(8));
                        dataDone.insertData(Task_progress, Time_progress, Task_name, SubTask_num, Date, Tot_task, Tot_time, Tot_sub);
                    }
                    data.deleteData();
                    onResume();
                }
            });
            mainList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(getActivity(), PageEditActivity.class);
                    TextView text=view.findViewById(R.id.Object_Name);
                    String IDD=text.getText().toString();
                    int ID=Integer.parseInt(IDD);
                    intent.putExtra("ID",ID+"");
                    //ID=position;
                    startActivity(intent);
                }
            });
        } else if(title.equals("tab2")){
            Cursor cursor=dataDone.readData();
            compList = view.findViewById(R.id.ListComplete);
            if(cursor.getCount()!=0){
                while(cursor.moveToNext()){
                    mainItem items = new mainItem();
                    items.setId(cursor.getInt(0));
                    items.setTask_progress(cursor.getInt(1));
                    items.setTime_progress(cursor.getInt(2));
                    items.setTask_name(cursor.getString(3));
                    items.setSubTask_num(cursor.getInt(4));
                    items.setDate(cursor.getString(5));
                    items.setTot_task(cursor.getInt(6));
                    items.setTot_time(cursor.getInt(7));
                    items.setTot_sub(cursor.getInt(8));
                    list.add(items);
                }
            }
            compList.setAdapter(adapter2);
            adapter2.setAdapter1(list);
            adapter2.notifyDataSetChanged();

        } else if(title.equals("tab3")){
            text = view.findViewById(R.id.log_info);
            databaseHelper = new DatabaseHelper2(getActivity());
            text = view.findViewById(R.id.log_info);
            if(databaseHelper.isLogin()){
                String name = databaseHelper.getLoginName();
                if(!name.isEmpty())
                    text.setText("Welcom back! "+name);
                System.out.println("Login name:--"+name+"------");
            }

            help_manual = view.findViewById(R.id.help_manual);
            help_manual.setOnClickListener(v ->{
                Intent intent = new Intent(getActivity(), WebViewActivity.class);
                startActivity(intent);
            });
            back_up = view.findViewById(R.id.back_up);
            back_up.setOnClickListener(v ->{
                databaseHelper = new DatabaseHelper2(getActivity());
                if(!databaseHelper.isLogin()){
                    Toast toast = Toast.makeText(getActivity(), "You haven't logged in yet!\n You need to log in first!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0, 0);
                    toast.show();
                }
                else {
                    // back up
                }
            });
            recover_data = view.findViewById(R.id.recovery_data);
            recover_data.setOnClickListener(v ->{
                databaseHelper = new DatabaseHelper2(getActivity());
                if(!databaseHelper.isLogin()){
                    Toast toast = Toast.makeText(getActivity(), "You haven't logged in yet!\n You need to log in first!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0, 0);
                    toast.show();
                }
                else {
                    // recover data
                }
            });
            login = view.findViewById(R.id.login);
            login.setOnClickListener(v ->{
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            });
            recover_data = view.findViewById(R.id.recovery_data);
            recover_data.setOnClickListener(v ->{
                databaseHelper = new DatabaseHelper2(getActivity());
                if(!databaseHelper.isLogin()){
                    Toast toast = Toast.makeText(getActivity(), "You haven't logged in yet!\n You need to log in first!", Toast.LENGTH_SHORT);
                    toast.setGravity(Gravity.CENTER,0, 0);
                    toast.show();
                }
                else {
                    // recover data

                }
            });
            login = view.findViewById(R.id.login);
            login.setOnClickListener(v ->{
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
            });


        }

    }
}

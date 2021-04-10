package com.miniproject.progress.ui.main;

import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.miniproject.progress.Adapter1;
import com.miniproject.progress.ChildPageActivity;
import com.miniproject.progress.LoginActivity;
import com.miniproject.progress.PageEditActivity;
import com.miniproject.progress.R;
import com.miniproject.progress.WebViewActivity;
import com.miniproject.progress.mainItem;
import com.miniproject.progress.sqliteDB.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;

public class MainPageFragment extends Fragment {
    private String title;
    private Button btnPlus;
    private View taskLayout;
    private ListView compList;

    private Button help_manual;
    private Button back_up;
    private Button recover_data;
    private Button login;

    private DatabaseHelper databaseHelper;

    private ViewPager viewPager;

    public MainPageFragment(String tab) {
        this.title = tab;

    }
    public MainPageFragment(){}

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
        if(title.equals("tab1")) {
            btnPlus = view.findViewById(R.id.btn_plus);
            taskLayout = view.findViewById(R.id.task1);
            taskLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(getActivity(), ChildPageActivity.class);
                    startActivity(intent);
                }
            });

            btnPlus.setOnClickListener(v -> {
                Intent intent = new Intent(getActivity(), PageEditActivity.class);
                startActivity(intent);
            });
        } else if(title.equals("tab2")){
            compList = view.findViewById(R.id.ListComplete);
            List<mainItem> list = new ArrayList<>();
            mainItem item = new mainItem(50,50,"project1",5,"2022/4/9",100,100,10);
            list.add(item);
            Adapter1 adapter1 = new Adapter1(getActivity(),R.layout.main_list_item,list);
            compList.setAdapter(adapter1);

        } else if(title.equals("tab3")){

            help_manual = view.findViewById(R.id.help_manual);
            help_manual.setOnClickListener(v ->{
                    Intent intent = new Intent(getActivity(), WebViewActivity.class);
                    startActivity(intent);
            });
            back_up = view.findViewById(R.id.back_up);
            back_up.setOnClickListener(v ->{
                databaseHelper = new DatabaseHelper(getActivity());
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
                databaseHelper = new DatabaseHelper(getActivity());
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

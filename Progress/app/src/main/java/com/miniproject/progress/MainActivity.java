package com.miniproject.progress;

import android.app.ActionBar;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import com.miniproject.progress.jdbc.UserDao;
import com.miniproject.progress.sqliteDB.DatabaseHelper;
import com.miniproject.progress.sqliteDB.DatabaseHelper2;
import com.miniproject.progress.ui.main.MainPageFragment;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.text.BreakIterator;

public class MainActivity extends AppCompatActivity {


    private MainPageFragment mainPageFragment1;
    private MainPageFragment mainPageFragment2;
    private MainPageFragment mainPageFragment3;
    private MainPageFragment currentFragment;
    private TextView tab1;
    private TextView tab2;
    private TextView tab3;
    private TextView curTab;

    private DatabaseHelper2 databaseHelper;

    public boolean isLogin = false;
    public String userName = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        hideStatusBar(MainActivity.this);

        initView();
        mainPageFragment1 = new MainPageFragment("tab1");
        mainPageFragment2 = new MainPageFragment("tab2");
        mainPageFragment3 = new MainPageFragment("tab3");

        addOnclickListener();

        // 默认放 ongoing
        // 将 Fragment 放入到 Activity 中
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_main, mainPageFragment1, "tab1").commit();
        currentFragment = mainPageFragment1;
        curTab = tab1;

    }

    private void initView() {
        tab1 = findViewById(R.id.tab1);
        tab2 = findViewById(R.id.tab2);
        tab3 = findViewById(R.id.tab3);
    }

    private void addOnclickListener() {
        // tab Listener
        tab1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentOnclick(mainPageFragment1);
            }
        });
        tab2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentOnclick(mainPageFragment2);
            }
        });
        tab3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fragmentOnclick(mainPageFragment3);
            }
        });
    }

    private void fragmentOnclick(MainPageFragment tab){
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        changeTabColor(tab);
        if(!tab.isAdded()){
            fragmentTransaction.remove(currentFragment)
                    .add(R.id.fragment_main,tab)
                    .commit();
        }else {
            fragmentTransaction.remove(currentFragment)
                    .show(tab)
                    .commit();
        }

        currentFragment = tab;
    }

    private void changeTabColor(MainPageFragment tab) {
        curTab.setBackgroundColor(getResources().getColor(R.color.gray));
        if(tab.equals(mainPageFragment1)){
            curTab = tab1;
            curTab.setBackgroundColor(getResources().getColor(R.color.gray_press));
        }else if(tab.equals(mainPageFragment2)){
            curTab = tab2;
            curTab.setBackgroundColor(getResources().getColor(R.color.gray_press));
        }else if(tab.equals(mainPageFragment3)){
            curTab = tab3;
            curTab.setBackgroundColor(getResources().getColor(R.color.gray_press));
        }
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        databaseHelper = new DatabaseHelper2(MainActivity.this);
        TextView text = findViewById(R.id.log_info);
        if(databaseHelper.isLogin()){
            String name = databaseHelper.getLoginName();
            if(!name.isEmpty()&&text!=null)
                text.setText("Welcom back! "+name);
            System.out.println("Login name:--"+name+"------");
            System.out.println("-------loginstate:--"+databaseHelper.isLogin()+" ------");
            System.out.println("-------loginname:--"+databaseHelper.getLoginName()+" ----");
        }
        System.out.println("------------changed----------");
    }

    public static void hideStatusBar(Activity activity){
        View decorView = activity.getWindow().getDecorView();
        int uiOptions = View.SYSTEM_UI_FLAG_FULLSCREEN;
        decorView.setSystemUiVisibility(uiOptions);
        ActionBar actionBar = activity.getActionBar();
        if (actionBar != null)
            actionBar.hide();
    }
}
package com.miniproject.progress;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;

import com.miniproject.progress.ui.main.EditPageFragment;

import java.util.List;

public class PageEditActivity extends AppCompatActivity {

    private EditPageFragment DataFragment;
    private EditPageFragment ListFragment;

    private Button btnChange_data;
    private Button btnChange_list;
    private Button curChange;

    private EditPageFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.child_edit_page);

        btnChange_data = findViewById(R.id.buttonData);
        btnChange_list = findViewById(R.id.buttonList);

        // 实例化
        ListFragment = new EditPageFragment("List");
        DataFragment = new EditPageFragment("Data");

        btnChange_data.setOnClickListener(v -> {
            curChange.setBackground(getResources().getDrawable(R.drawable.buttonright_normal));
            curChange = btnChange_data;
            curChange.setBackground(getResources().getDrawable(R.drawable.buttonleft_press));
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if(!DataFragment.isAdded()){
                fragmentTransaction.hide(currentFragment)
                        .add(R.id.fragment_change,DataFragment)
                        .commit();
            }else {
                fragmentTransaction.hide(currentFragment)
                        .show(DataFragment)
                        .commit();
            }
            currentFragment = DataFragment;
        });

        btnChange_list.setOnClickListener(v -> {
            curChange.setBackground(getResources().getDrawable(R.drawable.buttonleft_normal));
            curChange = btnChange_list;
            curChange.setBackground(getResources().getDrawable(R.drawable.buttonright_press));
            FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
            if(!ListFragment.isAdded()){
                fragmentTransaction.hide(currentFragment)
                        .add(R.id.fragment_change,ListFragment)
                        .commit();
            }else {
                fragmentTransaction.hide(currentFragment)
                        .show(ListFragment)
                        .commit();
            }
            currentFragment = ListFragment;
        });


        // 将 Fragment 放入到 Activity 中
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_change, DataFragment, "Data").commit();
        currentFragment = DataFragment;
        curChange = btnChange_data;
        curChange.setBackground(getResources().getDrawable(R.drawable.buttonleft_press));
    }
}


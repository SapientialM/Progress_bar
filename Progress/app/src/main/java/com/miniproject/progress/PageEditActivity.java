package com.miniproject.progress;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;

import com.miniproject.progress.sqliteDB.DatabaseHelper;
import com.miniproject.progress.ui.main.EditPageFragment;

import java.util.List;

import static com.miniproject.progress.MainActivity.hideStatusBar;

public class PageEditActivity extends AppCompatActivity {

    private EditPageFragment DataFragment;
    private DatabaseHelper data;
    private Button btnChange_data;
    private Button curChange;

    private EditPageFragment currentFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        data=new DatabaseHelper(this);
        setContentView(R.layout.child_edit_page);
        hideStatusBar(PageEditActivity.this);
        Intent intent=getIntent();
        String id=intent.getStringExtra("ID");
        int ID=Integer.parseInt(id);
        btnChange_data = findViewById(R.id.buttonData);

        // 实例化
        DataFragment = new EditPageFragment("Data");

        btnChange_data.setOnClickListener(v -> {
            EditText task_name=findViewById(R.id.edit_task_name);
            Button time=findViewById(R.id.btnTime);
            EditText task_num=findViewById(R.id.edit_task_num_);
            EditText complete_num=findViewById(R.id.edit_task_num_comp_);
            String tName=task_name.getText().toString();
            String Time=time.getText().toString();
            String Task_Num=task_num.getText().toString();
            String Complete_num=complete_num.getText().toString();
            System.out.print("--------------alter before");
            data.alterByID(id,tName,Time,Task_Num,Complete_num);
            System.out.print("-------------alter");
            finish();
        });

        // 将 Fragment 放入到 Activity 中
        getSupportFragmentManager().beginTransaction().add(R.id.fragment_change, DataFragment, "Data").commit();
        currentFragment = DataFragment;
        curChange = btnChange_data;
    }
}


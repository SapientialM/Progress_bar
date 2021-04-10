package com.miniproject.progress;

import android.os.Bundle;
import android.os.Looper;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.miniproject.progress.jdbc.UserDao;
import com.miniproject.progress.sqliteDB.DatabaseHelper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends AppCompatActivity {
    private Button btn_login, btn_register;
    private EditText edit_name,edit_pswd;
    private DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);

        btn_login = findViewById(R.id.login_page_login);
        btn_register = findViewById(R.id.login_page_register);
        edit_name = findViewById(R.id.username_edit);
        edit_pswd = findViewById(R.id.userpswd_edit);
        setEditTextInhibitInputSpaChat(edit_name);
        setEditTextInhibitInputSpaChat(edit_pswd);

        btn_login.setOnClickListener(v ->{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String name = edit_name.getText().toString().trim();
                    String pswd = edit_pswd.getText().toString().trim();

                    UserDao dao = new UserDao();
                    boolean result =dao.login(name,pswd);
                    if (result){
                        Looper.prepare();
                        Toast toast = Toast.makeText(LoginActivity.this,"Login success！",Toast.LENGTH_SHORT);
                        toast.show();
                        databaseHelper = new DatabaseHelper(LoginActivity.this);
                        databaseHelper.setLogin(true);
                        databaseHelper.insertData(name,pswd);
                        Looper.loop();

                    }
                    else{
                        Looper.prepare();
                        Toast toast = Toast.makeText(LoginActivity.this,"Login failed！",Toast.LENGTH_SHORT);
                        toast.show();
                        Looper.loop();
                    }

                }
            }).start();
        });
        btn_register.setOnClickListener(v ->{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String name = edit_name.getText().toString().trim();
                    String pswd = edit_pswd.getText().toString().trim();
                    UserDao dao = new UserDao();
                    boolean result =dao.register(name,pswd);
                    if (!result){
                        Looper.prepare();
                        Toast toast = Toast.makeText(LoginActivity.this,"Register success！",Toast.LENGTH_SHORT);
                        toast.show();
                        Looper.loop();
                    }
                    else {
                        Looper.prepare();
                        Toast toast = Toast.makeText(LoginActivity.this,"Register failed！",Toast.LENGTH_SHORT);
                        toast.show();
                        Looper.loop();
                    }

                }
            }).start();
        });
    }
    /**

     * EditText ban special text

     * @param editText

     */
    public static void setEditTextInhibitInputSpaChat(EditText editText) {
        InputFilter filter_space = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence source, int start, int end, Spanned dest, int dstart, int dend) {
                if (source.equals(" "))
                    return "";
                else
                    return null;
            }
        };
        InputFilter filter_speChat = new InputFilter() {
            @Override
            public CharSequence filter(CharSequence charSequence, int i, int i1, Spanned spanned, int i2, int i3) {
                String speChat = "[`~!@#_$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）— +|{}【】‘；：”“’。，、？]";
                Pattern pattern = Pattern.compile(speChat);
                Matcher matcher = pattern.matcher(charSequence.toString());
                if (matcher.find()) return "";
                else return null;
            }
        };
        editText.setFilters(new InputFilter[]{filter_space, filter_speChat});
    }

}

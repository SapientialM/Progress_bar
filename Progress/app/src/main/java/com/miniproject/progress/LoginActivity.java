package com.miniproject.progress;

import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Bundle;
import android.os.Looper;
import android.text.InputFilter;
import android.text.Spanned;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.miniproject.progress.jdbc.UserDao;
import com.miniproject.progress.sqliteDB.DatabaseHelper;
import com.miniproject.progress.sqliteDB.DatabaseHelper2;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.miniproject.progress.MainActivity.hideStatusBar;

public class LoginActivity extends AppCompatActivity {
    private Button btn_login, btn_register;
    private EditText edit_name,edit_pswd;
    private DatabaseHelper2 databaseHelper;
    private TextView helpView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login_page);
        hideStatusBar(LoginActivity.this);

        btn_login = findViewById(R.id.login_page_login);
        btn_register = findViewById(R.id.login_page_register);
        edit_name = findViewById(R.id.username_edit);
        edit_pswd = findViewById(R.id.userpswd_edit);
        helpView = findViewById(R.id.login_page_help);
        setEditTextInhibitInputSpaChat(edit_name);
        setEditTextInhibitInputSpaChat(edit_pswd);
        helpView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog aldg;
                AlertDialog.Builder adBd=new AlertDialog.Builder(LoginActivity.this);
                adBd.setTitle("help");
                adBd.setIcon(R.drawable.add_image);
                adBd.setMessage("1.Account names and passwords supports no more than 16 characters.\n" +
                        "2.Account names and passwords cannot contain special characters.\n" +
                        "versions.1.1");
                adBd.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                });
                aldg=adBd.create();
                aldg.show();
            }
        });
        btn_login.setOnClickListener(v ->{
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String name = edit_name.getText().toString().trim();
                    String pswd = edit_pswd.getText().toString().trim();

                    UserDao dao = new UserDao();
                    boolean result =dao.login(name,pswd);
                    Looper.prepare();
                    Toast toast;
                    if (result){
                        toast = Toast.makeText(LoginActivity.this, "Login success！", Toast.LENGTH_SHORT);
                        toast.show();
                        edit_name.setText("");
                        edit_pswd.setText("");
                        databaseHelper = new DatabaseHelper2(LoginActivity.this);
                        if(databaseHelper.isLogin())
                            if(databaseHelper.getLoginName() == name) {
                                String args[] = new String[]{name};
                                Cursor cursor = databaseHelper.query(args);
                                cursor.moveToNext();
                                databaseHelper.update(cursor.getInt(cursor.getPosition()),name,pswd);
                                return;
                            }
                        databaseHelper.setLoginName(name);
                        databaseHelper.insertData(name,pswd);
                    }
                    else{
                        toast = Toast.makeText(LoginActivity.this, "Login failed！", Toast.LENGTH_SHORT);
                        toast.show();
                    }
                    Looper.loop();

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
                        edit_pswd.setText("");
                        edit_pswd.setText("");
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

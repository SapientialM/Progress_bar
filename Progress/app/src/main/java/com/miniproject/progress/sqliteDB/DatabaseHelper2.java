package com.miniproject.progress.sqliteDB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper2 extends SQLiteOpenHelper {


    public static final String DATABASE_NAME = "Account.db";
    public static String TABLE_NAME = "Login";
    public static String IS_LOGIN = "isLogin_table";

    public String COL_2 = "userName";
    public String COL_3 = "userPswd";

    public String COL_Login1 = "userName";
    public String COL_Login2 = "islogin";

    //Simplified constructor
    public DatabaseHelper2(Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //Create the database
        sqLiteDatabase.execSQL("CREATE TABLE " + TABLE_NAME + " (" +
                "userId integer primary key autoincrement," +
                "userName text," +
                "userPswd text)");
        sqLiteDatabase.execSQL("CREATE TABLE " + IS_LOGIN + " (" +
                "userId integer primary key autoincrement," +
                "userName text," +
                "isLogin bool)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    public boolean insertData(String userName, String userPswd) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_2, userName);
        contentValues.put(COL_3, userPswd);

        //Table name, null and the content values are needed as param

        long result = sqLiteDatabase.insert(TABLE_NAME, null, contentValues);
        if (result == -1) {
            //Insert has failed
            return false;
        } else {
            //Successful insertion
            return true;
        }
    }

    public void update(int id, String userName,String userPswd) {
        SQLiteDatabase db = this.getWritableDatabase();
        String where = "_id = ?";
        String[] whereValue = { Integer.toString(id) };
        ContentValues cv = new ContentValues();
        cv.put("userName", userName);
        cv.put("userPswd", userPswd);
        db.update(TABLE_NAME, cv, where, whereValue);
    }

    public Cursor query(String[] args) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT userId FROM "+TABLE_NAME+" WHERE userName LIKE ?", args);
        return cursor;
    }

    public boolean isLogin(){
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select "+COL_Login2+" from "+IS_LOGIN+" order by userId desc limit 0,1";
        Cursor cursor = db.rawQuery(sql, null);
        int num = cursor.getCount();
        cursor.moveToNext();
        if(num == 0)
            return false;
        else if(cursor.getInt(cursor.getPosition()) == 0)
            return false;
        else
            return true;
    }

    public String getLoginName() {
        SQLiteDatabase db = this.getReadableDatabase();
        String sql = "select "+COL_Login1+" from "+IS_LOGIN+" order by userId desc limit 0,1";
        Cursor cursor = db.rawQuery(sql, null);
        cursor.moveToNext();
        return cursor.getString(cursor.getPosition());
    }

    public boolean setLoginName(String loginName) {
        SQLiteDatabase sqLiteDatabase = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_Login1, loginName);
        contentValues.put(COL_Login2, true);
        //Table name, null and the content values are needed as param
        long result = sqLiteDatabase.insert(IS_LOGIN, null, contentValues);
        if (result == -1) {
            //Insert has failed
            return false;
        } else {
            //Successful insertion
            return true;
        }
    }

}
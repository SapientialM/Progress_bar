package com.miniproject.progress.jdbc;

import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.Properties;

public class JdbcUtil {
    private static JdbcUtil instance;

    public static JdbcUtil getInstance(){
        if (instance ==null){
            instance = new JdbcUtil();
        }
        return instance;
    }
    public Connection getConnection(String dbName,String name,String password) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            String url = "jdbc:mysql://39.96.77.56:3306/"+dbName;
            return DriverManager.getConnection(url,name,password);
        } catch (Exception e) {
            System.out.println("get connection: "+e);
            return null;
        }
    }

    public Connection getConnection(String file){
        File f = new File(file);
        if(!f.exists()){
            return null;
        }else {
            Properties pro = new Properties();
            try {
                Class.forName("com.mysql.jdbc.Driver");
                pro.load(new FileInputStream(f));
                String url = pro.getProperty("url");
                String name = pro.getProperty("name");
                String password = pro.getProperty("password");
                return DriverManager.getConnection(url,name,password);
            }catch (Exception e){
                return null;
            }
        }
    }
}



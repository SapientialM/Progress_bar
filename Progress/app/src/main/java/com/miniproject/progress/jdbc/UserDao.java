package com.miniproject.progress.jdbc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {

    JdbcUtil jdbcUtil = JdbcUtil.getInstance();
    //第一个参数为数据库名称，第二个参数为数据库账号 第三个参数为数据库密码
    Connection conn = jdbcUtil.getConnection("ProgressBar","ProgressBar","2KBEtM8j6Sdn6iJC");
    //注册
    public  boolean register(String name,String password){
        if (conn==null){
            System.out.println("----------------------------register:connector is null----------------------------");
            return false;
        }else {
            //进行数据库操作
            System.out.println("----------------------------connect success!----------------------------");
            String sql = "insert into Admin(userName,userPswd) values(?,?)";
            try {
                PreparedStatement pre = conn.prepareStatement(sql);
                pre.setString(1,name);
                pre.setString(2,password);System.out.println("--------------register:"+pre.execute()+"-------");
                return pre.execute();
            } catch (SQLException e) {
                System.out.println("register: "+e);
                return false;
            }finally {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }
    //登录
    public boolean login(String name,String password){
        if (conn==null){
            System.out.println("----------------------------login:connector is null----------------------------");
            return false;
        }else {
            String sql = "select * from Admin where userName=? and userPswd=?";
            try {
                PreparedStatement pres = conn.prepareStatement(sql);
                pres.setString(1,name);
                pres.setString(2,password);
                ResultSet res = pres.executeQuery();
                boolean t = res.next();
                System.out.println("--------------login:"+t+"-------");
                return t;
            } catch (SQLException e) {
                System.out.println("login"+e);
                return false;
            }

        }
    }
}



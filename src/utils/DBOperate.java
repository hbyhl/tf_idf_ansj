package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class DBOperate {

    private static Connection conn = null;

    private final static String URL = "jdbc:mysql://10.10.90.156:3306/app_xinfang?amp;characterEncoding=UTF8&amp;autoReconnect=true&amp;autoReconnectForPools=true&amp;zeroDateTimeBehavior=convertToNull";
    private final static String USERNAME = "develop" ;   
    private final static String PASSWORF = "p3m12d" ; 
    public static Connection getConnection() {
        try {
            if (conn == null) {

                Class.forName("com.mysql.jdbc.Driver"); // 动态加载mysql驱动
                System.out.println("成功加载MySQL驱动程序");
                conn = DriverManager.getConnection(URL,USERNAME,PASSWORF);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return conn;
    }

    public static ArrayList<String> query(String sql) {
        ArrayList<String> result = new ArrayList<String>();
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
//                System.out.println(rs.getString(1));
                result.add(rs.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public static ArrayList<ArrayList<String>> query1(String sql) {
        ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
        ArrayList<String> questionList = new ArrayList<String>();
        ArrayList<String> idList = new ArrayList<String>();
        Connection conn = getConnection();
        Statement stmt = null;
        ResultSet rs = null;
        try {
            stmt = conn.createStatement();
            rs = stmt.executeQuery(sql);
            while (rs.next()) {
//                System.out.println(rs.getString(1));
                idList.add(rs.getString(1));
                questionList.add(rs.getString(2));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                conn.close();
            } catch (SQLException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
        result.add(idList);
        result.add(questionList);
        return result;
    }
    public static void main(String[] args) {
        
//        String querSql = "select question from question where build_id=2504 and answer!= '您的问题与房产或楼盘无关'";
//        query(querSql);
        String querSql1 = "select id,question from question where build_id=2504 and answer!= '您的问题与房产或楼盘无关'";
        query(querSql1);
    }

}

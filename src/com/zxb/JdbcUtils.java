package com.zxb;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
public class JdbcUtils {
    private static String url=null;
    private static String driver=null;
    private static String user=null;
    private static String pwd=null;
    static {
        try {
            InputStream in = JdbcUtils.class.getClassLoader().getResourceAsStream("db.properties");
            Properties properties=new Properties();
            properties.load(in);
            url=properties.getProperty("url");
            driver=properties.getProperty("driver");
            user=properties.getProperty("user");
            pwd=properties.getProperty("pwd");
            //加载驱动
            Class.forName(driver);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
      return  DriverManager.getConnection(url,user,pwd);
    }
    public static void disConnected(Connection conn, Statement state, ResultSet res){

        if(res!=null){
            try {
                res.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);

            }
        }
        if(state!=null){

            try {
                state.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }
        if(conn!=null){
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                throw new RuntimeException(e);
            }
        }

    }
}

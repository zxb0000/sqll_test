package com.zxb;

import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.*;
import java.util.Properties;
public class Jdbc_DBCP_Utils {

    static DataSource dataSource=null;
    static {
        try {
            InputStream in = Jdbc_DBCP_Utils.class.getClassLoader().getResourceAsStream("dbcp.properties");
            Properties properties=new Properties();
            properties.load(in);
            //创建数据源
            dataSource= BasicDataSourceFactory.createDataSource(properties);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public static Connection getConnection() throws SQLException {
        return  dataSource.getConnection();//从数据源中获连接
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

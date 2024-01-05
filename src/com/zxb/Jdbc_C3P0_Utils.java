package com.zxb;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.apache.commons.dbcp.BasicDataSourceFactory;

import javax.sql.DataSource;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class Jdbc_C3P0_Utils {
    static DataSource dataSource=null;
    static {
        try {

            //创建数据源
             //使用自定义配置。不用配置文件的配置
            /*ComboPooledDataSource comboPooledDataSource=new ComboPooledDataSource();
            comboPooledDataSource.setJdbcUrl("");
            comboPooledDataSource.setAcquireIncrement(5);*/
            //使用.xml配置
            dataSource= new ComboPooledDataSource("MySQL");

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

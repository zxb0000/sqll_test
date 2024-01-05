package com.zxb;

import java.sql.*;

public class Tess_sql {

    public static void main(String[] args) {
       dengLu("admin","admin");
    }
    public static void dengLu(String user,String pwd){

        Connection connection=null;
        ResultSet resultSet=null;
        PreparedStatement statement=null;
        int i=0;
        try {
            connection=JdbcUtils.getConnection();
            connection.setAutoCommit(false);
            //statement = connection.createStatement();不需要创建Statement
            String sql="select * from user where user=? and pwd=?;";
            statement=connection.prepareStatement(sql);
            statement.setString(1,user);
            statement.setString(2,pwd);
            resultSet= statement.executeQuery();
            connection.commit();
            while (resultSet.next()){
                System.out.println(resultSet.getString("user"));
                System.out.println(resultSet.getString("pwd"));
            }



        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.disConnected(connection,statement,resultSet);
        }
    }
}

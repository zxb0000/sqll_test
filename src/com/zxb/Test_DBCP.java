package com.zxb;

import java.sql.*;

public class Test_DBCP {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Jdbc_DBCP_Utils.getConnection();

            String sql = "select * from user";

            statement = connection.prepareStatement(sql);
            resultSet = statement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString("user"));
                System.out.println(resultSet.getString("pwd"));
            }
        } catch(SQLException ex){
            throw new RuntimeException(ex);
        } finally{
            JdbcUtils.disConnected(connection, statement, resultSet);
        }
    }
}
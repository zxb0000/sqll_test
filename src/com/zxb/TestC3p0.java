package com.zxb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TestC3p0 {
    public static void main(String[] args) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = Jdbc_C3P0_Utils.getConnection();

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

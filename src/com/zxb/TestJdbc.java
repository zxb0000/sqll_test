package com.zxb;

import javax.sound.midi.Soundbank;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TestJdbc {
    public static void main(String[] args) {
        Connection connection=null;
        Statement statement=null;
        ResultSet resultSet=null;
        try {
            connection = JdbcUtils.getConnection();
            statement=connection.createStatement();
            String sql="select * from student1";

           resultSet= statement.executeQuery(sql);
           while (resultSet.next())
           {
               System.out.println(resultSet.getString("id"));

               System.out.println("============================================");
           }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }finally {
            JdbcUtils.disConnected(connection,statement,resultSet);
        }
    }
}

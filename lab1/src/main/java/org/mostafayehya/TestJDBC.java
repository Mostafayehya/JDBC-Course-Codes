package org.mostafayehya;

import java.sql.*;

public class TestJDBC {

    public static void main(String[] args) {
        try {
            String url = "jdbc:mysql://localhost:3306/sakila";
            String user = "root";
            String password = "root";
            Connection connection = DriverManager.getConnection(url, user, password);
            Statement statement = connection.createStatement();
            String query = "select * from actor";
            ResultSet rs = statement.executeQuery(query);
            while (rs.next()){
                System.out.println(rs.getString(1) +" " + rs.getString(2)+ " " + rs.getString(3));
            }
            statement.close();
            connection.close();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }
}

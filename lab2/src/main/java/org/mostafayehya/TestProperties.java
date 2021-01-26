package org.mostafayehya;

import javax.sql.DataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

import com.mysql.cj.jdbc.MysqlDataSource;


public class TestProperties {

    public static DataSource getDataSource() {
        FileInputStream fis = null;
        Properties properties = new Properties();
        MysqlDataSource mysqlDataSource = null;

        try {
            fis = new FileInputStream("db.properties");
            properties.load(fis);
            mysqlDataSource = new MysqlDataSource();

            mysqlDataSource.setURL(properties.getProperty("MYSQL_URL"));
            mysqlDataSource.setUser(properties.getProperty("MYSQL_USERNAME"));
            mysqlDataSource.setPassword(properties.getProperty("MYSQL_PASSWORD"));

        } catch (IOException e) {
            e.printStackTrace();
        }
        return mysqlDataSource;

    }

    public static void main(String[] args) {
        testDataSource();

    }

    private static void testDataSource() {

        DataSource dataSource = null;
        dataSource = getDataSource();
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;


        try {
            connection = dataSource.getConnection();
            preparedStatement = connection.prepareStatement("select * from actor where actor_id=?");
            preparedStatement.setInt(1, 5);
            resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                System.out.println("Actor ID :" + resultSet.getInt("actor_id") + " " + "First Name : " + resultSet.getString("first_name"));
            }


        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                if (resultSet != null)
                    resultSet.close();
                if (preparedStatement != null)
                    preparedStatement.close();
                if (connection != null)
                    connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
    }

}
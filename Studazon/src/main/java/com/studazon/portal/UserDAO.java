package com.studazon.portal;

import java.sql.*;

public class UserDAO {

    public User checkLogin(String email, String password) throws SQLException,
            ClassNotFoundException {
        String jdbcURL = "jdbc:mysql://studazon-db.cxxfrkbvifj5.us-east-1.rds.amazonaws.com:3306/studazon";
        String dbUser = "admin";
        String dbPassword = "Software2023!";
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
        String sql = "SELECT * FROM user WHERE email = ? and password = ?";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);

        ResultSet result = statement.executeQuery();

        User user = null;

        if (result.next()) {
            user = new User();
            user.setFullname(result.getString("fullname"));
            user.setEmail(email);
        }

        connection.close();

        return user;
    }
    public void registerUser(String fullname, String email, String password) throws SQLException,
            ClassNotFoundException {
        String jdbcURL = "jdbc:mysql://studazon-db.cxxfrkbvifj5.us-east-1.rds.amazonaws.com:3306/studazon";
        String dbUser = "admin";
        String dbPassword = "Software2023!";
        //System.out.println(fullname + email);
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection connection = DriverManager.getConnection(jdbcURL, dbUser, dbPassword);
        String sql = "INSERT INTO user (fullName, email, password) VALUES (fullName = ?, email = ?, password = ?)";
        PreparedStatement statement = connection.prepareStatement(sql);
        statement.setString(1, fullname);
        statement.setString(2, email);
        statement.setString(3, password);

        int result = statement.executeUpdate();

        System.out.println(result);

        // Should probably verify that the information was added to database before closing
        // I think the SQLException should throw any errors if there were any when attempting to send.

        connection.close();

    }
}
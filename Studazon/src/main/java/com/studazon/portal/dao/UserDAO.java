package com.studazon.portal.dao;

import com.studazon.portal.entity.User;
import org.apache.commons.codec.digest.Crypt;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.*;
import java.util.Properties;

public class UserDAO {

    protected static Properties props;

    protected static Connection getConnection() {
        props = new Properties();
        Connection currentCon = null;

        try {
            props.load(UserDAO.class.getClassLoader().getResourceAsStream("database.properties"));
            Class.forName("com.mysql.cj.jdbc.Driver");
            currentCon = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.username"), props.getProperty("db.password"));
        } catch (IOException | SQLException | ClassNotFoundException e) {
            System.out.println("Error loading database.properties: " + e);
            throw new RuntimeException(e);
        }
        return currentCon;
    }

    public static void registerUser(User user) throws SQLException, ClassNotFoundException, NoSuchAlgorithmException, NoSuchProviderException {

        Connection currentCon = getConnection();

        String sql = "INSERT INTO user (fullName, email, password) VALUES (?,?,?)";
        PreparedStatement statement = currentCon.prepareStatement(sql);


        statement.setString(1, user.getFullname());
        statement.setString(2, user.getEmail());
        statement.setString(3, Crypt.crypt(user.getPassword(), "$1$SZ"));

        int result = statement.executeUpdate();

        currentCon.close();
    }

    public User checkLogin(String email, String password) throws SQLException, ClassNotFoundException {
        Connection currentCon = getConnection();

        String sql = "SELECT * FROM user WHERE email = ? and password = ?";
        PreparedStatement statement = currentCon.prepareStatement(sql);
        statement.setString(1, email);
        statement.setString(2, password);

        ResultSet result = statement.executeQuery();

        User user = null;

        if (result.next()) {
//            user = new User();
            user.setFullname(result.getString("fullname"));
            user.setEmail(email);
        }
//        IF true = password matches
//        IF false = password did not match
//        if(Crypt.crypt(password,"$1$SZ").equals(user.getPassword()));

        currentCon.close();

        return user;
    }

    public static User findByEmail(String email) throws SQLException {
        User user = null;

        Connection currentCon = getConnection();
        String sql = "SELECT * FROM user WHERE email = ?";
        PreparedStatement statement = currentCon.prepareStatement(sql);
        statement.setString(1, email);


        try (ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                user = new User();
                String name = rs.getString("fullName");
                String emailFound = rs.getString("email");
                String password = rs.getString("password");

                // create a new User object using the retrieved values
                user.setEmail(emailFound);
                user.setPassword(password);
                user.setFullname(name);
            }
        } catch (SQLException exception) {
            System.err.println("UserDAO (findByEmail): ERROR: " + exception);
        }
        currentCon.close();
        return user;
    }
}
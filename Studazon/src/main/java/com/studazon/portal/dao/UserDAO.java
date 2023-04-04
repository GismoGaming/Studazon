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
    private static final String UPDATE_USER_SQL = "UPDATE users SET fullname=?,email=?,password=? WHERE id=?";

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

        String sql = "INSERT INTO users (fullname, email, password) VALUES (?,?,?)";
        PreparedStatement statement = currentCon.prepareStatement(sql);


        statement.setString(1, user.getFullname());
        statement.setString(2, user.getEmail());
        statement.setString(3, Crypt.crypt(user.getPassword(), "$1$SZ"));

        int result = statement.executeUpdate();

        currentCon.close();
    }

    public static User findByEmail(String email) throws SQLException {
        User user = null;

        Connection currentCon = getConnection();
        String sql = "SELECT * FROM users WHERE email = ?";
        PreparedStatement statement = currentCon.prepareStatement(sql);
        statement.setString(1, email);


        try (ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                user = new User();
                String id = rs.getString("id");
                String name = rs.getString("fullname");
                String emailFound = rs.getString("email");
                String password = rs.getString("password");

                // create a new User object using the retrieved values
                user.setId(Integer.parseInt(id));
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

    public static void updateUser(User user) throws SQLException {

        Connection currentCon = getConnection();

        PreparedStatement statement = currentCon.prepareStatement(UPDATE_USER_SQL);

        statement.setString(1, user.getFullname());
        statement.setString(2, user.getEmail());
        statement.setString(3, Crypt.crypt(user.getPassword(), "$1$SZ"));
        statement.setInt(4, user.getId());

        statement.executeUpdate();

        currentCon.close();
    }
}
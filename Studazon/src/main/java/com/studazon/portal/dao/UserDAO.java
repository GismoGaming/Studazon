package com.studazon.portal.dao;

import com.studazon.portal.entity.User;
import org.apache.commons.codec.digest.Crypt;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.*;
import java.util.Properties;
import java.util.UUID;

public class UserDAO {

    protected static Properties props;
    private static final String UPDATE_USER_SQL = "UPDATE users SET fullname=? WHERE id=?";
    private static final String UPDATE_USER_W_PSWD_SQL = "UPDATE users SET fullname=?,password=? WHERE id=?";
    private static final String UPDATE_USER_PASS_FORGOT_PASS = "UPDATE users SET password=? WHERE email=? AND security_question=? AND security_answer=?";
    private static final String FIND_USER_BY_ID_SQL = "SELECT * FROM users WHERE id = ?";
    private static final String FIND_MATCH_FORGOT_PASS = "SELECT * FROM users WHERE EXISTS (SELECT * FROM users WHERE email=? AND security_question=? AND security_answer=?)";


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

        String sql = "INSERT INTO users (fullname, email, password, security_question, security_answer) VALUES (?,?,?,?,?)";
        PreparedStatement statement = currentCon.prepareStatement(sql);


        statement.setString(1, user.getFullname());
        statement.setString(2, user.getEmail());
        statement.setString(3, Crypt.crypt(user.getPassword(), "$1$SZ"));
        statement.setString(4, user.getSecret_question());
        statement.setString(5, Crypt.crypt(user.getSecret_answer(),"$1$SZ"));

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
                String secret_question = rs.getString("security_question");
                String secret_answer = rs.getString("security_answer");

                // create a new User object using the retrieved values
                user.setId(Integer.parseInt(id));
                user.setEmail(emailFound);
                user.setPassword(password);
                user.setFullname(name);
                user.setSecret_question(secret_question);
                user.setSecret_answer(secret_answer);
            }
        } catch (SQLException exception) {
            System.err.println("UserDAO (findByEmail): ERROR: " + exception);
        }
        currentCon.close();
        return user;
    }

    public static void updateUser(User user, boolean passwordFlag) throws SQLException {

        Connection currentCon = getConnection();

        PreparedStatement statement = null;

        if (passwordFlag) {
            statement = currentCon.prepareStatement(UPDATE_USER_W_PSWD_SQL);
            statement.setString(1, user.getFullname());
            statement.setString(2, Crypt.crypt(user.getPassword(), "$1$SZ"));
            statement.setInt(3, user.getId());
        } else {
            statement = currentCon.prepareStatement(UPDATE_USER_SQL);
            statement.setString(1, user.getFullname());
            statement.setInt(2, user.getId());
        }
        statement.executeUpdate();
        currentCon.close();
    }

    public static User getUserById(int id) {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement(FIND_USER_BY_ID_SQL)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                User user = new User(rs.getString("fullname"), rs.getString("email"), rs.getString("password"), rs.getString("security_question"), rs.getString("security_answer"));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean findUserMatchForgotPass(String email, String security_question, String security_answer) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(FIND_MATCH_FORGOT_PASS);

        stmt.setString(1, email);
        stmt.setString(2, security_question);
        stmt.setString(3, Crypt.crypt(security_answer, "$1$SZ"));

        try (ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                System.err.println("UserDAO (findUserMatchForgotPass): ERROR: " + rs);
                return true;
            }
        } catch (SQLException exception) {
            System.err.println("UserDAO (findUserMatchForgotPass): ERROR: " + exception);
        }
        conn.close();
        return false;


    }
    public static void updateUserForgotPass(String email, String security_question, String security_answer, String temp_password) throws SQLException {
        Connection conn = getConnection();
        PreparedStatement stmt = conn.prepareStatement(UPDATE_USER_PASS_FORGOT_PASS);

        stmt.setString(1, temp_password);
        stmt.setString(2, email);
        stmt.setString(3, security_question);
        stmt.setString(4, Crypt.crypt(security_answer, "$1$SZ"));
        try {
            stmt.executeUpdate();

        } catch (SQLException e) {
            System.err.println("UserDAO (updateUserForgotPass): ERROR: " + e);
        }
        conn.close();
    }



}
package com.studazon.portal.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class BookDAO {
    protected static Properties props;

    protected static Connection getConnection() {
        props = new Properties();
        Connection currentCon = null;

        try {
            props.load(BookDAO.class.getClassLoader().getResourceAsStream("database.properties"));
            Class.forName("com.mysql.cj.jdbc.Driver");
            currentCon = DriverManager.getConnection(props.getProperty("db.url"), props.getProperty("db.username"), props.getProperty("db.password"));
        } catch (IOException | SQLException | ClassNotFoundException e) {
            System.out.println("Error loading database.properties: " + e);
            throw new RuntimeException(e);
        }
        return currentCon;
    }
    // retrieve all books
    // create a book for a User
    // retrieve a specific book
    // update a specific book
    // delete a specific book
}




package com.studazon.portal.api;

import com.studazon.portal.dao.UserDAO;
import com.studazon.portal.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.commons.codec.digest.Crypt;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "login", value = "/login")
public class login extends HttpServlet {
    @Override
    public void init() {
        System.out.println("Login (init): Servlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("user") != null) {
            response.sendRedirect("dash");
        } else {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        try {
            User userRecord = UserDAO.findByEmail(email);
            if (null == userRecord) {
                request.setAttribute("status", "failed");
                request.setAttribute("message", "User not found");
                request.getRequestDispatcher("login.jsp").include(request, response);
                return;
            }
            if (!Crypt.crypt(password, "$1$SZ").equals(userRecord.getPassword())) {
                request.setAttribute("status", "failed");
                request.setAttribute("message", "Incorrect Password");
                request.getRequestDispatcher("login.jsp").include(request, response);
                return;
            }
            HttpSession session = request.getSession(false);
            if (session != null) {
                // Invalidate the old session
                session.invalidate();
            }

            session = request.getSession(true);
            session.setAttribute("user", userRecord);
            session.setMaxInactiveInterval(200 * 60); // Set session timeout to 2 minutes
            System.out.println("Login (POST) : " + userRecord.getId() + " logged in successfully!");
            response.sendRedirect("dash");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}

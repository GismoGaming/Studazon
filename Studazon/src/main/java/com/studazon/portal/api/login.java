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
    private static final long serialVersionUID = 1L;

    public login() {
        super();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        UserDAO userDao = new UserDAO();

        // find the user by email
        //   - if not found, send error back to login
        // check the password
        //   - if not correct, send error back to login
        // save user to session
        // redirect to dash

        // TODO find the the user
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

            System.out.println("Login success");
            HttpSession session = request.getSession();
            session.setAttribute("user", userRecord.getFullname());
            response.sendRedirect("dash");

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}

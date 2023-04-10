package com.studazon.portal.api;

import com.studazon.portal.dao.UserDAO;
import com.studazon.portal.entity.User;
import com.studazon.portal.util.Mailer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.mail.MessagingException;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.sql.SQLException;

@WebServlet(name = "register", value = "/register")
public class register extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("register.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String fullName = request.getParameter("fullname");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        User user = new User(fullName, email, password);

        try {
            UserDAO.registerUser(user);
            Mailer mailer = new Mailer();
            mailer.setTo_name(user.getFullname());
            mailer.setTo_email(user.getEmail());
            mailer.setType_of_email("new_account");
            mailer.send();
            response.sendRedirect("login.jsp");
        } catch (ClassNotFoundException | NoSuchAlgorithmException | SQLException ex) {
            throw new ServletException(ex);
        } catch (NoSuchProviderException | MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}

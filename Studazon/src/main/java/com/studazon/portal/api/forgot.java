package com.studazon.portal.api;

import com.studazon.portal.dao.UserDAO;
import com.studazon.portal.entity.User;
import com.studazon.portal.util.Mailer;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "forgot", value = "/forgot")
public class forgot extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("forgot.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email = request.getParameter("email");
        String secret_question = request.getParameter("security-question");
        String secret_answer = request.getParameter("secret-word");

        try {
            String new_password = UserDAO.updateUserForgotPass(email, secret_question, secret_answer);
            if (new_password == null){
                System.err.println("This shouldn't be null - There is an error");
            }
            User user = UserDAO.findByEmail(email);
            Mailer mailer = new Mailer();
            mailer.setTo_name(user.getFullname());
            mailer.setTo_email(user.getEmail());
            mailer.setType_of_email("forgot_password");
            mailer.setNew_password(new_password);
            mailer.send();
            response.sendRedirect("login.jsp");
        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }
}

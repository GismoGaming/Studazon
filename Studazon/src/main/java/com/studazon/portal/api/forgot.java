package com.studazon.portal.api;

import com.studazon.portal.dao.UserDAO;
import com.studazon.portal.entity.User;
import com.studazon.portal.util.Mailer;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import org.apache.commons.codec.digest.Crypt;

import javax.mail.MessagingException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.UUID;

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

        // Generate a random UUID
        UUID uuid = UUID.randomUUID();
        // Get the UUID as a string
        String uuidString = uuid.toString();
        // Remove the hyphens from the UUID string
        String withoutHyphens = uuidString.replaceAll("-", "");
        // Take the first 8 characters as the random password
        String unsalted_password = withoutHyphens.substring(0, 8);
        // Salt it
        String temp_password = Crypt.crypt(unsalted_password, "$1$SZ");

        try {
            User user = UserDAO.findByEmail(email);
            if (user == null){
                request.setAttribute("status", "failed");
                request.setAttribute("message", "Email not found");
                request.getRequestDispatcher("forgot.jsp").include(request, response);
            }

            Boolean user_match = UserDAO.findUserMatchForgotPass(email, secret_question, secret_answer);
            if (user_match == true){
                UserDAO.updateUserForgotPass(email, secret_question, secret_answer, temp_password);

                Mailer mailer = new Mailer();
                mailer.setTo_name(user.getFullname());
                mailer.setTo_email(user.getEmail());
                mailer.setType_of_email("forgot_password");
                mailer.setNew_password(unsalted_password);
                mailer.send();
                response.sendRedirect("login.jsp");
            }

            request.setAttribute("status", "failed");
            request.setAttribute("message", "Match not found");
            request.getRequestDispatcher("forgot.jsp").include(request, response);

        } catch (SQLException ex) {
            throw new ServletException(ex);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

    }

}

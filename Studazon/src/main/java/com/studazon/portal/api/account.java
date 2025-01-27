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

@WebServlet(name = "account", value = "/account")
public class account extends HttpServlet {
    @Override
    public void init() {
        System.out.println("Account (init): Servlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("user") == null) {
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
        request.setAttribute("user", request.getSession().getAttribute("user"));
        request.getRequestDispatcher("account.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action.equals("update_profile")) {
            try {
                update_profile(request, response);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void update_profile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        String fullname = request.getParameter("fullname");
        String old_pswd = request.getParameter("old_password");
        String new_pswd = request.getParameter("new_password");
        boolean passwordFlag = false;

        if (fullname == null || fullname.isEmpty()) {
            request.setAttribute("status", "failed");
            request.setAttribute("message", "Invalid request");
            request.getRequestDispatcher("account.jsp").include(request, response);
            return;
        }

        User userRecord = (User) request.getSession().getAttribute("user");
        userRecord.setFullname(fullname);

        if (old_pswd != null && !old_pswd.isEmpty() && new_pswd != null && !new_pswd.isEmpty()) {
            if (!Crypt.crypt(old_pswd, "$1$SZ").equals(userRecord.getPassword())) {
                request.setAttribute("status", "failed");
                request.setAttribute("message", "Incorrect Password");
                request.getRequestDispatcher("account.jsp").include(request, response);
                return;
            }
            userRecord.setPassword(new_pswd);
            passwordFlag = true;
        }

        UserDAO.updateUser(userRecord, passwordFlag);
        request.setAttribute("user", userRecord);

        request.setAttribute("status", "success");
        request.setAttribute("message", "Profile Updated!");
        request.getRequestDispatcher("account.jsp").include(request, response);

    }
}

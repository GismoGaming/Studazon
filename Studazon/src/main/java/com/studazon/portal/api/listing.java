package com.studazon.portal.api;

import com.studazon.portal.dao.BookDAO;
import com.studazon.portal.entity.Book;
import com.studazon.portal.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "listing", value = "/listing")
public class listing extends HttpServlet {
    @Override
    public void init() {
        System.out.println("Listing (init): Servlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("user");
        List<Book> books = BookDAO.getAllBooks(user.getId());
        request.setAttribute("books", books);
        request.getRequestDispatcher("listing.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

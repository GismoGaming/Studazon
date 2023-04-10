package com.studazon.portal.api;

import com.studazon.portal.dao.BookDAO;
import com.studazon.portal.entity.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "dash", value = "/dash")
public class dash extends HttpServlet {
    @Override
    public void init() {
        System.out.println("Dash (init): Servlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("searchQuery");
        List<Book> books;
        if (searchQuery == null || searchQuery.equals("")) {
            books = BookDAO.getAllBooks();
        } else {
            books = BookDAO.getAllBooks(searchQuery);
        }
        request.setAttribute("books", books);
        request.getRequestDispatcher("dash.jsp").include(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) {
    }
}

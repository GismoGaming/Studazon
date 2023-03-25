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
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = BookDAO.getAllBooks();
        request.setAttribute("books", books);
        request.getRequestDispatcher("dash.jsp").forward(request, response);
//        request.getRequestDispatcher("dash.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

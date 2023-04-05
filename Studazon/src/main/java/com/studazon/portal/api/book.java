package com.studazon.portal.api;

import com.studazon.portal.dao.BookDAO;
import com.studazon.portal.entity.Book;
import com.studazon.portal.entity.User;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet(name = "book", value = "/book")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 1000,
        maxFileSize = 1024 * 1024 * 1000,
        maxRequestSize = 1024 * 1024 * 1000
)
public class book extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        HttpSession session = request.getSession();
        String user = (String) session.getAttribute("user");
        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        switch (action) {
            case "view":
                viewBook(request, response);
                break;
            case "create":
                createBook(request, response);
                break;
            case "update":
                updateBook(request, response);
                break;
            case "delete":
                deleteBook(request, response);
                break;
            default:
                listBooks(request, response);
                break;
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Part actionPart = request.getPart("action");
        String action = null;
        if (actionPart != null) {
            action = new String(actionPart.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
        }
        System.out.println("Book (POST) request received: " + action);
        if ("create".equals(action)) {
            createBook(request, response);
        } else if ("update".equals(action)) {
            updateBook(request, response);
        } else if ("delete".equals(action)) {
            deleteBook(request, response);
        } else {
            listBooks(request, response);
        }
    }

    private void listBooks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Book> books = BookDAO.getAllBooks();
        request.setAttribute("books", books);
        request.getRequestDispatcher("dash.jsp").forward(request, response);
    }

    private void viewBook(HttpServletRequest request, HttpServletResponse response) {
    }

    private void editBook(HttpServletRequest request, HttpServletResponse response) {
    }

    //    Post Actions
    private void createBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String title = request.getParameter("title");
        String author = request.getParameter("author");
        String isbn = request.getParameter("isbn");
        String book_condition = request.getParameter("condition");
        String comments = request.getParameter("comments");
        String price = request.getParameter("price");

        // read the image file as a byte array
        Part imagePart = request.getPart("imageUrl");
        InputStream imageStream = imagePart.getInputStream();
        byte[] imageData = imageStream.readAllBytes();

        System.out.println("Book (createBook): Title: " + title + " Author: " + author + " ISBN: " + isbn + " BookCondition: " + book_condition + " Comments: " + comments + "Price: " + price);
        if (null == title || null == author || null == isbn || null == book_condition || null == comments) {
            request.setAttribute("status", "failed");
            request.setAttribute("message", "Invalid (Blank) Request");
            request.getRequestDispatcher("dash.jsp").include(request, response);
            return;
        }

        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Book book = new Book(user.getId(), title, author, isbn, book_condition, imageData, comments, Double.parseDouble(price));
        BookDAO.insertBook(book);
        request.setAttribute("status", "success");
        request.setAttribute("message", title + " is added to listings");
        response.sendRedirect("dash");
    }

    private void updateBook(HttpServletRequest request, HttpServletResponse response) {
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) {
    }

}

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
    public void init() {
        System.out.println("Book (init): Servlet initialized");
    }

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

    private void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println(request.getParameter("edit-id"));
        int id = Integer.parseInt(request.getParameter("edit-id"));
        System.out.println(id);
        String title = request.getParameter("edit-title");
        String author = request.getParameter("edit-author");
        String isbn = request.getParameter("edit-isbn");
        String book_condition = request.getParameter("edit-condition");
        String comments = request.getParameter("edit-comments");
        double price = Double.parseDouble(request.getParameter("edit-price"));

//        // read the image file as a byte array
//        Part imagePart = request.getPart("edit-imageUrl");
//        InputStream imageStream = imagePart.getInputStream();
//        byte[] imageData = imageStream.readAllBytes();

        System.out.println("Book (updateBook): Title: " + title + " Author: " + author + " ISBN: " + isbn + " BookCondition: " + book_condition + " Comments: " + comments + "Price: " + price);
        if (null == title || null == author || null == isbn || null == book_condition || null == comments) {
            request.setAttribute("status", "failed");
            request.setAttribute("message", "Invalid (Blank) Request");
            request.getRequestDispatcher("dash.jsp").include(request, response);
            return;
        }

        Book book = BookDAO.getBookById(id);

        book.setTitle(title);
        book.setAuthor(author);
        book.setISBN(isbn);
        book.setBook_condition(book_condition);
        book.setComments(comments);
        book.setPrice(price);
        BookDAO.updateBook(book);


        request.setAttribute("status", "success");
        request.setAttribute("message", title + " is modified");
        response.sendRedirect("listing");
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        System.out.println(request.getParameter("edit-id"));
        int id = Integer.parseInt(request.getParameter("edit-id"));
        BookDAO.deleteBook(id);
        request.setAttribute("status", "success");
        request.setAttribute("message", request.getParameter("edit-title") + " is deleted");
        response.sendRedirect("listing");
    }

}

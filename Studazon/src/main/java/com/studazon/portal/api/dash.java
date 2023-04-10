package com.studazon.portal.api;

import com.studazon.portal.dao.BookDAO;
import com.studazon.portal.dao.UserDAO;
import com.studazon.portal.entity.Book;
import com.studazon.portal.entity.User;
import com.studazon.portal.util.Mailer;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.mail.MessagingException;
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
        System.out.println("Dash (get): Request received");
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
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Dash (post): Request received");
        String action = request.getParameter("action");

        if ("SendInterestWONotes".equals(action)) {
            sendInterestWithoutNotes(request, response);
        } else if ("SendInterestWNotes".equals(action)) {
            sendInterestWithNotes(request, response);
        }  // Handle other actions or redirect to an error page

    }

    private void sendInterestWithoutNotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Dash (sendInterestWithoutNotes): Request: " + request);
        int bookID = Integer.parseInt(request.getParameter("bookID"));
        User buyerUser = (User) request.getSession().getAttribute("user");
        Book book = (Book) BookDAO.getBookById(bookID);

        assert book != null;
        int seller_id = book.getUserId();
        User sellerUser = UserDAO.getUserById(seller_id);

        try {
            Mailer mailer = new Mailer();
            mailer.setTo_name(sellerUser.getFullname());
            mailer.setTo_email(sellerUser.getEmail());
            mailer.setBuyer_name(buyerUser.getFullname());
            mailer.setBuyer_email(buyerUser.getEmail());
            mailer.setType_of_email("book_interest");
            mailer.setBook(book);
            mailer.send();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        // Redirect to a success page or the next step
        request.setAttribute("status", "success");
        request.setAttribute("message", "Your interest has been sent to the seller!");
        request.getRequestDispatcher("dash.jsp").include(request, response);
    }

    private void sendInterestWithNotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Dash (sendInterestWithNotes): Request: " + request);
        String notes = request.getParameter("send-note");
        System.out.println("Book: " + request.getParameter("notes_bookID"));
        int bookID = Integer.parseInt(request.getParameter("notes_bookID"));
        User buyerUser = (User) request.getSession().getAttribute("user");
        Book book = (Book) BookDAO.getBookById(bookID);

        assert book != null;
        int seller_id = book.getUserId();
        User sellerUser = UserDAO.getUserById(seller_id);

        try {
            Mailer mailer = new Mailer();
            mailer.setTo_name(sellerUser.getFullname());
            mailer.setTo_email(sellerUser.getEmail());
            mailer.setBuyer_name(buyerUser.getFullname());
            mailer.setBuyer_email(buyerUser.getEmail());
            mailer.setType_of_email("book_interest_notes");
            mailer.setNotes(notes);
            mailer.setBook(book);
            mailer.send();
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        // Handle the Send Interest With Notes action here using the notes variable

        // Redirect to a success page or the next step
        request.setAttribute("status", "success");
        request.setAttribute("message", "Your interest (with your message) has been sent to the seller!");
        request.getRequestDispatcher("dash.jsp").include(request, response);
    }

}

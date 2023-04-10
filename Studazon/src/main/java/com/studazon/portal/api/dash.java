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
        String bookID = request.getParameter("bookID");
        User user = (User) request.getSession().getAttribute("user");

        // Handle the Send Interest Without Notes action here
        System.out.println("dash (sendInterestWithoutNotes) BookId: " + bookID);

        // Redirect to a success page or the next step
        request.setAttribute("status", "success");
        request.setAttribute("message", "Your interest has been sent to the seller!");
        request.getRequestDispatcher("dash.jsp").include(request, response);
    }

    private void sendInterestWithNotes(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        System.out.println("Dash (sendInterestWithNotes): Request: " + request);
        User user = (User) request.getSession().getAttribute("user");

        String notes = request.getParameter("send-note");
        System.out.println("dash(sendInterestWithnotes) Notes: " + notes);
        // Handle the Send Interest With Notes action here using the notes variable

        // Redirect to a success page or the next step
        request.setAttribute("status", "success");
        request.setAttribute("message", "Your interest (with your message) has been sent to the seller!");
        request.getRequestDispatcher("dash.jsp").include(request, response);
    }

}

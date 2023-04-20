package com.studazon.portal.api;

import com.google.gson.Gson;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(name = "api", value = "/api/*")
public class api extends HttpServlet {
    @Override
    public void init() {
        System.out.println("API (init): Servlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String pathInfo = request.getPathInfo();
        String apiKey = request.getParameter("apiKey");
        PrintWriter out = response.getWriter();


        if (apiKey == null || pathInfo == null) {
            out.println("Missing API Key / Path");
            out.flush();
            return;
        }
        String[] parts = pathInfo.split("/");
        if ("get_books".equalsIgnoreCase(parts[parts.length - 1])) {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            Gson gson = new Gson();
            out.print("This api service has been terminated");
            out.flush();
            return;
        }
        out.println("Invalid Path");
        out.flush();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}

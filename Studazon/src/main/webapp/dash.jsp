<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ page import="java.util.Base64" %>

<!DOCTYPE html>
<html>
<% if (session.getAttribute("user") == null) {
    response.sendRedirect("index");
}%>
<head>
    <%@include file="header.jsp" %>
    <link rel="stylesheet" href="./assets/css/SHARED.css">
    <link rel="stylesheet" href="assets/css/dash.css">
    <title>Studazon - Dashboard</title>
</head>
<body>
<div class="background_circle">
    <form action="book">
        <input type="image" class="img_account" src="./assets/Interactivity/account_circle.png" alt="Account Icon">
    </form>
    <div>
        <form action="book">
            <input type="image" class="img_notification" src="./assets/Interactivity/Notification%20Popup.png"
                   alt="Notification Icon">
        </form>
    </div>
</div>
<div class="background_square">

    <img class="logo" src="./assets/banner/Studazon%20-%20Banner%20Style.svg" alt="Studazon Logo">
    <div class="search-container">
        <form action="book">
            <input type="image" src="./assets/Interactivity/Search%20Icon.png" alt="Search Icon">
            <input class="input-field" type="text" placeholder="Search for a book...">
        </form>
    </div>
</div>

<form action="book" method="post" enctype="multipart/form-data">
    <label for="title">Title:</label>
    <input type="text" id="title" name="title"/><br>

    <label for="author">Author:</label>
    <input type="text" id="author" name="author"/><br>

    <label for="isbn">ISBN:</label>
    <input type="text" id="isbn" name="isbn"/><br>

    <label for="condition">Condition:</label>
    <input type="text" id="condition" name="condition"/><br>

    <label for="imageUrl">Image:</label>
    <input type="file" id="imageUrl" name="imageUrl"/><br>

    <label for="comments">Comments</label>
    <textarea id="comments" name="comments"></textarea><br>

    <input type="hidden" name="action" value="create">
    <button id="login" type="submit">Create Listing</button>
</form>
<h1>All Books</h1>
<table>
    <tr>
        <th>Title</th>
        <th>Author</th>
        <th>ISBN</th>
        <th>Condition</th>
        <th>Image URL</th>
        <th>Comments</th>
    </tr>
    <c:forEach var="book" items="${books}">
        <tr>
            <td>${book.title}</td>
            <td>${book.author}</td>
            <td>${book.ISBN}</td>
            <td>${book.book_condition}</td>
            <td><img src="data:image/jpg;base64,${Base64.getEncoder().encodeToString(book.imageUrl)}"
                     alt="${book.title}"></td>
        </tr>
    </c:forEach>
</table>
</body>
s
</html>
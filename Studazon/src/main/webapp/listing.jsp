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
    <link rel="stylesheet" href="assets/css/listing.css">
    <title>Studazon - My Listings</title>
</head>
<body>
<%--<%@include file="navbar.jsp"%>--%>
<div class="background_circle">
    <form action="account">
        <input type="image" class="img_account" src="./assets/Interactivity/account_circle.png"
               alt="Account Icon">
    </form>
    <div>
        <form action="account-notifications">
            <input type="image" class="img_notification" src="./assets/Interactivity/Notification%20Popup.png"
                   alt="Notification Icon">
        </form>
    </div>
</div>

<div class="search-container">
    <button id="modal_open" type="button" onclick="openModal()">Create a Listing</button>
</div>

<form action="listing">
    <input type="image" class="btn_createListing" src="./assets/Interactivity/Create New.png"
           alt="Create new listing icon">
</form>

<form action="dash">
    <input type="image" class="btn_createListing" src="./assets/Interactivity/Home Icon.png"
           alt="Dashboard icon">
</form>

<form action="logout">
    <input type="image" class="btn_createListing" src="./assets/Interactivity/Logout%20Icon.png"
           alt="Logout icon">
</form>

<div class="listingBG">
    <div class="listingCard-row">
        <!-- Repeating code for card -->
        <c:forEach var="book" items="${books}">
            <div class="listingCard-column">
                <div class="listingCard" onclick="openModal()">

                    <div class="listing-picturePriceCond">
                        <img src="data:image/jpg;base64,${Base64.getEncoder().encodeToString(book.imageUrl)}"
                             alt="${book.title}">
                        <div class="listing-picturePriceCond_priceCond">
                            <span>${book.price}</span>
                            <span>${book.book_condition}</span>
                        </div>
                    </div>
                    <p>${book.title}</p>
                    <p><i>${book.author}</i></p>
                    <p><i>${book.ISBN}</i></p>
                </div>
            </div>
        </c:forEach>
        <!-- End Repeated -->
    </div>
</div>

<!-- The Modal -->
<div id="modalPopup" class="modal_bg">
    <!-- Modal content -->
    <div class="modal_content">
        <img id = "modal_close" class="modal_close" src = "assets/Interactivity/cancel.png">
        <h1>Create a Listing</h1>
        <form action="book" method="post" enctype="multipart/form-data">
            <input type="hidden" name="action" value="create">

            <input type="text" id="title" name="title" placeholder="Title" required>
            <input type="text" id="author" name="author" placeholder="Author" required>
            <input type="number" id="isbn" name="isbn" placeholder="ISBN" required>
            <label for="condition">Book Condition:</label>
            <select id="condition" name="condition">
                <option value="War Torn">War Torn</option>
                <option value="Used">Used</option>
                <option value="New">New</option>
            </select>
            <input type="number" id="price" name="price" placeholder="Price" step="0.01" required>
            <input type="file" accept="image/*" id="imageUrl" name="imageUrl" placeholder="Choose Book Image" required>
            <textarea type="text" id="comments" name="comments" placeholder="Comments" required></textarea>

            <button id="createListingBtn" type="submit">Create Listing</button>
        </form>
    </div>
</div>

<!-- <%@include file="footer.jsp" %> -->
<script src="./assets/js/modalPopup.js"></script>
</body>
</html>
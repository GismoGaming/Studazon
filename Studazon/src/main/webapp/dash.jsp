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
<div class="background_square">
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
        <form action="search-Book">
            <input type="image" src="./assets/Interactivity/Search%20Icon.png" alt="Search Icon">
            <input class="input-field" type="text" placeholder="Search for a book...">
        </form>
    </div>

    <form action="book-new">
        <input type="image" class="btn_createListing" src="./assets/Interactivity/Create New.png"
               alt="Create new listing icon">
    </form>
    <div class="listingBG">
        <div class="listingCard-row">
            <!-- Repeating code for card -->
            <c:forEach var="book" items="${books}">
            <div class="listingCard-column">
                <div class="listingCard" onclick= "location.href = 'TEMPbookURL';">

                    <div class="listing-picturePriceCond">
                        <img src="data:image/jpg;base64,${Base64.getEncoder().encodeToString(book.imageUrl)}"
                             alt="${book.title}">
                        <div class="listing-picturePriceCond_priceCond">
                            <span>TEMPbookPrice</span>
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
</div>
<!-- <%@include file="footer.jsp" %> -->
</body>
</html>
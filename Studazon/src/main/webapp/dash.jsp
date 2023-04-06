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
<div class="sidebar">
    <div class="background_circle">
        <form action="account">
            <input type="image" class="btn_sidebar" src="./assets/Interactivity/account_circle.png"
                   alt="Account Icon">
        </form>
        <div>
            <form action="account-notifications">
                <input type="image" class="img_notification" src="./assets/Interactivity/Notification%20Popup.png"
                       alt="Notification Icon">
            </form>
        </div>
    </div>
    <div class="background_circle">
        <form action="dash">
            <input type="image" class="btn_sidebar" src="./assets/Interactivity/home.png"
                   alt="Home Icon">
        </form>
    </div>
    <div class="background_circle">
        <form action="listing">
            <input type="image" class="btn_sidebar" src="./assets/Interactivity/Create%20New.png"
                   alt="Create New Listing Icon">
        </form>
    </div>
    <div class="background_circle_logout">
        <form action="logout">
            <input type="image" class="btn_sidebar_logout" src="./assets/Interactivity/logout.png"
                   alt="Logout Icon">
        </form>
    </div>
</div>
<div class="search-container">
    <form action="dash">
        <input type="image" src="./assets/Interactivity/Search%20Icon.png" alt="Search Icon">
        <input class="input-field" type="text" placeholder="Search for a book..." name="searchQuery"
               <% if(request.getParameter("searchQuery")!=null) { %>value="<%=request.getParameter("searchQuery")%>"<% } %>>
    </form>
</div>
    <div class="listingBG">
        <div class="listingCard-row">
            <!-- Repeating code for card -->
            <c:forEach var="book" items="${books}">
                <div class="listingCard-column">
                    <div class="listingCard" onclick="changeModal.call(this)" data-title="${book.title}" data-author="${book.author}" data-isbn="${book.ISBN}"
                         data-image= "${Base64.getEncoder().encodeToString(book.imageUrl)}" data-condition="${book.book_condition}" data-price="${book.price}" data-comments="${book.comments}">
                        <div class="listing-priceCond">
                            <div class="listing-price">${book.price}</div>
                            <div class="listing-cond">${book.book_condition}</div>
                        </div>
                        <img class="listing-picture"
                             src="data:image/jpg;base64,${Base64.getEncoder().encodeToString(book.imageUrl)}"
                             alt="${book.title}">
                        <p class="listing-text"><b>${book.title}</b></p>
                        <p class="listing-text"><i>${book.author}</i></p>
                    </div>
                </div>
            </c:forEach>
            <!-- End Repeated -->
        </div>
    </div>
<!-- Start modal popup -->
<div id="view-modalPopup" class="modal_bg">
    <!-- Modal content -->
    <div class="modal_content">
        <img id="modal_close" class="modal_close" src="assets/Interactivity/cancel.png" alt="Exit Modal Buttons">

        <!-- Begin modal data -->
        <div class="modal-masterContainer">
            <div class="modal-imageTitleISBNContainer">
                <img id = "view-img" class="modal-picture" src="./assets/Interactivity/cancel.png" alt="Modal Close Button">
                <p id = "view-title" class="modal-pictureText-title"><b>Book Title</b></p>
                <p id = "view-author" class="modal-pictureText-title">Book Author</p>
                <p id = "view-isbn" class="modal-pictureText-ISBN"><i>ISBN: 12-34-456789</i></p>
            </div>

            <div class="modal-condCommentsSend">
                <p class="modal-condComments-header"><i><b>Book condition:</b></i></p>
                <p id = "view-condition" class="modal-condComments-content">Practicaly new</p>

                <p class="modal-condComments-header"><i><b>Comments:</b></i></p>
                <div class = "modal-commentOverflow">
                <p id = "view-comments" class="modal-condComments-content">
                    sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
                    nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                </p>
            </div>
                <div class="modal-bookCostInterest">
                    <p id = "view-price" class="modal-bookCostInterest-bookCost"><i><b>$100</b></i></p>
                    <button class="modal-bookCostInterest-sendInterestButton">Send Interest</button>
                </div>
            </div>
        </div>
    </div>
</div>
    <!-- End modal popup -->

    <!-- <%@include file="footer.jsp" %> -->
    <script src="./assets/js/modalPopup-dash.js"></script>
    <script src="./assets/js/card-conditionChanger.js"></script>
<!-- <%@include file="footer.jsp" %> -->
</body>
</html>
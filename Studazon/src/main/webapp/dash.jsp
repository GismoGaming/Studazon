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
<%@include file="sideBar.jsp"%>
<%@include file="searchContainer.jsp"%>

    <div class="listingBG">
        <div class="listingCard-row">
            <!-- Repeating code for card -->
            <c:forEach var="book" items="${books}">
                <div class="listingCard-column">
                    <div class="listingCard" onclick="changeModal.call(this)" data-title="${book.title}" data-author="${book.author}" data-isbn="${book.ISBN}"
                         data-id = "${book.id}" data-image= "${Base64.getEncoder().encodeToString(book.imageUrl)}" data-condition="${book.book_condition}" data-price="${book.price}" data-comments="${book.comments}">
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

<!-- Start modal popup - View Listing -->
<div id="view-modalPopup" class="modal_bg">
    <!-- Modal content -->
    <div class="modal_content">
        <img id="modal_close" class="modal_close" src="assets/Interactivity/cancel.png" alt="Exit Modal Buttons">

        <!-- Begin modal data -->
        <div class="modal-masterContainer">
            <div class="modal-imageTitleISBNContainer">
                <img id = "view-img" class="modal-picture" src="./assets/Interactivity/cancel.png" alt="Modal Close Button">
                <p id = "view-title" class="modal-pictureText-title"><b>Book Title</b></p>
                <p id = "view-author" class="modal-pictureText-title modal-pictureText-author">Book Author</p>
                <p id = "view-isbn" class="modal-pictureText-ISBN"><i>ISBN: 12-34-456789</i></p>
            </div>

            <div class="modal-condCommentsSend">
                <p class="modal-condComments-header"><i><b>Book condition:</b></i></p>
                <p id = "view-condition" class="modal-condComments-content">Practicaly new</p>

                <p class="modal-condComments-header"><i><b>Comments:</b></i></p>
                <div>
                    <p id="view-comments" class="modal-condComments-content">
                        sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis
                        nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.
                    </p>
                </div>
                <div class="modal-bookCostInterest">
                    <p id="view-price" class="modal-bookCostInterest-bookCost"><i><b>$100</b></i></p>
                    <div class="modal-buttonFlex">
                        <form action="dash" method="post">
                            <input type="hidden" name="action" value="SendInterestWONotes">
                            <input type="hidden" id="view-bookID" name="bookID" value="">
                            <button id="sendInterest" type="submit" class="modal-bookCostInterest-sendInterestButton">
                                Send Interest
                            </button>
                        </form>
                        <div title="Send interest with a note attached"
                             class="modal-bookCostInterest-sendInterestButton-addNotes"
                             onclick="changeSendModal.call(this)">
                            <img class="modal-image-addNotes" src="assets/Interactivity/add_notes.png"
                                 alt="Add note to interest button">
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- End modal popup -->

<!-- Start modal popup - Send Notes -->
<div id="sendNotes-modalPopup" class="modal_bg">
    <!-- Modal content -->
    <div class="modal_content">
        <img id="sendNotes_modal_close" class="modal_close" src="assets/Interactivity/cancel.png"
             alt="Exit Modal Buttons">

        <!-- Begin modal data -->
        <div class="modal-masterContainerAddNote">
            <form action="dash">
                <label for="send-note">Add a message to the seller (optional):</label>
                <textarea id="send-note" name="send-note" required></textarea>
                <div style="text-align: right">
                    <button class="modal-sendWithNotes" id="sendNote" type="submit">Send Interest With Message</button>
                </div>
            </form>
        </div>
    </div>
</div>
<!-- End modal popup -->

    <!-- <%@include file="footer.jsp" %> -->
<script src="assets/js/modalPopup-dashViewListing.js"></script>
<script src="assets/js/modalPopup-dashSendNotes.js"></script>
    <script src="./assets/js/card-conditionChanger.js"></script>
<!-- <%@include file="footer.jsp" %> -->
</body>
</html>
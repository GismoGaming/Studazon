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
                <div class="listingCard" onclick="changeModal.call(this)" data-title="${book.title}" data-author="${book.author}" data-isbn="${book.ISBN}"
                     data-condition="${book.book_condition}" data-price="${book.price}" data-comments="${book.comments}" data-id="${book.id}">
                    <div class="listing-priceCond">
                        <div class="listing-price">$${book.price}</div>
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

<!-- The Create Listing Modal -->
<div id="modalPopup" class="modal_bg">
    <!-- Modal content -->
    <div class="modal_content">
        <img id="modal_close" class="modal_close" src="assets/Interactivity/cancel.png" alt="Close Button">
        <h1>Create a Listing</h1>
        <form action="book" method="post" enctype="multipart/form-data" id="myForm">
            <input type="hidden" name="action" value="create">

            <input type="text" id="title" name="title" placeholder="Title" required>
            <input type="text" id="author" name="author" placeholder="Author" required>
            <input type="number" id="isbn" name="isbn" placeholder="ISBN" required>

            <div class="condition_container">
                <label for="condition">Book Condition: </label>
                <select id="condition" name="condition">
                    <option value="War Torn">War Torn</option>
                    <option value="Used">Used</option>
                    <option value="New">New</option>
                </select>
            </div>

            <input type="number" id="price" name="price" placeholder="Price" step="0.01" required>

            <div class="image_container">
                <label for="imageUrl">Image: </label>
                <input type="file" accept="image/*" id="imageUrl" name="imageUrl" placeholder="Choose Book Image" required>
            </div>

            <textarea type="text" id="comments" name="comments" placeholder="Comments" required></textarea>

            <button id="createListingBtn" type="submit">Create Listing</button>
        </form>
    </div>
</div>

<!-- The Modify Listing Modal -->
<div id="edit-modalPopup" class="modal_bg">
    <!-- Modal content -->
    <div class="modal_content">
        <img id="edit-modal_close" class="modal_close" src="assets/Interactivity/cancel.png" alt="Close Button">
        <h1>Modify Listing</h1>
        <form action="book" method="post" enctype="multipart/form-data" name="modifyForm">
            <input type="hidden" name="action" value="update">
            <input type="hidden" name="edit-id" id="edit-id" value="">

            <input type="text" id="edit-title" name="edit-title" placeholder="Title" required>
            <input type="text" id="edit-author" name="edit-author" placeholder="Author" required>
            <input type="number" id="edit-isbn" name="edit-isbn" placeholder="ISBN" required>

            <div class="condition_container">
                <label for="edit-condition">Book Condition: </label>
                <select id="edit-condition" name="edit-condition">
                    <option value="War Torn">War Torn</option>
                    <option value="Used">Used</option>
                    <option value="New">New</option>
                </select>
            </div>

            <input type="number" id="edit-price" name="edit-price" placeholder="Price" step="0.01" required>

            <div class="image_container">
                <label for="imageUrl">Image: </label>
                <input type="file" accept="image/*" id="edit-imageUrl" name="edit-imageUrl">
            </div>

            <textarea type="text" id="edit-comments" name="edit-comments" placeholder="Comments" required></textarea>

            <button id="modifyListingBtn" type="submit">Modify Listing</button>
            <button id="deleteListingBtn" type="submit">Delete Listing</button>
        </form>
    </div>
</div>

<!-- <%@include file="footer.jsp" %> -->
<script src="assets/js/modalPopup-editListing.js"></script>
<script src="./assets/js/card-conditionChanger.js"></script>
</body>
<script>
    document.getElementById("myForm").addEventListener("submit", function (e) {
        var fileInput = document.getElementById("imageUrl");
        if (fileInput.files.length > 0) {
            var fileSize = fileInput.files[0].size; // in bytes
            if (fileSize > 100000) {
                alert("Please select an image file that is less than 100kb.");
                e.preventDefault(); // prevent form submission
            }
        }
    });
</script>
</html>
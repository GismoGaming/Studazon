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
    <link rel="stylesheet" href="assets/css/account.css">
    <title>Studazon - My Account</title>
</head>

<%-- Alert Code (has to be on every page)--%>
<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
<input type="hidden" id="message" value="<%= request.getAttribute("message")%>">

<body>
<%@include file="sideBar.jsp"%>
<div class = "accountBG">
    <h1>My Account</h1>
    <p>${user.fullname}</p>
    <p>${user.email}</p>
    <button class = "accountButton" onclick="changeModal.call(this)">Edit Profile</button>
</div>

<!-- Start modal popup -->
<div id="account-modalPopup" class="modal_bg">
    <!-- Modal content -->
    <div class="modal_content">
        <img id="modal_close" class="modal_close" src="assets/Interactivity/cancel.png" alt="Exit Modal Buttons">

        <!-- Begin modal data -->
        <div class = "modal-masterContainer">
            <h1 >Updating Profile</h1>
        <form method="post" action="account">
            <input type="hidden" name="action" value="update_profile"/>

            <label for="fullname">Full Name:</label>
            <input class = "modal-input" type="text" id="fullname" name="fullname" value="${user.fullname}" required>

            <label for="email">Email:</label>
            <input  class = "modal-input" type="email" id="email" name="email" value="${user.email}" readonly>

            <label for="old_password">Old Password:</label>
            <input  class = "modal-input" type="old_password" id="old_password" name="old_password">

            <label for="new_password">New Password:</label>
            <input  class = "modal-input" type="new_password" id="new_password" name="new_password">

            <button type="submit">Update Profile</button>
        </form>
        </div>
    </div>
</div>
<!-- End modal popup -->
<!-- <%@include file="footer.jsp" %> -->
</body>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script src="./assets/js/modalPopup-account.js"></script>
<script type="text/javascript" src="./assets/js/alert.js"></script>
</html>
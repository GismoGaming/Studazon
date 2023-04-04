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

<form action="listing">
    <input type="image" class="btn_createListing" src="./assets/Interactivity/Create New.png"
           alt="Create new listing icon">
</form>

<div class="content">
    <h1>Account Profile</h1>
    <form method="post" action="account">
        <input type="hidden" name="action" value="update_profile"/>
        <label for="fullname">Full Name:</label>
        <input type="text" id="fullname" name="fullname" value="${user.fullname}" required>

        <label for="email">Email:</label>
        <input type="email" id="email" name="email" value="${user.email}" readonly>

        <label for="old_password">Old Password:</label>
        <input type="old_password" id="old_password" name="old_password">

        <label for="new_password">New Password:</label>
        <input type="new_password" id="new_password" name="new_password">

        <button type="submit">Update Profile</button>
    </form>
</div>

<!-- <%@include file="footer.jsp" %> -->
</body>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript" src="./assets/js/alert.js"></script>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
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
    <form action="TEMPacount">
        <input type="image" class="img_account" src="./assets/Interactivity/account_circle.png" alt = "Account Icon">
    </form>
    <div>
        <form action="TEMPnotifications">
            <input type="image" class="img_notification" src="./assets/Interactivity/Notification%20Popup.png" alt = "Notification Icon">
        </form>
    </div>
</div>
<div class="background_square">

    <img class="logo" src="./assets/banner/Studazon%20-%20Banner%20Style.svg" alt = "Studazon Logo">
    <div class="search-container">
        <form action="TEMPsearch">
            <input type="image" src = "./assets/Interactivity/Search%20Icon.png" alt = "Search Icon">
            <input class="input-field" type="text" placeholder="Search for a book...">
        </form>
    </div>
</div>

</body>
</html>
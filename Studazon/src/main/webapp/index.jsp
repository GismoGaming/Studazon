<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="header.jsp" %>
    <title>Studazon - For Students</title>
    <link rel="stylesheet" href="./assets/css/SHARED.css">
    <link rel="stylesheet" href="./assets/css/index.css">
</head>
<body>
<div class="banner">
    <img src="assets/banner/Studazon%20-%20Banner%20Style.png" alt="Studazon Logo">
</div>
<div class="main">
    <div class="btn-holder">
        <form action = "login">
            <input title = "Log into your Studazon account" class="login" type="submit" value="Login">
        </form>
        <form action = "register">
            <input title = "Register a new account with Studazon" class="register" type="submit" value="Register">
        </form>
    </div>
</div>

<%@include file="footer.jsp" %>
</body>
</html>
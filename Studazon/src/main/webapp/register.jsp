<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="header.jsp" %>
    <link rel="stylesheet" href="./assets/css/SHARED.css">
    <link rel="stylesheet" href="./assets/css/register.css">
    <title>Studazon</title>
</head>
<body>
<div class="rectangle"></div>
<img src="./assets/icon/Studazon%20-%20Icon%20Style.png" class="icon" alt="Studazon logo">
<h1>Register</h1>

<label class="name" for="fullName">Full Name</label>
<input type="text" id="fullName">

<label class="email" for="email">Email</label>
<input type="email" id="email">

<label class="password" for="password">Password</label>
<input type="password" id="password">

<label class="confirmPassword" for="confirmPassword">Confirm Password</label>
<input type="password" id="confirmPassword">

<button id="register">Register</button>
<label class="login">Have an account?<a href="loginUser"> Log in!</a></label>
<button><a href="studazonPage">Home</a></button>
<%@include file="footer.jsp" %>
</body>
</html>
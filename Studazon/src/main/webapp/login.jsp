<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="header.jsp" %>
    <link rel="stylesheet" href="./assets/css/SHARED.css">
    <link rel="stylesheet" href="./assets/css/login.css">
    <title>Studazon</title>
</head>
<body>
<div class="rectangle"></div>
<img src="./assets/icon/Studazon%20-%20Icon%20Style.svg" class="icon" alt="Studazon logo">
<h1>Login</h1>

<label class="email" for="email">Email</label>
<input type="email" id="email">

<label class="password" for="password">Password</label>
<input type="password" id="password">

<button id="login">Login</button>
<label class="register">Need an account?<a href="registerUser"> Sign up!</a></label>
<button><a href="studazonPage">Go To Home</a></button>
<%@include file="footer.jsp" %>
</body>
</html>
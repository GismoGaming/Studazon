<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="header.jsp" %>
    <link rel="stylesheet" href="./assets/css/SHARED.css">
    <link rel="stylesheet" href="./assets/css/index.css">
</head>
<body>
<h1 style="color: rebeccapurple; margin-top: 1%">Welcome to Studazon!</h1>
<h3 style="margin-bottom: 3%">A book selling platform</h3>
<div class="main">
    <div class="btn-holder">
        <button><a href="loginUser">Login</a></button>
        <button><a href="registerUser">Register</a></button>
    </div>
</div>

<%@include file="footer.jsp" %>
</body>
</html>
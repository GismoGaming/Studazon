<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="header.jsp" %>
    <link rel="stylesheet" href="./assets/css/SHARED.css">
    <link rel="stylesheet" href="./assets/css/index.css">
</head>
<body>
<div class="banner">
    <img src="assets/banner/Studazon%20-%20Banner%20Style.png" alt="Studazon Logo">
</div>
<div class="main">
    <div class="btn-holder">
        <%=session.getAttribute("user")%>
        <button><a href="login">Login</a></button>
        <button><a href="register">Register</a></button>
    </div>
</div>

<%@include file="footer.jsp" %>
</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="header.jsp" %>
    <link rel="stylesheet" href="./assets/css/SHARED.css">
    <link rel="stylesheet" href="./assets/css/login.css">
    <title>Studazon - Login</title>
</head>

<%-- Alert Code (has to be on every page)--%>
<input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
<input type="hidden" id="message" value="<%= request.getAttribute("message")%>">

<%-- Body--%>
<body>
<div class="rectangle">
    <a href="index"><img src="./assets/icon/Studazon%20-%20Icon%20Style.svg" class="icon" alt="Studazon logo"></a>
    <h1>Login</h1>

    <form action="login" method="post">
        <label class="email" for="email">Email</label>
        <input type="email" id="email" name="email" required>

        <label class="password" for="password">Password</label>
        <input type="password" id="password" name="password" required>

        <button id="login" type="submit">Login</button>
    </form>

    <label class="register">Need an account?<a href="register" id="signup"> Sign up!</a></label>
</div>
</body>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript" src="./assets/js/alert.js"></script>
<script type="text/javascript" src="./assets/js/login.js"></script>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="header.jsp" %>
    <link rel="stylesheet" href="./assets/css/SHARED.css">
    <link rel="stylesheet" href="./assets/css/register.css">
    <title>Studazon - Register</title>
</head>
<body>
<div class="rectangle">
    <a href="index"><img src="./assets/icon/Studazon%20-%20Icon%20Style.png" class="icon" alt="Studazon logo"></a>
    <h1>Register</h1>

    <form action="register" method="post">
        <label class="name" for="fullname">Full Name</label>
        <input type="text" id="fullname" name="fullname" required/>

        <label class="email" for="email">Email</label>
        <input type="email" id="email" name="email" required/>

        <label class="password" for="password">Password</label>
        <input type="password" id="password" name="password" required/>

        <label class="confirmPassword" for="confirmPassword">Confirm Password</label>
        <input type="password" id="confirmPassword" required/>
        <button id="register" type="submit">Register</button>
    </form>
    <label class="login">Have an account? <u><a href="login" id="loginLink">Log in!</a></u></label>
</div>
</body>
</html>
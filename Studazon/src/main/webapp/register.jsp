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
        <input type="password" id="confirmPassword" name="confirmPassword" required/>

        <label for="security-question">Security Question</label>
        <select id="security-question" name="security-question">
            <option>In what city were you born in?</option>
            <option>What high school did you attend?</option>
            <option>What is the name of a college you applied to but didn't attend?</option>
            <option>What is your oldest sibling's middle name?</option>
            <option>In what city or town did your parents meet?</option>
        </select>
        <input type="text" id="security-question-answer" name="security-question-answer">

        <button id="register" type="submit">Register</button>
    </form>
    <label class="login">Have an account? <u><a href="login" id="loginLink">Log in!</a></u></label>
</div>
</body>
</html>
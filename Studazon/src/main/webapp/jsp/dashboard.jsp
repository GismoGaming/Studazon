<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../header.jsp" %>
    <%--    <link href="<c:url value="../assets/css/SHARED.css"/>" rel="stylesheet">--%>
    <link rel="stylesheet" href="../assets/css/SHARED.css">
    <link rel="stylesheet" href="../assets/css/index.css">
</head>
<body>
<div class="banner">
    <img src="../assets/banner/Studazon%20-%20Banner%20Style.png" alt="Studazon Logo">
</div>
<div class="main">
    <div class="btn-holder">
        <form action="login" method="get">
            <button type="submit">Login</button>
        </form>
        <button><a href="register">Register</a></button>
    </div>
</div>

<%@include file="../footer.jsp" %>
</body>
</html>
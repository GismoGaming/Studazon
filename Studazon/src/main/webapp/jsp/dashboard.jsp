<%--<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>--%>
<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <%@include file="../header.jsp" %>
    <%--    <link href="<c:url value="../assets/css/SHARED.css"/>" rel="stylesheet">--%>
    <%--    <link href="<c:url value="../assets/css/index.css"/>" rel="stylesheet">--%>
</head>
<body>
<div class="banner">
    <img src="../assets/banner/Studazon%20-%20Banner%20Style.png" alt="Studazon Logo">
</div>
<div class="main">
    <div class="btn-holder">
        <button><a href="login">Login</a></button>
        <button><a href="register">Register</a></button>
    </div>
</div>

<%@include file="../footer.jsp" %>
</body>
</html>
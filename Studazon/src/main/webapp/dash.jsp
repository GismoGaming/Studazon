<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<% if (session.getAttribute("user") == null) {
    response.sendRedirect("index");
}%>
<head>
    <%@include file="header.jsp" %>
    <link rel="stylesheet" href="./assets/css/SHARED.css">
    <link rel="stylesheet" href="assets/css/dash.css">
</head>
<body>
<header>
    <nav>
        <a href="#" class="logo">Hello <%=session.getAttribute("user")%>
        </a>
        <form>
            <input type="text" placeholder="Search books...">
            <button type="submit">Search</button>
        </form>
        <ul>
            <li><a href="#">My Listings</a></li>
            <li><a href="#">Messages</a></li>
            <li><a href="#">Profile</a></li>
            <li><a href="logout">Logout</a></li>
        </ul>
    </nav>
</header>

<main>
    <h1>Welcome to Book Dashboard</h1>
    <p>Explore and discover books that interest you.</p>
</main>

<footer>
    <p>&copy; 2023 Studazon Dashboard</p>
</footer>

</body>
</html>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <%@include file="header.jsp" %>
  <link rel="stylesheet" href="./assets/css/SHARED.css">
  <link rel="stylesheet" href="./assets/css/forgot.css">
  <title>Studazon - Forgot my Password</title>
</head>
  <%-- Alert Code (has to be on every page)--%>
  <input type="hidden" id="status" value="<%= request.getAttribute("status")%>">
  <input type="hidden" id="message" value="<%= request.getAttribute("message")%>">

  <%-- Body--%>
<body>
<div class="rectangle">
  <a href="index"><img src="./assets/icon/Studazon%20-%20Icon%20Style.svg" class="icon" alt="Studazon logo"></a>
  <h1>Reset Password</h1>

  <form action="forgot" method="post">
    <label class="email" for="email">Email</label>
    <input type="email" id="email" name="email" required>

    <label for="security-question">Security Question</label>
    <select id="security-question" name="security-question">
      <option>In what city were you born in?</option>
      <option>What high school did you attend?</option>
      <option>What is the name of a college you applied to but didn't attend?</option>
      <option>What is your oldest sibling's middle name?</option>
      <option>In what city or town did your parents meet?</option>
    </select>

    <label class="password" for="secret-word">Answer</label>
    <input type="password" id="secret-word" name="secret-word" required>

    <button id="reset" type="submit">Reset Password</button>
  </form>

</div>
</body>
<script src="https://unpkg.com/sweetalert/dist/sweetalert.min.js"></script>
<script type="text/javascript" src="./assets/js/alert.js"></script>
<script type="text/javascript" src="./assets/js/login.js"></script>
</html>
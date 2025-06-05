<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Login</title>
</head>
<body>
    <%
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("userID")){
                    request.setAttribute("userIDSaved", cookie.getValue());
                }
            }
        }
    %>
    
  <h2>Login</h2>
  <c:if test="${not empty MSG}">
    <p style="color:red">${MSG}</p>
  </c:if>
  <form action="${pageContext.request.contextPath}/main/auth/login" method="POST">
    <label>UserID
      <input type="text" value="${requestScope.userIDSaved}" name="userID" required />
    </label>
    <br /><br />
    <label>Password
      <input type="password" name="password" required />
    </label>
    <br /><br />
    <label>
        <input type="checkbox" name="rememberMe" /> Remember me
    </label>
    <br /><br />
    <button type="submit">Login</button>
  </form>
</body>
</html>

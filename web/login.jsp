<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Login</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f8f9fa;
            }

            .login-container {
                max-width: 400px;
                margin: 60px auto;
                background-color: #ffffff;
                padding: 30px;
                border-radius: 12px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }

            .login-title {
                text-align: center;
                margin-bottom: 20px;
            }

            .error-msg {
                color: red;
                text-align: center;
            }
        </style>
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

        <div class="login-container">
            <h2 class="login-title">Login</h2>

            <c:if test="${not empty MSG}">
                <p class="error-msg">${MSG}</p>
            </c:if>

            <form action="${pageContext.request.contextPath}/main/auth/login" method="POST">
                <div class="mb-3">
                    <label for="userID" class="form-label">UserID</label>
                    <input type="text" class="form-control" id="userID" name="userID" value="${requestScope.userIDSaved}" required />
                </div>

                <div class="mb-3">
                    <label for="password" class="form-label">Password</label>
                    <input type="password" class="form-control" id="password" name="password" required />
                </div>

                <div class="form-check mb-3">
                    <input class="form-check-input" type="checkbox" name="rememberMe" id="rememberMe" />
                    <label class="form-check-label" for="rememberMe">Remember me</label>
                </div>

                <div class="d-grid">
                    <button type="submit" class="btn btn-primary">Login</button>
                </div>
            </form>
        </div>
    </body>
</html>

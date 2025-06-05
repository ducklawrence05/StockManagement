<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Admin Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f8f9fa;
            }

            .admin-container {
                max-width: 600px;
                margin: 80px auto;
                background-color: #ffffff;
                padding: 30px;
                border-radius: 12px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                text-align: center;
            }

            .admin-container h1 {
                margin-bottom: 20px;
            }

            .btn-group a {
                margin: 5px;
            }
        </style>
    </head>
    <body>

        <div class="admin-container">
            <h1>Admin Page</h1>
            <h4>Welcome <c:out value="${sessionScope.currentUser.fullName}" /></h4>

            <div class="d-grid gap-2 col-6 mx-auto mt-4">
                <a href="${pageContext.request.contextPath}/user" class="btn btn-primary">User List</a>
                <a href="${pageContext.request.contextPath}/stock" class="btn btn-secondary">Stock List</a>
                <a href="${pageContext.request.contextPath}/alert" class="btn btn-warning">Alert List</a>
                <a href="${pageContext.request.contextPath}/transaction" class="btn btn-success">Transaction List</a>
            </div>
        </div>
    </body>
</html>

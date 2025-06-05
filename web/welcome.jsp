<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Welcome Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f8f9fa;
            }

            .staff-container {
                max-width: 600px;
                margin: 80px auto;
                background-color: #ffffff;
                padding: 30px;
                border-radius: 12px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                text-align: center;
            }

            .staff-container h1 {
                margin-bottom: 20px;
            }

            .btn-group a {
                margin: 5px;
            }
        </style>
    </head>
    <body>

        <div class="staff-container">
            <h1>Welcome Page</h1>
            <h4>Welcome <c:out value="${sessionScope.currentUser.fullName}" /></h4>

            <div class="btn-group d-flex flex-column align-items-center mt-4">
                <a href="${pageContext.request.contextPath}/stock" class="btn btn-secondary w-75">Stock List</a>
                <a href="${pageContext.request.contextPath}/alert" class="btn btn-warning w-75">Alert List</a>
                <a href="${pageContext.request.contextPath}/transaction" class="btn btn-success w-75">Transaction List</a>
            </div>
        </div>
    </body>
</html>

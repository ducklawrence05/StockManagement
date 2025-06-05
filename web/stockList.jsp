<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Stock List</title>
        <style>
            body {
                font-family: Arial;
                margin: 20px;
            }
            a {
                color: #06c;
                text-decoration: none;
            }
            a:hover {
                text-decoration: underline;
            }
            table {
                border-collapse: collapse;
                margin-top: 10px;
            }
            th, td {
                border: 1px solid #ccc;
                padding: 4px 8px;
                white-space: nowrap;
            }
            th {
                background: #f0f0f0;
            }
            td.actions {
                text-align: center;
            }
            input[type=number] {
                width:80px;
            }
        </style>
    </head>
    <body>
        <h1>Welcome, <c:out value="${sessionScope.currentUser.fullName}"/></h1>
        <form action="${pageContext.request.contextPath}/main/auth/logout" method="POST">
            <input type="submit" value="LOGOUT" />
        </form>

        <p style="color: green;">${requestScope.MSG}</p>
        <hr />
        
        <form action="${pageContext.request.contextPath}/main/stock" method="GET">
            Price between
            <input type="number" step="0.01" name="minPrice" placeholder="min"
                   value="${param.minPrice}" required/>
            and
            <input type="number" step="0.01" name="maxPrice" placeholder="max"
                   value="${param.maxPrice}" required/>
            <button type="submit">Search Price</button>
        </form>

    </body>
</html>

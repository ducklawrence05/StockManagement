<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="dto.Stock" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Update Stock</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            margin: 20px;
        }
        h2 {
            margin-bottom: 12px;
        }
        .old-info, .edit-form {
            border: 1px solid #ccc;
            padding: 12px;
            margin-bottom: 20px;
        }
        .old-info h3, .edit-form h3 {
            margin-top: 0;
            margin-bottom: 8px;
            font-size: 1.1em;
            color: #333;
        }
        .old-info p {
            margin: 4px 0;
        }
        .edit-form label {
            display: inline-block;
            width: 100px;
            vertical-align: top;
            margin-top: 6px;
        }
        .edit-form input[type="text"],
        .edit-form input[type="number"],
        .edit-form select {
            width: 250px;
            padding: 4px;
            margin-bottom: 10px;
        }
        .edit-form button {
            padding: 6px 14px;
            font-size: 1em;
        }
        .back-link {
            margin-top: 12px;
            display: inline-block;
        }
    </style>
</head>
<body>
    <%
        
        User loginUser = (User) session.getAttribute("LOGIN_USER");
        if (loginUser == null) {
            response.sendRedirect("login.jsp");
            return;
        }
    %>
    <h2>Update Stock</h2>

    <%-- error out --%>
    <c:if test="${not empty MSG}">
        <p style="color: red; font-weight: bold;">${MSG}</p>
    </c:if>

    <%-- compare --%>
    <c:if test="${not empty stock}">
        <div class="old-info">
            <h3>Current (Old) Stock Details:</h3>
            <p><strong>Ticker:</strong> ${stock.ticker}</p>
            <p><strong>Name:</strong> ${stock.name}</p>
            <p><strong>Sector:</strong> ${stock.sector}</p>
            <p><strong>Price:</strong> ${stock.price}</p>
            <p><strong>Status:</strong> 
                <c:choose>
                    <c:when test="${stock.status}">Active</c:when>
                    <c:otherwise>Inactive</c:otherwise>
                </c:choose>
            </p>
        </div>
    </c:if>

    <%-- Form  --%>
    <c:if test="${not empty stock}">
        <div class="edit-form">
            <h3>Edit & Save Changes:</h3>
            <%-- back to /main/stock?action=update via POST --%>
            <form action="${pageContext.request.contextPath}/main/stock" method="POST">
                <%-- Bắt buộc phải có action=update để Controller nhận diện --%>
                <input type="hidden" name="action" value="update" />
                <%-- keep --%>
                <label>Ticker:</label>
                <input type="text" name="ticker" value="${stock.ticker}" readonly /><br/>

                <%-- Name --%>
                <label>Name:</label>
                <input type="text" name="name" value="${stock.name}" required /><br/>

                <%-- Sector (input text) --%>
                <label>Sector:</label>
                <input type="text" name="sector" value="${stock.sector}" required /><br/>

                <%-- Price (input number, step 0.01) --%>
                <label>Price:</label>
                <input type="number" step="0.01" name="price" value="${stock.price}" required /><br/>

                <%-- Status (select) --%>
                <label>Status:</label>
                <select name="status">
                    <option value="1" <c:if test="${stock.status}">selected</c:if>>Active</option>
                    <option value="0" <c:if test="${not stock.status}">selected</c:if>>Inactive</option>
                </select><br/>

                <button type="submit">Save Changes</button>
            </form>
        </div>
    </c:if>

    <%-- not found stock --%>
    <c:if test="${empty stock}">
        <p style="color: #c00;">Not found.</p>
    </c:if>

    <p class="back-link"><a href="${pageContext.request.contextPath}/main/stock?action=getAll">← Back to Stock List</a></p>
</body>
</html>

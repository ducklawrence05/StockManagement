<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <title>Create New Stock</title>
</head>
<body>
<h2>Create New Stock</h2>

<form action="${pageContext.request.contextPath}/stock" method="post">
    <input type="hidden" name="action" value="create">

    <label for="ticker">Ticker:</label>
    <input type="text" name="ticker" required><br>

    <label for="name">Name:</label>
    <input type="text" name="name" required><br>

    <label for="sector">Sector:</label>
    <input type="text" name="sector" required><br>

    <label for="price">Price:</label>
    <input type="number" name="price" step="0.01" required><br>

    <label for="status">Status:</label>
    <select name="status">
        <option value="1">Active</option>
        <option value="0">Inactive</option>
    </select><br><br>

    <input type="submit" value="Create Stock">
</form>

<c:if test="${not empty MSG}">
    <p style="color: green">${MSG}</p>
</c:if>

<a href="${pageContext.request.contextPath}/main/stock?action=getAll">Back to List</a>
</body>
</html>

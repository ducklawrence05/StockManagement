<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.Stock"%>
<%
    Stock stock = (Stock) request.getAttribute("stock");
    if (stock == null) {
        response.sendRedirect("stock");
        return;
    }
%>
<!DOCTYPE html>
<html>
<head>
    <title>Update Stock</title>
    <style>
        form {
            margin: 20px;
        }
        label {
            display: block;
            margin-top: 10px;
        }
    </style>
</head>
<body>
    <h2>Update Stock - <%= stock.getTicker() %></h2>
    <form action="stock" method="POST" onsubmit="return confirm('Are you sure you want to update this stock?');">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="ticker" value="<%= stock.getTicker() %>">

        <label for="name">Name:</label>
        <input type="text" name="name" placeholder="Leave empty to keep: <%= stock.getName() %>">

        <label for="sector">Sector:</label>
        <input type="text" name="sector" placeholder="Leave empty to keep: <%= stock.getSector() %>">

        <label for="price">Price:</label>
        <input type="number" name="price" step="0.01" placeholder="Leave empty to keep: <%= stock.getPrice() %>">

        <label for="status">Status (1 = Active, 0 = Inactive):</label>
        <select name="status">
            <option value="">Keep current: <%= stock.isStatus() ? "Active" : "Inactive" %></option>
            <option value="1">Active</option>
            <option value="0">Inactive</option>
        </select>

        <br><br>
        <button type="submit">Confirm Update</button>
        <a href="stock"><button type="button">Cancel</button></a>
    </form>
</body>
</html>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="dto.Stock"%>
<%@page import="java.util.List"%>
<!DOCTYPE html>
<html>
<head>
    <title>Stock List</title>
    <style>
        table {
            border-collapse: collapse;
        }
        th, td {
            border: 1px solid #999;
            padding: 8px;
        }
        th {
            background-color: #f2f2f2;
        }
    </style>
</head>
<body>
    <h2>Stock List</h2>

    <!-- Logout Button -->
    <form action="${pageContext.request.contextPath}/main/auth/logout" method="POST">
        <input type="submit" value="LOGOUT" />
    </form>
    <!-- Filter Form -->
    <form action="stock" method="GET">
        <input type="text" name="name" placeholder="Filter by name" value="${param.name}" />
        <input type="text" name="sector" placeholder="Filter by sector" value="${param.sector}" />
        <input type="text" name="ticker" placeholder="Filter by ticker" value="${param.ticker}" />
        <input type="hidden" name="action" value="filter" />
        <button type="submit">Filter</button>
    </form>

    <!-- Search by Price Range -->
    <form action="stock" method="GET" style="margin-top: 10px;">
        <input type="number" name="minPrice" placeholder="Min Price" value="${param.minPrice}" step="0.01" min="0" />
        <input type="number" name="maxPrice" placeholder="Max Price" value="${param.maxPrice}" step="0.01" min="0" />
        <input type="hidden" name="action" value="searchbyPrice" />
        <button type="submit">Search by Price Range</button>
    </form>

    <!-- Sort by Price -->
    <form action="stock" method="GET" style="margin-top:10px;">
        <input type="hidden" name="action" value="orderByPrice" />
        <select name="order">
            <option value="ASC">Sort Price ASC</option>
            <option value="DESC">Sort Price DESC</option>
        </select>
        <button type="submit">Sort</button>
    </form>

    <br>
    <table>
        <tr>
            <th>Ticker</th>
            <th>Name</th>
            <th>Sector</th>
            <th>Price</th>
            <th>Status</th>
            <th>Actions</th>
        </tr>
        <%
            List<Stock> stocks = (List<Stock>) request.getAttribute("stocks");
            if (stocks != null) {
                for (Stock stock : stocks) {
        %>
        <tr>
            <td><%= stock.getTicker() %></td>
            <td><%= stock.getName() %></td>
            <td><%= stock.getSector() %></td>
            <td><%= stock.getPrice() %></td>
            <td><%= stock.isStatus() ? "Active" : "Inactive" %></td>
            <td>
                <form action="stock" method="GET" style="display:inline">
                    <input type="hidden" name="action" value="searchByTicker" />
                    <input type="hidden" name="ticker" value="<%= stock.getTicker() %>" />
                    <button type="submit">Update</button>
                </form>
                <form action="stock" method="POST" style="display:inline" onsubmit="return confirm('Are you sure you want to delete this stock?');">
                    <input type="hidden" name="action" value="delete" />
                    <input type="hidden" name="ticker" value="<%= stock.getTicker() %>" />
                    <button type="submit">Delete</button>
                </form>
            </td>
        </tr>
        <%
                }
            }
        %>
    </table>
</body>
</html>

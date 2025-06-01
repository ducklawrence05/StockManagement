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
        <p><a href="${pageContext.request.contextPath}/LogoutController">Logout</a></p>

        <!-- Price‐range search -->
        <form action="${pageContext.request.contextPath}/SearchPriceController" method="POST">
            Price between
            <input type="number" step="0.01" name="minPrice" placeholder="min"
                   value="${param.minPrice}" required/>
            and
            <input type="number" step="0.01" name="maxPrice" placeholder="max"
                   value="${param.maxPrice}" required/>
            <button type="submit">Search Price</button>
        </form>

        <!-- Sort links -->
        <p style="margin-top:8px;">
            Sort by price:
            <a href="${pageContext.request.contextPath}/MainController?sort=asc">Ascending</a> |
            <a href="${pageContext.request.contextPath}/MainController?sort=desc">Descending</a> |
            <a href="${pageContext.request.contextPath}/MainController">None</a>
        </p>

        <c:if test="${empty listStock}">
            <p>No matching stocks found!</p>
        </c:if>

        <table>
            <thead>
                <tr>
                    <th>No</th><th>Ticker</th><th>Name</th><th>Sector</th><th>Price</th><th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="stock" items="${listStock}" varStatus="st">
                    <tr>
                        <form action="${pageContext.request.contextPath}/ActionController" method="POST">
                            <td>${st.count}</td>
                            <td>
                                <input type="hidden" name="ticker" value="${stock.ticker}"/>
                                ${stock.ticker}
                            </td>
                            <td><input type="text"  name="name"   value="${stock.name}"   required/></td>
                            <td><input type="text"  name="sector" value="${stock.sector}" required/></td>
                            <td><input type="number"step="0.01" name="price"  value="${stock.price}" required/></td>
                            <td class="actions">
                                <button type="submit" name="action" value="update">Update</button>
                                <button type="submit" name="action" value="delete">Delete</button>
                            </td>
                        </form>
                    </tr>
                </c:forEach>
            </tbody>
</table>
</body>
</html>

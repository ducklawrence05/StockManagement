<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Stock List Page</title>
    </head>
    <body>
        <%
            User loginUser = (User) session.getAttribute("LOGIN_USER");
            if (loginUser == null) {
                response.sendRedirect("login.jsp");
                return;
            }
        %>
        <h1>Welcome: <%= loginUser.getFullName()%></h1>
        <form action="${pageContext.request.contextPath}/main/auth/logout" method="POST">
            <input type="submit" value="LOGOUT" />
        </form>
        <!-- Price‐range search -->
        <form action="${pageContext.request.contextPath}/main/stock/searchbyPrice" method="POST">
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
        <form action="${pageContext.request.contextPath}/main/stock/orderByPrice" method=POST"">
            <select name="order">
                <option>ASC</option>
                <option>DESC</option>
            </select>
            <button type="submit">Sort by price</button>
        </form>
        </p>
        
        <c:if test="${empty stocks}">
            <p>No matching users found!</p>
        </c:if>
        <c:if test="${not empty stocks}">
            <table>
                <thead>
                    <tr>
                        <th>No</th>
                        <th>Ticker</th>
                        <th>Name</th>
                        <th>Sector</th>
                        <th>Price</th>
                        <th>Action</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="s" items="${stocks}" varStatus="loopStatus">
                        <tr>
                            <td>${loopStatus.index + 1}</td>

                            <td>${s.ticker}</td>

                            <td>
                                <input type="text" name="name_${s.ticker}" 
                                       value="${s.name}" />
                            </td>

                            <td>
                                <input type="text" name="sector_${s.ticker}" 
                                       value="${s.sector}" />
                            </td>

                            <td>
                                <input type="number" step="0.01" name="price_${s.ticker}"
                                       value="${s.price}" />
                            </td>

                            <td>
                                <%-- Form Update --%>
                                <form class="action-form" 
                                      action="updateStock.jsp" 
                                      method="POST">
                                    <input type="hidden" name="ticker" value="${s.ticker}" />
                                    <button type="submit">Update</button>
                                </form>

                                <%-- Form Delete --%>
                                <form action="${pageContext.request.contextPath}/main/stock?action=delete" method="POST">
                                    <input type="hidden" name="ticker" value="${s.ticker}" />
                                    <button type="submit" onclick="return confirm('Are you sure?');">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>            
        
    </body>
</html>

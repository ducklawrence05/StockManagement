<%-- 
    Document   : updateAlert
    Created on : Jun 5, 2025, 4:46:54 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Alert Page</title>
    </head>

    <body>
        <h1>Welcome, <c:out value="${sessionScope.currentUser.fullName}"/></h1>
        <form action="${pageContext.request.contextPath}/main/auth/logout" method="POST">
            <input type="submit" value="LOGOUT" />
        </form>
            <h3>Update user</h3>
        <p style="color: red;">${requestScope.ERRMSG}</p>
        <hr />

        <h3>Update user</h3>
        <p style="color: green;">${requestScope.MSG}</p>
        <hr />


        <table>
            <thead>
                <tr>
                    <th>No</th>
                    <th>User ID</th>
                    <th>Ticker</th>
                    <th>Direction</th>
                    <th>Threshold</th>
                    <th>Status</th>
                </tr>
            </thead>
            <tbody>
            <form action="${pageContext.request.contextPath}/main/alert/update" method="POST">
                <tr>
                    <td>
                        <input style="text-align: center; border: none; outline: none;" type="text" name="alertID" value="${alert.alertID}" readonly>
                    </td> 
                    <td>
                        <input style="text-align: center; border: none; outline: none;" type="text" name="userID" value="${alert.userID}" readonly>
                    </td>
                    <td>
                        <input style="text-align: center; border: none; outline: none;" type="text" name="ticker" value="${alert.ticker}" readonly>
                    </td>
                    <td>
                        <input style="text-align: center; border: none; outline: none;" type="text" name="direction" value="${alert.direction}" readonly>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${requestScope.can == true}">
                                <input style="text-align: center" type="text" name="threshold" value="${alert.threshold}" required="">
                            </c:when>
                            <c:otherwise>
                                <input style="text-align: center" type="text" name="threshold" value="${alert.threshold}" readonly>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${requestScope.can == true}">
                                <!--<input style="text-align: center" type="text" name="status" value="${alert.status}">-->
                                <select id="directionValue" name="status">
                                    <option value=${alert.status}>${alert.status}</option>
                                    <c:if test="${alert.status.equalsIgnoreCase('inactive')}">
                                        <option value="active">active</option>
                                    </c:if>
                                        <c:if test="${alert.status.equalsIgnoreCase('active')}">
                                        <option value="inactive">inactive</option>
                                    </c:if>
                                </select>
                            </c:when>
                            <c:otherwise>
                                <input style="text-align: center" type="text" name="status" value="${alert.status}" readonly>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <c:if test="${requestScope.can == true}">
                        <td class="actions">
                            <button type="submit">Update</button>
                        </td>
                    </c:if>
                </tr>
            </form>
        </tbody>
    </table>
    <form action="${pageContext.request.contextPath}/main/alert" method="GET">
        <button type="submit">Go To AlertList</button>
    </form>

</body>
</html>

<%-- 
    Document   : createAlert
    Created on : Apr 24, 2025, 9:21:46 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Alert Page</title>
    </head>
    <body>
        <h1>Welcome, <c:out value="${sessionScope.currentUser.fullName}"/></h1>
        <form action="${pageContext.request.contextPath}/main/auth/logout" method="POST">
            <input type="submit" value="LOGOUT" />
        </form>

        <h1>Create Alert</h1>
        <p style="color: green;">${requestScope.MSG}</p>
        <hr />

        <form action="${pageContext.request.contextPath}/main/alert/create" method="POST">
            UserID:  <input type="text" name ="userID" value="${sessionScope.currentUser.userID}" readonly> <br/>
            Ticker: <input type="text" name="ticker" placeholder="Enter ticker" required /><br/>
            Threshold: <input type="number" name="threshold" placeholder="Enter threshold" step="0.01" required /><br/>
            Direction: 
            <select name="direction" required>
                <option value="increase">increase</option>
                <option value="decrease">decrease</option>
            </select><br/>
            <input type="submit" name="action" value="create" /><br/>    
        </form>

        <form action="${pageContext.request.contextPath}/main/alert" method="GET">
            <button type="submit">Go To AlertList</button>
        </form>

    </body>
</html>

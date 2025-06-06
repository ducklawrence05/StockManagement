<%-- 
    Document   : createTransaction
    Created on : Apr 24, 2025, 7:34:00 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Transaction Page</title>
    </head>
    <body>
        <h1>Welcome, <c:out value="${sessionScope.currentUser.fullName}"/></h1>
        <form action="${pageContext.request.contextPath}/main/auth/logout" method="POST">
            <input type="submit" value="LOGOUT" />
        </form>
            
        <h3>Create new transaction</h3>
        <p style="color: green;">${requestScope.MSG}</p>
        
        <hr />
        <form action="${pageContext.request.contextPath}/main/transaction/create" method="POST">
            
            <label for="userID">user ID</label> <br>
            <input type="text" id="userID" name="userID" value="${sessionScope.currentUser.userID}"><br><br>
            
            <label for="ticker">ticker</label> <br>
            <input type="text" id="ticker" name="ticker" placeholder="Enter ticker" required><br><br>
            
            <label for="type">type</label>
            <select name="type" required>
                <option value="buy">Buy</option>
                <option value="sell">Sell</option>
            </select><br><br>
            
            <label for="quantity">quantity</label> <br>
            <input type="text" id="quantity" name="quantity" placeholder="Enter quantity" min="1" required><br><br>
            
            <label for="price">price</label> <br>
            <input type="text" id="price" name="price" placeholder="Enter Price" step="0.01" min="0.01" required><br><br>
           
            <label for="status">status</label>
            <select name="status" required>
                <option value="executed">executed</option>
                <option value="pending">pending</option>
            </select><br><br>
            
            <button type="submit" name="action" value="create">Create Transaction</button>
            
        </form> <br />
            
        <a href="${pageContext.request.contextPath}/main/transaction">Back to Transaction List</a>
    </body>
</html>

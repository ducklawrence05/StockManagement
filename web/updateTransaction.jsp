<%-- 
    Document   : updateUser
    Created on : Jun 1, 2025, 9:19:22 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update user Page</title>
    </head>
    <body>
        <h1>Welcome, <c:out value="${sessionScope.currentUser.fullName}"/></h1>
        <form action="${pageContext.request.contextPath}/main/auth/logout" method="POST">
            <input type="submit" value="LOGOUT" />
        </form>

        <h3>Update transaction</h3>  
        <p style="color: green;">${requestScope.MSG}</p>
       
        <hr />
            <form action="${pageContext.request.contextPath}/main/transaction/update" method="POST">
                <label for="id">id</label>
                <input type="text" name="id" value="${transaction.id}"/><br><br>
                
                <label for="userID">user ID</label> 
                <input type="text" name="userID" value="${transaction.userID}"/><br><br>
                
                <label for="ticker">ticker</label>
                <input type="text" id="ticker" name="ticker" value="${transaction.ticker}"> <br><br>
                
                <label for="type">type</label>
                <input type="text" id="type" name="type" value="${transaction.type}"> <br><br>
                
                <label for="quantity">quantity</label>
                <input type="text" id="quantity" name="quantity" value="${transaction.quantity}"> <br><br>
                
                <label for="price">price</label>
                <input type="text" id="price" name="price" value="${transaction.price}"> <br><br>
                
                <label for="status">status</label>
                <input type="text" id="status" name="status" value="${transaction.status}"> <br><br> 
                <c:if test="${!empty requestScope.can}"><button type="submit" name="action" value="update">Update</button></c:if>
                
            </form>    

        <a href="${pageContext.request.contextPath}/main/transaction">Back to transaction CRUD</a>
    </body>
</html>
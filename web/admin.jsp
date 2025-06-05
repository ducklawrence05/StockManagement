<%-- 
    Document   : admin
    Created on : May 31, 2025, 7:24:29 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Page</title>
    </head>
    <body>
        <h1>Admin page</h1>
        <h1>Welcome <c:out value="${sessionScope.currentUser.fullName}"/></h1>
        <a href="${pageContext.request.contextPath}/main/user">User List</a><br />
        <a href="${pageContext.request.contextPath}/main/stock">Stock List</a><br />
        <a href="${pageContext.request.contextPath}/main/alert">Alert List</a><br />
        <a href="${pageContext.request.contextPath}/main/transaction">Transaction List</a>
    </body>
</html>

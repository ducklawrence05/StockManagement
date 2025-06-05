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
        <a href="${pageContext.request.contextPath}/user">User List</a><br />
        <a href="${pageContext.request.contextPath}/stock">Stock List</a><br />
        <a href="${pageContext.request.contextPath}/alert">Alert List</a><br />
        <a href="${pageContext.request.contextPath}/transaction">Transaction List</a>
    </body>
</html>

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
        <title>Welcome Page</title>
    </head>
    <body>
        <h1>Staff page</h1>
        <h1>Welcome <c:out value="${sessionScope.currentUser.fullName}"/></h1>
        <a href="${pageContext.request.contextPath}/stock">Stock List</a>
        <a href="${pageContext.request.contextPath}/alert">Alert List</a>
        <a href="${pageContext.request.contextPath}/transaction">Transaction List</a>
    </body>
</html>

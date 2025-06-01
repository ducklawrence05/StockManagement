<%-- 
    Document   : createUser
    Created on : May 31, 2025, 7:57:42 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create user page</title>
    </head>
    <body>
        <h1>Welcome, <c:out value="${sessionScope.currentUser.fullName}"/></h1>
        <form action="${pageContext.request.contextPath}/main/auth/logout" method="POST">
            <input type="submit" value="LOGOUT" />
        </form>
            
        <h3>Create new user</h3>
        <p style="color: green;">${requestScope.MSG}</p>
        <hr />
        <form action="${pageContext.request.contextPath}/main/user/create" method="POST">
            <label for="userID">UserID</label><br>
            <input type="text" id="userID" name="userID" placeholder="Enter user ID" required><br><br>
            
            <label for="fullName">Full name</label><br>
            <input type="text" id="fullName" name="fullName" placeholder="Enter full name" required><br><br>
            
            <label for="roleID">Role</label>
            <select id="roleID" name="roleID" required>
                <c:forEach var="r" items="${roleList}">
                    <option value="${r.value}">${r}</option>
                </c:forEach>
            </select><br /><br />
            
            <label for="password">Password</label><br>
            <input type="password" id="password" name="password" placeholder="Enter password" required><br><br>
            
            <label for="confirmPassword">Confirm password</label><br>
            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Enter confirm password" required><br><br>

            <button type="submit">Create</button>
        </form>
                
        <a href="${pageContext.request.contextPath}/user">Back to user CRUD</a>
    </body>
</html>

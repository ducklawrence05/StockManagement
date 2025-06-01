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

        <h3>Update new user</h3>
        <p style="color: green;">${requestScope.MSG}</p>
        <hr />
        <form action="${pageContext.request.contextPath}/main/user/getUserByID" method="GET">
            <label for="userID">UserID</label><br>
            <input type="text" id="userID" name="userID" placeholder="Enter user ID" required><br><br>
            <button type="submit">Search</button>
        </form>
        <hr />

        <form id="updateForm" action="${pageContext.request.contextPath}/main/user/update" method="POST">
            <input type="hidden" name="userID" value="${user.userID}"/>

            <label for="fullName">Full name</label><br>
            <input type="text" id="fullName" name="fullName" value="${user.fullName}" required><br><br>

            <label for="roleID">Role</label>
            <select id="roleID" name="roleID" required>
                <c:forEach var="r" items="${roleList}">
                    <option value="${r.value}" ${r == user.role ? "selected" : ""}>${r}</option>
                </c:forEach>
            </select><br /><br />

            <label for="oldPassword">Old password</label><br>
            <input type="password" id="oldPassword" name="oldPassword" placeholder="Enter old password" ><br><br>

            <label for="password">Password</label><br>
            <input type="password" id="password" name="password" placeholder="Enter password" ><br><br>

            <label for="confirmPassword">Confirm password</label><br>
            <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Enter confirm password" ><br><br>

            <button type="submit">Update</button>
        </form>

        <a href="${pageContext.request.contextPath}/user">Back to user CRUD</a>

        <script>
            const oldpwd = document.getElementById('oldPassword');
            const pwd = document.getElementById('password');
            const confirmpwd = document.getElementById('confirmPassword');
            const form = document.getElementById('updateForm');

            function checkRequired() {
                if (oldpwd.value.trim() !== '' || pwd.value.trim() !== '' || confirmpwd.value.trim() !== '') {
                    oldpwd.required = true;
                    pwd.required = true;
                    confirmpwd.required = true;
                } else {
                    oldpwd.required = false;
                    pwd.required = false;
                    confirmpwd.required = false;
                }
            }

            oldpwd.addEventListener('keydown', checkRequired);
            pwd.addEventListener('keydown', checkRequired);
            confirmpwd.addEventListener('keydown', checkRequired);

            form.addEventListener('submit', function (e) {
                checkRequired();
            });
        </script>
    </body>
</html>

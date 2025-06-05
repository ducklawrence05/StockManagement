<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Create User Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
        <style>
            body {
                background-color: #f8f9fa;
                padding: 40px 20px;
            }
            .create-user-container {
                max-width: 500px;
                margin: auto;
                background: white;
                padding: 30px 40px;
                border-radius: 10px;
                box-shadow: 0 0 15px rgba(0,0,0,0.1);
            }
            h1, h3 {
                text-align: center;
                margin-bottom: 30px;
            }
            .logout-btn {
                display: block;
                width: 100%;
                margin-bottom: 30px;
            }
            .message {
                color: green;
                text-align: center;
                margin-bottom: 20px;
            }
            label {
                font-weight: 600;
            }
            input, select {
                margin-bottom: 20px;
            }
            a.back-link {
                display: block;
                text-align: center;
                margin-top: 20px;
                color: #0d6efd;
                text-decoration: none;
            }
            a.back-link:hover {
                text-decoration: underline;
            }
        </style>
    </head>
    <body>
        <div class="create-user-container">
            <h1>Welcome, <c:out value="${sessionScope.currentUser.fullName}" /></h1>

            <form action="${pageContext.request.contextPath}/main/auth/logout" method="POST">
                <input type="submit" class="btn btn-danger logout-btn" value="LOGOUT" />
            </form>

            <h3>Create New User</h3>

            <p class="message">${requestScope.MSG}</p>

            <form action="${pageContext.request.contextPath}/main/user/create" method="POST">
                <label for="userID">UserID</label>
                <input type="text" id="userID" name="userID" placeholder="Enter user ID" required class="form-control" />

                <label for="fullName">Full name</label>
                <input type="text" id="fullName" name="fullName" placeholder="Enter full name" required class="form-control" />

                <label for="roleID">Role</label>
                <select id="roleID" name="roleID" required class="form-select">
                    <c:forEach var="r" items="${roleList}">
                        <option value="${r.value}">${r}</option>
                    </c:forEach>
                </select>

                <label for="password">Password</label>
                <input type="password" id="password" name="password" placeholder="Enter password" required class="form-control" />

                <label for="confirmPassword">Confirm password</label>
                <input type="password" id="confirmPassword" name="confirmPassword" placeholder="Enter confirm password" required class="form-control" />

                <button type="submit" class="btn btn-primary w-100">Create</button>
            </form>

            <a href="${pageContext.request.contextPath}/user" class="back-link">Back to user CRUD</a>
        </div>
    </body>
</html>

<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Stock List</title>
        <style>
            body {
                font-family: Arial;
                margin: 20px;
            }
            a {
                color: #06c;
                text-decoration: none;
            }
            a:hover {
                text-decoration: underline;
            }
            table {
                border-collapse: collapse;
                margin-top: 10px;
            }
            th, td {
                border: 1px solid #ccc;
                padding: 4px 8px;
                white-space: nowrap;
            }
            th {
                background: #f0f0f0;
            }
            td.actions {
                text-align: center;
            }
            input[type=number] {
                width:80px;
            }
        </style>
    </head>
    <body>
        <h1>Welcome, <c:out value="${sessionScope.currentUser.fullName}"/></h1>
        <form action="${pageContext.request.contextPath}/main/auth/logout" method="POST">
            <input type="submit" value="LOGOUT" />
        </form>

        <!-- Price‐range search -->
<!--        <form action="${pageContext.request.contextPath}/main" method="POST">
            Price between
            <input type="number" step="0.01" name="minPrice" placeholder="min"
                   value="${param.minPrice}" required/>
            and
            <input type="number" step="0.01" name="maxPrice" placeholder="max"
                   value="${param.maxPrice}" required/>
            <button type="submit">Search Price</button>
        </form>-->

        <p style="color: green;">${requestScope.MSG}</p>
        <hr />
        
        <h3>Create new user</h3>
        <form action="${pageContext.request.contextPath}/main/user/create" method="POST">
            <input type="text" name="userID" placeholder="User ID" required>
            <input type="text" name="fullName" placeholder="Full name" required>
            <select name="roleID" required>
                <c:forEach var="r" items="${roleList}">
                    <option value="${r.value}">${r}</option>
                </c:forEach>
            </select>
            <input type="password" name="password" placeholder="Password" required>
            <input type="password" name="confirmPassword" placeholder="Confirm password" required>
            <button type="submit">Create</button>
        </form>
        <hr />

        <c:if test="${empty users}">
            <p>No matching users found!</p>
        </c:if>

        <table>
            <thead>
                <tr>
                    <th>No</th>
                    <th>UserID</th>
                    <th>Full name</th>
                    <th>Role</th>
                    <th>Password</th>
                    <th>Confirm password</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${requestScope.users}" varStatus="st">
                <form action="${pageContext.request.contextPath}/main/user/" method="POST">
                    <tr>
                        <td>${st.count}</td>
                        <td>
                            <input type="hidden" name="userID" value="${user.userID}"/>
                            ${user.userID}
                        </td>
                        <td><input type="text"  name="fullName" value="${user.fullName}" required/></td>
                        <td>
                            <select name="roleID" required>
                                <c:forEach var="r" items="${roleList}">
                                    <option value="${r.value}" ${r == user.role ? "selected" : ""}>
                                        ${r}
                                    </option>
                                </c:forEach>
                            </select>
                        </td>
                        <td><input type="password" name="password" placeholder="Password" /></td>
                        <td><input type="password" name="confirmPassword" placeholder="Confirm password" /></td>
                        <td class="actions">
                            <button type="submit" name="action" value="update">Update</button>
                            <button type="submit" name="action" value="delete">Delete</button>
                        </td>
                    </tr>
                </form>
            </c:forEach>
        </tbody>
    </table>
</body>
</html>

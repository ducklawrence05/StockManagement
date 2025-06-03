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
        <a 
            href="${pageContext.request.contextPath}/user"
            style="text-decoration: none; color: black"
        >User CRUD</a>
        <hr />
        <form action="${pageContext.request.contextPath}/main/auth/logout" method="POST">
            <input type="submit" value="LOGOUT" />
        </form>
        <hr />

        <form action="${pageContext.request.contextPath}/main/user" method="GET">
            <button type="submit" name="action" value="create">Create user</button> |
            <button type="submit" name="action" value="update">Update user</button>
        </form>

        <hr />
        
        <form action="${pageContext.request.contextPath}/main/user" method="GET">
            <label for="keySearch">Search</label>
            <input type="text" id="keySearch" name="keySearch" placeholder="Search..." required/> |

            <select name="action">
                <option value="getUsersByID">Search by ID</option>
                <option value="getUsersByName">Search by Name</option>
            </select> |

            <button type="submit">Search</button>
        </form>

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
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="user" items="${requestScope.users}" varStatus="st">

                    <tr>
                        <td>${st.count}</td>
                        <td>${user.userID}</td>
                        <td>${user.fullName}</td>
                        <td>${user.role}</td>
                        <td class="actions">
                            <form action="${pageContext.request.contextPath}/main/user/delete" method="POST">
                                <button type="submit" name="userID" value="${user.userID}">Delete</button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>

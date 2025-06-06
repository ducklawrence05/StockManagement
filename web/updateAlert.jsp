<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Update Alert Page</title>
        <!-- Bootstrap 5 CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <style>
            .post-container {
                max-width: 900px;
            }
        </style>
    </head>
    <body>
        <div class="post-container">
            <h1>Welcome, <c:out value="${sessionScope.currentUser.fullName}"/></h1>

            <form action="${pageContext.request.contextPath}/main/auth/logout" method="POST">
                <input type="submit" class="btn btn-danger logout-btn" value="LOGOUT" />
            </form>

            <h3>Update Alert</h3>

            <c:if test="${not empty requestScope.ERRMSG}">
                <p class="message text-danger">${requestScope.ERRMSG}</p>
            </c:if>
            <c:if test="${not empty requestScope.MSG}">
                <p class="message text-success">${requestScope.MSG}</p>
            </c:if>

            <div class="table-responsive">
                <form action="${pageContext.request.contextPath}/main/alert/update" method="POST">
                    <table class="table table-bordered form-table text-center">
                        <thead class="table-light">
                            <tr>
                                <th>No</th>
                                <th>User ID</th>
                                <th>Ticker</th>
                                <th>Direction</th>
                                <th>Threshold</th>
                                <th>Status</th>
                                    <c:if test="${requestScope.can == true}">
                                    <th>Action</th>
                                    </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <input class="readonly-input" type="text" name="alertID" value="${alert.alertID}" readonly>
                                </td>
                                <td>
                                    <input class="readonly-input" type="text" name="userID" value="${alert.userID}" readonly>
                                </td>
                                <td>
                                    <input class="readonly-input" type="text" name="ticker" value="${alert.ticker}" readonly>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${requestScope.can == true}">
                                            <select name="direction" class="form-select">
                                                <option value="${alert.direction}" selected>${alert.direction}</option>
                                                <c:if test="${alert.direction.equalsIgnoreCase('increase')}">
                                                    <option value="decrease">decrease</option>
                                                </c:if>
                                                <c:if test="${alert.direction.equalsIgnoreCase('decrease')}">
                                                    <option value="increase">increase</option>
                                                </c:if>
                                            </select>
                                        </c:when>
                                        <c:otherwise>
                                            <input class="readonly-input" type="text" name="direction" value="${alert.direction}" readonly>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${requestScope.can == true}">
                                            <input type="text" name="threshold" value="${alert.threshold}" required class="form-control" />
                                        </c:when>
                                        <c:otherwise>
                                            <input class="readonly-input" type="text" name="threshold" value="${alert.threshold}" readonly>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <td>
                                    <c:choose>
                                        <c:when test="${requestScope.can == true}">
                                            <select name="status" class="form-select">
                                                <option value="${alert.status}" selected>${alert.status}</option>
                                                <c:if test="${alert.status.equalsIgnoreCase('inactive')}">
                                                    <option value="active">active</option>
                                                </c:if>
                                                <c:if test="${alert.status.equalsIgnoreCase('active')}">
                                                    <option value="inactive">inactive</option>
                                                </c:if>
                                            </select>
                                        </c:when>
                                        <c:otherwise>
                                            <input class="readonly-input" type="text" name="status" value="${alert.status}" readonly>
                                        </c:otherwise>
                                    </c:choose>
                                </td>
                                <c:if test="${requestScope.can == true}">
                                    <td>
                                        <button type="submit" class="btn btn-primary">Update</button>
                                    </td>
                                </c:if>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>

            <form action="${pageContext.request.contextPath}/main/alert" method="GET" class="mt-4 text-center">
                <button type="submit" class="btn btn-secondary">Go To Alert List</button>
            </form>
        </div>
    </body>
</html>

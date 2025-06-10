<%@ page contentType="text/html; charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>News List</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
        <style>
            body {
                padding: 30px;
            }
        </style>
    </head>
    <body>
        <div class="container bg-white p-4 rounded shadow-sm">
            <h2>Welcome, <c:out value="${sessionScope.currentUser.fullName}"/></h2>

            <div class="mb-3">
                <a href="${pageContext.request.contextPath}/main/news" class="btn btn-primary me-2">News CRUD</a>

                <form action="${pageContext.request.contextPath}/main/auth/logout" method="POST" class="d-inline">
                    <button type="submit" name="action" value="Logout" class="btn btn-danger">Logout</button>
                </form>
            </div>

            <form action="${pageContext.request.contextPath}/main/news" method="GET" class="mb-3">
                <button type="submit" name="action" value="create" class="btn btn-success">Create News</button>
            </form>

            <form action="${pageContext.request.contextPath}/main/news/getNewsByTitle" method="GET" class="row g-2 mb-4">
                <label for="keySearch" class="col-md-4">Search</label>
                <input type="keySearch" id="title" name="keySearch" 
                       placeholder="Enter key search..." class="form-control col-md-4" required />
                
                <!-- nếu cần tìm theo thuộc tính khác nhau thì xài cái dưới, nhớ chỉnh lại url nếu xài cái dưới
                    <div class="col-md-4">
                        <select name="action" class="form-select">
                            <option value="getNewsByContent">Search by Content</option>
                            <option value="getNewsByTitle">Search by Title</option>
                        </select> 

                    </div>
                    <div class="col-md-4">
                        <input type="text" class="form-control" id="keySearch" name="keySearch" placeholder="Search..." required />
                    </div>
                -->
                <div class="col-md-4">
                    <button type="submit" class="btn btn-primary w-100">Search</button>
                </div>
            </form>

            <c:if test="${empty news}">
                <div class="alert alert-warning">No matching news found!</div>
            </c:if>

            <c:if test="${not empty news}">
                <table class="table table-bordered table-hover">
                    <thead class="table-light">
                        <tr>
                            <!-- chỉnh các tên cột -->
                            <th>No</th>
                            <th>News ID</th>
                            <th>Title</th>
                            <th>Content</th>
                            <th>Ticker</th>
                            <th>Action</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="item" items="${requestScope.news}" varStatus="st">
                            <tr>
                                <td>${st.count}</td>
                                <td>${item.id}</td> <!-- để id do của dto là id chứ ko phải newsId -->
                                <td>${item.title}</td>
                                <td>${item.content}</td>
                                <td>${item.ticker}</td>
                                <td class="table-actions">
                                    <form 
                                        action="${pageContext.request.contextPath}/main/news/update" 
                                        method="GET"
                                        >
                                        <button type="submit" name="newsId" value="${item.id}" class="btn btn-sm btn-warning">Update</button>
                                    </form>
                                    <form 
                                        action="${pageContext.request.contextPath}/main/news/delete" 
                                        method="POST" 
                                        onsubmit="return confirm('Delete this news?');"
                                        >
                                        <button type="submit" name="newsId" value="${item.id}" class="btn btn-sm btn-danger">Delete</button>
                                    </form>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </c:if>

            <!-- Back to home -->
            <c:choose>
                <c:when test="${sessionScope.currentUser.role.name() == 'ADMIN'}">
                    <a href="${pageContext.request.contextPath}/admin.jsp" class="btn btn-outline-primary mt-3">Back to admin page</a>
                </c:when>
                <c:when test="${sessionScope.currentUser.role.name() == 'STAFF'}">
                    <a href="${pageContext.request.contextPath}/welcome.jsp" class="btn btn-outline-primary mt-3">Back to home</a>
                </c:when>
            </c:choose>
        </div>
    </body>
</html>

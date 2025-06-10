<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Create News Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <div class="post-container">
            <h3>Welcome, <c:out value="${sessionScope.currentUser.fullName}" /></h3>

            <form action="${pageContext.request.contextPath}/main/auth/logout" method="POST">
                <input type="submit" class="btn btn-danger logout-btn" value="LOGOUT" />
            </form>

            <h3>Create News</h3>

            <p class="message">${requestScope.MSG}</p>

            <form action="${pageContext.request.contextPath}/main/news/create" method="POST">
                <!--Do id của news tự tăng nên ko cần cái dưới, tuỳ đề-->
<!--                <label for="newsId">News ID</label>
                <input type="text" id="newsId" name="newsId" placeholder="Enter news ID" required class="form-control" />-->

                <label for="title">Title</label>
                <input type="text" id="title" name="title" placeholder="Enter title" required class="form-control" />

                <label for="content">Content</label>
                <input type="text" id="content" name="content" placeholder="Enter content" required class="form-control" />
                
                <!-- Đúng là ticker nhưng theo đề nên hiển thị sectorID -->
                <label for="ticker">Sector ID</label> 
                <input type="text" id="ticker" name="ticker" placeholder="Enter ticker" required class="form-control" />
                
                <button type="submit" class="btn btn-primary w-100">Create</button>
            </form>

            <a href="${pageContext.request.contextPath}/main/news" class="back-link">Back to list page</a>
        </div>
    </body>
</html>

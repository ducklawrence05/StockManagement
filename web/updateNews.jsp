<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8" />
        <title>Update News Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" />
        <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    </head>
    <body>
        <div class="post-container">
            <h3>Welcome, <c:out value="${sessionScope.currentUser.fullName}" /></h3>

            <form action="${pageContext.request.contextPath}/main/auth/logout" method="POST">
                <input type="submit" class="btn btn-danger logout-btn" value="LOGOUT" />
            </form>

            <h3>Update User</h3>
            <p class="message">${requestScope.MSG}</p>
            <hr />

            <form id="updateForm" action="${pageContext.request.contextPath}/main/news/update" method="POST">
                <input type="hidden" name="newsId" value="${news.id}"/>

                <!--copy từ create nhưng bỏ required-->
                <label for="title">Title</label>
                <input 
                    type="text" id="title" name="title" 
                    placeholder="Enter title" value="${news.title}"
                    class="form-control" />

                <label for="content">Content</label>
                <input type="text" id="content" name="content" 
                       placeholder="Enter content" value="${news.content}"
                       class="form-control" />
                
                <button type="submit" class="btn btn-primary w-100">Update</button>
            </form>

            <a href="${pageContext.request.contextPath}/main/news" class="back-link">Back to list page</a>
        </div>

    </body>
</html>
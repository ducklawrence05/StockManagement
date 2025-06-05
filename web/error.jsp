<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Error Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body {
                background-color: #f8f9fa;
            }

            .error-container {
                max-width: 600px;
                margin: 100px auto;
                background-color: #fff;
                padding: 30px;
                border-radius: 12px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                text-align: center;
            }

            .error-message {
                color: red;
                font-size: 1.25rem;
            }
        </style>
    </head>
    <body>
        <%
            String error = (String) request.getAttribute("MSG");
            if (error == null) error = "Unknown error occurred.";
        %>

        <div class="error-container">
            <h2>Error</h2>
            <p class="error-message"><%= error %></p>
        </div>
    </body>
</html>

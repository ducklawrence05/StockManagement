<%-- 
    Document   : createAlert
    Created on : Apr 24, 2025, 9:21:46 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Create Alert Page</title>
    </head>
    <body>
        <h1>Create Alert</h1>
        <p style="color: green;">${requestScope.MSG}</p><br>
        <form action="${pageContext.request.contextPath}/main/alert/create" method="POST">
            Ticker: <input type="text" name="ticker" placeholder="Enter ticker" required /><br/>
            Threshold: <input type="number" name="threshold" placeholder="Enter threshold" step="0.01" required /><br/>
            Direction: 
            <select name="direction" required>
                <option value="increase">increase</option>
                <option value="decrease">decrease</option>
            </select><br/>
            <input type="submit" name="action" value="CreateAlert" /><br/>
           <button type="submit">Create</button>
        </form>
        
        
        
    </body>
</html>

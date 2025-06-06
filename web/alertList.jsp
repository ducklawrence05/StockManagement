<%-- 
    Document   : alertList
    Created on : Apr 24, 2025, 8:44:01 AM
    Author     : admin
--%>

<%@page import="dto.Alert"%>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Alert List Page</title>
        <style>
            #directionSelect,
            #statusSelect,
            #tickerInput {
                display: none;
            }
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
            button{
                width: 100px;
            }
        </style>
    </head>
    <body>
        <h1>Welcome, <c:out value="${sessionScope.currentUser.fullName}"/></h1>
        <a 
            href="${pageContext.request.contextPath}/main/alert"
            style="text-decoration: none; color: black"
            >Alert CRUD</a>
        <hr />
        <form action="${pageContext.request.contextPath}/main/auth/logout" method="POST">
            <input type="submit" value="LOGOUT" />
        </form>
        <hr />
        <form action="${pageContext.request.contextPath}/main/alert" method="GET">
            <button type="submit" name="action" value="create">Create Alert</button> 
        </form>

        

        <form action="${pageContext.request.contextPath}/main/alert" method="GET">
            <label for="keySearch">Search By:</label>
            <select id="action" name="action" onchange="updateSelectOptions()">
                <option value="">Get All Alerts</option>
                <option value="getAlertsByDirection">Direction</option>
                <option value="getAlertsByStatus">Status</option>
                <option value="getAlertsByTicker">Ticker</option>
            </select>

            <div id="directionSelect">
                <label for="directionValue">Select Direction:</label>
                <select id="directionValue" name="keySearch">
                    <option value="Increase">Increase</option>
                    <option value="Decrease">Decrease</option>
                </select>
            </div>

            <div id="statusSelect">
                <label for="statusValue">Select Status:</label>
                <select id="statusValue" name="keySearch">
                    <option value="Active">Active</option>
                    <option value="Inactive">Inactive</option>
                </select>
            </div>

            <div id="tickerInput">
                <label for="tickerValue">Search Ticker:</label>
                <input type="text" id="tickerValue" name="keySearch" placeholder="Ex: VNM">
            </div>

            <br>
            <button type="submit">Search</button>
        </form>
        <c:if test="${!empty alerts}">
            <table>
                <thead>
                    <tr>
                        <th>No</th>
                        <th>User ID</th>
                        <th>Ticker</th>
                        <th>Threshold</th>
                        <th>Direction</th>
                        <th>Status</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="alert" items="${requestScope.alerts}" varStatus="st">

                        <tr>
                            <td>${alert.alertID}</td>
                            <td>${alert.userID}</td>
                            <td>${alert.ticker}</td>
                            <td>${alert.threshold}</td>
                            <td>${alert.direction}</td>
                            <td>${alert.status}</td>
                            <td class="actions">
                                <form 
                                    action="${pageContext.request.contextPath}/main/alert/update" 
                                    method="GET"  
                                    >
                                    <input  type="hidden" name="alertID" value="${alert.alertID}">
                                    <input  type="hidden" name="userID" value="${sessionScope.currentUser.userID}">
                                    <button type="submit" name="action" value="update">Update</button>
                                </form>
                                <form 
                                    action="${pageContext.request.contextPath}/main/alert/delete" 
                                    method="POST" 
                                    onsubmit="return confirm('Delete this alert?');"
                                    >
                                    <input  type="hidden" name="alertID" value="${alert.alertID}">
                                    <input  type="hidden" name="userID" value="${sessionScope.currentUser.userID}">
                                    <button type="submit" name="userID" value="${alert.userID}">Delete</button>
                                </form>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
    </form>

    <c:if test="${empty requestScope.ERRMSG}">
        <p style="color: green;">${requestScope.MSG}</p>
        <hr />
    </c:if>

    <c:if test="${!empty requestScope.ERRMSG}">
        <p style="color: red;">${requestScope.ERRMSG}</p>
        <hr />
    </c:if>

    <script>
        function updateSelectOptions() {
            const action = document.getElementById("action").value;

            // Ẩn và disable tất cả
            document.getElementById("directionSelect").style.display = "none";
            document.getElementById("statusSelect").style.display = "none";
            document.getElementById("tickerInput").style.display = "none";

            document.getElementById("directionValue").disabled = true;
            document.getElementById("statusValue").disabled = true;
            document.getElementById("tickerValue").disabled = true;

            // Hiện và enable đúng cái đang chọn
            if (action === "getAlertsByDirection") {
                document.getElementById("directionSelect").style.display = "block";
                document.getElementById("directionValue").disabled = false;
            } else if (action === "getAlertsByStatus") {
                document.getElementById("statusSelect").style.display = "block";
                document.getElementById("statusValue").disabled = false;
            } else if (action === "getAlertsByTicker") {
                document.getElementById("tickerInput").style.display = "block";
                document.getElementById("tickerValue").disabled = false;
            }
        }

        window.onload = updateSelectOptions;
    </script>
</body>
</html>

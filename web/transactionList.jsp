<%-- 
    Document   : transactionList
    Created on : Apr 23, 2025, 9:35:16 AM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.ArrayList"%>
<%@page import="dto.Transaction"%>
<%@page import="dto.User"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Transaction List Page</title>
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

        <hr />

        <form action="${pageContext.request.contextPath}/main/auth/logout" method="POST">
            <button type="submit" name="action" value="Logout">Logout</button>
        </form>

        <hr />    

        <form action="${pageContext.request.contextPath}/main/transaction" method="GET">
            <label for="keySearch">Search</label>
            <input type="text" id="keySearch" name="keySearch" placeholder="Search..."/> |

            <select name="action">
                <option value="getTransactionByTicker">Search by ticker</option>
                <option value="getTransactionByType">Search by type</option>
                <option value="getTransactionByStatus">Search by status</option>
                <option value="getAllTransactions">Search all</option>
            </select>

            <button type="submit">Search</button>
        </form>

        <c:if test="${empty users}">
            <p>No matching users found!</p>
        </c:if>

            <form 
                action="${pageContext.request.contextPath}/main/transaction/create" 
                method="GET">
                <input type="submit" name="action" value="create"></input>    
            </form> 

        <table>
            <thead>
                <tr>
                    <th>ID</th>
                    <th>User ID</th>
                    <th>Ticker</th>
                    <th>Type</th>
                    <th>Quantity</th>
                    <th>Price</th>
                    <th>Status</th>      
                </tr>
            </thead>
            <tbody>
                <c:forEach var = "transaction" items="${requestScope.transactions}" varStatus="st">

                    <tr>
                        <td>${transaction.id}</td>
                        <td>${transaction.userID}</td>
                        <td>${transaction.ticker}</td>
                        <td>${transaction.type}</td>
                        <td>${transaction.quantity}</td>
                        <td>${transaction.price}</td>
                        <td>${transaction.status}</td>
                        <td class="action">
                            
                            <form 
                                action="${pageContext.request.contextPath}/main/transaction/update" 
                                method="GET">
                                <input type="hidden" name="id" value="${transaction.id}">
                                <button type="submit" name="action" value="update">Update</button>    
                            </form> 
                                
                            <form 
                                action="${pageContext.request.contextPath}/main/transaction/delete" 
                                method="POST">
                                
                                <input type="hidden" name="id" value="${transaction.id}"> </input>
                               <button type="submit" name="action" value="delete">Delete</button>
                            </form>                       
                        </td>
                    </tr>
                </c:forEach>
            </tbody>    
        </table>
    </body>
</html>

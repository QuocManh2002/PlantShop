<%-- 
    Document   : header_loginedUser
    Created on : Jun 7, 2022, 1:59:39 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="mycss.css" type="text/css"/>
    </head>
    <body>
        <nav>
            <ul>
                <li><a href="index.jsp">Home</a></li>
                <li><a href="view.jsp?status=4">Change profile</a></li>
                <li><a href="view.jsp?status=2">Completed orders</a></li>
                <li><a href="view.jsp?status=3">Canceled orders</a></li>
                <li><a href="view.jsp?status=1">Processing orders</a></li>
                <form action="mainController" method="post">
                    <li>from<input type="date" name="from" value="<%= (request.getParameter("from")==null)?"":request.getParameter("from") %>">
                        to <input type="date" name="to" value="<%= (request.getParameter("to")==null)?"":request.getParameter("to") %>">
                    <input type="submit" value="Search by Date" name="action"></li>
                </form>
            </ul>
        </nav>
    </body>
</html>

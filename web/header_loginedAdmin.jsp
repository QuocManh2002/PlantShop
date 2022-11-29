<%-- 
    Document   : header_loginedAdmin
    Created on : Jul 18, 2022, 1:42:57 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css"/>
    </head>
    <body>
        <header>
            <ul>
                <li><a href="mainController?action=manageAccounts">Manage Accounts</a></li>
                <li><a href="mainController?action=manageOrders">Manage Orders</a></li>
                  <li><a href="mainController?action=managePlants">Manage Plants</a></li>
                   <li><a href="mainController?action=manageCategories">Manage categories</a></li>
                   <li>Welcome ${sessionScope.name}| <a href="mainController?action=logout">Log out</a></li>
            </ul>
        </header>
    </body>
</html>

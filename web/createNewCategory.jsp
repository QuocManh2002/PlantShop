<%-- 
    Document   : createNewCategory
    Created on : Jul 19, 2022, 3:09:48 PM
    Author     : ADMIN
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css"/>
    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp"></c:import>
        <form action="mainController" method="post">
            <table class="order">
                <tr>
                     <td>New Category Name</td>
                 <td><input type="text" name="txtnewCateName"></td>
                 <td><input type="submit" value="Create Category" name="action"></td>
                 </table>
        </form>
    </body>
</html>

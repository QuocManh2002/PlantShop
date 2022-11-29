<%-- 
    Document   : updateCategory
    Created on : Jul 21, 2022, 1:15:40 AM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>.
        <link rel="stylesheet" href="mycss.css" type="text/css"/>
    </head>
    <body>
        <c:import url="header_loginedAdmin.jsp"></c:import>
        <c:set var="cid" value="${param.cid}"></c:set>
        <table class="order">
                <tr>
                     <td>Category ID</td>
                     <td>New Category Name</td>
                     
                 </tr>
                 <form action="mainController" method="post">
                 <tr>
                     <td><input type="hidden" name="cid" value="${cid}" >${cid}</td>
                    <td><input type="text" name="txtupdateCateName"></td>
                 </tr>
                 <tr>
                     <td></td><td><input type="submit" value="UpdateCate" name="action"></td>
                 </tr>
                 </form>
        </table>
                 
    </body>
</html>

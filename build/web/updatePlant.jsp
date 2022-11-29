<%-- 
    Document   : updatePlant
    Created on : Jul 20, 2022, 7:29:35 PM
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
        <c:set var="pid" value="${param.pid}"></c:set>
        <table>
            
                <tr>
                    <td>ID</td>
                    <td>New Plant Name</td>
                    <td>New Price</td>
                    <td>New Image</td>
                    <td>New Description</td>
                    <td>New Status</td>
                    <td>New Categories ID</td>
                </tr>
                <form action="mainController" method="post">
                <tr>
                    <td><input type="hidden" name="pid" value="${pid}" >${pid}</td>
                    <td><input type="text" name="txtupdatePlantName"></td>
                    <td><input type="text" name="txtupdatePrice"></td>
                    <td><input type="text" name="txtupdateimgpath"></td>
                    <td><input type="text" name="txtupdateDescription"></td>
                    <td><input type="text" name="txtupdateStatus"></td>
                    <td><input type="text" name="txtupdatecateID"></td>
                </tr>
                
                <tr>
                    <td></td>
                    <td></td>
                    <td><input type="submit" value="UpdatePlant" name="action"></td>
                </tr>
                </form>
            </table>
        
    </body>
</html>

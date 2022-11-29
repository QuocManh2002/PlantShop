<%-- 
    Document   : createNewPlant
    Created on : Jul 18, 2022, 10:08:58 PM
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
            <table>
                <tr>
                    <td>Plant Name</td>
                    <td>Price</td>
                    <td>Image</td>
                    <td>Description</td>
                    <td>Status</td>
                    <td>Categories ID</td>
                </tr>
                <tr>
                    <td><input type="text" name="txtnewPlantName"></td>
                    <td><input type="text" name="txtnewPrice"></td>
                    <td><input type="text" name="txtnewimgpath"></td>
                    <td><input type="text" name="txtnewDescription"></td>
                    <td><input type="text" name="txtnewStatus"></td>
                    <td><input type="text" name="txtnewcateID"></td>
                </tr>
                <tr>
                    <td></td>
                    <td></td>
                    <td><input type="submit" value="Create" name="action"></td>
                </tr>
            </table>
        </form>
    </body>
</html>

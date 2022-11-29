<%-- 
    Document   : ManagePlants
    Created on : Jul 18, 2022, 8:17:55 PM
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
        <section>
            <form action="mainController" method="post">
                <input type="submit" value="Create new Plant" name="action"><br>                 
                
            </form>
            
            <table class="order">
                
                <tr>
                    <td>Plant ID</td>
                    <td>Plant Name</td>
                    <td>Price</td>
                    <td>Image</td>
                    <td>Description</td>
                    <td>Status</td>
                    <td>Categories ID</td>
                    <td>Action</td>
                </tr>
            <c:forEach var="plant" items="${sessionScope.plantList}">
                <tr>
                    
                    <td><c:out value="${plant.getId() }"></c:out></td>
                    
                        <td><c:out value="${plant.getName() }"></c:out></td>
                        <td><c:out value="${plant.getPrice() }"></c:out></td>
                        
                            <td><img src="${plant.getImgpath()}" class="plantimg"></td>
                        <td><c:out value="${plant.getDescription() }"></c:out></td>
                        <td><c:out value="${plant.getStatus() }"></c:out></td>
                        <td><c:out value="${plant.getCateid() }"></c:out></td>
                        <td><a href="updatePlant.jsp?pid=${plant.getId()}">Update</a></td>
                </tr>
                
            </c:forEach>
                    
            </table>
        </section>
    </body>
</html>

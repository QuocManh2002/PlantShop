<%-- 
    Document   : ManageCategories
    Created on : Jul 18, 2022, 9:43:17 PM
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
            <form action="createNewCategory.jsp" method="post">
                <input type="submit" value="Create new Category" name="action">                
            </form>
            <table class="order">
                <tr>
                     <td>Category ID</td>
                     <td>Category Name</td>
                     <td>Action</td>
                 </tr>
            <c:forEach var="cate" items="${sessionScope.categoryList}">
                 <tr>
                     <td><c:out value="${cate.getCateID()}"></c:out></td>
                     <td><c:out value="${cate.getCateName()}"></c:out></td>
                     <td><a href="updateCategory.jsp?cid=${cate.getCateID()}" >Update</a></td>
                 </tr>
                 </c:forEach>
            </table>
        </section>
    </body>
</html>

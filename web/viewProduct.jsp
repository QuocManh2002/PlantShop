<%-- 
    Document   : viewProduct
    Created on : Jul 17, 2022, 9:43:02 PM
    Author     : ADMIN
--%>

<%@page import="DBAccess.PlantDao"%>
<%@page import="DTO.Plant"%>
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
            <%@include file="header.jsp" %>
        </header>
        <section>
            <%
//                int id = Integer.parseInt((String) session.getAttribute("pid"));
//                Plant product = PlantDao.getPlant(id);
            %>
            
            <jsp:useBean id="product" class="DTO.Plant" scope="session"/>
            <table class="order">
                <tr><td>Image</td><td>Product ID</td><td>Product Name</td><td>Price</td><td>Status</td><td>Category</td></tr>
                
                <tr><td><img class="plantimg" src="<jsp:getProperty name="product" property="imgpath"></jsp:getProperty>"></td>
                <td><jsp:getProperty name="product" property="id" ></jsp:getProperty></td>
                <td><jsp:getProperty name="product" property="name"></jsp:getProperty></td>
                <td><jsp:getProperty name="product" property="price"></jsp:getProperty></td>
                <td><jsp:getProperty name="product" property="status"></jsp:getProperty></td>
                <td><jsp:getProperty name="product" property="catename"></jsp:getProperty></td>
                </tr>
                
                
                <tr>
                <td><img class="plantimg" src="${product.imgpath}" ></td>
                <td>${product.id}</td>
                <td>${product.name}</td>
                <td>${product.price}</td>
                <td>${product.status}</td>
                <td>${product.catename}</td>                
                </tr>
            
</table >
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>

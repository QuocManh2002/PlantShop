<%-- 
    Document   : index
    Created on : Jun 6, 2022, 11:11:24 PM
    Author     : ADMIN
--%>

<%@page import="DBAccess.PlantDao"%>
<%@page import="DTO.Plant"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>index</title>
        <link rel="stylesheet" href="mycss.css" type="text/css"/>
        <script>
            function warning() {
                var a = window.confirm("Add this plant to cart ?");
                if (a === true) {
                    return true;
                }
                return false;
            }
        </script>
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <section>
            <%
            String keyword = request.getParameter("txtsearch");
            String searchby = request.getParameter("searchby");
            ArrayList<Plant> list;
            String[] tmp ={"out of stock ", "available"};
            if(keyword == null|| searchby == null){
                list = PlantDao.getPlants();
            }else{
                list = PlantDao.getPlants(keyword, searchby);
            }
            if(list !=  null && !list.isEmpty()){
                for(Plant p : list){%>
                <table class="order">
                    <tr><td>Image</td><td>Product ID</td><td>Product Name</td><td>Price</td><td>Status</td><td>Category</td></tr>
                            <tr>
                                <td><img src=" <%= p.getImgpath() %>" class="plantimg"/></td>
                                <td><%= p.getId() %></td>
                                <td><%= p.getName() %></td>
                                <td> <%= p.getPrice() %> </td> 
                                <td><%= p.getStatus() %></td>                                
                                <td><%= p.getCatename() %></td>
                                                <td><a href="mainController?action=addtocart&pid=<%= p.getId()%>" onclick="return warning()">Add to cart</a></td></tr>

                            </tr>
                  
                </table>
                <%}
            }else{
%>
<h1>
    NOT FOUND

</h1>
            <%
}
            %>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>

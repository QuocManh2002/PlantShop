<%-- 
    Document   : viewCart
    Created on : Jun 23, 2022, 4:24:27 PM
    Author     : ADMIN
--%>

<%@page import="DBAccess.PlantDao"%>
<%@page import="DTO.Plant"%>
<%@page import="java.sql.Date"%>
<%@page import="java.util.Set"%>
<%@page import="java.util.HashMap"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css"/>
        <script>
            function warning() {
                var a = window.confirm("Ban co chac muon bo san pham nay? ");
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
            String name = (String) session.getAttribute("name");
            if(name!=null){
            %>
            <h3>Welcome <%= session.getAttribute("name") %> come back</h3>
            <h3><a href="mainController?action=logout">Log out</a></h3>
            <h3><a href="personalPage.jsp">Personal page</a></h3>
            <%}%>
            <font style="color: red;"><%= (request.getAttribute("WARNING")==null)?"":(String)request.getAttribute("WARNING") %></font>
            <table width="100%" class="shopping">
                <tr><td>Product Id</td><td>Images</td><td>Price</td><td>Quantity</td><td>Action</td></tr>
                <%
                HashMap<String, Integer> cart = (HashMap) session.getAttribute("cart");
                int total = 0;
                if(cart!=null){
                    Set<String> pids = cart.keySet();
                    for(String pid: pids){
                        int quantity = cart.get(pid);
                        Plant p = PlantDao.getPlant(Integer.parseInt(pid));
                         %>
                         <form action="mainController" method="post">
                             <tr><td><input type="hidden" value="<%= pid%>" name="pid"><a href="viewProductServlet?pid=<%= pid%>"><%= pid%></a></td>
                                 <td><img src="<%= p.getImgpath() %>" class="plantimg"></td>
                                 <td><%= p.getPrice() %></td>
                                 <td><input type="number" value="<%= quantity%>" name="quantity"></td>
                                 
                                 <td><input type="submit" value="update" name="action">
                                     <input type="submit" value="delete" name="action" onclick="return warning()"></td>
                             </tr>
                         </form>
                                 <% total = total + p.getPrice()*quantity; 
                                 session.setAttribute("pid", pid);
                                }
                    
                }else{ %>
                <tr><td>Your cart is empty</td></tr>
               <%}%>
               <tr><td>Total money: <%= total%></td></tr>
               <tr><td>Order Date: <%= (new Date(System.currentTimeMillis())).toString() %></td></tr>
               <tr><td>Ship Date: After 7 days</td></tr>
            </table>
               
        </section>
               <section>
                   <form action="mainController" method="post">
                       <input type="submit" value="saveOrder" name="action" class="submitorder">
                   </form>
               </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>

<%-- 
    Document   : view
    Created on : Jun 21, 2022, 3:01:59 AM
    Author     : ADMIN
--%>

<%@page import="DBAccess.OrderDao"%>
<%@page import="DBAccess.OrderDao"%>
<%@page import="DTO.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css"/>
    </head>
    <%
            String name = (String) session.getAttribute("name");
            String email=(String) session.getAttribute("email");
            if (name == null) {   
        %>
        <p><font color='red'>You must login to view personal page</font></p>
        <p></p>
        <%
        }else{
        %>
        <header>
            <%@include file = "header_loginedUser.jsp" %>
        </header>
        <section>
            <h3>Welcome <%= name %> come back</h3>
            <h3><a href="mainController?action=logout">Log out</a></h3>
        </section>
        <section> <!-- load all orders of the user at here -->
        <%            
        ArrayList<Order> list = OrderDao.getOrders(email);
        String[] status = {"","processing","completed","canceled"};
        int type = 0;
        try{
         type= Integer.parseInt(request.getParameter("status"));
        }catch(Exception e){
            e.printStackTrace();
        }
        String alert = (String)session.getAttribute("alert");
        if(alert!=null){
            %>
            <h3><%= alert%></h3>
            <%
        }
            else if(alert == null && type==4){
            %>
            <h3>Change your profile</h3>
            <form action="mainController" method="post">
                <table>
                    <tr><td>New name: </td><td><input type="text" name="txtnewname"></td></tr>
                    <tr><td>New phone: </td><td><input type="text" name="txtnewphone"></td></tr>
                    <tr><td></td><td><input type="submit" value="Change" name="action"></td></tr>
                </table>
            </form>
            <%
                
        }
session.setAttribute("alert", null);
        if(list!=null && !list.isEmpty()){
            for (Order ord : list) { 
            if(ord.getStatus() == type){

            %>
            <table class="order">
                <tr><td>Order ID</td><td>Order Date</td><td>Ship Date</td><td>Orders's status</td><td>action</td></tr>
                <tr>
                    <td><%= ord.getOrderID() %></td>
                    <td><%= ord.getOrderDate() %></td>
                    <td><%= ord.getShipDate() %></td>
                    <td><%= status[ord.getStatus()] %>
                        <br/><% if(ord.getStatus() == 1) %><a href="#">cancel order</a>
                    </td>
                    <td><a href="orderDetail.jsp?orderid=<%= ord.getOrderID() %>">detail</a></td>
                </tr>
            </table>
                    <%   } }
        }else{
        %>
        <p>You don't have any order</p> <%}%>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <% } %>


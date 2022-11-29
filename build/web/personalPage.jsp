<%-- 
    Document   : personalPage
    Created on : Jun 7, 2022, 2:08:29 AM
    Author     : ADMIN
--%>

<%@page import="DTO.Account"%>
<%@page import="DBAccess.AccountDao"%>
<%@page import="DBAccess.OrderDao"%>
<%@page import="DTO.Order"%>
<%@page import="DTO.Order"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="mycss.css" type="text/css"/>
    </head>
    
        <%
            String name = (String) session.getAttribute("name");
            String email=(String) session.getAttribute("email");
            Cookie [] c = request.getCookies();
            boolean login = false;
            if (name == null) {  
            String token="";
            for (Cookie aCookie : c) 
                if(aCookie.getName().equals("selector")){
                    token = aCookie.getValue();
                    Account acc = AccountDao.getAccount(token);
                    if(acc!= null){
                        name = acc.getFullname();
                        email = acc.getEmail();
                        login = true;
                        session.setAttribute("acc", acc);
                    }
                }
            }else
            login = true;
            if(!login){
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
         
        ArrayList<Order> list =(ArrayList<Order>) session.getAttribute("list");
        String[] status = {"","processing","completed","canceled"};
        if(list!=null && !list.isEmpty()){
            for (Order ord : list) { %>
            <table class="order">
                <tr><td>Order ID</td><td>Order Date</td><td>Ship Date</td><td>Orders's status</td><td>action</td></tr>
                <tr>
                    <form action="mainController" method="post">
                    <td><input type="hidden" value="<%= ord.getOrderID() %>" name="oid"><%= ord.getOrderID()%></td>
                    <td><%= ord.getOrderDate() %></td>
                    <td><%= ord.getShipDate() %></td>
                    <td><%= status[ord.getStatus()] %>
                        
                            <br/><% if(ord.getStatus() == 1) %><input type="submit" value="cancelOrder" name="action"></a>
                        <% if(ord.getStatus()==3) %><input type="submit" value="orderAgain" name="action"></a>  
                    
                        
                    </td>
                        </form>
                    <td><a href="orderDetail.jsp?orderid=<%= ord.getOrderID() %>">detail</a></td>
                </tr>
            </table>
                    <%    }
        }else{
        %>
        <p>You don't have any order</p> <%}%>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <% } %>

</html>

    


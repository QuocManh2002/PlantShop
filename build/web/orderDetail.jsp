<%-- 
    Document   : orderDetail
    Created on : Jun 19, 2022, 3:14:34 AM
    Author     : ADMIN
--%>

<%@page import="DBAccess.OrderDao"%>
<%@page import="DTO.OrderDetail"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.ArrayList"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            String name = (String) session.getAttribute("name");
            String email = (String) session.getAttribute("email");
            if (name == null) {
        %>
        <p><font color='red'>You must login to view personal page</font></p>
        <p></p>
        <%} else {
        %>
        <header>
            <%@include file = "header_loginedUser.jsp" %>
        </header>
        <section>
            <h3>Welcome <%= name %> come back</h3>
            <h3>Log out</h3>
        </section>
            <section>
                <%
                String orderid = request.getParameter("orderid");
                if(orderid != null){
                    int orderID = Integer.parseInt(orderid.trim());
                    ArrayList<OrderDetail> list = OrderDao.getOrderDetail(orderID);
                    if(list != null && list.size()>0){
                        int money = 0;
                        for(OrderDetail detail: list){%>
                        <table class="order">
                            <tr><td>Order Id</td><td>Plant ID</td><td>Plant Name</td><td>Image</td><td>Price</td><td>Quantity</td></tr>
                            <tr>
                                <td><%= detail.getOrderID() %></td>
                                <td><%= detail.getPlantID() %></td>
                                <td><%= detail.getPlantName() %></td>
                                <td><img src=" <%= detail.getImgPath() %>" class="plantimg"/> </td> 
                                <td><%= detail.getPrice() %></td>
                                
                                <td><%= detail.getQuantity() %></td>.
                                <% money = money + detail.getPrice() * detail.getQuantity(); %>
                            </tr>
                        </table>
                    <%}%>
                    <h3>Total money: <%= money %></h3>
                <%}else{%>
                <p>You don't have any order</p>
                <%}
                  }
                %>
            </section>
            <footer>
            <%@include file="footer.jsp" %>
        </footer>
        <% } %>
    </body>
</html>

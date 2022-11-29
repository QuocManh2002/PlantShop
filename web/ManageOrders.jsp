<%-- 
    Document   : ManageOrders
    Created on : Jul 18, 2022, 7:49:10 PM
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
                    <li>from<input type="date" name="from" value="<%= (request.getParameter("from")==null)?"":request.getParameter("from") %>">
                        to <input type="date" name="to" value="<%= (request.getParameter("to")==null)?"":request.getParameter("to") %>">
                    <input type="submit" value="Search by Date" name="action"></li>
                </form>
                    <br>
                    <form action="mainController" method="post">
                        <input type="text" name="txtidsearch">
                        <input type="submit" value="Search by ID" name="action">
                    </form>
         <section>
             <table class="order">
                 <tr>
                     <td>OrderID</td>
                     <td>Order Date</td>
                     <td>Ship Date</td>
                     <td>Status</td>
                     <td>Account ID</td>
                 </tr>
             <c:forEach var="ord" items="${sessionScope.orderList}">
                 <tr>
                     <td><c:out value="${ord.getOrderID() }"></c:out></td>
                     <td><c:out value="${ord.getOrderDate() }"></c:out></td>
                     <td>
                     <c:choose >
                         <c:when test="${ord.getShipDate() ==null}">
                             Unknown
                         </c:when>
                         <c:otherwise>
                             ${ord.getShipDate()}
                         </c:otherwise>
                     </c:choose>
                     </td>
                     <td><c:out value="${ord.getStatus() }"></c:out></td>
                     <td><c:out value="${ord.getAccID() }"></c:out></td>
                 </tr>
             </c:forEach>
             </table>
         </section>
    </body>
</html>

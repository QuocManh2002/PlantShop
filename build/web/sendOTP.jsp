<%-- 
    Document   : sendOTP
    Created on : Jun 21, 2022, 3:39:14 PM
    Author     : ADMIN
--%>

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
            <form>
                <%
                    String email = (String) request.getParameter("txtemail");
                %>
                <h3 style="text-align: center">Please check your email: <%= email%>, a confirm code is send to you!</h3>
            </form>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>

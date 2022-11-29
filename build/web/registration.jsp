<%-- 
    Document   : registration
    Created on : Jun 6, 2022, 11:22:10 PM
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Registration</title>
        <link rel="stylesheet" href="mycss.css" type="text/css"/>
    </head>
    <body>
        <header>
            <%@include file="header.jsp" %>
        </header>
        <section>
            <form action="mainController" method="post" class="formregister">
                <h1>Register</h1>
                <table>
                    <tr><td>Email</td>
                        <td><input type="text" name="txtemail" required=""parttern="^(\\w)+@(a-zA-Z]+([.](\\w+){1,2}" value="<%= (request.getAttribute("txtemail")==null)?"":request.getAttribute("txtemail")%>"></td></tr>
                    <tr><td>Full name</td>
                        <td> <input type="text" name="txtfullname" required="" value="<%= (request.getAttribute("txtfullname")==null)?"":request.getAttribute("txtfullname")%>"></td></tr>
                    <tr><td>Password</td><td> <input type="password" name="txtpassword" required="" ></td></tr>
                    <tr><td>Phone</td>
                        <td><input type="text" name="txtphone" value="<%= (request.getAttribute("txtphone")==null) ? "": request.getAttribute("txtphone")%>">
                                <%= (request.getAttribute("ERROR")==null)?"":request.getAttribute("ERROR") %></td>
                                </tr>
                    <tr><td colspan="2"><input type="submit" value="register" name="action"></td></tr>
                </table>
            </form>
        </section>
        <footer>
            <%@include file="footer.jsp" %>
        </footer>
    </body>
</html>

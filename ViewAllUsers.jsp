
<%-- 
    Document   : ViewAllUsers
    Created on : Sep 18, 2022, 1:33:14 PM
    Author     : Dell
--%>

<%@page import="java.util.*,SparkBankCodes.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Show All Users</title>
        <link rel="stylesheet" href="ViewAllUsers.css">

    </head>
    <body>

        <%@include file="Navbar.html" %>
        <div class=" header">
            <h5>List Of All Coustumers</h5>
        </div>
        <table class="center" >      
            <tr>


                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Balance</th>
            </tr>



            <%
                List<SparkBankPojo> list = SparkBankModel.getAllUsers();

                for (SparkBankPojo obj : list) {

                    out.println("<td>" + obj.getId() + "</td>");
                    out.println("<td>" + obj.getName() + "</td>");
                    out.println("<td>" + obj.getMail() + "</td>");
                    out.println("<td>" + obj.getBalance() + "</td>");
                    out.println("</tr>");

                }

            %>


        </table>



        <script src="" async defer></script>
    </body>
</body>
</html>

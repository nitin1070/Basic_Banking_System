<%-- 
    Document   : TransferMoney
    Created on : Sep 19, 2022, 9:51:44 PM
    Author     : Dell
--%>

<%@page import="java.util.*,SparkBankCodes.*" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="ViewAllUsers.css">
        <title>Transfer Money</title>
    </head>
    <body>
        <%@include file="Navbar.html" %>
        <div class=" header">
            <h5>TRANSFER MONEY</h5>
        </div>


        <form action="SparkBankControlerServlet" method="GET">
        <table class="center" >      
            <tr>


                <th>ID</th>
                <th>Name</th>
                <th>Email</th>
                <th>Balance</th>
                <th>Operation</th>
            </tr>

            <tr>

                <%

                    List<SparkBankPojo> list = SparkBankModel.getAllUsers();
                    for (SparkBankPojo mylist : list) {
                %>  
                <td><%=mylist.getId()%></td>
                <td><%=mylist.getName()%></td>
                <td><%=mylist.getMail()%></td>
                <td><%=mylist.getBalance()%></td>
                <td><a class="trans-btn" href="TransactionPage.jsp?id=<%= mylist.getId()%>" >Transact</a></td>
            </tr>
            <%
                }
               
            %>


        </table>

        </form>

    </body>
</html>

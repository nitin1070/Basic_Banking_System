<%-- 
    Document   : TransactionPage
    Created on : Sep 19, 2022, 10:28:24 PM
    Author     : Dell
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="SparkBankCodes.SparkBankModel"%>
<%@page import="SparkBankCodes.SparkBankPojo"%>
<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Navbar.css">
        <link rel="stylesheet" href="TransactionPage.css">
        <title>Transaction Money</title>

    </head>
    <body>
        <%@include file="Navbar.html" %>
     <div class=" header">
                <h5>TRANSFER MONEY</h5>
            </div>
    <center>
        <form action="SparkBankControlerServlet" method="POST">
             

            <table >
                <tr>
                    <th>ID</th>
                    <th>Name</th>
                    <th>Email</th>
                    <th>Balance</th>      
                </tr>

                <%
                    String name="";
                    String id = request.getParameter("id");
                    List<SparkBankPojo> mylist = SparkBankModel.getUserById(id);
                    for (SparkBankPojo list : mylist) {
                     name=list.getName();

                %>
                <tr>
                    <td><%=list.getId()%></td>  
                    <td><%=list.getName()%></td>
                    <td><%=list.getMail()%></td>
                    <td><%=list.getBalance()%></td>
                </tr>
                <% }
                %>

            </table>
            <%
                List<SparkBankPojo> list = SparkBankModel.getAllUsers();

            %>



            <div class="userlist-div">

                <div class="chooseuser-div">
                    <label>Choose User To Transfer money</label> 
                </div>

                <select  name="item"class="userlist-dropdown">
                    <option selected disabled>Choose User</option>
                    <%for (SparkBankPojo obj : list) {
                    %>
                    <option><%=obj.getName()%> </option>
                    <% }
                    %>
                </select>  


            </div>

            <div class="ammount-div">
                <label>Ammount </label><input type="number" id="ammount" name="ammount">
                <input type="submit" id="submit" name="transfer" value="TRANSFER"/>
                <input type="hidden" name="senderid" value=<%= id%> />
                <input type="hidden" name="senderName" value=<%= name%> />
                
            </div>
            <%
                java.util.Date dt=new java.util.Date();
               SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-YYYY@hh:mm:ss");
               String today=sdf.format(dt);
                
                
                
            %>
              <input type="hidden" name="date" value=<%= today%> />
        </form>
    </center>

</body>
</html>
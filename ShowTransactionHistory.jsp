<%-- 
    Document   : ShowTransactionHistory
    Created on : Oct 14, 2022, 9:18:26 PM
    Author     : Dell
--%>

<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="SparkBankCodes.TransactionHistoryPojo"%>
<%@page import="SparkBankCodes.SparkBankModel"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="Navbar.css">
         <link rel="stylesheet" href="ShowHistoryTable.css">
        <title>Transaction History</title>
        <style>
          
  
        </style>
    </head>
    <body>
        <%@include file="Navbar.html" %>
          <div class=" header">
            <h5>All Transactions History</h5>
        </div>
    <center>
        <table class="center" >      
            <tr>
                <th>ID</th>
                <th>Sender Name</th>
                <th>Receiver Name</th>
                <th>Ammount</th>
                <th>Date </th>
            </tr>
            <%
                List<TransactionHistoryPojo> list=SparkBankModel.getTransactionHistory();
                for(TransactionHistoryPojo mylist:list){

                    out.println("<td>" + mylist.getId()+ "</td>");
                    out.println("<td>" + mylist.getSenderName() + "</td>");
                    out.println("<td>" + mylist.getReceiverName()+ "</td>");
                    out.println("<td>" + mylist.getAmmount()+ "</td>");
                     out.println("<td>" + mylist.getDate()+ "</td>");
                    
                    out.println("</tr>");

                }
                
          
                %>



        </table>
    </center>

    </body>
</html>

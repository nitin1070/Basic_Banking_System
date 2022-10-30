/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SparkBankCodes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
public class SparkBankControlerServlet extends HttpServlet {
    
    @Override
    public void destroy() {
        super.destroy(); //To change body of generated methods, choose Tools | Templates.
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        
        response.setContentType("text/html");
        
        PrintWriter pw = response.getWriter();
         String ammount = request.getParameter("ammount");
           if(ammount.isEmpty()){
             pw.println("<script type= text/javascript>");
             pw.println("alert('Please fill the ammount')");
           
            pw.println("</script>");
        }
       
        double newammount = Double.parseDouble(ammount);

        String Receivername = request.getParameter("item");
      
        boolean result = SparkBankModel.UpdateReceiverMoney(Receivername, newammount);
        
        
        String id=request.getParameter("senderid");
        String senderName=request.getParameter("senderName");
        int x=Integer.parseInt(id);
        boolean res=SparkBankModel.UpdateSenderMoney(x,newammount);
      
     
       
         String TransactionDate= request.getParameter("date");
         java.util.Date d=new Date();
         SimpleDateFormat sdf=new SimpleDateFormat("dd-MMM-YYYY@hh:mm:ss");
         sdf.format(d);
         java.sql.Date today=new java.sql.Date(d.getTime());
         
         
         TransactionHistoryPojo obj=new TransactionHistoryPojo();
         obj.setId(x);
         obj.setSenderName(senderName);
         obj.setReceiverName(Receivername);
         obj.setAmmount(newammount);
         obj.setDate(today);
        
          int DateRes=SparkBankModel.getTransactionDate(obj);
         if (result || res && DateRes==1) {

            pw.println("<script type= text/javascript>");
            pw.println("alert('Transfer Successfully ')");
             response.sendRedirect("ShowTransactionHistory.jsp");
            pw.println("</script>");
            
        }
         
        else{
            pw.println("<script type= text/javascript>");
            pw.println("alert('SOMETHING WENT WRONG ')");
            pw.println("</script>");
        }
//                   pw.println("<html><head></head>");
//                   pw.println("<body>");
//                 
//                   pw.println("<h3>Transaction date is"+today+"</h3>");
//                    pw.println("<h3> sender name is "+senderName+"</h3>");
//                  
//             
//                 pw.println("</body></html>");
//            
           
                
           

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SparkBankControlerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(SparkBankControlerServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

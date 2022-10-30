/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SparkBankCodes;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Dell
 */
public class ContactServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, SQLException {
        response.setContentType("text/html;charset=UTF-8");
       PrintWriter out = response.getWriter();
            
              ContactPojo obj = new ContactPojo();
            String firstname = request.getParameter("firstname");
            String lastname = request.getParameter("lastname");
            String mobile = request.getParameter("mobile");
            String subject = request.getParameter("subject");
            if (firstname.isEmpty() || lastname.isEmpty() || mobile.isEmpty() || subject.isEmpty()) {
                response.sendRedirect("ContactPage.html");
            } else {
               
                obj.setFirstname(firstname);
                obj.setLastname(lastname);
                obj.setMobile(mobile);
                obj.setSubject(subject);
            }
               
                    int res = SparkBankModel.getContactDetails(obj);
                    if (res == 1) {
                        out.println("<script type= text/javascript>");
                        out.println("alert('ThankYou for Contact me')");

                        out.println("</script>");
                    }
                    else{
                         out.println("<script type= text/javascript>");
                        out.println("alert('Problem while fetching the data')");

                        out.println("</script>");
                    }
    
              
    
            
    
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet ContactServlet</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Servlet ContactServlet at " + request.getContextPath() + "</h1>");
                out.println("</body>");
                out.println("</html>");
            }



        
        @Override
        protected void doGet
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ContactServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

       
        @Override
        protected void doPost
        (HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (SQLException ex) {
            Logger.getLogger(ContactServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
        }

        /**
         * Returns a short description of the servlet.
         *
         * @return a String containing servlet description
         */
        @Override
        public String getServletInfo
        
            () {
        return "Short description";
        }// </editor-fold>

    }

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author HAEMANTH
 */
public class LoginServlet extends HttpServlet {
   private int hitCount; 

   public void init() { 
      // Reset hit counter.
      hitCount = 0;
   } 

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        hitCount++;
        response.setContentType("text/html;charset=UTF-8");
        
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>"); 
            out.println("<style type='text/css'>");
            out.println("body {background-color: #257ef2;\n" +
                "font-family: Arial, Helvetica, sans-serif;}");
            out.println("</style>");          
            out.println("</head>");
            
            try{
                Connection con = DBConnection.initializeDatabase();

                PreparedStatement st = con.prepareStatement("SELECT * FROM users WHERE username=?");

                st.setString(1, username);


                ResultSet rs = st.executeQuery();
                if( !rs.next() ){
                    out.println("<body>");
                    out.println("<center>");
                    out.println("<h1>User does not exist!</h1>");
                    out.println("<p>Click <a href='signup.html'>here</a> to signup</p>");
                    out.println("</center>");
                    out.println("</body>");
                    out.println("</html>");
                    return;
                }
                
                else {
                    String p = rs.getString("password");
                    if(!p.equals(password)){
                        out.println("<body>");
                        out.println("<center>");
                        out.println("<h1>Password is incorrect</h1>");
                        out.println("<p>Click <a href='login.html'>here</a> to login again</p>");
                        out.println("</center>");
                        out.println("</body>");
                        out.println("</html>");
                        return;
                    }
                }

                // Close all the connections
                st.close();
                con.close();
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Success</title>");  
                out.println("<style type='text/css'>");
                out.println("body {background-color: #257ef2;\n" +
                        "font-family: Arial, Helvetica, sans-serif;}");
                out.println("</style>");
                out.println("</head>");
                out.println("<body>");
                out.println("<center>");
                out.println("<h1>Welcome "+username+"! </h1>");
                out.println("<form action='CourseRegister' method='post'>");
                out.println("<input type='hidden' name='username' value='"+username+"'/>");
                out.println("<input type='hidden' name='password' value='"+password+"'/>");
                out.println("<input type='submit' value='Register courses'/>");
                out.println("</form>");
                out.println("<p>Hit count:"+hitCount+"</p>");
                out.println("</center>");
                out.println("</body>");
                out.println("</html>");
            }
            catch(ClassNotFoundException | SQLException e){
                out.println(e);
            }
        }
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
    
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

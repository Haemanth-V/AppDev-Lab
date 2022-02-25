/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.System.console;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HAEMANTH
 */
public class RegisterCourses extends HttpServlet {

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
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            
            /* TODO output your page here. You may use following sample code. */
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String roll = request.getParameter("roll");
            String year = request.getParameter("year");
            String semester = request.getParameter("semester");
            String course = request.getParameter("course");
            
            log(username);
            log(course);
            
            try {
                Connection con = DBConnection.initializeDatabase();
                
                PreparedStatement st = con.prepareStatement("SELECT * FROM student_details "
                        + "WHERE username=?");
                
                st.setString(1, username);
                
                ResultSet rs = st.executeQuery();
                
                if(!rs.next()){
                    
                    st = con.prepareStatement("INSERT INTO student_details(roll, username, year, semester)"
                            + " VALUES(?,?,?,?)");

                    // For the first parameter,
                    // get the data using request object
                    // sets the data to st pointer
                    st.setString(1, roll);

                    // Same for second parameter
                    st.setString(2, username);
                    st.setString(3, year);
                    st.setString(4, semester);

                    // Execute the insert command using executeUpdate()
                    // to make changes in database
                    st.executeUpdate();

                    // Close all the connections
                    st.close();
                
                } else {
                    
                    String r = rs.getString("roll");
                    
                    if(!r.equals(roll)){
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Course registered</title>");  out.println("<style type='text/css'>");
                        out.println("body {background-color: #257ef2;\n" + 
                                "font-family: Arial, Helvetica, sans-serif;}");
                        out.println("</style>");          
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Welcome  " + username + "</h1>");
                        out.println("<h2>Your username does not match your roll number</h2>");
                        out.println("</body>");
                        out.println("</html>");
                        return;
                    }
                
                    st = con.prepareStatement("UPDATE student_details SET year=?, semester=?, username=?"
                            + "WHERE roll=?");

                    // For the first parameter,
                    // get the data using request object
                    // sets the data to st pointer
                    st.setString(1, roll);

                    // Same for second parameter
                    st.setString(2, username);
                    st.setString(3, year);
                    st.setString(4, semester);

                    // Execute the insert command using executeUpdate()
                    // to make changes in database
                    st.executeUpdate();

                    // Close all the connections
                    st.close();
                }
                
                st = con.prepareStatement("SELECT * FROM registered_courses where roll=?");
                st.setString(1,roll);
                
                rs = st.executeQuery();
                
                while(rs.next()){
                    String c = rs.getString("course");
                    
                    if(c.equals(course)){
                        out.println("<!DOCTYPE html>");
                        out.println("<html>");
                        out.println("<head>");
                        out.println("<title>Course registered</title>");  out.println("<style type='text/css'>");
                        out.println("body {background-color: #257ef2;\n" + 
                                "font-family: Arial, Helvetica, sans-serif;}");
                        out.println("</style>");          
                        out.println("</head>");
                        out.println("<body>");
                        out.println("<h1>Welcome " + username + "</h1>");
                        out.println("<h2>You have already registered for course " + course + "</h2>");
                        out.println("</body>");
                        out.println("</html>");
                        return;
                    }
                }
                
                st = con.prepareStatement("Insert into registered_courses set roll=?, course=?");
                st.setString(1, roll);
                st.setString(2,course);                
                
                st.executeUpdate();
                
                con.close();
                
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Course registered</title>");  out.println("<style type='text/css'>");
                out.println("body {background-color: #257ef2;\n" + 
                        "font-family: Arial, Helvetica, sans-serif;}");
                out.println("</style>");          
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Welcome " + username + "</h1>");
                out.println("<h2>"+roll+" registered for course " + course + "</h2>");
                out.println("</body>");
                out.println("</html>");
            }
            
            catch(ClassNotFoundException | SQLException e) {
                out.println(e);
            }
            
            
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}

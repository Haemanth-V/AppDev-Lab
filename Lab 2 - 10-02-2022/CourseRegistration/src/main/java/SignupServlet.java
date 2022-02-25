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


/**
 *
 * @author HAEMANTH
 */
public class SignupServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        try(PrintWriter out = response.getWriter()){
            try {
            
                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>"); 
                out.println("<style type='text/css'>");
                out.println("body {background-color: #257ef2;\n" +
                    "font-family: Arial, Helvetica, sans-serif;}");
                out.println("</style>");          
                out.println("</head>");
                String username = request.getParameter("username");
                String password = request.getParameter("password");

                // Initialize the database
                Connection con = DBConnection.initializeDatabase();
                
                PreparedStatement st = con.prepareStatement("SELECT * FROM users WHERE username=? AND password=?");

                st.setString(1, username);

                // Same for second parameter
                st.setString(2, password);
                
                ResultSet rs = st.executeQuery();
                if( rs.next() ){
                    out.println("<body>");
                    out.println("<center>");
                    out.println("<h1>User already exists!</h1>");
                    out.println("<p>Click <a href='login.html'>here</a> to login</p>");
                    out.println("</center>");
                    out.println("</body>");
                    out.println("</html>");
                    return;
                }

                // Close all the connections
                st.close();
                
                st = con.prepareStatement("SELECT * FROM users WHERE username=?");

                st.setString(1, username);
                
                rs = st.executeQuery();
                if( rs.next() ){
                    out.println("<body>");
                    out.println("<center>");
                    out.println("<h1>Username already chosen! Choose a different username</h1>");
                    out.println("<p>Click <a href='signup.html'>here</a> to signup again</p>");
                    out.println("</center>");
                    out.println("</body>");
                    out.println("</html>");
                    return;
                }

                // Close all the connections
                st.close();
                
                // Create a SQL query to insert data into demo table
                // demo table consists of two columns, so two '?' is used
                st = con.prepareStatement("INSERT INTO users(username, password) VALUES(?,?)");

                // For the first parameter,
                // get the data using request object
                // sets the data to st pointer
                st.setString(1, username);

                // Same for second parameter
                st.setString(2, password);

                // Execute the insert command using executeUpdate()
                // to make changes in database
                st.executeUpdate();

                // Close all the connections
                st.close();
                con.close();

                /* TODO output your page here. You may use following sample code. */
                
                out.println("<body>");
                out.println("<center>");
                out.println("<h1>User added succesfully to the database </h1>");
                out.println("<p>Welcome "+username+"! Click <a href='login.html'>here</a> to login</p>");
                out.println("</center>");
                out.println("</body>");
                out.println("</html>");
            }
            catch (ClassNotFoundException | SQLException e) {
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

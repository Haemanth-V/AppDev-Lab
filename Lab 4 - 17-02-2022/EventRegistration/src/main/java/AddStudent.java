/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 *
 * @author HAEMANTH
 */

public class AddStudent extends HttpServlet {

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
        
        // Initialize the database
        
        PrintWriter out = response.getWriter();
        
        try{
            
            String roll = request.getParameter("roll").trim();
            roll = roll.substring(0, roll.length() - 1);
            String name = request.getParameter("name").trim();
            name = name.substring(0, name.length() - 1);
            String course = request.getParameter("course").trim();
            course = course.substring(0, course.length() - 1);
            String events = request.getParameter("events").trim();
            events = events.substring(0, events.length() - 1);
            String fee = request.getParameter("fee").trim();
            fee = fee.substring(0, fee.length() - 1);
            System.out.println(roll);
            Connection con = DBConnection.initializeDatabase();
                
            PreparedStatement st = con.prepareStatement("SELECT * FROM registered_events WHERE Roll = ?");
            st.setString(1, roll);
            
            ResultSet rs = st.executeQuery();
            
            if(rs.next()) {
                System.out.println("Roll number already registered!");
                out.write("Roll number already registered!");
                return;
            }
                    
                    
            st = con.prepareStatement("INSERT INTO registered_events VALUES(?,?,?,?,?)");

            st.setString(1,roll);
            st.setString(2,name);
            st.setString(3,course);
            st.setString(4,events);
            st.setString(5,fee);
            
            st.executeUpdate();
            
            st.close();
            con.close();
            
            out.write("Record added successfully!");
            
        } catch (Exception e) {
            
            out.write("Record could not be added!");
            e.printStackTrace();
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

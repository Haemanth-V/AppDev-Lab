/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONArray;
import org.json.JSONObject;

/**
 *
 * @author HAEMANTH
 */
public class FetchEntry extends HttpServlet {

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
        try{
            String roll = request.getParameter("roll").trim();
            roll = roll.substring(0, roll.length() - 1);
            
            Connection con = DBConnection.initializeDatabase();
            
            System.out.println("Fetching entry");
                
            PreparedStatement st = con.prepareStatement("SELECT * FROM registered_events WHERE Roll = ?");
            st.setString(1, roll);
            
            System.out.println("Roll: " + roll);

            ResultSet rs = st.executeQuery();
            
            PrintWriter out = response.getWriter();
            
            response.setContentType("text/html");
          
            ResultSetMetaData metadata = rs.getMetaData();
            int numColumns = metadata.getColumnCount();
            if(rs.next()) { 
                JSONObject obj = new JSONObject(); //extends HashMap
                for (int i = 1; i <= numColumns; ++i) //iterate columns
                {
                    String column_name = metadata.getColumnName(i);
                    obj.put(column_name, rs.getObject(column_name));
                    System.out.println("rs.getObject('" + column_name + "')........." + rs.getObject(column_name));
                }

                response.setContentType("application/json; charset=UTF-8");
                String jsonString = obj.toString();
                response.getWriter().write(jsonString);
                System.out.println("Entry : " + jsonString);
            }

            con.close();
            
        } 
        catch (Exception e) {
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

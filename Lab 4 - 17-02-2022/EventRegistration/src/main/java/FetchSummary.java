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
public class FetchSummary extends HttpServlet {

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
        
        PrintWriter out = response.getWriter();
            
        try{
            Connection con = DBConnection.initializeDatabase();
                
            PreparedStatement st = con.prepareStatement("SELECT * FROM registered_events");

            ResultSet rs = st.executeQuery();
            
            response.setContentType("text/html");

//            // send HTML page to client
//            out.println("<html>");
//            out.println("<head><title>Registration Summary</title></head>");
//            out.println("<body>");
//            out.println("<h1>Registration Summary</h1>");
//            out.println("<table cellspacing=\"2\" cellpading=\"8\" border=\"1\" >\n" +
//"                    <tr>\n" +
//"                        <td class=\"col\">\n" +
//"                            <h4>Sl. No.</h4>\n" +
//"                        </td>\n" +
//"                        <td class=\"col\">\n" +
//"                            <h4>Name</h4>\n" +
//"                        </td>\n" +
//"                        <td class=\"col\">\n" +
//"                            <h4>Roll No.</h4>\n" +
//"                        </td>\n" +
//"                        <td class=\"col\">\n" +
//"                            <h4>Course</h4>\n" +
//"                        </td>\n" +
//"                        <td class=\"col\">\n" +
//"                            <h4>Events</h4>\n" +
//"                        </td>\n" +
//"                        <td class=\"col\">\n" +
//"                            <h4>Fee</h4>\n" +
//"                        </td>\n" +
//"                        <td class=\"col\">\n" +
//"                            <h4>Edit</h4>\n" +
//"                        </td>\n" +
//"                        <td class=\"col\">\n" +
//"                            <h4>Delete</h4>\n" +
//"                        </td>\n" +
//"                    </tr>\n" +
//"                ");
//            
//            int i=0;
//           
//            while(rs.next()) {
//                i++;
//                String roll = rs.getString("Roll");
//                String name = rs.getString("Name");
//                String course = rs.getString("Course");
//                String events = rs.getString("Events");
//                String fee = rs.getString("Fee");
//                
//                out.println("<tr>\n" +
//"                        <td class=\"col\">\n" +
//"                            <p>" + Integer.toString(i) + "</p>\n" +
//"                        </td>\n" +
//"                        <td class=\"col\">\n" +
//"                            <p>" + name + "</p>\n" +
//"                        </td>\n" +
//"                        <td class=\"col\">\n" +
//"                            <p>" + roll + "</p>\n" +
//"                        </td>\n" +
//"                        <td class=\"col\">\n" +
//"                            <p>" + course + "</p>\n" +
//"                        </td>\n" +
//"                        <td class=\"col\">\n" +
//"                            <p>" + events + "</p>\n" +
//"                        </td>\n" +
//"                        <td class=\"col\">\n" +
//"                            <p>" + fee + "</p>\n" +
//"                        </td>\n" +
//"                        <td class=\"col\">\n" +
//"                            <input type='button' value='Edit'/>\n" +
//"                        </td>\n" +
//"                        <td class=\"col\">\n" +
//"                            <input type='button' value='Delete'/>\n" +
//"                        </td>\n" +
//"                    </tr>\n" +
//"                ");
//            }
//            
//            out.println("</table></body></html>");
              
            JSONArray json = new JSONArray();
            ResultSetMetaData metadata = rs.getMetaData();
            int numColumns = metadata.getColumnCount();
            while (rs.next()) //iterate rows
            {
                JSONObject obj = new JSONObject(); //extends HashMap
                for (int i = 1; i <= numColumns; ++i) //iterate columns
                {
                    String column_name = metadata.getColumnName(i);
                    obj.put(column_name, rs.getObject(column_name));
                    System.out.println("rs.getObject('" + column_name + "')........." + rs.getObject(column_name));
                }
                json.put(obj);

            }

            response.setContentType("application/json; charset=UTF-8");
            String jsonString = json.toString();
            response.getWriter().write(jsonString);

            Map<String, Object> jsonMap = new HashMap<String, Object>();
            jsonMap.put("Status", "Success");
            jsonMap.put("Rows", 100);

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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.*;
import org.w3c.dom.*;

/**
 *
 * @author HAEMANTH
 */
public class LoginServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    
    private DocumentBuilderFactory fact; 
    private DocumentBuilder builder; 
    private Document doc; 
    private NodeList list, children; 
    private Node node, child; 
    private String str; 
    private String username, password; 
    private ServletConfig sc; 
    
    public void init(ServletConfig sc){ 
        try{ 
            this.sc = sc; 
            str = "C:\\Users\\HAEMANTH\\Documents\\NITT\\Sem 6\\CSLR62 - App Development Lab\\Lab 6 - 09-03-2022\\UserInformation\\src\\main\\java\\Users.xml"; 
            fact = DocumentBuilderFactory.newInstance(); 
            builder = fact.newDocumentBuilder(); 
            doc = (Document)builder.parse(str); 
            System.out.println("In the Init Method"); 
        } 
        catch(Exception e){ 
            System.out.println("Error in the Init Method"+e.getMessage()); 
        } 
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        init();
        try ( PrintWriter out = response.getWriter()) {
            
            
            /* TODO output your page here. You may use following sample code. */
            username = request.getParameter("username"); 
            password = request.getParameter("password");
            
            System.out.println(username + ", " + password);
            
            response.setContentType("text/html");
            
            out.print("<html><head>"
                    + "<style>"
                    + ".key {"
                    + "display: inline-block;"
                    + "margin-left: 42%;"
                    + "}"
                    + ".value {"
                    + "display: inline-block;"
                    + "margin-left: 20px;"
                    + "}" 
                    + "</style></head><body>");
            
            list = doc.getElementsByTagName("User"); 
            out.print("<center><h1>User Information page</h1></center>"); 
            
            boolean found = false;
            
            for(int i=0;i<list.getLength();i++){ 
                node = (Node) list.item(i); 
                Element element = (Element) node;
                if(element.getElementsByTagName("username").item(0).getTextContent().equals(username) &&
                    element.getElementsByTagName("password").item(0).getTextContent().equals(password)){ 
                    
                    System.out.println(node);
                    found = true;
                    out.print("<form method='post' action='VerifyOTP.jsp'>");
                    out.print("<h4 class='key'>User Id:</h4><input class='value' readonly value='"+element.getElementsByTagName("username").item(0).getTextContent()+ "'><br>");
                    out.print("<h4 class='key'>Roll No:</h4><input class='value' name='roll' readonly value='"+element.getElementsByTagName("roll").item(0).getTextContent()+ "'><br>");
                    out.print("<h4 class='key'>Name:</h4><input class='value' readonly value='"+element.getElementsByTagName("name").item(0).getTextContent()+ "'><br>");
                    out.print("<h4 class='key'>Father's name:</h4><input class='value' readonly value='"+element.getElementsByTagName("father_name").item(0).getTextContent()+ "'><br>");
                    out.print("<h4 class='key'>Branch:</h4><input class='value' readonly value='"+element.getElementsByTagName("branch").item(0).getTextContent()+ "'><br>");
                    out.print("<h4 class='key'>Year:</h4><input class='value' readonly value='"+element.getElementsByTagName("year").item(0).getTextContent()+ "'><br>");
                    out.print("<h4 class='key'>Email:</h4><input name='email' class='value' readonly value='"+element.getElementsByTagName("email").item(0).getTextContent()+ "'><br>");
                    out.print("<h4 class='key'>Phone:</h4><input class='value' readonly value='"+element.getElementsByTagName("phone").item(0).getTextContent()+ "'><br>");
                    out.print("<h4 class='key'>Address:</h4><input class='value' readonly value='"+element.getElementsByTagName("address").item(0).getTextContent()+ "'><br>");
                    
                    out.print("<br><br><center><input style='height: 25px; width: 60px;' type='submit' value='Edit'></center>");
                    out.print("</form>");
                }
            }
            
            if(!found) {
                out.print("<p>Invalid credentials! No users found. Please register or check your credentials.</p>");
            }
       
            out.print("</body></html>");
        } catch(Exception e) {
            e.printStackTrace();
        } 
          

    }
    
    public ServletConfig getServletConfig(){ 
        return sc; 
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

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 * @author HAEMANTH
 */
public class AddUser extends HttpServlet {

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
    
    public void addChildToRoot(Element root, String childName, String childText){
        Element username = doc.createElement(childName);
        username.setTextContent(childText);
        root.appendChild(username);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            
            String uname = request.getParameter("uname");
            String roll = request.getParameter("roll");
            String name = request.getParameter("name");
            String father = request.getParameter("father");
            String branch = request.getParameter("branch");
            String year = request.getParameter("year");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String addr = request.getParameter("addr");
            String pwd = request.getParameter("pwd");
            
            // Write to XML file
            Element rootElement = doc.createElement("User");
            
            addChildToRoot(rootElement, "username", uname);
            addChildToRoot(rootElement, "password", pwd);
            addChildToRoot(rootElement, "name", name);
            addChildToRoot(rootElement, "roll", roll);
            addChildToRoot(rootElement, "father_name", father);
            addChildToRoot(rootElement, "branch", branch);
            addChildToRoot(rootElement, "year", year);
            addChildToRoot(rootElement, "email", email);
            addChildToRoot(rootElement, "phone", phone);
            addChildToRoot(rootElement, "address", addr);
            
            doc.getDocumentElement().appendChild(rootElement);
            
            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

                // send DOM to file
                tr.transform(new DOMSource(doc), 
                                     new StreamResult(new FileOutputStream(str)));
                
                out.println("<html><head></head><body><center><br><br><h1>User Added successfully!</h1></center>");
                out.println("Click <a href='login.html'> here </a> to login</body></html>");

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
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

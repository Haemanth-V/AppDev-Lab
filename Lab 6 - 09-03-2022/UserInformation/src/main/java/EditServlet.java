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
public class EditServlet extends HttpServlet {

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
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
           
            list = doc.getElementsByTagName("User");  
            System.out.print("List: ");
            System.out.println(list);
            
            String uname = request.getParameter("uname");
            String pwd = request.getParameter("pwd");
            String roll = request.getParameter("roll");
            String name = request.getParameter("name");
            String father = request.getParameter("father");
            String branch = request.getParameter("branch");
            String year = request.getParameter("year");
            String email = request.getParameter("email");
            String phone = request.getParameter("phone");
            String addr = request.getParameter("addr");
            
            for(int i=0;i<list.getLength();i++){ 
                node = (Node) list.item(i); 
                Element element = (Element) node;
                if(element.getElementsByTagName("roll").item(0).getTextContent().equals(roll)){ 
                    element.getElementsByTagName("name").item(0).setTextContent(name);
                    element.getElementsByTagName("username").item(0).setTextContent(uname);
                    element.getElementsByTagName("father_name").item(0).setTextContent(father);
                    element.getElementsByTagName("password").item(0).setTextContent(pwd);
                    element.getElementsByTagName("branch").item(0).setTextContent(branch);
                    element.getElementsByTagName("year").item(0).setTextContent(year);
                    element.getElementsByTagName("phone").item(0).setTextContent(phone);
                    element.getElementsByTagName("email").item(0).setTextContent(email);
                    element.getElementsByTagName("address").item(0).setTextContent(addr);
                }
            }
            
            
            try {
                Transformer tr = TransformerFactory.newInstance().newTransformer();
                tr.setOutputProperty(OutputKeys.INDENT, "yes");
                tr.setOutputProperty(OutputKeys.METHOD, "xml");
                tr.setOutputProperty(OutputKeys.ENCODING, "UTF-8");

                // send DOM to file
                tr.transform(new DOMSource(doc), 
                                     new StreamResult(new FileOutputStream(str)));
                 
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
                out.print("<center><h1>Editing done successfully!</h1></center>");
                out.print("<h4 class='key'>User Id:</h4><input class='value' readonly value='"+uname+ "'><br>");
                out.print("<h4 class='key'>Roll No:</h4><input class='value' name='roll' readonly value='"+roll+ "'><br>");
                out.print("<h4 class='key'>Name:</h4><input class='value' readonly value='"+name+ "'><br>");
                out.print("<h4 class='key'>Father's name:</h4><input class='value' readonly value='"+father+ "'><br>");
                out.print("<h4 class='key'>Branch:</h4><input class='value' readonly value='"+branch+ "'><br>");
                out.print("<h4 class='key'>Year:</h4><input class='value' readonly value='"+year+ "'><br>");
                out.print("<h4 class='key'>Email:</h4><input name='email' class='value' readonly value='"+email+ "'><br>");
                out.print("<h4 class='key'>Phone:</h4><input class='value' readonly value='"+phone+ "'><br>");
                out.print("<h4 class='key'>Address:</h4><input class='value' readonly value='"+addr+ "'><br>");

                out.print("</body></html>");

            } catch (TransformerException te) {
                System.out.println(te.getMessage());
            } catch (IOException ioe) {
                System.out.println(ioe.getMessage());
            }
        } catch(Exception e) {
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

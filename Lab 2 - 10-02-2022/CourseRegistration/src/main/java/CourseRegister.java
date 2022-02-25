/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author HAEMANTH
 */
public class CourseRegister extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            
            out.println("<!DOCTYPE html>");
            out.println("<html>\n" +
                "    <head>\n" +
                "        <title>Login</title>\n" +
                "        <meta charset=\"UTF-8\">\n" +
                "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                "    <style type=\"text/css\">\n" +
                "            body {\n" +
                "                background-color: #0066ff;\n" +
                "                font-family: Arial, Helvetica, sans-serif;\n" +
                "            }\n" +
                "            label, input {\n" +
                "                display: table-row;\n" +
                "                margin-top: 15px;\n" +
                "                margin-right: 10px;\n" +
                "                height: 20px;\n" +
                "                align-content: center;\n" +
                "            }\n" +
                "            label .button {\n" +
                "                height: 30px;\n" +
                "                border-radius: 5px;\n" +
                "                width: 80px;\n" +
                "            }\n" +
                "            label {\n" +
                "                margin-bottom: 20px;\n" +
                "            }\n" +
                "        </style>\n" +
                "        <script >\n" +
                "            function validate() {\n" +
                "                var roll = document.getElementById(\"roll\").value;\n" +
                "                var year = document.getElementById(\"year\").value;\n" +
                "                var semester = document.getElementById(\"semester\").value;\n" +
                "                var course = document.getElementById(\"course\").value;\n" +
                "                \n" +
                "                if (roll==\"\"){\n" +
                "                    alert(\"Please enter your roll number\");\n" +
                "                } else if (year == \"\"){\n" +
                "                    alert(\"Please enter your year of study\");\n" +
                "                } else if (year > 6) {\n" +
                "                    alert(\"Year cannot be greater than 6\");\n" +
                "                } else if (semester == \"\"){\n" +
                "                    alert(\"Please enter your semester\");\n" +
                "                } else if (semester > 8) {\n" +
                "                    alert(\"Semester cannot be greater than 8\");\n" +
                "                } else if (course == \"\") {\n" +
                "                    alert(\"Enter a course\");\n" +
                "                } else {\n" +
                "                    return true;\n" +
                "                }\n" +
                "                \n" +
                "                return false;\n" +
                "            }\n" +
                "        </script>\n" +
                "        \n" +
                "    </head>\n" +
                "    \n" +
                "    <body >\n" +
                "    <div id=\"main\">\n" +
                "            <center>\n" +
                "                <h1>Welcome "+username+"</h1>" +
                "                <Form name=\"Course Registration\" method=\"post\" onsubmit=\"return validate()\" action=\"RegisterCourses\">\n" +
                "                </label>\n" +
                "                <label>\n" +
                "                <input type=\"textbox\" name=\"uname\" value=\"Username\" readonly/>\n" +
                "                <input type=\"text\" name=\"username\" id=\"username\" value='"+username+"' readonly/>\n" +
                "                </label>\n" +
                "                <label>\n" +
                "                <input type=\"textbox\" name=\"rno\" value=\"Roll Number\" readonly/>\n" +
                "                <input type=\"number\" name=\"roll\" id=\"roll\"/>\n" +
                "                </label>\n" +
                "                <label>\n" +
                "                <input type=\"textbox\" name=\"yr\" value=\"Year\" readonly/>\n" +
                "                <input type=\"number\" name=\"year\" id=\"year\"/>\n" +
                "                </label>\n" +
                "                <label>\n" +
                "                <input type=\"textbox\" name=\"sem\" value=\"Semester\" readonly/>\n" +
                "                <input type=\"number\" name=\"semester\" id=\"semester\"/>\n" +
                "                </label>\n" +
                "                <label>\n" +
                "                    <input type=\"textbox\" name=\"cr\" value=\"Course\" readonly/>\n" +
                "                <input type=\"textbox\" name=\"course\" id=\"course\"/><br><br>\n" +
                "                </label>\n" +
                "                <label>\n" +
                "                   <input type=\"hidden\" name=\"password\" value=\""+password+"\"/>" +
                "                </label>" +
                "                <label>\n" +
                "                <input class=\"button\" type=\"submit\" value=\"Register\"/>\n" +
                "                </label>\n" +
                "            </form>\n" +
                "                </center>\n" +
                "        </div>\n" +
                "    </body></html>");
            out.close();
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

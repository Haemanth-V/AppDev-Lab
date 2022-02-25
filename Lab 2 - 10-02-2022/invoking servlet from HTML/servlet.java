import java.io.*; 
import java.util.*; 
import javax.servlet.*;
 

public class PostParam extends GenericServlet 


{ 

public void service(HttpServletRequest request,ServletResponse response) throws 

ServletException,IOException { 

 

PrintWriter pw = response.getWriter(); 

 

Enumeration e = request.getParameterNames(); 

 

while(e.hasMoreElements()) 

 

{ 

 

String pname = (String)e.nextElement(); 

 

pw.print(pname + " = "); 

 

String pvalue = request.getParameter(pname); 

 

pw.println(pvalue); 

 

} 

 

pw.close(); 

 

} 

 

} 

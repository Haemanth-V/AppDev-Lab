<%-- 
    Document   : exam_server
    Created on : 17-Feb-2022, 3:02:43 am
    Author     : HAEMANTH
--%>

<%@page import="java.sql.ResultSet"%>
<%@page import="java.sql.Statement"%>
<%@page import="java.sql.DriverManager"%>
<%@page import="java.sql.Connection"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
    
    <% 
        int score = 0;
        String roll=request.getParameter("roll");
        String name=request.getParameter("name");
        String marks=request.getParameter("score");
    %>
    
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Results</title>
    </head>
    <body>
    
    <%
        try
        {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/lab3", "root", "10.10.2001");
            Statement st=conn.createStatement();
            ResultSet rs;
            
            for(int i=0; i<5; i++){
                rs = st.executeQuery("SELECT answer FROM Answers WHERE qid='" + Integer.toString(i+1) + "'");
                rs.next();
                String t = "answer"+Integer.toString(i+1);
                String val = request.getParameter(t);
//                out.println("RESPONSE: " + val);
//                out.println(rs.getString("answer"));
//                out.println(val.equals(rs.getString("answer")));
                if(val.equals(rs.getString("answer")))
                    score = score+1;
            }
            
            rs = st.executeQuery("SELECT * FROM student_marks WHERE roll='"+roll+"'");
            
            if(rs.next()){
                String prev_score = rs.getString("Marks");
                int i=st.executeUpdate("UPDATE student_marks SET Marks='"+marks+"' WHERE roll='"+roll+"' AND Name='"+name+"'");
                if(i==0){
                    out.println("Invalid roll number and name! Score not stored in DB.");
                } else {
                    out.println("Score updated! Previous score: "+prev_score);
                }
            }

            else {
                int i=st.executeUpdate("INSERT INTO student_marks(roll, name, marks) values('"+roll+"','"+name+"','"+marks+"')");
                out.println("Data successfully inserted!");
            }
        }
            catch(Exception e)
        {
            System.out.print(e);
            e.printStackTrace();
        }
        out.println("Score:"+Integer.toString(score));

    %>
    
    <center><h1>Marks Scored</h1></center>
        <table cellspacing="2" align="center" cellpadding="8" border="1">
            <tr>
                <td>Roll</td>
                <td><%= request.getParameter("roll")%></td>
            </tr>
            <tr>
                <td>Name</td>
                <td><%= request.getParameter("name")%></td>
            </tr>
            <tr>
                <td>Marks</td>
                <td><%= score%></td>
            </tr>
        </table>
    </body>
    
</html>

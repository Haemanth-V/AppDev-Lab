<%-- 
    Document   : question_form
    Created on : 16-Feb-2022, 7:10:21 pm
    Author     : HAEMANTH
--%>
<script>
    var cur_qn = 0;    
</script>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page language="java" import="java.sql.*" %>
<%@ page import="java.io.*" %>
<%@ page import="java.util.*" %>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js" type="text/javascript"></script>
    

 <html>
 <head>
     <title>Online Examination</title>
   
 <body>
    <center><h1>Online Examination</h1></center>
        <form action="" >
            <div>
                Roll Number: <input type="number" id="roll" name="roll"/>
                Name: <input type="text" id="name" name="name"/>
            </div>
            <center>
                <h2>CSLR62: App Development Laboratory</h2>
            </center>
            <div class="qns" id="1">
                
                <h4 id="q1">
                    Which object of HttpSession can be used to view and manipulate information about a session?
                </h4>
                <div class="options">
                    <input type="radio" name="gender" id="q1_1"/> Session identifier 
                    <input type="radio" name="gender" id="q1_2"/> Creation time 
                    <input type="radio" name="gender" id="q1_3"/> Last accessed time
                    <input type="radio" name="gender" id="q1_4"/> All mentioned above
                </div>
            </div>
            
            <div class="qns" id="2" hidden>
                
                <h4 id="q2">
                    Connection Pooling Class manages no of user requests for connections to improve the performance
                </h4>
                <div class="options">
                    <input type="radio" name="gender" id="q2_1"/> True
                    <input type="radio" name="gender" id="q2_2"/> False
                </div>
                
            </div>
            
            <center>
                <div class="buttons">
                    <button id="prev">Previous</button>
                    <button id="next" onclick="get_next_qn()"/>Next</button>
                </div>
                <button type="submit"/>Submit</button>
            </center>
        </form>
    </body>
    
    <script language="javascript">
     function get_qn(){
        document.getElementById("qno").innerHTML = (cur_qn+1).toString();
        document.getElementById("qn").innerHTML = qns[cur_qn];
    }
    
    function get_next_qn(){
        cur_qn = (cur_qn+1)%2;
        for(let i=1; i<3; i++){
            if(cur_qn === i) {
                document.getElementbyId(i.toString()).style.display = "block";
            }
            else {                
                document.getElementbyId(i.toString()).style.display = "none";
            }
        }
    }
    
//    function validation(Form_obj){ 
//        
//        if(Form_obj.Seat_no.value.length===0){
//       
//            alert("Please,fill up the Seat Number");
//            Form_obj.Seat_no.focus();
//            return false;
//        }
//        if(Form_obj.Name.value.length===0){
//        
//            alert("Please,fill up the Name");
//            Form_obj.Name.focus();
//            return false;
//        }
//        return true;
//    }
 </script>
    
</html>
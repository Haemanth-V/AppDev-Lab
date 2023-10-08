<%-- 
    Document   : UpdateUsers
    Created on : 10-Mar-2022, 2:10:08 am
    Author     : HAEMANTH
--%>

<%@page import="org.w3c.dom.Element"%>
<%@page import="org.w3c.dom.Node"%>
<%@page import="org.w3c.dom.NodeList"%>
<%@page import="org.w3c.dom.Document"%>
<%@page import="javax.xml.parsers.DocumentBuilder"%>
<%@page import="javax.xml.parsers.DocumentBuilderFactory"%>
<%@page import="javax.activation.*"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<% 
    String str;
    ServletConfig sc;
    DocumentBuilderFactory fact; 
    DocumentBuilder builder; 
    Document doc; 
    NodeList list, children; 
    Node node, child; 
    String username, password;
    String roll = request.getParameter("roll").trim();
    System.out.println(roll);
    String name = "";
    String uname = "";
    String father = "";
    String branch = "";
    String year = "";
    String email = "";
    String phone = "";
    String addr = "";
    String pwd = "";
    
    try{ 
        str = "C:\\Users\\HAEMANTH\\Documents\\NetBeansProjects\\UserInformation\\src\\main\\java\\Users.xml"; 
        fact = DocumentBuilderFactory.newInstance(); 
        builder = fact.newDocumentBuilder(); 
        doc = (Document)builder.parse(str);  
        list = doc.getElementsByTagName("User");  
        System.out.print("List: ");
        System.out.println(list);
        for(int i=0;i<list.getLength();i++){ 
            node = (Node) list.item(i); 
            Element element = (Element) node;
            if(element.getElementsByTagName("roll").item(0).getTextContent().equals(roll)){ 
                name = element.getElementsByTagName("name").item(0).getTextContent();
                uname = element.getElementsByTagName("username").item(0).getTextContent();
                father = element.getElementsByTagName("father_name").item(0).getTextContent();
                pwd = element.getElementsByTagName("password").item(0).getTextContent();
                branch = element.getElementsByTagName("branch").item(0).getTextContent();
                year = element.getElementsByTagName("year").item(0).getTextContent();
                phone = element.getElementsByTagName("phone").item(0).getTextContent();
                email = element.getElementsByTagName("email").item(0).getTextContent();
                addr = element.getElementsByTagName("address").item(0).getTextContent();
                System.out.println("Roll: " + roll);
            }
        }
    } 
    catch(Exception e){ 
        System.out.println("Error in Init"+e.getMessage()); 
    }
%>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New User</title>
        <style>
            .key {
                display: inline-block;
            }
            .value{
                display: inline-block;
            }
        </style>
        
        <script>
            
            
            function validate() {
                var uname = document.getElementById("uname").value;
                var pwd = document.getElementById("pwd").value;
                var cpwd = document.getElementById("cpwd").value;
                var roll = document.getElementById("roll").value;
                var name = document.getElementById("name").value;
                var address = document.getElementById("addr").value;
                var email = document.getElementById("email").value;
                var phone = document.getElementById("phone").value;
                var year = document.getElementById("year").value;
                var branch = document.getElementById("branch").value;
                var father = document.getElementById("father").value;

                var pwd_expression =
                  /^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$%^&*-])/;
                var letters = /^[A-Za-z]+$/;

                console.log("In registeration");

                if (uname == "") {
                  alert("Please enter your username");
                } else if(!letters.test(uname)){
                    alert("Username can only contain letters")
                } else if(roll == ""){
                    alert("Enter your roll number");
                } else if(name == "") {
                    alert("Enter your name");
                } else if(father == ""){
                    alert("Enter your father's name");
                } else if(branch == ""){
                    alert("Enter your Branch");
                } else if(year == ""){
                    alert("Enter your year");
                } else if(email == ""){
                    alert("Enter your email ID");
                } else if(phone == ""){
                    alert("Enter your phone number");
                } else if(addr == ""){
                    alert("Enter your address");
                } else if (pwd == "") {
                    alert("Please enter your password");
                } else if (!pwd_expression.test(pwd)) {
                    alert(
                      "Password must have atleast one upper case letter, lower case letter, special character and digit"
                    );
                } else if (pwd.length < 6) {
                    alert("Password minimum length is 6");
                } else if (pwd.length > 12) {
                    alert("Password max length is 12");
                } else if (cpwd == "") {
                    alert("Please confirm your password");
                } else if (pwd != cpwd) {
                    alert("Password and confirm password don't match!");
                } else {
                    return true;
                }
                return false;
          
            }
            
            function clear() {
                document.getElementById("uname").value = ""
                document.getElementById("pwd").value = ""
                document.getElementById("cpwd").value = ""
                document.getElementById("roll").value = "";
                document.getElementById("name").value = "";
                document.getElementById("addr").value = "";
                document.getElementById("email").value = "";
                document.getElementById("phone").value = "";
                document.getElementById("year").value = "";
                document.getElementById("branch").value = "";
                document.getElementById("father").value = "";
            }
        </script>
    </head>
    <body><center>
        <h1>Edit User Information</h1>
        <form method='post' onsubmit='return validate()'>
            <h4 class='key'>User Id:</h4>
            <input class='value' type='text' id='uname' name='uname' value='<%= uname %>'><br>
            <h4 class='key'>Roll No:</h4>
            <input class='value' type='number' id='roll' name='roll' value='<%= roll %>' readonly><br>
            <h4 class='key'>Name:</h4>
            <input class='value' type='text' id='name' name='name' value='<%= name %>'><br>
            <h4 class='key'>Father's Name:</h4>
            <input class='value' type='text' id='father' name='father' value='<%= father %>'><br>
            <h4 class='key'>Branch:</h4>
            <input class='value' type='text' id='branch' name='branch' value='<%= branch %>'><br>
            <h4 class='key'>Year:</h4>
            <input class='value' type='number' id='year' name='year' value='<%= year %>'><br>
            <h4 class='key'>Email:</h4>
            <input class='value' type='text' id='email' name='email' value='<%= email %>'><br>
            <h4 class='key'>Phone:</h4>
            <input class='value' type='number' id='phone' name='phone' value='<%= phone %>'><br>
            <h4 class='key'>Address:</h4>
            <input class='value' id='addr' name='addr' value='<%= addr %>'><br>
            <h4 class='key'>Password: </h4>
            <input class='value' type='password' id='pwd' name='pwd' value="<%=pwd%>"><br>
            <h4 class='key'>Confirm Password: </h4>
            <input class='value' type='password' id='cpwd' name='cpwd' value="<%=pwd%>"><br>
            
            
            <input type='submit' value='Submit' formaction="EditServlet" > 
            <input type='reset' value='Clear' onclick='clear()'> 
        </form></center>
    </body>
</html>


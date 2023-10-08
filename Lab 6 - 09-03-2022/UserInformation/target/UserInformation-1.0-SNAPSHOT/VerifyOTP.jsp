<%-- 
    Document   : VerifyOTP
    Created on : 10-Mar-2022, 12:10:42 am
    Author     : HAEMANTH
--%>


<%@page import="java.util.*"%>
<%@page import="javax.mail.*"%>
<%@page import="javax.mail.Session"%>
<%@page import="javax.mail.internet.*"%>
<%@page import="javax.mail.Transport"%>
<%@page import="javax.activation.*"%>


<%
    final String recipient = request.getParameter("email");
    String roll = request.getParameter("roll");
    System.out.println("Recipient: "+ recipient);
    System.out.println("Roll:"+roll);
    String otp = sendMail(recipient);
    System.out.println("OTP "+otp);

%>

<%!
    public String sendMail(String recipient) {// email ID of Recipient.
        // email ID of  Sender.
        final String sender = "phononsclient@gmail.com";
//
//        // using host as localhost
//        String host = "127.0.0.1";
//
//        // Getting system properties
//        Properties properties = System.getProperties();
//
//        // Setting up mail server
//        properties.setProperty("mail.smtp.host", host);
//
//        // creating session object to get properties
//        Session session = Session.getDefaultInstance(properties);
//
//        try
//        {
//           // MimeMessage object.
//           MimeMessage message = new MimeMessage(session);
//
//           // Set From Field: adding senders email to from field.
//           message.setFrom(new InternetAddress(sender));
//
//           // Set To Field: adding recipient's email to from field.
//           message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
//
//           // Set Subject: subject of the email
//           message.setSubject("OTP for Editing User information");
//           
//           int randomPin   =(int) (Math.random()*9000)+1000;
//           String otp  = String.valueOf(randomPin);
//
//           
//        
//            // set body of the email.
//           message.setText("Use " + otp + "as your OTP for verification!");
//
//           // Send email.
//           Transport.send(message);
//           System.out.println("Mail successfully sent");
//           return otp;
//        }
//        catch (MessagingException mex){
//           mex.printStackTrace();
//           return "Fail";
//        }

// Assuming you are sending email from through gmails smtp
        String host = "smtp.gmail.com";

        // Get system properties
        Properties properties = System.getProperties();

        // Setup mail server
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        // Get the Session object.// and pass username and password
        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(sender, "Phonons@123");

            }

        });

        // Used to debug SMTP issues
        session.setDebug(true);

        try {
            // Create a default MimeMessage object.
            MimeMessage message = new MimeMessage(session);

            // Set From: header field of the header.
            message.setFrom(new InternetAddress(sender));

            // Set To: header field of the header.
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));

            // Set Subject: subject of the email
            message.setSubject("OTP for Editing User information");
           
            int randomPin  = (int)(Math.random()*9000)+1000;
            String otp  = String.valueOf(randomPin);          
        
            // set body of the email.
            message.setText("Use " + otp + " as your OTP for verification!");

            System.out.println("sending...");
            // Send message
            Transport.send(message);
            System.out.println("Sent message successfully....");

            return otp;
        } catch (MessagingException mex) {
            mex.printStackTrace();
            return "Fail";
        }

    }


%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Verify OTP</title>
        <script>
            function validate() {
                var enteredOTP = document.getElementById("otp").value;
                
                if(enteredOTP == <%= otp %>){
                    alert("OTP verified successfully!");
                    return true;
                } else {
                    alert("Invalid OTP! Try again");
                    return false;
                }
            }
        </script>
    </head>
    <body>
        <center>
            <form>
                <h1>OTP Sent Successfully to <%= recipient %> </h1>
                <input type="number" placeholder="Enter the OTP" id="otp" ><br><br><br>
                <input style="margin-right: 10px;" type="submit" onclick="return validate()" formaction="UpdateUsers.jsp" >
                <input type="submit" value="Cancel" formaction="login.html" >
                <input type='number' value='<%= roll %>' name="roll" hidden >
            </form>
        </center>
    </body>
</html>

<%@page import="com.entity.contactus"%>
<%@page import="java.util.List"%>
<%@page import="com.dao.DAO5"%>
<%@page import="com.conn.DBConnect"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Table Conact Us</title>

<link rel="stylesheet" href="images/bootstrap.css">
<link rel="stylesheet" href="Css/w3.css">
<link rel="stylesheet" href="Css/font.css">

<style>
.w3-sidebar a {font-family: "Roboto", sans-serif}
body,h1,h2,h3,h4,h5,h6,.w3-wide {font-family: "Montserrat", sans-serif;}
</style>
</head>
<body>

<form method="post" action="">
    <%@ include file="admin_navbar.jsp" %>

    <center>
        <div style="background-color: #ebe9eb">    
            <br>
            <h1>Contact Us Table</h1>
            <br>
        </div>

        <br>

        <table border>
            <thead>
                <tr style='background-color:#ebe9eb'>
                    <th style='border: 1px solid; text-align: center'>Id</th>
                    <th style='border: 1px solid; text-align: center'>Name</th>
                    <th style='border: 1px solid; text-align: center'>Email Id</th>
                    <th style='border: 1px solid; text-align: center'>Contact No.</th>
                    <th style='border: 1px solid; text-align: center'>Message</th>
                    <th style='border: 1px solid; text-align: center' colspan="2" align="center">Actions</th>
                </tr>
            </thead>
            <tbody>
                <% 
                try {
                    DAO5 dao = new DAO5(DBConnect.getConn());
                    List<contactus> listv = dao.getcontactus();
                    for(contactus v : listv) {
                %>
                <tr>
                    <td style='border: 1px solid; text-align: center'><%= v.getId() %></td>
                    <td style='border: 1px solid; text-align: center'><%= v.getName() %></td>
                    <td style='border: 1px solid; text-align: center'><%= v.getEmail_Id() %></td>
                    <td style='border: 1px solid; text-align: center'><%= v.getContact_No() %></td>    
                    <td style='border: 1px solid; text-align: center'><%= v.getMessage() %></td>
                    <td style='border: 1px solid; text-align: center'><a href='remove_contactus?id=<%= v.getId() %>'><img src="images/delete.jpg" alt="Remove" height="25px"></a></td>
                </tr>
                <% 
                    }
                } catch (Exception e) {
                    out.println("Error: " + e.getMessage());
                    e.printStackTrace();
                }
                %>
            </tbody>
        </table>
    </center>

    <br>
	<footer text-align: center;
  padding: 3px;
  background-color: DarkSalmon;
  color: white;>
  
	<%@ include file = "footer.jsp" %>
</footer>
</form>

</body>
</html>

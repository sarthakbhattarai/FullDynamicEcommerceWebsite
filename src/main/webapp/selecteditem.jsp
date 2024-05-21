<%@page import="com.entity.viewlist"%>
<%@page import="com.dao.DAO2"%>
<%@page import="java.util.Base64.Decoder"%>
<%@page import="java.sql.*,java.io.*,java.text.*,java.util.*" %> 
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Product Details</title>
<link rel="stylesheet" href="images/bootstrap.css">
<link rel="stylesheet" href="Css/w3.css">
<link rel="stylesheet" href="Css/font.css">
<style>
.w3-sidebar a {font-family: "Roboto", sans-serif}
body,h1,h2,h3,h4,h5,h6,.w3-wide {font-family: "Montserrat", sans-serif;}
</style>
</head>
<body>
<%@ include file="navbar.jsp" %>
<center>
<div style="background-color: #ebe9eb">
    <br>
    <h1>Product</h1>
    <br>
</div>
<br>

<div class="container border" style="background-color:">
    <center>
    <div class="row">
        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
        <% 
            String st = request.getParameter("Pn");
            DAO2 dao = new DAO2(DBConnect.getConn());       
            viewlist product = dao.getProductByImageName(st);
            if (product != null) { // Check if product is not null
        %>  
            <table>
                <tr>
                    <th colspan='2' align='center'><img src='images/<%= product.getPimage() %>' height=250px></th>
                </tr>
            </table>
            <table border='1' cellspacing=5 cellpadding=5 align='center'>
                <tr>
                    <th>Brand: </th>
                    <th><%= product.getBname() %></th>
                </tr>
                <tr>
                    <th>Category: </th>
                    <th><%= product.getCname() %></th>
                </tr>
                <tr>
                    <th>Product Name: </th>
                    <th><%= product.getPname() %></th>
                </tr>
                <tr>
                    <th>Price: </th>
                    <th>₹ <%= product.getPprice() %></th>
                </tr>
                <tr>
                    <th>Quantity: </th>
                    <th><%= product.getPquantity() %></th>
                </tr>
                <tr>
                    <th style='text-align: center' colspan='2' align='center' bgcolor='#D6EEEE'>
                        <a href='addtocartnull?Pn=<%= product.getPimage() %>'>Add To Cart</a>
                    </th>
                </tr>
            </table>
        <% 
            } else {
                // Handle case when product is null
                out.println("Product not found!");
            }
        %>
        </div>
        <div class="col-lg-6 col-md-6 col-sm-12 col-xs-12">
            <!-- Product description -->
            <h2><%= product != null ? product.getPname() : "" %></h2>
            <h3>₹ <%= product != null ? product.getPprice() : "" %></h3>
            <p>
                Lorem ipsum dolor sit amet, consecte adipisicing elit, 
                sed do eiusmll tempor incididunt ut labore et dolore magna 
                aliqua. Ut enim ad mill veniam, quis nostrud exercitation 
                ullamco laboris nisi ut aliquip exet commodo consequat. 
                Duis aute irure dolor
            </p>
            <br>
            <h3>Description</h3><br>
            <p>
               <%= product.getDesc() %>
            </p>
        </div>
    </div>
    </center>
</div>
</body>
</html>

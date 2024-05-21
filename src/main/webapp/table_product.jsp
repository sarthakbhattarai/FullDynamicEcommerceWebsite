<%@page import="java.util.List"%>
<%@page import="com.dao.DAO5"%>
<%@page import="com.conn.DBConnect"%>
<%@page import="com.entity.Product"%>
<%@page import="com.dao.DAO3"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Table Product</title>

<link rel="stylesheet" href = "images/bootstrap.css">

<link rel="stylesheet" href="Css/w3.css">
<link rel="stylesheet" href="Css/font.css">

<style>
.w3-sidebar a {font-family: "Roboto", sans-serif}
body,h1,h2,h3,h4,h5,h6,.w3-wide {font-family: "Montserrat", sans-serif;}
</style>
</head>
<body>

<form method = "post" action = "addView">
	<%@ include file = "admin_navbar.jsp" %>



<center>
<div style="background-color: #ebe9eb">	
	<br>
	<h1>All Product</h1>
	<br>
	</div>

	<br>
	

	<div class = "table-responsive">
	<table border>
	<thead>
		<tr style='background-color:#ebe9eb'>
			<th style='border: 1px solid ; text-align: center'>Product Id</th>
			<th style='border: 1px solid ; text-align: center'>Product Name</th>
			<th style='border: 1px solid ; text-align: center'>Price</th>
			<th style='border: 1px solid ; text-align: center'>Quantity</th>
			<th style='border: 1px solid ; text-align: center'>Image Path</th>
			<th style='border: 1px solid ; text-align: center'>Brand Name</th>
			<th style='border: 1px solid ; text-align: center'>Category Name</th>
			<th style='border: 1px solid ; text-align: center'>Description</th>
						<th style='border: 1px solid ; text-align: center' colspan="2" align="center">Actions</th>
			
		</tr>
	</thead>
	
	
	
	<tbody>
	<%
	String CategoryName="";
	String BrandName="";
	DAO3 dao = new DAO3(DBConnect.getConn());
	List<Product> listv = dao.getAllProduct();
	for(Product v : listv)
	{
		if (v.getBid() == 1){
			BrandName="Samsung";
		}
		else if (v.getBid() == 3){
			BrandName="Lenovo";
		}
		else if (v.getBid() == 2){
			BrandName="Sony";
		}
		else if (v.getBid() == 4){
			BrandName="Acer";
		}
		else if (v.getBid() == 5){
			BrandName="Apple";
		}
		
		if (v.getCid()==1){
			CategoryName="Laptop";
		}
		else if (v.getCid()==2){
			CategoryName="TV";
		}
		else if (v.getCid()==3){
			CategoryName="Mobile";
		}
		else if (v.getCid()==4){
			CategoryName="Watch";
		}
		
	%>
	
	
				<tr>
					<td style='border: 1px solid ; text-align: center' name='Pid'><%=v.getPid() %></td>
					<td style='border: 1px solid ; text-align: center' name='name'><%=v.getPname() %></td>
					<td style='border: 1px solid ; text-align: center' name='price'> ₹<%=v.getPprice() %></td>
					<td style='border: 1px solid ; text-align: center' name='quantity'><%=v.getPquantity() %></td>
					<td style='border: 1px solid ; text-align: center' name='image'> <%=v.getPimage()%></td>
					<td style='border: 1px solid ; text-align: center' name='brandName'><%=BrandName %></td>
					<td style='border: 1px solid ; text-align: center' name='categoryName'><%=CategoryName %></td>
					<td style='border: 1px solid ; text-align: center' name='prodDesc'><%=v.getProdDesc() %></td>
						
	
					
					
						<td style='border: 1px solid ; text-align: center'><a href='removeProduct?id=<%=v.getPid()%>'><img src = "images/delete.jpg" alt="Remove" height= 25px></td>
					
					
				</tr>
			<%} %>
	
	
	
	</tbody>
	</table>
	</div>
	<br>
	
	<button>Add to User View</button>

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
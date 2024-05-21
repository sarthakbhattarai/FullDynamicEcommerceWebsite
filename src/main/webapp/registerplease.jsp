<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href = "images/bootstrap.css">

<link rel="stylesheet" href="Css/w3.css">
<link rel="stylesheet" href="Css/font.css">
<link rel="stylesheet" href="Css/abc.css">
<style>
.w3-sidebar a {font-family: "Roboto", sans-serif}
body,h1,h2,h3,h4,h5,h6,.w3-wide {font-family: "Montserrat", sans-serif;}

.imgReg{
width: 450px;
}
.reg{
    
}
}
</style>
</head>
<body>
<%@ include file = "navbar.jsp" %>
<h2>I see, you haven't registered yet!</h2>
<h3>Hope you register now!</h3>
<img class="imgReg"alt="Please Register" src="images/register.png">
<br>
<br>
<button class="reg"><a href = "customer_reg.jsp" style='text-decoration: none'>Register</a></button>

</body>
</html>
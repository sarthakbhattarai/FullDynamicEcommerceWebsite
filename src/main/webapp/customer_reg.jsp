<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href = "images/bootstrap.css">

<link rel="stylesheet" href="Css/w3.css">
<link rel="stylesheet" href="Css/abc.css">
<link rel="stylesheet" href="Css/font.css">
<link rel="stylesheet" href="Css/whitespace.css">

<style>
.w3-sidebar a {font-family: "Roboto", sans-serif}
body,h1,h2,h3,h4,h5,h6,.w3-wide {font-family: "Montserrat", sans-serif;}


.a{
	margin-right: 200px;
	
	}
    
    .b{
	margin-right: 205px;
	
	}
	
	.e{
	margin-right: 215px;
	
	}
    
    .d{
	margin-right: 185px;
	
	}
</style>
</head>


<body>
		
		<form id="registrationForm" method= "post" action = "addcustomer" enctype="multipart/form-data" >
			<%@ include file = "navbar.jsp" %>
			
			<% String Total5 = request.getParameter("Total"); %>
	
	<input type = "hidden" name = "Total" value =<%=Total5 %> >
	
	
	<% String CusName5 = request.getParameter("CusName"); %>
	
	<input type = "hidden" name = "CusName" value =<%=CusName5 %> >
	
			
			<div align = "center">
			<div style="background-color: #ebe9eb">
	<br>
	<h1>Customer Registration</h1>
	<br>
	</div>
	<br>
	<div class = "container border" style="background-color:white">
                <br>
	
	<div class = "row" style="justify-content: center">

                        <div class = "col-xl-2 col-lg-1 col-md-1" style="background-color:white">
                            <br>
                            <center>
                                <img src="images/regimg.png" alt=Picture height="400px">   
                            </center><br>

                        </div>
                        <br>
                        &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;
                        &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;
                        &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;
                        &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;
                        &ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;&ensp;

                        <div class = "col-xl-2 col-lg-1 col-md-1" style="background-color: white">
                            <center> 
	
	
	
	
	
			<br>
            <h4 class = "a ws"><b>Set Username : </b></h4>
            <h2><input type ="text"  name ="Username" id="Username"  class = "c" required></h2>
            
            <h4 class = "b ws"><b>Set Password : </b></h4>
            <h2><input type ="password"  name ="Password" id="Password" class = "c" required></h2>
            
            <h4 class = "b ws"><b>Confirm Password : </b></h4>
            <h2><input type ="password"  name ="Password" id="ConfirmPassword" class = "c" required></h2>
            <h4 class = "e ws"><b>Set Email Id : </b></h4>
            <h2><input type ="email"  name ="Email_Id" id="Email_Id" class = "c" required></h2>
            
            <h4 class = "d ws"><b>Set Contact No : </b></h4>
            <h2><input type ="text"  name ="Contact_No" id="Contact_No" class = "c" required></h2>
            
            <h4 class = "d ws"><b>Set Address : </b></h4>
            <h2><input type ="text"  name ="Address" id="Address" class = "c" required></h2>
            <br>
            <h3><b><input type ="submit" name="b1" value ="Register"></b></h3>
          	
            
            
            
            </center> 
                            <br>
                            <br>                        
                        </div>
                    
                </div>    
                <br>
            </div>   
           
           
           
           
           
           
           
           </div>
            <br>
	<footer text-align: center;
  padding: 3px;
  background-color: DarkSalmon;
  color: white;>
  
	<%@ include file = "footer.jsp" %>
</footer>

<script>
        function validateForm() {
            var username = document.getElementById("Username").value.trim();
            var password = document.getElementById("Password").value.trim();
            var confirmPassword = document.getElementById("ConfirmPassword").value.trim();
            var email = document.getElementById("Email_Id").value.trim();
            var contactNo = document.getElementById("Contact_No").value.trim();
            var address = document.getElementById("Address").value.trim();

            if (username === "") {
                alert("Please enter a username.");
                return false;
            }
            if (password.length < 8) {
                alert("Password should be at least 8 characters long.");
                return false;
            }
            if (password !== confirmPassword) {
                alert("Passwords do not match. Please retype your password.");
                return false;
            }
            if (!/(?=.*\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=]).{8,}/.test(password)) {
                alert("Password must contain at least one number, one lowercase and one uppercase letter and atleast one special character.");
                return false;
            }
            if (email === "") {
                alert("Please enter an email address.");
                return false;
            }
            if (contactNo === "" || contactNo.length !== 10 || isNaN(contactNo)) {
                alert("Please enter a valid 10-digit contact number.");
                return false;
            }
            return true;
        }

        document.getElementById("registrationForm").onsubmit = function() {
            return validateForm();
        };
    </script>     
            
		</form>

</body>
</html>
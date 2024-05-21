<%@page import="com.entity.customer"%>

<%
customer cust = (customer) session.getAttribute("customer");

if (cust == null) {
    response.sendRedirect("customerlogin.jsp");
    return;
}
%>
<!DOCTYPE html>
<html>
<head>
    <title>Profile</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            overflow: hidden;
        }

        h3 {
            text-align: center;
        }

        label {
            font-weight: bold;
            margin-top: 10px;
            display: block;
        }

        input[type="text"],
        input[type="password"] {
            width: calc(100% - 20px);
            padding: 10px;
            margin-top: 5px;
            margin-bottom: 20px;
            border: 1px solid #ccc;
            border-radius: 4px;
            box-sizing: border-box;
        }

        button {
            background-color: #4CAF50;
            color: white;
            padding: 10px 20px;
            border: none;
            border-radius: 4px;
            cursor: pointer;
            width: 100%;
            max-width: 300px;
            margin: 20px auto;
            display: block;
        }

        button:hover {
            background-color: #45a049;
        }

        

        .profile-container {
            float: left;
            width: 40%;
            padding-right: 20px;
            box-sizing: border-box;
            text-align: center;
        }

        .profile {
            border-radius: 50%;
            width: 200px;
            height: 200px;
            margin-bottom: 20px;
        }

        .form-container {
            float: left;
            width: 60%;
        }

        .form-group {
            overflow: hidden;
        }

        /* Eye icon */
        .password-toggle {
            position: absolute;
            right: 23px;
            top: 40%;
            transform: translateY(-50%);
            cursor: pointer;
            opacity: 0.5;
        }

        .password-toggle:hover {
            opacity: 1;
        }
    </style>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css">
</head>
<body>
<%@ include file="profile_navbar.jsp" %>
<div class="container">
    <h3>Personal Information</h3>
    <div class="form-group">
        <div class="profile-container">
            <img class="profile" alt="Profile pic" src="images/profile.png">
        </div>
        <div class="form-container">
            <form id="update-user" method="post" action="updateUser">
                <input type="hidden" name="custId" value="<%=cust.getCust_Id()%>" required>
                <label for="Name">Name:</label>
                <input type="text" id="Name" name="Name" value="<%= cust.getName() %>" placeholder="Full Name">
                <label for="Email">Email:</label>
                <input type="text" id="Email" name="Email" value="<%=cust.getEmail_Id()%>" placeholder="Email">
                <label for="Phone">Contact Number:</label>
                <input type="text" id="Phone" name="Phone" value="<%=cust.getContact_No() %>" placeholder="Phone">
                <label for="password">Password:</label>
                <div style="position: relative;">
                    <input type="password" id="password" name="password" value="<%= cust.getPassword() %>" placeholder="Password">
                    <span class="password-toggle" onclick="togglePassword()"><i class="fas fa-eye"></i></span>
                </div>
                <label for="Address">Address:</label>
                <input type="text" id="Address" name="Address" value="<%= cust.getAddress() %>">
                <button type="submit">Update</button>
            </form>
        </div>
    </div>
</div>

<footer text-align: center;
  padding: 3px;
  background-color: DarkSalmon;
  color: white;
  
  >
  
    <%@ include file = "footer.jsp" %>
</footer>

<%
    // Retrieve the update message from session
    String updateMessage = (String) session.getAttribute("updateMessage");
    if (updateMessage != null) {
%>
    <script>
        alert("<%= updateMessage %>");
    </script>
<%
    // Remove the message from session after displaying
    session.removeAttribute("updateMessage");
    }
%>

<!-- JavaScript for toggling password visibility -->
<script>
    function togglePassword() {
        var passwordInput = document.getElementById("password");
        if (passwordInput.type === "password") {
            passwordInput.type = "text";
        } else {
            passwordInput.type = "password";
        }
    }
</script>
</body>
</html>

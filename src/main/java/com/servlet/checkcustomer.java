package com.servlet;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.conn.DBConnect;
import com.dao.DAO2;
import com.entity.PasswordEnDe;
import com.entity.customer;

@MultipartConfig
@WebServlet("/checkcustomer")
public class checkcustomer extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public checkcustomer() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Create a DAO object to interact with the database
        DAO2 dao = new DAO2(DBConnect.getConn());
        PasswordEnDe pd = new PasswordEnDe();
        
        // Retrieve parameters from the request
        String email = request.getParameter("Email_Id").trim();
        String password = request.getParameter("Password").trim();
        String total = request.getParameter("Total");
        String cusName = request.getParameter("CusName");
        
        Connection con = DBConnect.getConn();
        String sql = "select * from customer  where Email_Id=?";
        PreparedStatement st;
        
        try {
        	 
            st = con.prepareStatement(sql);
            st.setString(1, email);
            ResultSet rs = st.executeQuery();
            
            if(rs.next()) {
                String dbEncryptedPassword = rs.getString("Password");
                String decryptedPassword = pd.decrypt(dbEncryptedPassword);
                
                if (decryptedPassword.equals(password)) {
                    // Successful login
                    
                    // Attempt to retrieve the customer from the database based on provided email and password
                    customer cust = dao.giveCust(email);
                    System.out.println(cust.getName());
                    // If a customer is found, set it in the session
                    if (cust != null) {
                    	HttpSession session = request.getSession();
                    	session.setAttribute("customer", cust);
                        session.setMaxInactiveInterval(30*60);
                    }
                    // Create a customer object with the provided email and password
                    customer c = new customer();
                    c.setEmail_Id(email);
                    if (dao.checkcust(c)) {
                        // If the customer exists, create a cookie with the customer's email
                        Cookie cus = new Cookie("cname", email);
                        cus.setMaxAge(1800);
                        response.addCookie(cus);

                        // Redirect to appropriate page based on whether CusName is "empty" or not
                        if (cusName.equals("empty")) {
                            response.sendRedirect("ShippingAddress.jsp?Total=" + total + "&CusName=" + cusName);
                        } else {
                            response.sendRedirect("customerhome.jsp");
                        }
                    } else {
                        // If the customer does not exist, create a cookie to indicate login failure
                        Cookie up = new Cookie("un", "up");
                        up.setMaxAge(10);
                        response.addCookie(up);
                        response.sendRedirect("customerlogin.jsp?Total=" + total + "&CusName=" + cusName);
                    }
                } else {
                    // Password doesn't match
                    response.sendRedirect("customerlogin.jsp?Total=" + total + "&CusName=" + cusName);
                }
            } else {
                // Customer not found
                response.sendRedirect("customerlogin.jsp?Total=" + total + "&CusName=" + cusName);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

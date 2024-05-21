package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.conn.DBConnect;
import com.dao.DAO2;
import com.entity.PasswordEnDe;
import com.entity.customer;

/**
 * Servlet implementation class updateUser
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/updateUser" })
public class updateUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public updateUser() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String custIdStr = request.getParameter("custId");
		System.out.println("custIdStr: " + custIdStr);	
		int custId = Integer.parseInt(request.getParameter("custId"));
	    String name = request.getParameter("Name");
	    String password = request.getParameter("password");
	    String email = request.getParameter("Email");
	    String contactNo = request.getParameter("Phone");
	    String address = request.getParameter("Address");
	    String encryptPassword=null;
		try {
			encryptPassword = PasswordEnDe.encrypt(password);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Error in encryption");
			e.printStackTrace();
		}
	    
	    customer cust = new customer();
	    cust.setCust_Id(custId);
	    cust.setName(name);
	    cust.setPassword(encryptPassword);
	    cust.setEmail_Id(email);
	    cust.setContact_No(contactNo);
	    cust.setAddress(address);

	    DAO2 dao = new DAO2(DBConnect.getConn());
	    dao.updateCustomer(cust);

	    HttpSession session = request.getSession();
	    session.setAttribute("updateMessage", "Your profile has been updated successfully.");

	    response.sendRedirect("profile.jsp");
	}

}

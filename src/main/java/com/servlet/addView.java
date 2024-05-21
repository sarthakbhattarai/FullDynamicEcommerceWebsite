package com.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conn.DBConnect;
import com.dao.DAO;
import com.dao.DAO2;
import com.dao.DAO3;
import com.entity.Product;
import com.entity.customer;
import com.entity.viewlist;


/**
 * Servlet implementation class addView
 */
@WebServlet(asyncSupported = true, urlPatterns = { "/addView" })
public class addView extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addView() {
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
	    // Initialize DAO objects
	    DAO dao = new DAO(DBConnect.getConn());
	    DAO3 dao3 = new DAO3(DBConnect.getConn());
	    
	    // Fetch the list of products from the product table
	    List<Product> listv = dao3.getAllProduct(); // Assuming you have a method like getAllProduct() in your DAO class
	    
	    boolean success = true; // Flag to track if all additions were successful
	    
	    // Iterate over the list of products
	    for (Product p : listv) {
	        // Extract product details
	        String brandName = ""; // Initialize brandName
	        String categoryName = ""; // Initialize categoryName
	        
	        // Set brandName based on p.getBid()
	        switch (p.getBid()) {
	            case 1:
	                brandName = "Samsung";
	                break;
	            case 2:
	                brandName = "Sony";
	                break;
	            case 3:
	                brandName = "Lenovo";
	                break;
	            case 4:
	                brandName = "Acer";
	                break;
	            case 5:
	                brandName = "Apple";
	                break;
	            default:
	                // Handle default case
	                break;
	        }
	        
	        // Set categoryName based on p.getCid()
	        switch (p.getCid()) {
	            case 1:
	                categoryName = "Laptop";
	                break;
	            case 2:
	                categoryName = "TV";
	                break;
	            case 3:
	                categoryName = "Mobile";
	                break;
	            case 4:
	                categoryName = "Watch";
	                break;
	            default:
	                // Handle default case
	                break;
	        }
	        
	        // Construct viewlist object
	        viewlist v = new viewlist(brandName, categoryName, p.getPname(), p.getPprice(), p.getPquantity(), p.getPimage(),p.getProdDesc());
	        
	        // Add the product to the view list in the database
	        int result = dao.addViewListProduct(v);
	        
	        // If any addition fails, set success flag to false
	        if (result <= 0) {
	            success = false;
	            break; // Exit the loop early
	        }
	    }
	    
	    // Redirect based on the success flag
	    if (success) {
	        response.sendRedirect("passc.jsp");
	    } else {
	        response.sendRedirect("failp.jsp");
	    }
	}



}

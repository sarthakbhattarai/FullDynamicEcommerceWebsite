package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.conn.DBConnect;
import com.dao.DAO2;
import com.dao.DAO5;
import com.entity.Product;

/**
 * Servlet implementation class removeProduct
 */
@WebServlet("/removeProduct")
public class removeProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public removeProduct() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
		int id = Integer.parseInt(request.getParameter("id"));
		
		Product p = new Product();
		p.setPid(id);
		
		try{
			
			DAO5 dao = new DAO5(DBConnect.getConn());
			
			
			if(dao.removeProduct(p) > 0)
			{
					response.sendRedirect("table_product.jsp");
		
			}
			else
			{
				
					response.sendRedirect("table_product.jsp");
			}
			
			
			
			}catch(Exception ex){
			   System.out.println(ex.getMessage());
			}
			
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

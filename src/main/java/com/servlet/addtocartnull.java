package com.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.conn.DBConnect;
import com.dao.DAO2;
import com.entity.cart;
import com.entity.viewlist;

import java.io.IOException;

@WebServlet("/addtocartnull")
public class addtocartnull extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String imageName = request.getParameter("Pn");
        String custName = request.getParameter("custName");
        DAO2 dao = new DAO2(DBConnect.getConn());
        viewlist product = dao.getProductByImageName(imageName);
        
        if (product != null) {
            cart cartItem = new cart();
            cartItem.setName(custName);
            cartItem.setPname(product.getPname());
            cartItem.setBname(product.getBname());
            cartItem.setCname(product.getCname());
            cartItem.setPprice(product.getPprice());
            cartItem.setPquantity(1); // Assuming default quantity is 1
            cartItem.setPimage(product.getPimage());

            try {
                if (dao.checkAddToCartNull(cartItem)) {
                    if (dao.updateAddToCartNull(cartItem) > 0) {
                        response.sendRedirect("cartnull.jsp");
                    } else {
                        response.sendRedirect("selecteditem.jsp?Pn=" + imageName);
                    }
                } else {
                    if (dao.addToCartNull(cartItem) > 0) {
                        response.sendRedirect("cartnull.jsp");
                    } else {
                        response.sendRedirect("selecteditem.jsp?Pn=" + imageName);
                    }
                }
            } catch (Exception ex) {
                System.out.println("Error in addtocartnull servlet: " + ex.getMessage());
                response.sendRedirect("selecteditem.jsp?Pn=" + imageName);
            }
        } else {
            response.sendRedirect("selecteditem.jsp?Pn=" + imageName);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

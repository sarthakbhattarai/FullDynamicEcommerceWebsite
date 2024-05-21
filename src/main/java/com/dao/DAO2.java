package com.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes.Name;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.conn.DBConnect;
import com.entity.PasswordEnDe;
import com.entity.Product;
import com.entity.cart;
import com.entity.customer;
import com.entity.order_details;
import com.entity.orders;
import com.entity.usermaster;
import com.entity.viewlist;
import com.utility.MyUtilities;




public class DAO2 {
	private Connection conn;
	
	public DAO2(Connection conn) {
		this.conn = conn;
	}
	
	public List<viewlist> searchProducts(String query) {
        List<viewlist> relatedProducts = new ArrayList<>();
        try {
//        	System.out.println(query);
        	String sql="SELECT * FROM viewlist WHERE Pname LIKE ? ";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, "%" + query + "%");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
//            	System.out.println(query);
                // Retrieve other fields from the ResultSet
                String bname = rs.getString("Bname");
                String cname = rs.getString("Cname");
                String pname = rs.getString("Pname");
                int pprice = rs.getInt("Price");
                int pquantity = rs.getInt("Quantity");
                String desc = rs.getString("Deacription");
                // Retrieve image URL from database or set it as needed
                String pimage = rs.getString("Image"); // Replace with actual image URL
                // Create a new viewlist object with retrieved values
                viewlist product = new viewlist(bname, cname, pname, pprice, pquantity, pimage, desc);
                // Add product to the list of related products
                relatedProducts.add(product);
//                System.out.println(cname);
            }
        } catch (Exception e) {
        	System.out.println("error in search"+e.getMessage());
            e.printStackTrace();
        }
        return relatedProducts;
    }


	 public void updateCustomer(customer cust) {
	        Connection conn = null;
	        PreparedStatement pstmt = null;

	        try {
	            conn = DBConnect.getConn();
	            String query = "UPDATE customer SET Name=?, Email_Id=?, Contact_No=?, Password=?, Address=? WHERE custId=?";
	            pstmt = conn.prepareStatement(query);
	            pstmt.setString(1, cust.getName());
	            pstmt.setString(2, cust.getEmail_Id());
	            pstmt.setString(3, cust.getContact_No());
	            pstmt.setString(4, cust.getPassword());
	            pstmt.setString(5, cust.getAddress());
	            pstmt.setInt(6, cust.getCust_Id());
	            pstmt.executeUpdate();
	        } catch (SQLException e) {
	            e.printStackTrace();
	        } finally {
	            // Close resources
	            try {
	                if (pstmt != null) pstmt.close();
	                if (conn != null) conn.close();
	            } catch (SQLException e) {
	            	System.out.println("Error in updateCustomer "+e.getMessage());
	                e.printStackTrace();
	            }
	        }
	    }
	public viewlist getProductByImageName(String imageName) {
//		List<viewlist> listv = new ArrayList<viewlist>();
        viewlist product = null;
        String sql = "SELECT * FROM viewlist WHERE Image = ?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, imageName);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                product = new viewlist();
                product.setPname(rs.getString("Pname"));
                product.setPprice(rs.getInt("Price"));
                product.setBname(rs.getString("Bname"));
                product.setCname(rs.getString("Cname"));
                product.setPquantity(rs.getInt("Quantity"));
                product.setPimage(rs.getString("Image"));
                product.setDesc(rs.getString("Deacription"));
                
             
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
	


	
	// viewproduct
	
	public List<viewlist> getAllviewlist(){
		List<viewlist> listv = new ArrayList<viewlist>();
		
		viewlist product = null;
		
		try {
			String sql = "select * from viewlist";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				 	product = new viewlist();
				 	product.setPname(rs.getString("Pname"));
	                product.setPprice(rs.getInt("Price"));
	                product.setBname(rs.getString("Bname"));
	                product.setCname(rs.getString("Cname"));
	                product.setPquantity(rs.getInt("Quantity"));
	                product.setPimage(rs.getString("Image"));
	                product.setDesc(rs.getString("Deacription"));
	                listv.add(product);
				
			}
			
			
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return listv;
		}
	
	
	public customer giveCust(String email) {
	    try (Connection con = DBConnect.getConn()) {
	        String sql = "SELECT * FROM customer WHERE Email_Id=?";
	        PreparedStatement st = con.prepareStatement(sql);
	        st.setString(1, email);
	        ResultSet rs = st.executeQuery();
	        if (rs.next()) {
	            int id = rs.getInt("custId");
	            String name = rs.getString("Name");
	            String email1 = rs.getString("Email_Id");
	            String contact = rs.getString("Contact_No");
	            String encryptedPass = rs.getString("Password"); // Fix typo here
	            String address = rs.getString("Address");

	            // Decrypt the password retrieved from the database
	            String decryptedPass = PasswordEnDe.decrypt(encryptedPass);

	            // Create and return a customer object with decrypted password
	            return new customer(id, name, decryptedPass, email1, contact, address);
	        } else {
	            return null;
	        }
	    } catch (Exception e) {
	        System.out.println("Error in giveCust " + e.getMessage());
	        return null;
	    }
	}

	
	// check customer login
	
	public boolean checkcust(customer cust)
	{
		boolean f = false;
	
	
		try{
			String sql = "select * from customer  where  Email_Id=?";
			PreparedStatement ps = conn.prepareStatement(sql);
		
		
		ps.setString(1, cust.getEmail_Id());
		
		ResultSet rs=ps.executeQuery();
		if (rs.next()==true)
			f = true;
		else
			f = false;
		
		}catch(Exception ex){
		   System.out.println(ex.getMessage());
		}

	return f;
			
	}
	
	// check admin login
	
		public boolean checkadmin(usermaster admin)
		{
			boolean f = false;
		
		
			try{
				String sql = "select * from usermaster  where Name=? and Password=?";
				PreparedStatement ps = conn.prepareStatement(sql);
			
			
			ps.setString(1, admin.getName());
			ps.setString(2, admin.getPassword());
			
			ResultSet rs=ps.executeQuery();
			if (rs.next()==true)
				f = true;
			else
				f = false;
			
			}catch(Exception ex){
			   System.out.println(ex.getMessage());
			}

		return f;
				
		}
		
		// customer registration
		
		public int addcustomer(customer ct) {
		    int rowsAffected = 0;
		    try {
		        String sql = "INSERT INTO customer(Name, Password, Email_Id, Contact_No, Address) VALUES (?, ?, ?, ?, ?)";
		        PreparedStatement ps = conn.prepareStatement(sql);
		        ps.setString(1, ct.getName());
		        ps.setString(2, ct.getPassword());
		        ps.setString(3, ct.getEmail_Id());
		        ps.setString(4, ct.getContact_No());
		        ps.setString(5, ct.getAddress());  // Corrected the parameter index here

		        rowsAffected = ps.executeUpdate();
		    } catch (SQLException e) {
		        // Handle SQL exception properly, log or display meaningful error message
		        e.printStackTrace();
		    }
		    return rowsAffected;
		}		
//===================================================================================================================
		//view selected item
		
		// viewproduct
		
		public List<viewlist> getSelecteditem(String st){
			List<viewlist> listv = new ArrayList<viewlist>();
			
			viewlist v = null;
			
			try {
				String sql = "select * from viewlist where Image = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ps.setString(1, st);
				
				ResultSet rs = ps.executeQuery();
				
				while(rs.next())
				{
					v = new viewlist();
					v.setBname(rs.getString(1));
					v.setCname(rs.getString(2));
					v.setPname(rs.getString(3));
					v.setPprice(rs.getInt(4));
					v.setPquantity(rs.getInt(5));
					v.setPimage(rs.getString(6));
					listv.add(v);
					
				}
				
				
					
				}catch (Exception e) {
					e.printStackTrace();
				}
				
				return listv;
			}
			
		
		
// addtocartnull
		
		 public boolean checkAddToCartNull(cart c) {
		        boolean exists = false;
		        try {
		            String sql = "SELECT * FROM cart WHERE pimage = ?";
		            PreparedStatement ps = conn.prepareStatement(sql);
		            ps.setString(1, c.getPimage());
		            ResultSet rs = ps.executeQuery();
		            if (rs.next()) {
		                exists = true;
		            }
		        } catch (Exception ex) {
		            System.out.println("Error in checkAddToCartNull: " + ex.getMessage());
		        }
		        return exists;
		    }

		    public int updateAddToCartNull(cart c) {
		        int rowsAffected = 0;
		        try {
		            String sql = "UPDATE cart SET pquantity = pquantity + 1 WHERE pimage = ?";
		            PreparedStatement ps = conn.prepareStatement(sql);
		            ps.setString(1, c.getPimage());
		            rowsAffected = ps.executeUpdate();
		        } catch (Exception ex) {
		            System.out.println("Error in updateAddToCartNull: " + ex.getMessage());
		        }
		        return rowsAffected;
		    }

		    public int addToCartNull(cart c) {
		        int rowsAffected = 0;
		        try {
		            String sql = "INSERT INTO cart (Name, bname, cname, pname, pprice, pquantity, pimage) VALUES (?, ?, ?, ?, ?, ?, ?)";
		            PreparedStatement ps = conn.prepareStatement(sql);
		            ps.setString(1, c.getName());
		            ps.setString(2, c.getBname());
		            ps.setString(3, c.getCname());
		            ps.setString(4, c.getPname());
		            ps.setInt(5, c.getPprice());
		            ps.setInt(6, c.getPquantity());
		            ps.setString(7, c.getPimage());
		            rowsAffected = ps.executeUpdate();
		        } catch (Exception ex) {
		            System.out.println("Error in addToCartNull: " + ex.getMessage());
		        }
		        return rowsAffected;
		    }

	
//===================================================================

// view cart


// 

public List<cart> getSelectedcart(){
	List<cart> listv = new ArrayList<cart>();
	
	cart c = null;
	
	try {
		String sql = "select * from cart where Name is NULL";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			c = new cart();
			c.setName(rs.getString(2));
			c.setBname(rs.getString(3));
			c.setCname(rs.getString(4));
			c.setPname(rs.getString(5));
			c.setPprice(rs.getInt(6));
			c.setPquantity(rs.getInt(7));
			c.setPimage(rs.getString(8));
			listv.add(c);
			
		}
		
		
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Message in getSelectedcart "+e.getMessage());
		}
		
		return listv;
	}
	
//
// view cart of specific customer

public List<cart> getcart(String ct){
	List<cart> listv = new ArrayList<cart>();
	
	cart c = null;
	
	try {
		String sql = "select * from cart where Name = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, ct);
		ResultSet rs = ps.executeQuery();
		
		while(rs.next())
		{
			c = new cart();
			c.setName(rs.getString(2));
			c.setBname(rs.getString(3));
			c.setCname(rs.getString(4));
			c.setPname(rs.getString(5));
			c.setPprice(rs.getInt(6));
			c.setPquantity(rs.getInt(7));
			c.setPimage(rs.getString(8));
			listv.add(c);
			
		}
		
		
			
		}catch (Exception e) {
			e.printStackTrace();
			System.out.println("Error Message in getcart "+e.getMessage());
		}
		
		return listv;
	}

// removecartnull

public int removecartnull(cart c) {
	
	int i = 0;
	try{
		String sql = "delete from cart where Name is NULL and pimage = ?";
		PreparedStatement ps = conn.prepareStatement(sql);
	
		ps.setString(1, c.getPimage());;

	
	i = ps.executeUpdate();
	if(i > 0)
		i = 1;
	
	
	}catch(Exception ex){
	   System.out.println(ex.getMessage());
	}

return i;
	

	
}


// removecart

	public int removecart(cart c) {
	
	int j = 0;
	try{
		String sql = "delete from cart where Name= ? and pimage= ?";
		PreparedStatement ps = conn.prepareStatement(sql);
	
		ps.setString(1, c.getName());
		ps.setString(2, c.getPimage());

	
	j = ps.executeUpdate();
	if(j > 0)
		j = 1;
	
	
	}catch(Exception ex){
	   System.out.println(ex.getMessage());
	}

	return j;
	
	}
	
	public int removePrdocuct(cart c) {
		
		int j = 0;
		try{
			String sql = "delete from cart where Name= ? and pimage= ?";
			PreparedStatement ps = conn.prepareStatement(sql);
		
			ps.setString(1, c.getName());
			ps.setString(2, c.getPimage());

		
		j = ps.executeUpdate();
		if(j > 0)
			j = 1;
		
		
		}catch(Exception ex){
		   System.out.println(ex.getMessage());
		}

		return j;
		
		}
	
	
	
	// check existing customer login name for new registration
	
	// check customer login
	
		public boolean checkcust2(customer cus)
		{
			boolean f = false;
		
		
			try{
				String sql = "select * from customer  where Name=? or Email_Id=?";
				PreparedStatement ps = conn.prepareStatement(sql);
			
			
			ps.setString(1, cus.getName());
			ps.setString(2, cus.getEmail_Id());
			
			ResultSet rs=ps.executeQuery();
			if (rs.next()==true)
				f = true;
			else
				f = false;
			
			}catch(Exception ex){
			   System.out.println(ex.getMessage());
			}

		return f;
				
		}
		
		
// remove orders
		
public int removeorders(orders o) {
			
			int j = 0;
			try{
				String sql = "delete from orders where Order_Id= ?";
				PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setInt(1, o.getOrder_Id());
			

			
			j = ps.executeUpdate();
			if(j > 0)
				j = 1;
			
			
			}catch(Exception ex){
			   System.out.println(ex.getMessage());
			}

			return j;
				
			
			
			}
		
}



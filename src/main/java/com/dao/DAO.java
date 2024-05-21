package com.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;


import com.entity.category;
import com.entity.customer;
import com.entity.viewlist;
import com.utility.MyUtilities;


import com.entity.Product;
import com.entity.customer;
import com.entity.brand;

public class DAO {
	private Connection conn;
	
	public DAO(Connection conn) {
		this.conn = conn;
	}
	
	
	// list all brand
	
	public List<brand> getAllbrand(){
		List<brand> listb = new ArrayList<brand>();
		
		brand b = null;
		
		try {
			String sql = "select * from brand";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				b = new brand();
				b.setBid(rs.getInt(1));
				b.setBname(rs.getString(2));
				listb.add(b);
				
			}
			
			
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return listb;
		}
	
	
	// list all category
	
	public List<category> getAllcategory(){
		List<category> listc = new ArrayList<category>();
		
		category c = null;
		
		try {
			String sql = "select * from category";
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ResultSet rs = ps.executeQuery();
			
			while(rs.next())
			{
				c = new category();
				c.setCid(rs.getInt(1));
				c.setCname(rs.getString(2));
				listc.add(c);
				
			}
			
			
				
			}catch (Exception e) {
				e.printStackTrace();
			}
			
			return listc;
		}
	
	
	//
//	public List<viewlist> getAllviewlist(){
//		List<viewlist> listv = new ArrayList<viewlist>();
//		
//		viewlist v = null;
//		
//		try {
//			String sql = "select * from viewlist";
//			PreparedStatement ps = conn.prepareStatement(sql);
//			
//			ResultSet rs = ps.executeQuery();
//			
//			while(rs.next())
//			{
//				v = new viewlist();
//				v.setBname(rs.getString(1));
//				v.setCname(rs.getString(2));
//				v.setPname(rs.getString(3));
//				v.setPprice(rs.getInt(4));
//				v.setPquantity(rs.getInt(5));
//				v.setPimage(rs.getString(6));
//				listv.add(v);
//				
//			}
//			
//			
//				
//			}catch (Exception e) {
//				e.printStackTrace();
//			}
//			
//			return listv;
//		}
	
	
	
	public int addViewListProduct(viewlist v) {
		try  {
			String sql = "insert into viewlist(Bname,Cname,Pname,Price,Quantity,Image, Deacription) values(?,?,?,?,?,?,?)";
            PreparedStatement st = conn.prepareStatement(sql);
            st.setString(1, v.getBname());
            st.setString(2, v.getCname());
            st.setString(3,v.getPname());
            st.setInt(4, v.getPprice());
            st.setInt(5, v.getPquantity());
            st.setString(6, v.getPimage());
            st.setString(7, v.getDesc());

            int result = st.executeUpdate();
            return result > 0 ? 1 : 0;
        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error message in add viewlist: " + ex.getMessage());
            return -1;
        }
			
		}
		

	public int  addproduct(HttpServletRequest request) {
		
		String path = "D:\\User\\OneDrive\\Desktop\\EcommerceApp-master\\EcommerceApp-master\\EcommerceApp\\src\\main\\webapp\\";
		
		
		
			int a =  0;
			try {
				
				String pname = "";
				int pprice = 0;
				int pquantity = 0;
				String pimage = "";
				String prodDesc = "";
				int bid = 0;
				int cid = 0;
				
				String sql = "insert into product(pname,pprice,pquantity,pimage,bid,cid,Description) values(?,?,?,?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
				

				List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
				
				for (FileItem item1 : multiparts) {
					if (item1.isFormField()) {
						if (item1.getFieldName().equals("pname"))
							pname = item1.getString();

						if (item1.getFieldName().equals("pprice"))
							pprice = Integer.parseInt(item1.getString());

						if (item1.getFieldName().equals("pquantity"))
							pquantity = Integer.parseInt(item1.getString());
						
						if (item1.getFieldName().equals("prodDesc"))
							prodDesc = item1.getString();
						if (item1.getFieldName().equals("bname"))
							{
							if(item1.getString().equals("Samsung"))
								bid = 1;
							if(item1.getString().equals("Sony"))
								bid = 2;
							if(item1.getString().equals("Lenovo"))
								bid = 3;
							if(item1.getString().equals("Acer"))
								bid = 4;
							if(item1.getString().equals("Apple"))
								bid = 5;
							}
						if (item1.getFieldName().equals("cname"))
						{
							if(item1.getString().equals("Laptop"))
								cid = 1;
							if(item1.getString().equals("Tv"))
								cid = 2;
							if(item1.getString().equals("Mobile"))
								cid = 3;
							if(item1.getString().equals("Watch"))
								cid = 4;
						}

					}
					
					else
						{
						MyUtilities m1=new MyUtilities();
						String destinationpath=path + "images/";
						ArrayList <String> ext=new ArrayList();
						ext.add(".jpg");ext.add(".bmp");ext.add(".jpeg");ext.add(".png");ext.add(".webp");
						
						pimage = m1.UploadFile(item1, destinationpath, ext);
						
						}
				}
				
				if(pimage.equals("Problem with upload") == false)
				{
					
					
					ps.setString(1, pname);
					ps.setInt(2,pprice);
					ps.setInt(3,pquantity);
					ps.setString(4,pimage);
					ps.setInt(5,bid);
					ps.setInt(6,cid);
					ps.setString(7,prodDesc);
					ps.executeUpdate();
					a = 1;
				}
				
				System.out.println("pname: " + pname);
				System.out.println("pprice: " + pprice);
				System.out.println("pquantity: " + pquantity);
				System.out.println("pimage: " + pimage);
				System.out.println("bid: " + bid);
				System.out.println("cid: " + cid);

				
				conn.close();
				
			}catch (Exception e) {
				System.out.println(e);
			}
		return a;
			
		}
	
//	public int addProduct(HttpServletRequest request) {
//        String path = "C:/Users/Swapnil/eclipse-workspace/imagetable2/src/main/webapp/";
//        int a = 0;
//        try {
//            String pname = "";
//            int pprice = 0;
//            int pquantity = 0;
//            String pimage = "";
//            int bid = 0;
//            int cid = 0;
//
//            String sql = "INSERT INTO product (pname, pprice, pquantity, pimage, bid, cid) VALUES (?, ?, ?, ?, ?, ?)";
//            PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
//
//            List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);
//
//            for (FileItem item1 : multiparts) {
//                if (item1.isFormField()) {
//                    if (item1.getFieldName().equals("pname"))
//                        pname = item1.getString();
//
//                    if (item1.getFieldName().equals("pprice"))
//                        pprice = Integer.parseInt(item1.getString());
//
//                    if (item1.getFieldName().equals("pquantity"))
//                        pquantity = Integer.parseInt(item1.getString());
//
//                    if (item1.getFieldName().equals("bname")) {
//                        if (item1.getString().equals("Samsung"))
//                            bid = 1;
//                        if (item1.getString().equals("Sony"))
//                            bid = 2;
//                        if (item1.getString().equals("Lenovo"))
//                            bid = 3;
//                        if (item1.getString().equals("Acer"))
//                            bid = 4;
//                        if (item1.getString().equals("Apple"))
//                            bid = 5;
//                    }
//                    if (item1.getFieldName().equals("cname")) {
//                        if (item1.getString().equals("Laptop"))
//                            cid = 1;
//                        if (item1.getString().equals("Tv"))
//                            cid = 2;
//                        if (item1.getString().equals("Mobile"))
//                            cid = 3;
//                        if (item1.getString().equals("Watch"))
//                            cid = 4;
//                    }
//
//                } else {
//                    MyUtilities m1 = new MyUtilities();
//                    String destinationpath = path + "images/";
//                    ArrayList<String> ext = new ArrayList();
//                    ext.add(".jpg");
//                    ext.add(".bmp");
//                    ext.add(".jpeg");
//                    ext.add(".png");
//                    ext.add(".webp");
//
//                    pimage = m1.UploadFile(item1, destinationpath, ext);
//
//                }
//            }
//
//            if (!pimage.equals("Problem with upload")) {
//
//                ps.setString(1, pname);
//                ps.setInt(2, pprice);
//                ps.setInt(3, pquantity);
//                ps.setString(4, pimage);
//                ps.setInt(5, bid);
//                ps.setInt(6, cid);
//                int rowsAffected = ps.executeUpdate();
//                if (rowsAffected > 0) {
//                    ResultSet generatedKeys = ps.getGeneratedKeys();
//                    if (generatedKeys.next()) {
//                        int generatedId = generatedKeys.getInt(1);
//                        System.out.println("Auto-generated ID: " + generatedId);
//                    }
//                    a = 1;
//                }
//            }
//
//            System.out.println("pname: " + pname);
//            System.out.println("pprice: " + pprice);
//            System.out.println("pquantity: " + pquantity);
//            System.out.println("pimage: " + pimage);
//            System.out.println("bid: " + bid);
//            System.out.println("cid: " + cid);
//
//            conn.close();
//
//        } catch (Exception e) {
//            System.out.println(e);
//        }
//        return a;
//    }


//display all customers

public List<customer> getAllCustomer()
{
	List<customer> list = new ArrayList <customer>();
	
	customer c = null;
	
	try {
		String sql = "select * from customer";
		PreparedStatement ps = conn.prepareStatement(sql);
		
	ResultSet rs = ps.executeQuery();
	
	while(rs.next())
	{
		c = new customer();
		c.setName(rs.getString(2));
		c.setPassword(rs.getString(3));
		c.setEmail_Id(rs.getString(4));
		c.setContact_No(rs.getString(5));
		c.setAddress(rs.getString(6));
		
		list.add(c);
		
	}
	
	
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	return list;
}


//Delete Customer

	public boolean deleteCustomer(customer c)
	{
		boolean f = false;
		
		try {
			
			String sql = "delete from customer where Name = ? and Email_Id = ?";
			
			PreparedStatement ps = conn.prepareStatement(sql);
			
			ps.setString(1, c.getName());
			ps.setString(2, c.getEmail_Id());
			
			
			int i = ps.executeUpdate();
			
			
			if(i == 1)
			{
				f = true;
			}
			
			
		}catch (Exception e) {
			e.printStackTrace();
		}
		
		return f;
	}

	
	// display selected customer

public List<customer> getCustomer(String eid)
{
	List<customer> list = new ArrayList <customer>();
	
	customer c = null;
	
	try {
		String sql = "select * from customer where Email_Id=?";
		PreparedStatement ps = conn.prepareStatement(sql);
		
		ps.setString(1, eid);
	ResultSet rs = ps.executeQuery();
	
	while(rs.next())
	{
		c = new customer();
		c.setName(rs.getString(2));
		c.setPassword(rs.getString(3));
		c.setEmail_Id(rs.getString(4));
		c.setContact_No(rs.getString(5));
		c.setAddress(rs.getString(6));
		list.add(c);
		
	}
	
	
		
	}catch (Exception e) {
		e.printStackTrace();
	}
	
	return list;
}
	
}

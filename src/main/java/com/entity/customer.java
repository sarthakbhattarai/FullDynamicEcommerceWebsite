package com.entity;

public class customer {
	private int Cust_Id;
	private String Name;
	private String Password;
	private String Email_Id;
	private String Contact_No;
	private String Address;
	
	
	
	public customer(int custId, String name, String password, String email_Id, String contact_No, String address) {
		super();
		Cust_Id = custId;
		Name = name;
		Password = password;
		Email_Id = email_Id;
		Contact_No = contact_No;
		Address = address;
	}
	public customer() {
		
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getPassword() {
		return Password;
	}
	public void setPassword(String password) {
		Password = password;
	}
	public String getEmail_Id() {
		return Email_Id;
	}
	public void setEmail_Id(String email_Id) {
		Email_Id = email_Id;
	}
	public String getContact_No() {
		return Contact_No;
	}
	public void setContact_No(String contact_No) {
		Contact_No = contact_No;
	}
	
	
	public String getAddress() {
		return Address;
	}
	public void setAddress(String address) {
		Address = address;
	}
	public int getCust_Id() {
		return Cust_Id;
	}
	public void setCust_Id(int cust_Id) {
		Cust_Id = cust_Id;
	}
	
}

package com.entity;

public class viewlist {

	private String bname;
	private String cname;
	private String pname;
	private int pprice;
	private int pquantity;
	private String pimage;
	private String desc;
	
	public viewlist(String bname, String cname, String pname, int pprice, int pquantity, String pimage,String desc) {
		super();
		this.bname = bname;
		this.cname = cname;
		this.pname = pname;
		this.pprice = pprice;
		this.pquantity = pquantity;
		this.pimage = pimage;
		this.desc = desc;
	}
	public viewlist() {
		// TODO Auto-generated constructor stub
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
	 
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getCname() {
		return cname;
	}
	public void setCname(String cname) {
		this.cname = cname;
	}
	public String getPname() {
		return pname;
	}
	public void setPname(String pname) {
		this.pname = pname;
	}
	public int getPprice() {
		return pprice;
	}
	public void setPprice(int pprice) {
		this.pprice = pprice;
	}
	public int getPquantity() {
		return pquantity;
	}
	public void setPquantity(int pquantity) {
		this.pquantity = pquantity;
	}
	public String getPimage() {
		return pimage;
	}
	public void setPimage(String pimage) {
		this.pimage = pimage;
	}
	public String getImage() {
        return pimage;
    }
	
	
}

package com.softeem.model;

public class Order {
	private String id;
	private String ordernumber;
	private String gidlist;
	private int goodstotallist;//¹ºÂòÊıÁ¿
	private String name;
	private String uadname;
	private double account;
	private String uadid;
	private String mid;
	private int paydr;
	private int orderdr;
	private String reserved1;
	private String reserved2;
	private String reserved3;
	private String reserved4;
	private String reserved5;
	public Order(String id, String ordernumber, String gidlist, int goodstotallist, String name, String uadname,
			double account, String uadid, String mid, int paydr, int orderdr, String reserved1, String reserved2,
			String reserved3, String reserved4, String reserved5) {
		super();
		this.id = id;
		this.ordernumber = ordernumber;
		this.gidlist = gidlist;
		this.goodstotallist = goodstotallist;
		this.name = name;
		this.uadname = uadname;
		this.account = account;
		this.uadid = uadid;
		this.mid = mid;
		this.paydr = paydr;
		this.orderdr = orderdr;
		this.reserved1 = reserved1;
		this.reserved2 = reserved2;
		this.reserved3 = reserved3;
		this.reserved4 = reserved4;
		this.reserved5 = reserved5;
	}
	public Order() {
		super();
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	public String getGidlist() {
		return gidlist;
	}
	public void setGidlist(String gidlist) {
		this.gidlist = gidlist;
	}
	public int getGoodstotallist() {
		return goodstotallist;
	}
	public void setGoodstotallist(int goodstotallist) {
		this.goodstotallist = goodstotallist;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getUadname() {
		return uadname;
	}
	public void setUadname(String uadname) {
		this.uadname = uadname;
	}
	public double getAccount() {
		return account;
	}
	public void setAccount(double account) {
		this.account = account;
	}
	public String getUadid() {
		return uadid;
	}
	public void setUadid(String uadid) {
		this.uadid = uadid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public int getPaydr() {
		return paydr;
	}
	public void setPaydr(int paydr) {
		this.paydr = paydr;
	}
	public int getOrderdr() {
		return orderdr;
	}
	public void setOrderdr(int orderdr) {
		this.orderdr = orderdr;
	}
	public String getReserved1() {
		return reserved1;
	}
	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}
	public String getReserved2() {
		return reserved2;
	}
	public void setReserved2(String reserved2) {
		this.reserved2 = reserved2;
	}
	public String getReserved3() {
		return reserved3;
	}
	public void setReserved3(String reserved3) {
		this.reserved3 = reserved3;
	}
	public String getReserved4() {
		return reserved4;
	}
	public void setReserved4(String reserved4) {
		this.reserved4 = reserved4;
	}
	public String getReserved5() {
		return reserved5;
	}
	public void setReserved5(String reserved5) {
		this.reserved5 = reserved5;
	}
	@Override
	public String toString() {
		return "Order [id=" + id + ", ordernumber=" + ordernumber + ", gidlist=" + gidlist + ", goodstotallist="
				+ goodstotallist + ", name=" + name + ", uadname=" + uadname + ", account=" + account + ", uadid="
				+ uadid + ", mid=" + mid + ", paydr=" + paydr + ", orderdr=" + orderdr + ", reserved1=" + reserved1
				+ ", reserved2=" + reserved2 + ", reserved3=" + reserved3 + ", reserved4=" + reserved4 + ", reserved5="
				+ reserved5 + "]";
	}
	
	
	
}

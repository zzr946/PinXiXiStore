package com.softeem.model;

public class Cart {
	private String id;
	private String uid;
	private String gidlist;
	private int goodstotallist;//商品数量
	private double goodsprice;//单价
	private int reserved1;//状态
	private String reserved2;
	private String reserved3;
	private String reserved4;
	private String reserved5;
	public Cart() {
		super();
	}
	public Cart(String id, String uid, String gidlist, int goodstotallist, double goodsprice, int reserved1,
			String reserved2, String reserved3, String reserved4, String reserved5) {
		super();
		this.id = id;
		this.uid = uid;
		this.gidlist = gidlist;
		this.goodstotallist = goodstotallist;
		this.goodsprice = goodsprice;
		this.reserved1 = reserved1;
		this.reserved2 = reserved2;
		this.reserved3 = reserved3;
		this.reserved4 = reserved4;
		this.reserved5 = reserved5;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
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
	public double getGoodsprice() {
		return goodsprice;
	}
	public void setGoodsprice(double goodsprice) {
		this.goodsprice = goodsprice;
	}
	public int getReserved1() {
		return reserved1;
	}
	public void setReserved1(int reserved1) {
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
	
	
	
}

package com.softeem.model;

public class PayItem {
	private String gid;//商品id
	private String goodsimage;//商品图片
	private String goodsname;//商品名
	private String reserved1;//规格
	private double goodsprice;//goodsprice
	private int total;//数量
	private double sumprice;//单个商品的总价
	public PayItem(String gid, String goodsimage, String goodsname, String reserved1, double goodsprice, int total,
			double sumprice) {
		super();
		this.gid = gid;
		this.goodsimage = goodsimage;
		this.goodsname = goodsname;
		this.reserved1 = reserved1;
		this.goodsprice = goodsprice;
		this.total = total;
		this.sumprice = sumprice;
	}
	public PayItem() {
		super();
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public String getGoodsimage() {
		return goodsimage;
	}
	public void setGoodsimage(String goodsimage) {
		this.goodsimage = goodsimage;
	}
	public String getGoodsname() {
		return goodsname;
	}
	public void setGoodsname(String goodsname) {
		this.goodsname = goodsname;
	}
	public String getReserved1() {
		return reserved1;
	}
	public void setReserved1(String reserved1) {
		this.reserved1 = reserved1;
	}
	public double getGoodsprice() {
		return goodsprice;
	}
	public void setGoodsprice(double goodsprice) {
		this.goodsprice = goodsprice;
	}
	public int getTotal() {
		return total;
	}
	public void setTotal(int total) {
		this.total = total;
	}
	public double getSumprice() {
		return sumprice;
	}
	public void setSumprice(double sumprice) {
		this.sumprice = sumprice;
	}
	
	
}

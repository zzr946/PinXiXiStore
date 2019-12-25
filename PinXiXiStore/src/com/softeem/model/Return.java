package com.softeem.model;

public class Return {
	private String id;
	private String ordernumber;
	private String returntype;
	private String returncause;
	private String  returndetails;
	private int returndr;
	private double reserved1;
	private String reserved2;
	private String reserved3;
	private String reserved4;
	private String reserved5;
	public Return() {
		super();
	}
	public Return(String id, String ordernumber, String returntype, String returncause, String details, int returndr,
			double reserved1, String reserved2, String reserved3, String reserved4, String reserved5) {
		super();
		this.id = id;
		this.ordernumber = ordernumber;
		this.returntype = returntype;
		this.returncause = returncause;
		this.returndetails = details;
		this.returndr = returndr;
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
	public String getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(String ordernumber) {
		this.ordernumber = ordernumber;
	}
	public String getReturntype() {
		return returntype;
	}
	public void setReturntype(String returntype) {
		this.returntype = returntype;
	}
	public String getReturncause() {
		return returncause;
	}
	public void setReturncause(String returncause) {
		this.returncause = returncause;
	}
	public String getReturndetails() {
		return returndetails;
	}
	public void setReturndetails(String details) {
		this.returndetails = details;
	}
	public int getReturndr() {
		return returndr;
	}
	public void setReturndr(int returndr) {
		this.returndr = returndr;
	}
	public double getReserved1() {
		return reserved1;
	}
	public void setReserved1(double reserved1) {
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

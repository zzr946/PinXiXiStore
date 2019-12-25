package com.softeem.model;

public class Alertstore {
	private String alertid;
	private String mid;
	private String newname;
	private String newstoreinfo;
	private String newaddress;
	private int checkdr;
	private String reserved1;
	private String reserved2;
	private String reserved3;
	private String reserved4;
	private String reserved5;
	public Alertstore() {
		super();
	}
	
	public Alertstore(String alertid, String mid, String newname, String newstoreinfo, String newaddress,
			int checkdr, String reserved1, String reserved2, String reserved3, String reserved4, String reserved5) {
		super();
		this.alertid = alertid;
		this.mid = mid;
		this.newname = newname;
		this.newstoreinfo = newstoreinfo;
		this.newaddress = newaddress;
		this.checkdr = checkdr;
		this.reserved1 = reserved1;
		this.reserved2 = reserved2;
		this.reserved3 = reserved3;
		this.reserved4 = reserved4;
		this.reserved5 = reserved5;
	}

	public String getAlertid() {
		return alertid;
	}
	public void setAlertid(String alertid) {
		this.alertid = alertid;
	}
	public String getMid() {
		return mid;
	}
	public void setMid(String mid) {
		this.mid = mid;
	}
	public String getNewname() {
		return newname;
	}
	public void setNewname(String newname) {
		this.newname = newname;
	}
	public String getNewstoreinfo() {
		return newstoreinfo;
	}
	public void setNewstoreinfo(String newstoreinfo) {
		this.newstoreinfo = newstoreinfo;
	}
	public String getNewaddress() {
		return newaddress;
	}
	public void setNewaddress(String newaddress) {
		this.newaddress = newaddress;
	}
	public int getCheckdr() {
		return checkdr;
	}
	public void setCheckdr(int checkdr) {
		this.checkdr = checkdr;
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
	
}

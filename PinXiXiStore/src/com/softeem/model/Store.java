package com.softeem.model;

public class Store {
	private String sid;
	private String storename;
	private String storelogo;
	private String storeinfo;
	private String storeaddress;
	private int storedr;
	private String aptitude;
	private String subtime;
	private String reserved1;
	private String reserved2;
	private String reserved3;
	private String reserved4;
	private String reserved5;
	public Store(String sid, String storename, String storelogo, String storeinfo, String storeaddress, int storedr,
			String aptitude, String subtime, String reserved1, String reserved2, String reserved3, String reserved4,
			String reserved5) {
		super();
		this.sid = sid;
		this.storename = storename;
		this.storelogo = storelogo;
		this.storeinfo = storeinfo;
		this.storeaddress = storeaddress;
		this.storedr = storedr;
		this.aptitude = aptitude;
		this.subtime = subtime;
		this.reserved1 = reserved1;
		this.reserved2 = reserved2;
		this.reserved3 = reserved3;
		this.reserved4 = reserved4;
		this.reserved5 = reserved5;
	}
	public Store() {
		super();
	}
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getStorename() {
		return storename;
	}
	public void setStorename(String storename) {
		this.storename = storename;
	}
	public String getStorelogo() {
		return storelogo;
	}
	public void setStorelogo(String storelogo) {
		this.storelogo = storelogo;
	}
	public String getStoreinfo() {
		return storeinfo;
	}
	public void setStoreinfo(String storeinfo) {
		this.storeinfo = storeinfo;
	}
	public String getStoreaddress() {
		return storeaddress;
	}
	public void setStoreaddress(String storeaddress) {
		this.storeaddress = storeaddress;
	}
	public int getStoredr() {
		return storedr;
	}
	public void setStoredr(int storedr) {
		this.storedr = storedr;
	}
	public String getAptitude() {
		return aptitude;
	}
	public void setAptitude(String aptitude) {
		this.aptitude = aptitude;
	}
	public String getSubtime() {
		return subtime;
	}
	public void setSubtime(String subtime) {
		this.subtime = subtime;
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

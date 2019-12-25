package com.softeem.model;

public class Uaddress {
	private String uadid;
	private String uid;
	private String province;
	private String city;
	private String area;
	private String datailaddress;
	private int addressdr;
	private String mobile;
	private String uadname;
	private String reserved1;
	private String reserved2;
	private String reserved3;
	private String reserved4;
	private String reserved5;
	public Uaddress(String uadid, String uid, String province, String city, String area, String datailaddress,
			int addressdr, String mobile, String uadname, String reserved1, String reserved2, String reserved3,
			String reserved4, String reserved5) {
		super();
		this.uadid = uadid;
		this.uid = uid;
		this.province = province;
		this.city = city;
		this.area = area;
		this.datailaddress = datailaddress;
		this.addressdr = addressdr;
		this.mobile = mobile;
		this.uadname = uadname;
		this.reserved1 = reserved1;
		this.reserved2 = reserved2;
		this.reserved3 = reserved3;
		this.reserved4 = reserved4;
		this.reserved5 = reserved5;
	}
	public Uaddress() {
		super();
	}
	public String getUadid() {
		return uadid;
	}
	public void setUadid(String uadid) {
		this.uadid = uadid;
	}
	public String getUid() {
		return uid;
	}
	public void setUid(String uid) {
		this.uid = uid;
	}
	public String getProvince() {
		return province;
	}
	public void setProvince(String province) {
		this.province = province;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getDatailaddress() {
		return datailaddress;
	}
	public void setDatailaddress(String datailaddress) {
		this.datailaddress = datailaddress;
	}
	public int getAddressdr() {
		return addressdr;
	}
	public void setAddressdr(int addressdr) {
		this.addressdr = addressdr;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getUadname() {
		return uadname;
	}
	public void setUadname(String uadname) {
		this.uadname = uadname;
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
		return "Uaddress [uadid=" + uadid + ", uid=" + uid + ", province=" + province + ", city=" + city + ", area="
				+ area + ", datailaddress=" + datailaddress + ", addressdr=" + addressdr + ", mobile=" + mobile
				+ ", uadname=" + uadname + ", reserved1=" + reserved1 + ", reserved2=" + reserved2 + ", reserved3="
				+ reserved3 + ", reserved4=" + reserved4 + ", reserved5=" + reserved5 + "]";
	}
	
	
}

package com.softeem.service;

import com.softeem.model.ResultModel;

public interface UaddressService {

	// yonghu用户添加地址的方法
	public ResultModel useraddaddress(String uid, String uadname, String mobile, String province, String city,
			String area, String detailaddress);
	
	//根据用户id查询该用户的所有收货地址
	public ResultModel useraddressAll(String uid);
	
	
	//指定用户下的指定地址设置为默认
	public ResultModel setdefaultaddress(String uid,String uadid);
	
	//用户删除地址的方法
	public ResultModel delteAddress(String uid,String uadid);
	
	
}

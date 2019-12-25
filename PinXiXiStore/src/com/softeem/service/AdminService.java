package com.softeem.service;

import com.softeem.model.Admin;
import com.softeem.model.ResultModel;

public interface AdminService {
	//系统管理员登录
	public ResultModel loadbyadminaccount(String adminaccount,String adminpassword);
	
	//系统管理员密码修改
	public ResultModel changebyadminaccount(String adminaccount ,String adminpassword);
	
	//系统管理员根据用户名查看密保问题
	public ResultModel checkbyquestion(String adminname);
	
	//系统管理员根据密码比对答案
	public ResultModel checkbyquestion1(String answer);
	
	//系统管理员根据storedr显示商家
	public ResultModel checkbystoredr(int storedr);
	
	//系統管理員根據sid審核商家入职
	public ResultModel changestoredr(String sid);
	
	//系统管理员根据checkdr显示商家
	public ResultModel checkbycheckdr(int checkdr);
	
	//系统管理员根据alterid进行修改信息审核通过
	public ResultModel changecheckdr(String alertid);
	
	//系统管理员根据alterid进行修改信息审核不通过
	public ResultModel changecheckdr1(String alertid);
	
	//显示所有的入驻申请待审核的商家
	public ResultModel checkAllstore(int page);
	
	//审核商家的时候进行封禁
	public ResultModel banstoredr(String sid);
	
	//显示所有信息修改的商家
	public ResultModel checkAllalterstore();
	
	//显示所有举报次数过多的商家
	public ResultModel checkReportMerchant();
		
	//封禁举报次数过多的商家
	public ResultModel banReportMerchant(String mid);
		
	//显示所有举报次数过多的商品
	public ResultModel checkReportGoods();
		
	//强制下架举报次数过多的商品
	public ResultModel downReportGoods(String gid);
	
	public ResultModel changephtot(String address,String adminaccount);
	//审核通过修改店铺表 中的信息
	public ResultModel adminUpdateStore(String aid);
	
	
	public Admin checkupload(String adminaccount,String adminpassword);
}

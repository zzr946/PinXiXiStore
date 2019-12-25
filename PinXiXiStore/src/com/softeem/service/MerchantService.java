package com.softeem.service;

import com.softeem.model.ResultModel;

public interface MerchantService {

	//商家注册
	public ResultModel reg(String phone,String password);
	
	//商家登录
	public ResultModel login(String phone,String password);
	
	//创建店铺后将店铺id加入商家表中
	public boolean addSid(String sid,String mid);
	
	//忘记密码修改密码
	public ResultModel updatePWD(String phone,String password);
}

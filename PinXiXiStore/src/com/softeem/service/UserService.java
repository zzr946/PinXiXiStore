package com.softeem.service;

import com.softeem.model.ResultModel;

public interface UserService {
	
	//用户注册
	public ResultModel reg(String phone,String password);
	
	
	//账号密码登录
	public ResultModel loginBypwd(String phone,String password);
	
	
	//短信验证登录
	public ResultModel loginByphonecode(String phone);
	
	
	//修改密码的方法
	public ResultModel modifyPassword(String phone,String newpassword);
	
	
	//修改用户信息的方法
	public ResultModel changeUserinfo(String uid,String photo,String nickname,String name,String sex);
	
	
	//用户根据密码修改密码的方法
	public ResultModel modifypwdtopwd(String uid,String oldpwd,String newpwd);
	
	//根据id查询用户所有信息
	public ResultModel selectUser(String userid);
	
}

package com.softeem.service.impl;

import com.softeem.dao.UserDAO;
import com.softeem.model.ResultModel;
import com.softeem.model.User;
import com.softeem.service.UserService;
import com.softeem.tools.Tools;

public class UserServiceImpl implements UserService {
	UserDAO udao=new UserDAO();
	/**
	 * 用户注册的方法
	 * 返回result结果集 code为0表示成功 1表示失败 2手机号不可用
	 */
	@Override
	public ResultModel reg(String phone, String password) {
		ResultModel result = new ResultModel();
		//判断手机号是否可用
		boolean boo=udao.selectPhoneExist(phone);
		if(boo){
			//手机号码已经存在
			result.setCode("2");
			result.setMsg("手机号已被占用");
			result.setData(null);
			return result;
		}
		//手机号可用，用户注册
		//获取id
		String uid=Tools.getUUID();
		//密码加密
		password=Tools.getMD5(password);
		User user=new User();
		user.setUid(uid);
		user.setPhone(phone);
		user.setPassword(password);
		user.setUserdr(0);//注册成功账号处于未登录状态
		//调用dao层
		boolean bo=udao.insert(user);
		if(bo){
			//注册成功
			result.setCode("0");
			result.setMsg("注册成功");
			result.setData(user);//只包含id,手机号,密码,状态0
		}else{
			//注册失败
			result.setCode("1");
			result.setMsg("注册失败");
			result.setData(null);
		}
		return result;
	}

	
	/**
	 * 用户使用密码登录
	 * 返回result结果集 code为0表示成功 1表示失败
	 */
	@Override
	public ResultModel loginBypwd(String phone, String password) {
		ResultModel result = new ResultModel();
		//密码加密
		password=Tools.getMD5(password);
		//调用dao层
		User user=udao.selectBypwd(phone, password);
		if(user==null){
			//登录失败
			result.setCode("1");
			result.setMsg("用户名或密码错误");
			result.setData(null);
		}else{
			//登录成功
			result.setCode("0");
			result.setMsg("登录成功");
			result.setData(user);
		}
		return result;
	}

	
	/**
	 * 使用手机验证码登录
	 * 返回result结果集 code为0表示成功 1表示失败
	 */
	@Override
	public ResultModel loginByphonecode(String phone) {
		ResultModel result = new ResultModel();
		//调用dao层
		User user=udao.selectByPhoneCode(phone);
		if(user==null){
			//登录失败
			result.setCode("1");
			result.setMsg("该手机号还未注册");
			result.setData(null);
		}else{
			//登录成功
			result.setCode("0");
			result.setMsg("登录成功");
			result.setData(user);
		}
		return result;
	}


	/**
	 * 用户忘记密码找回密码
	 * 返回result结果集 code为0表示成功 1表示失败 2表示该手机号没注册
	 */
	@Override
	public ResultModel modifyPassword(String phone, String newpassword) {
		ResultModel result=new ResultModel();
		//调用dao层查询手机号是否存在
		boolean boo=udao.selectPhoneExist(phone);
		if(!boo){
			//手机号不存在(还没有注册)
			result.setCode("2");
			result.setMsg("该手机号尚未注册");
			result.setData(null);
			return result;
		}
		//手机号存在(已经注册过了)
		//密码加密
		newpassword=Tools.getMD5(newpassword);
		//调用dao层的方法
		boolean bo=udao.updateByphone(phone, newpassword);
		if(bo){
			//修改成功
			result.setCode("0");
			result.setMsg("修改成功");
			result.setData(null);
		}else{
			//修改失败
			result.setCode("1");
			result.setMsg("修改失败");
			result.setData(null);
		}
		return result;
	}


	/**
	 * 用户修改个人信息
	 * 返回result结果集 code为0表示成功 1表示失败 
	 */
	@Override
	public ResultModel changeUserinfo(String uid,String photo, String nickname, String name, String sex) {
		ResultModel result=new ResultModel();
		//将用户信息装进user对象
		User user=new User();
		user.setPhoto(photo);
		user.setNickname(nickname);
		user.setName(name);
		user.setSex(sex);
		//调用dao层的方法修改用户信息
		boolean boo=udao.updateUserAll(uid, user);
		if(boo){
			//修改成功
			//调用dao层的方法查询出用户的所用信息
			User newuser=udao.selectUser(uid);
			result.setCode("0");
			result.setMsg("修改成功");
			result.setData(newuser);
		}else{
			//修改失败
			result.setCode("1");
			result.setMsg("修改失败");
			result.setData(null);
		}
		return result;
	}

	/**
	 * 用户根据密码修改密码的方法
	 * 返回result结果集 code为0表示成功 1表示失败  2表示原密码输入错误
	 */
	@Override
	public ResultModel modifypwdtopwd(String uid,String oldpwd,String newpwd) {
		ResultModel result=new ResultModel();
		//首先查询原来的密码是否正确
		//原密码加密
		oldpwd=Tools.getMD5(oldpwd);
		//调用dao层
		boolean bo=udao.selectpwd(uid, oldpwd);
		if(!bo){
			//如果没查询到
			result.setCode("2");
			result.setMsg("原密码输入错误");
			result.setData(null);
			return result;
		}
		
		//原密码输入正确，则继续往下执行
		//密码加密
		newpwd=Tools.getMD5(newpwd);
		//调用dao层的方法
		boolean boo=udao.updatepwd(uid, newpwd);
		if(boo){
			//修改成功
			result.setCode("0");
			result.setMsg("修改成功");
			result.setData(null);
		}else{
			//修改失败
			result.setCode("1");
			result.setMsg("修改失败");
			result.setData(null);
		}
		return result;
	}

	
	/**
	 * 根据用户id查询用户所有信息
	 */
	public ResultModel selectUser(String userid){
		ResultModel result=new ResultModel();
		//调用dao层的方法
		User u=udao.selectUser(userid);
		if(u==null){
			//查询失败
			result.setCode("1");
			result.setMsg("查询失败");
			result.setData(null);
		}else{
			//查询成功
			result.setCode("0");
			result.setMsg("查询成功");
			result.setData(u);
		}
		return result;
	}
	
}

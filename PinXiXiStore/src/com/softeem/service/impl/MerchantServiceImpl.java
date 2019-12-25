package com.softeem.service.impl;

import com.softeem.dao.MerchantDAO;
import com.softeem.model.Merchant;
import com.softeem.model.ResultModel;
import com.softeem.service.MerchantService;
import com.softeem.tools.Tools;

public class MerchantServiceImpl implements MerchantService {

	MerchantDAO mdao = new MerchantDAO();
	
	
	@Override
	public ResultModel reg(String phone, String password) {
		boolean boo = false;
		ResultModel result = new ResultModel();
		//判断手机号是否存在
		boo = mdao.select_Merchant_Phoneexist(phone);
		if(boo){
			//手机号码已经存在
			result.setCode("2");
			result.setMsg("手机号已被占用");
			result.setData(null);
			return result;
		}
		//手机号不存在，可以使用该手机号注册
		String mid = Tools.getUUID();
		password = Tools.getMD5(password);
		Merchant merchant = new Merchant();
		merchant.setMid(mid);
		merchant.setPhone(phone);
		merchant.setMpassword(password);
		merchant.setMerchantdr(0);//0表示账号状态正常
		boo = mdao.insert(merchant);
		if(boo){
			//注册成功
			result.setCode("0");
			result.setMsg("注册成功");
			result.setData(merchant);
		}else{
			//注册失败
			result.setCode("1");
			result.setMsg("注册失败");
			result.setData(null);
		}
		return result;
	}


	@Override
	public ResultModel login(String phone, String password) {
		ResultModel result = new ResultModel();
		password = Tools.getMD5(password);
		Merchant me = mdao.selectByPassword(phone,password);
		if(me == null){
			//没有查到数据，也就是用户名或密码错误
			result.setCode("1");
			result.setMsg("用户名或密码错误");
			result.setData(null);
			return result;
		}else{
			//登录成功
			result.setCode("0");
			result.setMsg("成功");
			result.setData(me);
			return result;
		}
	}


	@Override
	public boolean addSid(String sid, String mid) {
		return mdao.updateSid(sid, mid);
	}


	//修改密码
	@Override
	public ResultModel updatePWD(String phone, String password) {
		ResultModel result = new ResultModel();
		password = Tools.getMD5(password);
		boolean boo = mdao.updatePWD(phone, password);
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

}

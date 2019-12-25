package com.softeem.service.impl;

import java.util.List;

import com.softeem.dao.AdminDAO;
import com.softeem.model.Admin;
import com.softeem.model.Alertstore;
import com.softeem.model.Report;
import com.softeem.model.ResultModel;
import com.softeem.model.Store;
import com.softeem.service.AdminService;

public class AdminServiceImpl implements AdminService {
	AdminDAO admindao =new AdminDAO();

	@Override
	public ResultModel loadbyadminaccount(String adminaccount,String adminpassword) {
		//创建结果集
		ResultModel result = new ResultModel();
		//调用dao层
		Admin admin =admindao.loadbyadminaccount(adminaccount, adminpassword);
		if(admin==null){
			//登录失败
			result.setCode("1");
			result.setMsg("用户名或密码错误");
			result.setData(null);
		}else{
			//登录成功
			result.setCode("0");
			result.setMsg("登录成功");
			result.setData(admin);
		}
		return result;
	}

	//商家店铺信息修改
	public ResultModel adminUpdateStore(String aid){
		ResultModel result = new ResultModel();
		Alertstore as = admindao.selectByAid(aid);
		String sid = admindao.selectByMid(as.getMid());
		boolean boo = admindao.updateStore(as,sid);
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
	
	
	
	@Override
	public ResultModel changebyadminaccount(String adminaccount, String adminpassword) {
		//创建结果集
		ResultModel result = new ResultModel();
		//调用dao层
		boolean boo = admindao.changebyadminaccount(adminaccount, adminpassword);
		if(boo){
			//登录成功
			result.setCode("0");
			result.setMsg("修改成功");
			result.setData(null);
		}else{
			//登录失败
			result.setCode("1");
			result.setMsg("修改失败");
			result.setData(null);
		}
		
		return result;
	}





	@Override
	public ResultModel checkbyquestion(String adminname) {
		//创建结果集
		ResultModel result = new ResultModel();
		Admin admin = admindao.checkbyquestion(adminname);
		System.out.println("已经找到了");
		System.out.println(admin);
		if(admin==null){
			//登录失败
			result.setCode("1");
			result.setMsg("请输入正确的系统管理员姓名");
			result.setData(null);
		}else{
			//登录成功
			result.setCode("0");
			result.setMsg("输入正确");
			result.setData(admin);
		}
		return result;
	}





	@Override
	public ResultModel checkbyquestion1(String answer) {
		//创建结果集
		ResultModel result = new ResultModel();
		Admin admin = admindao.checkbyquestion1(answer);
		if(admin==null){
			//登录失败
			result.setCode("1");
			result.setMsg("请输入正确的系统管理员姓名");
			result.setData(null);
		}else{
			//登录成功
			result.setCode("0");
			result.setMsg("输入正确");
			result.setData(admin);
		}
		return result;
	}





	@Override
	public ResultModel checkbystoredr(int storedr) {
		//创建结果集
		ResultModel result = new ResultModel();
		List<Store> list = admindao.checkstore(storedr);
		if(list==null){
			//没有带审核的商家
			result.setCode("1");
			result.setMsg("还没有待审核的商家");
			result.setData(null);
		}else{
			//查看到带审核的商家
			result.setCode("0");
			result.setMsg("显示待审核的商家");
			result.setData(list);
		}
		return result;
	}




	/*
	 * 审核商家入住 
	 */
	@Override
	public ResultModel changestoredr(String sid) {
		//創建一個結果集
		ResultModel result = new ResultModel();
		int storedr1=1;
		Boolean boo= admindao.changestoredr(sid, storedr1);
		if(boo){
			//更改成功
			result.setCode("0");
			result.setMsg("更改成功");
			result.setData(null);
		}else{
			//更改失敗
			result.setCode("1");
			result.setMsg("更改失敗");
			result.setData(null);
		}
		return result;
	}
	
	/*
	 * 商家审核时进行封禁
	 */
	@Override
	public ResultModel banstoredr(String sid) {
		//創建一個結果集
		ResultModel result = new ResultModel();
		int storedr1=2;
		Boolean boo= admindao.changestoredr(sid, storedr1);
		if(boo){
			//更改成功
			result.setCode("0");
			result.setMsg("更改成功");
			result.setData(null);
		}else{
			//更改失敗
			result.setCode("1");
			result.setMsg("更改失敗");
			result.setData(null);
		}
		return result;
	}
	
	
	




	//修改信息审核
	@Override
	public ResultModel changecheckdr(String alertid) {
		//创建一个结果集
		ResultModel result = new ResultModel();
		int checkdr=1;
		boolean boo = admindao.changecheckdr(alertid, checkdr);
		System.out.println(boo);
		if(boo){
			//更改成功
			result.setCode("0");
			result.setMsg("更改成功");
			result.setData(null);
		}else{
			//更改失败
			result.setCode("1");
			result.setMsg("更改失败");
			result.setData(null);
		}
		return result;
	}




	//系统管理员根据checkdr显示商家
	@Override
	public ResultModel checkbycheckdr(int checkdr) {
		//创建一个结果集
		ResultModel result = new ResultModel();
		List<Alertstore> list = admindao.checkalterstore(checkdr);
		if(list==null){
			//还没有需要修改信息的商家
			result.setCode("1");
			result.setMsg("还没有需要修改信息的商家");
			result.setData(null);
		}else{
			//查询到有需要修改信息的商家
			result.setCode("0");
			result.setMsg("查询到有需要修改信息的商家");
			result.setData(list);
		}
		return result;
	}




	//显示所有需要审核商家的商家
	@Override
	public ResultModel checkAllstore(int page) {
		//创建结果集
		ResultModel result = new ResultModel();
		List<Store> list = admindao.checkAllstore(page);
		
		if(list.isEmpty()){
			//还没有需要修改信息的商家
			result.setCode("1");
			result.setMsg("还没有需要修改信息的商家");
			result.setData(null);
		}else{
			//查询到有需要修改信息的商家
			result.setCode("0");
			result.setMsg("查询到有需要修改信息的商家");
			result.setData(list);
		}
		return result;
	}




	//显示所有需要信息修改审核的商家
	@Override
	public ResultModel checkAllalterstore() {
		//创建结果集
		ResultModel result = new ResultModel();
		List<Alertstore> list  = admindao.checkAllalterstore();
		if(list==null){
			//还没有需要修改信息的商家
			result.setCode("1");
			result.setMsg("还没有需要修改信息的商家");
			result.setData(null);
		}else{
			//查询到有需要修改信息的商家
			result.setCode("0");
			result.setMsg("查询到有需要修改信息的商家");
			result.setData(list);
		}
		return result;
	}





	@Override
	public ResultModel changecheckdr1(String alertid) {
		//创建一个结果集
				ResultModel result = new ResultModel();
				int checkdr=2;
				boolean boo = admindao.changecheckdr(alertid, checkdr);
				System.out.println(boo);
				if(boo){
					//更改成功
					result.setCode("0");
					result.setMsg("更改成功");
					result.setData(null);
				}else{
					//更改失败
					result.setCode("1");
					result.setMsg("更改失败");
					result.setData(null);
				}
				return result;
	}





	@Override
	public Admin checkupload(String adminaccount,String adminpassword) {
		Admin admin = admindao.loadbyadminaccount(adminaccount, adminpassword);
		return admin;
	}





	@Override
	public ResultModel changephtot(String address, String adminaccount) {
		ResultModel result = new ResultModel();
		boolean boo = admindao.changephoto(address, adminaccount);
		if(boo){
			//成功
			result.setCode("0");
			result.setMsg("上传成功");
			result.setData(null);
		}else{
			//失败
			result.setCode("1");
			result.setMsg("上传失败");
			result.setData(null);
		}
		return result;
	}

	@Override
	public ResultModel checkReportMerchant() {
		//创建结果集
				ResultModel result = new ResultModel();
				List<Report> list = admindao.checkreportmarchant();
				
				if(list.isEmpty()){
					//还没有需要修改信息的商家
					result.setCode("1");
					result.setMsg("还没有举报次数过多的商家");
					result.setData(null);
				}else{
					//查询到有需要修改信息的商家
					result.setCode("0");
					result.setMsg("查询到举报次数过多的商家");
					result.setData(list);
				}
				return result;
	}










	@Override
	public ResultModel banReportMerchant(String mid) {
		//创建一个结果集
				ResultModel result = new ResultModel();
				
				boolean boo = admindao.banTheMerchant(mid);
				System.out.println(mid);
				System.out.println(boo);
				if(boo){
					//更改成功
					result.setCode("0");
					result.setMsg("更改成功");
					result.setData(null);
				}else{
					//更改失败
					result.setCode("1");
					result.setMsg("更改失败");
					result.setData(null);
				}
				return result;
	}










	@Override
	public ResultModel checkReportGoods() {
		ResultModel result = new ResultModel();
		List<Report> list = admindao.checkreportgoods();
		
		if(list.isEmpty()){
			//还没有需要修改信息的商家
			result.setCode("1");
			result.setMsg("还没有举报次数过多的商品");
			result.setData(null);
		}else{
			//查询到有需要修改信息的商家
			result.setCode("0");
			result.setMsg("查询到举报次数过多的商品");
			result.setData(list);
		}
		return result;
	}










	@Override
	public ResultModel downReportGoods(String gid) {
		//创建一个结果集
		ResultModel result = new ResultModel();
		System.out.println(gid);
		boolean boo = admindao.downTheGoods(gid);
		System.out.println(boo);
		if(boo){
			//更改成功
			result.setCode("0");
			result.setMsg("更改成功");
			result.setData(null);
		}else{
			//更改失败
			result.setCode("1");
			result.setMsg("更改失败");
			result.setData(null);
		}
		return result;
	}

	
	
	
	
	
	
	
}

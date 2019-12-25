package com.softeem.service.impl;

import java.util.List;

import com.softeem.dao.UAddressDAO;
import com.softeem.model.ResultModel;
import com.softeem.model.Uaddress;
import com.softeem.service.UaddressService;
import com.softeem.tools.Tools;

public class UaddressServiceImpl implements UaddressService {
	private UAddressDAO uaddressdao=new UAddressDAO();
	/**
	 * 用户添加收货地址的方法
	 * 返回result结果集 0表示添加成功 1表示添加失败 2表示上限，不能再添加
	 */
	@Override
	public ResultModel useraddaddress(String uid, String uadname, String mobile, String province, String city,
			String area, String detailaddress) {
		ResultModel result=new ResultModel();
		//查询该用户下的所有地址
		List<Uaddress> list=uaddressdao.selectaddressAll(uid);
		if(list.size()>=3){
			result.setCode("2");
			result.setMsg("已经添加上限，不能在添加了");
			result.setData(null);
			return result;
		}
		
		//获取UUID
		String uadid=Tools.getUUID();
		Uaddress address=new Uaddress(uadid, uid, province, city, area, detailaddress, 1, mobile, uadname, null, null, null, null, null);
		//调用dao层的方法
		boolean boo=uaddressdao.insertUseraddress(address);
		if(boo){
			//添加成功
			//查询该用户下的所有收货地址
			List<Uaddress> list1=uaddressdao.selectaddressAll(uid);
			result.setCode("0");
			result.setMsg("添加成功");
			result.setData(list1);
		}else{
			//添加失败
			result.setCode("1");
			result.setMsg("添加失败");
			result.setData(null);
		}
		return result;
	}

	
	/**
	 * 根据用户id查看该用户下的所有地址
	 * 返回result结果集 0表示查到数据  1表示没有查到数据 
	 */
	public ResultModel useraddressAll(String uid){
		ResultModel result=new ResultModel();
		//调用uaddressdao层的方法
		List<Uaddress> list=uaddressdao.selectaddressAll(uid);
		if(list.isEmpty()){
			//没查到数据
			result.setCode("1");
			result.setMsg("没查到数据");
			result.setData(null);
		}else{
			//查到数据
			result.setCode("0");
			result.setMsg("查到数据");
			result.setData(list);
		}
		return result;
	}


	/**
	 * 修改指定用户下的默认地址
	 */
	@Override
	public ResultModel setdefaultaddress(String uid, String uadid) {
		ResultModel result=new ResultModel();
		//首先将该用户下的所有地址设置为备选状态
		//调用dao层的方法将该用户下的所有地址状态改为备选状态
		boolean boo=uaddressdao.updateaddresstonot(uid);
		if(boo){
			//如果设置成功则将指定id的地址修改为默认地址
			boolean bo=uaddressdao.updateaddress(uid, uadid);
			if(bo){
				//将地址设置为默认状态成功
				result.setCode("0");
				result.setMsg("设置成功");
				result.setData(null);
			}else{
				//将地址设置为默认状态失败
				result.setCode("1");
				result.setMsg("设置失败");
				result.setData(null);
			}
		}else{
			//将地址设置为备选状态失败
			result.setCode("2");
			result.setMsg("将地址设置成备选状态失败");
			result.setData(null);
		}
		return result;
	}


	/**
	 * 用户删除地址的方法
	 */
	@Override
	public ResultModel delteAddress(String uid, String uadid) {
		ResultModel result=new ResultModel();
		//调用dao层的方法
		boolean boo=uaddressdao.UpdateAddress(uid, uadid);
		if(boo){
			//删除成功
			//查询该用户下的所有收货地址
			List<Uaddress> list=uaddressdao.selectaddressAll(uid);
			result.setCode("0");
			result.setMsg("删除成功");
			result.setData(list);
		}else{
			//删除失败
			result.setCode("1");
			result.setMsg("删除失败");
			result.setData(null);
		}
		return result;
	}
	
}

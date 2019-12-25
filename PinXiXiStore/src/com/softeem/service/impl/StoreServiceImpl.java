package com.softeem.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.softeem.dao.StoreDAO;
import com.softeem.model.ResultModel;
import com.softeem.model.Store;
import com.softeem.service.StoreService;

public class StoreServiceImpl implements StoreService {

	StoreDAO sdao = new StoreDAO();
	
	/**
	 * 注册店铺,返回一个结果集
	 */
	@Override
	public ResultModel reg(String sid,String storename, String storelogo, String storeinfo, String storeaddress, String aptitude) {
		boolean boo = false;
		ResultModel result = new ResultModel();
		//判断店铺是否存在
		boo = sdao.selectStoreExist(storename);
		if(boo){
			//说明店铺存在
			result.setCode("2");
			result.setMsg("店铺已存在");
			result.setData(null);
			return result;
		}
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		String subtime = df.format(date);//使用当前系统时间为注册时间
		Store store = new Store();
		store.setSid(sid);
		store.setStorelogo(storelogo);
		store.setStoreinfo(storeinfo);
		store.setStoreaddress(storeaddress);
		store.setAptitude(aptitude);
		store.setStorename(storename);
		store.setSubtime(subtime);
		store.setStoredr(0);
		boo = sdao.insert(store);
		if(boo){
			//创建成功
			result.setCode("0");
			result.setMsg("创建成功");
			result.setData(store);
		}else{
			//创建失败
			result.setCode("1");
			result.setMsg("创建失败");
			result.setData(null);
		}
		return result;
	}
	

	/**
	 * 查看是否创建店铺，返回一个结果集
	 */
	@Override
	public ResultModel selectExist(String msid) {
		Store store = sdao.selectByMsid(msid);
		ResultModel result = new ResultModel();
		if(store == null){
			result.setCode("-1");//表示未创建店铺
			result.setMsg("还未创建店铺");
			result.setData(null);
			return result;
		}
		//已创建店铺
		if(store.getStoredr() == 0){
			result.setCode("3");//还未激活
			result.setMsg("店铺还未激活");
			result.setData(store);
			return result;
		}else if(store.getStoredr() == 1){
			result.setCode("1");//已激活
			result.setMsg("店铺已激活");
			result.setData(store);
			return result;
		}else if(store.getStoredr() == 2){
			result.setCode("2");//还未激活
			result.setMsg("店铺封禁中");
			result.setData(store);
			return result;
		}
		return result;
	}


	//提交修改店铺的信息
	@Override
	public ResultModel sendUpdate(String mid, String newname, String newstoreinfo, String newaddress) {
		boolean boo = sdao.updateStore(mid, newname, newstoreinfo, newaddress);
		ResultModel result = new ResultModel();
		if(boo){
			result.setCode("0");
			result.setMsg("提交成功");
			result.setData(null);
		}else{
			result.setCode("1");
			result.setMsg("提交失败");
			result.setData(null);
		}
		return result;
	}

}

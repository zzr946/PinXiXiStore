package com.softeem.service;

import com.softeem.model.ResultModel;

public interface StoreService {

	//创建店铺
	public ResultModel reg(String sid,String storename,String storelogo,String storeinfo,String storeaddress,String aptitude);
	
	//查看商家表中的sid查看店铺是否创建
	public ResultModel selectExist(String msid);
	
	//提交修改店铺信息
	public ResultModel sendUpdate(String mid,String newname,String newstoreinfo,String newaddress);
}

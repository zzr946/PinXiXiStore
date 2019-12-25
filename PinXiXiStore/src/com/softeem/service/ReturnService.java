package com.softeem.service;

import com.softeem.model.ResultModel;

public interface ReturnService {

	//按页查询退货列表
	public ResultModel selectByRPage(int page,String mid);
	
	//确认退货
	public ResultModel agreeTuiHuo(String id);
}

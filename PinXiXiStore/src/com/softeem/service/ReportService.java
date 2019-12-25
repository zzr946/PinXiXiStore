package com.softeem.service;

import com.softeem.model.ResultModel;

public interface ReportService {
	
	//用户举报的方法
	public ResultModel report(String uid,String gid,String goodsname,String reportcause,String reportcontent);
	
}

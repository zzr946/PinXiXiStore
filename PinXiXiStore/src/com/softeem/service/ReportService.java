package com.softeem.service;

import com.softeem.model.ResultModel;

public interface ReportService {
	
	//�û��ٱ��ķ���
	public ResultModel report(String uid,String gid,String goodsname,String reportcause,String reportcontent);
	
}

package com.softeem.service;

import com.softeem.model.ResultModel;

public interface ReturnService {

	//��ҳ��ѯ�˻��б�
	public ResultModel selectByRPage(int page,String mid);
	
	//ȷ���˻�
	public ResultModel agreeTuiHuo(String id);
}

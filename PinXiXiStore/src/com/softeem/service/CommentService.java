package com.softeem.service;

import com.softeem.model.ResultModel;

public interface CommentService {

	//����ָ��ҳ���������б�
	public ResultModel selectByCPage(int page);
	
	//��������idɾ������
	public ResultModel deleteByCid(String cid);
}

package com.softeem.service;

import com.softeem.model.ResultModel;

public interface CommelistService {
	
	//�����û�id��ѯ��Ʒ��Ϣ
	public ResultModel goodsInfo(String gid);
	
	//���û��ύ�����۲������ݿ�
	public ResultModel saveComment(String uid,String gid,String content);
	
	//�鿴ĳ����Ʒ����������
	public ResultModel commentAll(String gid);
	
}

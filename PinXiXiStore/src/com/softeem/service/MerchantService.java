package com.softeem.service;

import com.softeem.model.ResultModel;

public interface MerchantService {

	//�̼�ע��
	public ResultModel reg(String phone,String password);
	
	//�̼ҵ�¼
	public ResultModel login(String phone,String password);
	
	//�������̺󽫵���id�����̼ұ���
	public boolean addSid(String sid,String mid);
	
	//���������޸�����
	public ResultModel updatePWD(String phone,String password);
}

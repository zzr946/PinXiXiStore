package com.softeem.service;

import com.softeem.model.ResultModel;

public interface UaddressService {

	// yonghu�û���ӵ�ַ�ķ���
	public ResultModel useraddaddress(String uid, String uadname, String mobile, String province, String city,
			String area, String detailaddress);
	
	//�����û�id��ѯ���û��������ջ���ַ
	public ResultModel useraddressAll(String uid);
	
	
	//ָ���û��µ�ָ����ַ����ΪĬ��
	public ResultModel setdefaultaddress(String uid,String uadid);
	
	//�û�ɾ����ַ�ķ���
	public ResultModel delteAddress(String uid,String uadid);
	
	
}

package com.softeem.service;

import com.softeem.model.ResultModel;

public interface StoreService {

	//��������
	public ResultModel reg(String sid,String storename,String storelogo,String storeinfo,String storeaddress,String aptitude);
	
	//�鿴�̼ұ��е�sid�鿴�����Ƿ񴴽�
	public ResultModel selectExist(String msid);
	
	//�ύ�޸ĵ�����Ϣ
	public ResultModel sendUpdate(String mid,String newname,String newstoreinfo,String newaddress);
}

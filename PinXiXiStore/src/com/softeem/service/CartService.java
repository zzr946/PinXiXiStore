package com.softeem.service;

import com.softeem.model.ResultModel;

public interface CartService {
	
	//�û���ӵ����ﳵ�ķ���
	public ResultModel addGoodsToCart(String uid,String gid,double price,int count);
	
	//��ѯ�û��Ĺ��ﳵ��������Ʒ�ķ���
	public ResultModel cartAll(String uid);
	
	
}

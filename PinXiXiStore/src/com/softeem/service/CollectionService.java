package com.softeem.service;

import com.softeem.model.ResultModel;

public interface CollectionService {
	
	//�����ղر�ķ���(�Ѳ���Ʒ)
	public ResultModel addcollGoods(String uid,String gid);

	//�鿴�û��ղص���Ʒ
	public ResultModel selectcollGoods(String uid);
	
	
}

package com.softeem.service;

import com.softeem.model.Goods;
import com.softeem.model.ResultModel;

public interface SalesService {

	// ��ѯ��Ʒ�������е�������Ʒ
	public ResultModel salesall();

	// ��������в�������
	public ResultModel insert(Goods goods, int salesdr, String id);

	// ɾ��ָ������
	public ResultModel delete(String gid);

}

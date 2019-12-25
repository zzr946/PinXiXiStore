package com.softeem.service;

import com.softeem.model.ResultModel;
import com.softeem.model.Sort;

public interface SortService {

	//��ӷ���
	public ResultModel addSort(String sortname,String sortinfo,String merchantid);
	
	//��ҳ��ѯ����
	public ResultModel selectByPage(String mid,int page);
	
	//��ѯ���з���
	public ResultModel selectAll(String mid);
	
	//ɾ������
	public ResultModel deleteBySid(String sid);
	
	//�޸ķ���
	public ResultModel updateSort(String sortname1,String sortname2,String sortinfo);
}

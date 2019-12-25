package com.softeem.service;

import com.softeem.model.ResultModel;
import com.softeem.model.Sort;

public interface SortService {

	//添加分类
	public ResultModel addSort(String sortname,String sortinfo,String merchantid);
	
	//按页查询分类
	public ResultModel selectByPage(String mid,int page);
	
	//查询所有分类
	public ResultModel selectAll(String mid);
	
	//删除分类
	public ResultModel deleteBySid(String sid);
	
	//修改分类
	public ResultModel updateSort(String sortname1,String sortname2,String sortinfo);
}

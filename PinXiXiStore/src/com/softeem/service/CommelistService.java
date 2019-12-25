package com.softeem.service;

import com.softeem.model.ResultModel;

public interface CommelistService {
	
	//根据用户id查询商品信息
	public ResultModel goodsInfo(String gid);
	
	//将用户提交的评论插入数据库
	public ResultModel saveComment(String uid,String gid,String content);
	
	//查看某件商品的所有评论
	public ResultModel commentAll(String gid);
	
}

package com.softeem.service;

import com.softeem.model.ResultModel;

public interface CommentService {

	//加载指定页数的评论列表
	public ResultModel selectByCPage(int page);
	
	//根据评论id删除评论
	public ResultModel deleteByCid(String cid);
}

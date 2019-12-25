package com.softeem.service;

import com.softeem.model.ResultModel;

public interface CollectionService {
	
	//插入收藏表的方法(搜藏商品)
	public ResultModel addcollGoods(String uid,String gid);

	//查看用户收藏的商品
	public ResultModel selectcollGoods(String uid);
	
	
}

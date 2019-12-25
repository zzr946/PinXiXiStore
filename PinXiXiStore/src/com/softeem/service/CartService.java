package com.softeem.service;

import com.softeem.model.ResultModel;

public interface CartService {
	
	//用户添加到购物车的方法
	public ResultModel addGoodsToCart(String uid,String gid,double price,int count);
	
	//查询用户的购物车里所有商品的方法
	public ResultModel cartAll(String uid);
	
	
}

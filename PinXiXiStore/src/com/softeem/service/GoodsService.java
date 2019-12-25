package com.softeem.service;

import com.softeem.model.Goods;
import com.softeem.model.ResultModel;

public interface GoodsService {
	
	//查询所有商品的方法(第一页)
	public ResultModel goodsAll();
	
	//查询所有商品的方法(指定页数)
	public ResultModel goodsAllpage(int page);
	
	//根据用户输入的内容模糊查询(显示第一页)
	public ResultModel goodsByinput(String title);
	
	//根据用户输入的内容查询相关商品(指定页数)
	public ResultModel goodsByinputpage(String title,int page);
	
	
	//根据用户输入的内容查询相关商品,并按销量排序(指定页数)
	public ResultModel inputSalesSortpage(String title,int page);
	
	//根据用户输入的内容查询相关商品,并按销量排序(指定页数)
	public ResultModel inputPriceSortpage(String title,int page);
	
	
	
	//查询所有商品并按销量排序(第一页)
	public ResultModel salesSort();
	
	//查询所有按销量排序完的商品(指定页数)
	public ResultModel salesSortpage(int page);
	
	//查询所有商品并按单价排序
	public ResultModel priceSort();
	
	//查询指定页的商品并按单价排序
	public ResultModel priceSortpage(int page);
	
	
	//查询用户搜索的商品，并按销量排序(第一页)
	public ResultModel selectSalesBynameSort(String title);
	
	
	//查询用户搜索的商品，并按价格排序(第一页)
	public ResultModel selectPriceBynameSort(String title);
	
	
	//添加商品
		public ResultModel addGoods(Goods goods);
		
		//通过页数查询商品列表
		public ResultModel selectByPage(String mid,int page);
		
		//通过页数查询商品列表
			public ResultModel selectBySpecialPage(String mid,int page);
		
		//修改商品信息
		public ResultModel updateGoods(Goods goods);
		
		//商品上架
		public ResultModel shangjia(String gid);
		
		//商品下架
		public ResultModel xiajia(String gid);
		
		//修改促销价格和促销状态
		public ResultModel updateDiscount(String gid,double discountprice);
		
		//修改促销价格和促销状态
			public ResultModel updateDiscountSecond(String gid,double goodsprice);
	
	
	
	
}

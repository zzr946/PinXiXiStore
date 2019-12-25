package com.softeem.service.impl;

import java.util.List;

import com.softeem.dao.GoodsDAO;
import com.softeem.model.Goods;
import com.softeem.model.ResultModel;
import com.softeem.service.GoodsService;

public class GoodsServiceImpl implements GoodsService {
	
	private GoodsDAO gdao=new GoodsDAO();
	
	/**
	 * 查询所有商品的方法(第一页)
	 * 返回result结果集 0表示查询成功  1表示未查询到商品
	 */
	@Override
	public ResultModel goodsAll() {
		ResultModel result=new ResultModel();
		//调用dao层的方法查询所有
		List<Goods> list=gdao.selectGoodsAll();
		if(list.isEmpty()){
			//没有商品
			result.setCode("1");
			result.setMsg("未查询到商品");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("查询成功");
			result.setData(list);
		}
		return result;
	}

	/**
	 * 查询所有商品的方法(指定页数)
	 * 返回result结果集 0表示查询成功  1表示未查询到商品
	 */
	@Override
	public ResultModel goodsAllpage(int page) {
		ResultModel result=new ResultModel();
		//调用dao层的方法查询所有
		List<Goods> list=gdao.selectGoodsAllpage(page);
		if(list.isEmpty()){
			//没有商品
			result.setCode("1");
			result.setMsg("未查询到商品");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("查询成功");
			result.setData(list);
		}
		return result;
	}
	
	
	
	
	/**
	 * 根据用户输入的条件查询相关商品(第一页)
	 * 返回result结果集 0表示查询成功  1表示未查询到商品
	 */
	@Override
	public ResultModel goodsByinput(String title) {
		ResultModel result=new ResultModel();
		//调用dao层的方法
		List<Goods> list=gdao.selectGoodsByname(title);
		if(list.isEmpty()){
			//没有商品
			result.setCode("1");
			result.setMsg("未查询到商品");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("查询成功");
			result.setData(list);
		}
		return result;
	}

	
	/**
	 * 根据用户输入的条件查询相关商品(指定页数)
	 * 返回result结果集 0表示查询成功  1表示未查询到商品
	 */
	@Override
	public ResultModel goodsByinputpage(String title,int page){
		ResultModel result=new ResultModel();
		//调用dao层的方法
		List<Goods> list=gdao.selectGoodsBynamepage(title, page);
		if(list.isEmpty()){
			//没有商品
			result.setCode("1");
			result.setMsg("未查询到商品");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("查询成功");
			result.setData(list);
		}
		return result;
	}
	
	
	
	/**
	 * 查询所有商品并按销量排序的方法(第一页)
	 * 返回result结果集 0表示查询成功  1表示未查询到商品
	 * 返回
	 */
	@Override
	public ResultModel salesSort() {
		ResultModel result=new ResultModel();
		//调用dao层的方法
		List<Goods> list=gdao.selectsalesstore();
		if(list.isEmpty()){
			//没有商品
			result.setCode("1");
			result.setMsg("未查询到商品");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("查询成功");
			result.setData(list);//存放排序完的商品对象集合(第一页)
		}
	return result;
	}

	
	/**
	 * 查询指定页的所有商品按销量排序完的结果(指定页数)
	 */
	@Override
	public ResultModel salesSortpage(int page){
		ResultModel result=new ResultModel();
		List<Goods> list=gdao.selectsalesstorepage(page);
		if(list.isEmpty()){
			//没有商品
			result.setCode("1");
			result.setMsg("未查询到商品");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("查询成功");
			result.setData(list);//存放排序完的商品对象集合(第一页)
		}
		return result;
	}
	
	
	/**
	 * 按单价查询所有商品(第一页)
	 */
	@Override
	public ResultModel priceSort() {
		ResultModel result=new ResultModel();
		//调用dao层的方法
		List<Goods> list=gdao.selectpricesort();
		if(list.isEmpty()){
			//没有商品
			result.setCode("1");
			result.setMsg("未查询到商品");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("查询成功");
			result.setData(list);//存放排序完的商品对象集合(第一页)
		}
		return result;
	}


	/**
	 * 查询指定页数的所有商品并按单价排序(指定页数)
	 */
	@Override
	public ResultModel priceSortpage(int page) {
		ResultModel result=new ResultModel();
		//调用dao层的方法
		List<Goods> list=gdao.selectpricesortpage(page);
		if(list.isEmpty()){
			//没有商品
			result.setCode("1");
			result.setMsg("未查询到商品");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("查询成功");
			result.setData(list);//存放排序完的商品对象集合(第一页)
		}
		return result;
	}
	
	/**
	 * 查询用户搜索的商品，并按销量排序(第一页)
	 */
	@Override
	public ResultModel selectSalesBynameSort(String title) {
		ResultModel result=new ResultModel();
		//调用dao层的方法
		List<Goods> list=gdao.selectSalesBynameSort(title);
		if(list.isEmpty()){
			//没有商品
			result.setCode("1");
			result.setMsg("未查询到商品");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("查询成功");
			result.setData(list);//存放排序完的商品对象集合(第一页)
		}
		return result;
	}


	/**
	 * 查询用户搜索的商品，并按价格排序(第一页)
	 */
	@Override
	public ResultModel selectPriceBynameSort(String title) {
		ResultModel result=new ResultModel();
		//调用dao层的方法
		List<Goods> list=gdao.selectPriceBynameSort(title);
		if(list.isEmpty()){
			//没有商品
			result.setCode("1");
			result.setMsg("未查询到商品");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("查询成功");
			result.setData(list);//存放排序完的商品对象集合(第一页)
		}
		return result;
	}

	



	
	/**
	 * 根据用户输入的内容查询相关商品,并按销量排序(指定页数)
	 */
	@Override
	public ResultModel inputSalesSortpage(String title, int page) {
		ResultModel result=new ResultModel();
		//调用dao层的方法
		List<Goods> list=gdao.selectSalesBynameSortpage(title, page);
		if(list.isEmpty()){
			//没有商品
			result.setCode("1");
			result.setMsg("未查询到商品");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("查询成功");
			result.setData(list);//存放排序完的商品对象集合(第一页)
		}
		return result;
	}

	
	/**
	 * 根据用户输入的内容查询相关商品,并按价格排序(指定页数)
	 */
	@Override
	public ResultModel inputPriceSortpage(String title, int page) {
		ResultModel result=new ResultModel();
		//调用dao层的方法
		List<Goods> list=gdao.selectPriceBynameSortpage(title, page);
		if(list.isEmpty()){
			//没有商品
			result.setCode("1");
			result.setMsg("未查询到商品");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("查询成功");
			result.setData(list);//存放排序完的商品对象集合(第一页)
		}
		return result;
	}
	
	
	//添加商品
		@Override
		public ResultModel addGoods(Goods goods) {
			//检查商品名是否存在
			boolean boo = gdao.selectByGname(goods.getGoodsname(), goods.getMid());
			ResultModel result = new ResultModel();
			if(boo){
				//商品名已存在
				result.setCode("2");
				result.setMsg("商品名已存在");
				result.setData(null);
				return result;
			}
			//商品名可用
			boo = gdao.insert(goods);
			if(boo){
				result.setCode("0");
				result.setMsg("添加成功");
				result.setData("goods");
			}else{
				result.setCode("1");
				result.setMsg("添加错误");
				result.setData(null);
			}
			return result;
		}
		
		//通过页数查找商品列表
		@Override
		public ResultModel selectByPage(String mid, int page) {
			ResultModel result = new ResultModel();
			List<Goods> list = gdao.selectByPage(mid, page);
			if(list.isEmpty()){
				result.setCode("1");
				result.setMsg("没查到");
				result.setData(null);
			}else{
				result.setCode("0");
				result.setMsg("查到了");
				result.setData(list);
			}
			return result;
		}

		//修改商品信息
		@Override
		public ResultModel updateGoods(Goods goods) {
			ResultModel result = new ResultModel();
			boolean boo = gdao.updateGoods(goods);
			if(boo){
				//更新成功
				result.setCode("0");
				result.setMsg("修改成功");
				result.setData(null);
			}else{
				//更新失败
				result.setCode("1");
				result.setMsg("修改失败");
				result.setData(null);
			}
			return result;
		}

		//上架
		@Override
		public ResultModel shangjia(String gid) {
			ResultModel result = new ResultModel();
			boolean boo = gdao.updateGoodsDrS(gid);
			if(boo){
				//上架成功
				result.setCode("0");
				result.setMsg("上架成功");
				result.setData(null);
			}else{
				//上架失败
				result.setCode("1");
				result.setMsg("上架失败");
				result.setData(null);
			}
			return result;
		}

		//下架
		@Override
		public ResultModel xiajia(String gid) {
			ResultModel result = new ResultModel();
			boolean boo = gdao.updateGoodsDrX(gid);
			if(boo){
				//下架成功
				result.setCode("0");
				result.setMsg("下架成功");
				result.setData(null);
			}else{
				//下架失败
				result.setCode("1");
				result.setMsg("下架失败");
				result.setData(null);
			}
			return result;
		}

		//促销
		@Override
		public ResultModel selectBySpecialPage(String mid, int page) {
			ResultModel result = new ResultModel();
			List<Goods> list = gdao.selectBySpecialPage(mid, page);
			if(list.isEmpty()){
				result.setCode("1");
				result.setMsg("没查到");
				result.setData(null);
			}else{
				result.setCode("0");
				result.setMsg("查到了");
				result.setData(list);
			}
			return result;
		}

		//更新商品的促销信息
		@Override
		public ResultModel updateDiscount(String gid, double discountprice) {
			ResultModel result = new ResultModel();
			boolean boo = gdao.updateDiscountPrice(gid, discountprice);
			if(boo){
				result.setCode("0");
				result.setMsg("修改成功");
				result.setData(null);
			}else{
				result.setCode("1");
				result.setMsg("修改失败");
				result.setData(null);
			}
			return result;
		}

		@Override
		public ResultModel updateDiscountSecond(String gid, double goodsprice) {
			ResultModel result = new ResultModel();
			boolean boo = gdao.updateDiscountPrice(gid, goodsprice);
			if(boo){
				result.setCode("0");
				result.setMsg("修改成功");
				result.setData(null);
			}else{
				result.setCode("1");
				result.setMsg("修改失败");
				result.setData(null);
			}
			return result;
		}
	
	
	
	
	
}

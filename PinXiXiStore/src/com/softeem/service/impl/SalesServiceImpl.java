package com.softeem.service.impl;

import java.util.List;

import com.softeem.dao.SalesDAO;
import com.softeem.model.Goods;
import com.softeem.model.ResultModel;
import com.softeem.model.Sales;
import com.softeem.service.SalesService;

public class SalesServiceImpl implements SalesService {
	private SalesDAO sdao=new SalesDAO();
	
	/**
	 * 查询促销表中所有商品的方法
	 */
	@Override
	public ResultModel salesall() {
		ResultModel result=new ResultModel();
		//调用dao层的方法
		List<Goods> list=sdao.selectsalesAll();
		if(list.isEmpty()){
			//查询失败
			result.setCode("1");
			result.setMsg("查询失败");
			result.setData(null);
		}else{
			//查询成功
			result.setCode("0");
			result.setMsg("查询成功");
			result.setData(list);
		}
		return result;
	}
	
	
	//向表中添加数据
		@Override
		public ResultModel insert(Goods goods, int salesdr, String id) {
			boolean boo = sdao.check(goods);
			ResultModel result = new ResultModel();
			if(boo){
			//已存在促销表中
				result.setCode("2");
				result.setMsg("已存在促销表中");
				result.setData(null);
			}
			//不在促销表中,则可以向表中添加
			Sales sales = new Sales();
			sales.setId(id);
			sales.setDiscountprice(goods.getDiscountprice());
			sales.setGoodsprice(goods.getGoodsprice());
			sales.setGoodsname(goods.getGoodsname());
			sales.setGid(goods.getGid());
			sales.setSalesdr(salesdr);
			boo = sdao.insertSales(sales);
			if(boo){
				result.setCode("0");
				result.setMsg("插入成功");
				result.setData(sales);
			}else{
				result.setCode("1");
				result.setMsg("插入失败");
				result.setData(null);
			}
			return result;
		}
		//从表中删除数据
		@Override
		public ResultModel delete(String gid) {
			ResultModel result = new ResultModel();
			boolean boo = sdao.deleteSales(gid);
			if(boo){
				result.setCode("0");
				result.setMsg("删除成功");
				result.setData(null);
			}else{
				result.setCode("1");
				result.setMsg("删除失败");
				result.setData(null);
			}
			return result;
		}
	

}

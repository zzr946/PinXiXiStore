package com.softeem.service.impl;

import java.util.List;

import com.softeem.dao.CartDAO;
import com.softeem.model.Cart;
import com.softeem.model.CartItem;
import com.softeem.model.ResultModel;
import com.softeem.service.CartService;
import com.softeem.tools.Tools;

public class CartServiceImpl implements CartService {
	private CartDAO cdao=new CartDAO();
	
	/**
	 * 用户添加到购物车的方法
	 */
	@Override
	public ResultModel addGoodsToCart(String uid, String gid, double price, int count) {
		ResultModel result=new ResultModel();
		//调用dao层的方法 先判断本次添加的商品以前添加过没
		Cart c=cdao.selectCart(uid, gid);
		if(c==null){
			//还没添加过
			//直接将该商品添加进去
			//获取id
			String id=Tools.getUUID();
			//将信息存入cart对象中
			Cart cart=new Cart();
			cart.setId(id);
			cart.setUid(uid);
			cart.setGidlist(gid);
			cart.setGoodstotallist(count);
			cart.setGoodsprice(price);
			cart.setReserved1(0);
			//调用dao层的方法，将商品存到购物车表中
			boolean boo=cdao.inserttoCart(cart);
			if(boo){
				//添加成功
				result.setCode("0");
				result.setMsg("添加成功");
				result.setData(cart);
			}else{
				//添加失败
				result.setCode("1");
				result.setMsg("添加失败");
				result.setData(null);
			}
		}else{
			//已经添加过
			//获取以前添加的该商品的数量
			int oldcount=c.getGoodstotallist();
			//调用dao层的方法将该商品的数量增加
			boolean boo=cdao.updateTototal(uid, gid, (oldcount+count));
			if(boo){
				//添加成功
				result.setCode("0");
				result.setMsg("添加成功");
				result.setData(null);
			}else{
				//添加失败
				result.setCode("1");
				result.setMsg("添加失败");
				result.setData(null);
			}
		}
		
		return result;
	}

	/**
	 * 查询用户购物车里的所有商品的方法
	 */
	@Override
	public ResultModel cartAll(String uid) {
		ResultModel result=new ResultModel();
		//调用dao层的方法查询
		List<CartItem> list=cdao.selectcartAll(uid);
		if(list.isEmpty()){
			//没有数据
			result.setCode("1");
			result.setMsg("没有数据");
			result.setData(null);
		}else{
			//有数据
			result.setCode("0");
			result.setMsg("查询成功");
			result.setData(list);
		}
		return result;
	}

}

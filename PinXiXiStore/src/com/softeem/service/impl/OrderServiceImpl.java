package com.softeem.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.softeem.dao.OrderDAO;
import com.softeem.model.Goods;
import com.softeem.model.Order;
import com.softeem.model.PayItem;
import com.softeem.model.ResultModel;
import com.softeem.model.Return;
import com.softeem.model.Uaddress;
import com.softeem.model.User;
import com.softeem.service.OrderService;
import com.softeem.tools.Tools;

public class OrderServiceImpl implements OrderService {
	private OrderDAO odao=new OrderDAO();
	
	/**
	 * 用户提交订单的方法
	 */
	@Override
	public ResultModel subOrder(String ordernumber,String uid, String gid,int goodstotal) {
		ResultModel result=new ResultModel();
		//生成订单id
		String id=Tools.getUUID();
		//生成订单编号
		//String ordernumber=Tools.getNum();
		//根据uid查询购买人的姓名
		String name=odao.selectUserByid(uid).getName();
		//根据商品id查询商家id
		String mid=odao.selectgoodsByid(gid).getMid();
		//将信息装入订单对象中
		Order order=new Order();
		order.setId(id);
		order.setOrdernumber(ordernumber);
		order.setGidlist(gid);
		order.setGoodstotallist(goodstotal);
		order.setName(name);
		order.setMid(mid);
		order.setPaydr(1);//未支付状态
		System.out.println(order);
		//将商品项添加到订单表中
		boolean boo=odao.insertOrder(order);
		if(boo){
			//添加成功
			//将用户购物车里的商品删除
			odao.UpdateCartdr(uid, gid);
			result.setCode("0");
			result.setMsg("添加成功");
			result.setData(null);
		}else{
			//添加失败
			result.setCode("1");
			result.setMsg("添加失败");
			result.setData(null);
		}
		return result;
	}

	
	
	/**
	 * 查询所有待支付的商品项目
	 */
	@Override
	public ResultModel payGoods(String ordernumber) {
		ResultModel result=new ResultModel();
		//调用dao层的方法
		List<PayItem> list=odao.selectPayitemAll(ordernumber);
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

	
	
	/**
	 * 用户确认订单  将订单改为已付款状态(待处理)付款成功
	 */
	public ResultModel success(String uid,String addressid,String ordernumber,int money){
		ResultModel result=new ResultModel();
		//调用dao层 获取获收货人姓名
		String uadname=odao.selectaddrByid(addressid).getUadname();
		//将信息存入到order对象中
		Order order=new Order();
		order.setUadname(uadname);
		order.setAccount(money);
		order.setUadid(addressid);
		//dao层修改订单状态
		boolean boo=odao.updateOrder(order);
		if(boo){
			//修改成功
			result.setCode("0");
			result.setMsg("修改成功");
			result.setData(null);
		}else{
			//修改失败
			result.setCode("1");
			result.setMsg("修改失败");
			result.setData(null);
		}
		return result;
	}
	
	
	/**
	 * 根据地址id查询地址信息
	 */
	public ResultModel addressinfo(String addressid){
		ResultModel result=new ResultModel();
		Uaddress address=odao.selectaddrByid(addressid);
		if(address!=null){
			//查询成功
			result.setCode("0");
			result.setMsg("查询成功");
			result.setData(address);
		}else{
			//查询失败
			result.setCode("1");
			result.setMsg("查询失败");
			result.setData(null);
		}
		return result;
	}
	
	/**
	 * 根据用户id查询用户所有订单信息
	 * @param uid 用户id
	 * @return 返回所有订单信息
	 */
	public ResultModel orderAll(String uid){
		ResultModel result=new ResultModel();
		//调用dao层查询用户信息
		User user=odao.selectUserByid(uid);
		//查询用户的所有订单
		List<Order> list=odao.selectOrderAll(user);
		if(list.isEmpty()){
			//查询失败
			result.setCode("1");
			result.setMsg("查询失败");
			result.setData(null);
		}else{
			//查询成功
			//根据商品id查询商品
			List<PayItem> listitem=new ArrayList<PayItem>();
			for (Order order : list) {
				Goods goods=odao.selectgoodsByid(order.getGidlist());
				listitem.add(new PayItem(goods.getGid(), goods.getGoodsimage(), goods.getGoodsname(), goods.getReserved1(), goods.getDiscountprice(), order.getGoodstotallist(), goods.getDiscountprice()*order.getGoodstotallist()));
			}
			result.setCode("0");
			result.setMsg("查询成功");
			result.setData(listitem);
		}
		return result;
	}


	
	/**
	 * 根据用户id查询用户所有待支付的订单
	 */
	@Override
	public ResultModel awaitOrder(String uid) {
		ResultModel result=new ResultModel();
		//调用dao层查询用户信息
		User user=odao.selectUserByid(uid);
		//调用service层的方法，查询用户所有待支付订单
		List<Order> list=odao.selectawaitOrder(user);
		if(list.isEmpty()){
			//查询失败
			result.setCode("1");
			result.setMsg("查询失败");
			result.setData(null);
		}else{
			//查询成功
			//根据商品id查询商品
			List<PayItem> listitem=new ArrayList<PayItem>();
			for (Order order : list) {
				Goods goods=odao.selectgoodsByid(order.getGidlist());
				listitem.add(new PayItem(goods.getGid(), goods.getGoodsimage(), goods.getGoodsname(), goods.getReserved1(), goods.getDiscountprice(), order.getGoodstotallist(), goods.getDiscountprice()*order.getGoodstotallist()));
			}
			result.setCode("0");
			result.setMsg("查询成功");
			result.setData(listitem);
		}
		return result;
	}


	/**
	 * 根据用户id查询用户所有待发货的订单
	 */
	@Override
	public ResultModel awaitSendOrder(String uid) {
		ResultModel result=new ResultModel();
		//调用dao层查询用户信息
		User user=odao.selectUserByid(uid);
		//调用service层的方法，查询用户所有待支付订单
		List<Order> list=odao.selectawaitSend(user);
		if(list.isEmpty()){
			//查询失败
			result.setCode("1");
			result.setMsg("查询失败");
			result.setData(null);
		}else{
			//查询成功
			//根据商品id查询商品
			List<PayItem> listitem=new ArrayList<PayItem>();
			for (Order order : list) {
				Goods goods=odao.selectgoodsByid(order.getGidlist());
				listitem.add(new PayItem(goods.getGid(), goods.getGoodsimage(), goods.getGoodsname(), goods.getReserved1(), goods.getDiscountprice(), order.getGoodstotallist(), goods.getDiscountprice()*order.getGoodstotallist()));
			}
			result.setCode("0");
			result.setMsg("查询成功");
			result.setData(listitem);
		}
		
		return result;
	}


	/**
	 * 根据用户id查看待收货的订单
	 */
	@Override
	public ResultModel awaittakeOrder(String uid) {
		ResultModel result=new ResultModel();
		//调用dao层查询用户信息
				User user=odao.selectUserByid(uid);
				//调用service层的方法，查询用户所有待支付订单
				List<Order> list=odao.selectawaittake(user);
				if(list.isEmpty()){
					//查询失败
					result.setCode("1");
					result.setMsg("查询失败");
					result.setData(null);
				}else{
					//查询成功
					//根据商品id查询商品
					List<PayItem> listitem=new ArrayList<PayItem>();
					for (Order order : list) {
						Goods goods=odao.selectgoodsByid(order.getGidlist());
						listitem.add(new PayItem(goods.getGid(), goods.getGoodsimage(), goods.getGoodsname(), goods.getReserved1(), goods.getDiscountprice(), order.getGoodstotallist(), goods.getDiscountprice()*order.getGoodstotallist()));
					}
					result.setCode("0");
					result.setMsg("查询成功");
					result.setData(listitem);
				}
		return result;
	}



	/**
	 * 根据用户id查看待评价订单
	 */
	@Override
	public ResultModel awaitevaluate(String uid) {
		ResultModel result=new ResultModel();
		//调用dao层查询用户信息
				User user=odao.selectUserByid(uid);
				//调用service层的方法，查询用户所有待支付订单
				List<Order> list=odao.selectawaitevaluate(user);
				if(list.isEmpty()){
					//查询失败
					result.setCode("1");
					result.setMsg("查询失败");
					result.setData(null);
				}else{
					//查询成功
					//根据商品id查询商品
					List<PayItem> listitem=new ArrayList<PayItem>();
					for (Order order : list) {
						Goods goods=odao.selectgoodsByid(order.getGidlist());
						listitem.add(new PayItem(goods.getGid(), goods.getGoodsimage(), goods.getGoodsname(), goods.getReserved1(), goods.getDiscountprice(), order.getGoodstotallist(), goods.getDiscountprice()*order.getGoodstotallist()));
					}
					result.setCode("0");
					result.setMsg("查询成功");
					result.setData(listitem);
				}
		return result;
	}


	/**
	 * 用户在查看订单页面一键支付(获取地址信息)
	 */
	@Override
	public ResultModel aKeyPayAddress(String uid, String gid) {
		ResultModel result=new ResultModel();
		//根据用户id查看用户所有地址信息
		List<Uaddress> list=odao.selectaddressAll(uid);
		result.setCode("0");
		result.setMsg("查询成功");
		result.setData(list);
		return result;
	}


	/**
	 * 用户查看订单时一键支付(获取商品信息)
	 */
	@Override
	public ResultModel aKeyPayGoods(String uid, String gid) {
		ResultModel result=new ResultModel();
		//根据商品id查看用户所有商品信息
		Goods goods=odao.selectgoodsByid(gid);
		result.setData(goods);
		result.setCode("0");
		result.setMsg("查询成功");
		return result;
	}



	/**
	 * 用户收货的方法
	 */
	@Override
	public ResultModel takeOrder(String uid, String gid) {
		ResultModel result=new ResultModel();
		//根据用户id查询用户所有信息
		User user=odao.selectUser(uid);
		//根据商品id查询商品信息
		Goods goods=odao.selectgoodsByid(gid);
		//将信息装入order中
		Order order=new Order();
		order.setGidlist(gid);
		order.setName(user.getName());
		//调用dao层的方法 收货
		boolean boo=odao.updatetakeOrder(order);
		if(boo){
			//收货成功
			result.setCode("0");
			result.setMsg("收货成功");
			result.setData(null);
		}else{
			//收货失败
			result.setCode("1");
			result.setMsg("收货失败");
			result.setData(null);
		}
		return result;
	}
	
	/**
	 * 用户退货的方法
	 * @param uid 用户id
	 * @param gid sp 需要退货的商品id
	 * @return
	 */
	public ResultModel returnmoney(String uid,String gid){
		ResultModel result=new ResultModel();
		Goods goods=odao.selectgoodsByid(gid);
		result.setCode("0");
		result.setMsg("查询成功");
		result.setData(goods);
		return result;
	}



	/**
	 * 用户提交退货单的方法
	 */
	@Override
	public ResultModel subreturn(String uid, String goodsnumber, String returntype, String returncause,
			String money, String returndetails) {
		ResultModel result=new ResultModel();
		//获取id
		String id=Tools.getUUID();
		//将退货金额转为double 类型
		double returnmoney=Double.valueOf(money);
		//将退款信息装入Return对象中
		Return ret=new Return(id,goodsnumber, returntype, returncause, returndetails, 1, returnmoney, uid, null, null, null);
		//调用dao层的方法
		boolean boo=odao.insertreturnGoods(ret);
		if(boo){
			//退货申请提交成功
			result.setCode("0");
			result.setMsg("退货申请提交成功");
			result.setData(null);
		}else{
			//退货申请提交失败
			result.setCode("1");
			result.setMsg("退货申请提交失败");
			result.setData(null);
		}
		return result;
	}
	
	//加载订单
		@Override
		public ResultModel findByPage(int page, String mid) {
			List<Order> list = odao.selectByOPage(page, mid);
			ResultModel result = new ResultModel();
			if(list.isEmpty()){
				//没查找到数据
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
		
		//发货
		@Override
		public ResultModel fahuo(String id) {
			ResultModel result = new ResultModel();
			boolean boo = odao.fahuo(id);
			if(boo){
				//发货成功
				result.setCode("0");
				result.setMsg("发货成功");
				result.setData(null);
			}else{
				//发货失败
				result.setCode("1");
				result.setMsg("发货失败");
				result.setData(null);
			}
			return result;
		}

	
	
}

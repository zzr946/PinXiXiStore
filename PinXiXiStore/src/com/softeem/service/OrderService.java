package com.softeem.service;

import com.softeem.model.ResultModel;

public interface OrderService {

	// 用户提交订单的方法(从购物车到支付页面)
	public ResultModel subOrder(String ordernumber, String uid, String gid, int goodstotal);

	// 支付界面查询待支付的商品项
	public ResultModel payGoods(String ordernumber);

	// 用户确认订单 将订单改为已付款状态(待处理)付款成功
	public ResultModel success(String uid, String addressid, String ordernumber, int money);

	// 根据地址id查询地址信息
	public ResultModel addressinfo(String addressid);

	// 根据用户id查询用户所有订单信息
	public ResultModel orderAll(String uid);

	// 根据用户id查询用户所有待支付的订单
	public ResultModel awaitOrder(String uid);

	// 根据用户id查询用户所有未发货的订单
	public ResultModel awaitSendOrder(String uid);

	// 根据用户id查看待收货的订单
	public ResultModel awaittakeOrder(String uid);

	// 根据用户id查看待评价的订单
	public ResultModel awaitevaluate(String uid);

	// 用户查看订单时一键支付(获取地址信息)
	public ResultModel aKeyPayAddress(String uid, String gid);

	// 用户查看订单时一键支付(获取商品信息)
	public ResultModel aKeyPayGoods(String uid, String gid);

	// 用户收货的方法
	public ResultModel takeOrder(String uid, String gid);

	// 用户退货
	public ResultModel returnmoney(String uid, String gid);

	// 用户提交退货信息
	public ResultModel subreturn(String uid, String goodsnumber, String returntype, String returncause,
			String returnmoney, String returndetails);

	// 加载指定页的订单
	public ResultModel findByPage(int page, String mid);

	// 发货
	public ResultModel fahuo(String id);

}

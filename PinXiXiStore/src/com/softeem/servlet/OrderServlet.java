package com.softeem.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RespectBinding;

import com.mysql.fabric.Response;
import com.softeem.model.ResultModel;
import com.softeem.service.OrderService;
import com.softeem.service.impl.OrderServiceImpl;
import com.softeem.service.impl.UaddressServiceImpl;
import com.softeem.tools.Tools;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String ordernumber;

	public OrderServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求头、响应头编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//System.out.println("请求进来了");
		// 获取请求
		String method = request.getParameter("method");
		System.out.println(method);
		switch (method) {
		case "paypage":
			//购物车提交订单，到支付界面
			payPage(request,response);
			break;
		case "success":
			//点击付款 ，提交订单
			success(request,response);
			break;
		case "find":
			//用户查看订单
			orderAll(request,response);
			break;
		case "order_pay":
			//查看订单时一键支付
			//aKetPay(request,response);
			break;
		case "take":
			//用户收货的方法
			take(request,response);
			break;
		case "returnmoney":
			//用户退货的方法
			returnGoods(request,response);
			break;
		case "subreturn":
			//用户提交退货单
			subreturn(request,response);
			
			break;
		default:
			break;
		}
		
		
	}
	

	//用户提交退货单
	private void subreturn(HttpServletRequest request, HttpServletResponse response) {
		//获取用户id
		String uid=request.getParameter("uid");
		//获取商品id
		String goodsnumber=request.getParameter("goodsnumber");
		//获取退货类型
		String returntype=request.getParameter("returntype");
		//获取退款原因
		String returncause=request.getParameter("returncause");
		//获取退款金额
		String returnmoney=request.getParameter("returnmoney");
		//退款说明
		String returndetails=request.getParameter("returndetails");
		
		//System.out.println(uid);//退款人id
		//System.out.println(goodsnumber);//商品id
		//System.out.println(returntype);//退款类型
		//System.out.println(returncause);//退款原因
		//System.out.println(returnmoney);//退款金额
		//System.out.println(returndetails);//退款说明
		//调用service层，将退货信息插入到退货表中
		OrderService oservice=new OrderServiceImpl();
		ResultModel result=oservice.subreturn(uid, goodsnumber, returntype, returncause, returnmoney, returndetails);
		if("0".equals(result.getCode())){
			//退货申请提交成功
			//重定向到订单详情userorder.jsp页面
			try {
				response.sendRedirect("userorder.jsp?result=0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			//退货申请提交失败
			//重定向到订单详情userorder.jsp页面
			try {
				response.sendRedirect("userorder.jsp?result=1");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	//查看订单时一键支付
	/*private void aKetPay(HttpServletRequest request, HttpServletResponse response) {
		//获取用户id
		String uid=request.getParameter("uid");
		//获取商品id
		String gid=request.getParameter("gid");
		//获取总金额
		String money=request.getParameter("money");
		//调用service层的方法 
		OrderService oservice=new OrderServiceImpl();
		
		//查询收货地址信息
		ResultModel resultaddress=oservice.aKeyPayAddress(uid, gid);
		//查询商品信息
		ResultModel resultgoods=oservice.aKeyPayGoods(uid, gid);
		//将结果存入session中
		HttpSession session=request.getSession();
		session.setAttribute("pay_address", resultaddress.getData());
		session.setAttribute("pay_goods", resultgoods.getData());
		session.setAttribute("pay_sum_money", money);
		//重定向到付款界面
		try {
			response.sendRedirect("pay.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

	//用户退货的方法
	private void returnGoods(HttpServletRequest request, HttpServletResponse response) {
		//获取用户id
		String uid=request.getParameter("uid");
		//获取商品id
		String gid=request.getParameter("gid");
		System.out.println(uid);
		System.out.println(gid);
		//调用dervice层的方法
		OrderService oservice=new OrderServiceImpl();
		ResultModel result=oservice.returnmoney(uid, gid);//查询需要退货的商品
		//System.out.println(result.getCode());
		//System.out.println(result.getMsg());
		//System.out.println(result.getData());
		//将商品存入session中
		HttpSession session=request.getSession();
		session.setAttribute("returngoodsmoney", result.getData());
		//跳转到退货界面
		try {
			response.sendRedirect("userrefund.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	//用户收货的方法
	private void take(HttpServletRequest request, HttpServletResponse response) {
		String uid=request.getParameter("uid");
		String gid=request.getParameter("gid");
		//System.out.println(uid);
		//System.out.println(gid);
		//调用service层的方法  收货
		OrderService oservice=new OrderServiceImpl();
		ResultModel result=oservice.takeOrder(uid, gid);
		if("0".equals(result.getCode())){
			//收货成功
//			try {
//				response.sendRedirect("userorder.jsp?err=0");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			orderAll(request,response);
		}else{
			//收货失败
			try {
				response.sendRedirect("userorder.jsp?err=1");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//用户查看所有订单
	private void orderAll(HttpServletRequest request, HttpServletResponse response) {
		//获取当前登录的用户id
		String uid=request.getParameter("uid");
		System.out.println(uid);
		OrderService oservice=new OrderServiceImpl();
		//调用service层,根据用户id查询用户的所有订单信息
		ResultModel result=oservice.orderAll(uid);
		//调用service层,根据用户id查询用户待支付订单信息
		ResultModel awaitresult=oservice.awaitOrder(uid);
		//调用service层,根据用户id查询用户待发货订单信息
		ResultModel awaitsendresult=oservice.awaitSendOrder(uid);
		//调用service层,根据用户id查询用户待收货订单信息
		ResultModel awaittakeresult=oservice.awaittakeOrder(uid);
		//调用service层，根据用户id查询待评价的订单信息
		ResultModel awaitevaluateresult=oservice.awaitevaluate(uid);
		//System.out.println(result.getCode());
		//System.out.println(result.getMsg());
		//System.out.println(result.getData());
		HttpSession session=request.getSession();
		if("0".equals(result.getCode())){
			//查询成功
			//将所有订单存入session中
			session.setAttribute("showOrderAll", result.getData());
			//将待付款订单存入session中
			session.setAttribute("showawaitOrder", awaitresult.getData());
			//将待发货订单存入session中
			session.setAttribute("showawaitSendOrder", awaitsendresult.getData());
			//将待收货订单存入session中
			session.setAttribute("showawaittake", awaittakeresult.getData());
			//将待评价订单存入session中
			session.setAttribute("showawaitevaluate", awaitevaluateresult.getData());
			
			//重定向到订单管理页面(userorder页面)
			try {
				response.sendRedirect("userorder.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			//查询失败
			//重定向到订单管理页面(userspace页面)
			try {
				response.sendRedirect("userorder.jsp?err=0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	//提交订单 到付款成功界面
	private void success(HttpServletRequest request, HttpServletResponse response) {
		//获取用户id
		String uid=request.getParameter("uid");
		//获取选中的地址id
		String addressid=request.getParameter("addressid");
		//获取订单id
		//System.out.println(ordernumber);
		//获取订单总金额
		String m=request.getParameter("money");
		int money=Integer.valueOf(m);
		//调用service层改变订单状态
		OrderService oservice=new OrderServiceImpl();
		ResultModel result=oservice.success(uid,addressid,ordernumber,money);
		if("0".equals(result.getCode())){
			//购买成功
			//查询收货地址信息
			ResultModel addresult=oservice.addressinfo(addressid);
			//将地址信息存入session中
			HttpSession session=request.getSession();
			session.setAttribute("successaddress", addresult.getData());
			//将总价存入session中
			session.setAttribute("countmoney", money);
			//跳转到购买成功页面
			try {
				response.sendRedirect("usersuccess.jsp?money="+money);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
			//购买失败
			try {
				response.sendRedirect("pay.jsp?err=0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	//从购物车跳转到支付界面
	private void payPage(HttpServletRequest request, HttpServletResponse response) {
		//获取用户id
		String uid=request.getParameter("uid");
		//获取商品id串
		String gid=request.getParameter("gids");
		//获取商品数量串
		String count=request.getParameter("goodscount");
		//获取总金额
		String money=request.getParameter("money");
		//System.out.println(uid);
		//System.out.println(gids);
		//System.out.println(goodscount);
		//获取商品id数组
		String[] gids=gid.split("~");
		//System.out.println(Arrays.toString(gids));
		//获取商品数量串
		String[] goodscount=count.split("~");
		//System.out.println(Arrays.toString(goodscount));
		//生成订单编号
		ordernumber=Tools.getNum();
		//调用service层的方法
		OrderService oservice=new OrderServiceImpl();
		//装结果的集合
		List<ResultModel> list=new ArrayList<ResultModel>();
		for(int i=0;i<gids.length;i++){
			//获取对应的商品id及商品数量
			String goodsid=gids[i];//每一个商品id
			String goodsnum=goodscount[i];
			int goodstotal=Integer.valueOf(goodsnum);//每件商品的购买数量
			//调用service层的方法生成订单
			ResultModel result=oservice.subOrder(ordernumber,uid, goodsid, goodstotal);
			//System.out.println(result.getCode());
			//System.out.println(result.getMsg());
			//System.out.println(result.getData());
			//将结果添加到结果集合中
			list.add(result);
		}
		//遍历结果集合，如果有一个code值为1则失败
		boolean boo=true;
		for (ResultModel result : list) {
			if("1".equals(result.getCode())){
				boo=false;
			}
		}
		HttpSession session=request.getSession();
		if(boo){
			//订单生成成功
			//System.out.println("订单生成成功");
			//查询用户所有地址信息
			ResultModel addressresult=new UaddressServiceImpl().useraddressAll(uid);
			//查询用户待支付的商品信息
			ResultModel goodsresult=oservice.payGoods(ordernumber);
			//将地址信息存入到session中
			session.setAttribute("pay_address", addressresult.getData());
			//将待支付商品信息添加到session中
			session.setAttribute("pay_goods", goodsresult.getData());
			//将总金额存入到session中
			session.setAttribute("pay_sum_money", money);
			//跳转到支付界面
			try {
				response.sendRedirect("pay.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
			//订单生成失败
			//System.out.println("订单生成失败");
		}
		
		
		
		
		
		
	}

}

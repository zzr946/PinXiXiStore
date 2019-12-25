package com.softeem.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softeem.model.ResultModel;
import com.softeem.service.CartService;
import com.softeem.service.impl.CartServiceImpl;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartServlet() {
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
		//System.out.println(method);
		switch (method) {
		case "addtocart":
			// 将商品添加到购物车中
			addtoCart(request, response);
			break;
		case "cart_no1":
			// 进入购物车是加载购物车里的商品
			loadCart(request, response);
			break;
		default:
			break;
		}

	}

	// 进入购物车是加载购物车里的商品
	private void loadCart(HttpServletRequest request, HttpServletResponse response) {
		//获取大年登录的用户id
		String uid=request.getParameter("uid");
		//System.out.println(uid);
		// 调用service层的方法查询购物车中的所有商品信息
		CartService cservice=new CartServiceImpl();
		ResultModel result=cservice.cartAll(uid);
		//System.out.println(result.getCode());
		//System.out.println(result.getMsg());
		//System.out.println(result.getData());
		//将结果存在request中，并跳转到购物车界面
		//HttpSession session=request.getSession();
		request.setAttribute("cartAll", result);
		//重定向到购物车界面
		try {
			request.getRequestDispatcher("shopcart.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

	// 将该商品添加到该用户的购物车中
	private void addtoCart(HttpServletRequest request, HttpServletResponse response) {
		// 获取当前用户id
		String uid = request.getParameter("uid");
		// 获取商品id
		String gid = request.getParameter("gid");
		// 获取商品单价
		String goodsprice = request.getParameter("price");
		double price = Double.valueOf(goodsprice);
		// 获取添加商品的数量
		String goodscount = request.getParameter("count");
		int count = Integer.valueOf(goodscount);
		// 调用service层的方法，将数据写入到购物车表中
		CartService cservice = new CartServiceImpl();
		ResultModel result = cservice.addGoodsToCart(uid, gid, price, count);
		// 将结果存入request并返回到当前界面(details.jsp)
		request.setAttribute("addCartResult", result);
		try {
			// 请求转发到商品详情(details.jsp)界面
			request.getRequestDispatcher("details.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}

}

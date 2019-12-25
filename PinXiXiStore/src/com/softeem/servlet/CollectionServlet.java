package com.softeem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softeem.model.ResultModel;
import com.softeem.service.CollectionService;
import com.softeem.service.impl.CollectionServiceImpl;

/**
 * 与用户收藏商品相关的操作
 * 
 * @author 赵志然
 *
 */
@WebServlet("/collection")
public class CollectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CollectionServlet() {
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
		System.out.println("请求进来了");
		// 获取请求
		String method = request.getParameter("method");
		System.out.println(method);
		switch (method) {
		case "collect":
			collectionGoods(request,response);
			break;
		case "selectAll":
			//查询所有收藏商品
			collGoodsAll(request,response);
			break;
		default:
			break;
		}
		
	}
	
	//查看所有收藏商品
	private void collGoodsAll(HttpServletRequest request, HttpServletResponse response) {
		//获取用户id
		String uid=request.getParameter("uid");
		//调用service查询所有收藏商品
		CollectionService cservice=new CollectionServiceImpl();
		ResultModel result=cservice.selectcollGoods(uid);
		//将result存入session中
		HttpSession session=request.getSession();
		session.setAttribute("collectGoodsAll", result.getData());
		//重定向到收藏界面(collection界面)
		try {
			response.sendRedirect("usercollection.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//用户收藏商品的方法
	private void collectionGoods(HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("************");
		//获取用户id
		String uid=request.getParameter("uid");
		//获取收藏的商品id
		String gid=request.getParameter("gid");
		//System.out.println("uid:"+uid);
		//System.out.println("收藏gid:"+gid);
		//调用service层的方法
		CollectionService collService=new CollectionServiceImpl();
		ResultModel result=collService.addcollGoods(uid, gid);
		//将结果装入到request中返回到前台
		request.setAttribute("collresult", result);
		//请求转发跳转到商品详情界面
		try {
			request.getRequestDispatcher("details.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	

}

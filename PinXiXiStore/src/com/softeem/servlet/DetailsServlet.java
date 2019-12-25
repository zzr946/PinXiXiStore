package com.softeem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softeem.model.ResultModel;
import com.softeem.service.CommelistService;
import com.softeem.service.DetailsService;
import com.softeem.service.impl.CommelistServiceImpl;
import com.softeem.service.impl.DetailsServiceImpl;

@WebServlet("/details")
public class DetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DetailsServlet() {
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
		System.out.println("goods请求进来了");
		// 获取请求
		String method = request.getParameter("method");
		System.out.println(method);
		switch (method) {
		case "goodsdetails":
			//点击单个商品进入到商品详情界面
			goodsdetails(request,response);
			break;

		default:
			break;
		}
	}

	
	//点击单个商品进入到商品详情界面
	private void goodsdetails(HttpServletRequest request, HttpServletResponse response) {
		//获取商品id
		String gid=request.getParameter("gid");
		System.out.println("商品id:"+gid);
		//调用service层的方法，根据商品id查询该商品的详细信息
		DetailsService dservice=new DetailsServiceImpl();
		ResultModel result=dservice.queryOne(gid);
		if("0".equals(result.getCode())){
			//查询成功
			
			//查询该商品的所有评论
			CommelistService cservice=new CommelistServiceImpl();
			ResultModel commresult=cservice.commentAll(gid);
			
			//将商品对象存入session中，并跳转到商品详情界面
			HttpSession session=request.getSession();
			//将评论集合存入session
			session.setAttribute("commlist", commresult.getData());
			session.setAttribute("goodsdetails", result.getData());
			//跳转到商品详情页面(details.jsp)
			try {
				response.sendRedirect("details.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
			//查询失败
			//继续停留于在原界面
			request.setAttribute("detailserror", "没查询到该商品的详细信息");
			try {
				request.getRequestDispatcher("commodity.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			};
			
		}
		
		
	}

}

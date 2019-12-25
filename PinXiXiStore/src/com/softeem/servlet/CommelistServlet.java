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
import com.softeem.service.impl.CommelistServiceImpl;

@WebServlet("/comment")
public class CommelistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommelistServlet() {
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
		// System.out.println("请求进来了");
		// 获取请求
		String method = request.getParameter("method");
		// System.out.println(method);
		switch (method) {
		case "subcomment":
			//用户跳转到评论商品
			subcomment(request,response);
			break;
		case "publishcomment":
			//用户提交评论
			publishComment(request,response);
			break;
		default:
			break;
		}
		
		
	}

	//用户提交评论
	private void publishComment(HttpServletRequest request, HttpServletResponse response) {
		//获取用户id
		String uid=request.getParameter("uid");
		//获取商品id
		String gid=request.getParameter("gid");
		//获取评论内容
		String content=request.getParameter("content");
		HttpSession session=request.getSession();
		//System.out.println(uid);
		//System.out.println(gid);
		//System.out.println(content);
		//调用service层将信息存入评论表
		CommelistService cservice=new CommelistServiceImpl();
		ResultModel result=cservice.saveComment(uid, gid, content);
		if("0".equals(result.getCode())){
			//存入成功
			//将修改后的待评价订单存入session中
			session.setAttribute("showawaitevaluate", result.getData());
			//重定向到订单管理页面
			try {
				response.sendRedirect("userorder.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
			//存入失败
			try {
				response.sendRedirect("userorder.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//用户跳转到评论商品
	private void subcomment(HttpServletRequest request, HttpServletResponse response) {
		String uid=request.getParameter("uid");
		String gid=request.getParameter("gid");
		//System.out.println(uid);
		//System.out.println(gid);
		//调用service层查询商品信息
		CommelistService cservice=new CommelistServiceImpl();
		ResultModel result=cservice.goodsInfo(gid);
		HttpSession session=request.getSession();
		if("0".equals(result.getCode())){
			//成功
			//将商品信息存入session中
			session.setAttribute("goodscomment", result.getData());
			//重定向到评价页面
			try {
				response.sendRedirect("usercommentlist.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			//失败
		}
		
		
		
	}

}

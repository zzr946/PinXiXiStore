package com.softeem.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softeem.model.ResultModel;
import com.softeem.service.SalesService;
import com.softeem.service.impl.SalesServiceImpl;

/**
 * 促销商品相关
 * 
 * @author 赵志然
 *
 */
@WebServlet("/sales")
public class SalesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SalesServlet() {
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
		case "salesall":
			//点击今日特惠时初始化界面
			salesAll(request,response);
			break;

		default:
			break;
		}
		
		
	}

	//点击今日特惠初始化界面
	private void salesAll(HttpServletRequest request, HttpServletResponse response) {
		//调用sevice层查询促销表中的所有商品
		SalesService sservice=new SalesServiceImpl();
		ResultModel result=sservice.salesall();
		//将结果存到request中，跳转到‘今日特惠’界面
		request.setAttribute("today", result);
		try {
			request.getRequestDispatcher("buytoday.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}

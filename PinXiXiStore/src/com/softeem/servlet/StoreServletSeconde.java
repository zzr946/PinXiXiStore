package com.softeem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softeem.model.ResultModel;
import com.softeem.service.StoreService;
import com.softeem.service.impl.StoreServiceImpl;


@WebServlet("/merchant/storesecond")
public class StoreServletSeconde extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public StoreServletSeconde() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置请求头编码
		request.setCharacterEncoding("utf-8");
		//设置响应头编码
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		System.out.println(method+"1");
		switch(method){
		case "findStore":
			findStore(request,response);
			break;
		case "update":
			update(request,response);
		default:
			break;
		}
		
		
	}

	//修改店铺信息
	private void update(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");
		String newname = request.getParameter("newname");
		String newstoreinfo = request.getParameter("newstoreinfo");
		String newaddress = request.getParameter("newaddress");
		StoreService ss = new StoreServiceImpl();
		ResultModel result = ss.sendUpdate(mid, newname, newstoreinfo, newaddress);
		if("0".equals(result.getCode())){
			//提交修改的店铺信息成功
			request.setAttribute("sendUpdate", 1);
			request.getRequestDispatcher("updateStore.jsp").forward(request, response);
		}else{
			//提交修改的店铺信息失败
			request.setAttribute("sendUpdate", 2);
			request.getRequestDispatcher("updateStore.jsp").forward(request, response);
		}
	}


	//查找店铺
	private void findStore(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String msid = request.getParameter("msid");
		StoreService ss = new StoreServiceImpl();
		ResultModel result = ss.selectExist(msid);
		if("-1".equals(result.getCode())){
			//店铺未创建
			request.setAttribute("result", result);
			request.getRequestDispatcher("main1.jsp").forward(request, response);
		}else if("3".equals(result.getCode())){
			//店铺还未激活
			request.setAttribute("result", result);
			request.getRequestDispatcher("main1.jsp").forward(request, response);
		}else if("1".equals(result.getCode())){
			//店铺已激活
			HttpSession session = request.getSession();
			session.setAttribute("store", result.getData());
			request.getRequestDispatcher("main2.jsp").forward(request, response);
		}else if("2".equals(result.getCode())){
			//店铺封禁中
			request.setAttribute("result", result);
			request.getRequestDispatcher("main1.jsp").forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

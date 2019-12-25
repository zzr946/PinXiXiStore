package com.softeem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softeem.model.Merchant;
import com.softeem.model.ResultModel;
import com.softeem.service.SortService;
import com.softeem.service.impl.SortServiceImpl;


@WebServlet("/merchant/sort")
public class SortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SortServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置请求头编码
		request.setCharacterEncoding("utf-8");
		//设置响应头编码
		response.setCharacterEncoding("utf-8");
		System.out.println("测试进来了");
		//获取请求的是哪种操作
		String method = request.getParameter("method");
		switch(method){
		case "add":
			add(request,response);
			break;
		case "findByPage":
			findByPage(request,response);
			break;
		case "deletesort":
			deleteBySid(request,response);
			break;
		case "updateSort":
			updateSort(request,response);
		default:
			break;
		}
	}

//	修改类别
	private void updateSort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取要修改成的类别名
		String sortname2 = request.getParameter("sortname2");
		//获取要修改成的类别简介
		String sortinfo = request.getParameter("sortinfo");
		//获取原来的类别名
		String sortname1 = request.getParameter("sortname1");
		SortService ss = new SortServiceImpl();
		ResultModel result = ss.updateSort(sortname1, sortname2, sortinfo);
		if("0".equals(result.getCode())){
			//修改成功
			request.setAttribute("updatesort", 1);
			request.getRequestDispatcher("sort.jsp").forward(request, response);
		}else{
			//修改失败
			request.setAttribute("updatesort", 2);
			request.getRequestDispatcher("sort.jsp").forward(request, response);
		}
		
	}

	//删除指定分类
	private void deleteBySid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter("sid");
		SortService ss = new SortServiceImpl();
		ResultModel result = ss.deleteBySid(sid);
		if("0".equals(result.getCode())){
			//删除成功
			request.setAttribute("deletesort", 1);
			//重写加载类别列表
			request.getRequestDispatcher("sort.jsp").forward(request, response);
		}else if("1".equals(result.getCode())){
			//删除失败
			request.setAttribute("deletesort", 2);
			//重写加载类别列表
			request.getRequestDispatcher("sort.jsp").forward(request, response);
		}
	}

	//分页查找数据
	private void findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取商家id
		String mid = request.getParameter("mid");
		//获取页数(需要将返回过来的查询页数转换为整型)
		int page = Integer.parseInt(request.getParameter("page"));
		System.out.println(page);
		System.out.println(mid);
		SortService ss = new SortServiceImpl();
		ResultModel result = ss.selectByPage(mid, page);
		System.out.println(result.getData());
		if("0".equals(result.getCode())){
			//查到了
			request.setAttribute("merchantid", mid);
			HttpSession session = request.getSession();
			session.setAttribute("sortList", result.getData());
			session.setAttribute("pageNow", page);
			request.getRequestDispatcher("sort.jsp").forward(request, response);
		}else{
			//没有查询到数据
			request.setAttribute("selectsort", 1);
//			request.setAttribute("pageNow", page-1);
			if(page == 1){
				request.setAttribute("pageNow", page);
			}else{
				request.setAttribute("pageNow", page-1);
			}
			request.getRequestDispatcher("sort.jsp").forward(request, response);
		}
		
	}

	//添加分类操作
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		//获取表单数据
		String sortname = request.getParameter("sortname");
		String sortinfo = request.getParameter("sortinfo");
		String merchantid = request.getParameter("mid");
		System.out.println(merchantid);
		SortService ss = new SortServiceImpl();
		ResultModel result = ss.addSort(sortname, sortinfo, merchantid);
		if("0".equals(result.getCode())){
			//添加成功
			//获取session对象
			HttpSession session = request.getSession();
			//将用户信息存入session对象中
			session.setAttribute("sort", result.getData());
			//添加成功
			request.setAttribute("addsort", 1);//添加成功
			//添加成功进入首页面，并将登录的用户信息传递到首页面
			try {
				request.getRequestDispatcher("createSort.jsp").forward(request, response);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if("1".equals(result.getCode())){
			//添加失败
			//请求转发到首页页面
			try {
				request.setAttribute("addsort", 2);//添加失败
				request.getRequestDispatcher("createSort.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if("2".equals(result.getCode())){
			//商品类别已存在
			//请求转发到首页页面
			try {
				request.setAttribute("addsort", 3);//商品类别已存在
				request.getRequestDispatcher("createSort.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}

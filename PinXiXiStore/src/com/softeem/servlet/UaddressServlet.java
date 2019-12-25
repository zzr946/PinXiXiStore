package com.softeem.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.alibaba.fastjson.JSON;
import com.softeem.model.ResultModel;
import com.softeem.service.UaddressService;
import com.softeem.service.impl.UaddressServiceImpl;

@WebServlet("/uaddress")
public class UaddressServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UaddressServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置请求头、响应头编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
//		System.out.println("请求进来了");
		//获取请求
		String method=request.getParameter("method");
//		System.out.println(method);
		switch (method) {
		case "addaddress":
		//用户添加收货地址
		addaddress(request,response);
		break;
		case "defaultaddress":
			//设置为默认地址
			setdefaultaddress(request,response);
			break;
		case "addressAll":
			//查询用户所有地址信息
			addressAll(request,response);
			break;
		case "deladdress":
			//删除地址的方法
			delAddress(request,response);
			break;
		default:
			break;
		}
	}
	
	//用户删除地址的方法
	private void delAddress(HttpServletRequest request, HttpServletResponse response) {
		//获取用户id
		String uid=request.getParameter("uid");
		//获取需要删除的用户id
		String addressid=request.getParameter("addressid");
		System.out.println("用户id"+uid);
		System.out.println("删除的地址id"+addressid);
		//调用service层的方法删除地址
		UaddressService uaddressservice=new UaddressServiceImpl();
		ResultModel result=uaddressservice.delteAddress(uid, addressid);
		System.out.println(result.getCode());
		System.out.println(result.getMsg());
		System.out.println(result.getData());
		//将结果存入到session中
		HttpSession session=request.getSession();
		session.setAttribute("useraddress", result.getData());
		//重定向到地址管理界面
		try {
			response.sendRedirect("useraddress.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	//查询用户的所有收货地址的方法
	private void addressAll(HttpServletRequest request, HttpServletResponse response) {
		//获取用户id
		String uid=request.getParameter("uid");
		//调用service层的方法
		UaddressService uservice=new UaddressServiceImpl();
		ResultModel result=uservice.useraddressAll(uid);
		//将地址存入道session中
		HttpSession session=request.getSession();
		session.setAttribute("useraddress", result.getData());
//		request.setAttribute("useraddress", result.getData());
		try {
			request.getRequestDispatcher("useraddress.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

	//设置为默认地址的方法
	private void setdefaultaddress(HttpServletRequest request, HttpServletResponse response) {
		//获取用户id
		String uid=request.getParameter("uid");
		String uadid=request.getParameter("uadid");
//		System.out.println(uid);
//		System.out.println(uadid);
		//调用service层的方法
		UaddressService uaddressservice=new UaddressServiceImpl();
		ResultModel result=uaddressservice.setdefaultaddress(uid, uadid);
		if("0".equals(result.getCode())){
			//设置成功
		}else{
			//设置失败
		}
		
	}

	//用户添加收货地址的方法
	private void addaddress(HttpServletRequest request, HttpServletResponse response) {
		//获取添加收货地址的用户id
		String uid=request.getParameter("uid");
		//获取收收货人姓名
		String uadname=request.getParameter("uadname");
		//获取收货人电话
		String mobile=request.getParameter("mobile");
		//获取省
		String province=request.getParameter("province");
		//获取市
		String city=request.getParameter("city");
		//获取区
		String area=request.getParameter("area");
		//获取详细地址
		String detailaddress=request.getParameter("detailaddress");
//		System.out.println("当前登录的用户"+uid);
//		System.out.println("收货人姓名"+uadname);
//		System.out.println("收货人手机号"+mobile);
//		System.out.println("省"+province);
//		System.out.println("市"+city);
//		System.out.println("区"+area);
//		System.out.println("详细地址"+detailaddress);
		//调用service层的方法
		UaddressService uaddressservice=new UaddressServiceImpl();
		ResultModel result=uaddressservice.useraddaddress(uid, uadname, mobile, province, city, area, detailaddress);
		if("0".equals(result.getCode())){
			//添加成功
			//将该用户所有地址信息存入到session中
			request.getSession().setAttribute("useraddress", result.getData());
			try {
				response.sendRedirect("useraddress.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if("1".equals(result.getCode())){
			//添加失败
			request.setAttribute("useraddresserror", "添加失败");
			try {
				request.getRequestDispatcher("useraddress.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			};
		}
		if("2".equals(result.getCode())){
			//上限，不能再添加
			request.setAttribute("noaddaddress", "添加上限了,不能在添加");
			try {
				request.getRequestDispatcher("useraddress.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			};
		}
		
		
		
	}
	
	

}

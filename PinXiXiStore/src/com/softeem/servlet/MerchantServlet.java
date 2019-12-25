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
import com.softeem.service.MerchantService;
import com.softeem.service.impl.MerchantServiceImpl;
import com.softeem.tools.PhoneCode;


@WebServlet("/merchant/merchant")
public class MerchantServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private static String code;
   
    public MerchantServlet() {
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
		//获取请求的是哪种操作
		String method = request.getParameter("method");
		switch(method){
		case "reg":
			reg(request,response);
			break;
		case "phonecode":
			phonecode(request,response);
			break;
		case "login":
			login(request,response);
			break;
		case "logout":
			logout(request,response);
			break;
		case "forgetpwd":
			forgetpwd(request,response);
			break;
		default:
			break;
		}
	}


	//找回密码
	private void forgetpwd(HttpServletRequest request, HttpServletResponse response) {
		String mphone = request.getParameter("reg_phone");
		String mpassword = request.getParameter("new_password");
		System.out.println(code);
		String yzm = request.getParameter("reg_yzm");
		System.out.println(yzm);
		System.out.println(yzm.equals(code));
		if(!yzm.equals(code)){
			request.setAttribute("checkerror", 1);
			try {
				request.getRequestDispatcher("forgetpwd.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			MerchantService mservice = new MerchantServiceImpl();
			ResultModel result = mservice.updatePWD(mphone, mpassword);
			if("0".equals(result.getCode())){
				try {
					//注册成功，页面跳转到登录页面(重定向跳转)
					response.sendRedirect("merchant_login.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				try {
					//注册失败回到注册页面(请求转发)
					request.getRequestDispatcher("forgetpwd.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	//退出清空数据
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		try {
			response.sendRedirect("merchant_login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	//登录的方法
	private void login(HttpServletRequest request, HttpServletResponse response) {
		//获取表单数据
		String phone = request.getParameter("login_phone");
		String password = request.getParameter("login_password");
		//将请求数据传递给Servicce业务逻辑层
		MerchantService mservice = new MerchantServiceImpl();
		ResultModel result = mservice.login(phone, password);
		//判断响应结果
				if("1".equals(result.getCode())){
					try {
						//将错误信息存入request
						request.setAttribute("logerror", result.getMsg());
						//用户名或密码错误
						request.getRequestDispatcher("merchant_login.jsp").forward(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					//获取session对象
					HttpSession session = request.getSession();
					//将用户信息存入session对象中
					session.setAttribute("merchant", result.getData());
					//登录成功进入首页面，并将登录的用户信息传递到首页面
					try {
						response.sendRedirect("main1.jsp");
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
	}


	//发送验证码
	private void phonecode(HttpServletRequest request, HttpServletResponse response) {
		String phone = request.getParameter("phoneNumber");
		code = PhoneCode.getCode();
		PhoneCode.sendCode(phone, code);
	}


	private void reg(HttpServletRequest request, HttpServletResponse response) {
		String mphone = request.getParameter("reg_phone");
		String mpassword = request.getParameter("reg_password");
		String yzm = request.getParameter("reg_yzm");
		System.out.println(code);
		System.out.println(yzm);
		System.out.println(yzm.equals(code));
		if(!yzm.equals(code)){
			request.setAttribute("checkerror", 1);
			try {
				request.getRequestDispatcher("merchant_reg.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			MerchantService mservice = new MerchantServiceImpl();
			ResultModel result = mservice.reg(mphone, mpassword);
			if("0".equals(result.getCode())){
				try {
					//注册成功，页面跳转到登录页面(重定向跳转)
					response.sendRedirect("merchant_login.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				try {
					//注册失败回到注册页面(请求转发)
					request.getRequestDispatcher("merchant_reg.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		
		
	}

}

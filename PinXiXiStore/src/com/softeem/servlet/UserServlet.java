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
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.softeem.dao.UAddressDAO;
import com.softeem.model.ResultModel;
import com.softeem.model.User;
import com.softeem.service.UaddressService;
import com.softeem.service.UserService;
import com.softeem.service.impl.UaddressServiceImpl;
import com.softeem.service.impl.UserServiceImpl;
import com.softeem.tools.PhoneCode;

@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private String code="";//用于存储随机验证码
       
    public UserServlet() {
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
		case "phonecode":
			//用户注册短信验证
			reg_phonecheck(request,response);
			break;
		case "userreg":
			//用户提交注册信息
			userReg(request,response);
			break;
		case "user_loginBypwd":
			//手机号密码登录
			loginBypwd(request,response);
			break;
		case "user_loginByPhone":
			//手机验证码登录
			loginByPhone(request,response);
			break;
		case "logout":
			//安全退出的方法
			userlogout(request,response);
			break;
		case "changepwd":
			//用户找回密码
			changePwd(request,response);
			break;
		case "changeuserinfo":
			//用户修改个人资料
			changeUserinfo(request,response);
		case "modifypwd":
			//用户根据密码修改密码
			modifypwd(request,response);
			break;
		default:
			break;
		}
		
	}

	

	//用户根据密码修改密码
	private void modifypwd(HttpServletRequest request, HttpServletResponse response) {
		//获取需要修改密码的用户id
		String uid=request.getParameter("uid");
		//获取原密码
		String oldpwd=request.getParameter("oldpwd");
		//获取新密码
		String newpwd=request.getParameter("newpwd");
//		System.out.println(uid+"--"+oldpwd+"--"+newpwd);
		//调用dao层的方法
		UserService uservice=new UserServiceImpl();
		ResultModel result=uservice.modifypwdtopwd(uid,oldpwd,newpwd);
		if("2".equals(result.getCode())){
			//原密码输入错误
			//将result转为json字符串格式
			String jstr=JSON.toJSONString(result);
			//设置编码
			response.setCharacterEncoding("utf-8");
			//将json字符串发送到前台
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println(jstr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if("0".equals(result.getCode())){
			//修改成功
			//清空session
			request.getSession().invalidate();
			//将result转为json字符串格式
			String jstr=JSON.toJSONString(result);
			//设置编码
			response.setCharacterEncoding("utf-8");
			//将json字符串发送到前台
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println(jstr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if("1".equals(result.getCode())){
			//修改失败
			//将result转为json字符串格式
			String jstr=JSON.toJSONString(result);
			//设置编码
			response.setCharacterEncoding("utf-8");
			//将json字符串发送到前台
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println(jstr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	//用户修改个人信息的方法
	private void changeUserinfo(HttpServletRequest request, HttpServletResponse response) {
		
	}

	//用户找回密码的方法
	private void changePwd(HttpServletRequest request, HttpServletResponse response) {
		//获取手机号
		String phone=request.getParameter("phone");
		//获取用户输入的验证码
		String check_code=request.getParameter("pnum");
		if(check_code.equals(code)){
			//如果验证码正确
			//获取用户输入的新密码
			String newpassword=request.getParameter("password");
			//调用service层的方法
			UserService uservice=new UserServiceImpl();
			ResultModel result=uservice.modifyPassword(phone, newpassword);
			System.out.println(result.getCode());
			System.out.println(result.getMsg());
			System.out.println(result.getData());
			
				if("0".equals(result.getCode())){
					//密码修改成功，跳转到登录页面
					request.setAttribute("changeok", "密码修改成功");
//					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
				if("1".equals(result.getCode())){
					//修改失败，继续跳转到修改页面
					request.setAttribute("changeerror", "密码修改失败");
					
				}
				if("2".equals(result.getCode())){
					//该手机号未注册，跳转到注册页面
					request.setAttribute("notreg", "该手机号未注册");
//					request.getRequestDispatcher("userreg.jsp").forward(request, response);
				}
			try {
				request.getRequestDispatcher("changepwd.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}else{
			//验证码输入错误跳转到当前页面
			request.setAttribute("changecodereeor", "验证码输入错误");
			try {
				request.getRequestDispatcher("changepwd.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	//用户退出的方法
	private void userlogout(HttpServletRequest request, HttpServletResponse response) {
		//清空用户信息，实际上就是清空session中的内容
//		request.getSession().removeAttribute("student");
//		request.getSession().removeAttribute("daily");
		request.getSession().invalidate();
		try {
			//回到主界面
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	//用户手机验证码登录
	private void loginByPhone(HttpServletRequest request, HttpServletResponse response) {
		//获取手机号
		String phone=request.getParameter("phone");
		//获取用户输入的验证码
		String check_code=request.getParameter("pnum");
		if(code.equals(check_code)){
			//验证码输入正确
			//调用service层的方法判断此手机号是否存在
			UserService uservice=new UserServiceImpl();
			ResultModel result=uservice.loginByphonecode(phone);
			try {
				if("0".equals(result.getCode())){
					//登录成功
					//调用UaddressDao层获取用户的所有收货地址
					UaddressService addressservice=new UaddressServiceImpl();
					ResultModel addressresult=addressservice.useraddressAll(((User) (result.getData())).getUid());
					System.out.println(addressresult.getData());
					
					//跳转到主界面，将结果存入到session中
					HttpSession session=request.getSession();
					session.setAttribute("userlogin", result.getData());
					//将用户地址存入session中
					session.setAttribute("useraddress", addressresult.getData());
					response.sendRedirect("index.jsp");//页面跳转
				}else{
					//登录失败
					//跳转到当前登录界面，将结果存到request中
					request.setAttribute("loginBypwderror", result.getMsg());
//				response.sendRedirect("login.jsp");
					request.getRequestDispatcher("loginbyphone.jsp").forward(request, response);
				}
			} catch (IOException e) {
				e.printStackTrace();
			} catch (ServletException e) {
				e.printStackTrace();
			}
			
		}else{
			// 验证码输入错误,跳转到当前登录页面
			request.setAttribute("codereeor", "验证码输入错误");
			try {
				request.getRequestDispatcher("loginbyphone.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	//用户手机密码登录
	private void loginBypwd(HttpServletRequest request, HttpServletResponse response) {
		//获取手机号
		String phone=request.getParameter("phone");
		//获取密码
		String password=request.getParameter("password");
//		System.out.println(phone);
//		System.out.println(password);
		//调用service层的方法
		UserService uservice=new UserServiceImpl();
		ResultModel result=uservice.loginBypwd(phone, password);
//		System.out.println(result.getCode());
//		System.out.println(result.getMsg());
//		System.out.println(result.getData());
		
		try {
			if("0".equals(result.getCode())){
				//登录成功跳转到主界面，将结果存入到session中
				//获取该用户下的所有收货地址
				//调用UaddressDao层获取用户的所有收货地址
				UaddressService addressservice=new UaddressServiceImpl();
				ResultModel addressresult=addressservice.useraddressAll(((User) (result.getData())).getUid());
				System.out.println(addressresult.getData());
				HttpSession session=request.getSession();
				//将用户地址存入session中
				session.setAttribute("useraddress", addressresult.getData());
				session.setAttribute("userlogin", result.getData());
				response.sendRedirect("index.jsp");//页面跳转
			}else{
				//登录失败(用户名或密码错误)，到转到当前登录界面，将结果存到request中
				request.setAttribute("loginBypwderror", result.getMsg());
//				response.sendRedirect("login.jsp");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		
	}

	//用户注册信息提交
	private void userReg(HttpServletRequest request, HttpServletResponse response) {
		//获取手机号码
		String phone=request.getParameter("phone");
		//获取用户输入的验证码
		String check_code=request.getParameter("pnum");
//		System.out.println(check_code);
//		System.out.println(code);
		//获取密码
		String password=request.getParameter("password");
		if(check_code.equals(code)){//如果验证码输入正确
			//调用service层的方法
			UserService uservice=new UserServiceImpl();
			ResultModel result=uservice.reg(phone, password);
//			System.out.println(result.getCode());
//			System.out.println(result.getMsg());
//			System.out.println(result.getData());
			//将用户结果存入到session中
			HttpSession session=request.getSession();
			session.setAttribute("user",result);
			try {
				if("0".equals(result.getCode())){
					//注册成功，跳转到登录界面
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}else{
					//注册失败(包括手机号被占用和插入数据错误)，跳转到当前界面
					request.getRequestDispatcher("userreg.jsp").forward(request, response);
				}
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			// 验证码输入错误,跳转到当前页面
			request.setAttribute("codeerror", "验证码输入错误");
			try {
				request.getRequestDispatcher("userreg.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//手机短信验证的方法
	private void reg_phonecheck(HttpServletRequest request, HttpServletResponse response) {
		//获取手机号码
		String phone=request.getParameter("phoneNumber");
		//获取随机验证码
		code=PhoneCode.getCode();
		//发送随机验证码
		PhoneCode.sendCode(phone, code);
	}

}

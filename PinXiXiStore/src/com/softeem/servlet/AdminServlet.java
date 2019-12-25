package com.softeem.servlet;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.softeem.model.ResultModel;
import com.softeem.service.AdminService;
import com.softeem.service.impl.AdminServiceImpl;

@WebServlet("/admin1/admin")
public class AdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
     

	
    public AdminServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	
	
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	    //设置响应头
	    request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
//		接受请求
	    String method = request.getParameter("method");
		switch (method) {
		case "Admin_loginByadminaccount":
			AdminloginByadminaccount(request,response);
			break;
		case "Admin_changeByadminaccount":
			AdminchangeByadminaccount(request,response);
			break;
		case "Admin_checkByadminname":
			AdmincheckByadminname(request,response);
			break;
		case "Admin_checkByadminname1":
			AdmincheckByadminname1(request,response);
			break;
		case "Admin_checkByadmindr":
			AdmincheckByadmindr(request,response);
			break;
		case "Admin_changedrbysid":
			Adminchangedrbysid(request,response);
			break;
		case "Admin_checkBycheckdr":
			AdmincheckBycheckdr(request,response);
			break;
		case "Admin_changecheckdr":
			Adminchangecheckdr(request,response);
			break;
		case "Admin_changecheckdr1":
			Adminchangecheckdr1(request,response);
			break;	
		case "Admin_banstoredr":
			Adminbanstoredr(request,response);
			break;
		case "Admin_checkAllstore":
			AdmincheckAllstore(request,response);
			break;
		case "Admin_checkAllalterstore":
			//显示所有需要审核批改的店铺
			checkAllalterstore(request,response);
			break;
		case "Admin_checkAllalterstore1":
			//checkjoin.jsp中的上一页下一页的方法
			checkAllalterstore1(request,response);
			break;
//		case "Admin_changephoto":
//			//上传的方法
//			changephoto(request,response);
//			break;
		case "Admin_exit":
			//安全退出的方法
			exit(request,response);
			break;
			
		case "Admin_checkReportMerchant":
			//显示所有举报次数过多的商家
			adminCheckReportMerchant(request,response);
			break;
			
		case "Admin_banTheMerchant":
			//封禁商家
			adminBanTheMerchant(request,response);
			break;
			
		case "Admin_checkReportGoods":
			//显示所有举报次数过多的商品
			adminCheckReportGoods(request,response);
			break;
			
		case "Admin_downTheGoods":
			//下架商品
			adminDownTheGoods(request,response);
			break;
				
		default:
			break;
		}
		
		
		
		
		
		
	}

	//下架商品
		private void adminDownTheGoods(HttpServletRequest request, HttpServletResponse response) {
			String gid=request.getParameter("gid");
			AdminService adminservice = new AdminServiceImpl();
			ResultModel result = adminservice.downReportGoods(gid);
			System.out.println(result.getCode());
			if("0".equals(result.getCode())){


				adminCheckReportGoods(request,response);
				try {
					response.sendRedirect("page/downthegoods.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				request.setAttribute("downthegoodserror", result.getMsg());
				try {
					request.getRequestDispatcher("page/downthegoods.jsp").forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	//显示所有不合格商品
		private void adminCheckReportGoods(HttpServletRequest request, HttpServletResponse response) {
			//调用service层判断是否成功
			
			AdminService adminservice = new AdminServiceImpl();
			ResultModel result = adminservice.checkReportGoods();
			if("0".equals(result.getCode())){
				//显示状态为0带审批的商品
				HttpSession session = request.getSession();
				session.setAttribute("report1", result.getData());
				
			
			}else{
				//还没有待审批的商品
				request.setAttribute("reporterror1", result.getMsg());
			}
			
		}
	//封禁商家
		private void adminBanTheMerchant(HttpServletRequest request, HttpServletResponse response) {
		   
		    String mid=request.getParameter("mid");
			AdminService adminservice = new AdminServiceImpl();
			ResultModel result = adminservice.banReportMerchant(mid);
			System.out.println(result.getCode());
			if("0".equals(result.getCode())){


				adminCheckReportMerchant(request,response);
				try {
					response.sendRedirect("page/banthemerchant.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				request.setAttribute("banthemerchanterror", result.getMsg());
				try {
					request.getRequestDispatcher("page/banthemerchant.jsp").forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
			
		}
	//显示所有举报次数过多的商家
		private void adminCheckReportMerchant(HttpServletRequest request, HttpServletResponse response) {
			//调用service层判断是否成功
					AdminService adminservice = new AdminServiceImpl();
					ResultModel result = adminservice.checkReportMerchant();
					if("0".equals(result.getCode())){
						//显示状态为0带审批的店铺
						HttpSession session = request.getSession();
						session.setAttribute("report", result.getData());
						
					
					}else{
						//还没有待审批的店铺
						request.setAttribute("reporterror", result.getMsg());
					}
				}
			

	
	

	private void checkAllalterstore1(HttpServletRequest request, HttpServletResponse response) {
		String page1 = request.getParameter("page");
		System.out.println(page1);
		int page=0;
		if(page1==null){
			page = 1;
		}else{
			page = Integer.parseInt(page1);
		}
		System.out.println(page);
		//调用service层判断是否成功
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.checkAllstore(page);
		System.out.println(result.getCode());
		if("0".equals(result.getCode())){
			//当前页数显示状态为0带审批的店铺
			HttpSession session = request.getSession();
			session.setAttribute("thispage", page);
			session.setAttribute("checkAllstore", result.getData());
			session.setAttribute("thispage1",2);
			try {
				response.sendRedirect("page/checkjoin.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			HttpSession session = request.getSession();
			session.setAttribute("thispage1",1);
			//当前页数还没有待审批的店铺
			request.setAttribute("checkAllstoreerror", result.getMsg());
			try {
				response.sendRedirect("page/checkjoin.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
	}

	private void Adminchangecheckdr1(HttpServletRequest request, HttpServletResponse response) {
		String alertid = request.getParameter("alertid");
		System.out.println(alertid);
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.changecheckdr1(alertid);
		System.out.println(result.getCode());
		if("0".equals(result.getCode())){
//			AdmincheckBycheckdr(request,response);
			checkAllalterstore(request,response);
			try {
				response.sendRedirect("page/checkchange.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			request.setAttribute("admincheckchange1error", result.getMsg());
			try {
				request.getRequestDispatcher("page/checkchange.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	// 显示所有的需要审核修改的商家
	private void checkAllalterstore(HttpServletRequest request, HttpServletResponse response) {
		//调用service层判断是否成功
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.checkAllalterstore();
		if("0".equals(result.getCode())){
			//显示状态为0带审批的店铺
			HttpSession session = request.getSession();
			session.setAttribute("checkAllalterstore", result.getData());
		}else{
			//还没有待审批的店铺
			request.setAttribute("checkAllalterstoreerror", result.getMsg());
		}
	}

	//审核时进行封禁
	private void Adminbanstoredr(HttpServletRequest request, HttpServletResponse response) {
		String sid = request.getParameter("sid"); 
		//调用service层判断是否成功
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.banstoredr(sid);
		if("0".equals(result.getCode())){
			System.out.println("更改成功了");
			AdmincheckByadmindr(request,response);
				try {
					//重新判断商家审核的状态
					AdmincheckAllstore(request,response);
					response.sendRedirect("page/checkjoin.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
		}else{
			//查看失败
			request.setAttribute("adminbanjoinerror", result.getMsg());
			try {
				request.getRequestDispatcher("checkjoin.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	private void AdmincheckAllstore(HttpServletRequest request, HttpServletResponse response) {
				String page1 = request.getParameter("page");
				int page=0;
				if(page1==null){
					page = 1;
				}else{
					page = Integer.parseInt(page1);
				}
				System.out.println(page);
				//调用service层判断是否成功
				AdminService adminservice = new AdminServiceImpl();
				ResultModel result = adminservice.checkAllstore(page);
				if("0".equals(result.getCode())){
					//显示状态为0带审批的店铺
					HttpSession session = request.getSession();
					session.setAttribute("thispage", page);
					session.setAttribute("checkAllstore", result.getData());
				}else{
					//还没有待审批的店铺
					request.setAttribute("checkAllstoreerror", result.getMsg());
				}
	}

	/*
	 * 管理员进行商家信息修改审核
	 */
	private void Adminchangecheckdr(HttpServletRequest request, HttpServletResponse response) {
		String alertid = request.getParameter("alertid");
		System.out.println(alertid);
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.changecheckdr(alertid);
		System.out.println(result.getCode());
		if("0".equals(result.getCode())){
			//审核通过
			//调用修改店铺表信息的方法
			ResultModel result1 = adminservice.adminUpdateStore(alertid);
			checkAllalterstore(request,response);
			try {
				response.sendRedirect("page/checkchange.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			request.setAttribute("admincheckchangeerror", result.getMsg());
			try {
				request.getRequestDispatcher("page/checkchange.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	//系统管理员根据修改信息审核状态查询到所有需要修改信息的商店
	private void AdmincheckBycheckdr(HttpServletRequest request, HttpServletResponse response) {
		//调用service层判断是否成功
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.checkbycheckdr(0);
		if("0".equals(result.getCode())){
			//显示状态为0带审批的店铺
			HttpSession session = request.getSession();
			session.setAttribute("checkcheckdr", result.getData());
		}else{
			//还没有待审批的店铺
			request.setAttribute("checkcheckdrerror", result.getMsg());
		}
	}
	
	
	//入驻审核
	private void Adminchangedrbysid(HttpServletRequest request, HttpServletResponse response){
		String sid = request.getParameter("sid"); 
		//調用service层是否成功
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.changestoredr(sid);
		if("0".equals(result.getCode())){
			System.out.println("更改成功了");
			AdmincheckByadmindr(request,response);
				try {
					//重新判断商家审核的状态
					AdmincheckAllstore(request,response);
					response.sendRedirect("page/checkjoin.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
		}else{
			//查看失败
			request.setAttribute("admincheckjoinerror", result.getMsg());
			try {
				request.getRequestDispatcher("page/checkjoin.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	 
	
	
	
	
			


	private void AdmincheckByadmindr(HttpServletRequest request, HttpServletResponse response) {
		//调用service层判断是否成功
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.checkbystoredr(0);
		System.out.println(result.getCode());
		if("0".equals(result.getCode())){
			//显示状态为0带审批的店铺
			HttpSession session = request.getSession();
			session.setAttribute("checkchange", result.getData());
//			try {
//				response.sendRedirect("checkchange.jsp");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}else{
			//还没有待审批的店铺
			request.setAttribute("checkchangeerror", result.getMsg());
//			try {
//				request.getRequestDispatcher("checkchange.jsp").forward(request, response);
//			} catch (ServletException | IOException e) {
//				e.printStackTrace();
//			}
		}
		
		
	}

	private void AdmincheckByadminname1(HttpServletRequest request, HttpServletResponse response) {
		//获取密码问题
		String answer = request.getParameter("answer");
		//调用service层判断是否成功
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.checkbyquestion1(answer);
		if("0".equals(result.getCode())){
			System.out.println("答案输入正确");
			//查看成功,跳到密保界面，并将结果集保存在session中
			HttpSession session = request.getSession();
			session.setAttribute("admincheck1", result.getData());
			System.out.println(session.toString());
			try {
				response.sendRedirect("forgetpassword2.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			//查看失败
			request.setAttribute("checkByadminnameerror1", result.getMsg());
			try {
				request.getRequestDispatcher("forgetpassword1.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}

		
		
		
		
	/*
	 * 密保界面
	 */
	private void AdmincheckByadminname(HttpServletRequest request, HttpServletResponse response) {
		//获取系统管理员姓名
		String  adminname = request.getParameter("adminname");
		//调用service层的方法判断是否成功
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.checkbyquestion(adminname);
		if("0".equals(result.getCode())){
			System.out.println("查看成功了");
			//查看成功,跳到密保界面，并将结果集保存在session中
			HttpSession session = request.getSession();
			session.setAttribute("admincheck", result.getData());
			System.out.println(session.toString());
			try {
				response.sendRedirect("forgetpassword1.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			//查看失败
			request.setAttribute("checkByadminnameerror", result.getMsg());
			try {
				request.getRequestDispatcher("forgetpassword.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	
	
	
	

	/*
	 * 系统管理员修改密码
	 */
	private void AdminchangeByadminaccount(HttpServletRequest request, HttpServletResponse response) {
		//获取管理员账号
		String adminaccount = request.getParameter("adminaccount");
		//获取管理员密码
		String adminpassword = request.getParameter("adminpassword");
		
		//调用service层的方法判断是否修改成功
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.changebyadminaccount(adminaccount, adminpassword);
		if("0".equals(result.getCode())){
			//修改成功，跳转到主界面，并将结果集保存在session中
			HttpSession session = request.getSession();
			session.setAttribute("adminchange",result.getData());
			try {
				//页面跳转
				response.sendRedirect("changePwd.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}                                                                                                                     
		}else{
			//修改失败
			//跳转到当前登录界面，将结果存到request中
			request.setAttribute("changeByadminaccounterror", result.getMsg());
			try {
				request.getRequestDispatcher("changePwd.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * 系统管理员登录
	 */
	private void AdminloginByadminaccount(HttpServletRequest request, HttpServletResponse response) {
		//获取管理员账号
		String adminaccount = request.getParameter("adminaccount");
		//获取管理员密码
		String adminpassword = request.getParameter("adminpassword");
		//调用service层的方法判断是否存在
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.loadbyadminaccount(adminaccount, adminpassword);
		if("0".equals(result.getCode())){
			//登录成功，跳转到主界面，并将结果集保存在session中
			HttpSession session = request.getSession();
			session.setAttribute("adminlogin",result.getData());
			AdmincheckAllstore(request, response);
			checkAllalterstore(request, response);
			adminCheckReportMerchant(request,response);
			adminCheckReportGoods(request,response);
			try {
				//页面跳转
				response.sendRedirect("adminmain.jsp");
			} catch (IOException e) {
				e.printStackTrace(); 
			}
		}else{
			//登录失败
			//跳转到当前登录界面，将结果存到request中
			request.setAttribute("loginByadminaccounterror", result.getMsg());
			
			try {
				request.getRequestDispatcher("adminlogin.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	private void exit(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		HttpSession session = request.getSession();
		session.invalidate();
		response.sendRedirect("adminlogin.jsp");
		}
	
	
}

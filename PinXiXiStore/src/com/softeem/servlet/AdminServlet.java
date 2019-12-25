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
	    //������Ӧͷ
	    request.setCharacterEncoding("utf-8");
	    response.setCharacterEncoding("utf-8");
//		��������
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
			//��ʾ������Ҫ������ĵĵ���
			checkAllalterstore(request,response);
			break;
		case "Admin_checkAllalterstore1":
			//checkjoin.jsp�е���һҳ��һҳ�ķ���
			checkAllalterstore1(request,response);
			break;
//		case "Admin_changephoto":
//			//�ϴ��ķ���
//			changephoto(request,response);
//			break;
		case "Admin_exit":
			//��ȫ�˳��ķ���
			exit(request,response);
			break;
			
		case "Admin_checkReportMerchant":
			//��ʾ���оٱ�����������̼�
			adminCheckReportMerchant(request,response);
			break;
			
		case "Admin_banTheMerchant":
			//����̼�
			adminBanTheMerchant(request,response);
			break;
			
		case "Admin_checkReportGoods":
			//��ʾ���оٱ������������Ʒ
			adminCheckReportGoods(request,response);
			break;
			
		case "Admin_downTheGoods":
			//�¼���Ʒ
			adminDownTheGoods(request,response);
			break;
				
		default:
			break;
		}
		
		
		
		
		
		
	}

	//�¼���Ʒ
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
	//��ʾ���в��ϸ���Ʒ
		private void adminCheckReportGoods(HttpServletRequest request, HttpServletResponse response) {
			//����service���ж��Ƿ�ɹ�
			
			AdminService adminservice = new AdminServiceImpl();
			ResultModel result = adminservice.checkReportGoods();
			if("0".equals(result.getCode())){
				//��ʾ״̬Ϊ0����������Ʒ
				HttpSession session = request.getSession();
				session.setAttribute("report1", result.getData());
				
			
			}else{
				//��û�д���������Ʒ
				request.setAttribute("reporterror1", result.getMsg());
			}
			
		}
	//����̼�
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
	//��ʾ���оٱ�����������̼�
		private void adminCheckReportMerchant(HttpServletRequest request, HttpServletResponse response) {
			//����service���ж��Ƿ�ɹ�
					AdminService adminservice = new AdminServiceImpl();
					ResultModel result = adminservice.checkReportMerchant();
					if("0".equals(result.getCode())){
						//��ʾ״̬Ϊ0�������ĵ���
						HttpSession session = request.getSession();
						session.setAttribute("report", result.getData());
						
					
					}else{
						//��û�д������ĵ���
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
		//����service���ж��Ƿ�ɹ�
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.checkAllstore(page);
		System.out.println(result.getCode());
		if("0".equals(result.getCode())){
			//��ǰҳ����ʾ״̬Ϊ0�������ĵ���
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
			//��ǰҳ����û�д������ĵ���
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

	// ��ʾ���е���Ҫ����޸ĵ��̼�
	private void checkAllalterstore(HttpServletRequest request, HttpServletResponse response) {
		//����service���ж��Ƿ�ɹ�
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.checkAllalterstore();
		if("0".equals(result.getCode())){
			//��ʾ״̬Ϊ0�������ĵ���
			HttpSession session = request.getSession();
			session.setAttribute("checkAllalterstore", result.getData());
		}else{
			//��û�д������ĵ���
			request.setAttribute("checkAllalterstoreerror", result.getMsg());
		}
	}

	//���ʱ���з��
	private void Adminbanstoredr(HttpServletRequest request, HttpServletResponse response) {
		String sid = request.getParameter("sid"); 
		//����service���ж��Ƿ�ɹ�
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.banstoredr(sid);
		if("0".equals(result.getCode())){
			System.out.println("���ĳɹ���");
			AdmincheckByadmindr(request,response);
				try {
					//�����ж��̼���˵�״̬
					AdmincheckAllstore(request,response);
					response.sendRedirect("page/checkjoin.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
		}else{
			//�鿴ʧ��
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
				//����service���ж��Ƿ�ɹ�
				AdminService adminservice = new AdminServiceImpl();
				ResultModel result = adminservice.checkAllstore(page);
				if("0".equals(result.getCode())){
					//��ʾ״̬Ϊ0�������ĵ���
					HttpSession session = request.getSession();
					session.setAttribute("thispage", page);
					session.setAttribute("checkAllstore", result.getData());
				}else{
					//��û�д������ĵ���
					request.setAttribute("checkAllstoreerror", result.getMsg());
				}
	}

	/*
	 * ����Ա�����̼���Ϣ�޸����
	 */
	private void Adminchangecheckdr(HttpServletRequest request, HttpServletResponse response) {
		String alertid = request.getParameter("alertid");
		System.out.println(alertid);
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.changecheckdr(alertid);
		System.out.println(result.getCode());
		if("0".equals(result.getCode())){
			//���ͨ��
			//�����޸ĵ��̱���Ϣ�ķ���
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
	
	
	//ϵͳ����Ա�����޸���Ϣ���״̬��ѯ��������Ҫ�޸���Ϣ���̵�
	private void AdmincheckBycheckdr(HttpServletRequest request, HttpServletResponse response) {
		//����service���ж��Ƿ�ɹ�
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.checkbycheckdr(0);
		if("0".equals(result.getCode())){
			//��ʾ״̬Ϊ0�������ĵ���
			HttpSession session = request.getSession();
			session.setAttribute("checkcheckdr", result.getData());
		}else{
			//��û�д������ĵ���
			request.setAttribute("checkcheckdrerror", result.getMsg());
		}
	}
	
	
	//��פ���
	private void Adminchangedrbysid(HttpServletRequest request, HttpServletResponse response){
		String sid = request.getParameter("sid"); 
		//�{��service���Ƿ�ɹ�
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.changestoredr(sid);
		if("0".equals(result.getCode())){
			System.out.println("���ĳɹ���");
			AdmincheckByadmindr(request,response);
				try {
					//�����ж��̼���˵�״̬
					AdmincheckAllstore(request,response);
					response.sendRedirect("page/checkjoin.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
		}else{
			//�鿴ʧ��
			request.setAttribute("admincheckjoinerror", result.getMsg());
			try {
				request.getRequestDispatcher("page/checkjoin.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	
	
	 
	
	
	
	
			


	private void AdmincheckByadmindr(HttpServletRequest request, HttpServletResponse response) {
		//����service���ж��Ƿ�ɹ�
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.checkbystoredr(0);
		System.out.println(result.getCode());
		if("0".equals(result.getCode())){
			//��ʾ״̬Ϊ0�������ĵ���
			HttpSession session = request.getSession();
			session.setAttribute("checkchange", result.getData());
//			try {
//				response.sendRedirect("checkchange.jsp");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
		}else{
			//��û�д������ĵ���
			request.setAttribute("checkchangeerror", result.getMsg());
//			try {
//				request.getRequestDispatcher("checkchange.jsp").forward(request, response);
//			} catch (ServletException | IOException e) {
//				e.printStackTrace();
//			}
		}
		
		
	}

	private void AdmincheckByadminname1(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ��������
		String answer = request.getParameter("answer");
		//����service���ж��Ƿ�ɹ�
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.checkbyquestion1(answer);
		if("0".equals(result.getCode())){
			System.out.println("��������ȷ");
			//�鿴�ɹ�,�����ܱ����棬���������������session��
			HttpSession session = request.getSession();
			session.setAttribute("admincheck1", result.getData());
			System.out.println(session.toString());
			try {
				response.sendRedirect("forgetpassword2.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			//�鿴ʧ��
			request.setAttribute("checkByadminnameerror1", result.getMsg());
			try {
				request.getRequestDispatcher("forgetpassword1.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}

		
		
		
		
	/*
	 * �ܱ�����
	 */
	private void AdmincheckByadminname(HttpServletRequest request, HttpServletResponse response) {
		//��ȡϵͳ����Ա����
		String  adminname = request.getParameter("adminname");
		//����service��ķ����ж��Ƿ�ɹ�
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.checkbyquestion(adminname);
		if("0".equals(result.getCode())){
			System.out.println("�鿴�ɹ���");
			//�鿴�ɹ�,�����ܱ����棬���������������session��
			HttpSession session = request.getSession();
			session.setAttribute("admincheck", result.getData());
			System.out.println(session.toString());
			try {
				response.sendRedirect("forgetpassword1.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			//�鿴ʧ��
			request.setAttribute("checkByadminnameerror", result.getMsg());
			try {
				request.getRequestDispatcher("forgetpassword.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	
	
	
	
	
	
	

	/*
	 * ϵͳ����Ա�޸�����
	 */
	private void AdminchangeByadminaccount(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ����Ա�˺�
		String adminaccount = request.getParameter("adminaccount");
		//��ȡ����Ա����
		String adminpassword = request.getParameter("adminpassword");
		
		//����service��ķ����ж��Ƿ��޸ĳɹ�
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.changebyadminaccount(adminaccount, adminpassword);
		if("0".equals(result.getCode())){
			//�޸ĳɹ�����ת�������棬���������������session��
			HttpSession session = request.getSession();
			session.setAttribute("adminchange",result.getData());
			try {
				//ҳ����ת
				response.sendRedirect("changePwd.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}                                                                                                                     
		}else{
			//�޸�ʧ��
			//��ת����ǰ��¼���棬������浽request��
			request.setAttribute("changeByadminaccounterror", result.getMsg());
			try {
				request.getRequestDispatcher("changePwd.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	/*
	 * ϵͳ����Ա��¼
	 */
	private void AdminloginByadminaccount(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ����Ա�˺�
		String adminaccount = request.getParameter("adminaccount");
		//��ȡ����Ա����
		String adminpassword = request.getParameter("adminpassword");
		//����service��ķ����ж��Ƿ����
		AdminService adminservice = new AdminServiceImpl();
		ResultModel result = adminservice.loadbyadminaccount(adminaccount, adminpassword);
		if("0".equals(result.getCode())){
			//��¼�ɹ�����ת�������棬���������������session��
			HttpSession session = request.getSession();
			session.setAttribute("adminlogin",result.getData());
			AdmincheckAllstore(request, response);
			checkAllalterstore(request, response);
			adminCheckReportMerchant(request,response);
			adminCheckReportGoods(request,response);
			try {
				//ҳ����ת
				response.sendRedirect("adminmain.jsp");
			} catch (IOException e) {
				e.printStackTrace(); 
			}
		}else{
			//��¼ʧ��
			//��ת����ǰ��¼���棬������浽request��
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

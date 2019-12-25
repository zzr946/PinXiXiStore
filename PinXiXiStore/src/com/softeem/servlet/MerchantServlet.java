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
		//��������ͷ����
		request.setCharacterEncoding("utf-8");
		//������Ӧͷ����
		response.setCharacterEncoding("utf-8");
		//��ȡ����������ֲ���
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


	//�һ�����
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
					//ע��ɹ���ҳ����ת����¼ҳ��(�ض�����ת)
					response.sendRedirect("merchant_login.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				try {
					//ע��ʧ�ܻص�ע��ҳ��(����ת��)
					request.getRequestDispatcher("forgetpwd.jsp").forward(request, response);
				} catch (ServletException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}


	//�˳��������
	private void logout(HttpServletRequest request, HttpServletResponse response) {
		request.getSession().invalidate();
		try {
			response.sendRedirect("merchant_login.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	//��¼�ķ���
	private void login(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ������
		String phone = request.getParameter("login_phone");
		String password = request.getParameter("login_password");
		//���������ݴ��ݸ�Servicceҵ���߼���
		MerchantService mservice = new MerchantServiceImpl();
		ResultModel result = mservice.login(phone, password);
		//�ж���Ӧ���
				if("1".equals(result.getCode())){
					try {
						//��������Ϣ����request
						request.setAttribute("logerror", result.getMsg());
						//�û������������
						request.getRequestDispatcher("merchant_login.jsp").forward(request, response);
					} catch (Exception e) {
						e.printStackTrace();
					}
				}else{
					//��ȡsession����
					HttpSession session = request.getSession();
					//���û���Ϣ����session������
					session.setAttribute("merchant", result.getData());
					//��¼�ɹ�������ҳ�棬������¼���û���Ϣ���ݵ���ҳ��
					try {
						response.sendRedirect("main1.jsp");
						
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
	}


	//������֤��
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
					//ע��ɹ���ҳ����ת����¼ҳ��(�ض�����ת)
					response.sendRedirect("merchant_login.jsp");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}else{
				try {
					//ע��ʧ�ܻص�ע��ҳ��(����ת��)
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

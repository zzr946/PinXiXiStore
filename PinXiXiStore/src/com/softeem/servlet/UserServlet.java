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
	
	private String code="";//���ڴ洢�����֤��
       
    public UserServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��������ͷ����Ӧͷ����
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
//		System.out.println("���������");
		//��ȡ����
		String method=request.getParameter("method");
//		System.out.println(method);
		switch (method) {
		case "phonecode":
			//�û�ע�������֤
			reg_phonecheck(request,response);
			break;
		case "userreg":
			//�û��ύע����Ϣ
			userReg(request,response);
			break;
		case "user_loginBypwd":
			//�ֻ��������¼
			loginBypwd(request,response);
			break;
		case "user_loginByPhone":
			//�ֻ���֤���¼
			loginByPhone(request,response);
			break;
		case "logout":
			//��ȫ�˳��ķ���
			userlogout(request,response);
			break;
		case "changepwd":
			//�û��һ�����
			changePwd(request,response);
			break;
		case "changeuserinfo":
			//�û��޸ĸ�������
			changeUserinfo(request,response);
		case "modifypwd":
			//�û����������޸�����
			modifypwd(request,response);
			break;
		default:
			break;
		}
		
	}

	

	//�û����������޸�����
	private void modifypwd(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ��Ҫ�޸�������û�id
		String uid=request.getParameter("uid");
		//��ȡԭ����
		String oldpwd=request.getParameter("oldpwd");
		//��ȡ������
		String newpwd=request.getParameter("newpwd");
//		System.out.println(uid+"--"+oldpwd+"--"+newpwd);
		//����dao��ķ���
		UserService uservice=new UserServiceImpl();
		ResultModel result=uservice.modifypwdtopwd(uid,oldpwd,newpwd);
		if("2".equals(result.getCode())){
			//ԭ�����������
			//��resultתΪjson�ַ�����ʽ
			String jstr=JSON.toJSONString(result);
			//���ñ���
			response.setCharacterEncoding("utf-8");
			//��json�ַ������͵�ǰ̨
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println(jstr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if("0".equals(result.getCode())){
			//�޸ĳɹ�
			//���session
			request.getSession().invalidate();
			//��resultתΪjson�ַ�����ʽ
			String jstr=JSON.toJSONString(result);
			//���ñ���
			response.setCharacterEncoding("utf-8");
			//��json�ַ������͵�ǰ̨
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println(jstr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if("1".equals(result.getCode())){
			//�޸�ʧ��
			//��resultתΪjson�ַ�����ʽ
			String jstr=JSON.toJSONString(result);
			//���ñ���
			response.setCharacterEncoding("utf-8");
			//��json�ַ������͵�ǰ̨
			PrintWriter out;
			try {
				out = response.getWriter();
				out.println(jstr);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	//�û��޸ĸ�����Ϣ�ķ���
	private void changeUserinfo(HttpServletRequest request, HttpServletResponse response) {
		
	}

	//�û��һ�����ķ���
	private void changePwd(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�ֻ���
		String phone=request.getParameter("phone");
		//��ȡ�û��������֤��
		String check_code=request.getParameter("pnum");
		if(check_code.equals(code)){
			//�����֤����ȷ
			//��ȡ�û������������
			String newpassword=request.getParameter("password");
			//����service��ķ���
			UserService uservice=new UserServiceImpl();
			ResultModel result=uservice.modifyPassword(phone, newpassword);
			System.out.println(result.getCode());
			System.out.println(result.getMsg());
			System.out.println(result.getData());
			
				if("0".equals(result.getCode())){
					//�����޸ĳɹ�����ת����¼ҳ��
					request.setAttribute("changeok", "�����޸ĳɹ�");
//					request.getRequestDispatcher("login.jsp").forward(request, response);
				}
				if("1".equals(result.getCode())){
					//�޸�ʧ�ܣ�������ת���޸�ҳ��
					request.setAttribute("changeerror", "�����޸�ʧ��");
					
				}
				if("2".equals(result.getCode())){
					//���ֻ���δע�ᣬ��ת��ע��ҳ��
					request.setAttribute("notreg", "���ֻ���δע��");
//					request.getRequestDispatcher("userreg.jsp").forward(request, response);
				}
			try {
				request.getRequestDispatcher("changepwd.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}else{
			//��֤�����������ת����ǰҳ��
			request.setAttribute("changecodereeor", "��֤���������");
			try {
				request.getRequestDispatcher("changepwd.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	//�û��˳��ķ���
	private void userlogout(HttpServletRequest request, HttpServletResponse response) {
		//����û���Ϣ��ʵ���Ͼ������session�е�����
//		request.getSession().removeAttribute("student");
//		request.getSession().removeAttribute("daily");
		request.getSession().invalidate();
		try {
			//�ص�������
			response.sendRedirect("index.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	//�û��ֻ���֤���¼
	private void loginByPhone(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�ֻ���
		String phone=request.getParameter("phone");
		//��ȡ�û��������֤��
		String check_code=request.getParameter("pnum");
		if(code.equals(check_code)){
			//��֤��������ȷ
			//����service��ķ����жϴ��ֻ����Ƿ����
			UserService uservice=new UserServiceImpl();
			ResultModel result=uservice.loginByphonecode(phone);
			try {
				if("0".equals(result.getCode())){
					//��¼�ɹ�
					//����UaddressDao���ȡ�û��������ջ���ַ
					UaddressService addressservice=new UaddressServiceImpl();
					ResultModel addressresult=addressservice.useraddressAll(((User) (result.getData())).getUid());
					System.out.println(addressresult.getData());
					
					//��ת�������棬��������뵽session��
					HttpSession session=request.getSession();
					session.setAttribute("userlogin", result.getData());
					//���û���ַ����session��
					session.setAttribute("useraddress", addressresult.getData());
					response.sendRedirect("index.jsp");//ҳ����ת
				}else{
					//��¼ʧ��
					//��ת����ǰ��¼���棬������浽request��
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
			// ��֤���������,��ת����ǰ��¼ҳ��
			request.setAttribute("codereeor", "��֤���������");
			try {
				request.getRequestDispatcher("loginbyphone.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	//�û��ֻ������¼
	private void loginBypwd(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�ֻ���
		String phone=request.getParameter("phone");
		//��ȡ����
		String password=request.getParameter("password");
//		System.out.println(phone);
//		System.out.println(password);
		//����service��ķ���
		UserService uservice=new UserServiceImpl();
		ResultModel result=uservice.loginBypwd(phone, password);
//		System.out.println(result.getCode());
//		System.out.println(result.getMsg());
//		System.out.println(result.getData());
		
		try {
			if("0".equals(result.getCode())){
				//��¼�ɹ���ת�������棬��������뵽session��
				//��ȡ���û��µ������ջ���ַ
				//����UaddressDao���ȡ�û��������ջ���ַ
				UaddressService addressservice=new UaddressServiceImpl();
				ResultModel addressresult=addressservice.useraddressAll(((User) (result.getData())).getUid());
				System.out.println(addressresult.getData());
				HttpSession session=request.getSession();
				//���û���ַ����session��
				session.setAttribute("useraddress", addressresult.getData());
				session.setAttribute("userlogin", result.getData());
				response.sendRedirect("index.jsp");//ҳ����ת
			}else{
				//��¼ʧ��(�û������������)����ת����ǰ��¼���棬������浽request��
				request.setAttribute("loginBypwderror", result.getMsg());
//				response.sendRedirect("login.jsp");
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
		}catch (IOException | ServletException e) {
			e.printStackTrace();
		}
		
	}

	//�û�ע����Ϣ�ύ
	private void userReg(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�ֻ�����
		String phone=request.getParameter("phone");
		//��ȡ�û��������֤��
		String check_code=request.getParameter("pnum");
//		System.out.println(check_code);
//		System.out.println(code);
		//��ȡ����
		String password=request.getParameter("password");
		if(check_code.equals(code)){//�����֤��������ȷ
			//����service��ķ���
			UserService uservice=new UserServiceImpl();
			ResultModel result=uservice.reg(phone, password);
//			System.out.println(result.getCode());
//			System.out.println(result.getMsg());
//			System.out.println(result.getData());
			//���û�������뵽session��
			HttpSession session=request.getSession();
			session.setAttribute("user",result);
			try {
				if("0".equals(result.getCode())){
					//ע��ɹ�����ת����¼����
					request.getRequestDispatcher("login.jsp").forward(request, response);
				}else{
					//ע��ʧ��(�����ֻ��ű�ռ�úͲ������ݴ���)����ת����ǰ����
					request.getRequestDispatcher("userreg.jsp").forward(request, response);
				}
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			// ��֤���������,��ת����ǰҳ��
			request.setAttribute("codeerror", "��֤���������");
			try {
				request.getRequestDispatcher("userreg.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	//�ֻ�������֤�ķ���
	private void reg_phonecheck(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�ֻ�����
		String phone=request.getParameter("phoneNumber");
		//��ȡ�����֤��
		code=PhoneCode.getCode();
		//���������֤��
		PhoneCode.sendCode(phone, code);
	}

}

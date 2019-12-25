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
		//��������ͷ����Ӧͷ����
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
//		System.out.println("���������");
		//��ȡ����
		String method=request.getParameter("method");
//		System.out.println(method);
		switch (method) {
		case "addaddress":
		//�û�����ջ���ַ
		addaddress(request,response);
		break;
		case "defaultaddress":
			//����ΪĬ�ϵ�ַ
			setdefaultaddress(request,response);
			break;
		case "addressAll":
			//��ѯ�û����е�ַ��Ϣ
			addressAll(request,response);
			break;
		case "deladdress":
			//ɾ����ַ�ķ���
			delAddress(request,response);
			break;
		default:
			break;
		}
	}
	
	//�û�ɾ����ַ�ķ���
	private void delAddress(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�û�id
		String uid=request.getParameter("uid");
		//��ȡ��Ҫɾ�����û�id
		String addressid=request.getParameter("addressid");
		System.out.println("�û�id"+uid);
		System.out.println("ɾ���ĵ�ַid"+addressid);
		//����service��ķ���ɾ����ַ
		UaddressService uaddressservice=new UaddressServiceImpl();
		ResultModel result=uaddressservice.delteAddress(uid, addressid);
		System.out.println(result.getCode());
		System.out.println(result.getMsg());
		System.out.println(result.getData());
		//��������뵽session��
		HttpSession session=request.getSession();
		session.setAttribute("useraddress", result.getData());
		//�ض��򵽵�ַ�������
		try {
			response.sendRedirect("useraddress.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}

	//��ѯ�û��������ջ���ַ�ķ���
	private void addressAll(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�û�id
		String uid=request.getParameter("uid");
		//����service��ķ���
		UaddressService uservice=new UaddressServiceImpl();
		ResultModel result=uservice.useraddressAll(uid);
		//����ַ�����session��
		HttpSession session=request.getSession();
		session.setAttribute("useraddress", result.getData());
//		request.setAttribute("useraddress", result.getData());
		try {
			request.getRequestDispatcher("useraddress.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

	//����ΪĬ�ϵ�ַ�ķ���
	private void setdefaultaddress(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�û�id
		String uid=request.getParameter("uid");
		String uadid=request.getParameter("uadid");
//		System.out.println(uid);
//		System.out.println(uadid);
		//����service��ķ���
		UaddressService uaddressservice=new UaddressServiceImpl();
		ResultModel result=uaddressservice.setdefaultaddress(uid, uadid);
		if("0".equals(result.getCode())){
			//���óɹ�
		}else{
			//����ʧ��
		}
		
	}

	//�û�����ջ���ַ�ķ���
	private void addaddress(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ����ջ���ַ���û�id
		String uid=request.getParameter("uid");
		//��ȡ���ջ�������
		String uadname=request.getParameter("uadname");
		//��ȡ�ջ��˵绰
		String mobile=request.getParameter("mobile");
		//��ȡʡ
		String province=request.getParameter("province");
		//��ȡ��
		String city=request.getParameter("city");
		//��ȡ��
		String area=request.getParameter("area");
		//��ȡ��ϸ��ַ
		String detailaddress=request.getParameter("detailaddress");
//		System.out.println("��ǰ��¼���û�"+uid);
//		System.out.println("�ջ�������"+uadname);
//		System.out.println("�ջ����ֻ���"+mobile);
//		System.out.println("ʡ"+province);
//		System.out.println("��"+city);
//		System.out.println("��"+area);
//		System.out.println("��ϸ��ַ"+detailaddress);
		//����service��ķ���
		UaddressService uaddressservice=new UaddressServiceImpl();
		ResultModel result=uaddressservice.useraddaddress(uid, uadname, mobile, province, city, area, detailaddress);
		if("0".equals(result.getCode())){
			//��ӳɹ�
			//�����û����е�ַ��Ϣ���뵽session��
			request.getSession().setAttribute("useraddress", result.getData());
			try {
				response.sendRedirect("useraddress.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		if("1".equals(result.getCode())){
			//���ʧ��
			request.setAttribute("useraddresserror", "���ʧ��");
			try {
				request.getRequestDispatcher("useraddress.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			};
		}
		if("2".equals(result.getCode())){
			//���ޣ����������
			request.setAttribute("noaddaddress", "���������,���������");
			try {
				request.getRequestDispatcher("useraddress.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			};
		}
		
		
		
	}
	
	

}

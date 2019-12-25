package com.softeem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softeem.model.ResultModel;
import com.softeem.service.CommelistService;
import com.softeem.service.impl.CommelistServiceImpl;

@WebServlet("/comment")
public class CommelistServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CommelistServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// ��������ͷ����Ӧͷ����
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		// System.out.println("���������");
		// ��ȡ����
		String method = request.getParameter("method");
		// System.out.println(method);
		switch (method) {
		case "subcomment":
			//�û���ת��������Ʒ
			subcomment(request,response);
			break;
		case "publishcomment":
			//�û��ύ����
			publishComment(request,response);
			break;
		default:
			break;
		}
		
		
	}

	//�û��ύ����
	private void publishComment(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�û�id
		String uid=request.getParameter("uid");
		//��ȡ��Ʒid
		String gid=request.getParameter("gid");
		//��ȡ��������
		String content=request.getParameter("content");
		HttpSession session=request.getSession();
		//System.out.println(uid);
		//System.out.println(gid);
		//System.out.println(content);
		//����service�㽫��Ϣ�������۱�
		CommelistService cservice=new CommelistServiceImpl();
		ResultModel result=cservice.saveComment(uid, gid, content);
		if("0".equals(result.getCode())){
			//����ɹ�
			//���޸ĺ�Ĵ����۶�������session��
			session.setAttribute("showawaitevaluate", result.getData());
			//�ض��򵽶�������ҳ��
			try {
				response.sendRedirect("userorder.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
			//����ʧ��
			try {
				response.sendRedirect("userorder.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//�û���ת��������Ʒ
	private void subcomment(HttpServletRequest request, HttpServletResponse response) {
		String uid=request.getParameter("uid");
		String gid=request.getParameter("gid");
		//System.out.println(uid);
		//System.out.println(gid);
		//����service���ѯ��Ʒ��Ϣ
		CommelistService cservice=new CommelistServiceImpl();
		ResultModel result=cservice.goodsInfo(gid);
		HttpSession session=request.getSession();
		if("0".equals(result.getCode())){
			//�ɹ�
			//����Ʒ��Ϣ����session��
			session.setAttribute("goodscomment", result.getData());
			//�ض�������ҳ��
			try {
				response.sendRedirect("usercommentlist.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			//ʧ��
		}
		
		
		
	}

}

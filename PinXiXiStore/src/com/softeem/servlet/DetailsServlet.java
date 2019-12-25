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
import com.softeem.service.DetailsService;
import com.softeem.service.impl.CommelistServiceImpl;
import com.softeem.service.impl.DetailsServiceImpl;

@WebServlet("/details")
public class DetailsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DetailsServlet() {
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
		System.out.println("goods���������");
		// ��ȡ����
		String method = request.getParameter("method");
		System.out.println(method);
		switch (method) {
		case "goodsdetails":
			//���������Ʒ���뵽��Ʒ�������
			goodsdetails(request,response);
			break;

		default:
			break;
		}
	}

	
	//���������Ʒ���뵽��Ʒ�������
	private void goodsdetails(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ��Ʒid
		String gid=request.getParameter("gid");
		System.out.println("��Ʒid:"+gid);
		//����service��ķ�����������Ʒid��ѯ����Ʒ����ϸ��Ϣ
		DetailsService dservice=new DetailsServiceImpl();
		ResultModel result=dservice.queryOne(gid);
		if("0".equals(result.getCode())){
			//��ѯ�ɹ�
			
			//��ѯ����Ʒ����������
			CommelistService cservice=new CommelistServiceImpl();
			ResultModel commresult=cservice.commentAll(gid);
			
			//����Ʒ�������session�У�����ת����Ʒ�������
			HttpSession session=request.getSession();
			//�����ۼ��ϴ���session
			session.setAttribute("commlist", commresult.getData());
			session.setAttribute("goodsdetails", result.getData());
			//��ת����Ʒ����ҳ��(details.jsp)
			try {
				response.sendRedirect("details.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
			//��ѯʧ��
			//����ͣ������ԭ����
			request.setAttribute("detailserror", "û��ѯ������Ʒ����ϸ��Ϣ");
			try {
				request.getRequestDispatcher("commodity.jsp").forward(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
			};
			
		}
		
		
	}

}

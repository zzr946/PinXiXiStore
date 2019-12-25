package com.softeem.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softeem.model.ResultModel;
import com.softeem.service.SalesService;
import com.softeem.service.impl.SalesServiceImpl;

/**
 * ������Ʒ���
 * 
 * @author ��־Ȼ
 *
 */
@WebServlet("/sales")
public class SalesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SalesServlet() {
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
		//System.out.println("���������");
		// ��ȡ����
		String method = request.getParameter("method");
		//System.out.println(method);
		switch (method) {
		case "salesall":
			//��������ػ�ʱ��ʼ������
			salesAll(request,response);
			break;

		default:
			break;
		}
		
		
	}

	//��������ػݳ�ʼ������
	private void salesAll(HttpServletRequest request, HttpServletResponse response) {
		//����sevice���ѯ�������е�������Ʒ
		SalesService sservice=new SalesServiceImpl();
		ResultModel result=sservice.salesall();
		//������浽request�У���ת���������ػݡ�����
		request.setAttribute("today", result);
		try {
			request.getRequestDispatcher("buytoday.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
	}

}

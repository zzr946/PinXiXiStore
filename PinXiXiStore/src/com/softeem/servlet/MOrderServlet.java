package com.softeem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softeem.model.ResultModel;
import com.softeem.service.GoodsService;
import com.softeem.service.OrderService;
import com.softeem.service.impl.GoodsServiceImpl;
import com.softeem.service.impl.OrderServiceImpl;


@WebServlet("/merchant/order")
public class MOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MOrderServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��������ͷ����
		request.setCharacterEncoding("utf-8");
		//������Ӧͷ����
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
		switch(method){
		case "findOrderByPage":
			findOrderByPage(request,response);
			break;
		case "fahuo":
			fahuo(request,response);
			default:
				break;
		}
	}

	//����
	private void fahuo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		OrderService os = new OrderServiceImpl();
		ResultModel result = os.fahuo(id);
		if("0".equals(result.getCode())){
			//�����ɹ�
			request.setAttribute("updateOrder", 1);
			request.getRequestDispatcher("orderlist.jsp").forward(request, response);
		}else{
			//����ʧ��
			request.setAttribute("updateOrder", 2);
			request.getRequestDispatcher("orderlist.jsp").forward(request, response);
		}
		
	}


	//ͨ��ҳ���鶩��
	private void findOrderByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		String mid = request.getParameter("mid");
		OrderService os = new OrderServiceImpl();
		ResultModel result = os.findByPage(page, mid);
		if("0".equals(result.getCode())){
			//�ҵ�������
			//�鵽��
			HttpSession session = request.getSession();
			session.setAttribute("orderList", result.getData());
			session.setAttribute("orderPage", page);
			request.getRequestDispatcher("orderlist.jsp").forward(request, response);
		}else{
			request.setAttribute("selectorder", 1);
			if(page == 1){
				request.setAttribute("orderpage", page);
			}else{
				request.setAttribute("orderpage", page-1);
			}
			request.getRequestDispatcher("orderlist.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

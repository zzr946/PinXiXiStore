package com.softeem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softeem.model.ResultModel;
import com.softeem.service.CollectionService;
import com.softeem.service.impl.CollectionServiceImpl;

/**
 * ���û��ղ���Ʒ��صĲ���
 * 
 * @author ��־Ȼ
 *
 */
@WebServlet("/collection")
public class CollectionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CollectionServlet() {
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
		System.out.println("���������");
		// ��ȡ����
		String method = request.getParameter("method");
		System.out.println(method);
		switch (method) {
		case "collect":
			collectionGoods(request,response);
			break;
		case "selectAll":
			//��ѯ�����ղ���Ʒ
			collGoodsAll(request,response);
			break;
		default:
			break;
		}
		
	}
	
	//�鿴�����ղ���Ʒ
	private void collGoodsAll(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�û�id
		String uid=request.getParameter("uid");
		//����service��ѯ�����ղ���Ʒ
		CollectionService cservice=new CollectionServiceImpl();
		ResultModel result=cservice.selectcollGoods(uid);
		//��result����session��
		HttpSession session=request.getSession();
		session.setAttribute("collectGoodsAll", result.getData());
		//�ض����ղؽ���(collection����)
		try {
			response.sendRedirect("usercollection.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	//�û��ղ���Ʒ�ķ���
	private void collectionGoods(HttpServletRequest request, HttpServletResponse response) {
		//System.out.println("************");
		//��ȡ�û�id
		String uid=request.getParameter("uid");
		//��ȡ�ղص���Ʒid
		String gid=request.getParameter("gid");
		//System.out.println("uid:"+uid);
		//System.out.println("�ղ�gid:"+gid);
		//����service��ķ���
		CollectionService collService=new CollectionServiceImpl();
		ResultModel result=collService.addcollGoods(uid, gid);
		//�����װ�뵽request�з��ص�ǰ̨
		request.setAttribute("collresult", result);
		//����ת����ת����Ʒ�������
		try {
			request.getRequestDispatcher("details.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	
	

}

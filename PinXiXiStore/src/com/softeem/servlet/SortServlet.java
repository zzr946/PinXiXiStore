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
import com.softeem.service.SortService;
import com.softeem.service.impl.SortServiceImpl;


@WebServlet("/merchant/sort")
public class SortServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public SortServlet() {
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
		System.out.println("���Խ�����");
		//��ȡ����������ֲ���
		String method = request.getParameter("method");
		switch(method){
		case "add":
			add(request,response);
			break;
		case "findByPage":
			findByPage(request,response);
			break;
		case "deletesort":
			deleteBySid(request,response);
			break;
		case "updateSort":
			updateSort(request,response);
		default:
			break;
		}
	}

//	�޸����
	private void updateSort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡҪ�޸ĳɵ������
		String sortname2 = request.getParameter("sortname2");
		//��ȡҪ�޸ĳɵ������
		String sortinfo = request.getParameter("sortinfo");
		//��ȡԭ���������
		String sortname1 = request.getParameter("sortname1");
		SortService ss = new SortServiceImpl();
		ResultModel result = ss.updateSort(sortname1, sortname2, sortinfo);
		if("0".equals(result.getCode())){
			//�޸ĳɹ�
			request.setAttribute("updatesort", 1);
			request.getRequestDispatcher("sort.jsp").forward(request, response);
		}else{
			//�޸�ʧ��
			request.setAttribute("updatesort", 2);
			request.getRequestDispatcher("sort.jsp").forward(request, response);
		}
		
	}

	//ɾ��ָ������
	private void deleteBySid(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sid = request.getParameter("sid");
		SortService ss = new SortServiceImpl();
		ResultModel result = ss.deleteBySid(sid);
		if("0".equals(result.getCode())){
			//ɾ���ɹ�
			request.setAttribute("deletesort", 1);
			//��д��������б�
			request.getRequestDispatcher("sort.jsp").forward(request, response);
		}else if("1".equals(result.getCode())){
			//ɾ��ʧ��
			request.setAttribute("deletesort", 2);
			//��д��������б�
			request.getRequestDispatcher("sort.jsp").forward(request, response);
		}
	}

	//��ҳ��������
	private void findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��ȡ�̼�id
		String mid = request.getParameter("mid");
		//��ȡҳ��(��Ҫ�����ع����Ĳ�ѯҳ��ת��Ϊ����)
		int page = Integer.parseInt(request.getParameter("page"));
		System.out.println(page);
		System.out.println(mid);
		SortService ss = new SortServiceImpl();
		ResultModel result = ss.selectByPage(mid, page);
		System.out.println(result.getData());
		if("0".equals(result.getCode())){
			//�鵽��
			request.setAttribute("merchantid", mid);
			HttpSession session = request.getSession();
			session.setAttribute("sortList", result.getData());
			session.setAttribute("pageNow", page);
			request.getRequestDispatcher("sort.jsp").forward(request, response);
		}else{
			//û�в�ѯ������
			request.setAttribute("selectsort", 1);
//			request.setAttribute("pageNow", page-1);
			if(page == 1){
				request.setAttribute("pageNow", page);
			}else{
				request.setAttribute("pageNow", page-1);
			}
			request.getRequestDispatcher("sort.jsp").forward(request, response);
		}
		
	}

	//��ӷ������
	private void add(HttpServletRequest request, HttpServletResponse response) throws ServletException {
		//��ȡ������
		String sortname = request.getParameter("sortname");
		String sortinfo = request.getParameter("sortinfo");
		String merchantid = request.getParameter("mid");
		System.out.println(merchantid);
		SortService ss = new SortServiceImpl();
		ResultModel result = ss.addSort(sortname, sortinfo, merchantid);
		if("0".equals(result.getCode())){
			//��ӳɹ�
			//��ȡsession����
			HttpSession session = request.getSession();
			//���û���Ϣ����session������
			session.setAttribute("sort", result.getData());
			//��ӳɹ�
			request.setAttribute("addsort", 1);//��ӳɹ�
			//��ӳɹ�������ҳ�棬������¼���û���Ϣ���ݵ���ҳ��
			try {
				request.getRequestDispatcher("createSort.jsp").forward(request, response);
				
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if("1".equals(result.getCode())){
			//���ʧ��
			//����ת������ҳҳ��
			try {
				request.setAttribute("addsort", 2);//���ʧ��
				request.getRequestDispatcher("createSort.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else if("2".equals(result.getCode())){
			//��Ʒ����Ѵ���
			//����ת������ҳҳ��
			try {
				request.setAttribute("addsort", 3);//��Ʒ����Ѵ���
				request.getRequestDispatcher("createSort.jsp").forward(request, response);
			} catch (ServletException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

}

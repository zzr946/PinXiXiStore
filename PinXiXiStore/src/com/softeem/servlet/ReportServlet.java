package com.softeem.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softeem.model.ResultModel;
import com.softeem.service.ReportService;
import com.softeem.service.impl.ReportServiceImpl;
/**
 * �û��ٱ����
 * @author ��־Ȼ
 *
 */
@WebServlet("/report")
public class ReportServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public ReportServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��������ͷ����Ӧͷ����
				request.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");
				System.out.println("���������");
				// ��ȡ����
				String method = request.getParameter("method");
				System.out.println(method);
				switch (method) {
				case "reportGoods":
					//�ٱ��ķ���
					reportGoods(request,response);
					break;

				default:
					break;
				}
	}

	//�û��ٱ���Ʒ�ķ���
	private void reportGoods(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�ٱ����û�id
		String uid=request.getParameter("uid");
		if("".equals(uid)){
			//���û��¼����ת��ԭҳ����ʾ��¼
			try {
				response.sendRedirect("details.jsp?plaselogin=0");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		
		//��ȡ�ٱ�����Ʒ
		String gid=request.getParameter("gid");
		//��ȡ�ٱ�����Ʒ����
		String goodsname=request.getParameter("goodsname");
		//��ȡ�ٱ�ԭ��
		String reportcause=request.getParameter("reportcause");
		//��ȡ�ٱ�����
		String reportcontent=request.getParameter("reportcontent");
		//����service��ķ������ٱ���Ϣ�浽�ٱ�����
		ReportService rservice=new ReportServiceImpl();
		ResultModel result=rservice.report(uid, gid, goodsname, reportcause, reportcontent);
		//���ٱ����װ��request���ص�ǰ̨
		request.setAttribute("reportresult", result);
		if("0".equals(result.getCode())){
			//�ٱ��ɹ�
			//�Ӷ�����Ʒ�������
			try {
				response.sendRedirect("details.jsp?represult=0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			//�ٱ�ʧ��
			//�Ӷ�����Ʒ�������
			try {
				response.sendRedirect("details.jsp?represult=1");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
	}

}

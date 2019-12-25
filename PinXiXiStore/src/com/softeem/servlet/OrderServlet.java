package com.softeem.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.xml.ws.RespectBinding;

import com.mysql.fabric.Response;
import com.softeem.model.ResultModel;
import com.softeem.service.OrderService;
import com.softeem.service.impl.OrderServiceImpl;
import com.softeem.service.impl.UaddressServiceImpl;
import com.softeem.tools.Tools;

@WebServlet("/order")
public class OrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String ordernumber;

	public OrderServlet() {
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
		System.out.println(method);
		switch (method) {
		case "paypage":
			//���ﳵ�ύ��������֧������
			payPage(request,response);
			break;
		case "success":
			//������� ���ύ����
			success(request,response);
			break;
		case "find":
			//�û��鿴����
			orderAll(request,response);
			break;
		case "order_pay":
			//�鿴����ʱһ��֧��
			//aKetPay(request,response);
			break;
		case "take":
			//�û��ջ��ķ���
			take(request,response);
			break;
		case "returnmoney":
			//�û��˻��ķ���
			returnGoods(request,response);
			break;
		case "subreturn":
			//�û��ύ�˻���
			subreturn(request,response);
			
			break;
		default:
			break;
		}
		
		
	}
	

	//�û��ύ�˻���
	private void subreturn(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�û�id
		String uid=request.getParameter("uid");
		//��ȡ��Ʒid
		String goodsnumber=request.getParameter("goodsnumber");
		//��ȡ�˻�����
		String returntype=request.getParameter("returntype");
		//��ȡ�˿�ԭ��
		String returncause=request.getParameter("returncause");
		//��ȡ�˿���
		String returnmoney=request.getParameter("returnmoney");
		//�˿�˵��
		String returndetails=request.getParameter("returndetails");
		
		//System.out.println(uid);//�˿���id
		//System.out.println(goodsnumber);//��Ʒid
		//System.out.println(returntype);//�˿�����
		//System.out.println(returncause);//�˿�ԭ��
		//System.out.println(returnmoney);//�˿���
		//System.out.println(returndetails);//�˿�˵��
		//����service�㣬���˻���Ϣ���뵽�˻�����
		OrderService oservice=new OrderServiceImpl();
		ResultModel result=oservice.subreturn(uid, goodsnumber, returntype, returncause, returnmoney, returndetails);
		if("0".equals(result.getCode())){
			//�˻������ύ�ɹ�
			//�ض��򵽶�������userorder.jspҳ��
			try {
				response.sendRedirect("userorder.jsp?result=0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			//�˻������ύʧ��
			//�ض��򵽶�������userorder.jspҳ��
			try {
				response.sendRedirect("userorder.jsp?result=1");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	//�鿴����ʱһ��֧��
	/*private void aKetPay(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�û�id
		String uid=request.getParameter("uid");
		//��ȡ��Ʒid
		String gid=request.getParameter("gid");
		//��ȡ�ܽ��
		String money=request.getParameter("money");
		//����service��ķ��� 
		OrderService oservice=new OrderServiceImpl();
		
		//��ѯ�ջ���ַ��Ϣ
		ResultModel resultaddress=oservice.aKeyPayAddress(uid, gid);
		//��ѯ��Ʒ��Ϣ
		ResultModel resultgoods=oservice.aKeyPayGoods(uid, gid);
		//���������session��
		HttpSession session=request.getSession();
		session.setAttribute("pay_address", resultaddress.getData());
		session.setAttribute("pay_goods", resultgoods.getData());
		session.setAttribute("pay_sum_money", money);
		//�ض��򵽸������
		try {
			response.sendRedirect("pay.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/

	//�û��˻��ķ���
	private void returnGoods(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�û�id
		String uid=request.getParameter("uid");
		//��ȡ��Ʒid
		String gid=request.getParameter("gid");
		System.out.println(uid);
		System.out.println(gid);
		//����dervice��ķ���
		OrderService oservice=new OrderServiceImpl();
		ResultModel result=oservice.returnmoney(uid, gid);//��ѯ��Ҫ�˻�����Ʒ
		//System.out.println(result.getCode());
		//System.out.println(result.getMsg());
		//System.out.println(result.getData());
		//����Ʒ����session��
		HttpSession session=request.getSession();
		session.setAttribute("returngoodsmoney", result.getData());
		//��ת���˻�����
		try {
			response.sendRedirect("userrefund.jsp");
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
		
	}

	//�û��ջ��ķ���
	private void take(HttpServletRequest request, HttpServletResponse response) {
		String uid=request.getParameter("uid");
		String gid=request.getParameter("gid");
		//System.out.println(uid);
		//System.out.println(gid);
		//����service��ķ���  �ջ�
		OrderService oservice=new OrderServiceImpl();
		ResultModel result=oservice.takeOrder(uid, gid);
		if("0".equals(result.getCode())){
			//�ջ��ɹ�
//			try {
//				response.sendRedirect("userorder.jsp?err=0");
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			orderAll(request,response);
		}else{
			//�ջ�ʧ��
			try {
				response.sendRedirect("userorder.jsp?err=1");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	//�û��鿴���ж���
	private void orderAll(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ��ǰ��¼���û�id
		String uid=request.getParameter("uid");
		System.out.println(uid);
		OrderService oservice=new OrderServiceImpl();
		//����service��,�����û�id��ѯ�û������ж�����Ϣ
		ResultModel result=oservice.orderAll(uid);
		//����service��,�����û�id��ѯ�û���֧��������Ϣ
		ResultModel awaitresult=oservice.awaitOrder(uid);
		//����service��,�����û�id��ѯ�û�������������Ϣ
		ResultModel awaitsendresult=oservice.awaitSendOrder(uid);
		//����service��,�����û�id��ѯ�û����ջ�������Ϣ
		ResultModel awaittakeresult=oservice.awaittakeOrder(uid);
		//����service�㣬�����û�id��ѯ�����۵Ķ�����Ϣ
		ResultModel awaitevaluateresult=oservice.awaitevaluate(uid);
		//System.out.println(result.getCode());
		//System.out.println(result.getMsg());
		//System.out.println(result.getData());
		HttpSession session=request.getSession();
		if("0".equals(result.getCode())){
			//��ѯ�ɹ�
			//�����ж�������session��
			session.setAttribute("showOrderAll", result.getData());
			//�������������session��
			session.setAttribute("showawaitOrder", awaitresult.getData());
			//����������������session��
			session.setAttribute("showawaitSendOrder", awaitsendresult.getData());
			//�����ջ���������session��
			session.setAttribute("showawaittake", awaittakeresult.getData());
			//�������۶�������session��
			session.setAttribute("showawaitevaluate", awaitevaluateresult.getData());
			
			//�ض��򵽶�������ҳ��(userorderҳ��)
			try {
				response.sendRedirect("userorder.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			//��ѯʧ��
			//�ض��򵽶�������ҳ��(userspaceҳ��)
			try {
				response.sendRedirect("userorder.jsp?err=0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	//�ύ���� ������ɹ�����
	private void success(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�û�id
		String uid=request.getParameter("uid");
		//��ȡѡ�еĵ�ַid
		String addressid=request.getParameter("addressid");
		//��ȡ����id
		//System.out.println(ordernumber);
		//��ȡ�����ܽ��
		String m=request.getParameter("money");
		int money=Integer.valueOf(m);
		//����service��ı䶩��״̬
		OrderService oservice=new OrderServiceImpl();
		ResultModel result=oservice.success(uid,addressid,ordernumber,money);
		if("0".equals(result.getCode())){
			//����ɹ�
			//��ѯ�ջ���ַ��Ϣ
			ResultModel addresult=oservice.addressinfo(addressid);
			//����ַ��Ϣ����session��
			HttpSession session=request.getSession();
			session.setAttribute("successaddress", addresult.getData());
			//���ܼ۴���session��
			session.setAttribute("countmoney", money);
			//��ת������ɹ�ҳ��
			try {
				response.sendRedirect("usersuccess.jsp?money="+money);
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
			//����ʧ��
			try {
				response.sendRedirect("pay.jsp?err=0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}

	//�ӹ��ﳵ��ת��֧������
	private void payPage(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�û�id
		String uid=request.getParameter("uid");
		//��ȡ��Ʒid��
		String gid=request.getParameter("gids");
		//��ȡ��Ʒ������
		String count=request.getParameter("goodscount");
		//��ȡ�ܽ��
		String money=request.getParameter("money");
		//System.out.println(uid);
		//System.out.println(gids);
		//System.out.println(goodscount);
		//��ȡ��Ʒid����
		String[] gids=gid.split("~");
		//System.out.println(Arrays.toString(gids));
		//��ȡ��Ʒ������
		String[] goodscount=count.split("~");
		//System.out.println(Arrays.toString(goodscount));
		//���ɶ������
		ordernumber=Tools.getNum();
		//����service��ķ���
		OrderService oservice=new OrderServiceImpl();
		//װ����ļ���
		List<ResultModel> list=new ArrayList<ResultModel>();
		for(int i=0;i<gids.length;i++){
			//��ȡ��Ӧ����Ʒid����Ʒ����
			String goodsid=gids[i];//ÿһ����Ʒid
			String goodsnum=goodscount[i];
			int goodstotal=Integer.valueOf(goodsnum);//ÿ����Ʒ�Ĺ�������
			//����service��ķ������ɶ���
			ResultModel result=oservice.subOrder(ordernumber,uid, goodsid, goodstotal);
			//System.out.println(result.getCode());
			//System.out.println(result.getMsg());
			//System.out.println(result.getData());
			//�������ӵ����������
			list.add(result);
		}
		//����������ϣ������һ��codeֵΪ1��ʧ��
		boolean boo=true;
		for (ResultModel result : list) {
			if("1".equals(result.getCode())){
				boo=false;
			}
		}
		HttpSession session=request.getSession();
		if(boo){
			//�������ɳɹ�
			//System.out.println("�������ɳɹ�");
			//��ѯ�û����е�ַ��Ϣ
			ResultModel addressresult=new UaddressServiceImpl().useraddressAll(uid);
			//��ѯ�û���֧������Ʒ��Ϣ
			ResultModel goodsresult=oservice.payGoods(ordernumber);
			//����ַ��Ϣ���뵽session��
			session.setAttribute("pay_address", addressresult.getData());
			//����֧����Ʒ��Ϣ��ӵ�session��
			session.setAttribute("pay_goods", goodsresult.getData());
			//���ܽ����뵽session��
			session.setAttribute("pay_sum_money", money);
			//��ת��֧������
			try {
				response.sendRedirect("pay.jsp");
			} catch (IOException e) {
				e.printStackTrace();
			}
			
		}else{
			//��������ʧ��
			//System.out.println("��������ʧ��");
		}
		
		
		
		
		
		
	}

}

package com.softeem.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softeem.model.ResultModel;
import com.softeem.service.CartService;
import com.softeem.service.impl.CartServiceImpl;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public CartServlet() {
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
		case "addtocart":
			// ����Ʒ��ӵ����ﳵ��
			addtoCart(request, response);
			break;
		case "cart_no1":
			// ���빺�ﳵ�Ǽ��ع��ﳵ�����Ʒ
			loadCart(request, response);
			break;
		default:
			break;
		}

	}

	// ���빺�ﳵ�Ǽ��ع��ﳵ�����Ʒ
	private void loadCart(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�����¼���û�id
		String uid=request.getParameter("uid");
		//System.out.println(uid);
		// ����service��ķ�����ѯ���ﳵ�е�������Ʒ��Ϣ
		CartService cservice=new CartServiceImpl();
		ResultModel result=cservice.cartAll(uid);
		//System.out.println(result.getCode());
		//System.out.println(result.getMsg());
		//System.out.println(result.getData());
		//���������request�У�����ת�����ﳵ����
		//HttpSession session=request.getSession();
		request.setAttribute("cartAll", result);
		//�ض��򵽹��ﳵ����
		try {
			request.getRequestDispatcher("shopcart.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}
		
	}

	// ������Ʒ��ӵ����û��Ĺ��ﳵ��
	private void addtoCart(HttpServletRequest request, HttpServletResponse response) {
		// ��ȡ��ǰ�û�id
		String uid = request.getParameter("uid");
		// ��ȡ��Ʒid
		String gid = request.getParameter("gid");
		// ��ȡ��Ʒ����
		String goodsprice = request.getParameter("price");
		double price = Double.valueOf(goodsprice);
		// ��ȡ�����Ʒ������
		String goodscount = request.getParameter("count");
		int count = Integer.valueOf(goodscount);
		// ����service��ķ�����������д�뵽���ﳵ����
		CartService cservice = new CartServiceImpl();
		ResultModel result = cservice.addGoodsToCart(uid, gid, price, count);
		// ���������request�����ص���ǰ����(details.jsp)
		request.setAttribute("addCartResult", result);
		try {
			// ����ת������Ʒ����(details.jsp)����
			request.getRequestDispatcher("details.jsp").forward(request, response);
		} catch (ServletException | IOException e) {
			e.printStackTrace();
		}

	}

}

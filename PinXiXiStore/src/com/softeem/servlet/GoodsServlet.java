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
import com.softeem.service.impl.GoodsServiceImpl;

@WebServlet("/goods")
public class GoodsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private String userinputtitle;//��¼�û��������������

	public GoodsServlet() {
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
		case "goodsbytitle":
			// ģ����ѯ��Ʒ
			selectgoodsBytitle(request, response);
			break;
		case "productall":
			// �����������Ʒҳ��(commodity.jsp)ִ�еķ���
			commodity(request, response);
			break;
		case "sales":
			// ����������
			salesSort(request, response);
			break;
		case "price":
			// ���۸�����
			priceSort(request, response);
			break;
		case "salestoinput":
			// �����������û�������������Ʒ
			salesinputSort(request, response);
			break;
		case "priceinput":
			// ���۸������û�������������Ʒ
			priceinputSort(request, response);
			break;
		case "paging":
			// ��ҳ��ѯĬ�ϵ�������Ʒ
			pageMode(request, response);
			break;
		case "salespaging":
			//Ĭ����Ʒ  ����������ҳ(��һҳ)
			salespageMode(request, response);
			break;
		case "salespagingup":
			//Ĭ����Ʒ  ����������ҳ(��һҳ)
			salespageModeUP(request,response);
			break;
		case "pricepaging":
			//Ĭ����Ʒ  �۸�������ҳ
			pricepageMode(request,response);
			break;
		case "inputSortPaging":
			//��ѯ�û�����������Ʒ����ʾָ����ҳ��(ָ��ҳ��)
			inputpageMode(request,response);
			break;
		case "inputSortSalesPaging":
			//��ѯ�û�����������Ʒ��������������(��һҳ)
			inputSortSalespageMode(request,response);
			break;
		case "inputSortpricepaging":
			//��ѯ�û�����������Ʒ�������۸�����(��һҳ)
			inputSortpricepageMode(request,response);
			break;
		case "commpaging":
			//������Ʒҳ��(commodity.jsp)��Ʒ��ҳ
			commpaging(request,response);
			break;
		default:
			break;
		}

	}

	

	//������Ʒҳ��(commodity.jsp)��Ʒ��ҳ
	private void commpaging(HttpServletRequest request, HttpServletResponse response) {
		// ��ȡ�û���Ҫ��ѯ�ĵڼ�ҳ����Ʒ
		String page = request.getParameter("current");
		int current = Integer.valueOf(page);
		System.out.println("��Ҫ��ѯ��ҳ����"+current);
		// ����service��ķ�����ѯ��ҳ��������Ʒ
		GoodsService gservice = new GoodsServiceImpl();
		ResultModel result = gservice.goodsAllpage(current);
		// ��ѯ��������,����Ʒ�б���뵽session�в���ת��usersearch.jspҳ����
		if ("0".equals(result.getCode())) {// ����鵽�����ݣ������ݷ���
			HttpSession session = request.getSession();
			session.setAttribute("productall", result.getData());
			try {
				response.sendRedirect("commodity.jsp?title=no&current=" + current);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// û�������ˣ���ֱ�ӷ���ǰ̨
			try {
				response.sendRedirect("commodity.jsp?title=no&current=" + (current - 1) + "&notgoods=0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	//��ѯ�û�����������Ʒ�������۸�����(��һҳ)
	private void inputSortpricepageMode(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�û�����������ؼ���
		System.out.println(userinputtitle);
		//��ȡ��ѯҳ��
		String page=request.getParameter("current");
		int current=Integer.valueOf(page);
		System.out.println(current);
		//����service��ķ���
		GoodsService gservice=new GoodsServiceImpl();
		ResultModel result=gservice.inputPriceSortpage(userinputtitle, current);
		// ��ѯ��������,����Ʒ�б���뵽session�в���ת��usersearch.jspҳ����
		if ("0".equals(result.getCode())) {// ����鵽�����ݣ������ݷ���
			HttpSession session = request.getSession();
			session.setAttribute("dimgoodslist", result.getData());
			try {
				response.sendRedirect("usersearch.jsp?dr=price&title="+userinputtitle+"&current=" + current);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// û�������ˣ���ֱ�ӷ���ǰ̨
			try {
				response.sendRedirect("usersearch.jsp?dr=price&title="+userinputtitle+"&current=" + (current - 1) + "&notgoods=0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	

	////��ѯ�û�����������Ʒ��������������(ָ��ҳ��)
	private void inputSortSalespageMode(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�û����������
//		System.out.println(userinputtitle);
		//��ȡ��Ҫ��ѯ�ڼ�ҳ����Ʒ
		String page=request.getParameter("current");
		int current=Integer.valueOf(page);
//		System.out.println(current);
		//����service��ķ���
		GoodsService gservice=new GoodsServiceImpl();
		ResultModel result=gservice.inputSalesSortpage(userinputtitle, current);
		
		// ��ѯ��������,����Ʒ�б���뵽session�в���ת��usersearch.jspҳ����
				if ("0".equals(result.getCode())) {// ����鵽�����ݣ������ݷ���
					HttpSession session = request.getSession();
					session.setAttribute("dimgoodslist", result.getData());
					try {
						response.sendRedirect("usersearch.jsp?dr=sales&title="+userinputtitle+"&current=" + current);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					// û�������ˣ���ֱ�ӷ���ǰ̨
					try {
						response.sendRedirect("usersearch.jsp?title="+userinputtitle+"&dr=sales&current=" + (current - 1) + "&notgoods=0");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		
		
		
		
	}

	//��ѯ�û�����������Ʒ(Ĭ��  ָ��ҳ��)
	private void inputpageMode(HttpServletRequest request, HttpServletResponse response) {
		//��ȡ�û����������
		System.out.println("�û����룺"+userinputtitle);
		//��ȡ�û���ǰ��ҳ��
		String current=request.getParameter("current");
//		System.out.println(current);
		//����service��ķ�����ѯָ��ҳ����Ʒ
		GoodsService gservice=new GoodsServiceImpl();
		ResultModel result=gservice.goodsByinputpage(userinputtitle, Integer.valueOf(current));
		// ��ѯ��������,����Ʒ�б���뵽session�в���ת��usersearch.jspҳ����
		if ("0".equals(result.getCode())) {// ����鵽�����ݣ������ݷ���
			HttpSession session = request.getSession();
			session.setAttribute("dimgoodslist", result.getData());
			try {
				response.sendRedirect("usersearch.jsp?title="+userinputtitle+"&current=" + current);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// û�������ˣ���ֱ�ӷ���ǰ̨
			try {
				response.sendRedirect("usersearch.jsp?title="+userinputtitle+"&current="+(Integer.valueOf(current)-1)+"&notgoods=0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
	}

	//Ĭ����Ʒ  �۸�������ҳ(��һҳ)
	private void pricepageMode(HttpServletRequest request, HttpServletResponse response) {
		// ��ȡ��Ҫ��ѯ�ĵڼ�ҳ��Ʒ
		String page = request.getParameter("current");
		int current = Integer.valueOf(page);
		System.out.println(current);
		// ����service��ķ�����ѯ��ҳ��������Ʒ
		GoodsService gservice = new GoodsServiceImpl();
		ResultModel result = gservice.priceSortpage(current);
		// ��ѯ��������,����Ʒ�б���뵽session�в���ת��usersearch.jspҳ����
		if ("0".equals(result.getCode())) {// ����鵽�����ݣ������ݷ���
			HttpSession session = request.getSession();
			session.setAttribute("dimgoodslist", result.getData());
			try {
				response.sendRedirect("usersearch.jsp?dr=price&title=no&current=" + current);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// û�������ˣ���ֱ�ӷ���ǰ̨
			try {
				response.sendRedirect("usersearch.jsp?dr=price&title=no&current=" + (current - 1) + "&notgoods=0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// Ĭ����Ʒ ����������ҳ(��һҳ)
	private void salespageMode(HttpServletRequest request, HttpServletResponse response) {
		// ��ȡ��Ҫ��ѯ�ĵڼ�ҳ��Ʒ
		String page = request.getParameter("current");
		int current = Integer.valueOf(page);
		// System.out.println(current);
		// ����service��ķ�����ѯ��ҳ��������Ʒ
		GoodsService gservice = new GoodsServiceImpl();
		ResultModel result = gservice.salesSortpage(current);
		// ��ѯ��������,����Ʒ�б���뵽session�в���ת��usersearch.jspҳ����
		if ("0".equals(result.getCode())) {// ����鵽�����ݣ������ݷ���
			HttpSession session = request.getSession();
			session.setAttribute("dimgoodslist", result.getData());
			try {
				response.sendRedirect("usersearch.jsp?dr=sales&title=no&current=" + current);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// û�������ˣ���ֱ�ӷ���ǰ̨
			try {
				response.sendRedirect("usersearch.jsp?title=no&dr=sales&current=" + (current - 1) + "&notgoods=0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// Ĭ����Ʒ ����������ҳ
	private void salespageModeUP(HttpServletRequest request, HttpServletResponse response) {
		// ��ȡ��Ҫ��ѯ�ĵڼ�ҳ��Ʒ
		String page = request.getParameter("current");
		int current = Integer.valueOf(page);
		// System.out.println(current);
		// ����service��ķ�����ѯ��ҳ��������Ʒ
		GoodsService gservice = new GoodsServiceImpl();
		ResultModel result = gservice.salesSortpage(current);
		// ��ѯ��������,����Ʒ�б���뵽session�в���ת��usersearch.jspҳ����
		HttpSession session = request.getSession();
		session.setAttribute("dimgoodslist", result.getData());
		try {
			response.sendRedirect("usersearch.jsp?dr=sales&title=no&current=" + (current-1));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// ��ҳ��ʾ����Ĭ�ϵ���Ʒ(ָ��ҳ��)
	private void pageMode(HttpServletRequest request, HttpServletResponse response) {
		// ��ȡ�û���Ҫ��ѯ�ĵڼ�ҳ����Ʒ
		String page = request.getParameter("current");
		int current = Integer.valueOf(page);
		System.out.println("��Ҫ��ѯ��ҳ����"+current);
		// ����service��ķ�����ѯ��ҳ��������Ʒ
		GoodsService gservice = new GoodsServiceImpl();
		ResultModel result = gservice.goodsAllpage(current);
		// ��ѯ��������,����Ʒ�б���뵽session�в���ת��usersearch.jspҳ����
		if ("0".equals(result.getCode())) {// ����鵽�����ݣ������ݷ���
			HttpSession session = request.getSession();
			session.setAttribute("dimgoodslist", result.getData());
			try {
				response.sendRedirect("usersearch.jsp?title=no&current=" + current);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// û�������ˣ���ֱ�ӷ���ǰ̨
			try {
				response.sendRedirect("usersearch.jsp?title=no&current=" + (current - 1) + "&notgoods=0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// ��ת��'������Ʒ'ҳ��(commodity.jsp)ʱ��ʼ��ҳ��ִ�еķ���(��һҳ)
	private void commodity(HttpServletRequest request, HttpServletResponse response) {
		// ����service���ѯ������Ʒ
		GoodsService gservice = new GoodsServiceImpl();
		ResultModel result = gservice.goodsAll();
		// ��ѯ��������,����Ʒ�б���뵽session�в���ת��usersearch.jspҳ����
		HttpSession session = request.getSession();
		session.setAttribute("productall", result.getData());
		try {
			response.sendRedirect("commodity.jsp?current=1");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// ���۸������û�������������Ʒ(��һҳ)
	private void priceinputSort(HttpServletRequest request, HttpServletResponse response) {
		// ��ȡ�û����������
		System.out.println("�û���������ݣ�" + userinputtitle);
		// ����service��ķ���
		GoodsService dservice = new GoodsServiceImpl();
		// �����û���������ݲ�ѯ��Ʒ������
		ResultModel result = dservice.selectPriceBynameSort(userinputtitle);
		// ����Ʒ�б���뵽session�У�����ת��usersearch.jspҳ��
		HttpSession session = request.getSession();
		session.setAttribute("dimgoodslist", result.getData());
		try {
			// �ض���������Ʒ����
			response.sendRedirect("usersearch.jsp?title="+userinputtitle+"&dr=price&current=1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ���û�������������Ʒ����������(��һҳ)
	// �����������û�������������Ʒ(��һҳ)
	private void salesinputSort(HttpServletRequest request, HttpServletResponse response) {
		// ��ȡ�û����������
		System.out.println("�û���������ݣ�" + userinputtitle);
		// ����service��ķ���
		GoodsService dservice = new GoodsServiceImpl();
		// �����û���������ݲ�ѯ��Ʒ������
		ResultModel result = dservice.selectSalesBynameSort(userinputtitle);
		// ����Ʒ�б���뵽session�У�����ת��usersearch.jspҳ��
		HttpSession session = request.getSession();
		session.setAttribute("dimgoodslist", result.getData());
		try {
			// �ض���������Ʒ����
			response.sendRedirect("usersearch.jsp?title="+userinputtitle+"&dr=sales&current=1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ���۸�����(��һҳ)
	// ��Ʒ����ҳ�棬��Ʒ���ռ۸�������ʾ(��һҳ)
	private void priceSort(HttpServletRequest request, HttpServletResponse response) {
		// ����service��ķ���
		GoodsService gservice = new GoodsServiceImpl();
		ResultModel result = gservice.priceSort();
		// ����Ʒ�б���뵽session�У�����ת��usersearch.jspҳ��
		HttpSession session = request.getSession();
		session.setAttribute("dimgoodslist", result.getData());
		try {
			// �ض���������Ʒ����
			response.sendRedirect("usersearch.jsp?dr=price&title=no&current=1");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// ����������(��һҳ)
	// ��Ʒ����ҳ�棬����������ķ���(��һҳ)
	private void salesSort(HttpServletRequest request, HttpServletResponse response) {
		// ����service��ķ���
		GoodsService gservice = new GoodsServiceImpl();
		ResultModel result = gservice.salesSort();
		// ����Ʒ�б���뵽session�У�����ת��usersearch.jspҳ��
		HttpSession session = request.getSession();
		session.setAttribute("dimgoodslist", result.getData());
		try {
			// �ض���������Ʒ����
			response.sendRedirect("usersearch.jsp?dr=sales&title=no&current=1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// ģ����ѯ������Ʒ(��һҳ)
	// �����û����������ģ����ѯ������Ʒ(��һҳ)
	private void selectgoodsBytitle(HttpServletRequest request, HttpServletResponse response) {
		// ��ȡ�û����������
		String title = request.getParameter("title");
		title = title.trim();
		// System.out.println(title);
		HttpSession session = request.getSession();
		if (title == "") {
			// ����û�û���������ݣ����ѯ������Ʒ
			// ����service��ķ���
			GoodsService gservice = new GoodsServiceImpl();
			ResultModel result = gservice.goodsAll();
			// ��ѯ��������,����Ʒ�б���뵽session�в���ת��usersearch.jspҳ����
			session.setAttribute("dimgoodslist", result.getData());
			try {
				response.sendRedirect("usersearch.jsp?title=no&current=1");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// ����û����������ݣ���ģ����ѯ�����Ʒ
			// ���û���������ݴ浽��Ա������
			userinputtitle = title;
			GoodsService gservice = new GoodsServiceImpl();
			ResultModel result = gservice.goodsByinput(title);
			if ("0".equals(result.getCode())) {
				// ��ѯ��������,����Ʒ�б���뵽session�в���ת��usersearch.jspҳ����
				session.setAttribute("dimgoodslist", result.getData());
				try {
					response.sendRedirect("usersearch.jsp?title=" + title+"&current=1");
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				// ģ����ѯû�в鵽���ѯ������Ʒ
				// ����service��ķ���
				ResultModel resultno = gservice.goodsAll();
				// ��ѯ��������,����Ʒ�б���뵽session�в���ת��usersearch.jspҳ����
				session.setAttribute("dimgoodslist", resultno.getData());
				try {
					response.sendRedirect("usersearch.jsp?title=no&current=1");
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

		}

	}

}

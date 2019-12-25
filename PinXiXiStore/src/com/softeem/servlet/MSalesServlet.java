package com.softeem.servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softeem.model.Goods;
import com.softeem.model.ResultModel;
import com.softeem.service.GoodsService;
import com.softeem.service.SalesService;
import com.softeem.service.impl.GoodsServiceImpl;
import com.softeem.service.impl.SalesServiceImpl;

@WebServlet("/merchant/sales")
public class MSalesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public MSalesServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//��������ͷ����
				request.setCharacterEncoding("utf-8");
				//������Ӧͷ����
				response.setCharacterEncoding("utf-8");
				String method = request.getParameter("method");
						switch (method) {
						case "findBySpecialPage":
							findBySpecialPage(request,response);
							break;
						case "addSpecial":
							addSpecial(request,response);
							break;
						case "quxiaocuxiao":
							quXiaoCuXiAO(request,response);
						default:
							break;
						}
	}

	private void quXiaoCuXiAO(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gid = request.getParameter("gid");
		double goodsprice = Double.parseDouble(request.getParameter("goodsprice"));
		SalesService ss = new SalesServiceImpl();
		GoodsService gs = new GoodsServiceImpl();
		ResultModel result1 = ss.delete(gid);
		ResultModel result2 = gs.updateDiscountSecond(gid, goodsprice);
		if("0".equals(result1.getCode())&&"0".equals(result2.getCode())){
			//�޸ĺͲ��붼�ɹ���
			request.setAttribute("addSales", 3);
			request.getRequestDispatcher("specialPrice.jsp").forward(request, response);
		}else{
			//����ʧ��
			request.setAttribute("addSales", 4);
			request.getRequestDispatcher("specialPrice.jsp").forward(request, response);
		}
	}


		//���Ǵ�������Ҫ���ҵ�ҳ��
		private void findBySpecialPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String mid = request.getParameter("mid");
			int page = Integer.parseInt(request.getParameter("specialPage"));
			System.out.println(page);
			System.out.println(mid+"--"+page+"����");
			GoodsService gs = new GoodsServiceImpl();
			ResultModel result = gs.selectBySpecialPage(mid, page);
			System.out.println("��������");
			if("0".equals(result.getCode())){
				//�鵽��
				HttpSession session = request.getSession();
				session.setAttribute("specialGoodsList", result.getData());
				session.setAttribute("specialPage", page);
				request.getRequestDispatcher("specialPrice.jsp").forward(request, response);
			}else{
				//û�в�ѯ������
				request.setAttribute("selectSpecialGoods", 1);
				if(page == 1){
					request.setAttribute("specialPage", page);
				}else{
					request.setAttribute("specialPage", page-1);
				}
				request.getRequestDispatcher("specialPrice.jsp").forward(request, response);
			}
			
		}
	
		//�����ؼ۴���
		private void addSpecial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//���ɴ�����������
			String id = UUID.randomUUID().toString();
			//��ȡ����״̬
			int salesdr = Integer.parseInt(request.getParameter("goodsdr"));
			//��ȡ��Ʒ��
			String goodsname = request.getParameter("goodsname");
			//��ȡ��Ʒԭ��
			double goodsprice = Double.parseDouble(request.getParameter("goodsprice"));
			//��ȡ��Ʒid
			String gid = request.getParameter("gid");
			//��ȡ��Ʒ������
			double discountprice = Double.parseDouble(request.getParameter("discountprice"));
			GoodsService gs = new GoodsServiceImpl();
			Goods goods = new Goods();
			//�洢gid
			goods.setGid(gid);
			//�洢��Ʒ��
			goods.setGoodsname(goodsname);
			//�洢��Ʒԭ��
			goods.setGoodsprice(goodsprice);
			//�洢��Ʒ������
			goods.setDiscountprice(discountprice);
			SalesService ss = new SalesServiceImpl();
			ResultModel result1 = gs.updateDiscount(gid, discountprice);//�ж��Ƿ��޸�����Ʒ���еĴ�����Ϣ
			ResultModel result2 = ss.insert(goods, salesdr, id);//�ж��Ƿ�����˴�����
			if("0".equals(result1.getCode())&&"0".equals(result2.getCode())){
				//�޸ĺͲ��붼�ɹ���
				request.setAttribute("addSales", 1);
				request.getRequestDispatcher("specialPrice.jsp").forward(request, response);
			}else{
				//����ʧ��
				request.setAttribute("addSales", 2);
				request.getRequestDispatcher("specialPrice.jsp").forward(request, response);
			}
		}
		
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

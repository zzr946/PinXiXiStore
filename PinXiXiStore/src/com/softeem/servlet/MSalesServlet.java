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
		//设置请求头编码
				request.setCharacterEncoding("utf-8");
				//设置响应头编码
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
			//修改和插入都成功了
			request.setAttribute("addSales", 3);
			request.getRequestDispatcher("specialPrice.jsp").forward(request, response);
		}else{
			//操作失误
			request.setAttribute("addSales", 4);
			request.getRequestDispatcher("specialPrice.jsp").forward(request, response);
		}
	}


		//这是促销表需要查找的页数
		private void findBySpecialPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String mid = request.getParameter("mid");
			int page = Integer.parseInt(request.getParameter("specialPage"));
			System.out.println(page);
			System.out.println(mid+"--"+page+"促销");
			GoodsService gs = new GoodsServiceImpl();
			ResultModel result = gs.selectBySpecialPage(mid, page);
			System.out.println("到这里了");
			if("0".equals(result.getCode())){
				//查到了
				HttpSession session = request.getSession();
				session.setAttribute("specialGoodsList", result.getData());
				session.setAttribute("specialPage", page);
				request.getRequestDispatcher("specialPrice.jsp").forward(request, response);
			}else{
				//没有查询到数据
				request.setAttribute("selectSpecialGoods", 1);
				if(page == 1){
					request.setAttribute("specialPage", page);
				}else{
					request.setAttribute("specialPage", page-1);
				}
				request.getRequestDispatcher("specialPrice.jsp").forward(request, response);
			}
			
		}
	
		//加入特价促销
		private void addSpecial(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			//生成促销表中主键
			String id = UUID.randomUUID().toString();
			//获取货物状态
			int salesdr = Integer.parseInt(request.getParameter("goodsdr"));
			//获取商品名
			String goodsname = request.getParameter("goodsname");
			//获取商品原价
			double goodsprice = Double.parseDouble(request.getParameter("goodsprice"));
			//获取商品id
			String gid = request.getParameter("gid");
			//获取商品促销价
			double discountprice = Double.parseDouble(request.getParameter("discountprice"));
			GoodsService gs = new GoodsServiceImpl();
			Goods goods = new Goods();
			//存储gid
			goods.setGid(gid);
			//存储商品名
			goods.setGoodsname(goodsname);
			//存储商品原价
			goods.setGoodsprice(goodsprice);
			//存储商品促销价
			goods.setDiscountprice(discountprice);
			SalesService ss = new SalesServiceImpl();
			ResultModel result1 = gs.updateDiscount(gid, discountprice);//判断是否修改了商品表中的促销信息
			ResultModel result2 = ss.insert(goods, salesdr, id);//判断是否插入了促销表
			if("0".equals(result1.getCode())&&"0".equals(result2.getCode())){
				//修改和插入都成功了
				request.setAttribute("addSales", 1);
				request.getRequestDispatcher("specialPrice.jsp").forward(request, response);
			}else{
				//操作失误
				request.setAttribute("addSales", 2);
				request.getRequestDispatcher("specialPrice.jsp").forward(request, response);
			}
		}
		
		
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

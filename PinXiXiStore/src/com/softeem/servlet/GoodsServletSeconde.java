package com.softeem.servlet;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softeem.model.Goods;
import com.softeem.model.ResultModel;
import com.softeem.model.Sort;
import com.softeem.service.GoodsService;
import com.softeem.service.SortService;
import com.softeem.service.impl.GoodsServiceImpl;
import com.softeem.service.impl.SortServiceImpl;


@WebServlet("/merchant/goodssecond")
public class GoodsServletSeconde extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public GoodsServletSeconde() {
        super();
    }
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//设置请求头编码
		request.setCharacterEncoding("utf-8");
		//设置响应头编码
		response.setCharacterEncoding("utf-8");
		String method = request.getParameter("method");
				switch (method) {
				case "findSort":
					findSort(request,response);
					break;
				case "findByPage":
					findByPage(request,response);
					break;
				case "updateGoods":
					updateGoods(request,response);
					break;
				case "xiajia":
					xiajia(request,response);
					break;
				case "shangjia":
					shangjia(request,response);
					break;
				default:
					break;
				}
	}

	

	

	//商品上架
	private void shangjia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gid = request.getParameter("gid");
		GoodsService gs = new GoodsServiceImpl();
		ResultModel result = gs.shangjia(gid);
		if("0".equals(result.getCode())){
			//上架成功
			request.setAttribute("updateGoods", 5);
			request.getRequestDispatcher("goodslist.jsp").forward(request, response);
		}else{
			//上架失败
			request.setAttribute("updateGoods", 6);
			request.getRequestDispatcher("goodslist.jsp").forward(request, response);
		}
		
	}

	//商品下架
	private void xiajia(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String gid = request.getParameter("gid");
		GoodsService gs = new GoodsServiceImpl();
		ResultModel result = gs.xiajia(gid);
		if("0".equals(result.getCode())){
			//下架成功
			request.setAttribute("updateGoods", 3);
			request.getRequestDispatcher("goodslist.jsp").forward(request, response);
		}else{
			//下架失败
			request.setAttribute("updateGoods", 4);
			request.getRequestDispatcher("goodslist.jsp").forward(request, response);
		}
	}

	//修改商品信息
	private void updateGoods(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取商品名
		String goodsname = request.getParameter("goodsname");
		//获取商品编号
		String goodsnumber = request.getParameter("goodsnumber");
		//获取商品价格
		double goodsprice = Double.parseDouble(request.getParameter("goodsprice"));
		//获取商品库存
		int goodstotal = Integer.parseInt(request.getParameter("goodstotal"));
		//获取计量单位
		String reserved1 = request.getParameter("reserved1");
		//获取商品简介
		String goodsinfo = request.getParameter("goodsinfo");
		//新建一个goods对象存储要修改的数据
		Goods goods = new Goods();
		goods.setGoodsname(goodsname);
		goods.setGoodsnumber(goodsnumber);
		goods.setGoodsprice(goodsprice);
		goods.setGoodstotal(goodstotal);
		goods.setReserved1(reserved1);
		goods.setGoodsinfo(goodsinfo);
		GoodsService gs = new GoodsServiceImpl();
		ResultModel result = gs.updateGoods(goods);
		if("0".equals(result.getCode())){
			//修改成功
			request.setAttribute("updateGoods", 1);
			request.getRequestDispatcher("goodslist.jsp").forward(request, response);
		}else{
			//修改失败
			request.setAttribute("updateGoods", 2);
			request.getRequestDispatcher("goodslist.jsp").forward(request, response);
		}
	}

	//通过页数查询商品列表
	private void findByPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String mid = request.getParameter("mid");
		int page = Integer.parseInt(request.getParameter("page"));
		GoodsService gs = new GoodsServiceImpl();
		ResultModel result = gs.selectByPage(mid, page);
		if("0".equals(result.getCode())){
			//查到了
			HttpSession session = request.getSession();
			session.setAttribute("goodsList", result.getData());
			session.setAttribute("pageNowGoods", page);
			request.getRequestDispatcher("goodslist.jsp").forward(request, response);
		}else{
			//没有查询到数据
			request.setAttribute("selectgoods", 1);
//			request.setAttribute("pageNow", page-1);
			if(page == 1){
				request.setAttribute("pageNowGoods", page);
			}else{
				request.setAttribute("pageNowGoods", page-1);
			}
			request.getRequestDispatcher("goodslist.jsp").forward(request, response);
		}
		
	}

	//查找分类
	private void findSort(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SortService ss = new SortServiceImpl();
		String mid = request.getParameter("mid");
		ResultModel result = ss.selectAll(mid);
		System.out.println(result.getData());
		List<Sort> list = new ArrayList<Sort>();
		if("0".equals(result.getCode())){
			//查到了
			request.setAttribute("sortList", result.getData());
			request.getRequestDispatcher("addGoods.jsp").forward(request, response);
		}else{
			//没查到
			request.getRequestDispatcher("addGoods.jsp").forward(request, response);
		}
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

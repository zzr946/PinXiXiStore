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
	private String userinputtitle;//记录用户输入的搜索内容

	public GoodsServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 设置请求头、响应头编码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		System.out.println("goods请求进来了");
		// 获取请求
		String method = request.getParameter("method");
		System.out.println(method);

		switch (method) {
		case "goodsbytitle":
			// 模糊查询商品
			selectgoodsBytitle(request, response);
			break;
		case "productall":
			// 点击到所有商品页面(commodity.jsp)执行的方法
			commodity(request, response);
			break;
		case "sales":
			// 按销量排序
			salesSort(request, response);
			break;
		case "price":
			// 按价格排序
			priceSort(request, response);
			break;
		case "salestoinput":
			// 按销量排序用户搜索出来的商品
			salesinputSort(request, response);
			break;
		case "priceinput":
			// 按价格排序用户搜索出来的商品
			priceinputSort(request, response);
			break;
		case "paging":
			// 分页查询默认的所有商品
			pageMode(request, response);
			break;
		case "salespaging":
			//默认商品  销量排序后分页(下一页)
			salespageMode(request, response);
			break;
		case "salespagingup":
			//默认商品  销量排序后分页(上一页)
			salespageModeUP(request,response);
			break;
		case "pricepaging":
			//默认商品  价格排序后分页
			pricepageMode(request,response);
			break;
		case "inputSortPaging":
			//查询用户输入的相关商品并显示指定的页数(指定页数)
			inputpageMode(request,response);
			break;
		case "inputSortSalesPaging":
			//查询用户输入的相关商品，并按销量排序(下一页)
			inputSortSalespageMode(request,response);
			break;
		case "inputSortpricepaging":
			//查询用户输入的相关商品，并按价格排序(下一页)
			inputSortpricepageMode(request,response);
			break;
		case "commpaging":
			//所有商品页面(commodity.jsp)商品分页
			commpaging(request,response);
			break;
		default:
			break;
		}

	}

	

	//所有商品页面(commodity.jsp)商品分页
	private void commpaging(HttpServletRequest request, HttpServletResponse response) {
		// 获取用户需要查询的第几页的商品
		String page = request.getParameter("current");
		int current = Integer.valueOf(page);
		System.out.println("需要查询的页数："+current);
		// 调用service层的方法查询该页的所有商品
		GoodsService gservice = new GoodsServiceImpl();
		ResultModel result = gservice.goodsAllpage(current);
		// 查询到了数据,将商品列表存入到session中并跳转到usersearch.jsp页面中
		if ("0".equals(result.getCode())) {// 如果查到了数据，则将数据返回
			HttpSession session = request.getSession();
			session.setAttribute("productall", result.getData());
			try {
				response.sendRedirect("commodity.jsp?title=no&current=" + current);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// 没有数据了，则直接返回前台
			try {
				response.sendRedirect("commodity.jsp?title=no&current=" + (current - 1) + "&notgoods=0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}

	//查询用户输入的相关商品，并按价格排序(下一页)
	private void inputSortpricepageMode(HttpServletRequest request, HttpServletResponse response) {
		//获取用户输入的搜索关键词
		System.out.println(userinputtitle);
		//获取查询页数
		String page=request.getParameter("current");
		int current=Integer.valueOf(page);
		System.out.println(current);
		//调用service层的方法
		GoodsService gservice=new GoodsServiceImpl();
		ResultModel result=gservice.inputPriceSortpage(userinputtitle, current);
		// 查询到了数据,将商品列表存入到session中并跳转到usersearch.jsp页面中
		if ("0".equals(result.getCode())) {// 如果查到了数据，则将数据返回
			HttpSession session = request.getSession();
			session.setAttribute("dimgoodslist", result.getData());
			try {
				response.sendRedirect("usersearch.jsp?dr=price&title="+userinputtitle+"&current=" + current);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// 没有数据了，则直接返回前台
			try {
				response.sendRedirect("usersearch.jsp?dr=price&title="+userinputtitle+"&current=" + (current - 1) + "&notgoods=0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
	}
	
	
	

	////查询用户输入的相关商品，并按销量排序(指定页数)
	private void inputSortSalespageMode(HttpServletRequest request, HttpServletResponse response) {
		//获取用户输入的内容
//		System.out.println(userinputtitle);
		//获取需要查询第几页的商品
		String page=request.getParameter("current");
		int current=Integer.valueOf(page);
//		System.out.println(current);
		//调用service层的方法
		GoodsService gservice=new GoodsServiceImpl();
		ResultModel result=gservice.inputSalesSortpage(userinputtitle, current);
		
		// 查询到了数据,将商品列表存入到session中并跳转到usersearch.jsp页面中
				if ("0".equals(result.getCode())) {// 如果查到了数据，则将数据返回
					HttpSession session = request.getSession();
					session.setAttribute("dimgoodslist", result.getData());
					try {
						response.sendRedirect("usersearch.jsp?dr=sales&title="+userinputtitle+"&current=" + current);
					} catch (IOException e) {
						e.printStackTrace();
					}
				} else {
					// 没有数据了，则直接返回前台
					try {
						response.sendRedirect("usersearch.jsp?title="+userinputtitle+"&dr=sales&current=" + (current - 1) + "&notgoods=0");
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
		
		
		
		
	}

	//查询用户输入的相关商品(默认  指定页数)
	private void inputpageMode(HttpServletRequest request, HttpServletResponse response) {
		//获取用户输入的内容
		System.out.println("用户输入："+userinputtitle);
		//获取用户当前的页数
		String current=request.getParameter("current");
//		System.out.println(current);
		//调用service层的方法查询指定页的商品
		GoodsService gservice=new GoodsServiceImpl();
		ResultModel result=gservice.goodsByinputpage(userinputtitle, Integer.valueOf(current));
		// 查询到了数据,将商品列表存入到session中并跳转到usersearch.jsp页面中
		if ("0".equals(result.getCode())) {// 如果查到了数据，则将数据返回
			HttpSession session = request.getSession();
			session.setAttribute("dimgoodslist", result.getData());
			try {
				response.sendRedirect("usersearch.jsp?title="+userinputtitle+"&current=" + current);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// 没有数据了，则直接返回前台
			try {
				response.sendRedirect("usersearch.jsp?title="+userinputtitle+"&current="+(Integer.valueOf(current)-1)+"&notgoods=0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
	}

	//默认商品  价格排序后分页(下一页)
	private void pricepageMode(HttpServletRequest request, HttpServletResponse response) {
		// 获取需要查询的第几页商品
		String page = request.getParameter("current");
		int current = Integer.valueOf(page);
		System.out.println(current);
		// 调用service层的方法查询该页的所有商品
		GoodsService gservice = new GoodsServiceImpl();
		ResultModel result = gservice.priceSortpage(current);
		// 查询到了数据,将商品列表存入到session中并跳转到usersearch.jsp页面中
		if ("0".equals(result.getCode())) {// 如果查到了数据，则将数据返回
			HttpSession session = request.getSession();
			session.setAttribute("dimgoodslist", result.getData());
			try {
				response.sendRedirect("usersearch.jsp?dr=price&title=no&current=" + current);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// 没有数据了，则直接返回前台
			try {
				response.sendRedirect("usersearch.jsp?dr=price&title=no&current=" + (current - 1) + "&notgoods=0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 默认商品 销量排序后分页(下一页)
	private void salespageMode(HttpServletRequest request, HttpServletResponse response) {
		// 获取需要查询的第几页商品
		String page = request.getParameter("current");
		int current = Integer.valueOf(page);
		// System.out.println(current);
		// 调用service层的方法查询该页的所有商品
		GoodsService gservice = new GoodsServiceImpl();
		ResultModel result = gservice.salesSortpage(current);
		// 查询到了数据,将商品列表存入到session中并跳转到usersearch.jsp页面中
		if ("0".equals(result.getCode())) {// 如果查到了数据，则将数据返回
			HttpSession session = request.getSession();
			session.setAttribute("dimgoodslist", result.getData());
			try {
				response.sendRedirect("usersearch.jsp?dr=sales&title=no&current=" + current);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// 没有数据了，则直接返回前台
			try {
				response.sendRedirect("usersearch.jsp?title=no&dr=sales&current=" + (current - 1) + "&notgoods=0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// 默认商品 销量排序后分页
	private void salespageModeUP(HttpServletRequest request, HttpServletResponse response) {
		// 获取需要查询的第几页商品
		String page = request.getParameter("current");
		int current = Integer.valueOf(page);
		// System.out.println(current);
		// 调用service层的方法查询该页的所有商品
		GoodsService gservice = new GoodsServiceImpl();
		ResultModel result = gservice.salesSortpage(current);
		// 查询到了数据,将商品列表存入到session中并跳转到usersearch.jsp页面中
		HttpSession session = request.getSession();
		session.setAttribute("dimgoodslist", result.getData());
		try {
			response.sendRedirect("usersearch.jsp?dr=sales&title=no&current=" + (current-1));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	// 分页显示所有默认的商品(指定页数)
	private void pageMode(HttpServletRequest request, HttpServletResponse response) {
		// 获取用户需要查询的第几页的商品
		String page = request.getParameter("current");
		int current = Integer.valueOf(page);
		System.out.println("需要查询的页数："+current);
		// 调用service层的方法查询该页的所有商品
		GoodsService gservice = new GoodsServiceImpl();
		ResultModel result = gservice.goodsAllpage(current);
		// 查询到了数据,将商品列表存入到session中并跳转到usersearch.jsp页面中
		if ("0".equals(result.getCode())) {// 如果查到了数据，则将数据返回
			HttpSession session = request.getSession();
			session.setAttribute("dimgoodslist", result.getData());
			try {
				response.sendRedirect("usersearch.jsp?title=no&current=" + current);
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// 没有数据了，则直接返回前台
			try {
				response.sendRedirect("usersearch.jsp?title=no&current=" + (current - 1) + "&notgoods=0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

	}

	// 跳转到'所有商品'页面(commodity.jsp)时初始化页面执行的方法(第一页)
	private void commodity(HttpServletRequest request, HttpServletResponse response) {
		// 调用service层查询所有商品
		GoodsService gservice = new GoodsServiceImpl();
		ResultModel result = gservice.goodsAll();
		// 查询到了数据,将商品列表存入到session中并跳转到usersearch.jsp页面中
		HttpSession session = request.getSession();
		session.setAttribute("productall", result.getData());
		try {
			response.sendRedirect("commodity.jsp?current=1");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 按价格排序用户搜索出来的商品(第一页)
	private void priceinputSort(HttpServletRequest request, HttpServletResponse response) {
		// 获取用户输入的内容
		System.out.println("用户输入的内容：" + userinputtitle);
		// 调用service层的方法
		GoodsService dservice = new GoodsServiceImpl();
		// 根据用户输入的内容查询商品并排序
		ResultModel result = dservice.selectPriceBynameSort(userinputtitle);
		// 将商品列表存入到session中，并跳转到usersearch.jsp页面
		HttpSession session = request.getSession();
		session.setAttribute("dimgoodslist", result.getData());
		try {
			// 重定向到搜索商品界面
			response.sendRedirect("usersearch.jsp?title="+userinputtitle+"&dr=price&current=1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 将用户搜索出来的商品按销量排序(第一页)
	// 按销量排序用户搜索出来的商品(第一页)
	private void salesinputSort(HttpServletRequest request, HttpServletResponse response) {
		// 获取用户输入的内容
		System.out.println("用户输入的内容：" + userinputtitle);
		// 调用service层的方法
		GoodsService dservice = new GoodsServiceImpl();
		// 更具用户输入的内容查询商品并排序
		ResultModel result = dservice.selectSalesBynameSort(userinputtitle);
		// 将商品列表存入到session中，并跳转到usersearch.jsp页面
		HttpSession session = request.getSession();
		session.setAttribute("dimgoodslist", result.getData());
		try {
			// 重定向到搜索商品界面
			response.sendRedirect("usersearch.jsp?title="+userinputtitle+"&dr=sales&current=1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 按价格排序(第一页)
	// 商品搜索页面，商品按照价格排序显示(第一页)
	private void priceSort(HttpServletRequest request, HttpServletResponse response) {
		// 调用service层的方法
		GoodsService gservice = new GoodsServiceImpl();
		ResultModel result = gservice.priceSort();
		// 将商品列表存入到session中，并跳转到usersearch.jsp页面
		HttpSession session = request.getSession();
		session.setAttribute("dimgoodslist", result.getData());
		try {
			// 重定向到搜索商品界面
			response.sendRedirect("usersearch.jsp?dr=price&title=no&current=1");
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	// 按销量排序(第一页)
	// 商品搜索页面，按销量排序的方法(第一页)
	private void salesSort(HttpServletRequest request, HttpServletResponse response) {
		// 调用service层的方法
		GoodsService gservice = new GoodsServiceImpl();
		ResultModel result = gservice.salesSort();
		// 将商品列表存入到session中，并跳转到usersearch.jsp页面
		HttpSession session = request.getSession();
		session.setAttribute("dimgoodslist", result.getData());
		try {
			// 重定向到搜索商品界面
			response.sendRedirect("usersearch.jsp?dr=sales&title=no&current=1");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 模糊查询所有商品(第一页)
	// 根据用户输入的内容模糊查询所有商品(第一页)
	private void selectgoodsBytitle(HttpServletRequest request, HttpServletResponse response) {
		// 获取用户输入的内容
		String title = request.getParameter("title");
		title = title.trim();
		// System.out.println(title);
		HttpSession session = request.getSession();
		if (title == "") {
			// 如果用户没有输入内容，则查询所有商品
			// 调用service层的方法
			GoodsService gservice = new GoodsServiceImpl();
			ResultModel result = gservice.goodsAll();
			// 查询到了数据,将商品列表存入到session中并跳转到usersearch.jsp页面中
			session.setAttribute("dimgoodslist", result.getData());
			try {
				response.sendRedirect("usersearch.jsp?title=no&current=1");
			} catch (IOException e) {
				e.printStackTrace();
			}
		} else {
			// 如果用户输入了内容，则模糊查询相关商品
			// 将用户输入的内容存到成员变量中
			userinputtitle = title;
			GoodsService gservice = new GoodsServiceImpl();
			ResultModel result = gservice.goodsByinput(title);
			if ("0".equals(result.getCode())) {
				// 查询到了数据,将商品列表存入到session中并跳转到usersearch.jsp页面中
				session.setAttribute("dimgoodslist", result.getData());
				try {
					response.sendRedirect("usersearch.jsp?title=" + title+"&current=1");
				} catch (IOException e) {
					e.printStackTrace();
				}

			} else {
				// 模糊查询没有查到则查询所有商品
				// 调用service层的方法
				ResultModel resultno = gservice.goodsAll();
				// 查询到了数据,将商品列表存入到session中并跳转到usersearch.jsp页面中
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

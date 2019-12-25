package com.softeem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softeem.model.ResultModel;
import com.softeem.service.ReturnService;
import com.softeem.service.impl.ReturnServiceImpl;


@WebServlet("/merchant/returns")
public class MReturnServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public MReturnServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//设置请求头编码
				request.setCharacterEncoding("utf-8");
				//设置响应头编码
				response.setCharacterEncoding("utf-8");
				String method = request.getParameter("method");
				switch(method){
				case "findByRPage":
					findByRPage(request,response);
					break;
				case "tuihuo":
					tuiHuo(request,response);
					break;
				default:
					break;
				}
	}

	//确认退货
	private void tuiHuo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String id = request.getParameter("id");
		ReturnService rs = new ReturnServiceImpl();
		ResultModel result = rs.agreeTuiHuo(id);
		if("0".equals(result.getCode())){
			//成功
			request.setAttribute("updateReturn", 3);
			request.getRequestDispatcher("retruns.jsp").forward(request, response);
		}else{
			//失败
			request.setAttribute("updateReturn", 4);
			request.getRequestDispatcher("retruns.jsp").forward(request, response);
		}
	}


	//按页查询列表
	private void findByRPage(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		String mid = request.getParameter("mid");
		ReturnService rs = new ReturnServiceImpl();
		ResultModel result = rs.selectByRPage(page, mid);
		if("0".equals(result.getCode())){
			//查到了
			HttpSession session = request.getSession();
			session.setAttribute("returnsList", result.getData());
			session.setAttribute("retrunsPage", page);
			request.getRequestDispatcher("retruns.jsp").forward(request, response);
		}else{
			//没有查询到数据
			request.setAttribute("selectreturns", 1);
			if(page == 1){
				request.setAttribute("retrunsPage", page);
			}else{
				request.setAttribute("retrunsPage", page-1);
			}
			request.getRequestDispatcher("retruns.jsp").forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

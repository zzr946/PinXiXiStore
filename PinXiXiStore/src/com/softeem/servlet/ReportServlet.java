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
 * 用户举报相关
 * @author 赵志然
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
		// 设置请求头、响应头编码
				request.setCharacterEncoding("utf-8");
				response.setCharacterEncoding("utf-8");
				System.out.println("请求进来了");
				// 获取请求
				String method = request.getParameter("method");
				System.out.println(method);
				switch (method) {
				case "reportGoods":
					//举报的方法
					reportGoods(request,response);
					break;

				default:
					break;
				}
	}

	//用户举报商品的方法
	private void reportGoods(HttpServletRequest request, HttpServletResponse response) {
		//获取举报的用户id
		String uid=request.getParameter("uid");
		if("".equals(uid)){
			//如果没登录则跳转到原页面提示登录
			try {
				response.sendRedirect("details.jsp?plaselogin=0");
			} catch (IOException e) {
				e.printStackTrace();
			}
			return;
		}
		
		
		//获取举报的商品
		String gid=request.getParameter("gid");
		//获取举报的商品名称
		String goodsname=request.getParameter("goodsname");
		//获取举报原因
		String reportcause=request.getParameter("reportcause");
		//获取举报详情
		String reportcontent=request.getParameter("reportcontent");
		//调用service层的方法将举报信息存到举报表中
		ReportService rservice=new ReportServiceImpl();
		ResultModel result=rservice.report(uid, gid, goodsname, reportcause, reportcontent);
		//将举报结果装入request返回到前台
		request.setAttribute("reportresult", result);
		if("0".equals(result.getCode())){
			//举报成功
			//从定向到商品详情界面
			try {
				response.sendRedirect("details.jsp?represult=0");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}else{
			//举报失败
			//从定向到商品详情界面
			try {
				response.sendRedirect("details.jsp?represult=1");
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
		
		
		
		
		
	}

}

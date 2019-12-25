package com.softeem.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.softeem.model.ResultModel;
import com.softeem.service.CommentService;
import com.softeem.service.impl.CommentServiceImpl;


@WebServlet("/merchant/comment")
public class CommentServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public CommentServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//设置请求头编码
				request.setCharacterEncoding("utf-8");
				//设置响应头编码
				response.setCharacterEncoding("utf-8");
				String method = request.getParameter("method");
				switch(method){
				case "findComment":
					findComment(request,response);
					break;
				case "deleteComment":
					deleteComment(request,response);
					break;
					default:
						break;
				}
	}

	//删除评论
	private void deleteComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//获取到评论id
		String cid = request.getParameter("cid");
		CommentService cs = new CommentServiceImpl();
		ResultModel result = cs.deleteByCid(cid);
		if("0".equals(result.getCode())){
			//删除成功
			request.setAttribute("deleteComment", 1);
			request.getRequestDispatcher("comment.jsp").forward(request, response);
		}else{
			//删除失败
			request.setAttribute("deleteComment", 2);
			request.getRequestDispatcher("comment.jsp").forward(request, response);
		}
	}


	//加载指定页数的评论列表
	private void findComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int page = Integer.parseInt(request.getParameter("page"));
		CommentService cs = new CommentServiceImpl();
		ResultModel result = cs.selectByCPage(page);
		if("0".equals(result.getCode())){
			//列表不为空
			HttpSession session = request.getSession();
			session.setAttribute("commentList", result.getData());
			session.setAttribute("commentPage", page);
			request.getRequestDispatcher("comment.jsp").forward(request, response);
		}else{
			//列表为空
			request.setAttribute("selectcomment", 1);
			if(page == 1){
				request.setAttribute("commentPage", page);
			}else{
				request.setAttribute("commentPage", page-1);
			}
			request.getRequestDispatcher("comment.jsp").forward(request, response);
		}
		
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}

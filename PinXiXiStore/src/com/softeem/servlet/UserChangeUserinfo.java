package com.softeem.servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.softeem.model.ResultModel;
import com.softeem.service.UserService;
import com.softeem.service.impl.UserServiceImpl;
import com.softeem.tools.Constant;
import com.softeem.tools.Tools;

@WebServlet("/user_changeuserinfo")
public class UserChangeUserinfo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    public UserChangeUserinfo() {
        super();
    }
    
    
    

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//调用Service层的方法
			UserService uservice=new UserServiceImpl();
			//设置请求头、响应头编码
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			System.out.println("用户修改用户信息的请求进来了");
			//创建一个smartUpload对象
			SmartUpload upload=new SmartUpload();
			//初始化SmartUpload对象
			upload.initialize(this, request, response);
			//开始上传头像
			upload.upload();
			Files files=upload.getFiles();
			//获取用户上传的头像文件的信息
			String photo = "";//存储的头像名字
			for(int i=0;i<files.getCount();i++){
				if(files.getSize()>0){
					File file=files.getFile(i);
//					System.out.println("文件名："+file.getFileName());
//					System.out.println("字段名："+file.getFieldName());
//					System.out.println("后缀名："+file.getFileExt());
//					System.out.println("单个文件的大小："+file.getSize());
//					System.out.println("文件类型："+file.getContentType());
					//文件重命名
					photo=UUID.randomUUID().toString()+"."+file.getFileExt();
					//将文件存到指定的位置
					file.saveAs(Constant.BASE_PATH+"/"+photo);
				}
			}
			
			//获取文件之外的表单数据
			//获取SmartUpload提供的request对象
			Request req=upload.getRequest();
			//获取昵称
			String nickname=req.getParameter("hiddennickname");
			//设置字符集处理中文乱码
//			nickname = new String(nickname.getBytes("gbk"),"utf-8");
			nickname=Tools.Gbktoutf(nickname);
			System.out.println("昵称："+nickname);
			//获取姓名
			String name=req.getParameter("hiddenname");
			//设置字符集处理中文乱码
//			name = new String(name.getBytes("gbk"),"utf-8");
			name=Tools.Gbktoutf(name);
			System.out.println("姓名："+name);
			//获取性别
			String sex=req.getParameter("sex");
			switch (sex) {
			case "nan":
				sex="男";
				break;
			case "nv":
				sex="女";
				break;
			case "no":
				sex="保密";
				break;
			default:
				break;
			}
			System.out.println("性别："+sex);
			//获取当前用户的id
			String uid=req.getParameter("hiddenuid");
//			System.out.println("uid"+uid);
			
			ResultModel result=uservice.changeUserinfo(uid, photo, nickname, name, sex);
			if("0".equals(result.getCode())){
				//修改成功
				//将修改信息的结果存入request中
				request.getSession().setAttribute("userlogin", result.getData());
				request.setAttribute("changeuserinfook", "修改成功");
				//重定向到用户修改个人信息的界面
				response.sendRedirect("userinfo.jsp");
			}else{
				//修改失败
				request.setAttribute("changeuserinfoerror", "修改失败");
				//请求转发到用户修改个人资料页面
				request.getRequestDispatcher("userinfo.jsp").forward(request, response);
			}
			
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}

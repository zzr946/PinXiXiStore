package com.softeem.servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;
import com.softeem.dao.AdminDAO;
import com.softeem.model.Admin;
import com.softeem.model.ResultModel;
import com.softeem.service.AdminService;
import com.softeem.service.impl.AdminServiceImpl;

@WebServlet("/admin1/upload")
public class AdminServletupload extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	//文件上传后的存储路径
	public static final String BASE_PATH = "D:/file";
	
    public AdminServletupload() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//创建出一个Smartupload对象
			SmartUpload upload = new SmartUpload();
			//初始化Smartupload对象
			upload.initialize(this, request, response);
			//允许设置上传的文件类型
			upload.setAllowedFilesList("jpg,png,gif,jpeg");
			upload.upload();
			//获取文件信息
			Files files = upload.getFiles();
			//多个文件循环存入
			String new_name=null;
			if(files.getSize()>0){
				File file = files.getFile(0);
				//文件重命名
				new_name = UUID.randomUUID().toString()+"."+file.getFileExt();
				//存
				file.saveAs(BASE_PATH + "/" + new_name);
			}
			HttpSession session = request.getSession();
			Admin admin = (Admin)session.getAttribute("adminlogin");
			AdminDAO admindao = new AdminDAO();
			admindao.changephoto(new_name,admin.getAdminaccount());	
			AdminService adminservice = new AdminServiceImpl();
			ResultModel result = adminservice.loadbyadminaccount(admin.getAdminaccount(),admin.getAdminpassword());
			if("0".equals(result.getCode())){
				//登录成功，跳转到主界面，并将结果集保存在session中
				HttpSession session1 = request.getSession();
				session1.setAttribute("adminlogin",result.getData());
				try {
					//页面跳转
					response.sendRedirect("adminmain.jsp");
				} catch (IOException e) {
					e.printStackTrace(); 
				}
			}else{
				//登录失败
				//跳转到当前登录界面，将结果存到request中
				request.setAttribute("loginByadminaccounterror", result.getMsg());
				try {
					request.getRequestDispatcher("adminmain.jsp").forward(request, response);
				} catch (ServletException | IOException e) {
					e.printStackTrace();
				}
			}
		} catch (SmartUploadException e) {
			e.printStackTrace();
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

}

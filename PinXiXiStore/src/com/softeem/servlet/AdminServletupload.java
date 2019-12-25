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
       
	//�ļ��ϴ���Ĵ洢·��
	public static final String BASE_PATH = "D:/file";
	
    public AdminServletupload() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			//������һ��Smartupload����
			SmartUpload upload = new SmartUpload();
			//��ʼ��Smartupload����
			upload.initialize(this, request, response);
			//���������ϴ����ļ�����
			upload.setAllowedFilesList("jpg,png,gif,jpeg");
			upload.upload();
			//��ȡ�ļ���Ϣ
			Files files = upload.getFiles();
			//����ļ�ѭ������
			String new_name=null;
			if(files.getSize()>0){
				File file = files.getFile(0);
				//�ļ�������
				new_name = UUID.randomUUID().toString()+"."+file.getFileExt();
				//��
				file.saveAs(BASE_PATH + "/" + new_name);
			}
			HttpSession session = request.getSession();
			Admin admin = (Admin)session.getAttribute("adminlogin");
			AdminDAO admindao = new AdminDAO();
			admindao.changephoto(new_name,admin.getAdminaccount());	
			AdminService adminservice = new AdminServiceImpl();
			ResultModel result = adminservice.loadbyadminaccount(admin.getAdminaccount(),admin.getAdminpassword());
			if("0".equals(result.getCode())){
				//��¼�ɹ�����ת�������棬���������������session��
				HttpSession session1 = request.getSession();
				session1.setAttribute("adminlogin",result.getData());
				try {
					//ҳ����ת
					response.sendRedirect("adminmain.jsp");
				} catch (IOException e) {
					e.printStackTrace(); 
				}
			}else{
				//��¼ʧ��
				//��ת����ǰ��¼���棬������浽request��
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

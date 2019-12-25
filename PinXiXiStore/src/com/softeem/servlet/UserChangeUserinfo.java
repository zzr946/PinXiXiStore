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
			//����Service��ķ���
			UserService uservice=new UserServiceImpl();
			//��������ͷ����Ӧͷ����
			request.setCharacterEncoding("utf-8");
			response.setCharacterEncoding("utf-8");
			System.out.println("�û��޸��û���Ϣ�����������");
			//����һ��smartUpload����
			SmartUpload upload=new SmartUpload();
			//��ʼ��SmartUpload����
			upload.initialize(this, request, response);
			//��ʼ�ϴ�ͷ��
			upload.upload();
			Files files=upload.getFiles();
			//��ȡ�û��ϴ���ͷ���ļ�����Ϣ
			String photo = "";//�洢��ͷ������
			for(int i=0;i<files.getCount();i++){
				if(files.getSize()>0){
					File file=files.getFile(i);
//					System.out.println("�ļ�����"+file.getFileName());
//					System.out.println("�ֶ�����"+file.getFieldName());
//					System.out.println("��׺����"+file.getFileExt());
//					System.out.println("�����ļ��Ĵ�С��"+file.getSize());
//					System.out.println("�ļ����ͣ�"+file.getContentType());
					//�ļ�������
					photo=UUID.randomUUID().toString()+"."+file.getFileExt();
					//���ļ��浽ָ����λ��
					file.saveAs(Constant.BASE_PATH+"/"+photo);
				}
			}
			
			//��ȡ�ļ�֮��ı�����
			//��ȡSmartUpload�ṩ��request����
			Request req=upload.getRequest();
			//��ȡ�ǳ�
			String nickname=req.getParameter("hiddennickname");
			//�����ַ���������������
//			nickname = new String(nickname.getBytes("gbk"),"utf-8");
			nickname=Tools.Gbktoutf(nickname);
			System.out.println("�ǳƣ�"+nickname);
			//��ȡ����
			String name=req.getParameter("hiddenname");
			//�����ַ���������������
//			name = new String(name.getBytes("gbk"),"utf-8");
			name=Tools.Gbktoutf(name);
			System.out.println("������"+name);
			//��ȡ�Ա�
			String sex=req.getParameter("sex");
			switch (sex) {
			case "nan":
				sex="��";
				break;
			case "nv":
				sex="Ů";
				break;
			case "no":
				sex="����";
				break;
			default:
				break;
			}
			System.out.println("�Ա�"+sex);
			//��ȡ��ǰ�û���id
			String uid=req.getParameter("hiddenuid");
//			System.out.println("uid"+uid);
			
			ResultModel result=uservice.changeUserinfo(uid, photo, nickname, name, sex);
			if("0".equals(result.getCode())){
				//�޸ĳɹ�
				//���޸���Ϣ�Ľ������request��
				request.getSession().setAttribute("userlogin", result.getData());
				request.setAttribute("changeuserinfook", "�޸ĳɹ�");
				//�ض����û��޸ĸ�����Ϣ�Ľ���
				response.sendRedirect("userinfo.jsp");
			}else{
				//�޸�ʧ��
				request.setAttribute("changeuserinfoerror", "�޸�ʧ��");
				//����ת�����û��޸ĸ�������ҳ��
				request.getRequestDispatcher("userinfo.jsp").forward(request, response);
			}
			
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
		
		
		
		
		
		
	}

}

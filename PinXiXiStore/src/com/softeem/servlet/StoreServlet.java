package com.softeem.servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softeem.model.Merchant;
import com.softeem.model.ResultModel;
import com.softeem.service.MerchantService;
import com.softeem.service.StoreService;
import com.softeem.service.impl.MerchantServiceImpl;
import com.softeem.service.impl.StoreServiceImpl;
import com.softeem.smartupload.SmartFile;
import com.softeem.smartupload.SmartFiles;
import com.softeem.smartupload.SmartRequest;
import com.softeem.smartupload.SmartUpload;
import com.softeem.smartupload.SmartUploadException;
import com.softeem.tools.Constant;

@WebServlet("/merchant/store")
public class StoreServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
    public StoreServlet() {
        super();
    }

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				//��������ͷ����
				request.setCharacterEncoding("utf-8");
				//������Ӧͷ����
				response.setCharacterEncoding("utf-8");
				System.out.println("���Խ�����");
				try {
					System.out.println("������Ϣ�ϴ�");
					//����һ��smartUpload����
					SmartUpload up=new SmartUpload();
					//��ʼ��SmartUpload����
					up.initialize(this, request, response);
					//��ʼ�ϴ��ļ�
					up.upload();
					SmartFiles files=up.getFiles();
					//��ȡ�û��ϴ����ļ�
					String picture_logo = "";//�洢��logo����
					String picture_aptitude = "";//�洢������֤��������
					for(int i=0;i<files.getCount();i++){
						if(files.getSize()>0){
							SmartFile file=files.getFile(i);
							System.out.println("�ֶ�����"+file.getFieldName());
							//���ļ��浽ָ����λ��
							if(file.getFieldName().equals("storelogo")){
								picture_logo=UUID.randomUUID().toString()+"."+file.getFileExt();
								file.saveAs(Constant.STORE_LOGO_PATH+"/"+picture_logo);
							}else{
								//�ļ�������
								picture_aptitude=UUID.randomUUID().toString()+"."+file.getFileExt();
								file.saveAs(Constant.STORE_APTITUDE_PATH+"/"+picture_aptitude);
							}
						}
					}
					//��ȡ�ļ�֮��ı�����
					//��ȡSmartUpload�ṩ��request����
					SmartRequest req=up.getRequest();
					//��ȡ������
					String storename = req.getParameter("storename");
					//��ȡ����logo��
					String storelogo = picture_logo;
					//��ȡ���̼��
					String storeinfo = req.getParameter("storeinfo");
					//��ȡ��������֤����
					String aptitude = picture_aptitude;
					//��ȡ��������֤����
					String storeaddress = req.getParameter("storeaddress");	
					//���ɵ���id
					String sid = UUID.randomUUID().toString();
					//����Service��ķ���
					StoreService ss = new StoreServiceImpl();
					String mid = req.getParameter("mid");
					MerchantService ms = new MerchantServiceImpl();
					ms.addSid(sid, mid);
					ResultModel result = ss.reg(sid,storename, storelogo, storeinfo, storeaddress, aptitude);
					if("0".equals(result.getCode())){
						request.setAttribute("createstore", 1);
						request.getRequestDispatcher("createStore.jsp").forward(request, response);
					}else{
						//����ʧ��
						request.setAttribute("createstore", 2);
						request.getRequestDispatcher("createStore.jsp").forward(request, response);
					}
					
				} catch (SmartUploadException e) {
					e.printStackTrace();
				}
	}

}

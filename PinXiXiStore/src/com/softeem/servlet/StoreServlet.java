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
				//设置请求头编码
				request.setCharacterEncoding("utf-8");
				//设置响应头编码
				response.setCharacterEncoding("utf-8");
				System.out.println("测试进来了");
				try {
					System.out.println("店铺信息上传");
					//创建一个smartUpload对象
					SmartUpload up=new SmartUpload();
					//初始化SmartUpload对象
					up.initialize(this, request, response);
					//开始上传文件
					up.upload();
					SmartFiles files=up.getFiles();
					//获取用户上传的文件
					String picture_logo = "";//存储的logo名字
					String picture_aptitude = "";//存储的资质证明的名字
					for(int i=0;i<files.getCount();i++){
						if(files.getSize()>0){
							SmartFile file=files.getFile(i);
							System.out.println("字段名："+file.getFieldName());
							//将文件存到指定的位置
							if(file.getFieldName().equals("storelogo")){
								picture_logo=UUID.randomUUID().toString()+"."+file.getFileExt();
								file.saveAs(Constant.STORE_LOGO_PATH+"/"+picture_logo);
							}else{
								//文件重命名
								picture_aptitude=UUID.randomUUID().toString()+"."+file.getFileExt();
								file.saveAs(Constant.STORE_APTITUDE_PATH+"/"+picture_aptitude);
							}
						}
					}
					//获取文件之外的表单数据
					//获取SmartUpload提供的request对象
					SmartRequest req=up.getRequest();
					//获取店铺名
					String storename = req.getParameter("storename");
					//获取店铺logo名
					String storelogo = picture_logo;
					//获取店铺简介
					String storeinfo = req.getParameter("storeinfo");
					//获取店铺资质证书名
					String aptitude = picture_aptitude;
					//获取店铺资质证书名
					String storeaddress = req.getParameter("storeaddress");	
					//生成店铺id
					String sid = UUID.randomUUID().toString();
					//调用Service层的方法
					StoreService ss = new StoreServiceImpl();
					String mid = req.getParameter("mid");
					MerchantService ms = new MerchantServiceImpl();
					ms.addSid(sid, mid);
					ResultModel result = ss.reg(sid,storename, storelogo, storeinfo, storeaddress, aptitude);
					if("0".equals(result.getCode())){
						request.setAttribute("createstore", 1);
						request.getRequestDispatcher("createStore.jsp").forward(request, response);
					}else{
						//创建失败
						request.setAttribute("createstore", 2);
						request.getRequestDispatcher("createStore.jsp").forward(request, response);
					}
					
				} catch (SmartUploadException e) {
					e.printStackTrace();
				}
	}

}

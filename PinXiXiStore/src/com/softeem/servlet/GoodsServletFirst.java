package com.softeem.servlet;

import java.io.IOException;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.softeem.dao.SortDAO;
import com.softeem.model.Goods;
import com.softeem.model.ResultModel;
import com.softeem.service.GoodsService;
import com.softeem.service.impl.GoodsServiceImpl;
import com.softeem.smartupload.SmartFile;
import com.softeem.smartupload.SmartFiles;
import com.softeem.smartupload.SmartRequest;
import com.softeem.smartupload.SmartUpload;
import com.softeem.smartupload.SmartUploadException;
import com.softeem.tools.Constant;
import com.softeem.tools.Tools;


@WebServlet("/merchant/goods")
public class GoodsServletFirst extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public GoodsServletFirst() {
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
			System.out.println("��Ʒ��Ϣ�ϴ�");
			//����һ��smartUpload����
			SmartUpload up=new SmartUpload();
			//��ʼ��SmartUpload����
			up.initialize(this, request, response);
			//��ʼ�ϴ��ļ�
			up.upload();
			SmartFiles files=up.getFiles();
			//��ȡ�û��ϴ����ļ�
			String goodsimage = "";//�洢����ƷͼƬ
			for(int i=0;i<files.getCount();i++){
				if(files.getSize()>0){
					SmartFile file=files.getFile(i);
					System.out.println("�ֶ�����"+file.getFieldName());
					//���ļ��浽ָ����λ��
					goodsimage=UUID.randomUUID().toString()+"."+file.getFileExt();
					file.saveAs(Constant.GOODS_PICTURE+'/'+goodsimage);
				}
			}
			//��ȡ�ļ�֮��ı�����
			//��ȡSmartUpload�ṩ��request����
			SmartRequest req=up.getRequest();
			//��ȡ�̼�id
			String mid = req.getParameter("mid");
			System.out.println(mid);
			//��ȡ��Ʒ��
			String goodsname = req.getParameter("goodsname");
			System.out.println(goodsname);
			//��ȡ��ѡ�������
			String selectsortname = req.getParameter("select");
			System.out.println(selectsortname);
			//��ȡ���id
			SortDAO sdao = new SortDAO();
			String sid = sdao.selectSid(mid, selectsortname);
			System.out.println(sid);
			//��ȡ��Ʒ�۸�
			double goodsprice = Double.parseDouble(req.getParameter("goodsprice"));
			System.out.println(goodsprice);
			//��ȡ��Ʒ��ʼ�����Ŀ��
			int goodstotal = Integer.parseInt(req.getParameter("goodstotal"));
			System.out.println(goodstotal);
			//��ȡ������λ
			String jldw = req.getParameter("reserved1");
			System.out.println(jldw);
			String goodsinfo = req.getParameter("goodsinfo");
			System.out.println(goodsinfo);
			//����gid
			String gid = UUID.randomUUID().toString();
			//������Ʒ���
			String goodsnumber = Tools.getNum();
			//������Ʒ״̬�����ϼ�
//			goods.setGoodsdr(0);
			//discountprice�ȸ�ԭ�۸�,������0��״̬��0�ϼ�,goodsdiscount��1���������
			Goods goods = new Goods(gid, goodsnumber, goodstotal, mid, goodsname, goodsprice, goodsprice, 0, 0,
					goodsinfo, 1, goodsimage, sid, jldw, null,  null,  null,  null);
			//����Service��ķ���
			GoodsService ss = new GoodsServiceImpl();
			ResultModel result = ss.addGoods(goods);
			if("0".equals(result.getCode())){
				//��ӳɹ�
				request.setAttribute("addGoods", 1);
				request.getRequestDispatcher("addGoods.jsp").forward(request, response);
			}else{
				//���ʧ��
				request.setAttribute("addGoods", 2);
				request.getRequestDispatcher("addGoods.jsp").forward(request, response);
			}
			
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
}
}



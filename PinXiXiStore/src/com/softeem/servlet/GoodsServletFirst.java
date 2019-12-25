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
		//设置请求头编码
		request.setCharacterEncoding("utf-8");
		//设置响应头编码
		response.setCharacterEncoding("utf-8");
		System.out.println("测试进来了");
		try {
			System.out.println("商品信息上传");
			//创建一个smartUpload对象
			SmartUpload up=new SmartUpload();
			//初始化SmartUpload对象
			up.initialize(this, request, response);
			//开始上传文件
			up.upload();
			SmartFiles files=up.getFiles();
			//获取用户上传的文件
			String goodsimage = "";//存储的商品图片
			for(int i=0;i<files.getCount();i++){
				if(files.getSize()>0){
					SmartFile file=files.getFile(i);
					System.out.println("字段名："+file.getFieldName());
					//将文件存到指定的位置
					goodsimage=UUID.randomUUID().toString()+"."+file.getFileExt();
					file.saveAs(Constant.GOODS_PICTURE+'/'+goodsimage);
				}
			}
			//获取文件之外的表单数据
			//获取SmartUpload提供的request对象
			SmartRequest req=up.getRequest();
			//获取商家id
			String mid = req.getParameter("mid");
			System.out.println(mid);
			//获取商品名
			String goodsname = req.getParameter("goodsname");
			System.out.println(goodsname);
			//获取所选的类别名
			String selectsortname = req.getParameter("select");
			System.out.println(selectsortname);
			//获取类别id
			SortDAO sdao = new SortDAO();
			String sid = sdao.selectSid(mid, selectsortname);
			System.out.println(sid);
			//获取商品价格
			double goodsprice = Double.parseDouble(req.getParameter("goodsprice"));
			System.out.println(goodsprice);
			//获取商品开始给定的库存
			int goodstotal = Integer.parseInt(req.getParameter("goodstotal"));
			System.out.println(goodstotal);
			//获取计量单位
			String jldw = req.getParameter("reserved1");
			System.out.println(jldw);
			String goodsinfo = req.getParameter("goodsinfo");
			System.out.println(goodsinfo);
			//生成gid
			String gid = UUID.randomUUID().toString();
			//生成商品编号
			String goodsnumber = Tools.getNum();
			//设置商品状态先是上架
//			goods.setGoodsdr(0);
			//discountprice先给原价格,销量给0，状态给0上架,goodsdiscount给1不参与促销
			Goods goods = new Goods(gid, goodsnumber, goodstotal, mid, goodsname, goodsprice, goodsprice, 0, 0,
					goodsinfo, 1, goodsimage, sid, jldw, null,  null,  null,  null);
			//调用Service层的方法
			GoodsService ss = new GoodsServiceImpl();
			ResultModel result = ss.addGoods(goods);
			if("0".equals(result.getCode())){
				//添加成功
				request.setAttribute("addGoods", 1);
				request.getRequestDispatcher("addGoods.jsp").forward(request, response);
			}else{
				//添加失败
				request.setAttribute("addGoods", 2);
				request.getRequestDispatcher("addGoods.jsp").forward(request, response);
			}
			
		} catch (SmartUploadException e) {
			e.printStackTrace();
		}
}
}



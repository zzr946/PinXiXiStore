package com.softeem.service.impl;

import java.util.Date;

import com.softeem.dao.ReportDAO;
import com.softeem.model.Goods;
import com.softeem.model.Report;
import com.softeem.model.ResultModel;
import com.softeem.service.ReportService;
import com.softeem.tools.Tools;

public class ReportServiceImpl implements ReportService {
	private ReportDAO rdao=new ReportDAO();
	
	/**
	 * 用户举报的方法
	 */
	@Override
	public ResultModel report(String uid, String gid, String goodsname, String reportcause, String reportcontent) {
		ResultModel result=new ResultModel();
		//获取id
		String id=Tools.getUUID();
		//获取举报时间
		String time=Tools.dateToStr("yyyy-MM-dd HH:mm:ss", new Date());
		//根据商品id获取商家id
		Goods goods=rdao.selectBygid(gid);
		String mid=goods.getMid();
		//将举报信息装入举报对象
		Report rep=new Report();
		rep.setId(id);
		rep.setUid(uid);
		rep.setGid(gid);
		rep.setGoodsname(goodsname);
		rep.setMid(mid);
		rep.setCause(reportcause);
		rep.setContent(reportcontent);
		rep.setReporttime(time);
		//调用dao层的方法将举报信息存入举报表
		boolean boo=rdao.insertReport(rep);
		if(boo){
			//举报成功
			result.setCode("0");
			result.setMsg("举报成功");
			result.setData(null);
		}else{
			//举报失败
			result.setCode("1");
			result.setMsg("举报失败");
			result.setData(null);
		}
		return result;
	}

}

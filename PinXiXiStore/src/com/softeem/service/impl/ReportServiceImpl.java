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
	 * �û��ٱ��ķ���
	 */
	@Override
	public ResultModel report(String uid, String gid, String goodsname, String reportcause, String reportcontent) {
		ResultModel result=new ResultModel();
		//��ȡid
		String id=Tools.getUUID();
		//��ȡ�ٱ�ʱ��
		String time=Tools.dateToStr("yyyy-MM-dd HH:mm:ss", new Date());
		//������Ʒid��ȡ�̼�id
		Goods goods=rdao.selectBygid(gid);
		String mid=goods.getMid();
		//���ٱ���Ϣװ��ٱ�����
		Report rep=new Report();
		rep.setId(id);
		rep.setUid(uid);
		rep.setGid(gid);
		rep.setGoodsname(goodsname);
		rep.setMid(mid);
		rep.setCause(reportcause);
		rep.setContent(reportcontent);
		rep.setReporttime(time);
		//����dao��ķ������ٱ���Ϣ����ٱ���
		boolean boo=rdao.insertReport(rep);
		if(boo){
			//�ٱ��ɹ�
			result.setCode("0");
			result.setMsg("�ٱ��ɹ�");
			result.setData(null);
		}else{
			//�ٱ�ʧ��
			result.setCode("1");
			result.setMsg("�ٱ�ʧ��");
			result.setData(null);
		}
		return result;
	}

}

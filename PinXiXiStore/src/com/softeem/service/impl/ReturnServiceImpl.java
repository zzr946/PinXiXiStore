package com.softeem.service.impl;

import java.util.List;

import com.softeem.dao.ReturnDAO;
import com.softeem.model.Goods;
import com.softeem.model.ResultModel;
import com.softeem.model.Return;
import com.softeem.service.ReturnService;

public class ReturnServiceImpl implements ReturnService {

	ReturnDAO rdao = new ReturnDAO();
	//��ҳ��ѯ�б�
	@Override
	public ResultModel selectByRPage(int page, String mid) {
		ResultModel result = new ResultModel();
		List<Return> list = rdao.selectByRPage(page, mid);
		if(list.isEmpty()){
			result.setCode("1");
			result.setMsg("û�鵽");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("�鵽��");
			result.setData(list);
		}
		return result;
	}
	//ȷ���˻�
	@Override
	public ResultModel agreeTuiHuo(String id) {
		ResultModel result = new ResultModel();
		boolean boo = rdao.agreeTuiHuo(id);
		if(boo){
			//�˻��ɹ�
			result.setCode("0");
			result.setMsg("�ɹ�");
			result.setData(null);
		}else{
			//�˻�ʧ��
			result.setCode("1");
			result.setMsg("ʧ��");
			result.setData(null);
		}
		return result;
	}

}

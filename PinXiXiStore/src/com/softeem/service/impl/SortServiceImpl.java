package com.softeem.service.impl;

import java.util.List;
import java.util.UUID;

import com.softeem.dao.SortDAO;
import com.softeem.model.ResultModel;
import com.softeem.model.Sort;
import com.softeem.service.SortService;

public class SortServiceImpl implements SortService {

	SortDAO sdao = new SortDAO();
	
	@Override
	public ResultModel addSort(String sortname,String sortinfo,String merchantid){
		//�������Ƿ����
		boolean boo = sdao.selectBySortName(sortname);
		ResultModel result = new ResultModel();
		if(boo){
			result.setCode("2");
			result.setMsg("������Ѵ���");
			result.setData(null);
			return result;
		}
		//��������ڣ�������
		String sid = UUID.randomUUID().toString();
		Sort sort = new Sort();
		sort.setSid(sid);
		sort.setSortname(sortname);
		sort.setSortinfo(sortinfo);
		sort.setMerchantid(merchantid);
		boo = sdao.insert(sort);
		if(boo){
			result.setCode("0");
			result.setMsg("��ӳɹ�");
			result.setData(sort);
		}else{
			result.setCode("1");
			result.setMsg("���ʧ��");
			result.setData(null);
		}
		return result;
	}

	//��ҳ��ѯ����
	@Override
	public ResultModel selectByPage(String mid, int page) {
		ResultModel result = new ResultModel();
		List<Sort> list = sdao.selectByPage(mid, page);
		if(list.isEmpty()){
			result.setCode("1");
			result.setMsg("���޷���");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("�鵽��");
			result.setData(list);
		}
		return result;
	}

	//ɾ������
	@Override
	public ResultModel deleteBySid(String sid) {
		ResultModel result = new ResultModel();
		boolean boo = sdao.deleteBySid(sid);
		if(boo){
			result.setCode("0");
			result.setMsg("ɾ���ɹ�");;
			result.setData(null);
		}else{
			result.setCode("1");
			result.setMsg("ɾ��ʧ��");;
			result.setData(null);
		}
		return result;
	}

	@Override
	public ResultModel updateSort(String sortname1, String sortname2, String sortinfo) {
		ResultModel result = new ResultModel();
		boolean boo = sdao.updateSort(sortname1, sortname2, sortinfo);
		if(boo){
			result.setCode("0");
			result.setMsg("�޸ĳɹ�");;
			result.setData(null);
		}else{
			result.setCode("1");
			result.setMsg("�޸�ʧ��");;
			result.setData(null);
		}
		return result;
	}

	//��ѯ���з���
	@Override
	public ResultModel selectAll(String mid) {
		ResultModel result = new ResultModel();
		List<Sort> list = sdao.selectAll(mid);
		if(list.isEmpty()){
			result.setCode("1");
			result.setMsg("���޷���");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("�鵽��");
			result.setData(list);
		}
		return result;
	}

}

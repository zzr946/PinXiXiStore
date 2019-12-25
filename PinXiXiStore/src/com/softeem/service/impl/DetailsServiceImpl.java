package com.softeem.service.impl;
/**
 * ��Ʒ����ʵ����
 */
import com.softeem.dao.DetailsDAO;
import com.softeem.model.Goods;
import com.softeem.model.ResultModel;
import com.softeem.service.DetailsService;

public class DetailsServiceImpl implements DetailsService {
	private DetailsDAO ddao=new DetailsDAO();
	
	/**
	 * ������Ʒid��ѯ��Ʒ����
	 */
	@Override
	public ResultModel queryOne(String gid) {
		ResultModel result=new ResultModel();
		//����dao��ķ�����ѯ
		Goods goods=ddao.selectOneByid(gid);
		if(goods==null){
			//û�в�ѯ��
			result.setCode("1");
			result.setMsg("û��ѯ������Ʒ��Ϣ");
			result.setData(null);
		}else{
			//��ѯ����
			result.setCode("0");
			result.setMsg("��ѯ�ɹ�");
			result.setData(goods);
		}
		return result;
	}
	

}

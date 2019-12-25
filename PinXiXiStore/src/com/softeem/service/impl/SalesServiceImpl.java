package com.softeem.service.impl;

import java.util.List;

import com.softeem.dao.SalesDAO;
import com.softeem.model.Goods;
import com.softeem.model.ResultModel;
import com.softeem.model.Sales;
import com.softeem.service.SalesService;

public class SalesServiceImpl implements SalesService {
	private SalesDAO sdao=new SalesDAO();
	
	/**
	 * ��ѯ��������������Ʒ�ķ���
	 */
	@Override
	public ResultModel salesall() {
		ResultModel result=new ResultModel();
		//����dao��ķ���
		List<Goods> list=sdao.selectsalesAll();
		if(list.isEmpty()){
			//��ѯʧ��
			result.setCode("1");
			result.setMsg("��ѯʧ��");
			result.setData(null);
		}else{
			//��ѯ�ɹ�
			result.setCode("0");
			result.setMsg("��ѯ�ɹ�");
			result.setData(list);
		}
		return result;
	}
	
	
	//������������
		@Override
		public ResultModel insert(Goods goods, int salesdr, String id) {
			boolean boo = sdao.check(goods);
			ResultModel result = new ResultModel();
			if(boo){
			//�Ѵ��ڴ�������
				result.setCode("2");
				result.setMsg("�Ѵ��ڴ�������");
				result.setData(null);
			}
			//���ڴ�������,�������������
			Sales sales = new Sales();
			sales.setId(id);
			sales.setDiscountprice(goods.getDiscountprice());
			sales.setGoodsprice(goods.getGoodsprice());
			sales.setGoodsname(goods.getGoodsname());
			sales.setGid(goods.getGid());
			sales.setSalesdr(salesdr);
			boo = sdao.insertSales(sales);
			if(boo){
				result.setCode("0");
				result.setMsg("����ɹ�");
				result.setData(sales);
			}else{
				result.setCode("1");
				result.setMsg("����ʧ��");
				result.setData(null);
			}
			return result;
		}
		//�ӱ���ɾ������
		@Override
		public ResultModel delete(String gid) {
			ResultModel result = new ResultModel();
			boolean boo = sdao.deleteSales(gid);
			if(boo){
				result.setCode("0");
				result.setMsg("ɾ���ɹ�");
				result.setData(null);
			}else{
				result.setCode("1");
				result.setMsg("ɾ��ʧ��");
				result.setData(null);
			}
			return result;
		}
	

}

package com.softeem.service;

import com.softeem.model.Goods;
import com.softeem.model.ResultModel;

public interface GoodsService {
	
	//��ѯ������Ʒ�ķ���(��һҳ)
	public ResultModel goodsAll();
	
	//��ѯ������Ʒ�ķ���(ָ��ҳ��)
	public ResultModel goodsAllpage(int page);
	
	//�����û����������ģ����ѯ(��ʾ��һҳ)
	public ResultModel goodsByinput(String title);
	
	//�����û���������ݲ�ѯ�����Ʒ(ָ��ҳ��)
	public ResultModel goodsByinputpage(String title,int page);
	
	
	//�����û���������ݲ�ѯ�����Ʒ,������������(ָ��ҳ��)
	public ResultModel inputSalesSortpage(String title,int page);
	
	//�����û���������ݲ�ѯ�����Ʒ,������������(ָ��ҳ��)
	public ResultModel inputPriceSortpage(String title,int page);
	
	
	
	//��ѯ������Ʒ������������(��һҳ)
	public ResultModel salesSort();
	
	//��ѯ���а��������������Ʒ(ָ��ҳ��)
	public ResultModel salesSortpage(int page);
	
	//��ѯ������Ʒ������������
	public ResultModel priceSort();
	
	//��ѯָ��ҳ����Ʒ������������
	public ResultModel priceSortpage(int page);
	
	
	//��ѯ�û���������Ʒ��������������(��һҳ)
	public ResultModel selectSalesBynameSort(String title);
	
	
	//��ѯ�û���������Ʒ�������۸�����(��һҳ)
	public ResultModel selectPriceBynameSort(String title);
	
	
	//�����Ʒ
		public ResultModel addGoods(Goods goods);
		
		//ͨ��ҳ����ѯ��Ʒ�б�
		public ResultModel selectByPage(String mid,int page);
		
		//ͨ��ҳ����ѯ��Ʒ�б�
			public ResultModel selectBySpecialPage(String mid,int page);
		
		//�޸���Ʒ��Ϣ
		public ResultModel updateGoods(Goods goods);
		
		//��Ʒ�ϼ�
		public ResultModel shangjia(String gid);
		
		//��Ʒ�¼�
		public ResultModel xiajia(String gid);
		
		//�޸Ĵ����۸�ʹ���״̬
		public ResultModel updateDiscount(String gid,double discountprice);
		
		//�޸Ĵ����۸�ʹ���״̬
			public ResultModel updateDiscountSecond(String gid,double goodsprice);
	
	
	
	
}

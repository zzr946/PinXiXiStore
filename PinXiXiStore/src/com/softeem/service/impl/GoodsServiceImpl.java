package com.softeem.service.impl;

import java.util.List;

import com.softeem.dao.GoodsDAO;
import com.softeem.model.Goods;
import com.softeem.model.ResultModel;
import com.softeem.service.GoodsService;

public class GoodsServiceImpl implements GoodsService {
	
	private GoodsDAO gdao=new GoodsDAO();
	
	/**
	 * ��ѯ������Ʒ�ķ���(��һҳ)
	 * ����result����� 0��ʾ��ѯ�ɹ�  1��ʾδ��ѯ����Ʒ
	 */
	@Override
	public ResultModel goodsAll() {
		ResultModel result=new ResultModel();
		//����dao��ķ�����ѯ����
		List<Goods> list=gdao.selectGoodsAll();
		if(list.isEmpty()){
			//û����Ʒ
			result.setCode("1");
			result.setMsg("δ��ѯ����Ʒ");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("��ѯ�ɹ�");
			result.setData(list);
		}
		return result;
	}

	/**
	 * ��ѯ������Ʒ�ķ���(ָ��ҳ��)
	 * ����result����� 0��ʾ��ѯ�ɹ�  1��ʾδ��ѯ����Ʒ
	 */
	@Override
	public ResultModel goodsAllpage(int page) {
		ResultModel result=new ResultModel();
		//����dao��ķ�����ѯ����
		List<Goods> list=gdao.selectGoodsAllpage(page);
		if(list.isEmpty()){
			//û����Ʒ
			result.setCode("1");
			result.setMsg("δ��ѯ����Ʒ");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("��ѯ�ɹ�");
			result.setData(list);
		}
		return result;
	}
	
	
	
	
	/**
	 * �����û������������ѯ�����Ʒ(��һҳ)
	 * ����result����� 0��ʾ��ѯ�ɹ�  1��ʾδ��ѯ����Ʒ
	 */
	@Override
	public ResultModel goodsByinput(String title) {
		ResultModel result=new ResultModel();
		//����dao��ķ���
		List<Goods> list=gdao.selectGoodsByname(title);
		if(list.isEmpty()){
			//û����Ʒ
			result.setCode("1");
			result.setMsg("δ��ѯ����Ʒ");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("��ѯ�ɹ�");
			result.setData(list);
		}
		return result;
	}

	
	/**
	 * �����û������������ѯ�����Ʒ(ָ��ҳ��)
	 * ����result����� 0��ʾ��ѯ�ɹ�  1��ʾδ��ѯ����Ʒ
	 */
	@Override
	public ResultModel goodsByinputpage(String title,int page){
		ResultModel result=new ResultModel();
		//����dao��ķ���
		List<Goods> list=gdao.selectGoodsBynamepage(title, page);
		if(list.isEmpty()){
			//û����Ʒ
			result.setCode("1");
			result.setMsg("δ��ѯ����Ʒ");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("��ѯ�ɹ�");
			result.setData(list);
		}
		return result;
	}
	
	
	
	/**
	 * ��ѯ������Ʒ������������ķ���(��һҳ)
	 * ����result����� 0��ʾ��ѯ�ɹ�  1��ʾδ��ѯ����Ʒ
	 * ����
	 */
	@Override
	public ResultModel salesSort() {
		ResultModel result=new ResultModel();
		//����dao��ķ���
		List<Goods> list=gdao.selectsalesstore();
		if(list.isEmpty()){
			//û����Ʒ
			result.setCode("1");
			result.setMsg("δ��ѯ����Ʒ");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("��ѯ�ɹ�");
			result.setData(list);//������������Ʒ���󼯺�(��һҳ)
		}
	return result;
	}

	
	/**
	 * ��ѯָ��ҳ��������Ʒ������������Ľ��(ָ��ҳ��)
	 */
	@Override
	public ResultModel salesSortpage(int page){
		ResultModel result=new ResultModel();
		List<Goods> list=gdao.selectsalesstorepage(page);
		if(list.isEmpty()){
			//û����Ʒ
			result.setCode("1");
			result.setMsg("δ��ѯ����Ʒ");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("��ѯ�ɹ�");
			result.setData(list);//������������Ʒ���󼯺�(��һҳ)
		}
		return result;
	}
	
	
	/**
	 * �����۲�ѯ������Ʒ(��һҳ)
	 */
	@Override
	public ResultModel priceSort() {
		ResultModel result=new ResultModel();
		//����dao��ķ���
		List<Goods> list=gdao.selectpricesort();
		if(list.isEmpty()){
			//û����Ʒ
			result.setCode("1");
			result.setMsg("δ��ѯ����Ʒ");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("��ѯ�ɹ�");
			result.setData(list);//������������Ʒ���󼯺�(��һҳ)
		}
		return result;
	}


	/**
	 * ��ѯָ��ҳ����������Ʒ������������(ָ��ҳ��)
	 */
	@Override
	public ResultModel priceSortpage(int page) {
		ResultModel result=new ResultModel();
		//����dao��ķ���
		List<Goods> list=gdao.selectpricesortpage(page);
		if(list.isEmpty()){
			//û����Ʒ
			result.setCode("1");
			result.setMsg("δ��ѯ����Ʒ");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("��ѯ�ɹ�");
			result.setData(list);//������������Ʒ���󼯺�(��һҳ)
		}
		return result;
	}
	
	/**
	 * ��ѯ�û���������Ʒ��������������(��һҳ)
	 */
	@Override
	public ResultModel selectSalesBynameSort(String title) {
		ResultModel result=new ResultModel();
		//����dao��ķ���
		List<Goods> list=gdao.selectSalesBynameSort(title);
		if(list.isEmpty()){
			//û����Ʒ
			result.setCode("1");
			result.setMsg("δ��ѯ����Ʒ");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("��ѯ�ɹ�");
			result.setData(list);//������������Ʒ���󼯺�(��һҳ)
		}
		return result;
	}


	/**
	 * ��ѯ�û���������Ʒ�������۸�����(��һҳ)
	 */
	@Override
	public ResultModel selectPriceBynameSort(String title) {
		ResultModel result=new ResultModel();
		//����dao��ķ���
		List<Goods> list=gdao.selectPriceBynameSort(title);
		if(list.isEmpty()){
			//û����Ʒ
			result.setCode("1");
			result.setMsg("δ��ѯ����Ʒ");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("��ѯ�ɹ�");
			result.setData(list);//������������Ʒ���󼯺�(��һҳ)
		}
		return result;
	}

	



	
	/**
	 * �����û���������ݲ�ѯ�����Ʒ,������������(ָ��ҳ��)
	 */
	@Override
	public ResultModel inputSalesSortpage(String title, int page) {
		ResultModel result=new ResultModel();
		//����dao��ķ���
		List<Goods> list=gdao.selectSalesBynameSortpage(title, page);
		if(list.isEmpty()){
			//û����Ʒ
			result.setCode("1");
			result.setMsg("δ��ѯ����Ʒ");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("��ѯ�ɹ�");
			result.setData(list);//������������Ʒ���󼯺�(��һҳ)
		}
		return result;
	}

	
	/**
	 * �����û���������ݲ�ѯ�����Ʒ,�����۸�����(ָ��ҳ��)
	 */
	@Override
	public ResultModel inputPriceSortpage(String title, int page) {
		ResultModel result=new ResultModel();
		//����dao��ķ���
		List<Goods> list=gdao.selectPriceBynameSortpage(title, page);
		if(list.isEmpty()){
			//û����Ʒ
			result.setCode("1");
			result.setMsg("δ��ѯ����Ʒ");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("��ѯ�ɹ�");
			result.setData(list);//������������Ʒ���󼯺�(��һҳ)
		}
		return result;
	}
	
	
	//�����Ʒ
		@Override
		public ResultModel addGoods(Goods goods) {
			//�����Ʒ���Ƿ����
			boolean boo = gdao.selectByGname(goods.getGoodsname(), goods.getMid());
			ResultModel result = new ResultModel();
			if(boo){
				//��Ʒ���Ѵ���
				result.setCode("2");
				result.setMsg("��Ʒ���Ѵ���");
				result.setData(null);
				return result;
			}
			//��Ʒ������
			boo = gdao.insert(goods);
			if(boo){
				result.setCode("0");
				result.setMsg("��ӳɹ�");
				result.setData("goods");
			}else{
				result.setCode("1");
				result.setMsg("��Ӵ���");
				result.setData(null);
			}
			return result;
		}
		
		//ͨ��ҳ��������Ʒ�б�
		@Override
		public ResultModel selectByPage(String mid, int page) {
			ResultModel result = new ResultModel();
			List<Goods> list = gdao.selectByPage(mid, page);
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

		//�޸���Ʒ��Ϣ
		@Override
		public ResultModel updateGoods(Goods goods) {
			ResultModel result = new ResultModel();
			boolean boo = gdao.updateGoods(goods);
			if(boo){
				//���³ɹ�
				result.setCode("0");
				result.setMsg("�޸ĳɹ�");
				result.setData(null);
			}else{
				//����ʧ��
				result.setCode("1");
				result.setMsg("�޸�ʧ��");
				result.setData(null);
			}
			return result;
		}

		//�ϼ�
		@Override
		public ResultModel shangjia(String gid) {
			ResultModel result = new ResultModel();
			boolean boo = gdao.updateGoodsDrS(gid);
			if(boo){
				//�ϼܳɹ�
				result.setCode("0");
				result.setMsg("�ϼܳɹ�");
				result.setData(null);
			}else{
				//�ϼ�ʧ��
				result.setCode("1");
				result.setMsg("�ϼ�ʧ��");
				result.setData(null);
			}
			return result;
		}

		//�¼�
		@Override
		public ResultModel xiajia(String gid) {
			ResultModel result = new ResultModel();
			boolean boo = gdao.updateGoodsDrX(gid);
			if(boo){
				//�¼ܳɹ�
				result.setCode("0");
				result.setMsg("�¼ܳɹ�");
				result.setData(null);
			}else{
				//�¼�ʧ��
				result.setCode("1");
				result.setMsg("�¼�ʧ��");
				result.setData(null);
			}
			return result;
		}

		//����
		@Override
		public ResultModel selectBySpecialPage(String mid, int page) {
			ResultModel result = new ResultModel();
			List<Goods> list = gdao.selectBySpecialPage(mid, page);
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

		//������Ʒ�Ĵ�����Ϣ
		@Override
		public ResultModel updateDiscount(String gid, double discountprice) {
			ResultModel result = new ResultModel();
			boolean boo = gdao.updateDiscountPrice(gid, discountprice);
			if(boo){
				result.setCode("0");
				result.setMsg("�޸ĳɹ�");
				result.setData(null);
			}else{
				result.setCode("1");
				result.setMsg("�޸�ʧ��");
				result.setData(null);
			}
			return result;
		}

		@Override
		public ResultModel updateDiscountSecond(String gid, double goodsprice) {
			ResultModel result = new ResultModel();
			boolean boo = gdao.updateDiscountPrice(gid, goodsprice);
			if(boo){
				result.setCode("0");
				result.setMsg("�޸ĳɹ�");
				result.setData(null);
			}else{
				result.setCode("1");
				result.setMsg("�޸�ʧ��");
				result.setData(null);
			}
			return result;
		}
	
	
	
	
	
}

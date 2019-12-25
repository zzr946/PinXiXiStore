package com.softeem.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.softeem.dao.OrderDAO;
import com.softeem.model.Goods;
import com.softeem.model.Order;
import com.softeem.model.PayItem;
import com.softeem.model.ResultModel;
import com.softeem.model.Return;
import com.softeem.model.Uaddress;
import com.softeem.model.User;
import com.softeem.service.OrderService;
import com.softeem.tools.Tools;

public class OrderServiceImpl implements OrderService {
	private OrderDAO odao=new OrderDAO();
	
	/**
	 * �û��ύ�����ķ���
	 */
	@Override
	public ResultModel subOrder(String ordernumber,String uid, String gid,int goodstotal) {
		ResultModel result=new ResultModel();
		//���ɶ���id
		String id=Tools.getUUID();
		//���ɶ������
		//String ordernumber=Tools.getNum();
		//����uid��ѯ�����˵�����
		String name=odao.selectUserByid(uid).getName();
		//������Ʒid��ѯ�̼�id
		String mid=odao.selectgoodsByid(gid).getMid();
		//����Ϣװ�붩��������
		Order order=new Order();
		order.setId(id);
		order.setOrdernumber(ordernumber);
		order.setGidlist(gid);
		order.setGoodstotallist(goodstotal);
		order.setName(name);
		order.setMid(mid);
		order.setPaydr(1);//δ֧��״̬
		System.out.println(order);
		//����Ʒ����ӵ���������
		boolean boo=odao.insertOrder(order);
		if(boo){
			//��ӳɹ�
			//���û����ﳵ�����Ʒɾ��
			odao.UpdateCartdr(uid, gid);
			result.setCode("0");
			result.setMsg("��ӳɹ�");
			result.setData(null);
		}else{
			//���ʧ��
			result.setCode("1");
			result.setMsg("���ʧ��");
			result.setData(null);
		}
		return result;
	}

	
	
	/**
	 * ��ѯ���д�֧������Ʒ��Ŀ
	 */
	@Override
	public ResultModel payGoods(String ordernumber) {
		ResultModel result=new ResultModel();
		//����dao��ķ���
		List<PayItem> list=odao.selectPayitemAll(ordernumber);
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

	
	
	/**
	 * �û�ȷ�϶���  ��������Ϊ�Ѹ���״̬(������)����ɹ�
	 */
	public ResultModel success(String uid,String addressid,String ordernumber,int money){
		ResultModel result=new ResultModel();
		//����dao�� ��ȡ���ջ�������
		String uadname=odao.selectaddrByid(addressid).getUadname();
		//����Ϣ���뵽order������
		Order order=new Order();
		order.setUadname(uadname);
		order.setAccount(money);
		order.setUadid(addressid);
		//dao���޸Ķ���״̬
		boolean boo=odao.updateOrder(order);
		if(boo){
			//�޸ĳɹ�
			result.setCode("0");
			result.setMsg("�޸ĳɹ�");
			result.setData(null);
		}else{
			//�޸�ʧ��
			result.setCode("1");
			result.setMsg("�޸�ʧ��");
			result.setData(null);
		}
		return result;
	}
	
	
	/**
	 * ���ݵ�ַid��ѯ��ַ��Ϣ
	 */
	public ResultModel addressinfo(String addressid){
		ResultModel result=new ResultModel();
		Uaddress address=odao.selectaddrByid(addressid);
		if(address!=null){
			//��ѯ�ɹ�
			result.setCode("0");
			result.setMsg("��ѯ�ɹ�");
			result.setData(address);
		}else{
			//��ѯʧ��
			result.setCode("1");
			result.setMsg("��ѯʧ��");
			result.setData(null);
		}
		return result;
	}
	
	/**
	 * �����û�id��ѯ�û����ж�����Ϣ
	 * @param uid �û�id
	 * @return �������ж�����Ϣ
	 */
	public ResultModel orderAll(String uid){
		ResultModel result=new ResultModel();
		//����dao���ѯ�û���Ϣ
		User user=odao.selectUserByid(uid);
		//��ѯ�û������ж���
		List<Order> list=odao.selectOrderAll(user);
		if(list.isEmpty()){
			//��ѯʧ��
			result.setCode("1");
			result.setMsg("��ѯʧ��");
			result.setData(null);
		}else{
			//��ѯ�ɹ�
			//������Ʒid��ѯ��Ʒ
			List<PayItem> listitem=new ArrayList<PayItem>();
			for (Order order : list) {
				Goods goods=odao.selectgoodsByid(order.getGidlist());
				listitem.add(new PayItem(goods.getGid(), goods.getGoodsimage(), goods.getGoodsname(), goods.getReserved1(), goods.getDiscountprice(), order.getGoodstotallist(), goods.getDiscountprice()*order.getGoodstotallist()));
			}
			result.setCode("0");
			result.setMsg("��ѯ�ɹ�");
			result.setData(listitem);
		}
		return result;
	}


	
	/**
	 * �����û�id��ѯ�û����д�֧���Ķ���
	 */
	@Override
	public ResultModel awaitOrder(String uid) {
		ResultModel result=new ResultModel();
		//����dao���ѯ�û���Ϣ
		User user=odao.selectUserByid(uid);
		//����service��ķ�������ѯ�û����д�֧������
		List<Order> list=odao.selectawaitOrder(user);
		if(list.isEmpty()){
			//��ѯʧ��
			result.setCode("1");
			result.setMsg("��ѯʧ��");
			result.setData(null);
		}else{
			//��ѯ�ɹ�
			//������Ʒid��ѯ��Ʒ
			List<PayItem> listitem=new ArrayList<PayItem>();
			for (Order order : list) {
				Goods goods=odao.selectgoodsByid(order.getGidlist());
				listitem.add(new PayItem(goods.getGid(), goods.getGoodsimage(), goods.getGoodsname(), goods.getReserved1(), goods.getDiscountprice(), order.getGoodstotallist(), goods.getDiscountprice()*order.getGoodstotallist()));
			}
			result.setCode("0");
			result.setMsg("��ѯ�ɹ�");
			result.setData(listitem);
		}
		return result;
	}


	/**
	 * �����û�id��ѯ�û����д������Ķ���
	 */
	@Override
	public ResultModel awaitSendOrder(String uid) {
		ResultModel result=new ResultModel();
		//����dao���ѯ�û���Ϣ
		User user=odao.selectUserByid(uid);
		//����service��ķ�������ѯ�û����д�֧������
		List<Order> list=odao.selectawaitSend(user);
		if(list.isEmpty()){
			//��ѯʧ��
			result.setCode("1");
			result.setMsg("��ѯʧ��");
			result.setData(null);
		}else{
			//��ѯ�ɹ�
			//������Ʒid��ѯ��Ʒ
			List<PayItem> listitem=new ArrayList<PayItem>();
			for (Order order : list) {
				Goods goods=odao.selectgoodsByid(order.getGidlist());
				listitem.add(new PayItem(goods.getGid(), goods.getGoodsimage(), goods.getGoodsname(), goods.getReserved1(), goods.getDiscountprice(), order.getGoodstotallist(), goods.getDiscountprice()*order.getGoodstotallist()));
			}
			result.setCode("0");
			result.setMsg("��ѯ�ɹ�");
			result.setData(listitem);
		}
		
		return result;
	}


	/**
	 * �����û�id�鿴���ջ��Ķ���
	 */
	@Override
	public ResultModel awaittakeOrder(String uid) {
		ResultModel result=new ResultModel();
		//����dao���ѯ�û���Ϣ
				User user=odao.selectUserByid(uid);
				//����service��ķ�������ѯ�û����д�֧������
				List<Order> list=odao.selectawaittake(user);
				if(list.isEmpty()){
					//��ѯʧ��
					result.setCode("1");
					result.setMsg("��ѯʧ��");
					result.setData(null);
				}else{
					//��ѯ�ɹ�
					//������Ʒid��ѯ��Ʒ
					List<PayItem> listitem=new ArrayList<PayItem>();
					for (Order order : list) {
						Goods goods=odao.selectgoodsByid(order.getGidlist());
						listitem.add(new PayItem(goods.getGid(), goods.getGoodsimage(), goods.getGoodsname(), goods.getReserved1(), goods.getDiscountprice(), order.getGoodstotallist(), goods.getDiscountprice()*order.getGoodstotallist()));
					}
					result.setCode("0");
					result.setMsg("��ѯ�ɹ�");
					result.setData(listitem);
				}
		return result;
	}



	/**
	 * �����û�id�鿴�����۶���
	 */
	@Override
	public ResultModel awaitevaluate(String uid) {
		ResultModel result=new ResultModel();
		//����dao���ѯ�û���Ϣ
				User user=odao.selectUserByid(uid);
				//����service��ķ�������ѯ�û����д�֧������
				List<Order> list=odao.selectawaitevaluate(user);
				if(list.isEmpty()){
					//��ѯʧ��
					result.setCode("1");
					result.setMsg("��ѯʧ��");
					result.setData(null);
				}else{
					//��ѯ�ɹ�
					//������Ʒid��ѯ��Ʒ
					List<PayItem> listitem=new ArrayList<PayItem>();
					for (Order order : list) {
						Goods goods=odao.selectgoodsByid(order.getGidlist());
						listitem.add(new PayItem(goods.getGid(), goods.getGoodsimage(), goods.getGoodsname(), goods.getReserved1(), goods.getDiscountprice(), order.getGoodstotallist(), goods.getDiscountprice()*order.getGoodstotallist()));
					}
					result.setCode("0");
					result.setMsg("��ѯ�ɹ�");
					result.setData(listitem);
				}
		return result;
	}


	/**
	 * �û��ڲ鿴����ҳ��һ��֧��(��ȡ��ַ��Ϣ)
	 */
	@Override
	public ResultModel aKeyPayAddress(String uid, String gid) {
		ResultModel result=new ResultModel();
		//�����û�id�鿴�û����е�ַ��Ϣ
		List<Uaddress> list=odao.selectaddressAll(uid);
		result.setCode("0");
		result.setMsg("��ѯ�ɹ�");
		result.setData(list);
		return result;
	}


	/**
	 * �û��鿴����ʱһ��֧��(��ȡ��Ʒ��Ϣ)
	 */
	@Override
	public ResultModel aKeyPayGoods(String uid, String gid) {
		ResultModel result=new ResultModel();
		//������Ʒid�鿴�û�������Ʒ��Ϣ
		Goods goods=odao.selectgoodsByid(gid);
		result.setData(goods);
		result.setCode("0");
		result.setMsg("��ѯ�ɹ�");
		return result;
	}



	/**
	 * �û��ջ��ķ���
	 */
	@Override
	public ResultModel takeOrder(String uid, String gid) {
		ResultModel result=new ResultModel();
		//�����û�id��ѯ�û�������Ϣ
		User user=odao.selectUser(uid);
		//������Ʒid��ѯ��Ʒ��Ϣ
		Goods goods=odao.selectgoodsByid(gid);
		//����Ϣװ��order��
		Order order=new Order();
		order.setGidlist(gid);
		order.setName(user.getName());
		//����dao��ķ��� �ջ�
		boolean boo=odao.updatetakeOrder(order);
		if(boo){
			//�ջ��ɹ�
			result.setCode("0");
			result.setMsg("�ջ��ɹ�");
			result.setData(null);
		}else{
			//�ջ�ʧ��
			result.setCode("1");
			result.setMsg("�ջ�ʧ��");
			result.setData(null);
		}
		return result;
	}
	
	/**
	 * �û��˻��ķ���
	 * @param uid �û�id
	 * @param gid sp ��Ҫ�˻�����Ʒid
	 * @return
	 */
	public ResultModel returnmoney(String uid,String gid){
		ResultModel result=new ResultModel();
		Goods goods=odao.selectgoodsByid(gid);
		result.setCode("0");
		result.setMsg("��ѯ�ɹ�");
		result.setData(goods);
		return result;
	}



	/**
	 * �û��ύ�˻����ķ���
	 */
	@Override
	public ResultModel subreturn(String uid, String goodsnumber, String returntype, String returncause,
			String money, String returndetails) {
		ResultModel result=new ResultModel();
		//��ȡid
		String id=Tools.getUUID();
		//���˻����תΪdouble ����
		double returnmoney=Double.valueOf(money);
		//���˿���Ϣװ��Return������
		Return ret=new Return(id,goodsnumber, returntype, returncause, returndetails, 1, returnmoney, uid, null, null, null);
		//����dao��ķ���
		boolean boo=odao.insertreturnGoods(ret);
		if(boo){
			//�˻������ύ�ɹ�
			result.setCode("0");
			result.setMsg("�˻������ύ�ɹ�");
			result.setData(null);
		}else{
			//�˻������ύʧ��
			result.setCode("1");
			result.setMsg("�˻������ύʧ��");
			result.setData(null);
		}
		return result;
	}
	
	//���ض���
		@Override
		public ResultModel findByPage(int page, String mid) {
			List<Order> list = odao.selectByOPage(page, mid);
			ResultModel result = new ResultModel();
			if(list.isEmpty()){
				//û���ҵ�����
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
		
		//����
		@Override
		public ResultModel fahuo(String id) {
			ResultModel result = new ResultModel();
			boolean boo = odao.fahuo(id);
			if(boo){
				//�����ɹ�
				result.setCode("0");
				result.setMsg("�����ɹ�");
				result.setData(null);
			}else{
				//����ʧ��
				result.setCode("1");
				result.setMsg("����ʧ��");
				result.setData(null);
			}
			return result;
		}

	
	
}

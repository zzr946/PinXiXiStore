package com.softeem.service.impl;

import java.util.List;

import com.softeem.dao.CartDAO;
import com.softeem.model.Cart;
import com.softeem.model.CartItem;
import com.softeem.model.ResultModel;
import com.softeem.service.CartService;
import com.softeem.tools.Tools;

public class CartServiceImpl implements CartService {
	private CartDAO cdao=new CartDAO();
	
	/**
	 * �û���ӵ����ﳵ�ķ���
	 */
	@Override
	public ResultModel addGoodsToCart(String uid, String gid, double price, int count) {
		ResultModel result=new ResultModel();
		//����dao��ķ��� ���жϱ�����ӵ���Ʒ��ǰ��ӹ�û
		Cart c=cdao.selectCart(uid, gid);
		if(c==null){
			//��û��ӹ�
			//ֱ�ӽ�����Ʒ��ӽ�ȥ
			//��ȡid
			String id=Tools.getUUID();
			//����Ϣ����cart������
			Cart cart=new Cart();
			cart.setId(id);
			cart.setUid(uid);
			cart.setGidlist(gid);
			cart.setGoodstotallist(count);
			cart.setGoodsprice(price);
			cart.setReserved1(0);
			//����dao��ķ���������Ʒ�浽���ﳵ����
			boolean boo=cdao.inserttoCart(cart);
			if(boo){
				//��ӳɹ�
				result.setCode("0");
				result.setMsg("��ӳɹ�");
				result.setData(cart);
			}else{
				//���ʧ��
				result.setCode("1");
				result.setMsg("���ʧ��");
				result.setData(null);
			}
		}else{
			//�Ѿ���ӹ�
			//��ȡ��ǰ��ӵĸ���Ʒ������
			int oldcount=c.getGoodstotallist();
			//����dao��ķ���������Ʒ����������
			boolean boo=cdao.updateTototal(uid, gid, (oldcount+count));
			if(boo){
				//��ӳɹ�
				result.setCode("0");
				result.setMsg("��ӳɹ�");
				result.setData(null);
			}else{
				//���ʧ��
				result.setCode("1");
				result.setMsg("���ʧ��");
				result.setData(null);
			}
		}
		
		return result;
	}

	/**
	 * ��ѯ�û����ﳵ���������Ʒ�ķ���
	 */
	@Override
	public ResultModel cartAll(String uid) {
		ResultModel result=new ResultModel();
		//����dao��ķ�����ѯ
		List<CartItem> list=cdao.selectcartAll(uid);
		if(list.isEmpty()){
			//û������
			result.setCode("1");
			result.setMsg("û������");
			result.setData(null);
		}else{
			//������
			result.setCode("0");
			result.setMsg("��ѯ�ɹ�");
			result.setData(list);
		}
		return result;
	}

}

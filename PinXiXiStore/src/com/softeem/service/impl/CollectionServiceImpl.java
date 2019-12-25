package com.softeem.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.softeem.dao.CollectionDAO;
import com.softeem.model.Collection;
import com.softeem.model.Goods;
import com.softeem.model.ResultModel;
import com.softeem.service.CollectionService;
import com.softeem.tools.Tools;

public class CollectionServiceImpl implements CollectionService {
	private CollectionDAO cdao=new CollectionDAO();
	
	/**
	 * �û��ղ���Ʒ�ķ���(���ղر��в�������)
	 * ���ص�result����� 0��ʾ�ղسɹ�  1��ʾ�ղ�ʧ��  2��ʾ����Ʒ�Ѿ��ղع���
	 */
	@Override
	public ResultModel addcollGoods(String uid, String gid) {
		ResultModel result=new ResultModel();
		//�ȼ���û��Ƿ��Ѿ��ղع�����Ʒ
		boolean bo=cdao.selectBygid(uid, gid);
		if(bo){
			//�û��Ѿ��ղع���
			result.setCode("2");
			result.setMsg("�ղسɹ�");
			result.setData(null);
			return result;
		}
		//��ȡid
		String id=Tools.getUUID();
		//��ȡ���빺�ﳵ��ʱ��
		String addtime=Tools.dateToStr("yyyy-MM-dd HH:mm:ss", new Date());
		//������װ�뵽һ���ղض�����
		Collection coll=new Collection();
		coll.setId(id);
		coll.setUid(uid);
		coll.setGid(gid);
		coll.setCollecttime(addtime);
		coll.setCollectdr(0);
		//����dao��ķ������뵽���ݿ��ղر���
		boolean boo=cdao.insertgoods(coll);
		if(boo){
			//�ղسɹ�
			result.setCode("0");
			result.setMsg("�ղسɹ�");
			result.setData(coll);
		}else{
			//�ղ�ʧ��
			result.setCode("1");
			result.setMsg("�ղ�ʧ��");
			result.setData(null);
		}
		return result;
	}

	/**
	 * �鿴�û��ղص���Ʒ
	 */
	@Override
	public ResultModel selectcollGoods(String uid) {
		ResultModel result=new ResultModel();
		//�����û�id��ѯ�ղر�
		List<Collection> listcoll=cdao.selectCollGoods(uid);
		//����һ����Ʒ����
		List<Goods> list=new ArrayList<Goods>();
		for (Collection collection : listcoll) {
			//����gid��ѯ�ղص���Ʒ��Ϣ
			Goods goods=cdao.selectgoodsByid(collection.getGid());
			list.add(goods);
		}
		result.setData(list);
		return result;
	}
	
	
	
}

package com.softeem.service.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import com.softeem.dao.StoreDAO;
import com.softeem.model.ResultModel;
import com.softeem.model.Store;
import com.softeem.service.StoreService;

public class StoreServiceImpl implements StoreService {

	StoreDAO sdao = new StoreDAO();
	
	/**
	 * ע�����,����һ�������
	 */
	@Override
	public ResultModel reg(String sid,String storename, String storelogo, String storeinfo, String storeaddress, String aptitude) {
		boolean boo = false;
		ResultModel result = new ResultModel();
		//�жϵ����Ƿ����
		boo = sdao.selectStoreExist(storename);
		if(boo){
			//˵�����̴���
			result.setCode("2");
			result.setMsg("�����Ѵ���");
			result.setData(null);
			return result;
		}
		Date date = new Date();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//�������ڸ�ʽ
		String subtime = df.format(date);//ʹ�õ�ǰϵͳʱ��Ϊע��ʱ��
		Store store = new Store();
		store.setSid(sid);
		store.setStorelogo(storelogo);
		store.setStoreinfo(storeinfo);
		store.setStoreaddress(storeaddress);
		store.setAptitude(aptitude);
		store.setStorename(storename);
		store.setSubtime(subtime);
		store.setStoredr(0);
		boo = sdao.insert(store);
		if(boo){
			//�����ɹ�
			result.setCode("0");
			result.setMsg("�����ɹ�");
			result.setData(store);
		}else{
			//����ʧ��
			result.setCode("1");
			result.setMsg("����ʧ��");
			result.setData(null);
		}
		return result;
	}
	

	/**
	 * �鿴�Ƿ񴴽����̣�����һ�������
	 */
	@Override
	public ResultModel selectExist(String msid) {
		Store store = sdao.selectByMsid(msid);
		ResultModel result = new ResultModel();
		if(store == null){
			result.setCode("-1");//��ʾδ��������
			result.setMsg("��δ��������");
			result.setData(null);
			return result;
		}
		//�Ѵ�������
		if(store.getStoredr() == 0){
			result.setCode("3");//��δ����
			result.setMsg("���̻�δ����");
			result.setData(store);
			return result;
		}else if(store.getStoredr() == 1){
			result.setCode("1");//�Ѽ���
			result.setMsg("�����Ѽ���");
			result.setData(store);
			return result;
		}else if(store.getStoredr() == 2){
			result.setCode("2");//��δ����
			result.setMsg("���̷����");
			result.setData(store);
			return result;
		}
		return result;
	}


	//�ύ�޸ĵ��̵���Ϣ
	@Override
	public ResultModel sendUpdate(String mid, String newname, String newstoreinfo, String newaddress) {
		boolean boo = sdao.updateStore(mid, newname, newstoreinfo, newaddress);
		ResultModel result = new ResultModel();
		if(boo){
			result.setCode("0");
			result.setMsg("�ύ�ɹ�");
			result.setData(null);
		}else{
			result.setCode("1");
			result.setMsg("�ύʧ��");
			result.setData(null);
		}
		return result;
	}

}

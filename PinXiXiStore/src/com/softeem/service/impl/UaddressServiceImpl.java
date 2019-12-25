package com.softeem.service.impl;

import java.util.List;

import com.softeem.dao.UAddressDAO;
import com.softeem.model.ResultModel;
import com.softeem.model.Uaddress;
import com.softeem.service.UaddressService;
import com.softeem.tools.Tools;

public class UaddressServiceImpl implements UaddressService {
	private UAddressDAO uaddressdao=new UAddressDAO();
	/**
	 * �û�����ջ���ַ�ķ���
	 * ����result����� 0��ʾ��ӳɹ� 1��ʾ���ʧ�� 2��ʾ���ޣ����������
	 */
	@Override
	public ResultModel useraddaddress(String uid, String uadname, String mobile, String province, String city,
			String area, String detailaddress) {
		ResultModel result=new ResultModel();
		//��ѯ���û��µ����е�ַ
		List<Uaddress> list=uaddressdao.selectaddressAll(uid);
		if(list.size()>=3){
			result.setCode("2");
			result.setMsg("�Ѿ�������ޣ������������");
			result.setData(null);
			return result;
		}
		
		//��ȡUUID
		String uadid=Tools.getUUID();
		Uaddress address=new Uaddress(uadid, uid, province, city, area, detailaddress, 1, mobile, uadname, null, null, null, null, null);
		//����dao��ķ���
		boolean boo=uaddressdao.insertUseraddress(address);
		if(boo){
			//��ӳɹ�
			//��ѯ���û��µ������ջ���ַ
			List<Uaddress> list1=uaddressdao.selectaddressAll(uid);
			result.setCode("0");
			result.setMsg("��ӳɹ�");
			result.setData(list1);
		}else{
			//���ʧ��
			result.setCode("1");
			result.setMsg("���ʧ��");
			result.setData(null);
		}
		return result;
	}

	
	/**
	 * �����û�id�鿴���û��µ����е�ַ
	 * ����result����� 0��ʾ�鵽����  1��ʾû�в鵽���� 
	 */
	public ResultModel useraddressAll(String uid){
		ResultModel result=new ResultModel();
		//����uaddressdao��ķ���
		List<Uaddress> list=uaddressdao.selectaddressAll(uid);
		if(list.isEmpty()){
			//û�鵽����
			result.setCode("1");
			result.setMsg("û�鵽����");
			result.setData(null);
		}else{
			//�鵽����
			result.setCode("0");
			result.setMsg("�鵽����");
			result.setData(list);
		}
		return result;
	}


	/**
	 * �޸�ָ���û��µ�Ĭ�ϵ�ַ
	 */
	@Override
	public ResultModel setdefaultaddress(String uid, String uadid) {
		ResultModel result=new ResultModel();
		//���Ƚ����û��µ����е�ַ����Ϊ��ѡ״̬
		//����dao��ķ��������û��µ����е�ַ״̬��Ϊ��ѡ״̬
		boolean boo=uaddressdao.updateaddresstonot(uid);
		if(boo){
			//������óɹ���ָ��id�ĵ�ַ�޸�ΪĬ�ϵ�ַ
			boolean bo=uaddressdao.updateaddress(uid, uadid);
			if(bo){
				//����ַ����ΪĬ��״̬�ɹ�
				result.setCode("0");
				result.setMsg("���óɹ�");
				result.setData(null);
			}else{
				//����ַ����ΪĬ��״̬ʧ��
				result.setCode("1");
				result.setMsg("����ʧ��");
				result.setData(null);
			}
		}else{
			//����ַ����Ϊ��ѡ״̬ʧ��
			result.setCode("2");
			result.setMsg("����ַ���óɱ�ѡ״̬ʧ��");
			result.setData(null);
		}
		return result;
	}


	/**
	 * �û�ɾ����ַ�ķ���
	 */
	@Override
	public ResultModel delteAddress(String uid, String uadid) {
		ResultModel result=new ResultModel();
		//����dao��ķ���
		boolean boo=uaddressdao.UpdateAddress(uid, uadid);
		if(boo){
			//ɾ���ɹ�
			//��ѯ���û��µ������ջ���ַ
			List<Uaddress> list=uaddressdao.selectaddressAll(uid);
			result.setCode("0");
			result.setMsg("ɾ���ɹ�");
			result.setData(list);
		}else{
			//ɾ��ʧ��
			result.setCode("1");
			result.setMsg("ɾ��ʧ��");
			result.setData(null);
		}
		return result;
	}
	
}

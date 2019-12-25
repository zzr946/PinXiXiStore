package com.softeem.service.impl;

import com.softeem.dao.MerchantDAO;
import com.softeem.model.Merchant;
import com.softeem.model.ResultModel;
import com.softeem.service.MerchantService;
import com.softeem.tools.Tools;

public class MerchantServiceImpl implements MerchantService {

	MerchantDAO mdao = new MerchantDAO();
	
	
	@Override
	public ResultModel reg(String phone, String password) {
		boolean boo = false;
		ResultModel result = new ResultModel();
		//�ж��ֻ����Ƿ����
		boo = mdao.select_Merchant_Phoneexist(phone);
		if(boo){
			//�ֻ������Ѿ�����
			result.setCode("2");
			result.setMsg("�ֻ����ѱ�ռ��");
			result.setData(null);
			return result;
		}
		//�ֻ��Ų����ڣ�����ʹ�ø��ֻ���ע��
		String mid = Tools.getUUID();
		password = Tools.getMD5(password);
		Merchant merchant = new Merchant();
		merchant.setMid(mid);
		merchant.setPhone(phone);
		merchant.setMpassword(password);
		merchant.setMerchantdr(0);//0��ʾ�˺�״̬����
		boo = mdao.insert(merchant);
		if(boo){
			//ע��ɹ�
			result.setCode("0");
			result.setMsg("ע��ɹ�");
			result.setData(merchant);
		}else{
			//ע��ʧ��
			result.setCode("1");
			result.setMsg("ע��ʧ��");
			result.setData(null);
		}
		return result;
	}


	@Override
	public ResultModel login(String phone, String password) {
		ResultModel result = new ResultModel();
		password = Tools.getMD5(password);
		Merchant me = mdao.selectByPassword(phone,password);
		if(me == null){
			//û�в鵽���ݣ�Ҳ�����û������������
			result.setCode("1");
			result.setMsg("�û������������");
			result.setData(null);
			return result;
		}else{
			//��¼�ɹ�
			result.setCode("0");
			result.setMsg("�ɹ�");
			result.setData(me);
			return result;
		}
	}


	@Override
	public boolean addSid(String sid, String mid) {
		return mdao.updateSid(sid, mid);
	}


	//�޸�����
	@Override
	public ResultModel updatePWD(String phone, String password) {
		ResultModel result = new ResultModel();
		password = Tools.getMD5(password);
		boolean boo = mdao.updatePWD(phone, password);
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

}

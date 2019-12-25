package com.softeem.service.impl;

import java.util.List;

import com.softeem.dao.AdminDAO;
import com.softeem.model.Admin;
import com.softeem.model.Alertstore;
import com.softeem.model.Report;
import com.softeem.model.ResultModel;
import com.softeem.model.Store;
import com.softeem.service.AdminService;

public class AdminServiceImpl implements AdminService {
	AdminDAO admindao =new AdminDAO();

	@Override
	public ResultModel loadbyadminaccount(String adminaccount,String adminpassword) {
		//���������
		ResultModel result = new ResultModel();
		//����dao��
		Admin admin =admindao.loadbyadminaccount(adminaccount, adminpassword);
		if(admin==null){
			//��¼ʧ��
			result.setCode("1");
			result.setMsg("�û������������");
			result.setData(null);
		}else{
			//��¼�ɹ�
			result.setCode("0");
			result.setMsg("��¼�ɹ�");
			result.setData(admin);
		}
		return result;
	}

	//�̼ҵ�����Ϣ�޸�
	public ResultModel adminUpdateStore(String aid){
		ResultModel result = new ResultModel();
		Alertstore as = admindao.selectByAid(aid);
		String sid = admindao.selectByMid(as.getMid());
		boolean boo = admindao.updateStore(as,sid);
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
	
	
	
	@Override
	public ResultModel changebyadminaccount(String adminaccount, String adminpassword) {
		//���������
		ResultModel result = new ResultModel();
		//����dao��
		boolean boo = admindao.changebyadminaccount(adminaccount, adminpassword);
		if(boo){
			//��¼�ɹ�
			result.setCode("0");
			result.setMsg("�޸ĳɹ�");
			result.setData(null);
		}else{
			//��¼ʧ��
			result.setCode("1");
			result.setMsg("�޸�ʧ��");
			result.setData(null);
		}
		
		return result;
	}





	@Override
	public ResultModel checkbyquestion(String adminname) {
		//���������
		ResultModel result = new ResultModel();
		Admin admin = admindao.checkbyquestion(adminname);
		System.out.println("�Ѿ��ҵ���");
		System.out.println(admin);
		if(admin==null){
			//��¼ʧ��
			result.setCode("1");
			result.setMsg("��������ȷ��ϵͳ����Ա����");
			result.setData(null);
		}else{
			//��¼�ɹ�
			result.setCode("0");
			result.setMsg("������ȷ");
			result.setData(admin);
		}
		return result;
	}





	@Override
	public ResultModel checkbyquestion1(String answer) {
		//���������
		ResultModel result = new ResultModel();
		Admin admin = admindao.checkbyquestion1(answer);
		if(admin==null){
			//��¼ʧ��
			result.setCode("1");
			result.setMsg("��������ȷ��ϵͳ����Ա����");
			result.setData(null);
		}else{
			//��¼�ɹ�
			result.setCode("0");
			result.setMsg("������ȷ");
			result.setData(admin);
		}
		return result;
	}





	@Override
	public ResultModel checkbystoredr(int storedr) {
		//���������
		ResultModel result = new ResultModel();
		List<Store> list = admindao.checkstore(storedr);
		if(list==null){
			//û�д���˵��̼�
			result.setCode("1");
			result.setMsg("��û�д���˵��̼�");
			result.setData(null);
		}else{
			//�鿴������˵��̼�
			result.setCode("0");
			result.setMsg("��ʾ����˵��̼�");
			result.setData(list);
		}
		return result;
	}




	/*
	 * ����̼���ס 
	 */
	@Override
	public ResultModel changestoredr(String sid) {
		//����һ���Y����
		ResultModel result = new ResultModel();
		int storedr1=1;
		Boolean boo= admindao.changestoredr(sid, storedr1);
		if(boo){
			//���ĳɹ�
			result.setCode("0");
			result.setMsg("���ĳɹ�");
			result.setData(null);
		}else{
			//����ʧ��
			result.setCode("1");
			result.setMsg("����ʧ��");
			result.setData(null);
		}
		return result;
	}
	
	/*
	 * �̼����ʱ���з��
	 */
	@Override
	public ResultModel banstoredr(String sid) {
		//����һ���Y����
		ResultModel result = new ResultModel();
		int storedr1=2;
		Boolean boo= admindao.changestoredr(sid, storedr1);
		if(boo){
			//���ĳɹ�
			result.setCode("0");
			result.setMsg("���ĳɹ�");
			result.setData(null);
		}else{
			//����ʧ��
			result.setCode("1");
			result.setMsg("����ʧ��");
			result.setData(null);
		}
		return result;
	}
	
	
	




	//�޸���Ϣ���
	@Override
	public ResultModel changecheckdr(String alertid) {
		//����һ�������
		ResultModel result = new ResultModel();
		int checkdr=1;
		boolean boo = admindao.changecheckdr(alertid, checkdr);
		System.out.println(boo);
		if(boo){
			//���ĳɹ�
			result.setCode("0");
			result.setMsg("���ĳɹ�");
			result.setData(null);
		}else{
			//����ʧ��
			result.setCode("1");
			result.setMsg("����ʧ��");
			result.setData(null);
		}
		return result;
	}




	//ϵͳ����Ա����checkdr��ʾ�̼�
	@Override
	public ResultModel checkbycheckdr(int checkdr) {
		//����һ�������
		ResultModel result = new ResultModel();
		List<Alertstore> list = admindao.checkalterstore(checkdr);
		if(list==null){
			//��û����Ҫ�޸���Ϣ���̼�
			result.setCode("1");
			result.setMsg("��û����Ҫ�޸���Ϣ���̼�");
			result.setData(null);
		}else{
			//��ѯ������Ҫ�޸���Ϣ���̼�
			result.setCode("0");
			result.setMsg("��ѯ������Ҫ�޸���Ϣ���̼�");
			result.setData(list);
		}
		return result;
	}




	//��ʾ������Ҫ����̼ҵ��̼�
	@Override
	public ResultModel checkAllstore(int page) {
		//���������
		ResultModel result = new ResultModel();
		List<Store> list = admindao.checkAllstore(page);
		
		if(list.isEmpty()){
			//��û����Ҫ�޸���Ϣ���̼�
			result.setCode("1");
			result.setMsg("��û����Ҫ�޸���Ϣ���̼�");
			result.setData(null);
		}else{
			//��ѯ������Ҫ�޸���Ϣ���̼�
			result.setCode("0");
			result.setMsg("��ѯ������Ҫ�޸���Ϣ���̼�");
			result.setData(list);
		}
		return result;
	}




	//��ʾ������Ҫ��Ϣ�޸���˵��̼�
	@Override
	public ResultModel checkAllalterstore() {
		//���������
		ResultModel result = new ResultModel();
		List<Alertstore> list  = admindao.checkAllalterstore();
		if(list==null){
			//��û����Ҫ�޸���Ϣ���̼�
			result.setCode("1");
			result.setMsg("��û����Ҫ�޸���Ϣ���̼�");
			result.setData(null);
		}else{
			//��ѯ������Ҫ�޸���Ϣ���̼�
			result.setCode("0");
			result.setMsg("��ѯ������Ҫ�޸���Ϣ���̼�");
			result.setData(list);
		}
		return result;
	}





	@Override
	public ResultModel changecheckdr1(String alertid) {
		//����һ�������
				ResultModel result = new ResultModel();
				int checkdr=2;
				boolean boo = admindao.changecheckdr(alertid, checkdr);
				System.out.println(boo);
				if(boo){
					//���ĳɹ�
					result.setCode("0");
					result.setMsg("���ĳɹ�");
					result.setData(null);
				}else{
					//����ʧ��
					result.setCode("1");
					result.setMsg("����ʧ��");
					result.setData(null);
				}
				return result;
	}





	@Override
	public Admin checkupload(String adminaccount,String adminpassword) {
		Admin admin = admindao.loadbyadminaccount(adminaccount, adminpassword);
		return admin;
	}





	@Override
	public ResultModel changephtot(String address, String adminaccount) {
		ResultModel result = new ResultModel();
		boolean boo = admindao.changephoto(address, adminaccount);
		if(boo){
			//�ɹ�
			result.setCode("0");
			result.setMsg("�ϴ��ɹ�");
			result.setData(null);
		}else{
			//ʧ��
			result.setCode("1");
			result.setMsg("�ϴ�ʧ��");
			result.setData(null);
		}
		return result;
	}

	@Override
	public ResultModel checkReportMerchant() {
		//���������
				ResultModel result = new ResultModel();
				List<Report> list = admindao.checkreportmarchant();
				
				if(list.isEmpty()){
					//��û����Ҫ�޸���Ϣ���̼�
					result.setCode("1");
					result.setMsg("��û�оٱ�����������̼�");
					result.setData(null);
				}else{
					//��ѯ������Ҫ�޸���Ϣ���̼�
					result.setCode("0");
					result.setMsg("��ѯ���ٱ�����������̼�");
					result.setData(list);
				}
				return result;
	}










	@Override
	public ResultModel banReportMerchant(String mid) {
		//����һ�������
				ResultModel result = new ResultModel();
				
				boolean boo = admindao.banTheMerchant(mid);
				System.out.println(mid);
				System.out.println(boo);
				if(boo){
					//���ĳɹ�
					result.setCode("0");
					result.setMsg("���ĳɹ�");
					result.setData(null);
				}else{
					//����ʧ��
					result.setCode("1");
					result.setMsg("����ʧ��");
					result.setData(null);
				}
				return result;
	}










	@Override
	public ResultModel checkReportGoods() {
		ResultModel result = new ResultModel();
		List<Report> list = admindao.checkreportgoods();
		
		if(list.isEmpty()){
			//��û����Ҫ�޸���Ϣ���̼�
			result.setCode("1");
			result.setMsg("��û�оٱ������������Ʒ");
			result.setData(null);
		}else{
			//��ѯ������Ҫ�޸���Ϣ���̼�
			result.setCode("0");
			result.setMsg("��ѯ���ٱ������������Ʒ");
			result.setData(list);
		}
		return result;
	}










	@Override
	public ResultModel downReportGoods(String gid) {
		//����һ�������
		ResultModel result = new ResultModel();
		System.out.println(gid);
		boolean boo = admindao.downTheGoods(gid);
		System.out.println(boo);
		if(boo){
			//���ĳɹ�
			result.setCode("0");
			result.setMsg("���ĳɹ�");
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

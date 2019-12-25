package com.softeem.service;

import com.softeem.model.Admin;
import com.softeem.model.ResultModel;

public interface AdminService {
	//ϵͳ����Ա��¼
	public ResultModel loadbyadminaccount(String adminaccount,String adminpassword);
	
	//ϵͳ����Ա�����޸�
	public ResultModel changebyadminaccount(String adminaccount ,String adminpassword);
	
	//ϵͳ����Ա�����û����鿴�ܱ�����
	public ResultModel checkbyquestion(String adminname);
	
	//ϵͳ����Ա��������ȶԴ�
	public ResultModel checkbyquestion1(String answer);
	
	//ϵͳ����Ա����storedr��ʾ�̼�
	public ResultModel checkbystoredr(int storedr);
	
	//ϵ�y����T����sid�����̼���ְ
	public ResultModel changestoredr(String sid);
	
	//ϵͳ����Ա����checkdr��ʾ�̼�
	public ResultModel checkbycheckdr(int checkdr);
	
	//ϵͳ����Ա����alterid�����޸���Ϣ���ͨ��
	public ResultModel changecheckdr(String alertid);
	
	//ϵͳ����Ա����alterid�����޸���Ϣ��˲�ͨ��
	public ResultModel changecheckdr1(String alertid);
	
	//��ʾ���е���פ�������˵��̼�
	public ResultModel checkAllstore(int page);
	
	//����̼ҵ�ʱ����з��
	public ResultModel banstoredr(String sid);
	
	//��ʾ������Ϣ�޸ĵ��̼�
	public ResultModel checkAllalterstore();
	
	//��ʾ���оٱ�����������̼�
	public ResultModel checkReportMerchant();
		
	//����ٱ�����������̼�
	public ResultModel banReportMerchant(String mid);
		
	//��ʾ���оٱ������������Ʒ
	public ResultModel checkReportGoods();
		
	//ǿ���¼ܾٱ������������Ʒ
	public ResultModel downReportGoods(String gid);
	
	public ResultModel changephtot(String address,String adminaccount);
	//���ͨ���޸ĵ��̱� �е���Ϣ
	public ResultModel adminUpdateStore(String aid);
	
	
	public Admin checkupload(String adminaccount,String adminpassword);
}

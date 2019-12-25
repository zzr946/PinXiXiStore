package com.softeem.service;

import com.softeem.model.ResultModel;

public interface OrderService {

	// �û��ύ�����ķ���(�ӹ��ﳵ��֧��ҳ��)
	public ResultModel subOrder(String ordernumber, String uid, String gid, int goodstotal);

	// ֧�������ѯ��֧������Ʒ��
	public ResultModel payGoods(String ordernumber);

	// �û�ȷ�϶��� ��������Ϊ�Ѹ���״̬(������)����ɹ�
	public ResultModel success(String uid, String addressid, String ordernumber, int money);

	// ���ݵ�ַid��ѯ��ַ��Ϣ
	public ResultModel addressinfo(String addressid);

	// �����û�id��ѯ�û����ж�����Ϣ
	public ResultModel orderAll(String uid);

	// �����û�id��ѯ�û����д�֧���Ķ���
	public ResultModel awaitOrder(String uid);

	// �����û�id��ѯ�û�����δ�����Ķ���
	public ResultModel awaitSendOrder(String uid);

	// �����û�id�鿴���ջ��Ķ���
	public ResultModel awaittakeOrder(String uid);

	// �����û�id�鿴�����۵Ķ���
	public ResultModel awaitevaluate(String uid);

	// �û��鿴����ʱһ��֧��(��ȡ��ַ��Ϣ)
	public ResultModel aKeyPayAddress(String uid, String gid);

	// �û��鿴����ʱһ��֧��(��ȡ��Ʒ��Ϣ)
	public ResultModel aKeyPayGoods(String uid, String gid);

	// �û��ջ��ķ���
	public ResultModel takeOrder(String uid, String gid);

	// �û��˻�
	public ResultModel returnmoney(String uid, String gid);

	// �û��ύ�˻���Ϣ
	public ResultModel subreturn(String uid, String goodsnumber, String returntype, String returncause,
			String returnmoney, String returndetails);

	// ����ָ��ҳ�Ķ���
	public ResultModel findByPage(int page, String mid);

	// ����
	public ResultModel fahuo(String id);

}

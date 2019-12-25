package com.softeem.service.impl;

import com.softeem.dao.UserDAO;
import com.softeem.model.ResultModel;
import com.softeem.model.User;
import com.softeem.service.UserService;
import com.softeem.tools.Tools;

public class UserServiceImpl implements UserService {
	UserDAO udao=new UserDAO();
	/**
	 * �û�ע��ķ���
	 * ����result����� codeΪ0��ʾ�ɹ� 1��ʾʧ�� 2�ֻ��Ų�����
	 */
	@Override
	public ResultModel reg(String phone, String password) {
		ResultModel result = new ResultModel();
		//�ж��ֻ����Ƿ����
		boolean boo=udao.selectPhoneExist(phone);
		if(boo){
			//�ֻ������Ѿ�����
			result.setCode("2");
			result.setMsg("�ֻ����ѱ�ռ��");
			result.setData(null);
			return result;
		}
		//�ֻ��ſ��ã��û�ע��
		//��ȡid
		String uid=Tools.getUUID();
		//�������
		password=Tools.getMD5(password);
		User user=new User();
		user.setUid(uid);
		user.setPhone(phone);
		user.setPassword(password);
		user.setUserdr(0);//ע��ɹ��˺Ŵ���δ��¼״̬
		//����dao��
		boolean bo=udao.insert(user);
		if(bo){
			//ע��ɹ�
			result.setCode("0");
			result.setMsg("ע��ɹ�");
			result.setData(user);//ֻ����id,�ֻ���,����,״̬0
		}else{
			//ע��ʧ��
			result.setCode("1");
			result.setMsg("ע��ʧ��");
			result.setData(null);
		}
		return result;
	}

	
	/**
	 * �û�ʹ�������¼
	 * ����result����� codeΪ0��ʾ�ɹ� 1��ʾʧ��
	 */
	@Override
	public ResultModel loginBypwd(String phone, String password) {
		ResultModel result = new ResultModel();
		//�������
		password=Tools.getMD5(password);
		//����dao��
		User user=udao.selectBypwd(phone, password);
		if(user==null){
			//��¼ʧ��
			result.setCode("1");
			result.setMsg("�û������������");
			result.setData(null);
		}else{
			//��¼�ɹ�
			result.setCode("0");
			result.setMsg("��¼�ɹ�");
			result.setData(user);
		}
		return result;
	}

	
	/**
	 * ʹ���ֻ���֤���¼
	 * ����result����� codeΪ0��ʾ�ɹ� 1��ʾʧ��
	 */
	@Override
	public ResultModel loginByphonecode(String phone) {
		ResultModel result = new ResultModel();
		//����dao��
		User user=udao.selectByPhoneCode(phone);
		if(user==null){
			//��¼ʧ��
			result.setCode("1");
			result.setMsg("���ֻ��Ż�δע��");
			result.setData(null);
		}else{
			//��¼�ɹ�
			result.setCode("0");
			result.setMsg("��¼�ɹ�");
			result.setData(user);
		}
		return result;
	}


	/**
	 * �û����������һ�����
	 * ����result����� codeΪ0��ʾ�ɹ� 1��ʾʧ�� 2��ʾ���ֻ���ûע��
	 */
	@Override
	public ResultModel modifyPassword(String phone, String newpassword) {
		ResultModel result=new ResultModel();
		//����dao���ѯ�ֻ����Ƿ����
		boolean boo=udao.selectPhoneExist(phone);
		if(!boo){
			//�ֻ��Ų�����(��û��ע��)
			result.setCode("2");
			result.setMsg("���ֻ�����δע��");
			result.setData(null);
			return result;
		}
		//�ֻ��Ŵ���(�Ѿ�ע�����)
		//�������
		newpassword=Tools.getMD5(newpassword);
		//����dao��ķ���
		boolean bo=udao.updateByphone(phone, newpassword);
		if(bo){
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
	 * �û��޸ĸ�����Ϣ
	 * ����result����� codeΪ0��ʾ�ɹ� 1��ʾʧ�� 
	 */
	@Override
	public ResultModel changeUserinfo(String uid,String photo, String nickname, String name, String sex) {
		ResultModel result=new ResultModel();
		//���û���Ϣװ��user����
		User user=new User();
		user.setPhoto(photo);
		user.setNickname(nickname);
		user.setName(name);
		user.setSex(sex);
		//����dao��ķ����޸��û���Ϣ
		boolean boo=udao.updateUserAll(uid, user);
		if(boo){
			//�޸ĳɹ�
			//����dao��ķ�����ѯ���û���������Ϣ
			User newuser=udao.selectUser(uid);
			result.setCode("0");
			result.setMsg("�޸ĳɹ�");
			result.setData(newuser);
		}else{
			//�޸�ʧ��
			result.setCode("1");
			result.setMsg("�޸�ʧ��");
			result.setData(null);
		}
		return result;
	}

	/**
	 * �û����������޸�����ķ���
	 * ����result����� codeΪ0��ʾ�ɹ� 1��ʾʧ��  2��ʾԭ�����������
	 */
	@Override
	public ResultModel modifypwdtopwd(String uid,String oldpwd,String newpwd) {
		ResultModel result=new ResultModel();
		//���Ȳ�ѯԭ���������Ƿ���ȷ
		//ԭ�������
		oldpwd=Tools.getMD5(oldpwd);
		//����dao��
		boolean bo=udao.selectpwd(uid, oldpwd);
		if(!bo){
			//���û��ѯ��
			result.setCode("2");
			result.setMsg("ԭ�����������");
			result.setData(null);
			return result;
		}
		
		//ԭ����������ȷ�����������ִ��
		//�������
		newpwd=Tools.getMD5(newpwd);
		//����dao��ķ���
		boolean boo=udao.updatepwd(uid, newpwd);
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
	 * �����û�id��ѯ�û�������Ϣ
	 */
	public ResultModel selectUser(String userid){
		ResultModel result=new ResultModel();
		//����dao��ķ���
		User u=udao.selectUser(userid);
		if(u==null){
			//��ѯʧ��
			result.setCode("1");
			result.setMsg("��ѯʧ��");
			result.setData(null);
		}else{
			//��ѯ�ɹ�
			result.setCode("0");
			result.setMsg("��ѯ�ɹ�");
			result.setData(u);
		}
		return result;
	}
	
}

package com.softeem.service;

import com.softeem.model.ResultModel;

public interface UserService {
	
	//�û�ע��
	public ResultModel reg(String phone,String password);
	
	
	//�˺������¼
	public ResultModel loginBypwd(String phone,String password);
	
	
	//������֤��¼
	public ResultModel loginByphonecode(String phone);
	
	
	//�޸�����ķ���
	public ResultModel modifyPassword(String phone,String newpassword);
	
	
	//�޸��û���Ϣ�ķ���
	public ResultModel changeUserinfo(String uid,String photo,String nickname,String name,String sex);
	
	
	//�û����������޸�����ķ���
	public ResultModel modifypwdtopwd(String uid,String oldpwd,String newpwd);
	
	//����id��ѯ�û�������Ϣ
	public ResultModel selectUser(String userid);
	
}

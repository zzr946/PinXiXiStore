package com.softeem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.softeem.model.User;
import com.softeem.tools.DBUtil;

public class UserDAO {
	/**
	 * ��ѯ�ֻ����Ƿ��Ѿ�����(ע����)
	 * @param phone ��Ҫ��ѯ���ֻ���
	 * @return true��ʾ�ֻ����Ѿ�����(ע�᲻��ʹ��)  false��ʾ�ֻ��Ų�����(ע�����ʹ��)
	 */
	public boolean selectPhoneExist(String phone){
		boolean boo=false;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select uid from user where phone=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, phone);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				//�ֻ����Ѿ�����
				boo=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}
	
	
	/**
	 * �û�ע��(��������)
	 * @param user ��Ҫע����û�����
	 * @return true��ʾע��ɹ�  false��ʾע��ʧ��
	 */
	public boolean insert(User user){
		boolean boo=false;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="insert into user(uid,password,phone,userdr) values(?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, user.getUid());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getPhone());
			ps.setInt(4, 0);//��ע��Ϊ����״̬
			int i=ps.executeUpdate();
			if(i>0){
				//ע��ɹ�
				boo=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}
	
	
	/**
	 * �û���¼(�ֻ��������¼)
	 * @param mobile  ���ڵ�¼���ֻ���
	 * @param pwd �����¼������
	 * @return ����user�Զ��� ������null���¼ʧ��,������null���¼�ɹ�
	 */
	public User selectBypwd(String mobile,String pwd){
		User user=null;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select uid,nickname,password,phone,photo,name,sex,userdr,reserved1,reserved2,reserved3,reserved4,reserved5 from user where phone=? and password=? and userdr=0";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, mobile);
			ps.setString(2, pwd);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				String uid=rs.getString("uid");
				String nickname=rs.getString("nickname");
				String password=rs.getString("password");
				String phone=rs.getString("phone");
				String photo=rs.getString("photo");
				String name=rs.getString("name");
				String sex=rs.getString("sex");
				int userdr=rs.getInt("userdr");
				user=new User(uid, nickname, password, phone, photo, name, sex, userdr, null, null, null, null, null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	/**
	 * �û���¼(�ֻ���֤���¼)
	 * @param mobile
	 * @return ����user�Զ��� ������null���¼ʧ��,������null���¼�ɹ�
	 */
	public User selectByPhoneCode(String mobile){
		User user=null;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select uid,nickname,password,phone,photo,name,sex,userdr,reserved1,reserved2,reserved3,reserved4,reserved5 from user where phone=? and userdr=0";		
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, mobile);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				String uid=rs.getString("uid");
				String nickname=rs.getString("nickname");
				String password=rs.getString("password");
				String phone=rs.getString("phone");
				String photo=rs.getString("photo");
				String name=rs.getString("name");
				String sex=rs.getString("sex");
				int userdr=rs.getInt("userdr");
				user=new User(uid, nickname, password, phone, photo, name, sex, userdr, null, null, null, null, null);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	/**
	 * �û������ֻ����һ�����
	 * @param phone ��Ҫ�һ�����ķ���
	 * @param newpassword ������
	 * @return true��ʾ�޸�����ɹ�  false��ʾ�����޸�ʧ��
	 */
	public boolean updateByphone(String phone,String newpassword){
		boolean boo=false;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="update user set password=? where phone=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, newpassword);
			ps.setString(2, phone);
			int i=ps.executeUpdate();
			if(i>0){
				boo=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}
	
	
	/**
	 * �����û�id��ѯ�û�������Ϣ
	 * @param userid ��Ҫ��ѯ���û�id
	 * @return ���ز�ѯ����user����  ��û��ѯ���򷵻�null
	 */
	public User selectUser(String userid){
		User user=null;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select uid,nickname,password,phone,photo,name,sex,userdr,reserved1,"
					+ "reserved2,reserved3,reserved4,reserved5 from user where uid=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, userid);
			ResultSet rs=ps.executeQuery();
			while (rs.next()) {
				String uid=rs.getString("uid");
				String nickname=rs.getString("nickname");
				String password=rs.getString("password");
				String phone=rs.getString("phone");
				String photo=rs.getString("photo");
				String name=rs.getString("name");
				String sex=rs.getString("sex");
				int userdr=rs.getInt("userdr");
				String reserved1=rs.getString("reserved1");
				String reserved2=rs.getString("reserved2");
				String reserved3=rs.getString("reserved3");
				String reserved4=rs.getString("reserved4");
				String reserved5=rs.getString("reserved5");
				user=new User(uid, nickname, password, phone, photo, name, sex, userdr, reserved1, reserved2, reserved3, reserved4, reserved5);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return user;
	}
	
	
	/**
	 * �����û�id�޸��û���Ϣ
	 * @param uid ��Ҫ�޸���Ϣ���û�id
	 * @param user �µ��û���Ϣ
	 * @return �޸ĳɹ�����true���޸�ʧ�ܷ���false
	 */
	public boolean updateUserAll(String uid,User user){
		boolean boo=false;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="update user set photo=?,nickname=?,name=?,sex=? where uid=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, user.getPhoto());
			ps.setString(2, user.getNickname());
			ps.setString(3, user.getName());
			ps.setString(4, user.getSex());
			ps.setString(5, uid);
			int i=ps.executeUpdate();
			if(i>0){
				boo=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}
	
	
	/**
	 * �û����������޸�����
	 * @param uid ��Ҫ�޸�������û�ID
	 * @param oldpwd ԭ����
	 * @param newpwd �޸ĺ������
	 * @return �޸ĳɹ�����true,�޸�ʧ�ܷ���false
	 */
	public boolean updatepwd(String uid,String newpwd){
		boolean boo=false;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="update user set password=? where uid=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, newpwd);
			ps.setString(2, uid);
			int i=ps.executeUpdate();
			if(i>0){
				boo=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}
	
	
	/**
	 * �����û�id�������ѯ�Ƿ����
	 * @param uid ��Ҫ��ѯ���û�id
	 * @param pwd ��Ҫ��ѯ������
	 * @return ��ѯ���˷���true û��ѯ������false
	 */
	public boolean selectpwd(String uid,String pwd){
		boolean boo=false;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select uid,nickname,password,phone,photo,name,sex,userdr,reserved1,reserved2,reserved3,reserved4,reserved5 from user where uid=? and password=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, pwd);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				boo=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}
	
	
	
}

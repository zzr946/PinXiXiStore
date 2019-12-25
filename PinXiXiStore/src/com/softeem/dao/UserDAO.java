package com.softeem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.softeem.model.User;
import com.softeem.tools.DBUtil;

public class UserDAO {
	/**
	 * 查询手机号是否已经存在(注册检查)
	 * @param phone 需要查询的手机号
	 * @return true表示手机号已经存在(注册不能使用)  false表示手机号不存在(注册可以使用)
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
				//手机号已经存在
				boo=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}
	
	
	/**
	 * 用户注册(插入数据)
	 * @param user 需要注册的用户对象
	 * @return true表示注册成功  false表示注册失败
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
			ps.setInt(4, 0);//刚注册为离线状态
			int i=ps.executeUpdate();
			if(i>0){
				//注册成功
				boo=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}
	
	
	/**
	 * 用户登录(手机号密码登录)
	 * @param mobile  用于登录的手机号
	 * @param pwd 用与登录的密码
	 * @return 返回user对对象 若等于null则登录失败,不等于null则登录成功
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
	 * 用户登录(手机验证码登录)
	 * @param mobile
	 * @return 返回user对对象 若等于null则登录失败,不等于null则登录成功
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
	 * 用户根据手机号找回密码
	 * @param phone 需要找回密码的方法
	 * @param newpassword 新密码
	 * @return true表示修改密码成功  false表示密码修改失败
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
	 * 根据用户id查询用户所有信息
	 * @param userid 需要查询的用户id
	 * @return 返回查询到的user对象  若没查询到则返回null
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
	 * 根据用户id修改用户信息
	 * @param uid 需要修改信息的用户id
	 * @param user 新的用户信息
	 * @return 修改成功返回true，修改失败返回false
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
	 * 用户根据密码修改密码
	 * @param uid 需要修改密码的用户ID
	 * @param oldpwd 原密码
	 * @param newpwd 修改后的密码
	 * @return 修改成功返回true,修改失败返回false
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
	 * 根据用户id和密码查询是否存在
	 * @param uid 需要查询的用户id
	 * @param pwd 需要查询的密码
	 * @return 查询到了返回true 没查询到返回false
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

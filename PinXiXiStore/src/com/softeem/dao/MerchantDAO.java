package com.softeem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.softeem.model.Merchant;
import com.softeem.tools.DBUtil;
import com.softeem.tools.DBUtil.CallBack;

public class MerchantDAO {

	/**
	 * ��ѯ�̼��ֻ����Ƿ����
	 * @param phone	�̼��ֻ���
	 * @return true��ʾ�Ѵ��ڣ�false��ʾ������
	 */
	public boolean select_Merchant_Phoneexist(String phone){
		boolean boo = false;
		ResultSet rs = null;
		PreparedStatement ps = null;
		Connection conn =null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select mid from merchant where mphone=? and merchantdr=0");
			ps.setString(1, phone);
			rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return false;
	}
	
	/**
	 * 
	 * @param merchant �̼��û�
	 * @return trueΪ��ӳɹ�falseΪ���ʧ��
	 */
	public boolean insert(Merchant merchant){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("insert into merchant(mid,mphone,mpassword,merchantdr) values(?,?,?,?)");
			ps.setString(1, merchant.getMid());
			ps.setString(2, merchant.getPhone());
			ps.setString(3, merchant.getMpassword());
			ps.setInt(4, 0);
			int i = ps.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public Merchant selectByPassword(String phone,String password){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select mid,merchantdr,sid,reserved1,reserved2,reserved3,reserved4,reserved5 "
					+ "from merchant where mphone=? and mpassword=? and merchantdr=0");
			ps.setString(1, phone);
			ps.setString(2, password);
			rs = ps.executeQuery();
			while(rs.next()){
				String mid = rs.getString("mid");
				int merchantdr = rs.getInt("merchantdr");
				String sid = rs.getString("sid");
				String reserved1 = rs.getString("reserved1");
				String reserved2 = rs.getString("reserved2");
				String reserved3 = rs.getString("reserved3");
				String reserved4 = rs.getString("reserved4");
				String reserved5 = rs.getString("reserved5");
				String mpassword = password;
				Merchant merchant = new Merchant(mid, phone, mpassword, merchantdr, sid, reserved1, reserved2, reserved3, reserved4, reserved5);
				return merchant;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return null;
	}
	
	
	/**
	 * 
	 * @param sid ����id
	 * @param mid �̼�id
	 * @return true�ɹ�false���ɹ�
	 */
	public boolean updateSid(String sid,String mid){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("update merchant set sid=? where mid=?");
			ps.setString(1, sid);
			ps.setString(2, mid);
			int i = ps.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	/**
	 * 
	 * @param phone �ֻ���
	 * @param password �޸ĵ�����
	 * @return true�޸ĳɹ�false�޸�ʧ��
	 */
	public boolean updatePWD(String phone,String password){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("update merchant set mpassword=? where mphone=?");
			ps.setString(1, password);
			ps.setString(2, phone);
			int i = ps.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
		
		
	}
	
}

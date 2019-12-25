package com.softeem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softeem.model.Sort;
import com.softeem.tools.DBUtil;

public class SortDAO {

	/**
	 * ��ѯ������Ƿ����
	 * @param sortName �����
	 * @return trueΪ���� falseΪ������
	 */
	public boolean selectBySortName(String sortName){
		Connection conn = null; 
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select sid from sort where sortname=?");
			ps.setString(1, sortName);
			rs = ps.executeQuery();
			if(rs.next()){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return false;
	}
	
	/**
	 * 
	 * @param sort������
	 * @return trueΪ�ɹ�falseΪʧ��
	 */
	public boolean insert(Sort sort){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("insert into sort(sid,sortname,sortinfo,merchantid) values(?,?,?,?)");
			ps.setString(1, sort.getSid());
			ps.setString(2, sort.getSortname());
			ps.setString(3, sort.getSortinfo());
			ps.setString(4, sort.getMerchantid());
			int i = ps.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, null);
		}
		return false;
	}
	
	/**
	 * 
	 * @param mid�̼�id
	 * @param pageҳ��
	 * @return����һ���б���
	 */
	public List<Sort> selectByPage(String mid,int page){
		//��ÿҳ5������ѯ
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Sort> list = new ArrayList<Sort>();
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select * from sort where merchantid=? limit ?,5");
			ps.setString(1, mid);
			ps.setInt(2, (page-1)*5);
			rs = ps.executeQuery();
			while(rs.next()){
				String sid = rs.getString("sid");
				String sortname = rs.getString("sortname");
				String sortinfo = rs.getString("sortinfo");
				String merchantid = mid;
				String reserved1 = rs.getString("reserved1");
				String reserved2 = rs.getString("reserved2");
				String reserved3 = rs.getString("reserved3");
				String reserved4 = rs.getString("reserved4");
				String reserved5 = rs.getString("reserved5");
				Sort sort = new Sort(sid, sortname, sortinfo, merchantid, reserved1, reserved2, reserved3, reserved4, reserved5);
				list.add(sort);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}
	
	
	/**
	 * 
	 * @param sid Ҫɾ����sort sid
	 * @return trueΪɾ���ɹ� falseΪɾ��ʧ��
	 */
	public boolean deleteBySid(String sid){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("delete from sort where sid =?");
			ps.setString(1, sid);
			int i = ps.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, null);
		}
		return false;
		
	}
	
	/**
	 * 
	 * @param sortname1 ԭ���ķ�����
	 * @param sortname2 Ҫ�ĳɵķ�����
	 * @param sortinfo Ҫ�ĳɵķ�����
	 * @return trueΪ�޸ĳɹ� falseΪ�޸�ʧ��
	 */
	public boolean updateSort(String sortname1,String sortname2,String sortinfo){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("update sort set sortname=?,sortinfo=? where sortname=?");
			ps.setString(1, sortname2);
			ps.setString(2, sortinfo);
			ps.setString(3, sortname1);
			int i = ps.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, null);
		}
		return false;
	}
	
	
	public List<Sort> selectAll(String mid){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Sort> list = new ArrayList<Sort>();
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select * from sort where merchantid=?");
			ps.setString(1, mid);
			rs = ps.executeQuery();
			while(rs.next()){
				String sid = rs.getString("sid");
				String sortname = rs.getString("sortname");
				String sortinfo = rs.getString("sortinfo");
				String merchantid = mid;
				String reserved1 = rs.getString("reserved1");
				String reserved2 = rs.getString("reserved2");
				String reserved3 = rs.getString("reserved3");
				String reserved4 = rs.getString("reserved4");
				String reserved5 = rs.getString("reserved5");
				Sort sort = new Sort(sid, sortname, sortinfo, merchantid, reserved1, reserved2, reserved3, reserved4, reserved5);
				list.add(sort);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}
	
	/**
	 * 
	 * @param mid �̼�id
	 * @param sortname �����
	 * @return ���id
	 */
	public String selectSid(String mid,String sortname){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select sid from sort where merchantid=? and sortname=?");
			ps.setString(1, mid);
			ps.setString(2, sortname);
			rs = ps.executeQuery();
			while(rs.next()){
				String sid = rs.getString("sid");
				return sid;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return null;
	}
}

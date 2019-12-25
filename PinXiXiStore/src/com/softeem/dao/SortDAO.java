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
	 * 查询类别名是否存在
	 * @param sortName 类别名
	 * @return true为存在 false为不存在
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
	 * @param sort类别对象
	 * @return true为成功false为失败
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
	 * @param mid商家id
	 * @param page页数
	 * @return返回一个列表集合
	 */
	public List<Sort> selectByPage(String mid,int page){
		//按每页5条来查询
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
	 * @param sid 要删除的sort sid
	 * @return true为删除成功 false为删除失败
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
	 * @param sortname1 原来的分类名
	 * @param sortname2 要改成的分类名
	 * @param sortinfo 要改成的分类简介
	 * @return true为修改成功 false为修改失败
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
	 * @param mid 商家id
	 * @param sortname 类别名
	 * @return 类别id
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

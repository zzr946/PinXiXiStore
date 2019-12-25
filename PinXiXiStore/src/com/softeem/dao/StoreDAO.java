package com.softeem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

import com.softeem.model.Alertstore;
import com.softeem.model.Store;
import com.softeem.tools.DBUtil;

public class StoreDAO {

	/**
	 * 
	 * @param storename 店铺名
	 * @return true为店铺存在	 false为不存在
	 */
	public boolean selectStoreExist(String storename){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select sid from store where storename=?");
			ps.setString(1, storename);
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
	 * @param store 店铺对象
	 * @return true为添加成功 false为添加失败
	 */
	public boolean insert(Store store){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("insert into store(sid,storename,storelogo,storeinfo,storeaddress,storedr,aptitude,subtime) values"
					+ "(?,?,?,?,?,?,?,?)");
			ps.setString(1, store.getSid());
			ps.setString(2, store.getStorename());
			ps.setString(3, store.getStorelogo());
			ps.setString(4, store.getStoreinfo());
			ps.setString(5, store.getStoreaddress());
			ps.setInt(6, store.getStoredr());
			ps.setString(7, store.getAptitude());
			ps.setString(8, store.getSubtime());
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
	 * @param msid 商家表中的sid
	 * @return true为已创建店铺  false为未创建店铺
	 */
	public Store selectByMsid(String msid){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select * from store where sid = ?");
			ps.setString(1, msid);
			rs = ps.executeQuery();
			while(rs.next()){
				Store store = new Store();
				String sid = rs.getString("sid");
				String storename = rs.getString("storename");
				String storelogo = rs.getString("storelogo");
				String storeinfo = rs.getString("storeinfo");
				String storeaddress = rs.getString("storeaddress");
				int storedr = rs.getInt("storedr");
				String aptitude = rs.getString("aptitude");
				String subtime = rs.getString("subtime");
				store.setSid(sid);
				store.setStorename(storename);
				store.setStorelogo(storelogo);
				store.setStoreinfo(storeinfo);
				store.setStoreaddress(storeaddress);
				store.setStoredr(storedr);
				store.setAptitude(aptitude);
				store.setSubtime(subtime);
				return store;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return null;
	}
	
	
	/**
	 * 
	 * @param alertid 修改表的主键
	 * @param mid 商家id
	 * @param newname 新的名字
	 * @param newstoreinfo 新的简介
	 * @param newaddress 新地址
	 * @param checkdr 审核状态
	 * @return true为提交成功 false为提交失败
	 */
	public boolean updateStore(String mid,String newname,String newstoreinfo,String newaddress){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("insert into alertstore(alertid,mid,newname,newstoreinfo,newaddress,checkdr) "
						+ "values(?,?,?,?,?,?)");
			ps.setString(1, UUID.randomUUID().toString());
			ps.setString(2, mid);
			ps.setString(3, newname);
			ps.setString(4, newstoreinfo);
			ps.setString(5, newaddress);
			ps.setInt(6, 0);
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
	
}

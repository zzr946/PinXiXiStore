package com.softeem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softeem.model.Admin;
import com.softeem.model.Alertstore;
import com.softeem.model.Report;
import com.softeem.model.Store;
import com.softeem.tools.DBUtil;

import javafx.scene.control.Alert;

public class AdminDAO {
	
	
	/**
	 * ϵͳ����Ա��¼
	 * @param adminaccount	���ڵ�¼�Ĺ���Ա�˺�
	 * @param password		���ڵ�¼�Ĺ���Ա����
	 * @return				����һ��ϵͳ����Ա
	 */
	public Admin loadbyadminaccount(String adminaccount,String adminpassword){
		Admin admin =null;
		try {
			Connection conn=DBUtil.getConnection();
			String sql ="select aid,adminaccount,adminpassword,adminname,reserved3 from admin where adminaccount=? and adminpassword=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, adminaccount);
			ps.setString(2, adminpassword);
			ResultSet rs =  ps.executeQuery();
			
			while(rs.next()){
				String aid =rs.getString("aid");
				String adminaccount1 = rs.getString("adminaccount");
				String adminpassword1 = rs.getString("adminpassword");
				String adminname = rs.getString("adminname");
				String reserved3 = rs.getString("reserved3");
				admin = new Admin(aid, adminaccount1, adminpassword1, adminname, null, null, reserved3, null, null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}

	/**
	 * 
	 * @param aid �޸ı������id
	 * @return ����һ���޸ı��еĶ���
	 */
	public Alertstore selectByAid(String aid){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		Alertstore as = new Alertstore();
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select * from alertstore where alertid=?");
			ps.setString(1, aid);
			rs = ps.executeQuery();
			while(rs.next()){
				String mid = rs.getString("mid");
				String newname = rs.getString("newname");
				String newstoreinfo = rs.getString("newstoreinfo");
				String newaddress = rs.getString("newaddress");
				as.setMid(mid);
				as.setNewname(newname);
				as.setNewstoreinfo(newstoreinfo);
				as.setNewaddress(newaddress);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return as;
	}
	/**
	 * 
	 * @param asһ���޸ĵĶ���
	 * @param sid�̼�id 
	 * @return trueΪ�޸ĳɹ�falseΪ�޸�ʧ��
	 */
	public boolean updateStore(Alertstore as,String sid){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("update store set storename=?,storeaddress=?,"
										+ "storeinfo=? where sid=?");
			ps.setString(1, as.getNewname());
			ps.setString(2, as.getNewaddress());
			ps.setString(3, as.getNewstoreinfo());
			ps.setString(4, sid);
			int i = ps.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, null);
		}
		return false;
	}
	/**
	 * 
	 * @param mid �̼�id
	 * @return ����һ������id
	 */
	public String selectByMid(String mid){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select sid from merchant where mid=?");
			ps.setString(1, mid);
			rs = ps.executeQuery();
			while(rs.next()){
				String sid = rs.getString("sid");
				return sid;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return null;
	}
	
	
	/*
	 * �����޸�
	 */
	public boolean changebyadminaccount(String adminaccount,String adminpassword){
		boolean boo = false;
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "update admin set adminpassword=? where adminaccount=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, adminpassword);
			ps.setString(2, adminaccount);
			int i = ps.executeUpdate();
			if(i>0){
				boo=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}
	
	
	
	/**
	 * �����û����鿴�ܱ���ȷ���ܱ��鿴���룩
	 * @param adminname   ��Ҫ�޸�ϵͳ����Ա������
	 * @return			һ��ϵͳ����Ա����
	 */
	public Admin checkbyquestion(String adminname){
		Admin admin = null;
		try {
			Connection conn  = DBUtil.getConnection();
			String sql = "select * from admin where adminname=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, adminname);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String aid = rs.getString("aid");
				String adminaccount = rs.getString("adminaccount");
				String adminpassword = rs.getString("adminpassword");
				String adminname1 = rs.getString("adminname");
				String reserved1 = rs.getString("reserved1");
				String reserved2 = rs.getString("reserved2");
				admin = new Admin(aid, adminaccount, adminpassword, adminname1, reserved1, reserved2, null, null, null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}
	
	
	public Admin checkbyquestion1(String answer){
		Admin admin = null;
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "select * from admin where reserved2=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, answer);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String aid = rs.getString("aid");
				String adminaccount = rs.getString("adminaccount");
				String adminpassword = rs.getString("adminpassword");
				String adminname1 = rs.getString("adminname");
				String reserved1 = rs.getString("reserved1");
				String reserved2 = rs.getString("reserved2");
				admin = new Admin(aid, adminaccount, adminpassword, adminname1, reserved1, reserved2, null, null, null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return admin;
	}
	
	
	
	//�̼���פ���
	
	
	/*
	 * �Ȳ鵽�����̵�
	 */
	public List<Store> checkAllstore(int page){
		Store store = null;
		List<Store> list =new ArrayList<Store>();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "select * from store order by storedr limit ?,5 ";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, (page-1)*5);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String sid = rs.getString("sid");
				String storename = rs.getString("storename");
				String storelogo = rs.getString("storelogo");
				String storeinfo = rs.getString("storeinfo");
				String storeaddress = rs.getString("storeaddress");
				int storedr1 = rs.getInt("storedr");
				String aptitude = rs.getString("aptitude");
				String subtime = rs.getString("subtime");
				String reserved1 = rs.getString("reserved1");
				String reserved2 = rs.getString("reserved2");
				String reserved3 = rs.getString("reserved3");
				String reserved4 = rs.getString("reserved4");
				String reserved5 = rs.getString("reserved5");
				store = new Store(sid, storename, storelogo, storeinfo, storeaddress, storedr1, aptitude, subtime, reserved1, reserved2, reserved3, reserved4, reserved5);
				list.add(store);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	/*
	 * �Ȳ鵽�̵�
	 */
	public List<Store> checkstore(int storedr){
		Store store = null;
		List<Store> list =new ArrayList<Store>();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "select * from store where storedr=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, storedr);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String sid = rs.getString("sid");
				String storename = rs.getString("storename");
				String storelogo = rs.getString("storelogo");
				String storeinfo = rs.getString("storeinfo");
				String storeaddress = rs.getString("storeaddress");
				int storedr1 = rs.getInt("storedr");
				String aptitude = rs.getString("aptitude");
				String subtime = rs.getString("subtime");
				String reserved1 = rs.getString("reserved1");
				String reserved2 = rs.getString("reserved2");
				String reserved3 = rs.getString("reserved3");
				String reserved4 = rs.getString("reserved4");
				String reserved5 = rs.getString("reserved5");
				store = new Store(sid, storename, storelogo, storeinfo, storeaddress, storedr1, aptitude, subtime, reserved1, reserved2, reserved3, reserved4, reserved5);
				list.add(store);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//�̼���Ϣ��ס���
	public boolean changestoredr(String sid,int storedr){
		boolean boo= false;
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "update store set storedr=? where sid=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, storedr);
			ps.setString(2, sid);
			int i = ps.executeUpdate();
			if(i>0){
				boo = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}
	
	
	//�̼��޸���Ϣ���
	public boolean changecheckdr(String alertid,int checkdr){
		System.out.println("�Ѿ������޸���Ϣ��˵�DAO");
		boolean boo =false;
		System.out.println(alertid);
		System.out.println(checkdr);
		Connection conn;
		try {
			conn = DBUtil.getConnection();
			String sql = "update Alertstore set checkdr=? where alertid=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1,checkdr);
			ps.setString(2,alertid);
			int i = ps.executeUpdate();
			if(i>0){
				boo = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println(boo);
		return boo;
	}
	
	//�鵽��Ҫ�޸��̵���Ϣ
	public List<Alertstore> checkalterstore(int checkdr){
		Alertstore alstore = null;
		List<Alertstore> list =new ArrayList<Alertstore>();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "select * from Alertstore where checkdr=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setInt(1, checkdr);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String alertid = rs.getString("alertid");
				String mid = rs.getString("mid");
				String newname = rs.getString("newname");
				String newstoreinfo = rs.getString("newstoreinfo");
				String newaddress = rs.getString("newaddress");
				int checkdr1 = rs.getInt("checkdr");
				String reserved1 = rs.getString("reserved1");
				String reserved2 = rs.getString("reserved2");
				String reserved3 = rs.getString("reserved3");
				String reserved4 = rs.getString("reserved4");
				String reserved5 = rs.getString("reserved5");
				alstore = new Alertstore(alertid, mid, newname, newstoreinfo, newaddress, checkdr1, reserved1, reserved2, reserved3, reserved4, reserved5);
				list.add(alstore);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	//�Ȳ鵽������Ҫ�����޸ĵ��̵�
	public List<Alertstore> checkAllalterstore(){
		Alertstore alstore = null;
		List<Alertstore> list =new ArrayList<Alertstore>();
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "select * from Alertstore";
			PreparedStatement ps = conn.prepareStatement(sql);
			ResultSet rs = ps.executeQuery();
			while(rs.next()){
				String alertid = rs.getString("alertid");
				String mid = rs.getString("mid");
				String newname = rs.getString("newname");
				String newstoreinfo = rs.getString("newstoreinfo");
				String newaddress = rs.getString("newaddress");
				int checkdr1 = rs.getInt("checkdr");
				String reserved1 = rs.getString("reserved1");
				String reserved2 = rs.getString("reserved2");
				String reserved3 = rs.getString("reserved3");
				String reserved4 = rs.getString("reserved4");
				String reserved5 = rs.getString("reserved5");
				alstore = new Alertstore(alertid, mid, newname, newstoreinfo, newaddress, checkdr1, reserved1, reserved2, reserved3, reserved4, reserved5);
				list.add(alstore);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	public boolean changephoto(String address,String adminaccount){
		boolean boo = false;
		try {
			Connection conn = DBUtil.getConnection();
			String sql = "update admin set reserved3=? where adminaccount=?";
			PreparedStatement ps = conn.prepareStatement(sql);
			ps.setString(1, address);
			ps.setString(2, adminaccount);
			int i = ps.executeUpdate();
			if(i>0){
				//�ɹ�
				boo = true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}
	//��ʾ�ٱ��������ڵ���2�ε��̼�
		public List<Report> checkreportmarchant(){
			Report report=null;
			List<Report> list= new ArrayList<Report>();
			try {
				Connection conn = DBUtil.getConnection();
				String sql = "select * from report WHERE gid in ( select gid from  report group by gid having count(gid)>1) ORDER BY reporttime DESC";
				PreparedStatement ps = conn.prepareStatement(sql);
				
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					String id = rs.getString("id");
					String uid = rs.getString("uid");
					String gid = rs.getString("gid");
					String goodsname = rs.getString("goodsname");
					String mid = rs.getString("mid");
					String cause = rs.getString("cause");
					String content = rs.getString("content");
					String reporttime = rs.getString("reporttime");
					String reserved1 = rs.getString("reserved1");
					String reserved2 = rs.getString("reserved2");
					String reserved3 = rs.getString("reserved3");
					String reserved4 = rs.getString("reserved4");
					String reserved5 = rs.getString("reserved5");
					report = new Report(id, uid, gid, goodsname, mid, cause, content, reporttime, reserved1, reserved2, reserved3, reserved4, reserved5);
				    list.add(report);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		
		
		//����ٱ�����������̼�
			public boolean banTheMerchant(String mid){
				boolean boo= false;
				try {
					Connection conn = DBUtil.getConnection();
					String sql = "update merchant set merchantdr=2 where mid=?";
					PreparedStatement ps = conn.prepareStatement(sql);
					ps.setString(1, mid);
					
					int i = ps.executeUpdate();
					if(i>0){
						boo = true;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				return boo;
			}
		//ǿ���¼ܲ��ϸ��Ʒ ����ʾ�ٱ���������1��
		public List<Report> checkreportgoods(){
			Report report = null;
			List<Report> list= new ArrayList<Report>();
			try {
				Connection conn = DBUtil.getConnection();
				String sql = "select * from report ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ResultSet rs = ps.executeQuery();
				while(rs.next()){
					String id = rs.getString("id");
					String uid = rs.getString("uid");
					String gid = rs.getString("gid");
					String goodsname = rs.getString("goodsname");
					String mid = rs.getString("mid");
					String cause = rs.getString("cause");
					String content = rs.getString("content");
					String reporttime = rs.getString("reporttime");
					String reserved1 = rs.getString("reserved1");
					String reserved2 = rs.getString("reserved2");
					String reserved3 = rs.getString("reserved3");
					String reserved4 = rs.getString("reserved4");
					String reserved5 = rs.getString("reserved5");
					report = new Report(id, uid, gid, goodsname, mid, cause, content, reporttime, reserved1, reserved2, reserved3, reserved4, reserved5);
					list.add(report);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return list;
		}
		
		//ǿ���¼ܾٱ������������Ʒ
				public boolean downTheGoods(String gid){
					boolean boo= false;
					try {
						Connection conn = DBUtil.getConnection();
						String sql = "update goods set goodsdr=2 where gid=?";
						PreparedStatement ps = conn.prepareStatement(sql);
						ps.setString(1, gid);
						
						int i = ps.executeUpdate();
						if(i>0){
							boo = true;
						}
					} catch (SQLException e) {
						e.printStackTrace();
					}
					return boo;
				}
	
	
}

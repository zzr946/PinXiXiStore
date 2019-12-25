package com.softeem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softeem.model.ResultModel;
import com.softeem.model.Uaddress;
import com.softeem.tools.DBUtil;

public class UAddressDAO {
	
	/**
	 * �û�����ջ���ַ�ķ���
	 * @param address ��Ҫ��ӵ��ջ���ַ
	 * @return
	 */
	public boolean insertUseraddress(Uaddress address){
		boolean boo=false;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="insert into uaddress(uadid,uid,province,city,area,detailaddress,addressdr,mobile,uadname) values(?,?,?,?,?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, address.getUadid());
			ps.setString(2, address.getUid());
			ps.setString(3, address.getProvince());
			ps.setString(4, address.getCity());
			ps.setString(5, address.getArea());
			ps.setString(6, address.getDatailaddress());
			ps.setInt(7, address.getAddressdr());
			ps.setString(8, address.getMobile());
			ps.setString(9, address.getUadname());
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
	 * �����û�id��ѯ���е��ջ���ַ
	 * @param uid ��Ҫ��ѯ��ַ���û�id
	 * @return ���ز�ѯ�����ĵ�ַ���� ��������򼯺ϲ�Ϊ�գ�û�������򼯺�Ϊ��
	 */
	public List<Uaddress> selectaddressAll(String uid){
		List<Uaddress> list=new ArrayList<Uaddress>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select uadid,uid,province,city,area,detailaddress,addressdr,"
					+ "mobile,uadname,reserved1,reserved2,reserved3,reserved4,reserved5 "
					+ "from uaddress where uid=? and addressdr!=2";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				String uadid=rs.getString("uadid");
				String province=rs.getString("province");
				String city=rs.getString("city");
				String area=rs.getString("area");
				String datailaddress=rs.getString("detailaddress");
				int addressdr=rs.getInt("addressdr");
				String mobile=rs.getString("mobile");
				String uadname=rs.getString("uadname");
				String reserved1=rs.getString("reserved1");
				String reserved2=rs.getString("reserved2");
				String reserved3=rs.getString("reserved3");
				String reserved4=rs.getString("reserved4");
				String reserved5=rs.getString("reserved5");
				list.add(new Uaddress(uadid, uid, province, city, area, datailaddress, addressdr, mobile, uadname, reserved1, reserved2, reserved3, reserved4, reserved5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * ��ָ���û��µ����е�ַ������Ϊ��ѡ״̬
	 * @param uid ָ�����û�
	 * @return �޸ĳɹ�����true �޸�ʧ��false
	 */
	public boolean updateaddresstonot(String uid){
		boolean boo=false;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="update uaddress set addressdr=1 where uid=? and addressdr!=2";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, uid);
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
	 * ���û�ָ���û�id��ָ����ַ�޸�ΪĬ�ϵ�ַ
	 * @param uid ��Ҫ�޸�Ĭ�ϵ�ַ���û�id
	 * @param uadid ��Ҫ�����ó�Ĭ�ϵ�ַ�ĵ�ַid
	 * @return ���óɹ�����true ����ʧ�ܷ���false
	 */
	public boolean updateaddress(String uid,String uadid){
		System.out.println(uid);
		System.out.println(uadid);
		
		boolean boo=false;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="update uaddress set addressdr=0 where uadid=? and uid=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, uadid);
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
	 * ��ѯָ��id���û���Ĭ�ϵ�ַ
	 * @param uid
	 * @return
	 */
	public Uaddress selectdefaultaddress(String uid){
		Uaddress address=null;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select uadid,uid,province,city,area,detailaddress,addressdr,mobile,uadname,reserved1,reserved2,reserved3,reserved4,reserved5 from uaddress where uid=? and address=0";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				String uadid=rs.getString("uadid");
				String province=rs.getString("province");
				String city=rs.getString("city");
				String area=rs.getString("area");
				String datailaddress=rs.getString("detailaddress");
				int addressdr=rs.getInt("addressdr");
				String mobile=rs.getString("mobile");
				String uadname=rs.getString("uadname");
				String reserved1=rs.getString("reserved1");
				String reserved2=rs.getString("reserved2");
				String reserved3=rs.getString("reserved3");
				String reserved4=rs.getString("reserved4");
				String reserved5=rs.getString("reserved5");
				address=new Uaddress(uadid, uid, province, city, area, datailaddress, addressdr, mobile, uadname, reserved1, reserved2, reserved3, reserved4, reserved5);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return address;
	}
	
	/**
	 * 	�û�ɾ����ַ�ķ���
	 * @param uid �û�id
	 * @param addressid ��Ҫɾ���ĵ�ַid
	 * @return ɾ���ɹ�����true ɾ���Ǹ÷���false
	 */
	public boolean UpdateAddress(String uid,String addressid){
		boolean boo=false;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="update uaddress set addressdr=2 where uid=? and uadid=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, addressid);
			int i=ps.executeUpdate();
			if(i>=1){
				boo=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}
	
}

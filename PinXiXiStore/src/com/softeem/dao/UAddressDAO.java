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
	 * 用户添加收货地址的方法
	 * @param address 需要添加的收货地址
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
	 * 根据用户id查询所有的收货地址
	 * @param uid 需要查询地址的用户id
	 * @return 返回查询出来的地址集合 如果由又则集合不为空，没有数据则集合为空
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
	 * 将指定用户下的所有地址都设置为备选状态
	 * @param uid 指定的用户
	 * @return 修改成功返回true 修改失败false
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
	 * 将用户指定用户id的指定地址修改为默认地址
	 * @param uid 需要修改默认地址的用户id
	 * @param uadid 需要被设置成默认地址的地址id
	 * @return 设置成功返回true 设置失败返回false
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
	 * 查询指定id的用户的默认地址
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
	 * 	用户删除地址的方法
	 * @param uid 用户id
	 * @param addressid 需要删除的地址id
	 * @return 删除成功返回true 删除是该返回false
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

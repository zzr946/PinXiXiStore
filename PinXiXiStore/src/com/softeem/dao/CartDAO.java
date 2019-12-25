package com.softeem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softeem.model.Cart;
import com.softeem.model.CartItem;
import com.softeem.tools.DBUtil;
/**
 * �빺�ﳵ��ص�sql���
 * @author ��־Ȼ
 *
 */
public class CartDAO {
	
	/**
	 * ��ѯָ���û����ﳵ�е�ָ����Ʒ
	 * @param uid �û�id
	 * @param gid ��Ʒid
	 * @return ����Ѿ���ӹ����򷵻�Cart����  û��ӹ�����null
	 */
	public Cart selectCart(String userid,String gid){
		Cart cart=null;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select id,uid,gidlist,goodstotallist,goodsprice,reserved1 from cart where uid=? and gidlist=? and reserved1=0";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, userid);
			ps.setString(2, gid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				String id=rs.getString("id");
				String uid=rs.getString("uid");
				String gidlist=rs.getString("gidlist");
				int goodstotallist=rs.getInt("goodstotallist");
				double goodsprice=rs.getDouble("goodsprice");
				int reserved1=rs.getInt("reserved1"); 
				cart=new Cart();
				cart.setId(id);
				cart.setUid(uid);
				cart.setGidlist(gidlist);
				cart.setGoodstotallist(goodstotallist);
				cart.setGoodsprice(goodsprice);
				cart.setReserved1(reserved1);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cart;
	}
	
	
	/**
	 * ��ָ���û����ﳵ�е�ĳ����Ʒ�����ı�
	 * @param uid �û�id
	 * @param gidlist ��Ʒid
	 * @param total Ҫ�޸ĳɵ�����
	 * @return ˾�ĳɹ�����true  �޸�ʧ�ܷ���false
	 */
	public boolean updateTototal(String uid,String gidlist,int total){
		boolean boo=false;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="update cart set goodstotallist=? where uid=? and gidlist=? and reserved1=0";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, total);
			ps.setString(2, uid);
			ps.setString(3, gidlist);
			int i=ps.executeUpdate();
			if(i>=1){
				boo=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}
	
	/**
	 * �û�����Ʒ��ӵ����ﳵ��
	 * @param cart ���ﳵ�е�һ����¼
	 * @return ��ӳɹ�����true  ���ʧ�ܷ���false
	 */
	public boolean inserttoCart(Cart cart){
		boolean boo=false;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="insert into cart(id,uid,gidlist,goodstotallist,goodsprice,reserved1) values(?,?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, cart.getId());
			ps.setString(2, cart.getUid());
			ps.setString(3, cart.getGidlist());
			ps.setInt(4, cart.getGoodstotallist());
			ps.setDouble(5, cart.getGoodsprice());
			ps.setInt(6, cart.getReserved1());
			int i=ps.executeUpdate();
			if(i>=1){
				boo=true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}
	
	/**
	 * ��ѯ���û����ﳵ������е�����¼
	 * @param uid
	 * @return
	 */
	public List<CartItem> selectcartAll(String uid){
		List<CartItem> list=new ArrayList<CartItem>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select g.gid,g.goodsimage,g.goodsname,g.reserved1,"
					+ "c.goodsprice,c.goodstotallist from goods g,"
					+ "(SELECT uid,gidlist,goodstotallist,goodsprice FROM cart WHERE uid=? and reserved1=0) c "
					+ "WHERE g.gid=c.gidlist";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				String gid=rs.getString("gid");
				String goodsimage=rs.getString("goodsimage");
				String goodsname=rs.getString("goodsname");
				String reserved1=rs.getString("reserved1");
				double goodsprice=rs.getDouble("goodsprice");
				int goodstotallist=rs.getInt("goodstotallist");
				list.add(new CartItem(gid, goodsimage, goodsname, reserved1, goodsprice, goodstotallist,goodsprice*goodstotallist));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
}

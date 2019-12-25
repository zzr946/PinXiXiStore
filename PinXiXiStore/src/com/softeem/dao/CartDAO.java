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
 * 与购物车相关的sql语句
 * @author 赵志然
 *
 */
public class CartDAO {
	
	/**
	 * 查询指定用户购物车中的指定商品
	 * @param uid 用户id
	 * @param gid 商品id
	 * @return 如果已经添加过了则返回Cart对象  没添加过返回null
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
	 * 将指定用户购物车中的某件商品数量改变
	 * @param uid 用户id
	 * @param gidlist 商品id
	 * @param total 要修改成的数量
	 * @return 司改成功返回true  修改失败返回false
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
	 * 用户将商品添加到购物车中
	 * @param cart 购物车中的一条记录
	 * @return 添加成功返回true  添加失败返回false
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
	 * 查询该用户购物车里的所有单条记录
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

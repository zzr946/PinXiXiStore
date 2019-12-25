package com.softeem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softeem.model.Comment;
import com.softeem.model.Goods;
import com.softeem.model.Order;
import com.softeem.model.User;
import com.softeem.tools.DBUtil;

public class CommelistDAO {
	
	/**
	 * 根据商品id查询商品的所有信息
	 * @param gid 需要查询的商品id
	 * @return 返回查询到的商品对象 没查询到返回null
	 */
	public Goods selectgoodsByid(String id){
		Goods goods=null;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select gid,goodsnumber,goodstotal,mid,goodsname,"
					+ "goodsprice,discountprice,goodssell,goodsdr,goodsinfo,"
					+ "goodsdiscount,goodsimage,sid,reserved1,reserved2,reserved3,"
					+ "reserved4,reserved5 from goods where gid=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				String gid=rs.getString("gid");
				String goodsnumber=rs.getString("goodsnumber");
				int goodstotal=rs.getInt("goodstotal");
				String mid=rs.getString("mid");
				String goodsname=rs.getString("goodsname");
				double goodsprice=rs.getDouble("goodsprice");
				double discountprice=rs.getDouble("discountprice");
				int goodssell=rs.getInt("goodssell");
				int goodsdr=rs.getInt("goodsdr");
				String goodsinfo=rs.getString("goodsinfo");
				int goodsdiscount=rs.getInt("goodsdiscount");
				String goodsimage=rs.getString("goodsimage");
				String sid=rs.getString("sid");
				String reserved1=rs.getString("reserved1");
				String reserved2=rs.getString("reserved2");
				String reserved3=rs.getString("reserved3");
				String reserved4=rs.getString("reserved4");
				String reserved5=rs.getString("reserved5");
				goods=new Goods(gid, goodsnumber, goodstotal, mid, goodsname, goodsprice, discountprice, goodssell, goodsdr, goodsinfo, goodsdiscount, goodsimage, sid, reserved1, reserved2, reserved3, reserved4, reserved5);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goods;
	}
	
	
	/**
	 * 根据用户id查询用户所有信息
	 * @param userid 需要查询的用户id
	 * @return 返回查询到的user对象  若没查询到则返回null
	 */
	public User selectUserByid(String userid){
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
	 * 将用户提交的评论存入数据库
	 * @param comm 评论对象
	 * @return 评论成功返回true  失败返回false
	 */
	public boolean insertComment(Comment comm){
		boolean boo=false;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="insert into comment(cid,uid,nickname,gid,content,subtime,commentdr) values(?,?,?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, comm.getCid());
			ps.setString(2, comm.getUid());
			ps.setString(3, comm.getNickname());
			ps.setString(4,comm.getGid());
			ps.setString(5,comm.getContent());
			ps.setString(6, comm.getSubtime());
			ps.setInt(7, comm.getCommentdr());
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
	 * 将订单状态改为已评价
	 * @param order 需要修改的订单
	 * @return 修改成功返回true  失败返回fasle
	 */
	public boolean updateCommentdr(Order order){
		boolean boo=false;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="update order1 set paydr=2 where gidlist=? and name=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, order.getGidlist());
			ps.setString(2, order.getName());
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
	 * 根据用户id查看待评价的订单
	 * @param user 需要查询的用户
	 * @return 返回查询的待评价的订单
	 */
	public List<Order> selectawaitevaluate(User user){
		List<Order> list=new ArrayList<Order>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select * from order1 where name=? and orderdr=2 and paydr!=2";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, user.getName());
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				String id=rs.getString("id");
				String ordernumber=rs.getString("ordernumber");
				String gidlist=rs.getString("gidlist");
				int goodstotallist=rs.getInt("goodstotallist");
				String name=rs.getString("name");
				String uadname=rs.getString("uadname");
				double account=rs.getDouble("account");
				String uadid=rs.getString("uadid");
				String mid=rs.getString("mid");
				int paydr=rs.getInt("paydr");
				int orderdr=rs.getInt("orderdr");
				String reserved1=rs.getString("reserved1");
				String reserved2=rs.getString("reserved2");
				String reserved3=rs.getString("reserved3");
				String reserved4=rs.getString("reserved4");
				String reserved5=rs.getString("reserved5");
				list.add(new Order(id, ordernumber, gidlist, goodstotallist, name, uadname, account, uadid, mid, paydr, orderdr, reserved1, reserved2, reserved3, reserved4, reserved5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 根据商品id查看该商品的所有评论
	 * @param gid 需要查看的商品
	 * @return 返回评论集合
	 */
	public List<Comment> selectCommentAll(String id){
		List<Comment> list=new ArrayList<>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select * from comment where gid=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				String cid=rs.getString("cid");
				String uid=rs.getString("uid");
				String nickname=rs.getString("nickname");
				String gid=rs.getString("gid");
				String content=rs.getString("content");
				String subtime=rs.getString("subtime");
				int commentdr=rs.getInt("commentdr");
				list.add(new Comment(cid, uid, nickname, gid, content, subtime, commentdr, null, null, null, null, null));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	
}

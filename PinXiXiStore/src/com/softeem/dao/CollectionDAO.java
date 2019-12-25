package com.softeem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softeem.model.Collection;
import com.softeem.model.Goods;
import com.softeem.tools.DBUtil;

/**
 * 用户收藏商品相关的sql语句
 * @author 赵志然
 *
 */
public class CollectionDAO {
	/**
	 * 根据用户id和商品id查询该商品是否被用户收藏
	 * @param uid 用户id
	 * @param gid 商品id
	 * @return 已经被用户收藏则返回true,还没被收藏则返回fasle
	 */
	public boolean selectBygid(String uid,String gid){
		boolean boo=false;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select id from collection where uid=? and gid=? and collectdr=0";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, gid);
			ResultSet rs=ps.executeQuery();
			if(rs.next()){
				//查询到了数据
				boo=true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}
	
	
	
	/**
	 * 用户收藏商品的方法
	 * @param coll 收藏表的一条记录
	 * @return 插入成功返回true，失败返回false
	 */
	public boolean insertgoods(Collection coll){
		boolean boo=false;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="insert into collection(id,uid,gid,collecttime,collectdr) values(?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, coll.getId());
			ps.setString(2, coll.getUid());
			ps.setString(3, coll.getGid());
			ps.setString(4, coll.getCollecttime());
			ps.setInt(5, coll.getCollectdr());
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
	 * 查询该用户的收藏商品
	 * @param uid 用户id
	 * @return 收藏的商品集合
	 */
	public List<Collection> selectCollGoods(String userid){
		List<Collection> list=new ArrayList<Collection>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select * from collection where collectdr=0 and uid=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, userid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				String id=rs.getString("id");
				String uid=rs.getString("uid");
				String gid=rs.getString("gid");
				String collecttime=rs.getString("collecttime");
				int collectdr=rs.getInt("collectdr");
				list.add(new Collection(userid, uid, gid, collecttime, collectdr, null, null, null, null, null));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
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
	
	
	
}

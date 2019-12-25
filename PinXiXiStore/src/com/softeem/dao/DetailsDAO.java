package com.softeem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.softeem.model.Goods;
import com.softeem.tools.DBUtil;

/**
 * 与商品详情有关的sql语句
 * @author 赵志然
 *
 */
public class DetailsDAO {
	
	/**
	 * 根据商品id查询商品信息
	 * @param id 需要查询的商品id
	 * @return 返回查询到的商品对象   没有则为null
	 */
	public Goods selectOneByid(String id){
		Goods goods=null;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select gid,goodsnumber,goodstotal,mid,goodsname,"
					+ "goodsprice,discountprice,goodssell,goodsdr,goodsinfo,"
					+ "goodsdiscount,goodsimage,sid,reserved1,reserved2,reserved3,"
					+ "reserved4,reserved5 from goods where gid=? and goodsdr=0";
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
				goods=new Goods(gid, goodsnumber, goodstotal, mid, goodsname, goodsprice, discountprice, goodssell, goodsdr, goodsinfo, goodsdiscount, goodsimage, sid, reserved1, null, null, null, null);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return goods;
	}
	
	
	
	
}

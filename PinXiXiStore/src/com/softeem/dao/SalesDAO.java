package com.softeem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softeem.model.Goods;
import com.softeem.model.Sales;
import com.softeem.tools.DBUtil;

public class SalesDAO {
	
	/**
	 * 查询商品促销表中的所有商品
	 * @return
	 */
	public List<Goods> selectsalesAll(){
		List<Goods> list=new ArrayList<Goods>();
		try {
			Connection conn=DBUtil.getConnection();
			//SELECT goods.* from goods as g,(select gid FROM sales where salesdr=0) as sal WHERE goods.gid=sal.gid
			String sql="select g.gid,goodsnumber,goodstotal,mid,g.goodsname,g.goodsprice,"
					+ "g.discountprice,goodssell,goodsdr,goodsinfo,goodsdiscount,goodsimage,"
					+ "sid,reserved1,reserved2,reserved3,reserved4,reserved5 "
					+ "from goods as g,(select gid from sales where salesdr=0) as sal "
					+ "where g.gid=sal.gid";
			PreparedStatement ps=conn.prepareStatement(sql);
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
				list.add(new Goods(gid, goodsnumber, goodstotal, mid, goodsname, goodsprice, discountprice, goodssell, goodsdr, goodsinfo, goodsdiscount, goodsimage, sid, null, null, null, null, null));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * 
	 * @param goods 存储的商品数据
	 * @return true为已在促销表中，false为不在促销表中
	 */
	public boolean check(Goods goods){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select id from sales where gid=?");
			ps.setString(1, goods.getGid());
			rs = ps.executeQuery();
			while(rs.next()){
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
	 * @param sales 促销对象
	 * @return true成功false失败
	 */
	public boolean insertSales(Sales sales){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("insert into sales(id,gid,goodsname,goodsprice,discountprice,salesdr) values(?,?,?,?,?,?)");
			ps.setString(1, sales.getId());
			ps.setString(2, sales.getGid());
			ps.setString(3, sales.getGoodsname());
			ps.setDouble(4, sales.getGoodsprice());
			ps.setDouble(5, sales.getDiscountprice());
			ps.setInt(6, sales.getSalesdr());
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
	
	public boolean deleteSales(String gid){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("delete from sales where gid = ?");
			ps.setString(1, gid);
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

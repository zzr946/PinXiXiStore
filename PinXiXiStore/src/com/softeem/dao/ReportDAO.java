package com.softeem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.softeem.model.Goods;
import com.softeem.model.Report;
import com.softeem.tools.DBUtil;

public class ReportDAO {
	/**
	 * 用户举报商品的方法
	 * @param rep
	 * @return
	 */
	public boolean insertReport(Report rep){
		boolean boo=false;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="insert into report(id,uid,gid,goodsname,mid,cause,content,reporttime) values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, rep.getId());
			ps.setString(2, rep.getUid());
			ps.setString(3, rep.getGid());
			ps.setString(4, rep.getGoodsname());
			ps.setString(5, rep.getMid());
			ps.setString(6, rep.getCause());
			ps.setString(7, rep.getContent());
			ps.setString(8, rep.getReporttime());
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
	 * 根据商品id查询商家id(查询该商品属于那个商家)
	 * @param gid 商品id
	 * @return 返回商品对象
	 */
	public Goods selectBygid(String id){
		Goods goods=null;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select gid,goodsnumber,goodstotal,mid,goodsname,goodsprice,discountprice,goodssell,goodsdr,goodsinfo,goodsdiscount,goodsimage,sid,reserved1,reserved2,reserved3,reserved4,reserved5 from goods where gid=?";
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

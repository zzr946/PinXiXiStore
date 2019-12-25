package com.softeem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softeem.model.Goods;
import com.softeem.model.ResultModel;
import com.softeem.tools.DBUtil;
import com.sun.prism.Presentable;
/**
 * 商品查询的sql语句
 * @author 赵志然
 *
 */
public class GoodsDAO {
	
	
	/**
	 * 查询所有商品的方法(第一次查询，显示第一页的数据)
	 * @return 返回所有商品对象的集合
	 */
	public List<Goods> selectGoodsAll(){
		List<Goods> list=new ArrayList<Goods>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select gid,goodsnumber,goodstotal,mid,goodsname,goodsprice,discountprice,goodssell,goodsdr,goodsinfo,goodsdiscount,goodsimage,sid,reserved1,reserved2,reserved3,reserved4,reserved5 from goods where goodsdr=0 limit 0,12";
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
	 * 查询所有商品的方法(查询指定页数的商品)
	 * @param page 需要查询第几页的商品
	 * @return 返回指定页的所有商品对象的集合
	 */
	public List<Goods> selectGoodsAllpage(int page){
		List<Goods> list=new ArrayList<Goods>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select gid,goodsnumber,goodstotal,mid,goodsname,"
					+ "goodsprice,discountprice,goodssell,goodsdr,goodsinfo,"
					+ "goodsdiscount,goodsimage,sid,reserved1,reserved2,reserved3,"
					+ "reserved4,reserved5 from goods where goodsdr=0 limit ?,12";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, ((page-1)*12));
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
	 * 根据用户输入的内容查询所有相关商品(第一次查询，显示第一页的数据)
	 * @param name 用户输入的内容
	 * @return 返回所有的商品对象集合
	 */
	public List<Goods> selectGoodsByname(String title){
		List<Goods> list=new ArrayList<Goods>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select gid,goodsnumber,goodstotal,mid,goodsname,goodsprice,"
					+ "discountprice,goodssell,goodsdr,goodsinfo,goodsdiscount,goodsimage,"
					+ "sid,reserved1,reserved2,reserved3,reserved4,reserved5 from "
					+ "goods where CONCAT(goodsname,goodsinfo) LIKE ? and goodsdr=0 limit 0,12";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, "%"+title+"%");
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
	 * 根据用户输入的内容查询所有相关商品(查询指定页数的相关商品)
	 * @param title 需要查询的相关商品(关键字)
	 * @param page 需要查询第几页的商品
	 * @return 返回指定页数的所有商品对象的集合
	 */
	public List<Goods> selectGoodsBynamepage(String title, int page){
		List<Goods> list=new ArrayList<Goods>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select gid,goodsnumber,goodstotal,mid,goodsname,goodsprice,"
					+ "discountprice,goodssell,goodsdr,goodsinfo,goodsdiscount,goodsimage,"
					+ "sid,reserved1,reserved2,reserved3,reserved4,reserved5 from goods "
					+ "where CONCAT(goodsname,goodsinfo) LIKE ? and goodsdr=0 limit ?,12";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, "%"+title+"%");
			ps.setInt(2, (page-1)*12);
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
	
	//...排序...
	
	/**
	 * 按销量查询所有商品(第一页数据)
	 * @return 按销量排序完成商品对象的集合
	 */
	public List<Goods> selectsalesstore(){
		List<Goods> list=new ArrayList<Goods>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select gid,goodsnumber,goodstotal,mid,goodsname,goodsprice,discountprice,goodssell,goodsdr,goodsinfo,goodsdiscount,goodsimage,sid,reserved1,reserved2,reserved3,reserved4,reserved5 from goods where goodsdr=0 order by goodssell desc limit 0,12";
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
	 * 按销量查询指定页数的所有商品并按照销量排序
	 * @param page 需要查询的页数
	 * @return 返回指定页数的所有商品对象排序完的集合
	 */
	public List<Goods> selectsalesstorepage(int page){
		List<Goods> list=new ArrayList<Goods>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select gid,goodsnumber,goodstotal,mid,goodsname,goodsprice,discountprice,goodssell,goodsdr,goodsinfo,goodsdiscount,goodsimage,sid,reserved1,reserved2,reserved3,reserved4,reserved5 from goods where goodsdr=0 order by goodssell desc limit ?,12";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, (page-1)*12);
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
	 * 按单价排序查询第一页的商品信息(第一页)
	 * @return 返回查询的商品结果对象集合
	 */
	public List<Goods> selectpricesort(){
		List<Goods> list=new ArrayList<Goods>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select gid,goodsnumber,goodstotal,mid,goodsname,goodsprice,discountprice,goodssell,goodsdr,goodsinfo,goodsdiscount,goodsimage,sid,reserved1,reserved2,reserved3,reserved4,reserved5 from goods where goodsdr=0 order by goodsprice limit 0,12";
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
	 * 查询指定页数的商品并按单价排序
	 * @param page 需要查询的页数
	 * @return 返回指定页数的排完序的商品对象集合
	 */
	public List<Goods> selectpricesortpage(int page){
		List<Goods> list=new ArrayList<Goods>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select gid,goodsnumber,goodstotal,mid,goodsname,goodsprice,discountprice,goodssell,goodsdr,goodsinfo,goodsdiscount,goodsimage,sid,reserved1,reserved2,reserved3,reserved4,reserved5 from goods where goodsdr=0 order by goodsprice limit ?,12";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setInt(1, (page-1)*12);
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
	 * 查询用户搜索的商品，并按销量排序(第一页)
	 * @param title 需要查询的商品
	 * @return 返回排序完成的商品对象集合
	 */
	public List<Goods> selectSalesBynameSort(String title){
		List<Goods> list=new ArrayList<Goods>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select gid,goodsnumber,goodstotal,mid,goodsname,goodsprice,discountprice,goodssell,goodsdr,goodsinfo,goodsdiscount,goodsimage,sid,reserved1,reserved2,reserved3,reserved4,reserved5 from goods where CONCAT(goodsname,goodsinfo) LIKE ? and goodsdr=0 order by goodssell desc limit 0,12";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, "%"+title+"%");
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
	 * 查询用户搜索的商品，并按价格排序(第一页)
	 * @param title 需要查询的商品
	 * @return 返回排序完成的商品对象集合
	 */
	public List<Goods> selectPriceBynameSort(String title){
		List<Goods> list=new ArrayList<Goods>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select gid,goodsnumber,goodstotal,mid,goodsname,goodsprice,discountprice,goodssell,goodsdr,goodsinfo,goodsdiscount,goodsimage,sid,reserved1,reserved2,reserved3,reserved4,reserved5 from goods where CONCAT(goodsname,goodsinfo) LIKE ? and goodsdr=0 order by goodsprice limit 0,12";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, "%"+title+"%");
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
	 * 根据用户输入的内容查询相关商品,并按销量排序(指定页数)
	 * @param title 需要查询的内容
	 * @param page 需要查询第几页的商品
	 * @return 返回该页的商品对象集合
	 */
	public List<Goods> selectSalesBynameSortpage(String title,int page){
		List<Goods> list=new ArrayList<Goods>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select gid,goodsnumber,goodstotal,mid,goodsname,goodsprice,"
					+ "discountprice,goodssell,goodsdr,goodsinfo,goodsdiscount,goodsimage,"
					+ "sid,reserved1,reserved2,reserved3,reserved4,reserved5 from goods "
					+ "where CONCAT(goodsname,goodsinfo) LIKE ? and goodsdr=0 order by goodssell desc limit ?,12";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, "%"+title+"%");
			ps.setInt(2, (page-1)*12);
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
	 * 根据用户输入的内容查询相关商品,并按价格排序(指定页数)
	 * @param title 需要查询的内容
	 * @param page 需要查询第几页的商品
	 * @return 返回该页的商品对象集合
	 */
	public List<Goods> selectPriceBynameSortpage(String title,int page){
		List<Goods> list=new ArrayList<Goods>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select gid,goodsnumber,goodstotal,mid,goodsname,goodsprice,"
					+ "discountprice,goodssell,goodsdr,goodsinfo,goodsdiscount,goodsimage,"
					+ "sid,reserved1,reserved2,reserved3,reserved4,reserved5 from goods "
					+ "where CONCAT(goodsname,goodsinfo) LIKE ? and goodsdr=0 order by goodsprice limit ?,12";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, "%"+title+"%");
			ps.setInt(2, (page-1)*12);
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
	 * @param goodsname 商品名
	 * @return true为存在该商品名 false为不存在
	 * @param mid 商家id
	 */
	public boolean selectByGname(String goodsname,String mid){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select gid from goods where goodsname=?");
			ps.setString(1, goodsname);
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
	 * @param goods 商品对象
	 * @return true为添加成功false为添加失败
	 */
	public boolean insert(Goods goods){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("insert into goods values"
					+ "(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			
//			(gid,goodsnumber,goodstotal,mid,goodsname,goodsprice,discountprice,goodssell,goodsdr,goodsinfo,goodsdiscount,goodsimage"
//					+ "sid,reserved1,reserved2,reserved3,reserved4,reserved5)
			ps.setString(1, goods.getGid());
			ps.setString(2, goods.getGoodsnumber());
			ps.setInt(3, goods.getGoodstotal());
			ps.setString(4, goods.getMid());
			ps.setString(5, goods.getGoodsname());
			ps.setDouble(6, goods.getGoodsprice());
			ps.setDouble(7, goods.getDiscountprice());
			ps.setInt(8, goods.getGoodssell());
			ps.setInt(9, goods.getGoodsdr());
			ps.setString(10,goods.getGoodsinfo());
			ps.setInt(11,goods.getGoodsdiscount());
			ps.setString(12,goods.getGoodsimage());
			ps.setString(13, goods.getSid());
			ps.setString(14, goods.getReserved1());//计量单位
			ps.setString(15, goods.getReserved2());
			ps.setString(16, goods.getReserved3());
			ps.setString(17, goods.getReserved4());
			ps.setString(18, goods.getReserved5());
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
	
	/**
	 * 此为通过页数查询出来的商品列表,每页五条记录
	 * @param mid 商家id
	 * @param page 查询的页数
	 * @return 返回一个商品集合
	 */
	public List<Goods> selectByPage(String mid1,int page){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Goods> list = new ArrayList<Goods>();
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select * from goods where mid=? limit ?,5");
			ps.setString(1, mid1);
			ps.setInt(2, (page-1)*5);
			rs = ps.executeQuery();
			while(rs.next()){
				String gid = rs.getString("gid");
				String goodsnumber = rs.getString("goodsnumber");
				int goodstotal = rs.getInt("goodstotal");
				String mid = mid1;
				String goodsname = rs.getString("goodsname");
				double goodsprice = rs.getDouble("goodsprice");
				double discountprice = rs.getDouble("discountprice");
				int goodssell = rs.getInt("goodssell");
				int goodsdr = rs.getInt("goodsdr");
				String goodsinfo = rs.getString("goodsinfo");
				int goodsdiscount = rs.getInt("goodsdiscount");
				String goodsimage = rs.getString("goodsimage");
				String sid = rs.getString("sid");
				String reserved1 = rs.getString("reserved1");
				String reserved2 = rs.getString("reserved2");
				String reserved3 = rs.getString("reserved3");
				String reserved4 = rs.getString("reserved4");
				String reserved5 = rs.getString("reserved5");
				Goods goods = new Goods(gid, goodsnumber, goodstotal, mid, goodsname, goodsprice, discountprice, goodssell,
						goodsdr, goodsinfo, goodsdiscount, goodsimage, sid, reserved1, reserved2, reserved3, reserved4, reserved5);
				list.add(goods);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}
	
	//促销
	public List<Goods> selectBySpecialPage(String mid1,int page){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Goods> list = new ArrayList<Goods>();
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select * from goods where mid=? limit ?,5");
			ps.setString(1, mid1);
			ps.setInt(2, (page-1)*5);
			rs = ps.executeQuery();
			while(rs.next()){
				String gid = rs.getString("gid");
				String goodsnumber = rs.getString("goodsnumber");
				int goodstotal = rs.getInt("goodstotal");
				String mid = mid1;
				String goodsname = rs.getString("goodsname");
				double goodsprice = rs.getDouble("goodsprice");
				double discountprice = rs.getDouble("discountprice");
				int goodssell = rs.getInt("goodssell");
				int goodsdr = rs.getInt("goodsdr");
				String goodsinfo = rs.getString("goodsinfo");
				int goodsdiscount = rs.getInt("goodsdiscount");
				String goodsimage = rs.getString("goodsimage");
				String sid = rs.getString("sid");
				String reserved1 = rs.getString("reserved1");
				String reserved2 = rs.getString("reserved2");
				String reserved3 = rs.getString("reserved3");
				String reserved4 = rs.getString("reserved4");
				String reserved5 = rs.getString("reserved5");
				Goods goods = new Goods(gid, goodsnumber, goodstotal, mid, goodsname, goodsprice, discountprice, goodssell,
						goodsdr, goodsinfo, goodsdiscount, goodsimage, sid, reserved1, reserved2, reserved3, reserved4, reserved5);
				list.add(goods);	
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}
	
	
	
	
	/**
	 * 
	 * @param goods goods中存放的是要修改的数据
	 * @return true为修改成功false为修改失败
	 */
	public boolean updateGoods(Goods goods){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("update goods set goodsname=?,goodsprice=?,goodstotal=?,reserved1=?,goodsinfo=? where goodsnumber=?");
			ps.setString(1, goods.getGoodsname());
			ps.setDouble(2, goods.getGoodsprice());
			ps.setInt(3,goods.getGoodstotal());
			ps.setString(4, goods.getReserved1());
			ps.setString(5, goods.getGoodsinfo());
			ps.setString(6, goods.getGoodsnumber());
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
	
	/**
	 * 
	 * @param gid 商品id
	 * @return true为下架成功，false为下架失败
	 */
	public boolean updateGoodsDrX(String gid){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("update goods set goodsdr=1 where gid=?");
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
	
	/**
	 * 
	 * @param gid 商品id
	 * @return true为上架成功，false为上架失败
	 */
	public boolean updateGoodsDrS(String gid){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("update goods set goodsdr=0 where gid=?");
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
	
	/**
	 * 
	 * @param gid 商品id
	 * @param discountprice 促销价格
	 * @return true为修改成功 false为修改失败
	 */
	public boolean updateDiscountPrice(String gid,double discountprice){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("update goods set discountprice=?,goodsdiscount=0 where gid=?");
			ps.setDouble(1, discountprice);
			ps.setString(2, gid);
			int i = ps.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	public boolean updateDiscountPriceSecond(String gid,double goodsprice){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("update goods set discountprice=?,goodsdiscount=1 where gid=?");
			ps.setDouble(1, goodsprice);
			ps.setString(2, gid);
			int i = ps.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	
}

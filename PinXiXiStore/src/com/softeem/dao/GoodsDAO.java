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
 * ��Ʒ��ѯ��sql���
 * @author ��־Ȼ
 *
 */
public class GoodsDAO {
	
	
	/**
	 * ��ѯ������Ʒ�ķ���(��һ�β�ѯ����ʾ��һҳ������)
	 * @return ����������Ʒ����ļ���
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
	 * ��ѯ������Ʒ�ķ���(��ѯָ��ҳ������Ʒ)
	 * @param page ��Ҫ��ѯ�ڼ�ҳ����Ʒ
	 * @return ����ָ��ҳ��������Ʒ����ļ���
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
	 * �����û���������ݲ�ѯ���������Ʒ(��һ�β�ѯ����ʾ��һҳ������)
	 * @param name �û����������
	 * @return �������е���Ʒ���󼯺�
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
	 * �����û���������ݲ�ѯ���������Ʒ(��ѯָ��ҳ���������Ʒ)
	 * @param title ��Ҫ��ѯ�������Ʒ(�ؼ���)
	 * @param page ��Ҫ��ѯ�ڼ�ҳ����Ʒ
	 * @return ����ָ��ҳ����������Ʒ����ļ���
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
	
	//...����...
	
	/**
	 * ��������ѯ������Ʒ(��һҳ����)
	 * @return ���������������Ʒ����ļ���
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
	 * ��������ѯָ��ҳ����������Ʒ��������������
	 * @param page ��Ҫ��ѯ��ҳ��
	 * @return ����ָ��ҳ����������Ʒ����������ļ���
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
	 * �����������ѯ��һҳ����Ʒ��Ϣ(��һҳ)
	 * @return ���ز�ѯ����Ʒ������󼯺�
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
	 * ��ѯָ��ҳ������Ʒ������������
	 * @param page ��Ҫ��ѯ��ҳ��
	 * @return ����ָ��ҳ�������������Ʒ���󼯺�
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
	 * ��ѯ�û���������Ʒ��������������(��һҳ)
	 * @param title ��Ҫ��ѯ����Ʒ
	 * @return ����������ɵ���Ʒ���󼯺�
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
	 * ��ѯ�û���������Ʒ�������۸�����(��һҳ)
	 * @param title ��Ҫ��ѯ����Ʒ
	 * @return ����������ɵ���Ʒ���󼯺�
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
	 * �����û���������ݲ�ѯ�����Ʒ,������������(ָ��ҳ��)
	 * @param title ��Ҫ��ѯ������
	 * @param page ��Ҫ��ѯ�ڼ�ҳ����Ʒ
	 * @return ���ظ�ҳ����Ʒ���󼯺�
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
	 * �����û���������ݲ�ѯ�����Ʒ,�����۸�����(ָ��ҳ��)
	 * @param title ��Ҫ��ѯ������
	 * @param page ��Ҫ��ѯ�ڼ�ҳ����Ʒ
	 * @return ���ظ�ҳ����Ʒ���󼯺�
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
	 * @param goodsname ��Ʒ��
	 * @return trueΪ���ڸ���Ʒ�� falseΪ������
	 * @param mid �̼�id
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
	 * @param goods ��Ʒ����
	 * @return trueΪ��ӳɹ�falseΪ���ʧ��
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
			ps.setString(14, goods.getReserved1());//������λ
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
	 * ��Ϊͨ��ҳ����ѯ��������Ʒ�б�,ÿҳ������¼
	 * @param mid �̼�id
	 * @param page ��ѯ��ҳ��
	 * @return ����һ����Ʒ����
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
	
	//����
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
	 * @param goods goods�д�ŵ���Ҫ�޸ĵ�����
	 * @return trueΪ�޸ĳɹ�falseΪ�޸�ʧ��
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
	 * @param gid ��Ʒid
	 * @return trueΪ�¼ܳɹ���falseΪ�¼�ʧ��
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
	 * @param gid ��Ʒid
	 * @return trueΪ�ϼܳɹ���falseΪ�ϼ�ʧ��
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
	 * @param gid ��Ʒid
	 * @param discountprice �����۸�
	 * @return trueΪ�޸ĳɹ� falseΪ�޸�ʧ��
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

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
 * �û��ղ���Ʒ��ص�sql���
 * @author ��־Ȼ
 *
 */
public class CollectionDAO {
	/**
	 * �����û�id����Ʒid��ѯ����Ʒ�Ƿ��û��ղ�
	 * @param uid �û�id
	 * @param gid ��Ʒid
	 * @return �Ѿ����û��ղ��򷵻�true,��û���ղ��򷵻�fasle
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
				//��ѯ��������
				boo=true;
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return boo;
	}
	
	
	
	/**
	 * �û��ղ���Ʒ�ķ���
	 * @param coll �ղر��һ����¼
	 * @return ����ɹ�����true��ʧ�ܷ���false
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
	 * ��ѯ���û����ղ���Ʒ
	 * @param uid �û�id
	 * @return �ղص���Ʒ����
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
	 * ������Ʒid��ѯ��Ʒ��������Ϣ
	 * @param gid ��Ҫ��ѯ����Ʒid
	 * @return ���ز�ѯ������Ʒ���� û��ѯ������null
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

package com.softeem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softeem.model.Goods;
import com.softeem.model.Order;
import com.softeem.model.PayItem;
import com.softeem.model.Return;
import com.softeem.model.Uaddress;
import com.softeem.model.User;
import com.softeem.tools.DBUtil;

/**
 * ������ص�sql���
 * @author ��־Ȼ
 *
 */
public class OrderDAO {
	
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
	
	
	/**
	 * �����û�id��ѯ�û�������Ϣ
	 * @param userid ��Ҫ��ѯ���û�id
	 * @return ���ز�ѯ����user����  ��û��ѯ���򷵻�null
	 */
	public User selectUserByid(String userid){
		User user=null;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select uid,nickname,password,phone,photo,name,sex,"
					+ "userdr,reserved1,reserved2,reserved3,reserved4,reserved5 "
					+ "from user where uid=?";
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
	 * �ӹ��ﳵ��֧�����棬ɾ�����ﳵ�е�����,(����Ʒ��ӵ�������)
	 * @param uid ��ǰ�û���id
	 * @param gid �������Ʒid
	 * @return �ɹ�����true  ʧ�ܷ���false
	 */
	public boolean UpdateCartdr(String uid,String gid){
		boolean boo=false;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="update cart set reserved1=1 where uid=? and gidlist=? and reserved1=0";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, uid);
			ps.setString(2, gid);
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
	 * ��������Ϣ��ӵ���������
	 * @param order ��Ҫ������������еĵ�����¼
	 * @return ����ɹ�����true  ����ʧ�ܷ���false
	 */
	public boolean insertOrder(Order order){
		boolean boo=false;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="insert into order1(id,ordernumber,gidlist,goodstotallist,name,mid,paydr) values(?,?,?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, order.getId());
			ps.setString(2, order.getOrdernumber());
			ps.setString(3, order.getGidlist());
			ps.setInt(4, order.getGoodstotallist());
			ps.setString(5, order.getName());
			ps.setString(6, order.getMid());
			ps.setInt(7, order.getPaydr());
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
	 * ���ύ�Ķ�������Ʒ��Ŀ��ѯ����
	 * @return
	 */
	public List<PayItem> selectPayitemAll(String ordernumber){
		List<PayItem> list=new ArrayList<PayItem>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select g.gid,g.goodsimage,g.goodsname,g.reserved1,g.discountprice,"
					+ "o.goodstotallist from goods g,(select goodstotallist,ordernumber,"
					+ "gidlist,paydr,orderdr from order1) o where o.paydr=1 and g.gid=o.gidlist and o.ordernumber=?";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, ordernumber);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				String gid=rs.getString("gid");
				String goodsimage=rs.getString("goodsimage");
				String goodsname=rs.getString("goodsname");
				String reserved1=rs.getString("reserved1");
				double discountprice=rs.getDouble("discountprice");
				int goodstotallist=rs.getInt("goodstotallist");
				list.add(new PayItem(gid, goodsimage, goodsname, reserved1, discountprice, goodstotallist, discountprice*goodstotallist));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * ���ݵ�ַid��ѯ�ĵ�ַ����ϸ��Ϣ
	 * @param uaddressid ��Ҫ��ѯ��ַ��id
	 * @return ���ز�ѯ���ĵ�ַ����
	 */
	public Uaddress selectaddrByid(String uaddressid){
		Uaddress address=null;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select uadid,uid,province,city,area,detailaddress,addressdr,"
					+ "mobile,uadname,reserved1,reserved2,reserved3,reserved4,reserved5 "
					+ "from uaddress where uadid=? and addressdr!=2";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, uaddressid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				String uadid=rs.getString("uadid");
				String uid=rs.getString("uid");
				String province=rs.getString("province");
				String city=rs.getString("city");
				String area=rs.getString("area");
				String datailaddress=rs.getString("detailaddress");
				int addressdr=rs.getInt("addressdr");
				String mobile=rs.getString("mobile");
				String uadname=rs.getString("uadname");
				String reserved1=rs.getString("reserved1");
				String reserved2=rs.getString("reserved2");
				String reserved3=rs.getString("reserved3");
				String reserved4=rs.getString("reserved4");
				String reserved5=rs.getString("reserved5");
				address=new Uaddress(uadid, uid, province, city, area, datailaddress, addressdr, mobile, uadname, reserved1, reserved2, reserved3, reserved4, reserved5);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return address;
	}
	
	/**
	 * �����û�id��ѯ�û���������Ϣ
	 * @param userid ��Ҫ��ѯ���û�ID
	 * @return ���ز�ѯ�����û�����
	 */
	public User selectUser(String userid){
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
	 * genju ������Ų鿴ָ����һ����Ʒ
	 * @param ordernumber
	 * @return ���ز�ѯ������Ʒ����
	 */
	public List<Goods> selectgoodsByordernum(String ordernumber){
		List<Goods> list=new ArrayList<Goods>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select g.* from goods g,(select gidlist from order1 where ordernumber=?) o "
					+ "where g.gid=o.gidlist";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, ordernumber);
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
				list.add(new Goods(gid, goodsnumber, goodstotal, mid, goodsname, goodsprice, discountprice, goodssell, goodsdr, goodsinfo, goodsdiscount, goodsimage, sid, reserved1, reserved2, reserved3, reserved4, reserved5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * �û��ύ����  �޸Ķ�����Ϣ
	 * @param order ��Ҫ�޸ĵĶ�����Ϣ
	 * @return �޸ĳɹ�����true ʧ�ܷ���false
	 */
	public boolean updateOrder(Order order){
		boolean boo=false;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="update order1 set uadname=?,account=?,uadid=?,paydr=0,orderdr=1";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, order.getUadname());
			ps.setDouble(2, order.getAccount());
			ps.setString(3, order.getUadid());
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
	 * ��ѯ�û������ж�����Ϣ
	 * @param user ��Ҫ��ѯ���û�
	 * @return �������ж�������
	 */
	public List<Order> selectOrderAll(User user){
		List<Order> list=new ArrayList<>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select * from order1 where name=?";
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
				list.add(new Order(id, ordernumber, gidlist, goodstotallist, uadname, uadname, account, uadid, mid, paydr, orderdr, reserved1, reserved2, reserved3, reserved4, reserved5));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	}
	
	
	/**
	 * ��ѯ�û�������Ķ���
	 * @param user ��Ҫ��ѯ���û�
	 * @return ���ز�ѯ�Ĵ����������
	 */
	public List<Order> selectawaitOrder(User user){
		List<Order> list=new ArrayList<>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select * from order1 where name=? and paydr=1";
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
	 * ��ѯ�û�����������
	 * @param user ��Ҫ��ѯ���û�
	 * @return ���ز�ѯ�Ĵ�������������
	 */
	public List<Order> selectawaitSend(User user){
		List<Order> list=new ArrayList<Order>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select * from order1 where name=? and paydr=0 and orderdr=1";
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
	 * �����û�id�鿴���ջ��Ķ���
	 * @param user ��Ҫ��ѯ���û�
	 * @return ���ز�ѯ�Ĵ��ջ��Ķ���
	 */
	public List<Order> selectawaittake(User user){
		List<Order> list=new ArrayList<Order>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select * from order1 where name=? and paydr=0 and orderdr=0";
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
	 * �����û�id�鿴�����۵Ķ���
	 * @param user ��Ҫ��ѯ���û�
	 * @return ���ز�ѯ�Ĵ����۵Ķ���
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
	 * �����û�id��ѯ���е��ջ���ַ
	 * @param uid ��Ҫ��ѯ��ַ���û�id
	 * @return ���ز�ѯ�����ĵ�ַ���� ��������򼯺ϲ�Ϊ�գ�û�������򼯺�Ϊ��
	 */
	public List<Uaddress> selectaddressAll(String uid){
		List<Uaddress> list=new ArrayList<Uaddress>();
		try {
			Connection conn=DBUtil.getConnection();
			String sql="select uadid,uid,province,city,area,detailaddress,addressdr,"
					+ "mobile,uadname,reserved1,reserved2,reserved3,reserved4,reserved5 "
					+ "from uaddress where uid=? and addressdr!=2";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, uid);
			ResultSet rs=ps.executeQuery();
			while(rs.next()){
				String uadid=rs.getString("uadid");
				String province=rs.getString("province");
				String city=rs.getString("city");
				String area=rs.getString("area");
				String datailaddress=rs.getString("detailaddress");
				int addressdr=rs.getInt("addressdr");
				String mobile=rs.getString("mobile");
				String uadname=rs.getString("uadname");
				String reserved1=rs.getString("reserved1");
				String reserved2=rs.getString("reserved2");
				String reserved3=rs.getString("reserved3");
				String reserved4=rs.getString("reserved4");
				String reserved5=rs.getString("reserved5");
				list.add(new Uaddress(uadid, uid, province, city, area, datailaddress, addressdr, mobile, uadname, reserved1, reserved2, reserved3, reserved4, reserved5));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
	
	
	/**
	 * �û��ջ��ķ���
	 * @param order ����
	 * @return �ջ��ɹ�����true,���򷵻�false
	 */
	public boolean updatetakeOrder(Order order){
		boolean boo=false;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="update order1 set orderdr=2 where gidlist=? and name=?";
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
	 * �û��ύ�˻���Ϣ,���˻���Ϣ���뵽�˻�����
	 * @param ret �˻���Ϣ
	 * @return ����ɹ�����true ����ʧ�ܷ���false
	 */
	public boolean insertreturnGoods(Return ret){
		boolean boo=false;
		try {
			Connection conn=DBUtil.getConnection();
			String sql="insert into return1(id,ordernumber,returntype,returncause,returndetails,returndr,reserved1,reserved2) values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps=conn.prepareStatement(sql);
			ps.setString(1, ret.getId());
			ps.setString(2, ret.getOrdernumber());
			ps.setString(3, ret.getReturntype());
			ps.setString(4, ret.getReturncause());
			ps.setString(5, ret.getReturndetails());
			ps.setInt(6, ret.getReturndr());
			ps.setDouble(7, ret.getReserved1());
			ps.setString(8, ret.getReserved2());
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
	 * 
	 * @param id ����Ķ���id
	 * @return trueΪ�����ɹ���falseʧ��
	 */
	public boolean fahuo(String id){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("update order1 set orderdr=0 where id=?");
			ps.setString(1, id);
			int i = ps.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	/**
	 * 
	 * @param page ҳ��
	 * @param mid �̼�id
	 * @return ����һ����������
	 */
	public List<Order> selectByOPage(int page,String mid){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Order> list = new ArrayList<Order>();
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select * from order1 where mid=? limit ?,5");
			ps.setString(1,mid);
			ps.setInt(2, (page-1)*5);
			rs = ps.executeQuery();
			while(rs.next()){
				String id = rs.getString("id");
				String ordernumber = rs.getString("ordernumber");
				String gidlist = rs.getString("gidlist");
				String goodstotallist = rs.getString("goodstotallist");
				String name = rs.getString("name");
				String uadname = rs.getString("uadname");
				double account = rs.getDouble("account");
				String uadid = rs.getString("uadid");
				String omid = rs.getString("mid");
				int paydr = rs.getInt("paydr");
				int orderdr = rs.getInt("orderdr");
				Order order = new Order(id, ordernumber, gidlist, Integer.valueOf(goodstotallist), uadname, uadname, account, uadid, mid, paydr, orderdr,
						null, null, null, null, null);
				list.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}
	
	
	
}

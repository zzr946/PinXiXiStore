package com.softeem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softeem.model.Return;
import com.softeem.tools.DBUtil;

public class ReturnDAO {

	public List<Return> selectByRPage(int page,String mid){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Return> list = new ArrayList<Return>();
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select return1.* from return1,(select goodsnumber from goods where mid=?) s where return1.ordernumber=s.goodsnumber limit ?,5");
			ps.setString(1,mid);
			ps.setInt(2, (page-1)*5);
			rs = ps.executeQuery();
			while(rs.next()){
				String id = rs.getString("id");
				String goodsnumber = rs.getString("ordernumber");
				String returntype = rs.getString("returntype");
				String returndetails = rs.getString("returndetails");
				String returncause = rs.getString("returncause");
				int returndr = rs.getInt("returndr");
				double reserved1 = rs.getDouble("reserved1");//退款金额
				String reserved2 = rs.getString("reserved2");//退款人id
				Return re = new Return(id, goodsnumber, returntype, returncause, returndetails, returndr,reserved1, reserved2, null, null, null);
				list.add(re);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}
	
	public boolean agreeTuiHuo(String id){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("update return1 set returndr=0 where id=?");
			ps.setString(1, id);
			int i = ps.executeUpdate();
			if(i>0){
				return true;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, null);
		}
		return false;
	}
}

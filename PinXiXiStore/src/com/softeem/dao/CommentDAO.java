package com.softeem.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.softeem.model.Comment;
import com.softeem.tools.DBUtil;

public class CommentDAO {

	/**
	 * 
	 * @param page 查询的页数
	 * @return 返回一个评论集合
	 */
	public List<Comment> selectByCPage(int page){
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		List<Comment> list = new ArrayList<Comment>();
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("select comment.* from comment,(select goods.gid as gid from goods,merchant where goods.mid = merchant.mid) s where comment.gid=s.gid and comment.commentdr=0 or comment.commentdr=1 limit ?,5");
			ps.setInt(1, (page-1)*5);
			rs = ps.executeQuery();
			while(rs.next()){
				String cid = rs.getString("cid");
				String uid = rs.getString("uid");
				String nickname = rs.getString("nickname");
				String gid = rs.getString("gid");
				String content = rs.getString("content");
				String subtime = rs.getString("subtime");
				int commentdr = rs.getInt("commentdr");
				Comment comment =new Comment(cid, uid, nickname, gid, content, subtime, commentdr, 
						null, null, null, null, null);
				list.add(comment);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			DBUtil.close(conn, ps, rs);
		}
		return list;
	}
	
	
	/**
	 * 
	 * @param cid 评论id
	 * @return true为删除成功 false为删除失败
	 */
	public boolean deleteByCid(String cid){
		Connection conn = null;
		PreparedStatement ps = null;
		try {
			conn = DBUtil.getConnection();
			ps = conn.prepareStatement("update comment set commentdr=2 where cid=?");
			ps.setString(1, cid);
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

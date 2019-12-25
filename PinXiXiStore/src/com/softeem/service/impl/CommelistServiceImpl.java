package com.softeem.service.impl;

import java.util.Date;
import java.util.List;

import com.softeem.dao.CommelistDAO;
import com.softeem.model.Comment;
import com.softeem.model.Goods;
import com.softeem.model.Order;
import com.softeem.model.ResultModel;
import com.softeem.model.User;
import com.softeem.service.CommelistService;
import com.softeem.tools.Tools;

public class CommelistServiceImpl implements CommelistService {
	private CommelistDAO cdao=new CommelistDAO();
	
	/**
	 * 根据商品id查询商品详细信息
	 */
	@Override
	public ResultModel goodsInfo(String gid) {
		ResultModel result=new ResultModel();
		//调用dao层
		Goods goods=cdao.selectgoodsByid(gid);
		if(goods!=null){
			//查询成功
			result.setCode("0");
			result.setMsg("查询成功");
			result.setData(goods);
		}else{
			//查询失败
			result.setCode("1");
			result.setMsg("查询失败");
			result.setData(null);
		}
		
		
		
		return result;
	}
	
	/**
	 * 将用户提交的评论存入数据库
	 */
	@Override
	public ResultModel saveComment(String uid, String gid, String content) {
		ResultModel result=new ResultModel();
		//获取评论id
		String cid=Tools.getUUID();
		//获取评论人昵称
		String nickname=cdao.selectUserByid(uid).getNickname();
		//获取评论时间
		String subtime=Tools.dateToStr("yyyy-MM-dd HH:mm:ss", new Date());
		//将信息存入评论对象中
		Comment comment=new Comment(cid, uid, nickname, gid, content, subtime, 0, null, null, null, null, null);
		//将信息存入评论表中
		boolean boo=cdao.insertComment(comment);
		if(boo){
			//存入成功
			//获取真实姓名
			String name=cdao.selectUserByid(uid).getName();
			Order order=new Order();
			order.setName(name);
			order.setGidlist(gid);
			//将订单状态改为已评价状态
			cdao.updateCommentdr(order);
			//查看订单
			User user=new User();
			user.setName(name);
			List<Order> list=cdao.selectawaitevaluate(user);
			
			result.setCode("0");
			result.setMsg("评论成功");
			result.setData(list);
		}else{
			//存入失败
			result.setCode("1");
			result.setMsg("评论失败");
			result.setData(null);
		}
		return result;
	}
	
	/**
	 * 查看某件商品的所有商品
	 * @param gid
	 * @return
	 */
	public ResultModel commentAll(String gid){
		ResultModel result=new ResultModel();
		//调用dao层的方法
		List<Comment> list=cdao.selectCommentAll(gid);
		result.setCode("1");
		result.setMsg("查询成功");
		result.setData(list);
		return result;
	}
	
	
}
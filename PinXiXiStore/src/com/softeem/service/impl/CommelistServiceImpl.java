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
	 * ������Ʒid��ѯ��Ʒ��ϸ��Ϣ
	 */
	@Override
	public ResultModel goodsInfo(String gid) {
		ResultModel result=new ResultModel();
		//����dao��
		Goods goods=cdao.selectgoodsByid(gid);
		if(goods!=null){
			//��ѯ�ɹ�
			result.setCode("0");
			result.setMsg("��ѯ�ɹ�");
			result.setData(goods);
		}else{
			//��ѯʧ��
			result.setCode("1");
			result.setMsg("��ѯʧ��");
			result.setData(null);
		}
		
		
		
		return result;
	}
	
	/**
	 * ���û��ύ�����۴������ݿ�
	 */
	@Override
	public ResultModel saveComment(String uid, String gid, String content) {
		ResultModel result=new ResultModel();
		//��ȡ����id
		String cid=Tools.getUUID();
		//��ȡ�������ǳ�
		String nickname=cdao.selectUserByid(uid).getNickname();
		//��ȡ����ʱ��
		String subtime=Tools.dateToStr("yyyy-MM-dd HH:mm:ss", new Date());
		//����Ϣ�������۶�����
		Comment comment=new Comment(cid, uid, nickname, gid, content, subtime, 0, null, null, null, null, null);
		//����Ϣ�������۱���
		boolean boo=cdao.insertComment(comment);
		if(boo){
			//����ɹ�
			//��ȡ��ʵ����
			String name=cdao.selectUserByid(uid).getName();
			Order order=new Order();
			order.setName(name);
			order.setGidlist(gid);
			//������״̬��Ϊ������״̬
			cdao.updateCommentdr(order);
			//�鿴����
			User user=new User();
			user.setName(name);
			List<Order> list=cdao.selectawaitevaluate(user);
			
			result.setCode("0");
			result.setMsg("���۳ɹ�");
			result.setData(list);
		}else{
			//����ʧ��
			result.setCode("1");
			result.setMsg("����ʧ��");
			result.setData(null);
		}
		return result;
	}
	
	/**
	 * �鿴ĳ����Ʒ��������Ʒ
	 * @param gid
	 * @return
	 */
	public ResultModel commentAll(String gid){
		ResultModel result=new ResultModel();
		//����dao��ķ���
		List<Comment> list=cdao.selectCommentAll(gid);
		result.setCode("1");
		result.setMsg("��ѯ�ɹ�");
		result.setData(list);
		return result;
	}
	
	
}
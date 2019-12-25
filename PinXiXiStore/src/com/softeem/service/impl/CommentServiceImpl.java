package com.softeem.service.impl;

import java.util.List;

import com.softeem.dao.CommentDAO;
import com.softeem.model.Comment;
import com.softeem.model.ResultModel;
import com.softeem.service.CommentService;

public class CommentServiceImpl implements CommentService {

	CommentDAO cdao = new CommentDAO();
	@Override
	public ResultModel selectByCPage(int page) {
		ResultModel result = new ResultModel();
		List<Comment> list = cdao.selectByCPage(page);
		if(list.isEmpty()){
			result.setCode("1");
			result.setMsg("û�鵽��");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("�鵽��");
			result.setData(list);
		}
		return result;
	}
	
	//��������idɾ��
	@Override
	public ResultModel deleteByCid(String cid) {
		ResultModel result = new ResultModel();
		boolean boo = cdao.deleteByCid(cid);
		if(boo){
			//ɾ���ɹ�
			result.setCode("0");
			result.setMsg("ɾ���ɹ�");
			result.setData(null);
		}else{
			//ɾ��ʧ��
			result.setCode("1");
			result.setMsg("ɾ��ʧ��");
			result.setData(null);
		}
		return result;
	}

}

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
			result.setMsg("没查到了");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("查到了");
			result.setData(list);
		}
		return result;
	}
	
	//根据评论id删除
	@Override
	public ResultModel deleteByCid(String cid) {
		ResultModel result = new ResultModel();
		boolean boo = cdao.deleteByCid(cid);
		if(boo){
			//删除成功
			result.setCode("0");
			result.setMsg("删除成功");
			result.setData(null);
		}else{
			//删除失败
			result.setCode("1");
			result.setMsg("删除失败");
			result.setData(null);
		}
		return result;
	}

}

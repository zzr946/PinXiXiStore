package com.softeem.service.impl;

import java.util.List;

import com.softeem.dao.ReturnDAO;
import com.softeem.model.Goods;
import com.softeem.model.ResultModel;
import com.softeem.model.Return;
import com.softeem.service.ReturnService;

public class ReturnServiceImpl implements ReturnService {

	ReturnDAO rdao = new ReturnDAO();
	//按页查询列表
	@Override
	public ResultModel selectByRPage(int page, String mid) {
		ResultModel result = new ResultModel();
		List<Return> list = rdao.selectByRPage(page, mid);
		if(list.isEmpty()){
			result.setCode("1");
			result.setMsg("没查到");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("查到了");
			result.setData(list);
		}
		return result;
	}
	//确认退货
	@Override
	public ResultModel agreeTuiHuo(String id) {
		ResultModel result = new ResultModel();
		boolean boo = rdao.agreeTuiHuo(id);
		if(boo){
			//退货成功
			result.setCode("0");
			result.setMsg("成功");
			result.setData(null);
		}else{
			//退货失败
			result.setCode("1");
			result.setMsg("失败");
			result.setData(null);
		}
		return result;
	}

}

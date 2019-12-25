package com.softeem.service.impl;

import java.util.List;
import java.util.UUID;

import com.softeem.dao.SortDAO;
import com.softeem.model.ResultModel;
import com.softeem.model.Sort;
import com.softeem.service.SortService;

public class SortServiceImpl implements SortService {

	SortDAO sdao = new SortDAO();
	
	@Override
	public ResultModel addSort(String sortname,String sortinfo,String merchantid){
		//检查类别是否存在
		boolean boo = sdao.selectBySortName(sortname);
		ResultModel result = new ResultModel();
		if(boo){
			result.setCode("2");
			result.setMsg("该类别已存在");
			result.setData(null);
			return result;
		}
		//如果不存在，添加类别
		String sid = UUID.randomUUID().toString();
		Sort sort = new Sort();
		sort.setSid(sid);
		sort.setSortname(sortname);
		sort.setSortinfo(sortinfo);
		sort.setMerchantid(merchantid);
		boo = sdao.insert(sort);
		if(boo){
			result.setCode("0");
			result.setMsg("添加成功");
			result.setData(sort);
		}else{
			result.setCode("1");
			result.setMsg("添加失败");
			result.setData(null);
		}
		return result;
	}

	//按页查询分类
	@Override
	public ResultModel selectByPage(String mid, int page) {
		ResultModel result = new ResultModel();
		List<Sort> list = sdao.selectByPage(mid, page);
		if(list.isEmpty()){
			result.setCode("1");
			result.setMsg("查无分类");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("查到了");
			result.setData(list);
		}
		return result;
	}

	//删除分类
	@Override
	public ResultModel deleteBySid(String sid) {
		ResultModel result = new ResultModel();
		boolean boo = sdao.deleteBySid(sid);
		if(boo){
			result.setCode("0");
			result.setMsg("删除成功");;
			result.setData(null);
		}else{
			result.setCode("1");
			result.setMsg("删除失败");;
			result.setData(null);
		}
		return result;
	}

	@Override
	public ResultModel updateSort(String sortname1, String sortname2, String sortinfo) {
		ResultModel result = new ResultModel();
		boolean boo = sdao.updateSort(sortname1, sortname2, sortinfo);
		if(boo){
			result.setCode("0");
			result.setMsg("修改成功");;
			result.setData(null);
		}else{
			result.setCode("1");
			result.setMsg("修改失败");;
			result.setData(null);
		}
		return result;
	}

	//查询所有分类
	@Override
	public ResultModel selectAll(String mid) {
		ResultModel result = new ResultModel();
		List<Sort> list = sdao.selectAll(mid);
		if(list.isEmpty()){
			result.setCode("1");
			result.setMsg("查无分类");
			result.setData(null);
		}else{
			result.setCode("0");
			result.setMsg("查到了");
			result.setData(list);
		}
		return result;
	}

}

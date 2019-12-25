package com.softeem.service.impl;
/**
 * 商品详情实现类
 */
import com.softeem.dao.DetailsDAO;
import com.softeem.model.Goods;
import com.softeem.model.ResultModel;
import com.softeem.service.DetailsService;

public class DetailsServiceImpl implements DetailsService {
	private DetailsDAO ddao=new DetailsDAO();
	
	/**
	 * 根据商品id查询商品详情
	 */
	@Override
	public ResultModel queryOne(String gid) {
		ResultModel result=new ResultModel();
		//调用dao层的方法查询
		Goods goods=ddao.selectOneByid(gid);
		if(goods==null){
			//没有查询到
			result.setCode("1");
			result.setMsg("没查询到该商品信息");
			result.setData(null);
		}else{
			//查询到了
			result.setCode("0");
			result.setMsg("查询成功");
			result.setData(goods);
		}
		return result;
	}
	

}

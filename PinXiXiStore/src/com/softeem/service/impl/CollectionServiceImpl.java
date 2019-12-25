package com.softeem.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.softeem.dao.CollectionDAO;
import com.softeem.model.Collection;
import com.softeem.model.Goods;
import com.softeem.model.ResultModel;
import com.softeem.service.CollectionService;
import com.softeem.tools.Tools;

public class CollectionServiceImpl implements CollectionService {
	private CollectionDAO cdao=new CollectionDAO();
	
	/**
	 * 用户收藏商品的方法(向收藏表中插入数据)
	 * 返回的result结果集 0表示收藏成功  1表示收藏失败  2表示该商品已经收藏过了
	 */
	@Override
	public ResultModel addcollGoods(String uid, String gid) {
		ResultModel result=new ResultModel();
		//先检查用户是否已经收藏过该商品
		boolean bo=cdao.selectBygid(uid, gid);
		if(bo){
			//用户已经收藏过了
			result.setCode("2");
			result.setMsg("收藏成功");
			result.setData(null);
			return result;
		}
		//获取id
		String id=Tools.getUUID();
		//获取加入购物车的时间
		String addtime=Tools.dateToStr("yyyy-MM-dd HH:mm:ss", new Date());
		//将数据装入到一个收藏对象中
		Collection coll=new Collection();
		coll.setId(id);
		coll.setUid(uid);
		coll.setGid(gid);
		coll.setCollecttime(addtime);
		coll.setCollectdr(0);
		//调用dao层的方法插入到数据库收藏表中
		boolean boo=cdao.insertgoods(coll);
		if(boo){
			//收藏成功
			result.setCode("0");
			result.setMsg("收藏成功");
			result.setData(coll);
		}else{
			//收藏失败
			result.setCode("1");
			result.setMsg("收藏失败");
			result.setData(null);
		}
		return result;
	}

	/**
	 * 查看用户收藏的商品
	 */
	@Override
	public ResultModel selectcollGoods(String uid) {
		ResultModel result=new ResultModel();
		//根据用户id查询收藏表
		List<Collection> listcoll=cdao.selectCollGoods(uid);
		//定义一个商品数组
		List<Goods> list=new ArrayList<Goods>();
		for (Collection collection : listcoll) {
			//根据gid查询收藏的商品信息
			Goods goods=cdao.selectgoodsByid(collection.getGid());
			list.add(goods);
		}
		result.setData(list);
		return result;
	}
	
	
	
}

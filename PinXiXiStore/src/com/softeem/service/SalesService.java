package com.softeem.service;

import com.softeem.model.Goods;
import com.softeem.model.ResultModel;

public interface SalesService {

	// 查询商品促销表中的所有商品
	public ResultModel salesall();

	// 向促销表中插入数据
	public ResultModel insert(Goods goods, int salesdr, String id);

	// 删除指定数据
	public ResultModel delete(String gid);

}

package com.softeem.service;

import com.softeem.model.ResultModel;

public interface DetailsService {

	//根据商品id擦护心该商品信息的方法
	public ResultModel queryOne(String gid);
}

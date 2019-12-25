package com.softeem.model;
/**
 * 结果集类
 * @author 赵志然
 *
 */
public class ResultModel {
	private String code;//结果代码
	private String msg;//结果信息
	private Object data;//结果对象
	public ResultModel() {
		super();
	}
	public ResultModel(String code, String msg, Object data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}

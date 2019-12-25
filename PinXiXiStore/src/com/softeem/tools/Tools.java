package com.softeem.tools;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;
import java.util.UUID;
import com.softeem.encrypt.MD5;


/**
 * 通用工具类
 * @author 赵志然
 *
 */
public class Tools {
	
	/**
	 * 将给定的Date时间转为指定的格式字符串
	 * @param path 指定格式
	 * @param date 给定的Date时间
	 * @return 按照指定格式转换好的时间字符串
	 */
	public static String dateToStr(String path,Date date){
		return new SimpleDateFormat(path).format(date);
	}

	/**
	 * 获取UUID
	 * @return
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString();
	}
	/**
	 * 通过MD5对字符串进行加密处理
	 * @param password 需要加密的字符串
	 * @return 加密后的字符串
	 */
	public static String getMD5(String password){
		return new MD5().getMD5ofStr(password);
	}
	
	
	/**
	 * 利用SmartUpload实现文件上传时获取非文件内容乱码解决方法
	 * @param str 需要处理的字符串
	 * @return 返回处理后的字符串
	 */
	public static String Gbktoutf(String str){
		try {
			str = new String(str.getBytes("gbk"),"utf-8");
			System.out.println(str);
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		if(str.contains("~")){
			//如果是以~结尾则截取
			str=str.substring(0, str.length()-1);
		}
		return str;
	}
	
	/**
	 * 生成商品编号的方法
	 * @return 返回随机生成的10为数
	 */
	public static String getNum(){
		StringBuffer sb=new StringBuffer();
		for(int i=1;i<=10;i++){
			int n=new Random().nextInt(10);
			sb.append(n+"");
		}
		return sb.toString();
	}
	
	
//	public static void main(String[] args) {
//		System.out.println(getUUID());
//		System.out.println(getNum());
//		System.out.println(13172+9136+1256+2167);
//	}
	
	

}
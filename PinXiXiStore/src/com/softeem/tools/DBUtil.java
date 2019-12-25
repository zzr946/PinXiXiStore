package com.softeem.tools;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

/**
 * 数据库连接工具类
 * @author Mr.Zhou
 *
 */
public class DBUtil {
	//数据库驱动
	private static String driver;
	//数据库连接路径
	private static String url;
	//数据库的用户名
	private static String user;
	//数据库的密码
	private static String password;
	
	//声明数据源
	private static BasicDataSource bds;
	//连接池的初始化连接数
	private static int initialSize;
	//连接池的最大连接
	private static int maxTotal;
	//连接池的最小闲置数
	private static int minIdle;
	//连接池的最大闲置数
	private static int maxIdle;
	//连接池的最大等待时间
	private static long maxWaitMillis;
	
	static{
		init();
	}
	
	//初始化方法
	private static void init(){
		bds = new BasicDataSource();
		//数据库的相关连接
		//获取属性对象
		Properties pro = System.getProperties();
		
		//加载指定的属性文件
		try {
			InputStream isp = Thread.currentThread().getContextClassLoader().getResourceAsStream("/jdbc.properties");
			pro.load(isp);
			
//			pro.load(new FileInputStream("D:\\代码\\Web上课代码\\dailysystem\\src\\jdbc.properties"));
			//读取配置文件
			driver = pro.getProperty("driver");
			url = pro.getProperty("url");
			user = pro.getProperty("user");
			password = pro.getProperty("password");
			initialSize = Integer.parseInt(pro.getProperty("initialSize"));
			maxTotal = Integer.parseInt(pro.getProperty("maxTotal"));
			minIdle = Integer.parseInt(pro.getProperty("minIdle"));
			maxWaitMillis = Long.parseLong(pro.getProperty("maxWaitMillis"));
			
			//数据库的相关连接
			//设置驱动
			bds.setDriverClassName(driver);
			//设置url
			bds.setUrl(url);
			//设置数据库用户名
			bds.setUsername(user);
			//设置数据库密码
			bds.setPassword(password);
			
			//连接池的相关数据设置
			//设置初始化连接数
			bds.setInitialSize(initialSize);
			//设置最大连接数
			bds.setMaxTotal(maxTotal);
			//设置最大闲置数
			bds.setMaxIdle(maxIdle);
			//设置最小闲置数
			bds.setMinIdle(minIdle);
			//设置等待时间
			bds.setMaxWaitMillis(maxWaitMillis);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * 封装的是获取数据库连接的方法
	 * @return 数据库连接对象
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		//如果数据源为空或者没有加载
		if(bds == null && bds.isClosed()){
			//初始化加载
			init();
		}
		//从数据源中获取连接
		return bds.getConnection();
	}
	
	/**
	 * 封装的是回收资源的方法
	 * @param conn 数据库连接对象
	 * @param ps	预处理对象
	 * @param rs	结果集对象
	 */
	public static void close(Connection conn,
							PreparedStatement ps,
							ResultSet rs){
		try {
			if(conn != null)conn.close();
			if(ps != null)ps.close();
			if(rs != null)rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * 封装的数据更新操作的方法
	 * @param conn 数据库连接对象
	 * @param sql	需要做更新操作的SQL语句
	 * @param obj	更新操作的参数
	 * @return
	 * @throws SQLException 
	 */
	public static boolean executeUpdate(Connection conn,String sql,Object...obj) throws SQLException{
		
		PreparedStatement ps = conn.prepareStatement(sql);
		for(int i=1;i<=obj.length;i++){
			ps.setObject(i, obj[i-1]);
		}
		int i = ps.executeUpdate();
		return i>0?true:false;
			
	}
	
	
	/**
	 * 封装的是一个查询所有数据的方法
	 * @param call 对结果集的处理函数
	 * @param conn	数据库连接
	 * @param sql	查询的SQL语句
	 * @param obj	SQL语句中的参数
	 * @return 处理好的List集合
	 * @throws SQLException
	 */
	public static <T> List<T> queryList(CallBack<T> call, 
										Connection conn,
										String sql,
										Object...obj) throws SQLException{
		PreparedStatement ps = conn.prepareStatement(sql);
		for(int i=1;i<=obj.length;i++){
			ps.setObject(i, obj[i-1]);
		}
		ResultSet rs = ps.executeQuery();
		return call.getDatas(rs);
	}
	
	/**
	 * 封装查询一个对象的方法
	 * @param call 对结果集的处理函数
	 * @param conn 数据库连接对象
	 * @param sql	查询SQL语句
	 * @param obj	SQL语句所需参数
	 * @return 查询到的一个对象
	 * @throws SQLException
	 */
	public static <T> T queryOne(CallBack<T> call, 
										Connection conn,
										String sql,
										Object...obj) throws SQLException{
		PreparedStatement ps = conn.prepareStatement(sql);
		for(int i=1;i<=obj.length;i++){
		ps.setObject(i, obj[i-1]);
		}
		ResultSet rs = ps.executeQuery();
		return call.getData(rs);
	}
	
	
	
	public interface CallBack<T>{
		//JDK1.8推出的新特性  默认接口(default),若是1.8一下版本可以采用抽象类的方法
		//查询所有
		default List<T> getDatas(ResultSet rs){
			return null;
		};
		//查询一个
		default <T> T getData(ResultSet rs){
			return null;
		};
	}
	
	
	
}

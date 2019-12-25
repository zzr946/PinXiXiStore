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
 * ���ݿ����ӹ�����
 * @author Mr.Zhou
 *
 */
public class DBUtil {
	//���ݿ�����
	private static String driver;
	//���ݿ�����·��
	private static String url;
	//���ݿ���û���
	private static String user;
	//���ݿ������
	private static String password;
	
	//��������Դ
	private static BasicDataSource bds;
	//���ӳصĳ�ʼ��������
	private static int initialSize;
	//���ӳص��������
	private static int maxTotal;
	//���ӳص���С������
	private static int minIdle;
	//���ӳص����������
	private static int maxIdle;
	//���ӳص����ȴ�ʱ��
	private static long maxWaitMillis;
	
	static{
		init();
	}
	
	//��ʼ������
	private static void init(){
		bds = new BasicDataSource();
		//���ݿ���������
		//��ȡ���Զ���
		Properties pro = System.getProperties();
		
		//����ָ���������ļ�
		try {
			InputStream isp = Thread.currentThread().getContextClassLoader().getResourceAsStream("/jdbc.properties");
			pro.load(isp);
			
//			pro.load(new FileInputStream("D:\\����\\Web�Ͽδ���\\dailysystem\\src\\jdbc.properties"));
			//��ȡ�����ļ�
			driver = pro.getProperty("driver");
			url = pro.getProperty("url");
			user = pro.getProperty("user");
			password = pro.getProperty("password");
			initialSize = Integer.parseInt(pro.getProperty("initialSize"));
			maxTotal = Integer.parseInt(pro.getProperty("maxTotal"));
			minIdle = Integer.parseInt(pro.getProperty("minIdle"));
			maxWaitMillis = Long.parseLong(pro.getProperty("maxWaitMillis"));
			
			//���ݿ���������
			//��������
			bds.setDriverClassName(driver);
			//����url
			bds.setUrl(url);
			//�������ݿ��û���
			bds.setUsername(user);
			//�������ݿ�����
			bds.setPassword(password);
			
			//���ӳص������������
			//���ó�ʼ��������
			bds.setInitialSize(initialSize);
			//�������������
			bds.setMaxTotal(maxTotal);
			//�������������
			bds.setMaxIdle(maxIdle);
			//������С������
			bds.setMinIdle(minIdle);
			//���õȴ�ʱ��
			bds.setMaxWaitMillis(maxWaitMillis);
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
	}
	
	
	/**
	 * ��װ���ǻ�ȡ���ݿ����ӵķ���
	 * @return ���ݿ����Ӷ���
	 * @throws SQLException 
	 */
	public static Connection getConnection() throws SQLException{
		//�������ԴΪ�ջ���û�м���
		if(bds == null && bds.isClosed()){
			//��ʼ������
			init();
		}
		//������Դ�л�ȡ����
		return bds.getConnection();
	}
	
	/**
	 * ��װ���ǻ�����Դ�ķ���
	 * @param conn ���ݿ����Ӷ���
	 * @param ps	Ԥ�������
	 * @param rs	���������
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
	 * ��װ�����ݸ��²����ķ���
	 * @param conn ���ݿ����Ӷ���
	 * @param sql	��Ҫ�����²�����SQL���
	 * @param obj	���²����Ĳ���
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
	 * ��װ����һ����ѯ�������ݵķ���
	 * @param call �Խ�����Ĵ�����
	 * @param conn	���ݿ�����
	 * @param sql	��ѯ��SQL���
	 * @param obj	SQL����еĲ���
	 * @return ����õ�List����
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
	 * ��װ��ѯһ������ķ���
	 * @param call �Խ�����Ĵ�����
	 * @param conn ���ݿ����Ӷ���
	 * @param sql	��ѯSQL���
	 * @param obj	SQL����������
	 * @return ��ѯ����һ������
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
		//JDK1.8�Ƴ���������  Ĭ�Ͻӿ�(default),����1.8һ�°汾���Բ��ó�����ķ���
		//��ѯ����
		default List<T> getDatas(ResultSet rs){
			return null;
		};
		//��ѯһ��
		default <T> T getData(ResultSet rs){
			return null;
		};
	}
	
	
	
}

package sunyard;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.Properties;

import javax.activation.DataSource;

public class JdbcPool implements DataSource{
	private static LinkedList<Connection> listConnections = 
			new LinkedList<Connection>();
	static {
		InputStream in = JdbcPool.class.getClassLoader().getResourceAsStream("db.properties");
		Properties prop = new Properties();
		try {
			prop.load(in);
			
			String driver = prop.getProperty("driver");
			System.out.println(driver);
			String url = prop.getProperty("url");
			System.out.println(url);
			String username = prop.getProperty("username");
			String password = prop.getProperty("password");
			System.out.println(username + password);
			int jdbcPoolInitSize = Integer.parseInt(prop.getProperty("jdbcPoolInitSize"));
			Class.forName(driver);
			for (int i=0; i < jdbcPoolInitSize; i++) {
				Connection conn = DriverManager.getConnection(url, username, password);
				System.out.println("��ȡ�����ӣ�" + conn);
				listConnections.add(conn);
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw new ExceptionInInitializerError(e);
		}
	}
	

	
	/**
	 * ��getConnection����ÿ�ε���ʱ����LinkedList��ȡ��һ��Connection���ظ��û�
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException{
		if (listConnections.size() > 0) {
			System.out.println("listConnection���ݿ����ӳش�СΪ" + listConnections.size());
			//���ض����еĵ�һ������
			final Connection conn = listConnections.removeFirst();

			//��̬����
			return (Connection) Proxy.newProxyInstance(JdbcPool.class.getClassLoader(), conn.getClass().getInterfaces(), new InvocationHandler(){
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
					if (!method.getName().equals("close")) {
						System.out.println("�ϵ�5");
						return method.invoke(conn, args);
					}else {
						//������close����ʱȷ��connection�᷵�ص����ӳ�
						listConnections.add(conn);
						System.out.println(conn + "������listConnections���ݿ����ӳ��ˣ���");
					    System.out.println("listConnections���ݿ����ӳش�СΪ" + listConnections.size());
					    return null;
					}
				}	
			});
//			return conn;
		}else {
			throw new RuntimeException("�Բ������ݿ�æ");
		}
	}
	public Connection getConnection(String username, String password) throws SQLException{
		return null;
		
	}
	
	public PrintWriter getLogWriter() throws SQLException{
		return null;
	}
	
	public void setLogWriter(PrintWriter out) throws SQLException{
		
	}
	
	public void setLoginTimeout(int seconds) throws SQLException{
		
	}
	
	public int getLoginTimeout() throws SQLException{
		return 0;
	}
	
	public <T> T unwarp(Class<T> iface) throws SQLException{
		return null;
	}
	
	public boolean isWarpperFor(Class<?> iface) throws SQLException{
		return false;
	}

	@Override
	public String getContentType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public InputStream getInputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public OutputStream getOutputStream() throws IOException {
		// TODO Auto-generated method stub
		return null;
	}
	
	private boolean isNotNull(String str) {
		return str != null;
	}

}

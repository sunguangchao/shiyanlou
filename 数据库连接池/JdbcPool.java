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
				System.out.println("获取了连接：" + conn);
				listConnections.add(conn);
			}
		}catch (Exception e) {
			// TODO: handle exception
			throw new ExceptionInInitializerError(e);
		}
	}
	

	
	/**
	 * 当getConnection方法每次调用时，从LinkedList中取出一个Connection返回给用户
	 * @return
	 * @throws SQLException
	 */
	public Connection getConnection() throws SQLException{
		if (listConnections.size() > 0) {
			System.out.println("listConnection数据库连接池大小为" + listConnections.size());
			//返回队列中的第一个连接
			final Connection conn = listConnections.removeFirst();

			//动态代理
			return (Connection) Proxy.newProxyInstance(JdbcPool.class.getClassLoader(), conn.getClass().getInterfaces(), new InvocationHandler(){
				@Override
				public Object invoke(Object proxy, Method method, Object[] args) throws Throwable{
					if (!method.getName().equals("close")) {
						System.out.println("断点5");
						return method.invoke(conn, args);
					}else {
						//当调用close方法时确保connection会返回到连接池
						listConnections.add(conn);
						System.out.println(conn + "被还给listConnections数据库连接池了！！");
					    System.out.println("listConnections数据库连接池大小为" + listConnections.size());
					    return null;
					}
				}	
			});
//			return conn;
		}else {
			throw new RuntimeException("对不起，数据库忙");
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

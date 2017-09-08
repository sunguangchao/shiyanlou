[WebApplicationContextUtils](http://www.cnblogs.com/luoruiyuan/p/5498407.html)
---------------------------

```java
public class demoServlet extends HttpServlet{
	IDemoWS demoWS;
	public void init() throws ServletException{
		super.init();
		ServletContext servletContext = this.getServletContext();
		WebApplicationContext ctx = WebApplicationContextUtils.getWebApplicationContext(servletContext);
		demoWS = (ISignpicWS)ctx.getBean("demoWS");
	}
	protected void doPost(HttpServletRequest request, HttpServletRequest response) throws ServletException{
		//request.getSession().getServletContext();
	}
}
```
request.getParameterNames()
--------------
request.getParameterNames()方法是将发送请求页面中form表单里所有具有name属性的表单对象获取(包括button).返回一个Enumeration类型的枚举  
通过Enumeration的hasMoreElements()方法遍历.再由nextElement()方法获得枚举的值.此时的值是form表单中所有控件的name属性的值.  
最后通过request.getParameter()方法获取表单控件的value值.

```java
//获取页面的form数据，放到HashMap中
public static HashMap<String, String> getFormData(HttpServletRequest request){
	HashMap<String, String> paramMap = new HashMap<String, String>();
	try{
		Enumeration<String> params = request.getParameterNames();
		while(params.hasMoreElements()){
			String paramName = params.nextElement();
			String paramValue = request.getParameter(paramName);
			//增加一步转换，把null转换成空字符串
			paramValue = (paramValue == null ? "" : paramValue);
			paramMap.put(paramName, paramValue);
		}
	}

```




[Java ResourceBundle外部路径资源文件方式](http://blog.csdn.net/u010143767/article/details/51967760)  
----------------------------


ResourceBundle读取的文件在classpath路径下，也就是在src目录下- config.properties

```java
ResourceBundle rb = ResourceBundle.getBundle("config");
```
如果在某包下，则为：package.config，比如在xcc包下：
```java
ResourceBundle rb = ResourceBundle.getBundle("xcc.config");
```
resourceBundle.properties放在一个文件夹下，比如新建config文件夹：
```java
private static ResourceBundle rb;
private static BufferedInputStream inputStream;
static{
	String proFilePath = System.getProperty("user.dir") + "\\config\\resourceBundle.properties";
	try{
		inputStream = new BufferedInputStream(new FileInputStream(proFilePath));
		rb = new PropertyResourceBundle(inputStream);
		inputStream.close();
	}catch(FileNotFoundException e){
		e.printStackTrace();
	}catch(IOException e){
		e.printStackTrace();
	}
}
```

iBatis
------------------
```xml
		<dynamic prepend=" and ">
			<isNotEmpty prepend=" and " property="JIGou">
				JIGOU = #JIGOU#
			</isNotEmpty>
		</dynamic>
```
<dynamic>动态sql标签，表示此标签内的sql将是动态sql，根据动态条件去判断是否拼接在一起；prepend前置的意思，prepend=" and "就是在以下sql语句之前加上and关键字（如果以下语句有需要的话，不拼接则不加 and ）；
<isNotEmpty prepend=" and " property="JIGou">判断参数JIGou的传值是否为空（包括null和空字符），如果不为空，则拼接这条sql，并按需要前置添加 and 。



在项目中有关iBatis的配置
----------

1.SqlMapClientFactoryBean的装配

SqlMapClientFactoryBean是SqlMapClientTemplate使用的基础，如果在SpringFramework应用中没有装配SqlMapClientFactoryBean，那么SqlMapClientTemplate将不可用，报空指针错误。  

applicatinContext.xml:

```xml
<bean id="sqlMapClient" class="org.springframework.orm.ibatis.SqlMapClientFactoryBean">
	<property name="dataSource" ref="dataSource1" /><!--在SpringFramework配置文件中使用的数据源-->
	<property name="configLocation" value="WEB-INF/sql-map-config.xml" />
  	<!--iBatis sqlmp config文件位置-->
</bean>
```

2.继承使用SqlMapClientDaoSupport类

```xml
<bean id="sqlExecuteDao" class="com.sunyard.csr.dao.SqlExecuteDao">
		<property name="sqlMapClient" ref="sqlMapClient" />
  		<!--SpringFramework配置文件中装配Java类-->
</bean>
```

```java
import org.springframework.orm.ibatis.support.SqlMapClientDaoSupport;
...
public class SqlExecuteDao extends SqlMapClientDaoSupport implements ISqlExecuteDao {
 ... 
}
```

3.使用SqlMapClientTemplate查询

```java
List result = getSqlMapClientTemplate().queryForList("TestSpace.qryTest");
```

TestSpace为iBatis SqlMap文件的命名空间，qryTest为iBatis SqlMap的查询方法id

当按照主键获取某条记录信息时：

```java
Long id = new Long("2");
Object resultObj = getSqlMapClientTemplate().queryForObject("TestSpace.getTest", id);
```

当按照某些条件查询时：

````
ObjectA objA = new ObjectA();
objA.setParam1("test1");
objA.setParam2("test2");
...
List result = getSqlMapClientTemplate().queryForList("TestSpace.qryTestByParam", objA);
````

使用SqlMapClientTemplate添加数据：

```java
ObjectA objA = new ObjectA();
objA.setParam1("test1");
objA.setParam2("test2");
...
List result = getSqlMapClientTemplate().insert("TestSpace.insertTest",objA);
```



iBatis在Spring下批处理实现
------------

1.iBatis的基本实现
iBatis通过SqlMapClient提供了一组方法用于批处理实现：
* startBatch()开始批处理
* executeBatch()执行批处理

```java
public void create(List<Reply> replyList){
	try{
		//开始批处理
		sqlMapClient.startBatch();
		for (Reply reply : replyList){
			sqlMapClient.insert("Reply.create", reply);
		}
		sqlMapClient.executeBatch();
	}catch(Exception e){
		e.printStackTrace();
	}
}
```
除了上面两个方法之外，还需要开启事务支持。否则，不起作用。
基于事务的iBatis的基本实现
----------
事务物理：
* startTransaction() 开始事务
* commitTransaction() 提交事务
* endTransaction() 结束事务

```java
public void create(List<Reply> replyList){
	try{
		sqlMapClient.startTransaction();
		sqlMapClient.startBatch();
		for (Reply reply : replyList){
			sqlMapClient.insert("Reploy.create", reply);
		}
		sqlMapClient.executeBatch();
		sqlMapClient.commitTransaction();
	}catch(Exception e){
		e.printStackTrace();
	}finally{
		try{
			sqlMapClient.endTransaction();
		}catch(SQLException e){
			e.printStackTrace();
		}
	}
}
```

如果要在Spring+iBatis中进行批处理实现，需要注意使用同一个salMapClient。同时，将提交事务的工作交给Spring统一处理！

3.基于事务的Spring+iBatis实现

```java
public void create(List<Reply> replyList){
	if (!CollectionUtils.isEmpty(replyList)) {
		//注意使用同一个SqlMapClient会话
		SqlMapClient sqlMapClient = sqlMapClientTemplate.getSqlMapClient();
		try{
			sqlMapClient.startTransaction();
			sqlMapClient.startBatch();
			for (Reply reply : replyList){
				//插入操作
				sqlMapClient.insert("Reply.create", reply);
			}
			//执行批处理
			sqlMapClient.executeBatch();
			//提交事务 交给Spring统一控制
			sqlMapClient.commitTransaction();
		} catch (Exception e){
			e.printStackTrace()
		} finally {
			try {
				sqlMapClient.endTransaction();
			} catch (SQLException e){
				e.printStackTrace();
			}
		}
	}
}
```
注意使用同一个sqlMapClient:
```java
SqlMapClient sqlMapClient =  sqlMapClientTemplate.getSqlMapClient();
```
如果直接用sqlMapClientTemplate执行insert()方法，将会造成异常。

4.基于回调方式的Spring+iBatis实现
如果观察过Spring的源代码，就会知道，Spring为了保持事务统一控制，在实现ORM框架时都采用了回调模式，从而避免了事务代码入侵的可能！
```java
@Suppresswarning("unchecked")
public void create(final List<Reply> replyList){
	sqlMapClientTemplate.execute(new SqlMapClientCallback(){
		public Object doInSqlMapClient(SqlMapExecutor executor) throws SQLException{
			//开始批处理
			executor.startBatch();
			for (Reply reply : replyList){
				//插入操作
				executor.insert("Reply.create", reply);
			}
			executor.executeBatch();

			return null;
		}
	});
}
```

注意，待遍历的参数replyList需要加入final标识！即，待遍历对象不能修改。
```
public void create(final List<Reply> replyList)
```
简述：
* SqlMapClientCallback回调接口
* doInSqlMapClient(SqlMapExecutor executor)回调实现方法
* DataAccessException最终可能抛出的异常




Spring中@Transaction对于事务异常的处理
-----------------------
```java
@Transactional(rollbackFor=Exception.class)
//让Spring对于Exception进行进行事务的回滚
@Transcational(noRollbackFor=RuntimeException.class)
//让Spring对于RuntimeException不回滚事务
```

参考：[Spring中@Transaction对于事务异常的处理](http://blog.csdn.net/xzf19901108/article/details/7857357)


IntrospectorCleanupListener简介
-------------------
```xml
<listener>
		<listener-class>
			org.springframework.web.context.ContextLoaderListener
		</listener-class>
	</listener>
	<!-- Spring 刷新Introspector防止内存泄露 -->
	<listener>
		<listener-class>
			org.springframework.web.util.IntrospectorCleanupListener
		</listener-class>
	</listener>
	<listener>
	  <listener-class>org.springframework.web.util.Log4jConfigListener</listener-class>
	 </listener>
```
`org.springframework.web.util.IntrospectorCleanupListener`主要负责处理由JavaBean Instrospector使用而引起的缓冲泄漏，它是一个在web应用关闭时清除JavaBean Instrospector的监听器，在web.xml中注册这个listener可以保证在web应用关闭的时候释放掉这个web应用相关的classloader和由它管理的类。

`org.springframework.web.util.IntrospectorCleanupListener`源代码中对其的解释如下：

Listener that flushes the JDK's JavaBeans Introspector cache on web app shutdown. Register this listener in your web.xml to guarantee proper release of the web application class loader and its loaded classes.

在Web应用程序关闭时IntrospectorCleanupListener将会刷新JDK的JavaBeans的Introspector缓存。在你的web.xml中注册这个listener来确保Web应用程序的类加载器以及其加载的类正确的释放资源。

If the JavaBeans Introspector has been used to analyze application classes, the system-level Introspector cache will hold a hard reference to those classes. Consequently, those classes and the web application class loader will not be garbage-collected on web app shutdown! This listener performs proper cleanup, to allow for garbage collection to take effect.      

如果JavaBeans的Introspector已被用来分析应用程序类，系统级的Introspector缓存将持有这些类的一个硬引用。因此，这些类和Web应用程序的类加载器在Web应用程序关闭时将不会被垃圾收集器回收！而IntrospectorCleanupListener则会对其进行适当的清理，已使其能够被垃圾收集器回收。

Unfortunately, the only way to clean up the Introspector is to flush the entire cache, as there is no way to specifically determine the application's classes referenced there. This will remove cached introspection results for all other applications in the server too.

不幸的是，唯一能够清理Introspector的方法是刷新整个Introspector缓存，没有其他办法来确切指定应用程序所引用的类。这将删除所有其他应用程序在服务器的缓存的Introspector结果。

Note that this listener is not necessary when using Spring's beans infrastructure within the application, as Spring's own introspection results cache will immediately flush an analyzed class from the JavaBeans Introspector cache and only hold a cache within the application's own ClassLoader. Although Spring itself does not create JDK Introspector leaks, note that this listener should nevertheless be used in scenarios where the Spring framework classes themselves reside in a 'common' ClassLoader (such as the system ClassLoader). In such a scenario, this listener will properly clean up Spring's introspection cache.

请注意，在使用Spring内部的bean机制时，不需要使用此监听器，因为Spring自己的introspection results cache将会立即刷新被分析过的JavaBeans Introspector cache，而仅仅会在应用程序自己的ClassLoader里面持有一个cache。虽然Spring本身不产生泄漏，注意，即使在Spring框架的类本身驻留在一个“共同”类加载器（如系统的ClassLoader）的情况下，也仍然应该使用使用IntrospectorCleanupListener。在这种情况下，这个IntrospectorCleanupListener将会妥善清理Spring的introspection cache。

Application classes hardly ever need to use the JavaBeans Introspector directly, so are normally not the cause of Introspector resource leaks. Rather, many libraries and frameworks do not clean up the Introspector: e.g. Struts and Quartz.

应用程序类，几乎不需要直接使用JavaBeans Introspector，所以，通常都不是Introspector resource造成内存泄露。相反，许多库和框架，不清理Introspector，例如： Struts和Quartz。

Note that a single such Introspector leak will cause the entire web app class loader to not get garbage collected! This has the consequence that you will see all the application's static class resources (like singletons) around after web app shutdown, which is not the fault of those classes!

需要注意的是一个简单Introspector泄漏将会导致整个Web应用程序的类加载器不会被回收！这样做的结果，将会是在web应用程序关闭时，该应用程序所有的静态类资源（比如：单实例对象）都没有得到释放。而导致内存泄露的根本原因其实并不是这些未被回收的类！

This listener should be registered as the first one in web.xml, before any application listeners such as Spring's ContextLoaderListener. This allows the listener to take full effect at the right time of the lifecycle. 

IntrospectorCleanupListener应该注册为web.xml中的第一个Listener，在任何其他Listener之前注册，比如在Spring's ContextLoaderListener注册之前，才能确保IntrospectorCleanupListener在Web应用的生命周期适当时机生效。

```java
package org.springframework.web.util

import java.beans.Introspector;
import java.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class IntrospectorCleanupListener implements ServletConetxtListener{
  public void contextInitialized(ServletContextEvent event){
    
  }
  public void contextDestroyed(ServletConetxtEvent event){
    Introspector.flushCaches();//把缓存中的内容清除掉
  }
}
```

## 获取JNDI数据源

如果应用配置在高性能的服务器如WebLogic或Websphere上，我们可能希望使用应用服务器本身提供的数据源，供开发者使用。Spring为此专门提供引用JNDI资源的JndiObjectFactoryBean类，在我们的项目中是这样配置的：

```java
<bean id="dataSource1" class="org.springframework.jndi.JndiObjectFactoryBean">
	<property name="jndiName">
		<value>java:comp/env/jdbc/newAgent</value>
	</property>
</bean>
```

web.xml中的配置：

```java
<context-param>
	<param-name>DATASOURCE_URL</param-name>
	<param-value>java:comp/env/jdbc/newAgent</param-value>
</context-param>
```

Spring的数据源实现类

Spring本身也提供了一个简单的数据源实现类`DriverManagerDataSource`，它位于`org.springframework.jdbc.datasource`包中。这个类实现了javax.sql.DataSource接口，但它并没有提供池化连接的机制，每次使用getConnection()获取新连接时，只是简单地创建一个新的连接。因此，这个数据源类比较适合在单元测试中，或简单的独立应用中使用，因为它不需要额外的依赖类。

```java
DriverManagerDataSource ds = new DriverManagerDataSource();
ds.setDriverClass("com.mysql.jdbc.Driver");
ds.setUrl("jdbc:mysql://localhost:3306/sampledb");
ds.setUsername("root");
ds.setPassword("root");
Connection actualCon = ds.getConnection();
```

[Spring的JNDI数据源连接池配置示例及Spring对JNDI实现分析](http://kingxss.iteye.com/blog/1481872)
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Java Web 基础知识</title>
</head>
<body>
<pre>
0.web.xml加载过程（步骤）：
	1.启动WEB项目的时候,容器(如:Tomcat)会去读它的配置文件web.xml.读两个节点:   
	<listener></listener> 和 <context-param></context-param>

	2.紧接着,容器创建一个ServletContext(上下文),这个WEB项目所有部分都将共享这个上下文.

	3.容器将<context-param></context-param>转化为键值对,并交给ServletContext.

	4.容器创建<listener></listener>中的类实例,即创建监听.

	5.在监听中会有contextInitialized(ServletContextEvent args)初始化方法,在这个方法中获得：
			ServletContext = ServletContextEvent.getServletContext();   
			context-param的值 = ServletContext.getInitParameter("context-param的键");  

	6.得到这个context-param的值之后,你就可以做一些操作了.注意,这个时候你的WEB项目还没有完全启动完成.这个动作会比所有的Servlet都要早.
	换句话说,这个时候,你对<context-param>中的键值做的操作,将在你的WEB项目完全启动之前被执行.

	7.举例.你可能想在项目启动之前就打开数据库.
	那么这里就可以在<context-param>中设置数据库的连接方式,在监听类中初始化数据库的连接.

	8.这个监听是自己写的一个类,除了初始化方法,它还有销毁方法.用于关闭应用前释放资源.比如说数据库连接的关闭.

对于某类配置节而言，与它们出现的顺序是有关的。
以 filter 为例，web.xml 中当然可以定义多个 filter，与 filter 相关的一个配置节是 filter-mapping，
这里一定要注意，对于拥有相同 filter-name 的 filter 和 filter-mapping 配置节而言，
filter-mapping 必须出现在 filter 之后，否则当解析到 filter-mapping 时，它所对应的 filter-name 还未定义。
web 容器启动时初始化每个 filter 时，是按照 filter 配置节出现的顺序来初始化的，
当请求资源匹配多个 filter-mapping 时，
filter 拦截资源是按照 filter-mapping 配置节出现的顺序来依次调用 doFilter() 方法的。

servlet 同 filter 类似，此处不再赘述。

【加载spring】
	比如filter 需要用到 bean ，但加载顺序是： 先加载filter 后加载spring，则filter中初始化操作中的bean为null；
	 所以，如果过滤器中要使用到 bean，可以将spring 的加载 改成 Listener的方式 :
	<listener>  
	        <listener-class>org.springframework.web.context.ContextLoaderListener</listener-class>  
	</listener>
最终结论：
web.xml 的加载顺序是：[context-param -> listener -> filter -> servlet -> spring]，
而同类型节点之间的实际程序调用的时候的顺序是根据对应的 mapping 的顺序进行调用的。


1.警告：WEB-INF文件夹下面必须要有3个文件： 
	classes文件夹、 lib文件夹、  web.xml文件，否则出错！
	
2.如何在外部浏览器中打开eclipse中的web项目？
	菜单栏Window -> Web Browser -> Default system web browser
	
3.eclipse 显示编码格式调整：window -> preferances ->general -> workspace 中的 text file encoding


4.JavaWeb监听器：事件源：三大域！
 * ServletContext、ServletRequest、HttpSession
 * 	ServletRequest      保存的键值仅在下一个request对象中可以得到。
 * 	Session        		它是一个会话范围，相当于一个局部变量，从Session第一次创建知道关闭，数据都一直 保存，
 * 						每一个客户都有一个Session，所以它可以被客户一直访问，只要Session没有关闭和超时即浏览器关闭。
 * 	servletContext   	它代表了servlet环境的上下文(即运行环境或情景)，相当于一个全局变量，即只要某个web应用在启动中，这个对象就一直都有效的存在，
 * 						所以它的范围是最大的，存储的数据可以被所有用户使用，只要服务器不关闭，数据就会一直都存在。	
5.JavaWeb 字符编码格式 
	a) .jsp or servlet 文件的字符编码格式
	b) request  客户端发送给服务器的数据的字符编码格式  request.setCharacterEncoding("utf-8");
	c) response	 服务器返回给客户端的数据的字符编码格式  response.setCharacterEncoding("UTF-8");
	谨记：
	request.setCharacterEncoding("UTF-8");
	response.setCharacterEncoding("UTF-8");//上面两句要放在第三句的前面
	PrintWriter out = response.getWriter();
</pre>
</body>
</html>
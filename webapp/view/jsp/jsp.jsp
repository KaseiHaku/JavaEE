<%-- 
一个 JSP == Servlet 类{
	<%! 在该标签里定义的变量or方法 == Servlet 成员变量 or 成员函数定义%>
	
	service(){
		<% 在该标签里定义的变量(不能定义方法) == Servlet中 service()方法的局部变量 %>
	}
}
--%>
<%-- JSP指令 --%>
<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8"%>
<%@ page import="java.util.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ include file="head.jsp" %>
<body>

<h1>JSP</h1>
<div>
	<%-- JSP 隐式对象： 
	request		HttpServletRequest类的实例
	response	HttpServletResponse类的实例
	out			PrintWriter类的实例，用于把结果输出至网页上
	session		HttpSession类的实例
	application	ServletContext类的实例，与应用上下文有关
	config		ServletConfig类的实例
	pageContext	PageContext类的实例，提供对JSP页面所有对象以及命名空间的访问
	page		类似于Java类中的this关键字
	Exception	Exception类的对象，代表发生错误的JSP页面中对应的异常对象
	--%>

	<%-- JSP 动作元素：
	jsp:include		在页面被请求的时候引入一个文件。
	jsp:useBean		寻找或者实例化一个JavaBean。
	jsp:setProperty	设置JavaBean的属性。
	jsp:getProperty	输出某个JavaBean的属性。
	jsp:forward		把请求转到一个新的页面。
	jsp:plugin		根据浏览器类型为Java插件生成OBJECT或EMBED标记。
	jsp:element		定义动态XML元素
	jsp:attribute	设置动态定义的XML元素属性。
	jsp:body		设置动态定义的XML元素内容。
	jsp:text		在JSP页面和文档中使用写入文本的模板
	
	所有的动作要素都有两个属性：id属性和scope属性。
	id属性：
	id属性是动作元素的唯一标识，可以在JSP页面中引用。动作元素创建的id值可以通过PageContext来调用。
	scope属性：
	该属性用于识别动作元素的生命周期。 id属性和scope属性有直接关系，scope属性定义了相关联id对象的寿命。 
	scope属性有四个可能的值： (a) page, (b)request, (c)session, 和 (d) application。
	 --%>
	<%! %><!-- 这是JSP声明标签：因为整个JSP文件会被编译成一个servlet类
					jsp页面里面的所有东西都会包含在一个方法里。如果不用声明标签去声明这是个方法，就会报错了
					因为方法里面不能有方法 
					在这里面定义的变量是成员变量，定义的方法是成员方法，
					-->
	<%	String txt = request.getParameter("txt"); %><%-- JSP 中的 Java 代码 --%>
	<%= "接收的参数值为："+txt %><%--JSP 表达式：用于直接向 Html文档输出 --%>
	<hr/>
	
	
	
		
	<h2>JSP EL表达式</h2>
	<pre>
	JSP EL简介
	使得访问存储在JavaBean中的数据变得非常简单。
	1、语法结构
	     ${expression}
	2、[ ]与.运算符
	     EL 提供“.“和“[ ]“两种运算符来存取数据。
	     当要存取的属性名称中包含一些特殊字符，如.或?等并非字母或数字的符号，就一定要使用“[ ]“。例如：
	         ${user.My-Name}应当改为${user["My-Name"] }
	     如果要动态取值时，就可以用“[ ]“来做，而“.“无法做到动态取值。例如：
	         ${sessionScope.user[data]}中data 是一个变量
	3、变量
	     EL存取变量数据的方法很简单，例如：${username}。它的意思是取出某一范围中名称为username的变量。
	     因为我们并没有指定哪一个范围的username，所以它会依序从Page、Request、Session、Application范围查找。
	     假如途中找到username，就直接回传，不再继续找下去，但是假如全部的范围都没有找到时，就回传null。
	     属性范围在EL中的名称
	     
				JSP EL支持下表列出的隐含对象：
			隐含对象				描述
			pageScope			page 作用域
			requestScope		request 作用域
			sessionScope		session 作用域
			applicationScope	application 作用域
			param				Request 对象的参数，字符串
			paramValues			Request对象的参数，字符串集合
			header				HTTP 信息头，字符串
			headerValues		HTTP 信息头，字符串集合
			initParam			上下文初始化参数
			cookie				Cookie值
			pageContext			当前页面的pageContext
	</pre>
	用 EL 表达式读取 Java Bean：${bean.name}
	<hr/>
</div>

</body>
</html>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>


<%-- 在本jsp文件中引入标签库，使用前缀  c --%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- 使用自己的标签库 --%>
<%@ taglib prefix="ex" uri="http://kasei.com/MyTag"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>tag.jsp</title>
</head>
<body>
	
	
	<h2>JSP JavaBean</h2>
	id中的字符串，就是javaWeb.JavaBean类的一个实例的引用<br/>
	<jsp:useBean id="bean" class="k02JavaBean.JavaBean"></jsp:useBean>
	<jsp:setProperty name="bean" property="name" value="rtrtr"/>
	用 Java 代码读取 Java Bean：<%= bean.getName() %><br/>
	用 jsp 动作元素读取 Java Bean：<jsp:getProperty name="bean" property="name"/>
	<hr/>
	
	
	<h2>JSTL 标签库</h2>
	需要在 jsp 头中导入标签库：
	<c:out value="jstl标签库"></c:out>
	<hr/>
	
	<h2>自定义标签</h2>
	1.要创建自定义的JSP标签，你首先必须创建处理标签的Java类，并继承 SimpleTagSupport 类<br/>
	2.重写 doTag() 方法<br/>
	3.最后创建如下标签库：\WEB-INF\custom.tld <br/>
	<ex:kasei message="This is custom tag">qwer</ex:kasei>
	<hr/>
	
</body>
</html>
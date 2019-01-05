<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Cookie.jsp</title>
</head>
<body>



<h2>设置 Cookie</h2>
<% 
//设置 Cookie：Cookie不能包含以下字符：  [ ] ( ) = , " / ? @ : ;
Cookie cookie = new Cookie("love", "wlt");//创建Cookie对象
cookie.setMaxAge(10);//设置cookie的最大存在时间,默认为-1表示直到浏览器关闭为止
cookie.setDomain("localhost");//设置域
cookie.setPath("/JavaWeb/");//设置路径
cookie.setValue("TMD");//设置值
cookie.setComment("注释：用于描述该cookie的用处");//设置注释
//cookie.setSecure(true);//是否加密传输,设置以后只能被 部署到 https 协议的网站读取

response.addCookie(cookie);//把cookie添加到http响应头中,当该页面响应时就会设置cookie了
%>
<form method="get" action="http://localhost:8088/JavaWeb/k01Servlet/K05ServletCookie" target="_blank">
	<input type="submit" value="演示 Cookie"/>
</form>




</body>
</html>
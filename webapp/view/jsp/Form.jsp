<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Form</title>
</head>
<body>
	<form action="/JavaWeb/k01Servlet/K02ServletForm" method="get">
		姓名：<input type="text" name="name" value="kasei"/><br/>
		
		<input type="checkbox" name="cars" value="Nissan" checked="true">Nissan
		<input type="checkbox" name="cars" value="宝马">宝马<br/><br/>		
	
		<input type="submit" value="Submit"/>
	</form>
</body>
</html>
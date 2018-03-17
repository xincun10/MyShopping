<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
<h1>登录界面</h1>
<form action="/MyShopping/GoHallUI" method="post">
<table border=1>
	<tr><td>用户id</td><td><input type="text" name="id"/></td></tr>
	<tr><td>密　码</td><td><input type="password" name="passwd"/></td></tr>
	<tr><td><input type="submit" value="登录"/></td><td><input type="reset" value="重置"/></td></tr>
</table>
</form>
</body>
</html>
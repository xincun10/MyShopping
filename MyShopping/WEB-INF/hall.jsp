<%@ page language="java" import="java.util.*,com.zcy.domain.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
</head>
<body>
	<h1>欢迎光临购物大厅</h1>
	<table border=1>
		<tr><td>书名</td><td>价格</td><td>出版社</td><td>点击购买</td></tr>
		<%
			//取出request中的ArrayList
			ArrayList al = (ArrayList)request.getAttribute("books");
			for(int i=0; i<al.size(); i++)
			{
				Book book = (Book)al.get(i);
				%>
				<tr><td><%=book.getName() %></td><td><%=book.getPrice() %></td>
				<td><%=book.getPublishHouse() %></td><td>
				<a href="/MyShopping/ShoppingClServlet?type=add&id=<%=book.getId() %>">点击购买</a></td></tr>
				<%
			}
		%>
		<tr><td colspan="4"><input type="button" value="查看购物车" /></td></tr>
		<a href="/MyShopping">返回重新登录</a>
	</table>
</body>
</html>
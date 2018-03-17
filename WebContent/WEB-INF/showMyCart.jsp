<%@ page language="java" import="java.util.*,com.zcy.domain.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<link rel="stylesheet" type="text/css" href="css/common.css" />
<title>Insert title here</title>
</head>
<body>
	<h1>我的购物车</h1>
	<a href="/MyShopping/GoHallUI">返回购物大厅</a><br/>
	<form action="/MyShopping/ShoppingClServlet?type=update" method="post">
	<table border=1 style="border-collapse:collapse;width:600px;">
		<tr><td>bookid</td><td>书名</td><td>价格</td><td>出版社</td><td>数量</td><td>删除</td></tr>
		<%
			//从request中取出要显示的商品信息
			ArrayList al = (ArrayList)request.getAttribute("bookList");
			for(int i=0; i<al.size(); i++)
			{
				Book book = (Book)al.get(i);
				%>
				<tr><td><%=book.getId() %><input type="hidden" name="id" value="<%=book.getId() %>"/></td>
				<td><%=book.getName() %></td><td><%=book.getPrice() %></td>
				<td><%=book.getPublishHouse() %></td>
				<td><input type="text" name="booknum" value="<%=book.getShoppingNum() %>"/>本</td>
				<td><a href="/MyShopping/ShoppingClServlet?type=del&id=<%=book.getId() %>">删除</a></td></tr>
				<%
			}
		%>
		
		<tr><td colspan="6"><input type="submit" value="update" /></td></tr>
		<tr><td colspan="6">购物车的总价格为：${totalPrice}元</td></tr>
	</table>
	</form>
	<a href="/MyShopping/GoMyOrderServlet">提交订单</a>
</body>
</html>
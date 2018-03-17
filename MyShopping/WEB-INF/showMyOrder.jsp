<%@ page language="java" import="java.util.*,com.zcy.domain.*" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
<script type="text/javascript">
<!--
	function goSubmitOrder()
	{
		window.location.href="/MyShopping/SubmitOrderServlet";
	}
</script>
</head>
<body>
	<h1>我的订单</h1>
	<h1>我的个人信息</h1>
	<table style="border-collapse: collapse" border="1">
	<tr><td colspan="2">用户个人信息</td></tr>
	<tr><td>用户名</td><td><%=((Users)session.getAttribute("loginUser")).getName() %></td></tr>
	<tr><td>电子邮件</td><td><%=((Users)session.getAttribute("loginUser")).getEmail() %></td></tr>
	<tr><td>用户级别</td><td><%=((Users)session.getAttribute("loginUser")).getGrade() %></td></tr>
	</table>
	<hr/>
	<table border="1">
	<tr><td>bookid</td><td>书名</td><td>价格</td><td>出版社</td><td>数量</td></tr>
	<%
		//循环显示购物车的商品信息
		ArrayList al = (ArrayList)request.getAttribute("orderinfo");
		for(int i=0; i<al.size(); i++)
		{
			Book book = (Book)al.get(i);
			%>
			<tr><td><%=book.getId() %></td><td><%=book.getName() %></td>
			<td><%=book.getPrice() %></td><td><%=book.getPublishHouse() %></td>
			<td><%=book.getShoppingNum() %></td></tr>
			<%
		}
	%>
	<tr><td colspan="5">总价格：<%=request.getAttribute("totalPrice") %></td></tr>
	</table>
	<input type="button" onclick="goSubmitOrder();" value="确认订单" />
</body>
</html>
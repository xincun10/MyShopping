package com.zcy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zcy.domain.Users;
import com.zcy.service.MyCart;
import com.zcy.service.OrderService;
import com.zcy.service.SendMail;

/**
 * 处理下订单的请求
 */
public class SubmitOrderServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		try
		{
			OrderService orderService = new OrderService();
			MyCart myCart = (MyCart) request.getSession().getAttribute("mycart");
			Users user = (Users) request.getSession().getAttribute("loginUser");
			orderService.submitOrder(myCart, user);
		}catch(Exception e)
		{
			System.out.println(e.getMessage());
			request.getRequestDispatcher("/WEB-INF/errInfo.jsp");
		}
		//如果订单写入到数据库，把邮件发送给客户
		//创建一个SendMail对象实例
		SendMail sendMail = new SendMail();
		Users user = (Users) request.getSession().getAttribute("loginUser");
		//发送邮件
		sendMail.sendToSomebody("你在xx网上订书了", "确认订单", user.getEmail());
		request.getRequestDispatcher("/WEB-INF/orderOk.jsp").forward(request, response);
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

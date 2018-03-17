package com.zcy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zcy.domain.Users;
import com.zcy.service.BookService;
import com.zcy.service.MyCart;
import com.zcy.service.UsersService;

/**
 * Servlet implementation class GoHallUI
 */
public class GoHallUI extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//先判断该用户是否已经登录，如果登录了的话，则直接跳转到购物大厅
		if(request.getSession().getAttribute("loginUser")!=null)
		{
			BookService bookService = new BookService();
			ArrayList al = bookService.getAllBook();
			//把显示的数据放入request,原因是因为request对象的声明最短
			request.setAttribute("books", al);
			request.getRequestDispatcher("/WEB-INF/hall.jsp").forward(request, response);
			return;
		}
		
		//得到从登录页面传递的用户名和密码
		String id = request.getParameter("id");
		String p = request.getParameter("passwd");
		//创建一个Users对象
		Users loginUser = new Users(Integer.parseInt(id),p);
		//使用业务逻辑类，完成验证
		UsersService usersService = new UsersService();
		if(usersService.checkUser(loginUser))
		{
			//说明是合法的用户，跳转到购物大厅
			//因为在其他的页面都可能使用到用户信息，因此我们可以
			//把用户信息存放在session
			request.getSession().setAttribute("loginUser", loginUser);
			//创建购物车
			MyCart myCart = new MyCart();
			//放在session里面
			request.getSession().setAttribute("mycart", myCart);
			
			//给下一个页面hall.jsp准备要显示的数据
			//模式开发在一定程度上约束了程序员自由
			BookService bookService = new BookService();
			ArrayList al = bookService.getAllBook();
			//把显示的数据放入request,原因是因为request对象的声明最短
			request.setAttribute("books", al);
			request.getRequestDispatcher("/WEB-INF/hall.jsp").forward(request, response);
		}
		else
		{
			//用户不合法
			request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

package com.zcy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zcy.service.MyCart;

/**
 * Servlet implementation class GoShowMyCart
 */
public class GoShowMyCart extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//什么时候创建购物车？（用户登录成功以后，创建一个购物车）
		//取出购物车，并添加书到购物车中
		MyCart myCart = (MyCart) request.getSession().getAttribute("mycart");
		
		//把要显示的数据放入request，准备显示
		request.setAttribute("bookList", myCart.showMyCart());
		request.setAttribute("totalPrice", myCart.getTotalPrice()+"");
		
		request.getRequestDispatcher("/WEB-INF/showMyCart.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

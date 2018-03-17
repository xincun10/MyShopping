package com.zcy.controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zcy.service.MyCart;

/**
 * 该servlet用于处理用户查看订单的请求
 */
public class GoMyOrderServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//得到购物车
		MyCart myCart = (MyCart) request.getSession().getAttribute("mycart");
		ArrayList al = myCart.showMyCart();
		float totalPrice = myCart.getTotalPrice();
		request.setAttribute("orderinfo", al);
		request.setAttribute("totalPrice", totalPrice);
		//跳到显示我的订单的页面
		request.getRequestDispatcher("/WEB-INF/showMyOrder.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}

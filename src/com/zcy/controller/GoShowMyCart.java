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
		
		//ʲôʱ�򴴽����ﳵ�����û���¼�ɹ��Ժ󣬴���һ�����ﳵ��
		//ȡ�����ﳵ��������鵽���ﳵ��
		MyCart myCart = (MyCart) request.getSession().getAttribute("mycart");
		
		//��Ҫ��ʾ�����ݷ���request��׼����ʾ
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

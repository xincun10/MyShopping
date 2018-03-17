package com.zcy.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zcy.domain.Book;
import com.zcy.service.BookService;
import com.zcy.service.MyCart;
//该控制器响应用户购买商品的请求
public class ShoppingClServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//接收type值，区分用户希望做什么，del,add,update...
		String type = request.getParameter("type");
		MyCart myCart = null;
		if(type.equals("del"))
		{
			//说明用户要删除商品
			//接受用户想要删除的商品id
			String id = request.getParameter("id");
			//取出购物车
			myCart = (MyCart) request.getSession().getAttribute("mycart");
			myCart.delBook(id);
			//把要显示的数据放入request，准备显示
			request.setAttribute("bookList", myCart.showMyCart());
			request.setAttribute("totalPrice", myCart.getTotalPrice()+"");
			//跳转到显示我的购物车去
			request.getRequestDispatcher("/WEB-INF/showMyCart.jsp").forward(request, response);
		}
		else if(type.equals("add"))
		{
			//说明用户希望添加商品到购物车
			//接收用户想购买的商品id
			String id = request.getParameter("id");
			//System.out.println("购买的书号是："+id);
			//什么时候创建购物车？（用户登录成功以后，创建一个购物车）
			//取出购物车，并添加书到购物车中
			myCart = (MyCart) request.getSession().getAttribute("mycart");
			myCart.addBook(id);
			
			//为了防止某个页面刷新，可以使用sendRedirect();
			response.sendRedirect("/MyShopping/GoShowMyCart");
		}
		else if(type.equals("update"))
		{
			//更新
			//得到用户希望更新的书号和数量
			String bookIds[] = request.getParameterValues("id");
			String bookNums[] = request.getParameterValues("booknum");
			//取出购物车，并添加书到购物车中
			myCart = (MyCart) request.getSession().getAttribute("mycart");
			for(int i=0; i<bookIds.length; i++)
			{
				//System.out.println(bookNums[i]);
				//更新整个购物车
				myCart.updateBook(bookIds[i], bookNums[i]);
			}
			//把要显示的数据放入request，准备显示
			request.setAttribute("bookList", myCart.showMyCart());
			request.setAttribute("totalPrice", myCart.getTotalPrice()+"");
			//跳转到显示我的购物车去
			request.getRequestDispatcher("/WEB-INF/showMyCart.jsp").forward(request, response);
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

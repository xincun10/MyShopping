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
//�ÿ�������Ӧ�û�������Ʒ������
public class ShoppingClServlet extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		//����typeֵ�������û�ϣ����ʲô��del,add,update...
		String type = request.getParameter("type");
		MyCart myCart = null;
		if(type.equals("del"))
		{
			//˵���û�Ҫɾ����Ʒ
			//�����û���Ҫɾ������Ʒid
			String id = request.getParameter("id");
			//ȡ�����ﳵ
			myCart = (MyCart) request.getSession().getAttribute("mycart");
			myCart.delBook(id);
			//��Ҫ��ʾ�����ݷ���request��׼����ʾ
			request.setAttribute("bookList", myCart.showMyCart());
			request.setAttribute("totalPrice", myCart.getTotalPrice()+"");
			//��ת����ʾ�ҵĹ��ﳵȥ
			request.getRequestDispatcher("/WEB-INF/showMyCart.jsp").forward(request, response);
		}
		else if(type.equals("add"))
		{
			//˵���û�ϣ�������Ʒ�����ﳵ
			//�����û��빺�����Ʒid
			String id = request.getParameter("id");
			//System.out.println("���������ǣ�"+id);
			//ʲôʱ�򴴽����ﳵ�����û���¼�ɹ��Ժ󣬴���һ�����ﳵ��
			//ȡ�����ﳵ��������鵽���ﳵ��
			myCart = (MyCart) request.getSession().getAttribute("mycart");
			myCart.addBook(id);
			
			//Ϊ�˷�ֹĳ��ҳ��ˢ�£�����ʹ��sendRedirect();
			response.sendRedirect("/MyShopping/GoShowMyCart");
		}
		else if(type.equals("update"))
		{
			//����
			//�õ��û�ϣ�����µ���ź�����
			String bookIds[] = request.getParameterValues("id");
			String bookNums[] = request.getParameterValues("booknum");
			//ȡ�����ﳵ��������鵽���ﳵ��
			myCart = (MyCart) request.getSession().getAttribute("mycart");
			for(int i=0; i<bookIds.length; i++)
			{
				//System.out.println(bookNums[i]);
				//�����������ﳵ
				myCart.updateBook(bookIds[i], bookNums[i]);
			}
			//��Ҫ��ʾ�����ݷ���request��׼����ʾ
			request.setAttribute("bookList", myCart.showMyCart());
			request.setAttribute("totalPrice", myCart.getTotalPrice()+"");
			//��ת����ʾ�ҵĹ��ﳵȥ
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

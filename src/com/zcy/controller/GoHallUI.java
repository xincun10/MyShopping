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
		
		//���жϸ��û��Ƿ��Ѿ���¼�������¼�˵Ļ�����ֱ����ת���������
		if(request.getSession().getAttribute("loginUser")!=null)
		{
			BookService bookService = new BookService();
			ArrayList al = bookService.getAllBook();
			//����ʾ�����ݷ���request,ԭ������Ϊrequest������������
			request.setAttribute("books", al);
			request.getRequestDispatcher("/WEB-INF/hall.jsp").forward(request, response);
			return;
		}
		
		//�õ��ӵ�¼ҳ�洫�ݵ��û���������
		String id = request.getParameter("id");
		String p = request.getParameter("passwd");
		//����һ��Users����
		Users loginUser = new Users(Integer.parseInt(id),p);
		//ʹ��ҵ���߼��࣬�����֤
		UsersService usersService = new UsersService();
		if(usersService.checkUser(loginUser))
		{
			//˵���ǺϷ����û�����ת���������
			//��Ϊ��������ҳ�涼����ʹ�õ��û���Ϣ��������ǿ���
			//���û���Ϣ�����session
			request.getSession().setAttribute("loginUser", loginUser);
			//�������ﳵ
			MyCart myCart = new MyCart();
			//����session����
			request.getSession().setAttribute("mycart", myCart);
			
			//����һ��ҳ��hall.jsp׼��Ҫ��ʾ������
			//ģʽ������һ���̶���Լ���˳���Ա����
			BookService bookService = new BookService();
			ArrayList al = bookService.getAllBook();
			//����ʾ�����ݷ���request,ԭ������Ϊrequest������������
			request.setAttribute("books", al);
			request.getRequestDispatcher("/WEB-INF/hall.jsp").forward(request, response);
		}
		else
		{
			//�û����Ϸ�
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

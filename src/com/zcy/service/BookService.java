package com.zcy.service;

import java.util.ArrayList;

import com.zcy.domain.Book;
import com.zcy.utils.SqlHelper;

/*����һ��ҵ���߼��࣬���ڴ�����book����ص�ҵ�񣬸�˾��ְ*/
public class BookService {
	//ͨ��id�õ�Book
	public Book getBookById(String id)
	{
		Book book = new Book();
		String sql = "select * from book where id=?";
		String para[] = {id};
		ArrayList al = new SqlHelper().executeQuery(sql, para);
		if(al.size()==1)
		{
			Object obj[] = (Object[]) al.get(0);
			book.setId(Integer.parseInt(obj[0].toString()));
			book.setName(obj[1].toString());
			book.setAuthor(obj[2].toString());
			book.setPublishHouse(obj[3].toString());
			book.setPrice(Float.parseFloat(obj[4].toString()));
			book.setNums(Integer.parseInt(obj[5].toString()));
		}
		return book;
	}
	//�õ����е��鼮��Ϣ����ҳ������
	public ArrayList getAllBook()
	{
		String sql = "select * from book where 1=?";
		String paras[] = {"1"};
		ArrayList al = new SqlHelper().executeQuery(sql, paras);
		ArrayList<Book> newAl = new ArrayList<Book>();
		//���η�װ
		for(int i=0; i<al.size(); i++)
		{
			Object obj[] = (Object[])al.get(i);
			Book book = new Book();
			book.setId(Integer.parseInt((obj[0].toString())));
			book.setName(obj[1].toString());
			book.setAuthor(obj[2].toString());
			book.setPublishHouse(obj[3].toString());
			book.setPrice(Float.parseFloat(obj[4].toString()));
			book.setNums(Integer.parseInt((obj[5].toString())));
			newAl.add(book);
		}
		
		return newAl;
	}
	
}

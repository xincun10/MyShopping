package com.zcy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.zcy.domain.Book;

//�����ʾ�ҵĹ��ﳵ
public class MyCart {
	HashMap<String, Book> hm = new HashMap<String, Book>();
	//�����
	public void addBook(String id)
	{
		if(hm.containsKey(id))
		{
			Book book = hm.get(id);
			//�Ȿ���Ѿ��������,shoppingNum������һ
			int shoppingNum = book.getShoppingNum();
			book.setShoppingNum(shoppingNum+1);
		}
		else
			hm.put(id, new BookService().getBookById(id));
	}
	//ɾ����
	public void delBook(String id)
	{
		hm.remove(id);
	}
	//������(����)
	public void updateBook(String id, String nums)
	{
		Book book = hm.get(id);
		book.setShoppingNum(Integer.parseInt(nums));
	}
	
	//��ʾ���ﳵ��������Ʒ��Ϣ
	public ArrayList showMyCart()
	{
		ArrayList<Book> al = new ArrayList<Book>();
		//����hashmap
		Iterator iterator = hm.keySet().iterator();
		while(iterator.hasNext())
		{
			//ȡ��key
			String id = (String) iterator.next();
			//ȡ��Book
			Book book = hm.get(id);
			al.add(book);
		}
		return al;
	}
	
	//���ظù��ﳵ���ܼ�
	public float getTotalPrice()
	{
		//�õ��ܼ۸�
		ArrayList<Book> al = new ArrayList<Book>();
		Iterator iterator = hm.keySet().iterator();
		float totalPrice = 0.0f;
		while(iterator.hasNext())
		{
			//ȡ�����
			String bookId = (String)iterator.next();
			//ȡ����Ŷ�Ӧ��book
			Book book = hm.get(bookId);
			totalPrice += book.getPrice()*book.getShoppingNum();
		}
		return totalPrice;
	}
	
	//��չ��ﳵ
	public void clearBook()
	{
		hm.clear();
	}
}

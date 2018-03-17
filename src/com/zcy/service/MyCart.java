package com.zcy.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import com.zcy.domain.Book;

//这个表示我的购物车
public class MyCart {
	HashMap<String, Book> hm = new HashMap<String, Book>();
	//添加书
	public void addBook(String id)
	{
		if(hm.containsKey(id))
		{
			Book book = hm.get(id);
			//这本书已经购买过了,shoppingNum数量加一
			int shoppingNum = book.getShoppingNum();
			book.setShoppingNum(shoppingNum+1);
		}
		else
			hm.put(id, new BookService().getBookById(id));
	}
	//删除书
	public void delBook(String id)
	{
		hm.remove(id);
	}
	//更新书(数量)
	public void updateBook(String id, String nums)
	{
		Book book = hm.get(id);
		book.setShoppingNum(Integer.parseInt(nums));
	}
	
	//显示购物车中所有商品信息
	public ArrayList showMyCart()
	{
		ArrayList<Book> al = new ArrayList<Book>();
		//遍历hashmap
		Iterator iterator = hm.keySet().iterator();
		while(iterator.hasNext())
		{
			//取出key
			String id = (String) iterator.next();
			//取出Book
			Book book = hm.get(id);
			al.add(book);
		}
		return al;
	}
	
	//返回该购物车的总价
	public float getTotalPrice()
	{
		//得到总价格
		ArrayList<Book> al = new ArrayList<Book>();
		Iterator iterator = hm.keySet().iterator();
		float totalPrice = 0.0f;
		while(iterator.hasNext())
		{
			//取出书号
			String bookId = (String)iterator.next();
			//取出书号对应的book
			Book book = hm.get(bookId);
			totalPrice += book.getPrice()*book.getShoppingNum();
		}
		return totalPrice;
	}
	
	//清空购物车
	public void clearBook()
	{
		hm.clear();
	}
}

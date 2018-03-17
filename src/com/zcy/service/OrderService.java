package com.zcy.service;

import com.zcy.domain.Book;
import com.zcy.domain.Users;
import com.zcy.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;

//处理与订单相关的业务逻辑
public class OrderService{
	private Connection ct = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	//下订单涉及到两张表，而且两张表有关系
	public void submitOrder(MyCart myCart, Users user)
	{
		String sql = "insert into orders values (null,?,?,null)";
		//因为添加订单复杂，所以这种操作很特别，于是我们就不适用SqlHelper,
		//而是专门针对下订单写对数据库的操作
		try
		{
			ct = DBUtil.getConnection();
			//为了保证我们的订单号是稳定的，所以将其事务隔离级别升级（可串行）
			ct.setAutoCommit(false);
			ct.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			ps = ct.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ps.setFloat(2, myCart.getTotalPrice());
			ps.executeUpdate();
			//如何得到刚刚插入的订单记录的订单号！！
			sql = "select last_insert_id() from orders";
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			int orderId = 0;
			if(rs.next())
			{
				//取出刚刚生成的订单号
				orderId = rs.getInt(1);
			}
			//把订单细节表生成【批量提交！】
			ArrayList al = myCart.showMyCart();
			for(int i=0; i<al.size(); i++)
			{
				Book book = (Book)al.get(i);
				sql = "insert into ordersitem values (null,?,?,?)";
				ps = ct.prepareStatement(sql);
				ps.setInt(1, orderId);
				ps.setInt(2, book.getId());
				ps.setInt(3, book.getShoppingNum());
				ps.executeUpdate();
			}
			//整体提交
			ct.commit();
		}
		catch(Exception e)
		{
			try
			{
				ct.rollback();
			}catch(SQLException e1)
			{
				e1.printStackTrace();
			}
			throw new RuntimeException(e.getMessage());
		}
		finally
		{
			DBUtil.close(rs, ps, ct);
		}
		
	}
}

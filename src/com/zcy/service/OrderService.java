package com.zcy.service;

import com.zcy.domain.Book;
import com.zcy.domain.Users;
import com.zcy.utils.DBUtil;

import java.sql.*;
import java.util.ArrayList;

//�����붩����ص�ҵ���߼�
public class OrderService{
	private Connection ct = null;
	private PreparedStatement ps = null;
	private ResultSet rs = null;
	//�¶����漰�����ű��������ű��й�ϵ
	public void submitOrder(MyCart myCart, Users user)
	{
		String sql = "insert into orders values (null,?,?,null)";
		//��Ϊ��Ӷ������ӣ��������ֲ������ر��������ǾͲ�����SqlHelper,
		//����ר������¶���д�����ݿ�Ĳ���
		try
		{
			ct = DBUtil.getConnection();
			//Ϊ�˱�֤���ǵĶ��������ȶ��ģ����Խ���������뼶���������ɴ��У�
			ct.setAutoCommit(false);
			ct.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE);
			
			ps = ct.prepareStatement(sql);
			ps.setInt(1, user.getId());
			ps.setFloat(2, myCart.getTotalPrice());
			ps.executeUpdate();
			//��εõ��ող���Ķ�����¼�Ķ����ţ���
			sql = "select last_insert_id() from orders";
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			int orderId = 0;
			if(rs.next())
			{
				//ȡ���ո����ɵĶ�����
				orderId = rs.getInt(1);
			}
			//�Ѷ���ϸ�ڱ����ɡ������ύ����
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
			//�����ύ
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

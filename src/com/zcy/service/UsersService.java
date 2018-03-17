package com.zcy.service;

import java.util.ArrayList;

import com.zcy.domain.Users;
import com.zcy.utils.SqlHelper;

//这是专门用于处理业务逻辑的类
//处理和users表相关的业务逻辑
public class UsersService {

	//验证用户是否合法的方法
	public boolean checkUser(Users user)
	{
		//到数据库去验证
		String sql = "select * from users where id=? and pwd=?";
		String paras[] = {user.getId()+"", user.getPwd()};
		ArrayList al = new SqlHelper().executeQuery(sql, paras);
		if(al.size()==0)
		{
			return false;
		}
		else
		{
			Object[] objects = (Object[]) al.get(0);
			//把对象数组封装到Users对象
			user.setName((String)objects[1]);//数据库中的第二个字段
			user.setEmail((String)objects[3]);
			user.setGrade(Integer.parseInt(objects[5].toString()));
			//user.setGrade((Integer)objects[5]);
			return true;
		}
	}
}

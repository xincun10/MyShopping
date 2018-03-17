package com.zcy.service;

import java.util.ArrayList;

import com.zcy.domain.Users;
import com.zcy.utils.SqlHelper;

//����ר�����ڴ���ҵ���߼�����
//�����users����ص�ҵ���߼�
public class UsersService {

	//��֤�û��Ƿ�Ϸ��ķ���
	public boolean checkUser(Users user)
	{
		//�����ݿ�ȥ��֤
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
			//�Ѷ��������װ��Users����
			user.setName((String)objects[1]);//���ݿ��еĵڶ����ֶ�
			user.setEmail((String)objects[3]);
			user.setGrade(Integer.parseInt(objects[5].toString()));
			//user.setGrade((Integer)objects[5]);
			return true;
		}
	}
}

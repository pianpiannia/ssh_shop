package com.jun.shop.user.service;

import com.jun.shop.user.dao.UserDao;
import com.jun.shop.user.vo.User;
import com.jun.shop.utils.UUIDUtils;

public class UserService {
	//ע��UserDao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	//���û�����ѯ�û��ķ���
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}

	//ҵ�������û�ע��ķ���
	public void save(User user) {
		user.setState(1);//0�����û�δ��� 1�����û��Ѿ�����
		//����uuid��Ϊ������������ݿ�
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
	}

	//�û���¼�ķ���
	public User login(User user) {
		return userDao.login(user);
	}
}

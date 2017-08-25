package com.jun.shop.user.service;

import com.jun.shop.user.dao.UserDao;
import com.jun.shop.user.vo.User;
import com.jun.shop.utils.UUIDUtils;

public class UserService {
	//注入UserDao
	private UserDao userDao;

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	//按用户名查询用户的方法
	public User findByUsername(String username){
		return userDao.findByUsername(username);
	}

	//业务层完成用户注册的方法
	public void save(User user) {
		user.setState(1);//0代表用户未激活。 1代表用户已经激活
		//产生uuid作为激活码存入数据库
		String code = UUIDUtils.getUUID()+UUIDUtils.getUUID();
		user.setCode(code);
		userDao.save(user);
	}

	//用户登录的方法
	public User login(User user) {
		return userDao.login(user);
	}
}

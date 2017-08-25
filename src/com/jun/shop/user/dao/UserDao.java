package com.jun.shop.user.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jun.shop.user.vo.User;

/**
 * �û�ģ��־ò����
 * @author jun
 *
 */
public class UserDao extends HibernateDaoSupport{
	
	//�������ֲ�ѯ�Ƿ��и��û�
	public User findByUsername(String username){
		String hql = "from User where username=?";
		List<User> list = this.getHibernateTemplate().find(hql,username);
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		return null;
	}

	//ע���û��������ݿ����ʵ��
	public void save(User user) {
		this.getHibernateTemplate().save(user);
	}

	//�û���¼�ķ���
	public User login(User user) {
		String hql = "from User where username=? and password =? and state = ?";
		List<User> list = this.getHibernateTemplate().find(hql,user.getUsername(),user.getPassword(),1);
		if(list !=null && list.size()>0){
			return list.get(0);
		}
		
		return null;
	}
}
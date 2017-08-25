package com.jun.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jun.shop.category.vo.Category;

public class CategoryDao extends HibernateDaoSupport{

	//dao���ѯ���з���ķ���
	public List<Category> findAll() {
		String sql = "from Category";
		List<Category> list = this.getHibernateTemplate().find(sql);
		return list;
	}

	//dao�㱣��һ������ķ���
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}

	//��ѯһ������
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	//ɾ��һ������
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}
	
}

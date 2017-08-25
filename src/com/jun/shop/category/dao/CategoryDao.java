package com.jun.shop.category.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jun.shop.category.vo.Category;

public class CategoryDao extends HibernateDaoSupport{

	//dao层查询所有分类的方法
	public List<Category> findAll() {
		String sql = "from Category";
		List<Category> list = this.getHibernateTemplate().find(sql);
		return list;
	}

	//dao层保存一级分类的方法
	public void save(Category category) {
		this.getHibernateTemplate().save(category);
	}

	//查询一级分类
	public Category findByCid(Integer cid) {
		return this.getHibernateTemplate().get(Category.class, cid);
	}

	//删除一级分类
	public void delete(Category category) {
		this.getHibernateTemplate().delete(category);
	}

	public void update(Category category) {
		this.getHibernateTemplate().update(category);
	}
	
}

package com.jun.shop.utils;

import java.lang.reflect.Parameter;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

public class BaseDaoImple<T> extends HibernateDaoSupport implements BaseDao<T>{
	private Class tclass;
	public BaseDaoImple(){
		Class clazz = this.getClass();
		Type type = clazz.getGenericSuperclass();
		ParameterizedType ptype = (ParameterizedType) type;
		Type[] t = ptype.getActualTypeArguments();
		Class pclass = (Class) t[0];
		this.tclass = pclass;
	}
	@Override
	public void add(T t) {
		this.getHibernateTemplate().save(t);
	}

	@Override
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	@Override
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	@Override
	public T findOne(int id) {
		this.getHibernateTemplate().get(tclass, id);
		return null;
	}

	@Override
	public List<T> findAll() {
		
		this.getHibernateTemplate().find("from"+tclass.getSimpleName());
		return null;
	}

}

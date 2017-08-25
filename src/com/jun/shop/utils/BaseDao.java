package com.jun.shop.utils;

import java.util.List;

public interface BaseDao<T> {
	void add(T t);
	void delete(T t);
	void update(T t);
	T findOne(int id);
	List<T> findAll();
}

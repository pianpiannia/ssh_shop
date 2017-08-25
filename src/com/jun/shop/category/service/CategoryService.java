package com.jun.shop.category.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jun.shop.category.dao.CategoryDao;
import com.jun.shop.category.vo.Category;
/**
 * 一级分类的业务层
 * @author jun
 *
 */
@Transactional
public class CategoryService {
	private CategoryDao categoryDao;

	public void setCategoryDao(CategoryDao categoryDao) {
		this.categoryDao = categoryDao;
	}

	//查询所有一级分类方法的实现
	public List<Category> findAll() {
		return categoryDao.findAll();
	}

	public void save(Category category) {
		// TODO Auto-generated method stub
		categoryDao.save(category);
	}

	public Category findByCid(Integer cid) {
		return categoryDao.findByCid(cid);
	}

	public void delete(Category category) {
		categoryDao.delete(category);
	}

	public void update(Category category) {
		categoryDao.update(category);
	}
	
}

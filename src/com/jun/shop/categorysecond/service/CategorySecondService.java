package com.jun.shop.categorysecond.service;

import java.util.List;

import com.jun.shop.categorysecond.dao.CategorySecondDao;
import com.jun.shop.categorysecond.vo.CategorySecond;
import com.jun.shop.utils.PageBean;

public class CategorySecondService {
	//注入二级分类dao
	private CategorySecondDao categorySecondDao;

	public void setCategorySecondDao(CategorySecondDao categorySecondDao) {
		this.categorySecondDao = categorySecondDao;
	}

	//分页查询二级分类的方法
	public PageBean<CategorySecond> findByPage(Integer page) {
		PageBean<CategorySecond> pageBean = new PageBean<CategorySecond>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页记录数
		int limit = 10;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = categorySecondDao.findCount();
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit +1;
		}
		pageBean.setTotalPage(totalPage);
		//设置分页显示数据的集合
		int begin = (page-1)*limit;
		List<CategorySecond> list = categorySecondDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	public void save(CategorySecond categorySecond) {
		categorySecondDao.save(categorySecond);
	}

	public CategorySecond findByCsid(Integer csid) {
		return categorySecondDao.findByCsid(csid);
	}

	public void delete(CategorySecond categorySecond) {
		categorySecondDao.delete(categorySecond);
	}

	public void update(CategorySecond categorySecond) {
		categorySecondDao.update(categorySecond);
	}

	public List<CategorySecond> findAll() {
		// TODO Auto-generated method stub
		return categorySecondDao.findAll();
	}

	
}

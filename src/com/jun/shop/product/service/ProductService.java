package com.jun.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jun.shop.product.dao.ProductDao;
import com.jun.shop.product.vo.Product;
import com.jun.shop.utils.PageBean;
/**
 * 商品业务层代码
 * @author jun
 *
 */
@Transactional
public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	//首页热门商品的查询
	public List<Product> findHot() {
		return productDao.findHot();
	}

	public List<Product> findNew() {
		return productDao.findNew();
	}

	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}

	//根据一级分类cid带有分页查询商品
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示的记录数
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		//Math.ceil(totalCount/limit);
		if(totalCount % limit ==0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//每页显示数据的集合
		//从哪开始
		int begin = (page-1)*limit;
		List<Product> list = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//根据二级分类查询分类商品的信息
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示的记录数
		int limit = 8;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		//Math.ceil(totalCount/limit);
		if(totalCount % limit ==0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//每页显示数据的集合
		//从哪开始
		int begin = (page-1)*limit;
		List<Product> list = productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//业务层查询商品带分页的方法
	public PageBean<Product> findByPage(Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//设置当前页数
		pageBean.setPage(page);
		//设置每页显示的记录数
		int limit = 10;
		pageBean.setLimit(limit);
		//设置总记录数
		int totalCount = 0;
		totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		//设置总页数
		int totalPage = 0;
		//Math.ceil(totalCount/limit);
		if(totalCount % limit ==0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//每页显示数据的集合
		//从哪开始
		int begin = (page-1)*limit;
		List<Product> list = productDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//业务层保存商品的方法
	public void save(Product product) {
		productDao.save(product);
	}

	public void delete(Product product) {
		productDao.delete(product);
	}
	
}

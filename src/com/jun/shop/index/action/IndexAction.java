package com.jun.shop.index.action;

import java.util.List;

import com.jun.shop.category.service.CategoryService;
import com.jun.shop.category.vo.Category;
import com.jun.shop.product.service.ProductService;
import com.jun.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
	//注入一级分类的service
	private CategoryService categoryService;
	//注入商品的service
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * 访问首页的方法
	 */
	public String execute(){
		//查询所有一级分类集合
		List<Category> cList = categoryService.findAll();
		//将一级分类存入session范围
		ActionContext.getContext().getSession().put("cList", cList);
		
		// 查询热门商品:
		List<Product> hList = productService.findHot();
		// 保存到值栈中:
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		//查询最新商品
		List<Product> nList = productService.findNew();
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
}

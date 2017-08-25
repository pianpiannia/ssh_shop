package com.jun.shop.index.action;

import java.util.List;

import com.jun.shop.category.service.CategoryService;
import com.jun.shop.category.vo.Category;
import com.jun.shop.product.service.ProductService;
import com.jun.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;

public class IndexAction extends ActionSupport {
	//ע��һ�������service
	private CategoryService categoryService;
	//ע����Ʒ��service
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	/**
	 * ������ҳ�ķ���
	 */
	public String execute(){
		//��ѯ����һ�����༯��
		List<Category> cList = categoryService.findAll();
		//��һ���������session��Χ
		ActionContext.getContext().getSession().put("cList", cList);
		
		// ��ѯ������Ʒ:
		List<Product> hList = productService.findHot();
		// ���浽ֵջ��:
		ActionContext.getContext().getValueStack().set("hList", hList);
		
		//��ѯ������Ʒ
		List<Product> nList = productService.findNew();
		ActionContext.getContext().getValueStack().set("nList", nList);
		return "index";
	}
}

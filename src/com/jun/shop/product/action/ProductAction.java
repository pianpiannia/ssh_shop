package com.jun.shop.product.action;

import java.util.List;

import com.jun.shop.category.service.CategoryService;
import com.jun.shop.category.vo.Category;
import com.jun.shop.product.service.ProductService;
import com.jun.shop.product.vo.Product;
import com.jun.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class ProductAction extends ActionSupport implements ModelDriven<Product>{
	//用于接受数据的模型驱动
	private Product product = new Product();
	//注入商品service
	private ProductService productService;
	//接受分类的cid
	private Integer cid;
	//注入一级分类的service
	private CategoryService categoryService;
	//接受当前页数
	private int page;
	//接收二级分类的id
	private Integer csid;
	public Integer getCsid() {
		return csid;
	}

	public void setCsid(Integer csid) {
		this.csid = csid;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	public void setCid(Integer cid) {
		this.cid = cid;
	}

	public Integer getCid() {
		return cid;
	}

	public void setProductService(ProductService productService) {
		this.productService = productService;
	}

	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}

	//根据商品的ID进行查询商品
	public String findByPid(){
		//调用service的方法
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
	
	//根据分类id查询商品
	public String findByCid(){
		//List<Category> cList = categoryService.findAll();
		PageBean<Product> pageBean = productService.findByPageCid(cid,page);//根据一级分类查询商品，带分页查询
		//将pageBean存入值栈
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	//根据二级分类id查询商品
	public String findByCsid(){
		PageBean<Product> pageBean = productService.findByPageCsid(csid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}

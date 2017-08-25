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
	//���ڽ������ݵ�ģ������
	private Product product = new Product();
	//ע����Ʒservice
	private ProductService productService;
	//���ܷ����cid
	private Integer cid;
	//ע��һ�������service
	private CategoryService categoryService;
	//���ܵ�ǰҳ��
	private int page;
	//���ն��������id
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

	//������Ʒ��ID���в�ѯ��Ʒ
	public String findByPid(){
		//����service�ķ���
		product = productService.findByPid(product.getPid());
		return "findByPid";
	}
	
	//���ݷ���id��ѯ��Ʒ
	public String findByCid(){
		//List<Category> cList = categoryService.findAll();
		PageBean<Product> pageBean = productService.findByPageCid(cid,page);//����һ�������ѯ��Ʒ������ҳ��ѯ
		//��pageBean����ֵջ
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCid";
	}
	
	//���ݶ�������id��ѯ��Ʒ
	public String findByCsid(){
		PageBean<Product> pageBean = productService.findByPageCsid(csid,page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByCsid";
	}
}

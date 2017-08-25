package com.jun.shop.product.adminaction;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;

import com.jun.shop.categorysecond.service.CategorySecondService;
import com.jun.shop.categorysecond.vo.CategorySecond;
import com.jun.shop.product.service.ProductService;
import com.jun.shop.product.vo.Product;
import com.jun.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminProductAction extends ActionSupport implements ModelDriven<Product>{
	private Product product = new Product();
	@Override
	public Product getModel() {
		// TODO Auto-generated method stub
		return product;
	}
	//注入service
	private ProductService productService;
	//接受page参数
	private Integer page;
	//注入二级分类的service
	private CategorySecondService categorySecondService;
	//文件上传需要的参数
	private File upload;//上传的文件
	private String uploadFileName;//文件上传的文件名
	private String uploadContextType;//接收文件上传的文件的MIME的类型
	public void setUpload(File upload) {
		this.upload = upload;
	}
	public void setUploadFileName(String uploadFileName) {
		this.uploadFileName = uploadFileName;
	}
	public void setUploadContextType(String uploadContextType) {
		this.uploadContextType = uploadContextType;
	}
	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}
	public void setPage(Integer page) {
		this.page = page;
	}
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	
	//带分页的商品查询
	public String findAll(){
		PageBean<Product> pageBean = productService.findByPage(page);
		//将数据传递到页面
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		//页面跳转
		return "findAll";
	}
	
	//跳转到添加页面
	public String addPage(){
		//查询所有的二级分类的集合
		List<CategorySecond> csList = categorySecondService.findAll();
		//通过值栈保存数据
		ActionContext.getContext().getValueStack().set("csList", csList);
		//页面的跳转
		return "addPageSuccess";
	}
	
	//保存商品的方法
	public String save() throws IOException{
		//调用service
		//product.setPdate(new Date());
		if(upload != null){
			//获得文件上传的磁盘绝对路径
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			//创建一个文件
			File diskFile = new File(realPath+"//"+uploadFileName);
			//文件上传
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/"+uploadFileName);
		}
		//将数据保存到数据库
		productService.save(product);
		return "saveSuccess";
	}
	
	//删除的方法
	public String delete(){
		//先查询在删除
		product = productService.findByPid(product.getPid());
		//删除上传的图片
		String path = product.getImage();
		if(path != null){
			ServletActionContext.getServletContext().getRealPath("/"+path);
			File file = new File(path);
			file.delete();
		}
		//删除商品
		productService.delete(product);
		
		return "deleteSuccess";		
	}
	
	//编辑商品的方法
	public String edit(){
		//根据商品id查询
		product = productService.findByPid(product.getPid());
		//查询二级分类
		List<CategorySecond> csList = categorySecondService.findAll();
		//数据保存到值栈
		ActionContext.getContext().getValueStack().set("csList", csList);
		//页面跳转
		return "editSuccess";
	}
}

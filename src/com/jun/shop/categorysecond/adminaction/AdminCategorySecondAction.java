package com.jun.shop.categorysecond.adminaction;

import java.util.List;

import com.jun.shop.category.service.CategoryService;
import com.jun.shop.category.vo.Category;
import com.jun.shop.categorysecond.service.CategorySecondService;
import com.jun.shop.categorysecond.vo.CategorySecond;
import com.jun.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategorySecondAction extends ActionSupport implements ModelDriven<CategorySecond>{

	private CategorySecond categorySecond = new CategorySecond();
	@Override
	public CategorySecond getModel() {
		return categorySecond;
	}

	//注入二级分类service
	private CategorySecondService categorySecondService;
	//接受page
	private Integer page;
	//注入一级分类service
	private CategoryService categoryService;
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}


	public void setPage(Integer page) {
		this.page = page;
	}


	public void setCategorySecondService(CategorySecondService categorySecondService) {
		this.categorySecondService = categorySecondService;
	}

	
	//查询二级分类分页的方法
	public String findAll(){
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//跳转到添加页面
	public String addPage(){
		//查询所有一级分类
		List<Category> cList = categoryService.findAll();
		//把数据显示到页面下拉列表中
		ActionContext.getContext().getValueStack().set("cList", cList);
		//跳转页面
		return "addPageSuccess";
	}
	
	//保存二级分类的方法
	public String save(){
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	
	//删除二级分类的方法
	public String delete(){
		//如果级联删除，先查询再删除，配置cascade
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	
	//编辑二级分类的方法
	public String edit(){
		//根据二级分类id查询二级分类对象
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		//查询所有一级分类
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	
	//修改二级分类的方法
	public String update(){
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}

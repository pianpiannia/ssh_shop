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

	//ע���������service
	private CategorySecondService categorySecondService;
	//����page
	private Integer page;
	//ע��һ������service
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

	
	//��ѯ���������ҳ�ķ���
	public String findAll(){
		PageBean<CategorySecond> pageBean = categorySecondService.findByPage(page);
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findAll";
	}
	
	//��ת�����ҳ��
	public String addPage(){
		//��ѯ����һ������
		List<Category> cList = categoryService.findAll();
		//��������ʾ��ҳ�������б���
		ActionContext.getContext().getValueStack().set("cList", cList);
		//��תҳ��
		return "addPageSuccess";
	}
	
	//�����������ķ���
	public String save(){
		categorySecondService.save(categorySecond);
		return "saveSuccess";
	}
	
	//ɾ����������ķ���
	public String delete(){
		//�������ɾ�����Ȳ�ѯ��ɾ��������cascade
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		categorySecondService.delete(categorySecond);
		return "deleteSuccess";
	}
	
	//�༭��������ķ���
	public String edit(){
		//���ݶ�������id��ѯ�����������
		categorySecond = categorySecondService.findByCsid(categorySecond.getCsid());
		//��ѯ����һ������
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "editSuccess";
	}
	
	//�޸Ķ�������ķ���
	public String update(){
		categorySecondService.update(categorySecond);
		return "updateSuccess";
	}
}

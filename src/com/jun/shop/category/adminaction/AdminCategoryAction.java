package com.jun.shop.category.adminaction;

import java.util.List;

import com.jun.shop.category.service.CategoryService;
import com.jun.shop.category.vo.Category;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

public class AdminCategoryAction extends ActionSupport implements ModelDriven<Category>{
	private Category category = new Category();
	@Override
	public Category getModel() {
		// TODO Auto-generated method stub
		return category;
	}
	
	//ע��һ������service
	private CategoryService categoryService;
	
	public void setCategoryService(CategoryService categoryService) {
		this.categoryService = categoryService;
	}

	//��ִ̨�в�ѯ����һ������ķ���
	public String findAll(){
		//��ѯ����һ������
		List<Category> cList = categoryService.findAll();
		ActionContext.getContext().getValueStack().set("cList", cList);
		return "findAll";
	}
	
	//��̨����һ������ķ���
	public String save(){
		//����service���б���
		categoryService.save(category);
		return "saveSuccess";
	}

	//��̨ɾ��һ������ķ���
	public String delete(){
		//����cid��ʹ��ģ��������ɾ��һ�����࣬ͬʱɾ���������ࡣ�����ȸ���id��ѯ�ٽ���ɾ����
		category = categoryService.findByCid(category.getCid());
		//ɾ��
		categoryService.delete(category);
		//ҳ����ת
		return "deleteSuccess";
	}
	
	//��̨�༭һ������ķ���
	public String edit(){
		//�Ȳ�ѯ
		category = categoryService.findByCid(category.getCid());
		//ҳ����ת
		return "editSuccess";
	}
	//��̨�޸�һ������ķ���
	public String update(){
		categoryService.update(category);
		//ҳ����ת
		return "updateSuccess";
	}
}

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
	//ע��service
	private ProductService productService;
	//����page����
	private Integer page;
	//ע����������service
	private CategorySecondService categorySecondService;
	//�ļ��ϴ���Ҫ�Ĳ���
	private File upload;//�ϴ����ļ�
	private String uploadFileName;//�ļ��ϴ����ļ���
	private String uploadContextType;//�����ļ��ϴ����ļ���MIME������
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
	
	//����ҳ����Ʒ��ѯ
	public String findAll(){
		PageBean<Product> pageBean = productService.findByPage(page);
		//�����ݴ��ݵ�ҳ��
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		//ҳ����ת
		return "findAll";
	}
	
	//��ת�����ҳ��
	public String addPage(){
		//��ѯ���еĶ�������ļ���
		List<CategorySecond> csList = categorySecondService.findAll();
		//ͨ��ֵջ��������
		ActionContext.getContext().getValueStack().set("csList", csList);
		//ҳ�����ת
		return "addPageSuccess";
	}
	
	//������Ʒ�ķ���
	public String save() throws IOException{
		//����service
		//product.setPdate(new Date());
		if(upload != null){
			//����ļ��ϴ��Ĵ��̾���·��
			String realPath = ServletActionContext.getServletContext().getRealPath("/products");
			//����һ���ļ�
			File diskFile = new File(realPath+"//"+uploadFileName);
			//�ļ��ϴ�
			FileUtils.copyFile(upload, diskFile);
			product.setImage("products/"+uploadFileName);
		}
		//�����ݱ��浽���ݿ�
		productService.save(product);
		return "saveSuccess";
	}
	
	//ɾ���ķ���
	public String delete(){
		//�Ȳ�ѯ��ɾ��
		product = productService.findByPid(product.getPid());
		//ɾ���ϴ���ͼƬ
		String path = product.getImage();
		if(path != null){
			ServletActionContext.getServletContext().getRealPath("/"+path);
			File file = new File(path);
			file.delete();
		}
		//ɾ����Ʒ
		productService.delete(product);
		
		return "deleteSuccess";		
	}
	
	//�༭��Ʒ�ķ���
	public String edit(){
		//������Ʒid��ѯ
		product = productService.findByPid(product.getPid());
		//��ѯ��������
		List<CategorySecond> csList = categorySecondService.findAll();
		//���ݱ��浽ֵջ
		ActionContext.getContext().getValueStack().set("csList", csList);
		//ҳ����ת
		return "editSuccess";
	}
}

package com.jun.shop.product.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jun.shop.product.dao.ProductDao;
import com.jun.shop.product.vo.Product;
import com.jun.shop.utils.PageBean;
/**
 * ��Ʒҵ������
 * @author jun
 *
 */
@Transactional
public class ProductService {
	private ProductDao productDao;

	public void setProductDao(ProductDao productDao) {
		this.productDao = productDao;
	}

	//��ҳ������Ʒ�Ĳ�ѯ
	public List<Product> findHot() {
		return productDao.findHot();
	}

	public List<Product> findNew() {
		return productDao.findNew();
	}

	public Product findByPid(Integer pid) {
		return productDao.findByPid(pid);
	}

	//����һ������cid���з�ҳ��ѯ��Ʒ
	public PageBean<Product> findByPageCid(Integer cid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿҳ��ʾ�ļ�¼��
		int limit = 8;
		pageBean.setLimit(limit);
		//�����ܼ�¼��
		int totalCount = 0;
		totalCount = productDao.findCountCid(cid);
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage = 0;
		//Math.ceil(totalCount/limit);
		if(totalCount % limit ==0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//ÿҳ��ʾ���ݵļ���
		//���Ŀ�ʼ
		int begin = (page-1)*limit;
		List<Product> list = productDao.findByPageCid(cid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//���ݶ��������ѯ������Ʒ����Ϣ
	public PageBean<Product> findByPageCsid(Integer csid, int page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿҳ��ʾ�ļ�¼��
		int limit = 8;
		pageBean.setLimit(limit);
		//�����ܼ�¼��
		int totalCount = 0;
		totalCount = productDao.findCountCsid(csid);
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage = 0;
		//Math.ceil(totalCount/limit);
		if(totalCount % limit ==0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//ÿҳ��ʾ���ݵļ���
		//���Ŀ�ʼ
		int begin = (page-1)*limit;
		List<Product> list = productDao.findByPageCsid(csid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//ҵ����ѯ��Ʒ����ҳ�ķ���
	public PageBean<Product> findByPage(Integer page) {
		PageBean<Product> pageBean = new PageBean<Product>();
		//���õ�ǰҳ��
		pageBean.setPage(page);
		//����ÿҳ��ʾ�ļ�¼��
		int limit = 10;
		pageBean.setLimit(limit);
		//�����ܼ�¼��
		int totalCount = 0;
		totalCount = productDao.findCount();
		pageBean.setTotalCount(totalCount);
		//������ҳ��
		int totalPage = 0;
		//Math.ceil(totalCount/limit);
		if(totalCount % limit ==0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit+1;
		}
		pageBean.setTotalPage(totalPage);
		//ÿҳ��ʾ���ݵļ���
		//���Ŀ�ʼ
		int begin = (page-1)*limit;
		List<Product> list = productDao.findByPage(begin,limit);
		pageBean.setList(list);
		return pageBean;
	}

	//ҵ��㱣����Ʒ�ķ���
	public void save(Product product) {
		productDao.save(product);
	}

	public void delete(Product product) {
		productDao.delete(product);
	}
	
}

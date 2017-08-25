package com.jun.shop.order.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import com.jun.shop.order.dao.OrderDao;
import com.jun.shop.order.vo.Order;
import com.jun.shop.utils.PageBean;

@Transactional
public class OrderService {
	private OrderDao orderDao;

	public void setOrderDao(OrderDao orderDao) {
		this.orderDao = orderDao;
	}

	//service�㱣�涩���ķ���
	public void save(Order order) {
		orderDao.save(order);
	}

	//�ҵĶ�����ҵ���Ĵ���
	public PageBean<Order> findByPageUid(Integer uid, Integer page) {
		PageBean<Order> pageBean = new PageBean<Order>();
		// ���õ�ǰҳ��:
		pageBean.setPage(page);
		// ����ÿҳ��ʾ��¼��:
		// ��ʾ5��
		int limit = 5;
		pageBean.setLimit(limit);
		// �����ܼ�¼��:
		int totalCount = 0;
		totalCount = orderDao.findByCountUid(uid);
		pageBean.setTotalCount(totalCount);
		// ������ҳ��
		int totalPage = 0;
		if(totalCount % limit == 0){
			totalPage = totalCount / limit;
		}else{
			totalPage = totalCount / limit + 1;
		}
		pageBean.setTotalPage(totalPage);
		// ����ÿҳ��ʾ���ݼ���:
		int begin = (page - 1)*limit;
		List<Order> list = orderDao.findByPageUid(uid,begin,limit);
		pageBean.setList(list);
		return pageBean;
	}
	
	//���ݶ���id��ѯ����
	public Order findByOid(Integer oid) {
		return orderDao.findByOid(oid);
	}

	//ҵ����޸Ķ����Ĳ���
	public void update(Order currOrder) {
		orderDao.update(currOrder);
	}
}

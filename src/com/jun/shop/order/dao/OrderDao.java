package com.jun.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jun.shop.order.vo.Order;
import com.jun.shop.utils.PageHibernateCallback;

/**
 * �����־ò����
 * @author jun
 *
 */
public class OrderDao extends HibernateDaoSupport{

	//dao�㱣�涩���� ����
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}

	//dao���ҵĶ����ĸ���ͳ��
	public Integer findByCountUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if(list != null && list.size()>0){
			return list.get(0).intValue();
		}
		return null;
	}
	
	//dao���ҵĶ����ķ�ҳ���ϵĲ�ѯ
	public List<Order> findByPageUid(Integer uid, Integer begin, Integer limit) {
		String hql = "from Order o where o.user.uid = ? order by o.ordertime desc";
		List<Order> list = this.getHibernateTemplate().execute(
				new PageHibernateCallback<Order>(hql, new Object[]{uid}, begin, limit));
		if (list != null && list.size() > 0) {
			return list;
		}
		return list;
	}

	public Order findByOid(Integer oid) {
		return this.getHibernateTemplate().get(Order.class, oid);
	}

	//dao���޸Ķ�������
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}

}

package com.jun.shop.order.dao;

import java.util.List;

import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

import com.jun.shop.order.vo.Order;
import com.jun.shop.utils.PageHibernateCallback;

/**
 * 订单持久层代码
 * @author jun
 *
 */
public class OrderDao extends HibernateDaoSupport{

	//dao层保存订单的 方法
	public void save(Order order) {
		this.getHibernateTemplate().save(order);
	}

	//dao层我的订单的个数统计
	public Integer findByCountUid(Integer uid) {
		String hql = "select count(*) from Order o where o.user.uid=?";
		List<Long> list = this.getHibernateTemplate().find(hql, uid);
		if(list != null && list.size()>0){
			return list.get(0).intValue();
		}
		return null;
	}
	
	//dao层我的订单的分页集合的查询
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

	//dao层修改订单操作
	public void update(Order currOrder) {
		this.getHibernateTemplate().update(currOrder);
	}

}

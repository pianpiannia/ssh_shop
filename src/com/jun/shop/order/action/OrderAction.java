package com.jun.shop.order.action;

import java.util.Date;

import org.apache.struts2.ServletActionContext;

import com.jun.shop.cart.vo.Cart;
import com.jun.shop.cart.vo.CartItem;
import com.jun.shop.order.service.OrderService;
import com.jun.shop.order.vo.Order;
import com.jun.shop.order.vo.OrderItem;
import com.jun.shop.user.vo.User;
import com.jun.shop.utils.PageBean;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;

/**
 * ���������action
 * @author jun
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	//ģ����������
	private Order order = new Order();
	//ע��orderservice
	private OrderService orderService;
	//����page����
	private Integer page;
	//����֧��ͨ������
	private String pd_FrpId;
	public void setPd_FrpId(String pd_FrpId) {
		this.pd_FrpId = pd_FrpId;
	}

	public void setPage(Integer page) {
		this.page = page;
	}

	public void setOrderService(OrderService orderService) {
		this.orderService = orderService;
	}

	@Override
	public Order getModel() {
		// TODO Auto-generated method stub
		return order;
	}

	//���ɶ����ķ���
	public String save(){
		//1.�������ݵ����ݿ�
		//order.setOrdertime(new Date());
		order.setState(1);//1:δ���� 	2���Ѹ����û����   3���Ѿ�����������û��ȷ���ջ�    4.�������
		//�ܽ������ǹ��ﳵ����Ϣ
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			this.addActionError("��û�й�����ȹ���");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		//���ö����еĶ�����
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			order.getOrderItems().add(orderItem);
		}
		//���ö��������û�
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser == null){
			this.addActionError("��δ��¼�����ȵ�¼");
			return "login";
		}
		order.setUser(existUser);
		orderService.save(order);
		//2.������������ʾ��ҳ��
		//ͨ��ֵջ�ķ�ʽ��ʾ����ΪOrder��ʾ�Ķ������ģ������ʹ�õĶ���
		//��չ��ﳵ
		cart.clearCart();
		return "saveSuccess";
	}
	
	//�ҵĶ����Ĳ�ѯ
	public String findByUid(){
		//�����û�idȥ��ѯ
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		//����service
		PageBean<Order> pageBean = orderService.findByPageUid(user.getUid(),page);
		//����ҳ������ʾ��ҳ����
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}
	
	//���ݶ���id��ѯ����
	public String findByOid(){
		order = orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}
	
	//Ϊ��������ķ���
	public String payOrder(){
		//�޸Ķ���
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);
		//Ϊ��������
		
		return NONE;
	}
}

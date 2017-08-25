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
 * 订单管理的action
 * @author jun
 *
 */
public class OrderAction extends ActionSupport implements ModelDriven<Order>{
	//模型驱动对象
	private Order order = new Order();
	//注入orderservice
	private OrderService orderService;
	//接受page参数
	private Integer page;
	//接受支付通道编码
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

	//生成订单的方法
	public String save(){
		//1.保存数据到数据库
		//order.setOrdertime(new Date());
		order.setState(1);//1:未付款 	2：已付款，但没发货   3：已经发货，但是没有确认收货    4.交易完成
		//总结数据是购物车中信息
		Cart cart = (Cart) ServletActionContext.getRequest().getSession().getAttribute("cart");
		if(cart == null){
			this.addActionError("还没有购物，请先购物");
			return "msg";
		}
		order.setTotal(cart.getTotal());
		//设置订单中的订单项
		for (CartItem cartItem : cart.getCartItems()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setCount(cartItem.getCount());
			orderItem.setSubtotal(cartItem.getSubtotal());
			orderItem.setProduct(cartItem.getProduct());
			orderItem.setOrder(order);
			
			order.getOrderItems().add(orderItem);
		}
		//设置订单所属用户
		User existUser = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		if(existUser == null){
			this.addActionError("还未登录，请先登录");
			return "login";
		}
		order.setUser(existUser);
		orderService.save(order);
		//2.将订单对象显示到页面
		//通过值栈的方式显示：因为Order显示的对象就是模型驱动使用的对象
		//清空购物车
		cart.clearCart();
		return "saveSuccess";
	}
	
	//我的订单的查询
	public String findByUid(){
		//根据用户id去查询
		User user = (User) ServletActionContext.getRequest().getSession().getAttribute("existUser");
		//调用service
		PageBean<Order> pageBean = orderService.findByPageUid(user.getUid(),page);
		//将分页数据显示到页面上
		ActionContext.getContext().getValueStack().set("pageBean", pageBean);
		return "findByUidSuccess";
	}
	
	//根据订单id查询订单
	public String findByOid(){
		order = orderService.findByOid(order.getOid());
		return "findByOidSuccess";
	}
	
	//为订单付款的方法
	public String payOrder(){
		//修改订单
		Order currOrder = orderService.findByOid(order.getOid());
		currOrder.setAddr(order.getAddr());
		currOrder.setName(order.getName());
		currOrder.setPhone(order.getPhone());
		orderService.update(currOrder);
		//为订单付款
		
		return NONE;
	}
}

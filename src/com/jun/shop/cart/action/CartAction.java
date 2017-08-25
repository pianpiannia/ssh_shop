package com.jun.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.jun.shop.cart.vo.Cart;
import com.jun.shop.cart.vo.CartItem;
import com.jun.shop.product.service.ProductService;
import com.jun.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionSupport;

/**
 * 购物车action
 * @author jun
 *
 */
public class CartAction extends ActionSupport{
	//接收pid
	private Integer pid;
	//接收数量
	private Integer count;
	//注入商品的service
	private ProductService productService;
	
	public void setProductService(ProductService productService) {
		this.productService = productService;
	}
	public void setPid(Integer pid) {
		this.pid = pid;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	//将购物项添加到购物车
	public String addCart(){
		//封装一个CartItem的对象
		CartItem cartItem = new CartItem();
		//设置数量
		cartItem.setCount(count);
		//根据商品id查询商品
		//设置商品
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		//将购物项添加到购物车。
		//购物车应该存在session中，从session中获得购物车
		Cart cart = getCart();
		cart.addCart(cartItem);
		return "addCart";
	}
	
	//清空购物车的方法
	public String clearCart(){
		//获得购物车对象
		Cart cart = getCart();
		//调用购物车中清空方法
		cart.clearCart();
		return "clearCart";
	}
	
	//从购物车中移除购物项
	public String removeCart(){
		//获得购物车对象
		Cart cart = getCart();
		//调用购物车中移除的方法
		cart.removeCart(pid);
		//返回页面
		return "removeCart";
	}
	
	//到我的购物车页面
	public String myCart(){
		return "myCart";
	}
	
	/**
	 * 获得购物车的方法，从session中获得购物车
	 * @return
	 */
	private Cart getCart() {
		Cart cart = (Cart) ServletActionContext.getRequest().getSession()
				.getAttribute("cart");
		if(cart == null){
			cart = new Cart();
			ServletActionContext.getRequest().getSession().setAttribute("cart", cart);
		}
		return cart;
	}
}

package com.jun.shop.cart.action;

import org.apache.struts2.ServletActionContext;

import com.jun.shop.cart.vo.Cart;
import com.jun.shop.cart.vo.CartItem;
import com.jun.shop.product.service.ProductService;
import com.jun.shop.product.vo.Product;
import com.opensymphony.xwork2.ActionSupport;

/**
 * ���ﳵaction
 * @author jun
 *
 */
public class CartAction extends ActionSupport{
	//����pid
	private Integer pid;
	//��������
	private Integer count;
	//ע����Ʒ��service
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
	//����������ӵ����ﳵ
	public String addCart(){
		//��װһ��CartItem�Ķ���
		CartItem cartItem = new CartItem();
		//��������
		cartItem.setCount(count);
		//������Ʒid��ѯ��Ʒ
		//������Ʒ
		Product product = productService.findByPid(pid);
		cartItem.setProduct(product);
		//����������ӵ����ﳵ��
		//���ﳵӦ�ô���session�У���session�л�ù��ﳵ
		Cart cart = getCart();
		cart.addCart(cartItem);
		return "addCart";
	}
	
	//��չ��ﳵ�ķ���
	public String clearCart(){
		//��ù��ﳵ����
		Cart cart = getCart();
		//���ù��ﳵ����շ���
		cart.clearCart();
		return "clearCart";
	}
	
	//�ӹ��ﳵ���Ƴ�������
	public String removeCart(){
		//��ù��ﳵ����
		Cart cart = getCart();
		//���ù��ﳵ���Ƴ��ķ���
		cart.removeCart(pid);
		//����ҳ��
		return "removeCart";
	}
	
	//���ҵĹ��ﳵҳ��
	public String myCart(){
		return "myCart";
	}
	
	/**
	 * ��ù��ﳵ�ķ�������session�л�ù��ﳵ
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

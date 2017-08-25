package com.jun.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart implements Serializable{
	//购物车的属性
	//购物者集合:map的key是商品pid，value是购物者
	private Map<Integer,CartItem> map = new LinkedHashMap<Integer,CartItem>();
	
	//将map的value转成单列集合返回，便于遍历
	//方法名为getCartItems，说明Cart对象中有一个叫cartItems属性
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	//购物总计
	private double total;
	
	public double getTotal() {
		return total;
	}

	//购物车的功能
	//1.将购物项添加到购物车
	public void addCart(CartItem cartItem){
		//判断购物车中是否已经存在购物项
		/*
		 * 如果存在：
		 * 		数量增加
		 * 		总计 = 总计+购物项小计
		 *  如果不存在：
		 *  	向map中添加购物项
		 *  	总计 = 总计+购物项小计
		 */
		//获得商品的id
		Integer pid = cartItem.getProduct().getPid();
		//判断购物车中是否已经存在该购物项
		if(map.containsKey(pid)){
			//存在
			CartItem _cartItem = map.get(pid);//获得购物车中原来购物项
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}
		else{
			//不存在
			map.put(pid, cartItem);
		}
		//设置总计的值
		total += cartItem.getSubtotal();
	}
	
	//2.从购物车中移除购物项
	public void removeCart(Integer pid){
		//将某个购物项移除
		CartItem cartItem = map.remove(pid);
		//总计 =总计-移除的购物项小计
		total -= cartItem.getSubtotal();
	}
	
	//3.清空购物车
	public void clearCart(){
		//将所有购物项清空
		map.clear();
		//将总计设置为0
		total = 0;
	}
}

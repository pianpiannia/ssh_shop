package com.jun.shop.cart.vo;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

public class Cart implements Serializable{
	//���ﳵ������
	//�����߼���:map��key����Ʒpid��value�ǹ�����
	private Map<Integer,CartItem> map = new LinkedHashMap<Integer,CartItem>();
	
	//��map��valueת�ɵ��м��Ϸ��أ����ڱ���
	//������ΪgetCartItems��˵��Cart��������һ����cartItems����
	public Collection<CartItem> getCartItems(){
		return map.values();
	}
	
	//�����ܼ�
	private double total;
	
	public double getTotal() {
		return total;
	}

	//���ﳵ�Ĺ���
	//1.����������ӵ����ﳵ
	public void addCart(CartItem cartItem){
		//�жϹ��ﳵ���Ƿ��Ѿ����ڹ�����
		/*
		 * ������ڣ�
		 * 		��������
		 * 		�ܼ� = �ܼ�+������С��
		 *  ��������ڣ�
		 *  	��map����ӹ�����
		 *  	�ܼ� = �ܼ�+������С��
		 */
		//�����Ʒ��id
		Integer pid = cartItem.getProduct().getPid();
		//�жϹ��ﳵ���Ƿ��Ѿ����ڸù�����
		if(map.containsKey(pid)){
			//����
			CartItem _cartItem = map.get(pid);//��ù��ﳵ��ԭ��������
			_cartItem.setCount(_cartItem.getCount()+cartItem.getCount());
		}
		else{
			//������
			map.put(pid, cartItem);
		}
		//�����ܼƵ�ֵ
		total += cartItem.getSubtotal();
	}
	
	//2.�ӹ��ﳵ���Ƴ�������
	public void removeCart(Integer pid){
		//��ĳ���������Ƴ�
		CartItem cartItem = map.remove(pid);
		//�ܼ� =�ܼ�-�Ƴ��Ĺ�����С��
		total -= cartItem.getSubtotal();
	}
	
	//3.��չ��ﳵ
	public void clearCart(){
		//�����й��������
		map.clear();
		//���ܼ�����Ϊ0
		total = 0;
	}
}

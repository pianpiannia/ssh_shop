package com.jun.shop.utils;

import java.util.UUID;

/**
 * ��������ַ����Ĺ�����
 * @author jun
 *
 */
public class UUIDUtils {
	/**
	 * �������ַ���
	 */
	public static String getUUID(){
		return UUID.randomUUID().toString().replace("-", "");
	}
}

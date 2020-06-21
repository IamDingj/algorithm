package com.dj.pattern.simpleFactory;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.NoSuchPaddingException;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;

public class TestClient {

	public static void main(String[] args) throws NoSuchAlgorithmException, NoSuchPaddingException {
		Product product = null;
		/*int type = 1;
		if (type == 1) {
			product = new ConcreteProductA();
		} else if (type == 2) {
			product = new ConcreteProductA();
		}*/
		// 用简单工厂封装实例化具体类的代码
		SimpleFactory simpleFactory = new SimpleFactory();
		product = simpleFactory.createProduct(1);
		product.show();

		// Java加密技术，获取不同加密算法的密钥生成器
		KeyGenerator keyGen = KeyGenerator.getInstance("DESede");

		// 创建密码器
		Cipher cp = Cipher.getInstance("DESede");

	}
}

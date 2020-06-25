package com.dj.pattern.factoryMethod;

import com.dj.pattern.simpleFactory.Product;

import java.util.Collection;

public class TestClient {

	public static void main(String[] args) {
		try {
			// 具体工厂对象可以注入到Spring中，由Spring容器管理
			Class<?> c = Class.forName("com.dj.pattern.factoryMethod.ConcreteFactoryA");
			Factory factory = (Factory)c.newInstance();
			Product product = factory.createProduct();
			product.show();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (InstantiationException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			e.printStackTrace();
		}

//       java.util.Collection

    }
}

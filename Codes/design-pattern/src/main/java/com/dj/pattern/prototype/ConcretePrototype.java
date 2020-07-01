package com.dj.pattern.prototype;

public class ConcretePrototype implements Cloneable {

	public ConcretePrototype() {
		System.out.println("创建具体原型。。。");
	}

	/**
	 *
	 * @return
	 * @throws CloneNotSupportedException
	 */
	public Object clone() throws CloneNotSupportedException {
		System.out.println("复制具体原型。。。");
		// 父类Object.clone()的浅拷贝
		return (ConcretePrototype) super.clone();
	}

}

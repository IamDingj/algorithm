package com.dj.pattern.proxy.dynamic;

public class TestClient {

	public static void main(String[] args) {
		//实例化JDKProxy对象
		JdkProxy jdkProxy = new JdkProxy();
		//获取代理对象
		UserManager jdkUser = (UserManager) jdkProxy.getJDKProxy(new UserManagerImpl());
		//执行新增方法
		jdkUser.addUser("admin", "123123");
		System.out.println("---------------------------------------------------");

		CglibProxy cglibProxy = new CglibProxy();//实例化CglibProxy对象
		UserManager cglibUser =  (UserManager) cglibProxy.getCglibProxy(new UserManagerImpl());//获取代理对象
		cglibUser.delUser("admin");//执行删除方法

	}

}

package com.dj.pattern.proxy.dynamic;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Auther: steven
 * @Date: 2018/7/5
 * @Description: JDK 动态代理需实现 java.lang.reflect.InvocationHandler
 */
public class JdkProxy implements InvocationHandler {

    //被代理对象，Object类型
    private Object target;

    /**
     *
     * @param proxy 给目标对象所动态产生的代理对象
     * @param method 调用的目标对象中的方法的镜像
     * @param args 调用方法的时候所传的参数
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        // 代理逻辑实现
        System.out.println("JDK 动态代理开始执行: "+method.getName()+" 方法被调用了...");
        Object result = method.invoke(target, args);
        System.out.println("JDK 动态代理执行结束。");
        return result;
    }

    /**
     * 定义获取代理对象方法
     * @param targetObject
     * @return
     */
    public Object getJDKProxy(Object targetObject){
        //为目标对象target赋值
        this.target = targetObject;
        /**
         * loader: 目标对象的类加载器
         * interfaces: 目标对象所实现的接口，可以看出：JDK动态代理只能针对实现了接口的类进行代理
         * InvocationHandler: 接口的实现类对象
         */
        return Proxy.newProxyInstance(targetObject.getClass().getClassLoader(), targetObject.getClass().getInterfaces(), this);
    }

}

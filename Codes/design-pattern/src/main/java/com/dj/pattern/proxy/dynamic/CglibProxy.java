package com.dj.pattern.proxy.dynamic;

import org.springframework.cglib.proxy.Enhancer;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * @Auther: steven
 * @Date: 2018/7/5
 * @Description: cglib 动态代理需实现 org.springframework.cglib.proxy.MethodInterceptor
 */
public class CglibProxy implements MethodInterceptor {

    //需要代理的目标对象
    private Object target;

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        System.out.println("Cglib动态代理开始执行: "+method.getName()+" 方法被调用了...");
        //方法执行，参数：target 目标对象 objects参数数组
        Object invoke = method.invoke(target, objects);
        System.out.println("Cglib动态代理执行结束！");
        return invoke;
    }

    /**
     * 获取代理对象
     * @param objectTarget
     * @return
     */
    public Object getCglibProxy(Object objectTarget){
        //为目标对象target赋值
        this.target = objectTarget;
        Enhancer enhancer = new Enhancer();
        //设置父类,因为Cglib是针对指定的类生成一个子类，所以需要指定父类
        enhancer.setSuperclass(objectTarget.getClass());
        // 设置回调
        enhancer.setCallback(this);
        //创建并返回代理对象
        Object result = enhancer.create();
        return result;
    }

}

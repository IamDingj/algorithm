package com.dj.pattern.proxy.dynamic;

/**
 * @Auther: steven
 * @Date: 2018/7/5
 * @Description: 用户管理接口
 */
public interface UserManager {
    //新增用户
    void addUser(String userName,String password);
    //删除用户
    void delUser(String userName);

}

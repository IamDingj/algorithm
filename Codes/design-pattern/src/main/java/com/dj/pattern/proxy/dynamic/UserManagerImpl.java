package com.dj.pattern.proxy.dynamic;

public class UserManagerImpl implements UserManager {

    @Override
    public void addUser(String userName, String password) {
        System.out.println("调用方法：addUser()， userName: "+userName+"， password: "+password);
    }

    @Override
    public void delUser(String userName) {
        System.out.println("调用方法：delUser()， userName: "+userName);
    }

}

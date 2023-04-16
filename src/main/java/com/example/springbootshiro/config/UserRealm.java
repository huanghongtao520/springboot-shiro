package com.example.springbootshiro.config;

import com.example.springbootshiro.pojo.User;
import com.example.springbootshiro.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;

public class UserRealm extends AuthorizingRealm {
    @Autowired
    private UserService userService;
    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行了授权");
        return null;
    }
    //认证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        System.out.println("执行了认证");
        UsernamePasswordToken userToken=(UsernamePasswordToken)token;
        //连接真实的数据库
        User user = userService.queryUserByName(userToken.getUsername());
        if (user==null)//没有这个人
            return null;
        //密码认证,shiro可以对密码加密MD5,MD5Hash
        return new SimpleAuthenticationInfo("",user.getPwd(),"");
    }
}

package com.example.springbootshiro.config;

import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfig {

    //ShiroFilterFactoryBean
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(@Qualifier("securityManager") DefaultWebSecurityManager defaultWebSecurityManager){
        ShiroFilterFactoryBean bean = new ShiroFilterFactoryBean();
        //设置安全管理器
        bean.setSecurityManager(defaultWebSecurityManager);

        //添加shiro的内置过滤器
        /*
        * anon:无需认证可以访问
        * authc:必须认证才可以访问
        * user：吸引拥有记住我功能才能用
        * perms:拥有对某个资源的权限才可以访问
        * role:拥有某个角色权限才可以访问
        * */
        Map<String,String> filterMap=new LinkedHashMap<>();
        /*filterMap.put("/user/add","authc");
        filterMap.put("/user/update","authc");*/

        //拥有权限才可以访问URL,注意：和下面的/user/*的顺序不能错，否则报错
        filterMap.put("/user/add","perms[user:add]");
        filterMap.put("/user/update","perms[user:update]");
        //必须认证才可以访问
        filterMap.put("/user/*","authc");
        //登录的URL
        bean.setLoginUrl("/user/login");
        //设置登陆页面
        bean.setFilterChainDefinitionMap(filterMap);
        //未授权时要跳转的页面
        bean.setUnauthorizedUrl("/unauthorized");
        return bean;
    }

    //defaulWebSecurityManager
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager getDefaultWebSecurityManager(@Qualifier("userRealm") UserRealm userRealm){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //关联UserRealm
        securityManager.setRealm(userRealm);
        return securityManager;
    }

    //创建realm对象,bean名默认是方法名
    @Bean(name = "userRealm")
    public UserRealm userRealm(){
        return new UserRealm();
    }
}

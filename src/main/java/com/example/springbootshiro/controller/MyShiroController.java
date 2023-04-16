package com.example.springbootshiro.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MyShiroController {
    @RequestMapping({"/","/index"})
    public String index(){
        return "index";
    }
    //shiro用户认证
    @RequestMapping("/login")
    public String login(String username, String password, Model model){
        //获取当前用户
        Subject subject = SecurityUtils.getSubject();
        //封装用户的登录数据
        UsernamePasswordToken token = new UsernamePasswordToken(username,password);
        try {
            //执行登陆方法
            subject.login(token);
            return "index";
        } catch (UnknownAccountException uae) {
        model.addAttribute("msg","用户不存在");
            return "login";
        } catch (IncorrectCredentialsException ice) {
        model.addAttribute("msg","密码不正确");
            return "login";
        } catch (LockedAccountException lae) {
        model.addAttribute("msg","用户被锁定");
            return "login";
        } catch (AuthenticationException ae) {
        model.addAttribute("msg",ae);
            return "login";
        }
    }

    @RequestMapping("/user/add")
    public String user(){
        return "user/add";
    }
    @RequestMapping("/user/update")
    public String update(){
        return "user/update";
    }

    @RequestMapping("/user/login")
    public String login(){
        return "login";
    }
}


package com.hjrpc.shirospringboot.controller;

import com.hjrpc.shirospringboot.mgb.model.SUser;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/user")
public class UserController {

    @RequestMapping(value = "/login",method = RequestMethod.POST,produces = "application/json;charset=utf-8")
    @ResponseBody
    public String login(SUser user){

        Subject subject = SecurityUtils.getSubject();
        AuthenticationToken token = new UsernamePasswordToken(user.getUsername(),user.getPassword(),user.isRemremberMe());
        try {
            subject.login(token);
        }catch (UnknownAccountException e){
            return "用戶名不存在";
        }catch (Exception e){
            return e.getMessage();
        }
        return "登錄成功";
    }

    @RequestMapping(value = "/loginPage")
    public String loginPage(){

       return "login";
    }

}

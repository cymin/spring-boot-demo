package com.tsingyun.shirodemo.controller;

import com.tsingyun.shirodemo.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authz.annotation.RequiresAuthentication;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by chen on 17/5/19.
 */
@RestController
public class DemoController {

    /**
     * 测试需要认证
     * @return
     */
    @GetMapping({"/","/index"})
    @RequiresAuthentication
    private String index(){
        return "hello";
    }

    @GetMapping("/test")
    public String test() {
        return "success";
    }

    /**
     * 测试需要admin角色
     * @return
     */
    @GetMapping("/admin")
    @RequiresRoles("admin")
    public String role() {
        return "admin";
    }

    /**
     * 测试需要写权限
     * @return
     */
    @GetMapping("/write")
    @RequiresPermissions("write")
    public User write() {
        Subject subject = SecurityUtils.getSubject();
        User user = (User) subject.getSession().getAttribute("user");
        return user;
    }

    /**
     * 测试需要读权限
     * @return
     */
    @GetMapping("/read")
    @RequiresPermissions("read")
    public String read() {
        return "read";
    }

    /**
     * 测试需要读、写两种权限
     * @return
     */
    @GetMapping("/both")
    @RequiresPermissions({"read","write"})
    public String both() {
        return "read & write";
    }

}

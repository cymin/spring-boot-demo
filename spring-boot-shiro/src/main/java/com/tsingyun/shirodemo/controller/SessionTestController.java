package com.tsingyun.shirodemo.controller;

import com.tsingyun.shirodemo.model.User;
import com.tsingyun.shirodemo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

/**
 * 测试两个session存储在同一个地方
 * Created by chen on 17/5/17.
 */
@RestController
public class SessionTestController {

    @Autowired
    UserService userService;

    @GetMapping("s1")
    private User session1(HttpSession session){
        User user = (User)session.getAttribute("user");
        return user;
    }

    @GetMapping("s2")
    private User session2(){
        User user = userService.getFromShiroSession();
        return user;
    }
}

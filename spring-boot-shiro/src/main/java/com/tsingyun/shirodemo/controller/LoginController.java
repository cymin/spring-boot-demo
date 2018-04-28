package com.tsingyun.shirodemo.controller;

import com.tsingyun.common.exception.CommonHttpException;
import com.tsingyun.common.model.HttpErrorCode;
import com.tsingyun.shirodemo.model.LoginInfo;
import com.tsingyun.shirodemo.model.User;
import com.tsingyun.shirodemo.service.UserService;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;

/**
 * Created by chen on 17/5/17.
 */
@RestController
public class LoginController {

    @Autowired
    private UserService userService;

    /**
     * 登录
     * @param loginInfo
     * @param httpSession
     * @return
     */
    @PostMapping("/login")
    User login(@RequestBody LoginInfo loginInfo, HttpSession httpSession) {
        Subject subject = SecurityUtils.getSubject();
        if (!subject.isAuthenticated()) {
            UsernamePasswordToken token = new UsernamePasswordToken(loginInfo.getUsername(), loginInfo.getPassword());
            try {
                subject.login(token);
            } catch (UnknownAccountException e) {
                throw new CommonHttpException(HttpStatus.UNAUTHORIZED, HttpErrorCode.USER_NOT_EXIST);
            } catch (IncorrectCredentialsException exception) {
                throw new CommonHttpException(HttpStatus.UNAUTHORIZED, HttpErrorCode.USER_PASSWORD_MISMATCH);
            } catch (LockedAccountException exception) {
                throw new CommonHttpException(HttpStatus.UNAUTHORIZED, HttpErrorCode.USER_IS_LOCKED);
            } catch (AuthenticationException e) {
                e.printStackTrace();
                throw new CommonHttpException(HttpStatus.UNAUTHORIZED, HttpErrorCode.USER_ERROR_UNKNOWN);
            }
        }

        Integer id = (Integer) subject.getPrincipal();
        User user = userService.findUserById(id);
        subject.getSession().setAttribute("user", user);
//        httpSession.setAttribute("user", user);
        return user;
    }

    /**
     * 登出
     */
    @PostMapping("/logout")
    public void logout() {
        Subject subject = SecurityUtils.getSubject();
        subject.logout();
    }

}

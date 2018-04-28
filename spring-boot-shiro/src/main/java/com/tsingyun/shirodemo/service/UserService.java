package com.tsingyun.shirodemo.service;

import com.tsingyun.shirodemo.model.Permission;
import com.tsingyun.shirodemo.model.Role;
import com.tsingyun.shirodemo.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by chen on 17/5/17.
 */
@Service
public class UserService {

    public User getFromShiroSession() {
        Subject subject = SecurityUtils.getSubject();
        subject.checkPermission("write");
        User user = (User) subject.getSession().getAttribute("user");
        return user;
    }

    public User findUserByUsername(String username) {
        if (username.equals("chen")) {
            return user4Admin();
        } else if (username.equals("zhang")) {
            return user4Guest();
        }
        return null;
    }

    public User findUserById(Integer id) {
        if (id.equals(1)) {
            return user4Admin();
        } else if (id.equals(2)) {
            return user4Guest();
        }
        return null;
    }

    private User user4Admin() {
        User user = new User(1, "chen", "123");
        user.setHashedPassword("MsaNVutaNPfP0ot5miS8hVgfuUYZlCTJD/vYb4/BkEU=");
        user.setRoles(Arrays.asList(role4Guest(), role4Admin()));
        return user;
    }

    private User user4Guest() {
        User user = new User(2, "zhang", "123");
        user.setHashedPassword("JlLic3EK9vL29HLAs9ZVlxOMtonDcWI+Nu/wOvu0ucc=");
        user.setRoles(Arrays.asList(role4Guest()));
        return user;
    }

    private Role role4Guest() {
        Role role = new Role(2, "guest");
        Set<Permission> permissions = new HashSet<>();
        permissions.add(new Permission(1, "read"));
        role.setPermissions(permissions);
        return role;
    }

    private Role role4Admin() {
        Role role = new Role(1, "admin");
        Set<Permission> permissions = new HashSet<>();
        permissions.add(new Permission(1, "read"));
        permissions.add(new Permission(2, "write"));
        role.setPermissions(permissions);
        return role;
    }
}

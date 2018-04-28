package com.tsingyun.shirodemo.realm;

import com.tsingyun.shirodemo.model.Permission;
import com.tsingyun.shirodemo.model.Role;
import com.tsingyun.shirodemo.model.User;
import com.tsingyun.shirodemo.service.UserService;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.crypto.hash.Sha256Hash;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by chen on 17/5/17.
 */
public class TsingyunRealm extends AuthorizingRealm {

    public TsingyunRealm() {
        setName("TsingyunRealm");
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        matcher.setHashAlgorithmName(Sha256Hash.ALGORITHM_NAME);
        matcher.setStoredCredentialsHexEncoded(false);
        matcher.setHashIterations(1024);
        setCredentialsMatcher(matcher);
    }

    //授权
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//        Integer userId = (Integer) principals.getPrimaryPrincipal();
        Integer userId = (Integer) principals.fromRealm(getName()).iterator().next();
        User user = userService.findUserById(userId);
        if (user != null) {
            SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
            //add Permission Resources
            for (Role role : user.getRoles()) {
                info.addRole(role.getName());
                for (Permission permission : role.getPermissions()) {
                    info.addStringPermission(permission.getName());
                }
            }
            return info;
        }
        return null;
    }

    //登录验证
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String) token.getPrincipal();
        User user = userService.findUserByUsername(username);
        if (user != null) {
            return new SimpleAuthenticationInfo(user.getId(), user.getHashedPassword(),
                ByteSource.Util.bytes(user.getName()), getName());
        } else {
            return null;
        }
    }

    @Autowired
    UserService userService;
}

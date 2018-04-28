package com.tsingyun.shirodemo.config;

import com.tsingyun.boot.shiro.web.config.OrderedShiroFilterChainDefinition;
import com.tsingyun.shirodemo.realm.TsingyunRealm;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "shiro.web.enabled", matchIfMissing = true)
public class ShiroConfig {

    @Bean
    public Realm realm() {
        return new TsingyunRealm();
    }

    @Value("#{ @environment['shiro.chain.enabled'] ?: true }")
    protected boolean shiroChainEnabled;

    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        OrderedShiroFilterChainDefinition chainDefinition = new OrderedShiroFilterChainDefinition();
        if (shiroChainEnabled) {
            chainDefinition.addPathDefinition("/login", "s_anon");
            chainDefinition.addPathDefinition("/logout", "s_logout");
            chainDefinition.addPathDefinition("/test", "s_perms[\"write\"]");
            chainDefinition.addPathDefinition("/**", "s_user");
        }
        return chainDefinition;
    }
}

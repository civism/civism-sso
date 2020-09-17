package com.civism.sso.config;

import com.civism.sso.filter.CheckLoginFilter;
import com.civism.sso.filter.RefreshFilter;
import com.civism.sso.shiro.CivismShiroUpmsRealm;
import com.civism.sso.shiro.JavaUuidSessionIdGenerator;
import com.civism.sso.shiro.ShiroRedisCacheSessionDAO;
import com.civism.sso.shiro.ShiroSessionManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.session.mgt.SessionManager;
import org.apache.shiro.session.mgt.eis.SessionDAO;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
@Configuration
public class ShiroConfig {

    @Bean
    public SessionManager sessionManager() {
        ShiroSessionManager shiroSessionManager = new ShiroSessionManager();
        shiroSessionManager.setGlobalSessionTimeout(86400000);
        shiroSessionManager.setDeleteInvalidSessions(true);
        shiroSessionManager.setSessionValidationInterval(1800000);

        shiroSessionManager.setSessionDAO(getSession());
        shiroSessionManager.getSessionIdCookie().setName("SHIRO-SESSIONID");
        //是否开启定时调度器进行检测过期session 默认为true
        shiroSessionManager.setSessionValidationSchedulerEnabled(true);
        //取消url 后面的 JSESSIONID
        shiroSessionManager.setSessionIdUrlRewritingEnabled(false);
        return shiroSessionManager;
    }

    @Bean
    public SessionDAO getSession() {
        ShiroRedisCacheSessionDAO shiroRedisCacheSessionDAO = new ShiroRedisCacheSessionDAO();
        shiroRedisCacheSessionDAO.setSessionIdGenerator(new JavaUuidSessionIdGenerator());
        return shiroRedisCacheSessionDAO;
    }

    @Bean
    public CheckLoginFilter getCheckLoginFilter() {
        return new CheckLoginFilter();
    }

    @Bean
    public RefreshFilter getRefreshFilter() {
        return new RefreshFilter();
    }

    @Bean
    public ShiroFilterFactoryBean shiroFilter(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        Map<String, Filter> filters = new HashMap<>(1);

        filters.put("checkLogin", getCheckLoginFilter());
        filters.put("refresh", getCheckLoginFilter());

        shiroFilterFactoryBean.setFilters(filters);

        //拦截器.
        Map<String, String> filterChainDefinitionMap = new LinkedHashMap<>();

        filterChainDefinitionMap.put("/sso/login", "anon");
        filterChainDefinitionMap.put("/**", "checkLogin,refresh");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager getSecurityManager() {
        DefaultWebSecurityManager defaultWebSecurityManager = new DefaultWebSecurityManager();
        defaultWebSecurityManager.setSessionManager(sessionManager());
        defaultWebSecurityManager.setRealm(getRealm());
        return defaultWebSecurityManager;
    }

    @Bean
    public Realm getRealm() {
        return new CivismShiroUpmsRealm();
    }

}

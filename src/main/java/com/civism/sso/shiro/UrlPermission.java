package com.civism.sso.shiro;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.util.PatternMatcher;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
public class UrlPermission implements Permission {


    private String url;

    public UrlPermission(String url) {
        this.url = url;
    }

    @Override
    public boolean implies(Permission p) {
        if (!(p instanceof UrlPermission)) {
            return false;
        }
        UrlPermission urlPermission = (UrlPermission) p;
        PatternMatcher patternMatcher = new UrlPathMatcher();
        return patternMatcher.matches(this.url, urlPermission.url);
    }
}

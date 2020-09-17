package com.civism.sso.shiro;

import org.apache.shiro.util.PatternMatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
public class UrlPathMatcher implements PatternMatcher {

    @Override
    public boolean matches(String p, String source) {
        if (p.equals(source)) {
            return true;
        } else {
            //原始路径, source请求路径
            Pattern pattern = Pattern.compile(p);
            Matcher matcher = pattern.matcher(source);
            return matcher.matches();
        }
    }
}

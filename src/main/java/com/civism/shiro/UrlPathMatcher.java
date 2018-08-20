package com.civism.shiro;

import org.apache.shiro.util.PatternMatcher;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author star
 * @date 2018/8/17 下午3:56
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
            if (matcher.matches()) {
                return true;
            }
            return false;
        }
    }
}

package com.civism.shiro;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;

/**
 * @author star
 * @date 2018/8/17 下午4:11
 */
public class UrlPermissionResolver implements PermissionResolver {
    @Override
    public Permission resolvePermission(String permissionString) {
        return new UrlPermission(permissionString);
    }
}
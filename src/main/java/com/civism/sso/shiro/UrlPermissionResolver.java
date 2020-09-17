package com.civism.sso.shiro;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.PermissionResolver;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
public class UrlPermissionResolver implements PermissionResolver {
    @Override
    public Permission resolvePermission(String permissionString) {
        return new UrlPermission(permissionString);
    }
}
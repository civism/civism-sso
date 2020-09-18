package com.civism.sso.shiro;

import org.apache.shiro.authz.Permission;
import org.apache.shiro.authz.permission.RolePermissionResolver;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

/**
 * @author : Civism
 * @version 1.0
 * @projectName：civism-sso
 * @Time: 2020-09-18
 * @E-mail:695234456@qq.com
 */
public class CustomRolePermissionResolver implements RolePermissionResolver {

    @Override
    public Collection<Permission> resolvePermissionsInRole(String s) {



        List<Permission> roleList = new LinkedList<>();
        roleList.add(new RolePermission(s));
        return roleList;
    }
}

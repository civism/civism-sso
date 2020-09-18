package com.civism.sso.shiro;

import org.apache.shiro.authz.Permission;

/**
 * @author : Civism
 * @version 1.0
 * @projectName：civism-sso
 * @Time: 2020-09-18
 * @E-mail:695234456@qq.com
 */
public class RolePermission implements Permission {

    private String role;


    public RolePermission(String role) {
        this.role = role;
    }

    @Override
    public boolean implies(Permission permission) {

        return false;
    }
}

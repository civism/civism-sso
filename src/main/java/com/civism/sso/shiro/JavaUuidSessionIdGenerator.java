package com.civism.sso.shiro;

import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.SessionIdGenerator;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
public class JavaUuidSessionIdGenerator implements SessionIdGenerator {

    public JavaUuidSessionIdGenerator() {
    }


    @Override
    public Serializable generateId(Session session) {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}

package com.civism.sso.shiro;

import com.civism.sso.redis.RedisClient;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
public class ShiroRedisCacheSessionDAO extends EnterpriseCacheSessionDAO {


    private static final String REDIS_KEY = "civism-sso-redis-";

    @Resource
    private RedisClient redisClient;

    @Override
    protected Serializable doCreate(Session session) {

        Serializable serializable = super.doCreate(session);
        redisClient.set(REDIS_KEY + serializable, session, (int) session.getTimeout() / 1000);
        return serializable;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        Session session = super.doReadSession(sessionId);
        if (session == null) {
            session = (Session) redisClient.get(REDIS_KEY + sessionId);
        }
        return session;
    }

    @Override
    protected void doUpdate(Session session) {
        super.doUpdate(session);
        redisClient.set(REDIS_KEY + session.getId(), session, (int) session.getTimeout() / 1000);
    }

    @Override
    protected void doDelete(Session session) {
        super.doDelete(session);
        redisClient.delete(REDIS_KEY + session.getId());
    }
}

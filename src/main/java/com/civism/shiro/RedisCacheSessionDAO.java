package com.civism.shiro;

import com.civism.constants.SsoConstants;
import com.civism.dao.RedisClient;
import com.civism.utils.SerializeUtil;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * @author star
 */
public class RedisCacheSessionDAO extends EnterpriseCacheSessionDAO {

    @Resource
    private RedisClient redisClient;

    @Override
    protected Serializable doCreate(Session session) {

        Serializable sessionId = this.generateSessionId(session);
        this.assignSessionId(session, sessionId);
        redisClient.set(SsoConstants.REDIS_KEY + session.getId(), SerializeUtil.serialize(session), session.getTimeout() / 1000);
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable serializable) {
        byte[] value = redisClient.get(SsoConstants.REDIS_KEY + serializable);
        return SerializeUtil.deserialize(value, Session.class);
    }


    @Override
    protected void doUpdate(Session session) {
        if (session == null || session.getId() == null) {
            throw new NullPointerException("session is empty");
        }
        super.doUpdate(session);
        redisClient.set(SsoConstants.REDIS_KEY + session.getId(), SerializeUtil.serialize(session));
    }

    @Override
    protected void doDelete(Session session) {
        if (session == null || session.getId() == null) {
            throw new NullPointerException("session is empty");
        }
        super.doDelete(session);
        redisClient.remove(SsoConstants.REDIS_KEY + session.getId());
    }


}
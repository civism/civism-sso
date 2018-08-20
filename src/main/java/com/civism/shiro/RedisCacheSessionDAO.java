package com.civism.shiro;

import com.civism.constants.SsoConstants;
import com.civism.dao.RedisClient;
import com.civism.utils.SerializeUtil;
import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.UnknownSessionException;
import org.apache.shiro.session.mgt.eis.AbstractSessionDAO;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

/**
 * @author star
 */
public class RedisCacheSessionDAO extends AbstractSessionDAO {

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
    public void update(Session session) throws UnknownSessionException {
        if (session == null || session.getId() == null) {
            throw new NullPointerException("session is empty");
        }
        redisClient.set(SsoConstants.REDIS_KEY + session.getId(), SerializeUtil.serialize(session));

    }

    @Override
    public void delete(Session session) {
        if (session == null || session.getId() == null) {
            throw new NullPointerException("session is empty");
        }
        redisClient.remove(SsoConstants.REDIS_KEY + session.getId());
    }

    @Override
    public Collection<Session> getActiveSessions() {

        Set<byte[]> keys = redisClient.keys(SsoConstants.REDIS_KEY);
        if (CollectionUtils.isEmpty(keys)) {
            return null;
        }
        Collection<Session> collection = new HashSet<>();
        for (byte[] key : keys) {
            collection.add(SerializeUtil.deserialize(key, Session.class));
        }
        return collection;

    }
}
/* 
 *
 * Copyright (C) 1999-2014 IFLYTEK Inc.All Rights Reserved. 
 * 
 * FileName：brain-police
 * 
 * Description：
 * 
 * History：
 * Version   Author      Date            Operation 
 * 1.0	     Administrator    2017/5/1621:18	  Create	
 */
package com.kxjl.brain.police.service.api.redis.impl;

import com.kxjl.brain.police.service.api.redis.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * redis操作
 * @Author DengWei
 * @Date 2017/5/16 21:18
 */

@Service
public class RedisServiceImpl implements RedisService{

    @Autowired
    private RedisTemplate<Serializable, Serializable> redisTemplate;

    private int dbIdx=0;
    @Override
    public void hMset(final String key,final Map<String, String> map) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(dbIdx);
                connection.hMSet(StringToArrayUTF8(key), fromStringToByte(map));
                return null;
            }
        });
    }

    @Override
    public void hMset(final String key,final Map<String, String> map,final long expireTime) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(dbIdx);
                connection.hMSet(StringToArrayUTF8(key), fromStringToByte(map));
                connection.expire(StringToArrayUTF8(key), expireTime);
                return null;
            }
        });
    }

    @Override
    public long hLen(final String key) {
        return (long)redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(dbIdx);
                long rel = connection.hLen(StringToArrayUTF8(key));
                return rel;
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public Map<String,String> hGetAll(final String key) {
        return (Map<String,String>)redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Map<String,String> doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(dbIdx);
                Map<String,String> map = fromByteToString(connection.hGetAll(StringToArrayUTF8(key)));
                return map;
            }
        });
    }

    @Override
    public void set(final String key,final String value,final long expireTime) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(dbIdx);
                connection.set(StringToArrayUTF8(key), StringToArrayUTF8(value));
                connection.expire(StringToArrayUTF8(key), expireTime);
                return null;
            }
        });
    }

    @Override
    public String get(final String key) {
        return (String) redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(dbIdx);
                byte[] result = connection.get(StringToArrayUTF8(key));
                String strValue = null;
                if(null == result) {
                    return null;
                } else {
                    try {
                        strValue = new String(result,"UTF8");
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                    return strValue;
                }
            }
        });
    }

    @Override
    public void sAdd(final String key,final String value) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(dbIdx);
                connection.sAdd(StringToArrayUTF8(key),StringToArrayUTF8(value));
                return null;
            }
        });
    }

    @Override
    @SuppressWarnings("unchecked")
    public Set<String> sMembers(final String key) {
        return (Set<String>)redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Set<String> doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(dbIdx);
                Set<byte[]> set = connection.sMembers(StringToArrayUTF8(key));
                return fromByteToString(set);
            }
        });
    }

    @Override
    public boolean keyExists(final String key) {
        return (boolean)redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(dbIdx);
                boolean exists = connection.exists(StringToArrayUTF8(key));
                return exists;
            }
        });
    }
    @Override
    public void delKey(final String key) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(dbIdx);
                if(connection.exists(StringToArrayUTF8(key)))
                    connection.del(StringToArrayUTF8(key));
                return null;
            }
        });
    }

    @Override
    public void selectDb(final int dbId) {
        dbIdx = dbId;
        //        redisTemplate.execute(new RedisCallback<Object>() {
        //            @Override
        //            public Object doInRedis(RedisConnection connection)
        //                    throws DataAccessException {
        //                connection.select(dbId);
        //                return null;
        //            }
        //        });
    }

    private Set<String> fromByteToString(Set<byte[]> set) {
        Set<String> resultSet = new HashSet<String>();
        for(byte[] value : set) {
            resultSet.add(new String(value));
        }
        return resultSet;
    }

    private Map<byte[],byte[]> fromStringToByte(Map<String,String> map) {
        Map<byte[],byte[]> resultMap = new HashMap<byte[], byte[]>();
        Set<String> keySet = map.keySet();
        for(String key : keySet) {
            String value = map.get(key);
            resultMap.put(StringToArrayUTF8(key),StringToArrayUTF8(value));
        }
        return resultMap;
    }

    private Map<String,String> fromByteToString(Map<byte[],byte[]> map) {
        Map<String,String> resultMap = new HashMap<String, String>();
        Set<byte[]> keySet = map.keySet();
        try{
            for(byte[] key : keySet) {
                String value = new String(map.get(key),"UTF8");
                String keyStr = new String(key,"UTF8");
                resultMap.put(keyStr, value);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return resultMap;
    }

    /* (non-Javadoc)
     * @see tv.stardream.redis.IRedisOperateDAO#hkeyExist(java.lang.String)
     */
    @Override
    public boolean hkeyExist(final String key,final String hkey) {
        return (boolean)redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(dbIdx);
                boolean exists = connection.hExists(StringToArrayUTF8(key),StringToArrayUTF8(hkey));
                return exists;
            }
        });
    }

    /* (non-Javadoc)
     * @see tv.stardream.redis.IRedisOperateDAO#hget(java.lang.String, java.lang.String)
     */
    @Override
    public String hget(final String key,final String hkey) {
        return (String)redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public String doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(dbIdx);
                byte[] val = connection.hGet(StringToArrayUTF8(key),StringToArrayUTF8(hkey));
                return new String(val);
            }
        });
    }

    /* (non-Javadoc)
     * @see tv.stardream.redis.IRedisOperateDAO#hset(java.lang.String, java.lang.String, java.lang.String)
     */
    @Override
    public Boolean hset(final String key,final String hkey,final String hval) {
        return (Boolean)redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(dbIdx);
                Boolean rel = connection.hSet(StringToArrayUTF8(key),StringToArrayUTF8(hkey),StringToArrayUTF8(hval));
                return rel;
            }
        });
    }

    /* (non-Javadoc)
     * @see tv.stardream.redis.IRedisOperateDAO#keys(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Set<String> keys(final String patten) {
        return (Set<String>)redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Set<String> doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(dbIdx);
                Set<byte[]> set = connection.keys(StringToArrayUTF8(patten));
                return fromByteToString(set);
            }
        });
    }

    /* (non-Javadoc)
     * @see tv.stardream.redis.IRedisOperateDAO#scard(java.lang.String)
     */
    @Override
    public long scard(final String key) {
        return (long)redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Long doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(dbIdx);
                long rel = connection.sCard(StringToArrayUTF8(key));
                return rel;
            }
        });
    }

    /* (non-Javadoc)
     * @see tv.stardream.redis.IRedisOperateDAO#sismember(java.lang.String, java.lang.String)
     */
    @Override
    public boolean sismember(final String key,final String mem) {
        return (Boolean)redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Boolean doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(dbIdx);
                Boolean rel = connection.sIsMember(StringToArrayUTF8(key),StringToArrayUTF8(mem));
                return rel;
            }
        });
    }

    /* (non-Javadoc)
     * @see tv.stardream.redis.IRedisOperateDAO#hkeys(java.lang.String)
     */
    @SuppressWarnings("unchecked")
    @Override
    public Set<String> hkeys(final String key) {
        return (Set<String>)redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Set<String> doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(dbIdx);
                Set<byte[]> set = connection.hKeys(StringToArrayUTF8(key));
                return fromByteToString(set);
            }
        });
    }

    /* (non-Javadoc)
     * @see tv.stardream.redis.IRedisOperateDAO#srem(java.lang.String, java.lang.String)
     */
    @Override
    public void srem(final String key,final String mem) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(dbIdx);
                if (connection.sIsMember(StringToArrayUTF8(key),StringToArrayUTF8(mem)))
                    connection.sRem(StringToArrayUTF8(key),StringToArrayUTF8(mem));
                return null;
            }
        });
    }

    @Override
    public void set(final String key, final String value) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(dbIdx);
                connection.set(StringToArrayUTF8(key), StringToArrayUTF8(value));
                return null;
            }
        });

    }

    @Override
    public void expire(final String key, final long seconds) {
        redisTemplate.execute(new RedisCallback<Object>() {
            @Override
            public Object doInRedis(RedisConnection connection)
                    throws DataAccessException {
                connection.select(dbIdx);
                connection.expire(StringToArrayUTF8(key), seconds);
                return null;
            }
        });
    }


    private byte[] StringToArrayUTF8(String string){
        byte[] strings = null;
        try {
            strings = string.getBytes("UTF8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        return strings;
    }
}

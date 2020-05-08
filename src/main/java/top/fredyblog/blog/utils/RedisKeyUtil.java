package top.fredyblog.blog.utils;

import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis键操作工具类
 * @author Fredy
 * @date 2020/5/7 14:25
 */
@Component
public class RedisKeyUtil {
    @Resource
    private RedisTemplate<String, Serializable> redisTemplate;

    /**
     * 功能描述：删除key
     * @param key
     */
    public void delete(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 功能描述：批量删除key
     * @param keys
     */
    public void delete(Collection<String> keys) {
        redisTemplate.delete(keys);
    }

    /**
     * 功能描述：序列化key
     * @param key
     * @return
     */
    public byte[] dump(String key) {
        return redisTemplate.dump(key);
    }

    /**
     * 功能描述：是否存在key
     * @param key
     * @return
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 功能描述：设置key的过期时间
     * @param key
     * @param timeout
     * @param unit
     * @return
     */
    public Boolean expire(String key, long timeout, TimeUnit unit) {
        return redisTemplate.expire(key, timeout, unit);
    }

    /**
     * 功能描述：查找匹配的key
     * @param pattern
     * @return
     */
    public Set<String> keys(String pattern) {
        return redisTemplate.keys(pattern);
    }

    /**
     * 功能描述：将当前数据库的key移动到给定的数据库db当中
     * @param key
     * @param dbIndex
     * @return
     */
    public Boolean move(String key, int dbIndex) {
        return redisTemplate.move(key, dbIndex);
    }

    /**
     * 功能描述：移出key的过期时间，key将持久保持
     * @param key
     * @return
     */
    public Boolean persist(String key) {
        return redisTemplate.persist(key);
    }

    /**
     * 功能描述：返回key的剩余过期时间
     * @param key
     * @param unit
     * @return
     */
    public Long getExpire(String key, TimeUnit unit) {
        return redisTemplate.getExpire(key, unit);
    }

    /**
     * 功能描述：从当前数据库中随机返回一个key
     * @return
     */
    public String randomKey() {
        return redisTemplate.randomKey();
    }

    /**
     * 功能描述：仅当newKey不存在时，将oldKey改名为newKey
     * @param oldKey
     * @param newKey
     */
    public void rename(String oldKey, String newKey) {
        redisTemplate.rename(oldKey, newKey);
    }

    /**
     * 功能描述：返回key所存储的值类型
     * @param key
     * @return
     */
    public DataType type(String key) {
        return redisTemplate.type(key);
    }
}

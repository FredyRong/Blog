package top.fredyblog.blog.utils;

import org.springframework.data.redis.core.*;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author Fredy
 * @date 2020/5/7 14:46
 */
@Component
public class RedisValueUtil {
    @Resource
    private ValueOperations<String, Serializable> valueOperations;
    @Resource
    private HashOperations<String, String, Serializable> hashOperations;
    @Resource
    private ListOperations<String, Serializable> listOperations;
    @Resource
    private SetOperations<String, Serializable> setOperations;
    @Resource
    private ZSetOperations<String, Serializable> zSetOperations;

    /** -------------------string相关操作--------------------- */

    /**
     * 功能描述: 设置指定 key 的值
     * @param key
     * @param value
     **/
    public void set(String key, Serializable value) {
        valueOperations.set(key, value);
    }

    /**
     * 功能描述: 获取指定 key 的值
     * @param key
     * @return java.io.Serializable
     **/
    public Serializable get(String key) {
        return valueOperations.get(key);
    }

    /**
     * 功能描述: 返回 key 中字符串值的子字符
     * @param key
     * @param start
     * @param end
     * @return
     **/
    public String getRange(String key, long start, long end) {
        return valueOperations.get(key, start, end);
    }

    /**
     * 功能描述: 将给定 key 的值设为 value ，并返回 key 的旧值(old value)
     * @param key
     * @param value
     * @return
     **/
    public Serializable getAndSet(String key, String value) {
        return valueOperations.getAndSet(key, value);
    }

    /**
     * 功能描述: 对 key 所储存的字符串值，获取指定偏移量上的位(bit)
     * @param key
     * @param offset
     * @return
     **/
    public Boolean getBit(String key, long offset) {
        return valueOperations.getBit(key, offset);
    }

    /**
     * 功能描述: 批量获取
     * @param keys
     * @return
     **/
    public List<Serializable> multiGet(Collection<String> keys) {
        return valueOperations.multiGet(keys);
    }

    /**
     * 功能描述: 设置ASCII码, 字符串'a'的ASCII码是97, 转为二进制是'01100001', 此方法是将二进制第offset位值变为value
     * @param key
     * @param offset
     * @param value
     * @return
     **/
    public boolean setBit(String key, long offset, boolean value) {
        return valueOperations.setBit(key, offset, value);
    }

    /**
     * 功能描述: 将值 value 关联到 key ，并将 key 的过期时间设为 timeout
     * @param key
     * @param value
     * @param timeout
     * @param unit    时间单位, 天:TimeUnit.DAYS 小时:TimeUnit.HOURS 分钟:TimeUnit.MINUTES 秒:TimeUnit.SECONDS
     *                毫秒:TimeUnit.MILLISECONDS
     **/
    public void setEx(String key, String value, long timeout, TimeUnit unit) {
        valueOperations.set(key, value, timeout, unit);
    }

    /**
     * 功能描述: 只有在 key 不存在时设置 key 的值
     * @param key
     * @param value
     * @return
     **/
    public boolean setIfAbsent(String key, String value) {
        return valueOperations.setIfAbsent(key, value);
    }

    /**
     * 功能描述: 用 value 参数覆写给定 key 所储存的字符串值，从偏移量 offset 开始
     * @param key
     * @param value
     * @param offset
     **/
    public void setRange(String key, String value, long offset) {
        valueOperations.set(key, value, offset);
    }

    /**
     * 功能描述: 获取字符串的长度
     * @param key
     * @return
     **/
    public Long size(String key) {
        return valueOperations.size(key);
    }

    /**
     * 功能描述: 批量添加
     * @param maps
     **/
    public void multiSet(Map<String, Serializable> maps) {
        valueOperations.multiSet(maps);
    }

    /**
     * 功能描述: 同时设置一个或多个 key-value 对，当且仅当所有给定 key 都不存在
     * @param maps
     * @return
     **/
    public boolean multiSetIfAbsent(Map<String, Serializable> maps) {
        return valueOperations.multiSetIfAbsent(maps);
    }

    /**
     * 功能描述: 自增1
     * @param key
     * @return
     **/
    public Long incr(String key) {
        return valueOperations.increment(key, 1);
    }

    /**
     * 功能描述: 自减1
     * @param key
     * @return
     **/
    public Long decr(String key) {
        return valueOperations.increment(key, -1);
    }

    /**
     * 功能描述: 自增（负数则为自减）——long类型
     * @param key
     * @param increment
     * @return
     **/
    public Long incrBy(String key, long increment) {
        return valueOperations.increment(key, increment);
    }

    /**
     * 功能描述: 自增（负数则为自减）——double类型
     * @param key
     * @param increment
     * @return
     **/
    public Double incrByFloat(String key, double increment) {
        return valueOperations.increment(key, increment);
    }

    /**
     * 功能描述: 追加到末尾
     * @param key
     * @param value
     * @return
     **/
    public Integer append(String key, String value) {
        return valueOperations.append(key, value);
    }

    /** -------------------hash相关操作------------------------- */

    /**
     * 功能描述: 获取存储在哈希表中指定字段的值
     * @param key
     * @param field
     * @return
     **/
    public Serializable hGet(String key, String field) {
        return hashOperations.get(key, field);
    }

    /**
     * 功能描述: 获取所有给定字段的值
     * @param key
     * @return
     **/
    public Map<String, Serializable> hGetAll(String key) {
        return hashOperations.entries(key);
    }

    /**
     * 功能描述: 获取所有给定字段的值
     * @param key
     * @param fields
     * @return
     **/
    public List<Serializable> hMultiGet(String key, Collection<String> fields) {
        return hashOperations.multiGet(key, fields);
    }

    /**
     * 功能描述: 指定key下新增元素
     * @param key
     * @param hashKey
     * @param value
     **/
    public void hPut(String key, String hashKey, Serializable value) {
        hashOperations.put(key, hashKey, value);
    }

    /**
     * 功能描述: 指定key下批量新增元素
     * @param key  1
     * @param maps 2
     * @author
     **/
    public void hPutAll(String key, Map<String, Serializable> maps) {
        hashOperations.putAll(key, maps);
    }

    /**
     * 功能描述: 仅当hashKey不存在时才设置
     * @param key
     * @param hashKey
     * @param value
     * @return
     **/
    public Boolean hPutIfAbsent(String key, String hashKey, Serializable value) {
        return hashOperations.putIfAbsent(key, hashKey, value);
    }

    /**
     * 功能描述: 删除一个或多个哈希表字段
     * @param key
     * @param fields
     * @return
     **/
    public Long hDelete(String key, String... fields) {
        return hashOperations.delete(key, fields);
    }

    /**
     * 功能描述: 查看哈希表 key 中，指定的字段是否存在
     * @param key   1
     * @param field 2
     * @return
     **/
    public boolean hExists(String key, String field) {
        return hashOperations.hasKey(key, field);
    }

    /**
     * 功能描述: 为哈希表 key 中的指定字段的整数值自增1
     * @param key   1
     * @param field 2
     * @return
     **/
    public Long hIncr(String key, String field) {
        return hashOperations.increment(key, field, 1);
    }

    /**
     * 功能描述: 为哈希表 key 中的指定字段的整数值自减1
     * @param key   1
     * @param field 2
     * @return
     **/
    public Long hDecr(String key, String field) {
        return hashOperations.increment(key, field, -1);
    }

    /**
     * 功能描述: 为哈希表 key 中的指定字段的整数值加上增量 increment(负数为自减)
     * @param key       1
     * @param field     2
     * @param increment 3
     * @return
     **/
    public Long hIncrBy(String key, String field, long increment) {
        return hashOperations.increment(key, field, increment);
    }

    /**
     * 功能描述: 为哈希表 key 中的指定字段的double值加上增量 increment(负数为自减)
     * @param key   1
     * @param field 2
     * @param delta 3
     * @return
     **/
    public Double hIncrByFloat(String key, String field, double delta) {
        return hashOperations.increment(key, field, delta);
    }

    /**
     * 功能描述: 获取指定key下的哈希表中的字段
     * @param key
     * @return
     **/
    public Set<String> hKeys(String key) {
        return hashOperations.keys(key);
    }

    /**
     * 功能描述: 获取哈希表中字段的数量
     * @param key
     * @return
     **/
    public Long hSize(String key) {
        return hashOperations.size(key);
    }

    /**
     * 功能描述: 获取哈希表中所有值
     * @param key
     * @return
     **/
    public List<Serializable> hValues(String key) {
        return hashOperations.values(key);
    }

    /**
     * 功能描述: 迭代哈希表中的键值对
     * @param key
     * @param options
     * @return
     **/
    public Cursor<Map.Entry<String, Serializable>> hScan(String key, ScanOptions options) {
        return hashOperations.scan(key, options);
    }

    /** ------------------------list相关操作---------------------------- */

    /**
     * 功能描述: 通过索引获取列表中的元素
     * @param key
     * @param index
     * @return java.lang.String
     * @author
     **/
    public Serializable lIndex(String key, long index) {
        return listOperations.index(key, index);
    }

    /**
     * 功能描述: 获取列表指定范围内的元素
     * @param key
     * @param start
     * @param end
     * @return
     **/
    public List<Serializable> lRange(String key, long start, long end) {
        return listOperations.range(key, start, end);
    }

    /**
     * 功能描述: list头部插入数据
     * @param key
     * @param value
     * @return
     **/
    public Long lLeftPush(String key, Serializable value) {
        return listOperations.leftPush(key, value);
    }

    /**
     * 功能描述: list头部批量插入数据
     * @param key
     * @param value
     * @return
     **/
    public Long lLeftPushAll(String key, Serializable... value) {
        return listOperations.leftPushAll(key, value);
    }

    /**
     * 功能描述: list头部批量插入数据
     * @param key
     * @param value
     * @return
     **/
    public Long lLeftPushAll(String key, Collection<Serializable> value) {
        return listOperations.leftPushAll(key, value);
    }

    /**
     * 功能描述: 当list存在的时候才头插入数据
     * @param key
     * @param value
     * @return
     **/
    public Long lLeftPushIfPresent(String key, Serializable value) {
        return listOperations.leftPushIfPresent(key, value);
    }

    /**
     * 功能描述: 如果pivot存在,再pivot前面添加
     * @param key
     * @param pivot
     * @param value
     * @return
     **/
    public Long lLeftPush(String key, Serializable pivot, Serializable value) {
        return listOperations.leftPush(key, pivot, value);
    }

    /**
     * 功能描述: list尾部插入数据
     * @param key
     * @param value
     * @return
     **/
    public Long lRightPush(String key, Serializable value) {
        return listOperations.rightPush(key, value);
    }

    /**
     * 功能描述: list尾部批量插入数据
     * @param key
     * @param value
     * @return
     **/
    public Long lRightPushAll(String key, Serializable... value) {
        return listOperations.rightPushAll(key, value);
    }

    /**
     * 功能描述: list尾部批量插入数据
     * @param key
     * @param value
     * @return
     **/
    public Long lRightPushAll(String key, Collection<Serializable> value) {
        return listOperations.rightPushAll(key, value);
    }

    /**
     * 功能描述: 为已存在的列表添加值（不存在就不操作）
     * @param key
     * @param value
     * @return
     **/
    public Long lRightPushIfPresent(String key, Serializable value) {
        return listOperations.rightPushIfPresent(key, value);
    }

    /**
     * 功能描述: 在pivot元素的右边添加值
     * @param key
     * @param pivot
     * @param value
     * @return
     **/
    public Long lRightPush(String key, Serializable pivot, Serializable value) {
        return listOperations.rightPush(key, pivot, value);
    }

    /**
     * 功能描述: 通过索引设置列表元素的值
     * @param key
     * @param index
     * @param value
     **/
    public void lSet(String key, long index, Serializable value) {
        listOperations.set(key, index, value);
    }

    /**
     * 功能描述: 移出并获取列表的第一个元素
     * @param key 1
     * @return java.lang.String
     **/
    public Serializable lLeftPop(String key) {
        return listOperations.leftPop(key);
    }

    /**
     * 功能描述: 移出并获取列表的第一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     * @param key
     * @param timeout
     * @param unit
     * @return
     **/
    public Serializable lBLeftPop(String key, long timeout, TimeUnit unit) {
        return listOperations.leftPop(key, timeout, unit);
    }

    /**
     * 功能描述: 移除并获取列表最后一个元素
     * @param key 1
     * @return
     **/
    public Serializable lRightPop(String key) {
        return listOperations.rightPop(key);
    }

    /**
     * 功能描述: 移出并获取列表的最后一个元素， 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     * @param key
     * @param timeout
     * @param unit
     * @return
     **/
    public Serializable lBRightPop(String key, long timeout, TimeUnit unit) {
        return listOperations.rightPop(key, timeout, unit);
    }

    /**
     * 功能描述: 移除sourceKey列表的最后一个元素，并将该元素添加到另一个destinationKey列表头位置并返回
     * @param sourceKey
     * @param destinationKey
     * @return
     **/
    public Serializable lRightPopAndLeftPush(String sourceKey, String destinationKey) {
        return listOperations.rightPopAndLeftPush(sourceKey,
                destinationKey);
    }

    /**
     * 功能描述: 移除sourceKey列表的最后一个元素，并将该元素添加到另一个destinationKey列表头位置并返回；
     * 如果列表没有元素会阻塞列表直到等待超时或发现可弹出元素为止
     * @param sourceKey
     * @param destinationKey
     * @param timeout
     * @param unit
     * @return
     **/
    public Serializable lBRightPopAndLeftPush(String sourceKey, String destinationKey,
                                              long timeout, TimeUnit unit) {
        return listOperations.rightPopAndLeftPush(sourceKey,
                destinationKey, timeout, unit);
    }

    /**
     * 功能描述: 删除集合中值等于value的元素
     * @param key
     * @param index index=0, 删除所有值等于value的元素; index>0, 从头部开始删除第一个值等于value的元素；
     *              index<0, 从尾部开始删除第一个值等于value的元素;
     * @param value
     * @return
     **/
    public Long lRemove(String key, long index, Serializable value) {
        return listOperations.remove(key, index, value);
    }

    /**
     * 功能描述: 裁剪list
     * @param key
     * @param start
     * @param end
     **/
    public void lTrim(String key, long start, long end) {
        listOperations.trim(key, start, end);
    }

    /**
     * 功能描述: 获取列表长度
     * @param key 1
     * @return
     **/
    public Long lLen(String key) {
        return listOperations.size(key);
    }

    /** --------------------set相关操作-------------------------- */

    /**
     * 功能描述: set添加元素
     * @param key    1
     * @param values 2
     * @return
     **/
    public Long sAdd(String key, Serializable... values) {
        return setOperations.add(key, values);
    }

    /**
     * 功能描述: set移除元素
     * @param key    1
     * @param values 2
     * @return
     **/
    public Long sRemove(String key, Serializable... values) {
        return setOperations.remove(key, values);
    }

    /**
     * 功能描述: 移除并返回集合的一个随机元素
     * @param key 1
     * @return
     **/
    public Serializable sPop(String key) {
        return setOperations.pop(key);
    }

    /**
     * 功能描述: 将元素value从一个集合移到另一个集合
     * @param key
     * @param value
     * @param destKey
     * @return
     **/
    public Boolean sMove(String key, Serializable value, String destKey) {
        return setOperations.move(key, value, destKey);
    }

    /**
     * 功能描述: 获取集合的大小
     * @param key 1
     * @return java.lang.Long
     * @author
     **/
    public Long sSize(String key) {
        return setOperations.size(key);
    }

    /**
     * 功能描述: 判断集合是否包含value
     * @param key
     * @param value
     * @return
     **/
    public Boolean sIsMember(String key, Object value) {
        return setOperations.isMember(key, value);
    }

    /**
     * 功能描述: 获取两个集合的交集
     * @param key
     * @param otherKey
     * @return
     **/
    public Set<Serializable> sIntersect(String key, String otherKey) {
        return setOperations.intersect(key, otherKey);
    }

    /**
     * 功能描述: 获取key集合与多个集合的交集
     * @param key
     * @param otherKeys
     * @return
     **/
    public Set<Serializable> sIntersect(String key, Collection<String> otherKeys) {
        return setOperations.intersect(key, otherKeys);
    }

    /**
     * 功能描述: key集合与otherKey集合的交集存储到destKey集合中
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     **/
    public Long sIntersectAndStore(String key, String otherKey, String destKey) {
        return setOperations.intersectAndStore(key, otherKey,
                destKey);
    }

    /**
     * 功能描述: key集合与多个集合的交集存储到destKey集合中
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     **/
    public Long sIntersectAndStore(String key, Collection<String> otherKeys,
                                   String destKey) {
        return setOperations.intersectAndStore(key, otherKeys,
                destKey);
    }

    /**
     * 功能描述: 获取两个集合的并集
     * @param key
     * @param otherKeys
     * @return
     **/
    public Set<Serializable> sUnion(String key, String otherKeys) {
        return setOperations.union(key, otherKeys);
    }

    /**
     * 功能描述: 获取key集合与多个集合的并集
     * @param key
     * @param otherKeys
     * @return
     **/
    public Set<Serializable> sUnion(String key, Collection<String> otherKeys) {
        return setOperations.union(key, otherKeys);
    }

    /**
     * 功能描述: key集合与otherKey集合的并集存储到destKey中
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     **/
    public Long sUnionAndStore(String key, String otherKey, String destKey) {
        return setOperations.unionAndStore(key, otherKey, destKey);
    }

    /**
     * 功能描述: key集合与多个集合的并集存储到destKey中
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     **/
    public Long sUnionAndStore(String key, Collection<String> otherKeys,
                               String destKey) {
        return setOperations.unionAndStore(key, otherKeys, destKey);
    }

    /**
     * 功能描述: 获取两个集合的差集
     * @param key
     * @param otherKey
     * @return
     **/
    public Set<Serializable> sDifference(String key, String otherKey) {
        return setOperations.difference(key, otherKey);
    }

    /**
     * 功能描述: 获取key集合与多个集合的差集
     * @param key
     * @param otherKeys
     * @return
     **/
    public Set<Serializable> sDifference(String key, Collection<String> otherKeys) {
        return setOperations.difference(key, otherKeys);
    }

    /**
     * 功能描述: key集合与otherKey集合的差集存储到destKey中
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     **/
    public Long sDifference(String key, String otherKey, String destKey) {
        return setOperations.differenceAndStore(key, otherKey,
                destKey);
    }

    /**
     * 功能描述: key集合与多个集合的差集存储到destKey中
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     **/
    public Long sDifference(String key, Collection<String> otherKeys,
                            String destKey) {
        return setOperations.differenceAndStore(key, otherKeys,
                destKey);
    }

    /**
     * 功能描述: 获取集合所有元素
     * @param key
     * @return
     **/
    public Set<Serializable> setMembers(String key) {
        return setOperations.members(key);
    }

    /**
     * 功能描述: 随机获取集合中的一个元素
     * @param key
     * @return
     **/
    public Serializable sRandomMember(String key) {
        return setOperations.randomMember(key);
    }

    /**
     * 功能描述: 随机获取集合中count个元素
     * @param key
     * @param count
     * @return
     **/
    public List<Serializable> sRandomMembers(String key, long count) {
        return setOperations.randomMembers(key, count);
    }

    /**
     * 功能描述: 随机获取集合中count个元素并且去除重复的
     * @param key
     * @param count
     * @return
     **/
    public Set<Serializable> sDistinctRandomMembers(String key, long count) {
        return setOperations.distinctRandomMembers(key, count);
    }

    /**
     * 功能描述:返回遍历集合的迭代器
     * @param key
     * @param options
     * @return
     **/
    public Cursor<Serializable> sScan(String key, ScanOptions options) {
        return setOperations.scan(key, options);
    }


    /**------------------zSet相关操作--------------------------------*/

    /**
     * 功能描述: 添加元素,有序集合是按照元素的score值由小到大排列
     * @param key
     * @param value
     * @param score
     * @return
     **/
    public Boolean zAdd(String key, Serializable value, double score) {
        return zSetOperations.add(key, value, score);
    }

    /**
     * 功能描述: 批量添加元素
     * @param key
     * @param values
     * @return
     **/
    public Long zAdd(String key, Set<ZSetOperations.TypedTuple<Serializable>> values) {
        return zSetOperations.add(key, values);
    }

    /**
     * 功能描述: 批量移除元素
     * @param key
     * @param values
     * @return
     **/
    public Long zRemove(String key, Serializable... values) {
        return zSetOperations.remove(key, values);
    }

    /**
     * 功能描述: 增加元素的score值，并返回增加后的值
     * @param key
     * @param value
     * @param delta
     * @return
     **/
    public Double zIncrementScore(String key, Serializable value, double delta) {
        return zSetOperations.incrementScore(key, value, delta);
    }

    /**
     * 功能描述: 返回元素在集合的排名,有序集合是按照元素的score值由小到大排列
     * @param key
     * @param value
     * @return
     **/
    public Long zRank(String key, Serializable value) {
        return zSetOperations.rank(key, value);
    }

    /**
     * 功能描述: 返回元素在集合的排名,按元素的score值由大到小排列
     * @param key
     * @param value
     * @return
     **/
    public Long zReverseRank(String key, Serializable value) {
        return zSetOperations.reverseRank(key, value);
    }

    /**
     * 功能描述: 获取集合的元素, 从小到大排序
     * @param key
     * @param start
     * @param end
     * @return
     **/
    public Set<Serializable> zRange(String key, long start, long end) {
        return zSetOperations.range(key, start, end);
    }

    /**
     * 功能描述: 获取集合元素, 并且把score值也获取
     * @param key
     * @param start
     * @param end
     * @return
     **/
    public Set<ZSetOperations.TypedTuple<Serializable>> zRangeWithScores(String key, long start,
                                                                         long end) {
        return zSetOperations.rangeWithScores(key, start, end);
    }

    /**
     * 功能描述: 根据Score值查询集合元素
     * @param key
     * @param min
     * @param max
     * @return
     **/
    public Set<Serializable> zRangeByScore(String key, double min, double max) {
        return zSetOperations.rangeByScore(key, min, max);
    }

    /**
     * 功能描述: 根据Score值查询集合元素, 从小到大排序
     * @param key
     * @param min
     * @param max
     * @return
     **/
    public Set<ZSetOperations.TypedTuple<Serializable>> zRangeByScoreWithScores(String key,
                                                                                double min, double max) {
        return zSetOperations.rangeByScoreWithScores(key, min, max);
    }

    /**
     * 功能描述: 在指定位置范围内根据Score值查询集合元素, 从小到大排序
     * @param key
     * @param min
     * @param max
     * @param start
     * @param end
     * @return
     **/
    public Set<ZSetOperations.TypedTuple<Serializable>> zRangeByScoreWithScores(String key,
                                                                                double min, double max, long start, long end) {
        return zSetOperations.rangeByScoreWithScores(key, min, max,
                start, end);
    }

    /**
     * 功能描述: 获取集合的元素, 从大到小排序
     * @param key
     * @param start
     * @param end
     * @return
     **/
    public Set<Serializable> zReverseRange(String key, long start, long end) {
        return zSetOperations.reverseRange(key, start, end);
    }

    /**
     * 功能描述: 获取集合的元素, 从大到小排序, 并返回score值
     * @param key
     * @param start
     * @param end
     * @return
     **/
    public Set<ZSetOperations.TypedTuple<Serializable>> zReverseRangeWithScores(String key,
                                                                                long start, long end) {
        return zSetOperations.reverseRangeWithScores(key, start,
                end);
    }

    /**
     * 功能描述: 根据Score值查询集合元素, 从大到小排序
     * @param key
     * @param min
     * @param max
     * @return
     **/
    public Set<Serializable> zReverseRangeByScore(String key, double min,
                                                  double max) {
        return zSetOperations.reverseRangeByScore(key, min, max);
    }

    /**
     * 功能描述: 根据Score值查询集合元素, 从大到小排序
     * @param key
     * @param min
     * @param max
     * @return
     **/
    public Set<ZSetOperations.TypedTuple<Serializable>> zReverseRangeByScoreWithScores(String key, double min, double max) {
        return zSetOperations.reverseRangeByScoreWithScores(key,
                min, max);
    }

    /**
     * 功能描述: 指定范围内根据Score值查询集合元素, 从大到小排序
     * @param key
     * @param min
     * @param max
     * @param start
     * @param end
     * @return
     **/
    public Set<Serializable> zReverseRangeByScore(String key, double min, double max, long start, long end) {
        return zSetOperations.reverseRangeByScore(key, min, max, start, end);
    }

    /**
     * 功能描述: 根据score值获取集合元素数量
     * @param key
     * @param min
     * @param max
     * @return
     **/
    public Long zCount(String key, double min, double max) {
        return zSetOperations.count(key, min, max);
    }

    /**
     * 功能描述: 获取集合大小
     * @param key
     * @return
     **/
    public Long zSize(String key) {
        return zSetOperations.size(key);
    }

    /**
     * 功能描述: 获取集合大小
     * @param key
     * @return
     **/
    public Long zZCard(String key) {
        return zSetOperations.zCard(key);
    }

    /**
     * 功能描述: 获取集合中value元素的score值
     * @param key
     * @param value
     * @return
     **/
    public Double zScore(String key, Serializable value) {
        return zSetOperations.score(key, value);
    }

    /**
     * 功能描述: 移除指定索引位置的成员
     * @param key
     * @param start
     * @param end
     * @return
     **/
    public Long zRemoveRange(String key, long start, long end) {
        return zSetOperations.removeRange(key, start, end);
    }

    /**
     * 功能描述: 根据指定的score值的范围来移除成员
     * @param key
     * @param min
     * @param max
     * @return
     **/
    public Long zRemoveRangeByScore(String key, double min, double max) {
        return zSetOperations.removeRangeByScore(key, min, max);
    }

    /**
     * 功能描述: 获取key和otherKey的并集并存储在destKey中
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     **/
    public Long zUnionAndStore(String key, String otherKey, String destKey) {
        return zSetOperations.unionAndStore(key, otherKey, destKey);
    }

    /**
     * 功能描述: 获取key和其余多个key集合的并集并存储在destKey中
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     **/
    public Long zUnionAndStore(String key, Collection<String> otherKeys,
                               String destKey) {
        return zSetOperations
                .unionAndStore(key, otherKeys, destKey);
    }

    /**
     * 功能描述: 获取key和otherKey的交集并存储在destKey中
     * @param key
     * @param otherKey
     * @param destKey
     * @return
     **/
    public Long zIntersectAndStore(String key, String otherKey,
                                   String destKey) {
        return zSetOperations.intersectAndStore(key, otherKey,
                destKey);
    }

    /**
     * 功能描述: 获取key和其余多个集合的交集并存储在destKey中
     * @param key
     * @param otherKeys
     * @param destKey
     * @return
     **/
    public Long zIntersectAndStore(String key, Collection<String> otherKeys,
                                   String destKey) {
        return zSetOperations.intersectAndStore(key, otherKeys,
                destKey);
    }

    /**
     * 功能描述: 返回遍历指定key对应的元素列表的集合的迭代器
     * @param key
     * @param options
     * @return
     **/
    public Cursor<ZSetOperations.TypedTuple<Serializable>> zScan(String key, ScanOptions options) {
        return zSetOperations.scan(key, options);
    }
}

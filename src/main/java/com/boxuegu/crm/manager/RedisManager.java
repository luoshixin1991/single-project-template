package com.boxuegu.crm.manager;

import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import javax.annotation.Resource;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 操作redis的类
 * 基于spring和redis的redisTemplate工具类
 * 针对所有的hash 都是以h开头的方法
 * 针对所有的Set 都是以s开头的方法
 * 针对所有的List 都是以l开头的方法
 *
 * @author luoshixin
 * @date 2018/9/19 下午5:21
 **/
@Component
public class RedisManager {

    @Resource
    private RedisTemplate<String, Object> redisTemplate;

    /**
     * 指定缓存失效时间
     *
     * @param key  键
     * @param time 时间(秒)
     */
    public void expire(String key, long time) {
        redisTemplate.expire(key, time, TimeUnit.SECONDS);
    }

    /**
     * 根据key 获取过期时间
     *
     * @param key 键 不能为null
     * @return 时间(秒) 返回0代表为永久有效
     */
    public Long getExpire(String key) {
        return redisTemplate.getExpire(key, TimeUnit.SECONDS);
    }

    /**
     * 判断key是否存在
     *
     * @param key 键
     * @return true 存在 false不存在
     */
    public Boolean hasKey(String key) {
        return redisTemplate.hasKey(key);
    }

    /**
     * 删除缓存
     *
     * @param keys 可以传一个值 或多个
     */
    public void delete(String... keys) {
        redisTemplate.delete(Stream.of(keys).collect(Collectors.toList()));
    }


    /**
     * 普通缓存获取
     *
     * @param key 键
     * @return 值
     */
    public Object get(String key) {
        return redisTemplate.opsForValue().get(key);
    }

    /**
     * 普通缓存放入
     *
     * @param key   键
     * @param value 值
     */
    public void set(String key, Object value) {
        redisTemplate.opsForValue().set(key, value);
    }

    /**
     * 普通缓存放入并设置时间
     *
     * @param key   键
     * @param value 值
     * @param time  时间(毫秒) time要大于0 如果time小于等于0 将设置无限期
     */
    public void set(String key, Object value, long time) {
        redisTemplate.opsForValue().set(key, value, time, TimeUnit.SECONDS);
    }

    /**
     * 递增
     *
     * @param key 键
     * @param by  要增加几
     */
    public Long increment(String key, long by) {
        return redisTemplate.opsForValue().increment(key, by);
    }

    /**
     * 递减
     *
     * @param key 键
     * @param by  要减少几(小于0)
     */
    public Long decrement(String key, long by) {
        return redisTemplate.opsForValue().increment(key, -by);
    }

    /**
     * HashGet
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return 值
     */
    public Object hGet(String key, String item) {
        return redisTemplate.opsForHash().get(key, item);
    }

    /**
     * 获取hashKey对应的所有键值
     *
     * @param key 键
     * @return 对应的多个键值
     */
    public Map<Object, Object> hEntries(String key) {
        return redisTemplate.opsForHash().entries(key);
    }

    /**
     * HashSet
     *
     * @param key 键
     * @param map 对应多个键值
     */
    public void hPutAll(String key, Map<String, Object> map) {
        redisTemplate.opsForHash().putAll(key, map);
    }

    /**
     * HashSet 并设置时间
     *
     * @param key  键
     * @param map  对应多个键值
     * @param time 时间(秒)
     */
    public void hPutAll(String key, Map<String, Object> map, long time) {
        redisTemplate.opsForHash().putAll(key, map);
        expire(key, time);
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @return true 成功 false失败
     */
    public void hPut(String key, String item, Object value) {
        redisTemplate.opsForHash().put(key, item, value);
    }

    /**
     * 向一张hash表中放入数据,如果不存在将创建
     *
     * @param key   键
     * @param item  项
     * @param value 值
     * @param time  时间(秒)  注意:如果已存在的hash表有时间,这里将会替换原有的时间
     */
    public void hPut(String key, String item, Object value, long time) {
        redisTemplate.opsForHash().put(key, item, value);
        expire(key, time);
    }

    /**
     * 删除hash表中的值
     *
     * @param key  键 不能为null
     * @param item 项 可以使多个 不能为null
     */
    public void hDelete(String key, Object... item) {
        redisTemplate.opsForHash().delete(key, item);
    }

    /**
     * 判断hash表中是否有该项的值
     *
     * @param key  键 不能为null
     * @param item 项 不能为null
     * @return true 存在 false不存在
     */
    public Boolean hHasKey(String key, String item) {
        return redisTemplate.opsForHash().hasKey(key, item);
    }

    /**
     * hash递增 如果不存在,就会创建一个 并把新增后的值返回
     *
     * @param key  键
     * @param item 项
     * @param by   要增加几(大于0)
     * @return
     */
    public Double hIncrement(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, by);
    }

    /**
     * hash递减
     *
     * @param key  键
     * @param item 项
     * @param by   要减少记(小于0)
     * @return
     */
    public Double hDecrement(String key, String item, double by) {
        return redisTemplate.opsForHash().increment(key, item, -by);
    }

    /**
     * 根据key获取Set中的所有值
     *
     * @param key 键
     * @return
     */
    public Set<Object> sMembers(String key) {
        return redisTemplate.opsForSet().members(key);
    }

    /**
     * 根据value从一个set中查询,是否存在
     *
     * @param key   键
     * @param value 值
     * @return true 存在 false不存在
     */
    public Boolean sIsMember(String key, Object value) {
        return redisTemplate.opsForSet().isMember(key, value);
    }

    /**
     * 将数据放入set缓存
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public Long sAdd(String key, Object... values) {
        return redisTemplate.opsForSet().add(key, values);
    }

    /**
     * 将set数据放入缓存
     *
     * @param key    键
     * @param time   时间(秒)
     * @param values 值 可以是多个
     * @return 成功个数
     */
    public Long sAdd(String key, long time, Object... values) {
        Long count = redisTemplate.opsForSet().add(key, values);
        expire(key, time);
        return count;
    }

    /**
     * 获取set缓存的长度
     *
     * @param key 键
     * @return
     */
    public Long sSize(String key) {
        return redisTemplate.opsForSet().size(key);
    }

    /**
     * 移除值为value的
     *
     * @param key    键
     * @param values 值 可以是多个
     * @return 移除的个数
     */
    public Long sRemove(String key, Object... values) {
        return redisTemplate.opsForSet().remove(key, values);
    }

    /**
     * 获取list缓存的内容
     *
     * @param key   键
     * @param start 开始
     * @param end   结束  0 到 -1代表所有值
     * @return
     */
    public List<Object> lRange(String key, long start, long end) {
        return redisTemplate.opsForList().range(key, start, end);
    }

    /**
     * 获取list缓存的长度
     *
     * @param key 键
     * @return
     */
    public Long lSize(String key) {
        return redisTemplate.opsForList().size(key);
    }

    /**
     * 通过key 从左起获取list中的值
     *
     * @param key 键
     * @return
     */
    public Object lLeftPop(String key) {
        return redisTemplate.opsForList().leftPop(key);
    }

    /**
     * 通过key 从右起获取list中的值
     *
     * @param key 键
     * @return
     */
    public Object rRightPop(String key) {
        return redisTemplate.opsForList().rightPop(key);
    }

    /**
     * 通过索引 获取list中的值
     *
     * @param key   键
     * @param index 索引  index>=0时， 0 表头，1 第二个元素，依次类推；index<0时，-1，表尾，-2倒数第二个元素，依次类推
     * @return
     */
    public Object lIndex(String key, long index) {
        return redisTemplate.opsForList().index(key, index);
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     */
    public void lRightPush(String key, Object value) {
        redisTemplate.opsForList().rightPush(key, value);
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     */
    public void lRightPush(String key, Object value, long time) {
        redisTemplate.opsForList().rightPush(key, value);
        expire(key, time);
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     */
    public void lRightPushAll(String key, List<Object> value) {
        redisTemplate.opsForList().rightPushAll(key, value);
    }

    /**
     * 将list放入缓存
     *
     * @param key   键
     * @param value 值
     * @param time  时间(秒)
     */
    public void lRightPushAll(String key, List<Object> value, long time) {
        redisTemplate.opsForList().rightPushAll(key, value);
        expire(key, time);
    }

    /**
     * 根据索引修改list中的某条数据
     *
     * @param key   键
     * @param index 索引
     * @param value 值
     */
    public void lSet(String key, long index, Object value) {
        redisTemplate.opsForList().set(key, index, value);
    }

    /**
     * 移除N个值为value
     *
     * @param key   键
     * @param count 移除多少个
     * @param value 值
     * @return 移除的个数
     */
    public Long lRemove(String key, long count, Object value) {
        return redisTemplate.opsForList().remove(key, count, value);
    }

    /**
     * 利用mGet批量获取redis中的内容，
     * 利用mGet批量获取和利用PipeLine批量获取两者底层都是用到execute方法，
     * multiGet在使用连接是没用到pipeline，一条命令直接传给Redis，Redis返回结果。
     * 而executePipelined实际上一条或多条命令，但是共用一个连接。
     *
     * @param keys
     * @param <T>
     * @return
     */
    public <T> List<T> multiGet(List<String> keys) {
        return (List<T>) redisTemplate.opsForValue().multiGet(keys);
    }

//    /**
//     * 利用PipeLine批量获取redis中的内容
//     * 利用mGet批量获取和利用PipeLine批量获取两者底层都是用到execute方法，
//     * multiGet在使用连接是没用到pipeline，一条命令直接传给Redis，Redis返回结果。
//     * 而executePipelined实际上一条或多条命令，但是共用一个连接。
//     *
//     * @param keys
//     * @param <T>
//     * @return
//     */
//    public <T> List<T> getBatchByPipeLine(List<String> keys) {
//        return (List<T> )redisTemplate.executePipelined(new RedisCallback<T>() {
//            @Override
//            public T doInRedis(RedisConnection redisConnection) throws DataAccessException {
//                // StringRedisConnection conn = redisConnection;
//                for (String key : keys) {
//                    byte[] value = redisConnection.get(serializeKey(key));
//                }
//                return null;
//            }
//        });
//    }

    /**
     * 加上分布式锁
     *
     * @param key     键
     * @param value   值
     * @param timeout 过期时间，单位秒
     * @return 是否成功加上锁
     */
    public Boolean tryLock(String key, String value, long timeout) {
        return redisTemplate.opsForValue().setIfAbsent(key, value, timeout, TimeUnit.SECONDS);
    }

    /**
     * 去掉分布式锁
     *
     * @param key   键
     * @param value 值
     */
    public void unlock(String key, String value) {
        if (Objects.equals(get(key), value)) {
            redisTemplate.delete(key);
        }
    }
}

# 概念

redis是一款高性能的nosql系列的非关系型数据库

# 命令操作

1. redis的数据结构：
   * redis存储的是：key,value格式的数据，其中key都是字符串，value后5种不同的数据结构
     * value的数据结构：
       1. 字符串类型 String
       2. 哈希类型 hash ：map格式
       3. 列表类型 list：Linklist格式，可以存储重复元素
       4. 集合类型 set：不可以存储重复的元素
       5. 有序集合类型 sort edset ：不可以存储重复元素，且元素有顺序 
2. 字符串类型String
   1. 存储：set key value
   2. 获取: get key
   3. 删除：del key
3. 哈希类型hash
   1. 存储：hset key field value
   2. 获取：
      * hget key field：获取指定的field对应的值
      * hgetall key：获取所有的field和value
   3. 删除：hdel key field 
4. 列表类型 list：简单的字符串列表，按照插入顺序排序。你可以添加一个元素到列表的头部（左边）或尾部（右边）
   1. 存储：
      1. lpush key value :从左边存入列表
      2. rpush key value：从与右边存入列表
   2. 获取：lrange key start end :范围获取
   3. 删除：
      * lpop key: 从列表的最左边移除一个元素，并将元素返回
      * rpop key: 从列表的最右边移除一个元素，并将元素返回  
5. 集合类型 set：不允许重复元素
   1. 存储：sadd key value
   2. 获取：smembers key : 获取set集合中所有元素
   3. 删除：srem key value: 删除set集合中的某个元素
6. 有序集合类型 sortedset：不允许重复元素，且元素有顺序
   1. 存储：zadd key score value
   2. 获取：zrange key start end 
   3. 删除：zrem key value
7. 通用命令
   1. keys * : 查询所有的键
   2. type key : 获取键对应的value的类型
   3. del key : 删除指定的key value
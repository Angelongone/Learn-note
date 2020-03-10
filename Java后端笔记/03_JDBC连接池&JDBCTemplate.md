# 数据库连接池

## 概念：

其实就是一个容器（集合），存放数据连接的容器

当系统初始化好后，容器被创建，容器中会申请一些连接对象，当用户访问数据库时，从容器中获取连接对象，用户访问完之后，会将连接对象归还给容器，

## 好处：

1.节约资源

2.用户访问高效

## 实现：

1.标准接口：DataSource  javax.sql包下的

​	1）方法：

​		获取连接：getConnection()

​		归还连接：Connection.close().如果连接对象Connection是从连接池中获取的，那么调用Connection.close()方法，则不会再关闭连接。而是归还连接。

2.一般我们不会去实现它，有数据库厂商来实现

​	1）C3P0:数据库连接池技术

​	2）Druid:数据库连接池实现技术，有阿里巴巴提供的

## C3P0：数据库连接池技术

步骤：

​	1.导入jar包（三个）还有一个依赖，数据库驱动jar包

​	2.定义配置文件：

​		名称：c3p0.properties或c3p0-config.xml

​		路径：直接将文件放在src目录下即可。

​	3.创建核心对象 数据库连接池对象 CombopooledDataSource

​	4.获取连接：getConnection

```java
//        1.创建数据库连接池对象
        ComboPooledDataSource ds = new ComboPooledDataSource();
//        2.获取连接对象
        Connection conn = ds.getConnection();
```

## Druid：数据库连接池实现技术，由阿里巴巴提供的

步骤：

​	1.导入jar包 

​	2.定义配置文件：

​		是properties形式的

​		可以叫任意名称，可以放在任意目录

​	3.加载配置文件：

```	java
	Properties pro = new Properties();

		InputStream is = Druid_Demo1.class.getClassLoader().getResourceAsStream("druid.properties");pro.load(is);

		//4.获取连接池对象

		DataSource ds = DruidDataSourceFactory.createDataSource(pro);	

		//5.获取连接

		Connection conn = ds.getConnection();

```



​			3.获取数据库连接池对象：通过工厂来获取  DruidDataSourceFactory

​			4.获取连接：getConnection

​		2.定义工具类

​			1.定义一个类：JDBCUtils

​			2.提供静态代码块加载配置文件，初始化连接池对象。

​			3.提供方法

​					1.获取连接方法：通过数据库连接池获取连接对象

​					2.释放资源

​					3.获取连接池的方法				

# Spring JDBC： JDBC Template

Spring框架对JDBC的简单封装。提供了一个JDBCTemplate对象简化JDBC的开发

步骤：

​	1.导入jar包

​	2.创建JdbcTemplate对象。依赖于数据源DataSource

​		JdbcTemplate template = new JdbcTemplate(ds);

​	3.调用JdbcTemplate的方法来完成CRUD的操作

​		update():执行DML语句。增删改语句、

​		queryForMap():查询结果将结果即封装为map集合。

​				* 注意：这个方法查询的结果长度为一。

​		queryForList()：查询结果将结果即封装为list集合。

​				* 注意：将每条记录封装为有一个Map集合，再将Map集合装载到list集合中。

​		query():查询结果，将结果封装为JavaBean对象

​			query的参数：RowMapper

​				一般我们使用BeanPropertyRowMapper实现类。可以完成数据到JavaBean的自动封装

​				new BeanPropertyRowMapper<类型>(类型.class)

​		queryForObject:查询结果，将结果封装为对象。

​			一般用于聚合函数查询
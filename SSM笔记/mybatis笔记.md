# 什么是框架

它是我们软件开发中的一套解决方案，不同的框架解决的是不同的问题。

* 使用框架的好处：
  * 框架封装了很多细节，使开发人员可以使用极简的方式实现功能。大大提高开发效率。

# 三层架构

1. 表现层：
   * 是用于展示数据的
2. 业务层：
   * 是处理业务需求
3. 持久层：
   * 是和数据库交互的

# 持久层技术解决方案

JDBC技术：

* Connection
* PreparedStatement
* ResultSet

Spring的JdbcTemplate：

* Spring中对jdbc的简单封装

Apache的DBUtils:

* 它和Spring的JdbcTemplate很像，也是对Jdbc的简单封装

以上都不是框架

* JDBC是规范
* Spring的JdbcTemplate和Apache的DBUtils都只是工具类

# mybatis的概述

mybatis是一个持久层框架，用java编写的

它封装了jdbc操作的很多细节，是开发者只需要关注SQL语句本身，而无需关注注册驱动，创建连接等繁杂过程

它使用ORM思想实现了结果的封装。

ORM：

* 对象关系映射
* 简单说：就是把数据库表和实体类的属性对应起来，让我们可以炒作实体类皆可以实现操作数据表
* 实体类中的属性和数据库中的字段名称保持一致。

# mybatis的入门

mybatis的环境搭建

1. 创建Maven工程并导入坐标

   * 导入的坐标

     ```xml
         <dependencies>
             <dependency>
                 <groupId>org.mybatis</groupId>
                 <artifactId>mybatis</artifactId>
                 <version>3.4.5</version>
             </dependency>
             <dependency>
                 <groupId>mysql</groupId>
                 <artifactId>mysql-connector-java</artifactId>
                 <version>5.1.6</version>
             </dependency>
             <dependency>
                 <groupId>junit</groupId>
                 <artifactId>junit</artifactId>
                 <version>4.12</version>
             </dependency>
         </dependencies>
     ```

2. 创建实体类和dao的接口

   * 创建实体类：User.java

     ```Java
     public class User {
         private Integer id;
         private String username;
     
         public Integer getId() {
             return id;
         }
     
         public void setId(Integer id) {
             this.id = id;
         }
     
         public String getUsername() {
             return username;
         }
     
         public void setUsername(String username) {
             this.username = username;
         }
     
         @Override
         public String toString() {
             return "User{" +
                     "id=" + id +
                     ", username='" + username + '\'' +
                     '}';
         }
     }
     ```
     
   * 创建dao接口UserDao.java

     ```java
     public interface UserDao {
     
         /**
          * 查询所有操作
          * @return
          */
         List<User> findAll();
     }
     
     ```

3. 创建mybatis的主配置文件

   * SalMapConfig.xml

     ```xml
     <?xml version="1.0" encoding="UTF-8" ?>
     <!DOCTYPE configuration
             PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
             "http://mybatis.org/dtd/mybatis-3-config.dtd">
     <!--mybatis的主配置-->
     <configuration>
         <!-- 配置环境 -->
         <environments default="mysql">
             <!-- 配置mysql的环境-->
             <environment id="mysql">
                 <!--配置事务的类型-->
                 <transactionManager type="JDBC"></transactionManager>
                 <!--配置数据源（连接池）-->
                 <dataSource type="POOLED">
                     <property name="driver" value="com.mysql.jdbc.Driver"/>
                     <property name="url" value="jdbc:mysql://localhost:3306/temp"/>
                     <property name="username" value="root"/>
                     <property name="password" value="angelong"/>
                 </dataSource>
             </environment>
         </environments>
     
         <!--指定映射配置文件的位置，映射配置文件指定的是每个dao独立的配置文件-->
         <mappers>
             <mapper resource="com/ang/dao/UserDao.xml"/>
         </mappers>
     </configuration>
     ```

4. 创建映射配置文件

   * UserDao.xml

     ```xml
     <?xml version="1.0" encoding="UTF-8" ?>
     <!DOCTYPE mapper
             PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
             "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
     <!--注意：UserDao和UserDao.xml是配对的 路径要一致-->
     <mapper namespace="com.ang.dao.UserDao">
         <!--配置查询所有-->
         <select id="findAll">
             select * for user
         </select>
     </mapper>
     ```

5. 环境搭建的注意事项

   1. 创建UserDa.xml和UserDao.java时名称是为了和我们之前的知识保持一致。在Mybatis中他把持久层作为接口名称和映射文件也叫做：Mapper ；所以UserDao和UserMapper是一样的
   2. 在idea中创建目录的时候，它和包是不一样的包在创建时：com.ang.dao它是三级结构目录在创建时：com.ang.dao是一级目录。
   3. mybatis的映射配置文件位置必须和dao接口的包结构相同
   4. 映射配置文件的mapper标签namespace属性的取值必须是dao接口的全限定类名
   5. 映射配置文件的操作配置（select)，id属性的取值必须是dao接口的方法名

   * 当我们遵循了三，四，五点后，我们在开发中无需再写完dao的实现类。
   
6. mybatis的入门案例

   1. 读取配置文件
   2. 创建SqlSessionFactory工厂
   3. 创建SqlSession
   4. 创建Dao接口代理对象
   5. 执行dao中的方法
   6. 释放资源

   * 注意事项：
     * 不要忘记再映射配置中告知mybatis要封装到那个实体类中配置的方式：指定实体类的全限定类名

7.  mybatis基于注解的入门案例

   * 把UserDao.xml移除，再dao接口的方法上使用@Select注解，并且指定sql语句；同时需要再SqlMapConfig.xml中的mapper配置时，使用class属性指定dao接口的全限定类名。

# mybatis的CRUD

## 插入

1. 在UserDao.java中：

   ```Java
       /**
        * 保存用户
        * @param user
        */
       void saveUser(User user);
   ```

2. 在UserDao.xml中配置：

   ```xml
       <!--保存用户-->
       <insert id="saveUser" parameterType="com.ang.domin.User">
           insert into user(id,username)values(#{id},#{username});
       </insert>
   ```

3. 在测试类中：

   ```Java
       @Test
       public void testSave() throws IOException {
           //读取配置文件
           InputStream in = Resources.getResourceAsStream("SqlMapConfig.xml");
           //创建SqlSessionFactory工厂
           SqlSessionFactoryBuilder builder = new SqlSessionFactoryBuilder();
           SqlSessionFactory factory = builder.build(in);
           //使用工厂生产SqlSession对象
           SqlSession session = factory.openSession();
           //使用SqlSession创建Dao接口的代理对象
           UserDao userDao = session.getMapper(UserDao.class);
           //使用代理代理对象执行方法
   
           User user = new User();
           //向表中添加数据
           user.setId(5);
           user.setUsername("Mybatis");
   		
           //使用代理方法执行方法
           userDao.saveUser(user);
   
           //提交事务
           session.commit();
       }
   ```

   * 测试类简化：

     ```Java
     public class MyTest_Demo1 {
         private InputStream in;
         private SqlSession session;
         private UserDao userDao;
     
         @Before
         public void init() throws IOException {
             in = Resources.getResourceAsStream("SqlMapConfig.xml");
             //创建SqlSessionFactory工厂
             SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
             //使用工厂生产SqlSession对象
             session = factory.openSession();
             //使用SqlSession创建Dao接口的代理对象
             userDao = session.getMapper(UserDao.class);
         }
     
         @After
         public void destory() throws IOException {
             session.commit();
             session.close();
             in.close();
         }
     
     
         @Test
         public void testSave(){
             User user = new User();
             user.setId(7);
             user.setUsername("Test");
     
             userDao.saveUser(user);
         }
     }
     ```

## 全部方法代码

* UserDao.java

  ```java 
  /**
   * 用户持久层接口
   */
  public interface UserDao {
      /**
       * 查询所有用户
       * @return
       */
      List<User> findAll();
  
      /**
       * 保存用户
       * @param user
       */
      void saveUser(User user);
  
      /**
       * 更新
       * @param user
       */
      void updateUser(User user);
  
      /**
       * 删除操作
       * @param id
       */
      void deleteUser(Integer id);
  
      /**
       * 根据id删除用户信息
       * @param id
       * @return
       */
      User findById(Integer id);
  
      /**
       * 根据名称模糊查询用户信息
       * @param username
       * @return
       */
      List<User> findByName(String username);
  
      /**
       * 查询总用户数
       * @return
       */
      int findTotal();
  }
  ```

* UserDao.xml

  ```xml
  <mapper namespace="com.ang.dao.UserDao">
      <!--配置查询所有-->
      <select id="findAll" resultType="com.ang.domin.User">
          select * from User
      </select>
  
      <!--保存用户-->
      <insert id="saveUser" parameterType="com.ang.domin.User">
          insert into user(id,username)values(#{id},#{username});
      </insert>
  
      <!--更新操作-->
      <update id="updateUser" parameterType="com.ang.domin.User">
          update user set username=#{username} where id=#{id};
      </update>
  
      <!--删除用户-->
      <delete id="deleteUser" parameterType="int">
          delete from user where id = #{did};
      </delete>
  
      <!--根据id查询用户-->
      <select id="findById" parameterType="int" resultType="com.ang.domin.User">
          select * from user where id = #{fid};
      </select>
  
      <!--根据名称模糊查询-->
      <select id="findByName" parameterType="string" resultType="com.ang.domin.User">
          select * from user where username like #{name};
      </select>
  
      <!--获取用户的总记录条数-->
      <select id="findTotal" resultType="int">
          select count(id) from user;
      </select>
  </mapper>
  ```

* 测试类

  ```Java
  public class MyTest_Demo1 {
      private InputStream in;
      private SqlSession session;
      private UserDao userDao;
  
      @Before
      public void init() throws IOException {
          in = Resources.getResourceAsStream("SqlMapConfig.xml");
          //创建SqlSessionFactory工厂
          SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(in);
          //使用工厂生产SqlSession对象
          session = factory.openSession();
          //使用SqlSession创建Dao接口的代理对象
          userDao = session.getMapper(UserDao.class);
      }
  
      @After
      public void destory() throws IOException {
          session.commit();
          session.close();
          in.close();
      }
  
      /**
       * 测试保存用户
       */
      @Test
      public void testSave(){
          User user = new User();
          user.setId(7);
          user.setUsername("Test");
  
          userDao.saveUser(user);
      }
  
      /**
       * 测试更新
       */
      @Test
      public void testUpdate(){
          User user = new User();
          user.setId(7);
          user.setUsername("UpdateUser");
  
          userDao.updateUser(user);
      }
  
      /**
       * 测试删除操作
       */
      @Test
      public void testDelete(){
          userDao.deleteUser(7);
      }
  
      /**
       * 测试根据id查询
       */
      @Test
      public void testFindById(){
          User user = userDao.findById(1);
          System.out.println(user);
      }
  
      /**
       * 测试根据用户名模糊查询
       */
      @Test
      public void testFindByName(){
          List<User> users =userDao.findByName("%o%");
          for (User user : users){
              System.out.println(user);
          }
      }
  
      /**
       * 测试查询总记录条数
       */
      @Test
      public void testFindTotal(){
          int total = userDao.findTotal();
          System.out.println(total);
      }
  }
  ```

# Mybatis的参数深入

* 对象图导航语言
* 他通过对象的取值方法来获取数据。在写法上把get给省略了。
  * 比如：我们通过用户的名称
    1. 类中的写法：user.getUsername();
    2. OGNL表达是写法：user.username
  * mybatis中为什么能直接写username ，而不用user.呢
    * 因为在parameterType中已经提供了属性所属的类，所以此时不需要写对象名

## 传递pojo包装对象

* 开发中通过pojo传递查询条件，查询条件是综合的查询条件，不仅包括用户查询条件还包括其他的查询条件(比如将用户购买商品信息也作为查询条件)，这是可以使用包装对象传递输入参数。Pojo类中包含pojo.

## Mybatis的输出结果封装

解决实体类中的字段名和MySQL中的字段名不一致，导致封装出错：

* 在UserDao.xml中配置列名和实体类的属性名的对应关系：

  ```xml
      <!--配置 查询结果的列名和实体类的属性名的对应关系-->
      <resultMap id="userMap" type="com.ang.domin.User">
          <!--主键字段的对应-->
          <id property="userId" column="id"></id>
          <!--非主键字段的对应-->
          <result property="userName" column="username"></result>
      </resultMap>
  
  	<!--此处parameterType属性应改为resultMap="userMap"-->
      <select id="findAll" resultMap="userMap">
          select * from user;
      </select>
  ```



# 配置

## 配置properties

可以在标签内部配置连接数据库的信息，也可以通过属性引用外部配置文件信息

* resource属性：用于指定配置文件的位置，是按照类路径的写法类写，并必须在用于内路径下。

* url属性：是要求按照url的写法来写地址

* 用法：

  *  创建jdbcConfig.properties文件

    ```properties
    jdbc.driver=com.mysql.jdbc.Driver
    jdbc.url=jdbc:mysql://localhost:3306/temp
    jdbc.username=root
    jdbc.password=angelong
    ```

  * SqlMapConfig.xml中

    ```xml
    <?xml version="1.0" encoding="UTF-8" ?>
    <!DOCTYPE configuration
            PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
            "http://mybatis.org/dtd/mybatis-3-config.dtd">
    <!--mybatis的主配置-->
    <configuration>
    
        <properties resource="jdbcConfig.properties"></properties>
    
        <!-- 配置环境 -->
        <environments default="mysql">
            <!-- 配置mysql的环境-->
            <environment id="mysql">
                <!--配置事务的类型-->
                <transactionManager type="JDBC"></transactionManager>
                <!--配置数据源（连接池）-->
                <dataSource type="POOLED">
                    <property name="driver" value="${jdbc.driver}"/>
                    <property name="url" value="${jdbc.url}"/>
                    <property name="username" value="${jdbc.username}"/>
                    <property name="password" value="${jdbc.password}"/>
                </dataSource>
            </environment>
        </environments>
    
        <!--指定映射配置文件的位置，映射配置文件指定的是每个dao独立的配置文件-->
        <mappers>
            <mapper resource="com/ang/dao/UserDao.xml"/>
        </mappers>
    </configuration>
    ```

## 配置类别名

1. typeAliases配置

   * 使用typeAliases配置别名，他只能配置domain中类的别名
   * typeAlias用于配置别名。type属性指定的是实体类全限定类名。alias属性指定别名，当指定了别名就不再区分大小写

2. package配置

   * **推荐** 使用package 用于指定要配置别名的包，当指定之后，该包下的实体类都会注册别名，并且类名就是别名，不再区分大小写

   ```xml
   <?xml version="1.0" encoding="UTF-8" ?>
   <!DOCTYPE configuration
           PUBLIC "-//mybatis.org//DTD Config 3.0//EN"
           "http://mybatis.org/dtd/mybatis-3-config.dtd">
   <!--mybatis的主配置-->
   <configuration>
   
       <properties resource="jdbcConfig.properties"></properties>
   
       <!--使用typeAliases配置别名，他只能配置domain中类的别名-->
       <typeAliases>
           <!--typeAlias用于配置别名。type属性指定的是实体类全限定类名。alias属性指定别名，当指定了别名就不再区分大小写-->
           <typeAlias type="com.ang.domin.User" alias="user"></typeAlias>
           <package name="com.ang.domin"></package>
       </typeAliases>
       
   
       <!-- 配置环境 -->
       <environments default="mysql">
           <!-- 配置mysql的环境-->
           <environment id="mysql">
               <!--配置事务的类型-->
               <transactionManager type="JDBC"></transactionManager>
               <!--配置数据源（连接池）-->
               <dataSource type="POOLED">
                   <property name="driver" value="${jdbc.driver}"/>
                   <property name="url" value="${jdbc.url}"/>
                   <property name="username" value="${jdbc.username}"/>
                   <property name="password" value="${jdbc.password}"/>
               </dataSource>
           </environment>
       </environments>
   
       <!--指定映射配置文件的位置，映射配置文件指定的是每个dao独立的配置文件-->
       <mappers>
           <mapper resource="com/ang/dao/UserDao.xml"/>
       </mappers>
   </configuration>
   ```



# mybatis中的连接池

* mybatis连接池提供了3种方式的配置：

  * 配置的位置：

    主配置文件SqlMapConfig.xml中的dataSource标签，type属性就是表示采用何种连接方式。

  * type属性的取值：

    POOLED：采用传统的javax.sql.DataSource规范中的连接池，mybatis中有针对规范的实现方法。

    UNPOOLED：采用传统的获取连接的方式，没有池的思想。

    JNDI：采用服务器提供的JNDI技术实现，来获取DataSource对象

# mybatis中的事务

1. 什么是事务
2. 事务的四大特征ACID
3. 不考虑隔离性会产生的3个问题
4. 解决办法：四种隔离级别

* 它是通过sqlsession对象的commit方法和roolback方法实现事务的提交和回滚。

# mybatis映射文件的SQL深入

## if

* UserDao.xml

  ```xml
      <!--根据条件查询-->
      <select id="findUserByCondition" resultMap="userMap" parameterType="user">
          select * from user where 1=1
          <if test="userName != null">
              and username = #{userName}
          </if>
          <if test="userSex != null">
              and sex = #{userSex}
          </if>
      </select>
  ```

## where

* UserDao.xml

  ```xml
      <!--根据条件查询-->
      <select id="findUserByCondition" resultMap="userMap" parameterType="user">
          select * from user where 
          <where>
              <if test="userName != null">
           	   and username = #{userName}
         		</if>
              <if test="userSex != null">
              	and sex = #{userSex}
          	</if>
          </where>
          
      </select>
  ```

## foreach

* UserDao.xml

  ```xml
      <select id="findUserByVo" resultMap="userMap" parameterType="queryvo">
          select * from user
          <where>
              <if test="ids != null and ids.size()>0">
                  <foreach collection="ids" open="and id in(" close=")" item="id" separator=",">
                      #{id}
                  </foreach>
              </if>
          </where>
      </select>
  ```

## 抽取重复的sql语句

* UserDao.xml

  ```xml
      <sql id="defaultUser">
          select * from user;
      </sql>
      <!--配置查询所有-->
      <select id="findAll" resultType="user">
          <include refid="defaultUser"></include>
           <!-- select * from User -->
      </select>
  ```

# mybatis的多表关联查询

* 表之间的关系有几种：

  1. 一对多：用户和订单
  2. 多对一：订单和用户
  3. 一对一：一个人只能有一个身份证
  4. 多对多：老师和学生之间

* 特例：如果拿出一个订单，他都只能属于一个用户。所以mybatis就把多对一看成一对一。

* mybatis中的多表查询：

  * 示例：用户和账户

    * 一个用户可以有多个账户
    * 一个账户只能属于一个用户(多个账户也可以属于同一个用户)

  * 步骤：

    1. 建立两张表：用户表，账户表

       让用户表和账户表之间具备一对多的关系：需要使用外键在账户表中添加

    2. 建立两个实体类：用户实体类和账户实体类

       让用户和账户的实体类能体现出一对多的关系

    3. 建立两个配置文件

       用户的配置文件

       账户的配置文件

    4. 实现配置：

       当我们查询用户时，可以同时得到用户下所包含的账户信息

       当我们查询账户时，可以同时得到账户的所属用户信息

# mybatis中的缓存

存在于内存中的临时数据，减少和数据库的交互次数，提高执行效率

* 适用于缓存：

  经常查询并且不经常改变的。

  数据的正确与否对最终结果影响不大的。

* 不适用于缓存：

  经常改变的数据

  数据的正确与否对最终结果影响很大的；

  例如：商品的库存，银行的汇率，股市的牌价

## 一级缓存

一级缓存是SqlSession范围的缓存，当调用SqlSession的修改，添加，删除，commit(),close()等方法时，就会清空一级缓存。

## 二级缓存

指在mybatis中SqlSessionFactory对象的缓存。有同一个SqlSessionFactory对象创建的SqlSession共享其缓存。

二级缓存的使用步骤：

1. 让mybatis框架支持二级缓存(在SqlMapConfig.xml中配置)
2. 让当前的映射文件支持二级缓存(在UserDao.xml中配置)
3. 让当前的操作支持二级缓存(在select标签中配置)

 # mybatis注解

* 在mybatis中针对CRUD一共有四个注解
  1. @Select
  2. @Insert
  3. @Update
  4. @Delete


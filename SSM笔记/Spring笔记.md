#  Spring概述

Spring是分层的Java SE/EE 应用 full-stack 轻量级开源框架，以IoC（反转控制）和 AOP（面向切面编程）为内核；

 # 程序的耦合

耦合：程序间的依赖关系

包括：类之间的依赖，方法之间的依赖

解耦：降低程序间的依赖关系

解耦思路：

1. 使用反射来创建对象，而避免使用new关键字。
2. 同伙读取配置文件来获取要创建的对象全限定类名。

# Spring入门

pom.xml中导入

```xml
    <dependency>
      <groupId>org.springframework</groupId>
      <artifactId>spring-context</artifactId>
      <version>5.1.6.RELEASE</version>
    </dependency>
```

创建bean.xml配置文件

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
        http://www.springframework.org/schema/beans/spring-beans.xsd">

     <!--把对象的创建交给spring来管理-->
    <bean id="accountService" class="com.ang.service.impl.AccountServiceImpl"></bean>
</beans>
```

获取Spring的IOC核心容器，并根据id获取对象

```java
/**
 * 模拟表现层，用于调用业务层
 */
public class Client {
    /**
     * 获取Spring的IOC核心容器，并根据id获取对象
     * @param args
     */
    public static void main(String[] args) {
        //1.获取核心容器对象
        ClassPathXmlApplicationContext ac = new ClassPathXmlApplicationContext("bin.xml");
        //2.根据id获取Bean对象
        IAccountService as = (IAccountService)ac.getBean("accountService");

        AccountServiceImpl a = ac.getBean("accountService", AccountServiceImpl.class);
//        as.saveAccount();
        System.out.println(as);
        System.out.println(a);
    }
}
```

ApplicationContext的三个常用实现类：

1. ClassPathXmlApplicationContext：他可以加载内路径下的配置文件，要求配置文件必须在类路径下，不在的话，加载不了（常用）
2. FileSystemXmlApplicationContext：他可以加载磁盘任意路径下的配置文件（必须有访问权限）
3. AnnotationConfigApplicationContext：它是用于读取注解创建容器的

核心容器的两个接口引发出的问题：

1. ApplicationContext：(单例对象使用）它在创建核心容器时，创建对象采取的策略是采取立即加载的方式，也就是说，只要已读取完配置文件马上就创建配置文件中配置对象。
2. BeanFactory：(多例对象使用）它在构建核心容器时，创建对象采取的策略是采取延迟加载的方式，也就是说，什么时候根据id获取对象了，什么时候才真正的创建对象。

## Spring对bean的管理细节

1. 创建bean的三种方式

   1. 使用默认构造函数创建，在spring的配置文件中使用bean标签，配以id和class的属性之后，并且没有其他属性和标签时；采用的就是默认构造函数创建bean对象，此时如果类中没有默认构造函数，则对象无法创建。

      ```xml
      <bean id="accountService" class="com.ang.service.impl.AccountServiceImpl"></bean>
      ```

      

   2. 使用普通工厂中的方法创建对象（使用某个类中的方法创建对象，并存入spring容器）

      ```xml
      <bean id="instanceFactory" calss="com.ang.factory.InstanceFactory"></bean>
      <!-- 通过InstanceFactory类获取getAccountService方法-->
      <bean id="accountService" factory-beab="instanceFactory" factory-method="getAccountService"></bean>
      ```

   3. 使用工厂中的静态方法创建对象（使用某个类中的静态方法创建对象，并存入spring容器）

      ```xml
      <!--通过StaticFactory类获取getAccountService-->
      <bean id="accountService" class="com.ang.factory.StaticFactory" factory-method="getAccountService"></bean>
      ```

      

2. bean对象的作用范围

   bean标签的scope属性：

   * 作用：用于指定bean的作用范围
   * 取值：常用的就是单例和多例
     1. singleton：单例的（默认）
     2. prototype：多例的
     3. request：作用于web应用的请求范围
     4. session：作用于web应用的会话范围
     5. global-session：作用于集群环境的会话范围（全局会话范围），当不是集群环境时，它就是session。

3. bean对象的生命周期

   * 单例对象：
     * 出生：当容器创建时对象出生
     * 活着：只要容器还在，对象一直活着
     * 死亡：容器销毁，对象消亡
     * 总结：单例对象的生命周期和容器相同
   * 多例对象：
     * 出生：当我们使用对象时spring框架为我们创建
     * 活着：对象只要还在使用过程中就一直活着
     * 死亡：当对象长时间不使用，且没有其他对象引用时，由java的垃圾回收器回收。

# spring中的依赖注入

* 依赖注入：Dependecy Injection

* IOC 的作用：降低程序之间的耦合（依赖关系）

* 依赖关系的管理：以后交给spring来维护；在当前类需要用到的其他类对象，由spring为我们提供，我们只需要在配置文件中说明；依赖关系的维护就称为依赖注入。

* 依赖注入：

  能注入的数据：有三类

  * 基本类型和String
  * 其他bean类型（在配置文件中或者注解配置过的bean)
  * 复杂类型/集合类型

  注入的方式：有三种

  1. 使用构造函数提供
  2. 使用set方法提供
  3. 使用注解提供

## 构造函数注入

* 使用的标签：constructor-arg

* 标签出现的位置：bean标签的内部

* 标签中的属性：

  * type：用于指定要注入的数据的数据类型，该数据类型也是构造函数中某个或某些参数的类型。
  * index：用于指定要注入的数据给构造函数中指定索引位置的参数赋值。索引的位置是从0开始
  * name：用于指定给构造函数中指定名称的参数赋值
  * 以上三个用于指定给构造函数中的那个参数赋值（最好用的是name）
  * value：用于提供基本类型和String类型的数据
  * ref：用于指定其他的bean类型数据。它指的就是在spring的IOC核心容器中出现过的bean对象。

  优势：在获取bean对象时，注入数据是必须的操作，否则对象无法创建成功。

  弊端：改变了bean对象的实例化方式，使我们在创建对象时，如果用不到这些数据，也必须提供。

  ```xml
  <bean id="accountService" class="com.ang.service.impl.AccouintServiceImpl">
      <constructor-arg name="name" value="Angelong"></constructor-arg>
      <constructor-arg name="age" value="20"></constructor-arg>
      <constructor-arg name="birthday" value="now"></constructor-arg>
  </bean>
  <!--配置一个日期对象-->
  <bean id="now" class="java.util.Date"></bean>
  ```

## set方法注入

* 涉及的标签：property

* 出现的位置：bean标签的内部

* 标签属性

  * name：用于指定注入时所调用的set方法名称
  * value：用于提供基本类型和String类型的数据
  * ref：用于指定其他的bean类型数据。它指定的就是在spring的IOC核心容器中出现过多beab对象
  * 优势：创建对象时没有明确的限制，可以直接使用默认构造函数。
  * 弊端：如果有某个成员必须有值，则获取对象时有可能set方法没有执行。

  ```xml
  <beab id="accounService2" class="com.ang.service.impl.AccountServiceImpl2">
      <property name="name" value="Angleong"></property>
      <property name="age" value="20"></property>
      <property name="birthday" value="now"></property>
  </beab>
  <bean id="now" class="java.util.Date"></bean>
  ```

# spring的注解

```xml
<?xml version="1.0" encoding="UTF-8" ?>
<beans xmlns="http://www.springframework.org/schema/beans"
       xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
       xmlns:context="http://www.springframework.org/schema/context"
       xsi:schemaLocation="http://www.springframework.org/schema/beans
    http://www.springframework.org/schema/beans/spring-beans-3.0.xsd
    http://www.springframework.org/schema/context
    http://www.springframework.org/schema/context/spring-context-3.0.xsd">

    <context:component-scan base-package="com.ang"></context:component-scan>
</beans>

```

* 用于创建对象的

  他们的作用就和在xml配置文件中编写一个bean标签实现的功能是一样的。

  * Component:

    作用：用于把当前类对象存入spring容器中

    属性：

    * value：用于指定bean的id。当我们不写时，它的默认值是当前类名，并且首字母改小写。

  * Controller：一般用在表现层

  * Service：一般用在业务层

  * Repository：一般用在持久层

  * 以上三个注解他们的作用和属性与Component是一摸一样的。他们三个是spring矿建为我们提供明确的三层使用的注解，使用我们的三层对象更加清晰。

* 用于注入数据的

  他们的作用就和在xml配置文件中的bean标签中写一个property标签的作用是一样的。

  * Autowired:

    作用：自动按照类型注入。只要容器中有唯一的一个bean对象类型和要注入的变量类型匹配，就可以注入成功。如果IOC容器中没有任何bean的类型和要注入的变量类型匹配，则报错。如果IOC容器中有多个类型匹配时，再根据变量名匹配。

    出现位置：可以是变量上，也可以是方法上。

    细节：在使用注解注入时，set方法就不是必须的了

  * Qualifier:

    作用：在按照类中注入的基础上再按照名称注入。它在给类成员注入时不能单独使用。

    属性：

    * value：用于指定注入bean的id。

  * Resource

    作用：直接按照bean的id注入。它可以独立使用

    属性：

    * name：用于指定bean的id。

    以上三个注入都只能注入其他bean类型的数据，而基本类型和String类型无法使用上述注解实现。

    另外，集合类型的注入只能通过XML来实现。

  * Value

    作用：用于注入基本类型和String类型的数据

    属性：

    * value：用于指定数据的值，他可以使用Spring中spEL(也就是spring的el表达式)

      SpEl的书写：${表达式}

* 用于改变作用范围的

  他们的作用就和在bean标签中使用scope属性实现的功能是一样的

  * Scope：

    作用：用于指定bean的作用范围

    属性：

    * value：指定范围的取值。常用取值：singleton protory

* 和生命周期有关

  他们的作用就和在bean标签中使用init-method和destroy-methode的作用是一样的。
  
  * PreDestroy
  
    作用：用于指定销毁方法
  
  * PostConstruct
  
    作用：用于指定初始化方法

# 新注解

spring中的新注解

* Configuration

  作用：指定当前类是一个配置类

  细节：当配置类作为AnnotationConfigApplicationContext对象创建的参数时。该注解可以不写。

* ComponentScan

  作用：用于通过注解指定spring在创建容器时要扫描的包

  属性：

  * value：他和beaePackages的作用是一样的，都是用于指定创建容器时要扫描的包

    我们使用此注解就等于在xml中配置：

    ```xml
    <context:component-scan base-package="com.ang"></context:component-scan>
    ```

* Bean

  作用：用于把当前方法的返回值作为bean对象存入spring的IOC容器中

  属性：

  * name：用于指定bean的id。当不写时，默认值是当前方法的名称

  细节：当我们使用注解配置方法时，如果方法有参数，spring框架回去容器中查找有没有可用的bean对象。查找的方式和Autowired注解的作用是一样的

* Import

  作用：用于导入其他的配置类。

  属性：

  * value：用于指定其他配置类的字节码。当我们使用Import的注解之后，有Import注解的类就父类配置类，而导入的都是子配置类。

* PropertySource

  作用：用于指定properties文件的位置

  属性：

  * value：指定文件的名称和路径。关键字：classpath，表示类路径下


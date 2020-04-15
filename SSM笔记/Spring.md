# Spring的基本应用

## 核心容器

>Spring框架提供了两种核心容器，分别为BeanFactory和ApplicationContext。

* BeanFactory：是基础类型额IoC容器；BeanFactory就是一个管理Bean的工厂，它主要负责初始化各种Bean，并调用他们的生命周期方法。

  * ```java
    BeanFactory beanFactory = new XmlBeanFactory(new F工leSystemResource("F: /applicationContext.xml")) ; 
    ```

* ApplictionContext：是BeanFactory的子接口，也被称为应用上下文，是另一个常用的Spring核心容器。

  * 两种方法创建：

    * 通过ClassPathXmlApplictonContext创建

      ```java
      ApplicationContext applicationContext = new ClassPathXmlApplicationContext(String configLocation) ; 
      ```

    * 通过FileSystemXmlApplictionContext创建

      ```java 
      ApplicationContext applicationContext = new FileSystemXmlApplicationContext(String configLocation); 
      ```

  ## Spring获取Bean的实例两种方法：

  * Object getBean(String name):根据容器中的Bean的id或name来获取指定的Bean，获取之后需要进行强制类型转换。
  * \<T>T getBean(Class\<T> requiredType):根据类的类型来获取Bean的事例。由于此方法为泛型方法，因此在获取Bean之后不需要进行强制类型转换。

* 入门案例：Demo1

# 依赖注入

>依赖注入(简称DI)与控制反转(IoC)的含义相同，只不过这两个称呼是从两个角度描述的同一个概念。

### 依赖注入的实现方式

> 两种实现方式：一种是属性setter方法注入，另一种是构造方法注入

* 属性setter方法注入：指IOC容器使用setter方法注入被依赖的实例。通过调用无参构造容器或无参静态工厂方法实例化Bean后，调用该Bean的setter方法，即可实现基于setter方法的依赖注入。
* 构造方法注入：指IOC如期使用构造方法注入被依赖的实例。基于构造方法的依赖注入通过调用带参数的构造方法来实现，每个参数代表着一个依赖。
* 实例：Demo2

# Spring中的Bean

## Bean的配置

> ​	Spring 可以被看作是一个大型工厂，这个工厂的作用就是生产和管理 Spring 容器中的 Bean
>
> ​	Spring 容器支持 XML 和 Properties 两种格式的配置文件，在实际开发中，最常使用的就是 XML 格式的配置方式。

###  \<bean>元素的常用属性及其子元素

| 属性或子元素名称 |                             描述                             |
| :--------------: | :----------------------------------------------------------: |
|        id        | 是一个 Bean 的唯一标识符， Spring 窑器对 Bean 的配置、 筐理部通过该属性来完成 |
|       name       | Spring 窑器同样可以通过此属性对容器中的 Bean 进行配置和管理， name 属性中可以为 Bean 指定多个名称，每个名称之间用逗号或分号隔开 |
|      class       | 该属性指定了 Bean 的具体实现类， E必须是一个完整的类毡，使用类的全限定名 |
|      scope       | 用来设定 Bean 实例的作用域，莫属性值有: singleton( 单例)、 prototype( 原型)、 request、 sesslon、 global Session , application 和 websocket. 真默认值为 singleton |
| constructor-arg  | \<bean>元素的子元素，可以使用此元素传入构造参数进行实例化。 该元素的 index 属性指定构造参数的序号(从 O 开始) , type 属性指定构造参数的类型，参数值可以通过 ref 属性 或 value 属性直接指定，也可以通过 ref 或 value 子元素指定 |
|     property     | \<bean>元素的子元素， 用于调用 Bean 实例中的 setter 方法完成属性赋值，从而完成依赖注入。 该元素的 name 属性指定 Bean 实例中的相应属性毡 ref 属性或 value 属性用于 指定参数值 |
|       ref        | \<property>、 \<constructor-arg>等元素的属性或子元素，可以用于指定对 Bean 工厂中 某个 Bean 实例的引用 |
|      value       | \<property>、 \<constructor-arg>等元素的属性或子元素，可以用于直接指定一个常量值 |
|       list       |              用于封装List 或数组类型的依赖注入               |
|       set        |               用于封装 Set 类型属性的依赖注入                |
|       map        |               用于封装 Map 类型属性的依赖注入                |
|      entry       | \<map>元素的子元素，用于设置一个键值对。 真 key 属性指定字符串类型的键值， ref 或 value 子元素指定真僵，也可以通过 value-ref 或 value 属性指定真值 |

## Bean的实例化

> 在 Spring 中， 要想使用容器中的 Bean ，需要实例化 Bean。
>
> 实例化 Bean 有三种方式，分别为构造器实例化、 静态工厂方式实例化和实例工厂方式实例化(其中最常用的是构造器实例化)。 

### 构造器实例化

> 构造器实例化是指 Spring 容器通过 Bean 对应类中默认的无参构造方法来实例化 Bean

* 实例：Demo3

### 静态工厂方式实例化

>  该方式要求开发者创建一个静态工厂的方法来 创建 Bean 的实例，其 Bean 配置中的 class 属性所指定的不再是 Bean 实例的实现类，而是静 态工厂类，同时还需要使用 factory-method 属性来指定所创建的静态工厂方法。 

* 实例：Demo4

### 实例工厂方式实例化

> 此种方式的工厂类中，不再使用静态方法 创建 Bean 实例，而是采用直接创建 Bean 实例的方式。 同时，在配置文件中，需要实例化的 Bean 也不是通过 class 属性直接指向的实例化类，而是通过 factory- bean 属性指向配置的实例工厂， 然后使用 factory- method 属性确定使用工厂中的哪个方法。 下面通过一个案例来演示实例工厂 方式的使用。

## Bean的作用域

### 作用域的种类

|    作用域名称    |                             说明                             |
| :--------------: | :----------------------------------------------------------: |
| singleton (单例) | 使用 singleton 定义的 Bean 在 Spring 窑器中将只有一个实例， 也就是说，无论有多少个 Bean 引用巴，始终将指向罔一个对象。 这也是 Spring 窑器默认的作用域 |
| prototype (原型) | 每次通过 Spring 窑器获取的 prototype 定义的 Bean 时，容器都将创建一个新的 Bean 实例 |
|     request      | 在一次 HTTP 请求中，容器会返回该 Bean 的同一个实例。 对不同的 HTTP 请求则会产生一个新的 Bean ，而且该 Bean 仅在当前 HTTP Request 内奇效 |
|     sesslon      | 在 ;欠 HTTP Session 中，容器会返回该 Bean 的同一个实例。 对不同的 HTTPi需求则会产 生一个新的 Bean ，而且该 Bean 仅在当前 HTTP Sessio门内奇效 |
|  globalSession   | 在一个全局的 HTTP Session 中，容器会返回该 Bean 的同一个实例。 仅在使用 portlet 上下 文时有效 |
|   application    | 为每个 ServletContext 对象创建一个实例。 仅在 Web 相关的 ApplicationContext 中生效 |
|    websocket     | 为每个 websocket 对象创建一个实例。 仅在 Web 徊关的 ApplicationContext 中生效 |

### singleton作用域

> singleton 是 Spring 容器默认的作用域，当 Bean 的作用域为 singleton 肘， Spring 容器就 只会存在一个共享的 Bean 实例，并且所有对 Bean 的请求，只要 id 与该 Bean 的 id 属性相匹配， 就会返回同一个 Bean 实例。

```xml
<bean id="scope " class=" com . 工theima.scope . Scope" scope="singleton"l> 
```

* 实例：Demo6

### prototype作用域

> 在使 用 prototype 作用域时， Spring 容器会为每个对该 Bean 的请求都创建一个新的实例。 

```xml
<bean id="scope" class="com.itheima.scope.Scope" scope="prototype" /> 
```

* 实例：Demo6

## Bean的装配方式

> Bean 的装配可以理解为依赖关系注入， Bean 的装配方式即 Bean 依赖注入的方式。 

### 基于XML的装配

> Spring 提供了两种基于 XML 的装配方式:设值注入( Setter Injection )和构造注入 ( Constructor Injection )。 

* 在 Spring 实例化 Bean 的过程中， Spring 首先会调用 Bean 的默认构造方法来实例化 Bean 对象，然后通过反射的方式调用 setter 方法来注入属性值。  因此，设值注入要求一个 Bean 必须 满足以下两点要求。
  *  Bean 类必须提供一个默认的无参构造方法。 
  *  Bean 类必须为需要注入的属性提供对应的 setter 方法。 

> 使用设值注入时，在 Spring 配置文件中，需要使用\<bean>元素的子元素\<property>来为每 个属性注入值;而使用构造注入时，在配置文件里，需要使用 \<bean> 元素的子元素 \<constructor -arg>来定义构造方法的参数，可以使用其 value 属性(或子元素)来设置该参数 的值。

* 实例：Demo7

### 基于Annotation的装配

> 在 Spring 中，尽管使用 XML配置文件可以实现 Bean 的装配工作，但如果应用中有很多 Bean 时，会导致 XML 配置文件过于靡肿，给后续的维护和升级工作带来一定的困难。 为此， Spring 提供了对 Annotation (注解)技术的全面支持。 

#### Spring 中常用的注解

|   注解名    |                             描述                             |
| :---------: | :----------------------------------------------------------: |
| @Component  | 可以使用此注解描述 Spring 中的 Bean ，但它是一个泛化的概念，仅仅表 示一个组件 (Bean l，并且可以作用在任何层次。 使用时只需将该注解标注在相应类上即可。 |
| @Repository | 用于将数据访问层( DAO 层)的类标识为 Spring 中的 Bean ，其功能与 @Component 相同。 |
|  @Service   | 通常作用在业务层( Service 层 ），用于将业务层的类标识为 Spring 中的 Bean ， 其功能与@Component 相同。 |
| @Controller | 通常作用在控制层(如 Spring MVC 的 Controller l，用于将控制层的类标识 为 Spring 中的 Bean ，其功能与@Component 相同。 |
| @Autowired  | 用于对 Bean 的属性变量、属性的 setter 方法及构造方法进行标注，配合对 应的注解处理器完成 Bean 的自动配置工作。 默认按照 Bean 的类型进行装配。 |
|  @Resource  | 其作用与 Autowired 一样。 其区别在于@Autowired 默认按照 Bean 类型装 配，而@Resource 默认按照 Bean 实例名称进行装配。 @Resource 中有两个重要属性: name 和 typeo Spring 将 name 属性解析为 Bean 实例名称， type 属性解析为 Bean 实例类型。 如果 指定 name 属性，贝IJ按实例名称进行装配;如果指定 type 属性，则按 Bean 类型进行装配;如 果都不指定，则先按 Bean 实例名称装配，如果不能匹配，再按照 Bean 类型进行装自己;如果都 无法匹配，则抛出 NoSuchBeanDefinitionException 异常。 |
| @Qualifier  | 与@Autowired 注解配合使用，会将默认的按 Bean 类型装配修改为接 Bean 的实例名称装配， Bean 的实例名称由@Qualifier 注解的参数指定。 |

* 在上面几个注解中，虽然@Repository、 @Service 与@Controller 功能与@Component 注解 的功能相同，但为了使标注类本身用途更加清晰，建议在实际开发中使用@Repository、 @Service 与@Controller 分别对实现类进行标注。 
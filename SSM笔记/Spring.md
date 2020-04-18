# Spring的基本应用

**archetypeCatalog        internal**

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

* 对包路径下的所有 Bean 文件进行扫描

  ```xml
  <context :cornponent-scan base-package="Bean 所在的包路径" />
  ```

* 实例：Demo8

### 自动装配

>  Spring 的 \<bean>元素中包含一个 autowire 属性，我们可以通过设置 autowire 的属性值来自动装配 Bean。 所谓自动装配，就是将一个 Bean 自动地注入到其他 Bean 的 Property 中。 

**autowíre 属性有 5 个值**

|       属性值       |                             说明                             |
| :----------------: | :----------------------------------------------------------: |
| default ( 默认值 ) | 由 \<bean> 的上级标签 \<beans> 的 default-autowire 属 性值确定 。 例如 <beans \defaultautowire="byName"> ，则该\<bean>元素中的 autowire 属性对应的属性值就为 byName |
|       byName       | 根据属性的名称自动装配。 窑器将根据名称查找与属性完全一致的 Bean ，并将真属性自动装配 |
|       byType       | 根据属性的数据类型 (Type) 自动装配，如果一个 Bean 的数据类型，兼容另一个 Bean 中属性的 数据类型，则自动装配 |
|    constructor     |    根据何造函数参数的数据类型，进行 byType 模式的自动装配    |
|         no         | 在默认情况下，不使用自动装配， Bean 依赖必须通过 ref 元素定义 |

 # Spring AOP

> AOP 的全称是 Aspect-Oriented Programming ，即面向切面编程(也称面向方面编程)。 它 是面向对象编程 (OOP) 的一种补充，目前已成为一种比较成熟的编程方式。 
>
> 目前最流行的 AOP 框架有两个，分别为 Spring AOP 和 AspectJ

* AOP术语

  |           术语           |                             说明                             |
  | :----------------------: | :----------------------------------------------------------: |
  |      Aspect (切面）      | 在实际应用中，切面通常是指封装的用于横向插入系统功能(如事务、 曰志等)的类， 该类要被 Spring 容器识别为切面，需要在配置文件中通 过\<bean>元素指定。 |
  |    Joinpoint (连接点)    | 是指切面与程序流程的交叉点，即那些需要处理的连接点，如图 3-2 所示。 通常在程序中，切入点指的是类或者方法名，如某个通知要应用到所有以 add 开头的 方法中，那么所有满足这一规则的方法都是切 入点。 |
  |  Advice( 通知/增强处理)  | AOP 框架在特 定的切入点执行的增强处理，即在定义好的切入 点处所要执行的程序代码。 可以将其理解为切面 类中的方法，它是切面的具体实现。 |
  | Target Object (目标对象) | 是指所有被 通知的对象，也称为被增强对象。 如果 AOP 框 架采用的是动态的 AOP 实现，那么该对象就是 一个被代理对象。 |
  |       Proxy (代理)       |        将通知应用到目标对象之 后，被动态创建的对象。         |
  |      Weaving (织入)      |     将切面代码插入到目标对象上，从而生成代理对象的过程。     |


## 动态代理

### JDK动态代理

> JDK 动态代理是通过 java.lang.reflect. Proxy 类来实现的，我们可以调用 Proxy 类的 newProxyl nstanceO方法来创建代理对象。 对于使用业务接口的类， Spring 默认会使用 JDK 动 态代理来实现 AOP。 
>
> 实例:Demo9

### CGLIB代理

> JDK 动态代理的使用非常简单，但它还有一定的局限性一一使用动态代理的对象必须实现一 个或多个接口 。 如果要对没有实现接口的类进行代理，那么可以使用 CGL旧代理。 CGL旧( Code Generation Library )是一个高性能开源的代码生成包，它采用非常底层的字 节码技术，对指定的目标类生成一个子类，并对子类进行增强。 在 Spring 的核心包中已经集成 了 CGL旧所需要的包，所以开发中不需要另外导入 JAR 包。 

## 基于代理类的AOP实现

> Spring 中的 AOP 代理默认就是使用 JDK 动态代理的方式来实现的 。 在 Spring 中，使用 Proxy FactoryBean 是创建 AOP 代理的最基本方式。

### Spring的通知类型

> Spring 中的通知按照 在目标类方法的连接点位置，可以分为以下 5 种类型。 
>
> *  org. aopall iance. intercept. Method I nterceptor (环绕通知) 在目标方法执行前后实施增强，可以应用于曰志、事务管理等功能。
> *  org.springframework.aop.MethodBeforeAdvice (前置通知) 在目标方法执行前实施增强，可以应用于权限管理等功能。 
> * org.springframework.aop.AfterReturningAdvice (后置通知) 在目标方法执行后实施增强，可以应用于关闭流、上传文件、删除临时文件等功能。 
> * org.springframework.aop.ThrowsAdvice (异常通知) 在方法抛出异常后实施增强，可以应用于处理异常记录曰志等功能。
> * org .springframework.aop.1 ntroduction I nterceptor (引介通知) 在目标类中添加一些新的方法和属性，可以应用于修改老版本程序(增强类)。

### ProxyFactoryBean

> ProxyFactoryBean 是 FactoryBean 接口的实现类， FactoryBean 负责实例化一个 Bean ，而 ProxyFactoryBean 负责为其他 Bean 创建代理实例。 在 Spring 中，使用 ProxyFactoryBean 是 创建 AOP 代理的基本方式。 

* ProxyFactoryBean的常用属性

  |     属性名称     |                             描述                             |
  | :--------------: | :----------------------------------------------------------: |
  |      target      |                        代理的目标对象                        |
  | proxylnterfaces  | 代理要实现的接口，如果是多个接口，可以使用以下格式赋值 \<Iist> \<value>\</value>...  \<br/>\</Iist> |
  | proxyTargetClass |   是否对类代理而不是接口，设置为 true 时， 使用 CGLlB 代理   |
  | interceptorNames |                    需要织入目标的 Advice                     |
  |    singleton     |      返回的代理是否为单实例，默认为 true (即返回单实例)      |
  |     optimize     |               当设置为 true 时，强制使用 CGLlB               |

## AspectJ开发

> AspectJ 是一个基于 Java 语言的 AOP 框架，它提供了强大的 AOP 功能。 
>
> 使用 AspectJ 实现 AOP 有两种方式:一种是基于 XML 的声明式 AspectJ ，另一种是基于注解的 声明式 AspectJ。

### 基于XML的声明式Aspect J

> 基于 XML 的声明式 AspectJ 是指通过 XML 文件来定义切面、切入点及通知，所有的切面、 切入点和通知都必须定义在\<aop:config>元素内。 

1. 配置切面

   * 在 Spring 的配置文件中 ，配置切面使用 的是\<aop:aspect>元素 ， 该元素会将一个已定 义好的 Spring Bean 转换成切面 Bean ， 所以要在配置文件中先定义一个普通的 Spring Bean ( 如上述代码中定义的 myAspect)。 定义完成后， 通过\<aop:aspect>元素的 ref 属性即可引 用该 Bean。 配置\<aop:aspect>元素时， 通常会指定 id 和 ref 两个属

     id:用于定义该切面的唯一标识名称

     ref:用于应用普通的Spring Bean

2. 配置切入点

   * 在 Spring 的配置文件中 ， 切入点是通过\<aop:pointcut>元素来定义的。 当 \<aop:pointcut> 元素作为\<aop:config>元素的子元素定义时，表示该切入点是全局切入点，它可被多个切面所 共享 ; 当 \<aop:pointcut>元素作为 \<aop:aspect>元素的子元素时，表示该切入点只对当前切面 有效。

   * 在定义\<aop:poíntcut>元素时，通常会指定 id 和 expresslon 两个属性

     id:用于指定切入点的唯一标识名称

     expression:用于指定切入点关联的表达式

   * **execution(* com. itheima.jdk. * .*(..))**就是定义的切入点表达式，该 切入点表达式的意思是匹配 com.itheima.jdk 包中任意类的任意方法的执行。 其中 executionO是 表达式的主体，第 1 个\*表示的是返回类型，使用\*代表所有类型; com.itheima.jdk 表示的是需 要拦截的包名，后面第 2 个\*表示的是类名，使用\*代表所有的类;第 3 个\*表示的是方法名，使 用\*表示所有方法;后面(..)表示方法的参数，其中的" .."表示任意参数。 需要注意的是，第 1 个*与包名之间有一个空格。 

   * Spring AOP 中切入点表达 式的基本格式如下:

     execution(modifiers-pattern? ret-type-pattern declaring-type-pattern? name-pattern(param-pattern) throws-pattern?)

     * modifiers-pattern: 表示定义的目标方法的访问修饰符，如 public、 prívate 等。 
     *  ret-type-pattern: 表示定义的目标方法的返回值类型，如 void 、 String 等。
     *  declaring-type-pattern: 表示定义的目标方法的类路径，如 com.itheima.jdk.UserDaolmpl。 
     * name-pattern: 表示具体需要被代理的目标方法，如 addO方法。
     * param-pattern l 表示需要被代理的目标方法包含的参数，本章示例中目标方法参数都 为空。 
     * throws-pattern: 表示需要被代理的目标方法抛出的异常类型。 

     其中带有问号(? )的部分，如 modifiers-pattern、 declaring-type-pa忧ern 和 throws-pattern 表示可配置项;而其他部分属于必须配置项。

3. 配置通知

   在配置代码中，分别使用\<aop:aspect>的子元素配置了 5 种常用通知，这 5 个子元素不支 持使用子元素，但在使用时可以指定一些属性

   |   属性名称   |                             描述                             |
   | :----------: | :----------------------------------------------------------: |
   |   pointcut   | 该属性用于指定一个切入点表达式， Spring 将在匹配该表达式的连接点时织入该通知 |
   | pointcut-ref | 该属性指定一个已经存在的切入点名称，如配置代码中的 myPointCut。 通常 pointcut f口 Pointcut-ref 两个属性只需要使用冥中之一 |
   |    method    | 该属性指定一个方法名，指定将切面 Bean 中的该方法转换为增强处理 |
   |   throwing   | 该属性只对\<after-throwing>元素有效， E用于指定一个形参名，异常通知方法可以通过该形 参访问目标方法所抛出的异常 |
   |  returning   | 该属性只对\<after-returning>元素有效， 2用于指定一个形参名，后置通知方法可以通过该形 参访问目标方法的返回值 |

   * 实例：Demo11

### 基于注解的声明式AspectJ

> 与基于代理类的 AOP 实现相比，基于 XML 的声明式 ApectJ 要便捷得多，但是它也存在着一些 缺点，那就是要在 Spring 文件中配置大量的代码信息。 为了解决这个问题， AspectJ 框架为 AOP 的实现提供了一套注解，用以取代 Spring 配置文件中为实现 AOP 功能所配置的腕肿代码。 

* AspectJ的注解及其描述

  |    注解名称     |                             描述                             |
  | :-------------: | :----------------------------------------------------------: |
  |     @Aspect     |                       用于定义一个切面                       |
  |    @Pointcut    | 用于定义切入点表达式。 在使用时还需定义一个包含名字和任意参数的方法签名来表示切入点 名称。 实际上，这个方法签名就是一个返回值为 void ，旦方法体为空的普通的方法 |
  |     @Before     | 用于定义前置通知，相当于 BeforeAdvice。在使用时，通常需要指定一个 value 属性值， 该 属性值用于指定一个切入点表达式( 可以是己有的切入点，也可以直接定义切λ点表达式) |
  | @AfterReturning | 用于定义后置通知，相当于 AfterReturningAdvice。在使用时可以指定 pOintcutlvalue 和 returning 属性，冥中 pointcut/value 这两个属性的作用一样，都用于指定切入点表达式。 returning 属性值用于表示 Advice 方法中可定义与此罔名的形参，该形参可用于访问目标方 法的返回值 |
  |     @Around     | 用于定义环绕通知，相当于 MethodInterceptor。 在使用时需要指定一个 value 属性，该属性 用于指定该通知被植入的切入点 |
  | @AfterThrowing  | 用于定义异常通知采处理程序中来处理的异常，相当于 ThrowAdvice 。在使用时可指定 pointcutlvalue 和 throwing 属性。 其中 pointcut/value 用于指定切入点表达式，而 throwing 属性值用于指定一个形参名来表示 Advice 方法中可定义与此罔名的形参，该形参可用于访问 目标方法抛出的异常 |
  |     @After      | 用于定义最终 final 通知 ， 不管是否异常，该通知都会执行。使用时需要指定一个 value 属性， 该属性用于指定该通知被植入的切入点 |
  | @DeclareParents | 用于定义引介通知，相当于 Introductionlnterceptor ( 不要求掌握) |

* 实例：Demo12

# Spring的数据库开发

## Spring JDBC

### Spring JdbcTemplate的解析

> 针对数据库的操作， Spring 框架提供了 JdbcTemplate 类，该类是 Spring 框架数据抽象层的基 础，其他更高层次的抽象类却是构建于 JdbcTemplate 类之上。 可以说， Jdbc T emplate 类是 Spring JDBC 的核心类。 

*  DataSource: 其主要功能是获取数据库连接，具体实现时还可以引入对数据库连接的缓 冲池和分布式事务的支持，它可以作为访问数据库资源的标准接口 。
*  SOLExceptionTranslator: org.springframework.jdbc.suppO比SOLExceptionT ranslator 接口 负责对 SOLException 进行转译工作。 通过必要的设置或者获取 SOLExceptionT ranslator 中的 方法，可以使 JdbcTemplate 在需要处理 SOLException 时，委托 SOLExceptionT ranslator 的实 现类来完成相关的转译工作。

### Spring JDBC的配置

>  Spring JDBC 模块主要由 4 个包组成，分别是 core (核心包)、 dataSource (数据源包)、 object (对象包)和 support (支持包)

* Spring JDBC中的主要国宝及说明

  |    包名    |                             说明                             |
  | :--------: | :----------------------------------------------------------: |
  |    core    | 包含了 JDBC 的核心功能，包括 JdbcTemplate 类、 SimpleJdbclnsert 类、 SimpleJdbcCal1 类以及 NamedParameterJdbcTemplate 类 |
  | dataSource | 访问数据源的实用工具类，白有多种数据源的实现，可以在 Java EE 容器外部测试 JDBC 代码 |
  |   object   | 以面向对象的方式访问数据库， 它允许执行查询并将返回结果作为业务对象，可以在数据袤的列和 业务对象的属性之间映射查询结果 |
  |  support   | 包含了 core 和 object 包的支持类，例如，提供异常转换功能的 SQLException 类 |

* dataSource的4个属性

  |      属性       |                      含义                       |
  | :-------------: | :---------------------------------------------: |
  | driverClassName | 所使用的驱动名称，对应驱动 JAR 包中的 Driver 类 |
  |       url       |                 数据源所在地址                  |
  |    username     |               访问数据库的用户名                |
  |    password     |                访问数据库的密码                 |

* 定义 jdbcTemplate 时，需要将 dataSource 注入到 jdbcTemplate 中，而其他需要使用 jdbcTempla恒的 Bean ，也需要将 jdbcTemplate 注入到该 Bean 中(通常注入到 Dao 类中，在 Dao 类中进行与数据库的相关操作)。

## Spring JdbcTemplate的常用方法

### execute()

> execute(String sql)方法能够完成执行 SOL 语句的功能。 

### update()

> update()方法可以完成插入、更新和删除数据的操作。 

* JdbcTemplate类中常用的update()方法

  |                        方法                        |                             说明                             |
  | :------------------------------------------------: | :----------------------------------------------------------: |
  |               int update(String sql)               | 该方法是最简单的 update 万法重载形式，百直接执行传入的 SQL 语句，并返回受影响的行数 |
  |      int update(PreparedStatementCreator psc)      | 该方法执行从 PreparedStatementCreator 返回的语句，然后 返回受影响的行数 |
  | int update(String sql,PreparedStatementSetter pss) | 该方法通过 PreparedStatementSetter 设置 SQL 语句中的参 数，并返回受影响的行数 |
  |       int update(String sql,Object... args)        | 该方法使用 Object...设置 SQL 语句中的参数，要求参数不能为 NULL ，并返回受影响的行数 |


### query()

> JdbcTemplate 类中还提供了大量的 queryO方法来处理各种对数据库表的查询操作。 

* JdbcTemplate中常用的query()方法

  |                             方法                             |                             说明                             |
  | :----------------------------------------------------------: | :----------------------------------------------------------: |
  |         List query(String sql, RowMapper rowMapper)          | 执行 String 类型参数提供的 SQL 语句，并通过 RowMapper 返回一个List 类型的结果 |
  | List query (String sql, PreparedStatementSetter pss, RowMapper rowMapper ) | 根据 String 类型参数提供的 SQL 语句创建 PreparedStatement 对象，通过 RowMapper 将结 果返回到List 中 |
  | List query ( String sql, ObjectD args, RowMapper rowMapper)  | 使用 Object口的值来设置 SQL 语句中的参数值，采用 RowMapper 回调万;去可以直接返回List 类型的制居 |
  | queryForObject(String sql, RowMapper rowMapper, RowMapper Object... args) | j'ij- args 参数绑定到 SQL 语句中，并通过 RowMapper 返回一个 Object 类型的单行记录 |
  | queryForList ( String sql,Object[] args， class\<T>  elementType) | 该方法可以返回多行数据的结果，但必须是返回列表 ，elementType 参数返回的是List 元素类型 |

# Spring的事务管理

## Spring事务管理概述

### 事务管理的核心接口

> 在 Spring 的所有 JAR 包中，包含一个名为 spring-tx-4.3.6.RELEASE 的 JAR 包，该包就是 Spring 提供的用于事务管理的依赖包。 

1.  PlatformTransactionManager 

   Platform T ransaction Manager 接口是 Spring 提供的平台事务管理器，主要用于管理事务。 

   * TransactionStatus getTransaction ( TransactionDefinition definition ):用于获取事务状 态信息。 
   *  void commit ( TransactionStatus status ):用于提交事务。
   *  void rollback ( TransactionStatus status ):用于回滚事务。 

    getTransaction ( TransactionDefinition definition )方法会根据 TransactionDefinition 参数返回一个 TransactionStatus 对象 , TransactionStatus 对象就表示一 个事务，它被关联在当前执行的线程上。

   PlatformTransactionManager 接口有许多不同的实现类

   * .springframework.jdbc.datasource. DataSourceTransactionManager: 用于配置 JDBC 数 据源的事务管理器。 
   * org .springframework.orm. hibernate4. HibernateTransactionManager :用于配置 Hibernate 的事务管理器。
   * org .springframework. transaction .jta.JtaTransactionManager: 用于配置全局事务管理器。

2. TransactionDefinìtion

   TransactionDefinition 接口是事务定义(描述)的对象，该对象中定义了事务规则，并提供 了获取事务相关信息的方法

   * String getName(): 获取事务对象名称。
   * int getlsolationLevel(): 获取事务的隔离级别。
   * int getPropagationBehavior(): 获取事务的传播行为。 
   * int getTimeout(): 获取事务的超时时间。 
   * boolean isReadOnly(): 获取事务是否只读。

   事务的传播行为是指在同一个方法中，不同操作前后所使用的事务。 



# MyBatis

> MyBatis (前身是 iBatis) 是一个支持普通 SOL 查询、存储过程以及高级映射的持久层框架， 它消除了几乎所有的 JDBC 代码和参数的手动设置以及对结果集的检索，并使用简单的 XML 或 注解进行配置和原始映射，用以将接口和 Java 的 POJO ( Plain Old Java Object，普通 Java 对 象)映射成数据库中的记录 使得 Java 开发人员可以使用面向对象的编程思想来操作数据库。 
>
> MyBatis 框架也被称之为 ORM (Object/Relational Mapping ，即对象关系映射)框架。 所 谓的 ORM 就是一种为了解决面向对象与关系型数据库中数据类型不匹配的技术，它通过描述 Java 对象与数据库表之间的映射关系，自动将 Java 应用程序中的对象持久化到关系型数据库的 表中。 

## MyBatis入门

>  \<mapper>元素是配置文件的根元素，它包含一个 namespace 属性，该属性为这个 \<mapper>指定了唯一的命名空间，通常会设置成"包名 +SQL 映射文件名"的形式。 子元素 \<select>中的信息是用于执行查询操作的配置 ，其 id 属性是<selecb元素在映射文件中的唯一 标识; paramete汀ype 属性用于指定传入参数的类型，这里表示传递给执行 SQL 的是一个 Integer 类型的参数j resultType 属性用于指定返回结果的类型，这里表示返回的数据是 Customer 类型。 在定义的查询 SQL 语句中， "#{)"用于表示一个占位符，相当于"?"，而 "#{id}" 表示该占位 符待接收参数的名称为 id

# MyBatis的核心配置

## Mybatis的核心对象

> 在使用 MyBatis 框架时，主要涉及两个核心对象: SqlSessionFactory 和 SqlSession ，它们 在 MyBatis 框架中起着至关重要的作用。 

### SqlSessionFactory

> SqlSessionFactory 是 MyBatis 框架中十分重要的对象，它是单个数据库映射关系经过编译 后的内存镜像，其主要作用是创建 SqlSession .
>
> SqlSessionFactory 对象的实例可以通过 SqlSessionFactoryBuilder 对象来构建，而 SqlSessionFactoryBuilder 则可以通过 XML 配置文件 或一个预先定义好的 Configuratiori 实例构建出 SqlSessionFactory 的实例。 
>
> Sq ISessionFactory 对象是线程安全的，它一旦被创建，在整个应用执行期间都会存在。
>
> 构建 SqlSessionFactory 实例时，建议使用单列模式。 

通过XML配置文件构建出的SqlSessionFactory

```java
读取配置文件 
    InputStream inputStream = Resources.getResourceAsStream("配置文件位置n) ;
     //根据配置文件构建 
    SqlSessionFactory SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder() .build(inputStream); 
```

### SqlSession

> SqlSession 是 MyBatis 框架中另一个重要的对象，它是应用程序与持久层之间执行交互操 作的一个单线程对象，其主要作用是执行持久化操作。
>
>  SqlSession 对象包含了数据库中所有执 行 SQL 操作的方法，由于其底层封装了 JDBC 连接，所以可以直接使用其实例来执行己映射的 SQL 语句。 

SqlSession 对象中包含了很多方法，其常用方法如下所示:

*  \<T> T selectOne ( String statement ):查询方法。 参数 statement 是在配置文件中定义的\<select>元素的 id。 使用该方法后，会返 回执行 SOL 语句查询结果的一条泛型对象。
*  \<T> T selectOne ( String statement, Object parameter ); 查询方法。 参数 statement 是在配置文件中定义的\<select>元素的 id ， parameter 是查询所 需的参数。 使用该方法后，会返回执行 SOL 语句查询结果的一条泛型对象。 
* \<E> List\<E> selectList ( String statement ); 查询方法。 参数 statement 是在配置文件中定义的\<select>元素的 id。 使用该方法后，会返 回执行 SOL 语句查询结果的泛型对象的集合。 
*  \<E> List\<E> selectList ( String statement, Object parameter ); 查询方法。 参数 statement 是在配置文件中定义的<selecb元素的 id ， parameter 是查询所 需的参数。 使用该方法后，会返回执行 SOL 语句查询结果的泛型对象的集合。 
*  void select ( String statement, Object parameter, ResultHandler handler ); 查询方法。 参数 statement 是在配置文件中定义的<selecb元素的 id ， parameter 是查询所 需的参数， ResultHandler 对象用于处理查询返回的复杂结果集，通常用于多表查询。 
*  int insert ( String statement ); 插入方法。 参数 statement 是在配置文件中定义的<inserb元素的 id。 使用该方法后，会返 回执行 SOL 语句所影响的行数。 
*  int insert ( String statement, Object parameter ); 插入方法。 参数 statement 是在配置文件中定义的<inserb元素的 id ， parameter 是插入所 需的参数。 使用该方法后，会返回执行 SOL 语句所影响的行数。 
*  int update ( String statement ); 更新方法。 参数 statement 是在配置文件中定义的\<update>元素的 id。 使用该方法后，会 返回执行 SOL 语句所影响的行数。
* int update ( String statement, Object parameter ); 更新方法。 参数 statement 是在配置文件中定义的\<update>元素的 id ， parameter 是更新 所需的参数。 使用该方法后，会返回执行 SOL 语句所影响的行数。 
*  int delete ( String statement ); 删除方法。 参数 statement 是在配置文件中定义的\<delete>元素的 id。 使用该方法后，会返 回执行 SOL 语句所影响的行数。
*  int delete ( String statement, Object parameter ); 删除方法。 参数 statement 是在配置文件中定义的\<delete>元素的 id ， parameter 是删除所 需的参数。 使用该方法后，会返回执行 SOL 语句所影响的行数。 
* void commit(); 提交事务的方法。
* void rollback() 回滚事务的方法。
* void closeO j 关闭 SqlSession 对象。
*  \<T> T getMappe r(Class\<T> type) j i衷方法会返回 Mapper 接口的代理对象，该对象关联了 SqlSession 对象，开发人员可以使 用该对象直接调用方法操作数据库。 参数 type 是 Mapper 的接口类型。 MyBatis 官方推荐通过 Mapper 对象访问 MyBatis。
* Connection getConnectionO j 获取 JDBC 数据库连接对象的方法。    

## 配置文件

### 主要元素

> 在 MyBatis 框架的核心配置文件中， \<configuration>元素是配置文件的根元素，其他元素 都要在 \<configuration>元素内配置。 

### \<properties>元素

> \<properties>是一个配置属性的元素，该元素通常用于将内部的配置外在化，即通过外部的 配置来动态地替换内部定义的属性。










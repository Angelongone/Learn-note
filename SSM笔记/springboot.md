# springboot 入门

## @AutoConfigurationPackage:自动配置包
将住配置类(@springbootApplication标注的类)的所在包及下面的所有组件扫描到spring容器中。

## 使用Spring Initializer快速创建Spring boot项目
默认生成的Springboot项目：
* 主程序已经生成好了，我们只需要编写我们需要的逻辑。
* resources文件夹中目录结构：
	* static:保存所有的静态资源：js css images;
	* templates:保存所有的模板页面;(Spring boot 默认jar包使用嵌入式的Tomcat。默认不支持jsp页面);可以使用模板引擎(freemarker,thymeleaf）;
	* appllcation.properties:Spring boot应用的配置文件;可以修改一些默认设置。

# 二、配置文件
## 1.配置文件
> Springboot 使用一个全局的配置文件。配置文件名是固定的;
* application.properties
* application.yml

* 配置文件的作用：修改Springboot自动配置的默认值;Springboot 在底层都给我们自动配置好了;
* YAML (YAML Ain't Markup Language)
	* YAML A Maarkup Language :是一个标记语言
	* YAML isn't Markup Language:不是一个标记语言
* 标记语言：
	* 以前的配置文件;大多都使用的是**XXX.xml**文件;
	* YAML:以数据为中心，比json,xml等更适合做配置文件;
			```yml
			server
			port:8080
			```
## 2.YAML语法
### 1.基本语法：
K:v :表示一对键值对(空格必须有)
以空格的缩进来控制层级关系;只要是有对齐的一列素据，都是同一个层级的
```yml
server:
port: 8080
path: /hello
```
属性和值也是大小写敏感的;

### 2.值的写法
* 字面量：普通的值(数字，字符串，布尔)
		K:V 字面直接来写;
		字符串默认不用加上单引号或双引号;
		"":双引号不会转义字符串中的特殊字符；特殊字符会作为本身想表示的意思
		'':单引号会转义特殊字符，特殊字符是最终只是一个普通的字符串数据

* 对象、Map(属性和值)(键值对)
		k: v:在下一行来写对象的属性和值的关系;注意缩进;
		对象还是k: v的方式：
		```yml
		friends:
		lastName: Angelong
		age: 21
		```
		行内写法：
		```yml
		friends: {lastName: Angleong, age: 21}
		```

* 数组(List,Set)
		用- 值表示数组中的一个元素
		```yml
		pets:
	- cat
	- dog
	- pig
	```
	用行内写法：
	```yml
	pets: [cat,dog,pig]
	```
### 3.配置文件值的注入
配置文件：
```yml
person:
lastName: hello
age: 21
boss: false
birth: 1999/05/21
maps: {k1: v1,k2: v2}
lists: 
		- lisi
		- zhaoliu
		dog:
		name: 小狗
		age: 12
		```

		javabean:
		```java
		/**
* 将配置文件中配置的每一个属性的值，映射到这个组件中
* @ConfigurationProperties:告诉Springboot将本类中的所有属性和配置文件中相关的配置进行绑定;
*		prefix = "person":配置文件中那个下面的所有属性进行--映射
*
*		只有这个组件，才能为人哦拿起提供的@ConfigurationProperties功能;
*/
@Component
@ConfigurationProperties(prefix = "person")
public class Person{
private String lastName;
private Integer age;
private Boolean boss;
private Date birth;

private Map<String,Object> maps;
private List<Object> lists;
private Dag dog;
}
```
### @Value获取值和@ConfigurationProperties获取值的比较
| |@ConfigurationProperties|@Value|
|----|----|----|
|功能|批量注入配置文件中的属性|一个个指定|
|松散绑定(松鼠语法)|支持|不支持|
|SpEl|不支持|支持|
|JSR303数据校验|支持|不支持|
|复杂类型封装|支持|不支持|

### 5.@PropertySource&@ImportResource
* @PropertySource:加载指定的配置文件;
* @ImportResource:导入Spring的配置文件，让配置文件里面的内容生效。
		Springboot里面没有Spring的配置文件，我们自己编写的配置文件，也不能自动识别;
		想让Spring的配置文件生效，加载进来：@ImportResource标注在一个配置类上
		```java
		@ImportResource(locations = {"classpath:beans.xml"})
		导入Spring的配置文件让其生效
		```

### 6.Springboot推荐给容器中添加组件的方式;推荐使用全注解的方式
1. 配置类 === Spring配置文件
2. 使用@Bean给容器中添加组件
3. @Configuration标注当前类是配置类
```java
/**
* @Configuration:指明当前类是一个配置类;就是来代替之前的Spring配置文件
*
* 在配置文件中用<bean></bean>标签添加组件
*/
@Configuration
public calss MyAppConfig{
//将方法的返回值添加到容器中，这个组件默认的id就是方法名
@Bean
public HelloService helloService(){
System.out.println("配置类@Bean给容器中添加组件了。。。")
return new HelloService();
}
}
```
### 7.配置文件占位符
1. 随机数
```java
${random.value},${random.int},${random.long}
${random.int(10)},${random.int[1024,65536]}
```
2. 占位符获取之前配置的值，如果没有可以是用：指定默认值
```properties
person.last-name=张三${random.uuid}
person.dog.name=${person.hello:hello}_dog
```
### 8.Profile
1. 多Profile文件
我们在住配置文件编写的时候，文件名可以是 application-{profile}.properties/yml
默认使用application.properties的配置;
2. yml支持多文档块方式
```yml
server:
port: 8081
spring:
profiles:
active: dev

---
server:
port: 8082
spring:
profiles: dev

---
server:
port: 8083
spring:
profiles: prodza
```
3. 激活指定profile
1. 在配置文件中指定spring.profiles.active=dev
2. 命令行：
java -jar spring-boot-02-config-0.0.1-SNAPSHOT.jar --spring.profiles.active-dev;
可以直接在测试的时候，配置传入命令行参数
3. 虚拟机参数：
-Dspring.profiles.active=dev
### 9.配置文件加载位置
springboot启动会扫描以下安慰在的apllication.properties或者application.yml文件作为Springboot的默认配置文件
-file:./config/
-file:./
-classpath:/config/
-classpath:/
优先级由高到低，高优先级的配置会覆盖低优先级的配置;
Springboot会从这四个位置全部加载主配置文件;
* 互补配置：
		我们还可以通过spring.config.location来改变默认文件位置
		项目打包好以后，我们可以使用命令行参数的形式，启动项目的时候来指定配置文件的新位置;指定配置文件和默认加载的这些配置文件共同起作用形成互补配置;

# 二、SpringBoot与日志
## 1.日志
* 日志门面：SLF4J
* 日志实现：Logback
* Springboot：底层是Spring框架，Spring框架默认是用JCL;
		Springboot选用SLF4J和Logback;

## 2.SLF4j使用
### 1.如何在系统中使用SLF4j
以后开发的时候，日志记录方法的调用，不应该来直接调用日志的实现类，而是调用日子抽象层里面的方法;
给系统里面导入slf4j的jar和logback的实现jar
```java
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HelloWord{
public static void main(String[] args){
		Logger logger = LoggerFactory.getLogger(HelloWord.class);
		logger.info("Hello Word");
	}
}
```
# 四、Springboot与Web开发
> 使用Springboot：
> 1.创建Springboot应用，选中我们需要的模块;
> 2.Springboot已经默认将这些场景配置好了，只需要在配置文件中指定少量配置就可以运行起来
> 3.编写业务代码;
## 1.模板引擎
> JSP,Velocity,Freemarker,Thymeleaf

### 1.引入hymeneal
```xml
<dependency>
	<groupId>org.springframework.boot</groupId>
	<artifactId>spring-boot-starter-thymeleaf</artifactId>
</dependency>
<!--更改版本-->
<!--<properties>中添加一下代码-->
<thymeleaf.version>3.0.2.RELEASE</thymeleaf.version>
<thymeleaf-layout-dialect.version>2.1.1</thymeleaf-layout-dialect.version>
```
### 2.Thymeleaf使用&语法
> 只要我们把HTML页面放在classpath:/templates/，thymeleaf就能自动渲染;
#### 使用
1. 导入thymeleaf的名称空间
```html
<html lang="en" xmlns:th="http://www.thymeleaf.org">
```
2. 使用thymeleaf语法
```html
<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org">
<head>
    <meta charset="UTF-8">
    <title>thymeleaf</title>
</head>
<body>
    <p th:text="'用户名：' + ${name}"></p>
</body>
</html>
```
### 3.语法规则
1. th:text;改变当前元素里面的文本内容;
	th:任意html属性来替换原生属性的值
包含内容：
* 片段包含 ：th:insert th:replace
* 遍历：th:each
* 判断：th:if th:unless th:switch th:case
* 变量声明：th:object th:with
* 任意变量属性修改：th:attr th:attrprepend
* 修改指定属性默认值：th:value th:href th:src ....
* 修改标签体内容：th:text(转义特殊字符) th:utext(不转义特殊字符)
* 声明片段： th:fragment th:remogve

2. 表达式
```propertis
Simple expressions:(表达式语法)
	Variable Expressions:${...}:获取变量值：OGNL;
	1. 获取对象的属性，调用方法
	2. 使用内置对象：
		#ctx: the context object
		#vars: the context variables.
		#locale: the context locale.
		#request:(only in web Contexts) the HttpServletRequest object.
		#response:(only in web Contexts) the HttpServletResponse object.
		#session:(only in Web Contexts) the HttpSession object.
		#servletContext:(only in Web Contexts) the ServletContext object.
		例子：${session.foo}
	3.内置的一些工具
	Selection Variable Expressions: *{...}:选择表达式和${..}在功能上是一样的;
		补充配合 th:object="${session.user}"
		<div th:object="${session.user}">
			<p>Name:<span th:tex="*{firstName}">Sebastian</span>.</p>
			<p>Surname:<span th:tex="*{lastName}">Pepper</span>.</p>
			<p>Nationality:<span th:tex="*{nationality}">Saturni</span>.</p>
		</div>
	Message Expressions:#{...}:获取国际化内容
	Link URL Expressions:@{...}:定义URL
		@{/order/process(execId=${execId},execType='FAST')}
	Fragment Expressions:~{...}:片段引用表达式
		<div th:insert="~{commons :: mainr}"></div>
```
### 如何修改SpringBoot的默认配置
模式：
	1. Springboot在自动配置很多组件的时候，先看容器中有没有用户自己配置的(@Bean，@Commponent)如果有就有用户配置的，如果没有，才自动配置如果有些组件可以有很多(ViewResolver)将用户配置的和自己默认的组合起来

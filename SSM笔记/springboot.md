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



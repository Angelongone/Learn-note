
# web相关概念回顾

## 1.软件构架

1）C/S：客户端/服务器端

2）B/S：浏览器/服务器端

## 2.资源分类

1）静态资源：所有用户访问后，得到的结果都是一样的，称为静态资源，静态资源可以直接被浏览器解析

​	如：html,css,javascript

2)动态资源：每个用户访问相同资源后，得到的结果可能不一样。称为动态资源。动态资源被访问后，需要先转换为静态资源，在返回给浏览器

​	如：servlet/jsp,php,asp......

## 3.网络通信三要素

1）IP：电子设备（计算机）在网络中的唯一标识。

2）端口：应用程序在计算机中的唯一标识。0~65536

3）传输协议：

​	1.基础协议：

​		1）tcp:安全协议，三次握手。速度稍慢

​		2）udp：不安全协议。速度快

# web服务器软件

服务器：安装了服务器软件的计算机

服务器软件：接收用户的请求，处理请求，作出相应。

web服务器软件：接收用户的请求，处理请求，做出相应。

​	在web服务器软件中，可以部署web项目，让用户通过浏览器来访问这些项目

​	web容器

常见的java相关的web服务器软件：

​	webLogic：Oracle公司，大型JavaEE服务器，支持所有的JavaEE规范，收费。

​	webSphere：IBM公司，大型JavaEE服务器，支持所有的JavaEE规范，收费。

​	JBOSS：JBOSS公司的，大型JavaEE服务器，支持所有的JavaEE规范，收费。

​	Tmacat:Apache基金组织，中小型JavaEE服务器，仅仅支持少量的JavaEE规范servlet/jsp。开源，免费。

JavaEE：java语言在企业及开发中使用的技术规范的总和，一共规定了13项大的规范

# Tomcat：web服务器软件

## 1.下载：

http://tomcat.apache.org/

## 2.安装：

解压压缩包即可

注意：安装目录建议不要有中文和空格

## 3.卸载：

删除目录就行了

## 4.启动：

bin/startup.bat.双击运行该文件即可

访问：浏览器：http://localhost:8080 访问自己的

​						http://别人的IP：8080  访问别人的

可能遇到的问题：

​	1.黑窗口一闪而过：

​		原因：没有正确配置JAVA_HOME环境变量

​	2.启动报错：

​		1）暴力：找到占用的端口号，并且找到对应的进程，杀死该进程。

​			netstat  -ano

​		2)温柔：修改自身的端口号

​			conf/server.xml

​			一般会将tomcat的默认端口号改为80。80端口号是HTTP协议的默认端口号。

​				好处：在访问时，就不用输入端口号了

## 关闭

1.正常关闭：

​	bin/shutdown.bat

​	ctrl+c

2.强制关闭

​	点击窗口的X

## 配置 

部署项目的方式：

​	1.直接将项目放到webapps目录下即可。

​		/hello：项目的访问路径-->虚拟目录

​		简化部署：将项目打成war包，再将war包放置到WebApps目录下。

​			war包会自动解压缩

​	2.配置conf/server.xml文件

​		在<Host>标签体中配置

​		<Context docBase="D:\hello" path="/hehe"/>

​		docBase:项目存放的路径

​		path:虚拟目录

​	3.在conf\Cotalina\localhost创建任意名称的xml文件。在文件中编写

​		<Context docBase="D:\hello"/>

​			虚拟目录：xml文件的名称

静态项目和动态项目：

​	目录结构

​		java动态项目的目录结构：

​			--项目的根目录

​				--WEB-INF目录：

​					--web.xml:web项目的核心配置文件

​					--classes目录：放置字节码文件的目录

​					--lib目录：放置依赖的jar包

将Tomcat部署到idea上，开发web项目 

# Servlet

## 概念：运行在服务器端的小程序

​	Servlet就是一个接口，定义Java类被浏览器访问到（Tomact识别）的规则。

​	将来我们自定义一个类，实现Servlet接口，复写方法。

## 快速入门：

​	1.创建JavaEE项目

​	2.定义一个类，实现Servlet接口

​				public class Text1_Servlet implements Servlet 

​	3.实现接口中的抽象方法

​	4.配置Servlet：在web.xml中

<!--配置Severlet-->

<servlet>    

​	<servlet-name>Text1</servlet-name>    

​	<servlet-class>cn.itcast.web.servlet.Text1_Servlet</servlet-class></servlet>

<servlet-mapping>    

​	<servlet-name>Text1</servlet-name>    

​	<url-pattern>/Text1</url-pattern>

</servlet-mapping>

## 执行原理：

1.当服务器接受到客户端浏览器的请求，会解析请求URL路径，获取访问的Servlet的资源路径

2.查找web.xml文件，是否有对应的<url-pattern>标签体内容。

3.如果有，则在找到对应的<servlet-class>全类名

4.Tomcat会将字节码文件加载进内存，并且创建其对象‘

5.调用其方法



## Servlet方法：

### init方法：

​	初始化方法

​	在Servlet被创建时，只会执行一次。

### service方法：

​	提供服务的方法

​	每次Servlet被访问时，执行。执行多次。

### destroy方法：

​	销毁方法

​	在服务器正常关闭时，执行，执行一次。

### ServletConfig方法：

​	获取ServletConfig对象

​	ServletConfig：Servlet的配置对象

### getServletInfo方法：

​	获取Servlet的一些信息，版本，作者等等...

## Servlet中的生命周期：

​	1.被创建：执行init方法，只执行一次

​		指定Servlet的创建时机：

​			在<Servlet>标签下配置

​			1.第一错被访问时，创建

​				<load-on-startup>的值为负数<默认-1>

​			2.在服务器启动时。创建

​				<load-on-startup>的值为0或正整数

​		Servlet的init方法，只执行一次，说明一个Servlet在内存中只存在一个对象，Servlet是单例的

​			多个用户同时访问时，可能存在安全问题。

​			解决：尽量不要在Servlet中定义成员变量。即使定义了变量也不要对其修改值。  

​	2.提供服务：执行Service方法。执行多次。

​		每次访问Servlet时，Service方法都会被调用一次。

​	3.被销毁：执行destroy方法，执行一次

​		Servlet被销毁时执行。服务器关闭时，Servlet被销毁。

​		只有服务器正常关闭时，才会执行destroy方法

​		destroy方法在Servlet被销毁之前执行，一般用于释放资源

​	4.Servlet3.0：

​		好处：支持注解配置。可以不需要web.xml了。

​		步骤：

​			1.创建JavaEE项目，选择Servlet的版本3.0以上，可以不创建web.xml

​			2.定义一个类，实现Servlet注解，进行配置

​			3.复写方法

​			4.在类上使用@WebServlet注解，进行配置

​				@WebServlet（“资源路径”）

## IDEA与Tomcat的相关配置

1.IDEA会为每一个Tomcat部署的项目单独建立一份配置文件

​	查看控制台的log：Using CATALINA_BASE:     "路径"

2.工作空间项目 和 Tomcat部署的web项目

​	Tomcat真正访问的是“Tomcat部署的web项目”,"tomcat 部署的web项目"对应着"工作空间项目"的web目录下的所有资源

​	WEB-INF目录下的资源不能被浏览器直接访问。

 
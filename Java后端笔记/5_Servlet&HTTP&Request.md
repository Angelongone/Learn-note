# Servlet

## Servlet的体系结构

### Servlet -- 接口

#### GengricServlet  --  抽象类

##### HttpServlet -- 抽象类

### GenericServlet：将Servlet接口中其他的方法做了默认空实现，只将service（） 方法作为抽象

##### 将来定义Servlet类时，可以继承GenericServlet，实现service（）方法即可 

### HttpServlet:对http协议的一种封装，简化操作

1.定义类继承HttpServlet

2.复写doGet/doPost方法

### Servlet相关配置

urlpartten:Servlet访问路径

1.一个Servlet可以定义多个访问路径：@WebServlet({"/d4","/dd4","/ddd4"})

2.路径定义规则：

1）/XXX

2)/XXX/XXX:多层路径，目录结构

3）*.do

# HTTP协议

## 概念：超文本传输协议

​	传输协议：定义了，客户端和服务器端通信时，发送数据的格式

​	特点：

​		1.基于TCP/IP的高级协议

​		2.默认端口号：80

​		3.基于请求/响应模型的：一次请求对应一次响应

​		4.无状态的：每次请求之间相互独立，不能交互数据

​	历史版本：

​		1.0：每一次请求响应都会建立新的连接

​		2.1：复用连接

### 请求消息数据格式

1.请求行

​	请求方式 请求url 请求协议/版本

​	GET /login.html HTTP/1.1

​	请求方式：

​		HTTP协议有7种请求方式，常用的有2种

​			GET：

​				1.请求参数在请求行中，在url后。

​				2.请求的url长度有限制

​				3.不太安全

​			POST：

​				1.请求参数在请求体中

​				2.请求的url长度没有限制

​				3.相对安全

2.请求头

​	请求头名称：请求头值

​	常见的请求头：

​		1.User-Agent:浏览器告诉服务器，我访问你使用的浏览器版本信息。

​				可以在服务器端获取该同的信息，解决浏览器的兼容性问题

​		2.Referer:http://localhost/login.html

​			告诉服务器，我（当前请求）从哪里来？

​				作用：

​					1.防盗链

​					2.统计工作

3.请求空行

​	空行，就是用于分割POST请求的请求头和请求体的

4.请求体（正文）

​	封装POST请求消息的请求参数的

字符串格式：

​	POST  /login.html   HTTP/1.1

​	Host : localhost

​	userName=Angelong

# Request

 ## 1.request对象和response对象的原理

1.request和response对象是由服务器创建的，我们来使用他们

2.**request对象是来获取请求消息，response对象是来设置响应消息**

## 2.request对象继承体系结构

ServletRequest  -- 接口

​			|			继承

HttpServleRequest  --  接口

​			|			实现

org.apache.catalina.connector.PequestFacade  类（tomca）

## 3.request功能：

1.获取请求消息数据

​	1.获取请求行数据

​		GET /day14/demo1?name=Angelong  HTTP/1.1

​		方法：

​			1.获取请求方式：GET

​				String  getMethod（）

​			2.（重要）获取虚拟目录：/day14

​				String getContextPath()

​			3.获取Servlet路径：/demo1

​				String getServletPath()

​			4.获取get方式请求参数：name=Angelong

​				String getQueryString()

​			5.（重要）获取请求URL：/day14/demo1

​				String getRequestURl（）		：/day14/domo1

​				String Buffer getRequestURL()	: http://localhost/day14/demo1



​				URL：同一资源定位符

​				URI：同一资源标识符

​			6.获取协议及版本：HTTP/1.1

​				String getProtocol()

​			7.获取客户机的IP地址：

​				StringgetRemoteAddr()

​	2.获取请求头数据

​		方法：

​			（重要）String getHeader(String name):通过请求的名获取请求头的值

​				Enumeration<String>  getHeaderNames();获取所有的请求头名称

​	3.获取请求体数据：

​		请求体：只有POST请求方式，才有请求体，在请求体中封装了POST请求的请求参数

​		步骤：

​			1.获取输入流对象

​				BufferedReader getReader():获取字符输入流，只能操作字符数据

​				ServletInputStream getInputStream():获取字节输入流，可以操作所有类型数据

​					在文件上传知识点后讲解

​			2.再从流对象中拿数据

​	2.其他功能

​		1.获取请求参数通用方式:不论get还是post请求方式都可以使用下列方法来获取请求参数

​			1）（常用）String getParameter(String name):根据参数名称获取参数值	username=zs&password=123

​			2)String[] getParameterValues(String name):根据参数名称获取参数值的数组	hobby=XX&hobby=name

​			3)Enumeration<String> getParameterNames():获取所有请求的参数名称

​			4）（常用）Map<String,String> getParameterMap():获取所有参数的map集合

​			

​			中文乱码问题：

​				get方式：Tomcat8已经将get方式乱码问题解决了

​				post方式：会乱码

​					解决：在获取参数前，设置request的编码  ： request.setCharacterEncoding("utf-8");

​		2.请求转发：一种在服务器内部的资源跳转方式

​			1.步骤：

​				1）通过request对象获取请求转发器对象：RequestDispatcher getRequestDispatcher(String path)

​				2)使用RequestDispatcher 对象来进行转发：forward(ServletRequest request,ServletResponse response)

​			2.特点：

​				1）浏览器地址栏路径不发生改变

​				2)只能发到当前服务器内部资源中。

​				3）转发一次请求 

​		3.共享数据

​			域对象：一个有作用范围的对象，可以在范围内共享数据

​			request域：代表依次请求的范围，一般用于请求转发多个资源中共享数据

​			方法：

​				1）void setAttribute(String name,Object obj):存储数据

​				2）Object getAttitude(String name):通过键获取值

​				3）void removeAttribute(String name):通过键移除键值对

​		4.获取ServletContext  发
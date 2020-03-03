# 会话技术

1. 会话：一次会话中包含多次请求和响应。

   * 一次会话：浏览器第一次给服务器资源发送请求，会话建立，直到有一方单开为止

2. 功能：在一次会话的范围内的多次请求间，共享数据

3. 方式：

   1. 客户端会话技术：Cookie
   2. 服务器端会话技术：Session

   

# Cookie

1. 概念：客户端会话技术，将数据保存到客户端

2. 快速入门：

   * 使用步骤：
     1. 创建Cookie对象，绑定数据
        * new Cookie(String name , String value)
     2. 发送Cookie对象
        * response.addCookie(Cookie cookie)
     3. 获取Cookie，拿到数据
        * Cookie[] request.getCookies()

3. 实现原理

   * 基于响应头set-cookie和请求头cookie实现

4. cookie的细节

   1. 一次可不可以发送多个cookie？

      可以创建多个cookie对象，使用response调用多次addCookie方法发送cookie即可

   2. cookie在浏览器中保存多长时间？

      1. 默认情况下，当浏览器关闭后，cookie数据被销毁
      2. 持久化存储：
         * setMaxAge(int seconds)
           1. 正数：将cookie数据写到硬盘的文件中。持久化存储。cookie存活时间
           2. 负数：默认值，浏览器关闭，数据销毁
           3. 零：删除cookie信息

   3. cookie能不能存中文？

      * 在tomcat 8 之前 cookie中不能直接存储中文数据
        * 需要将中文数据转码---一般采用URL编码（%E3）
      * 在tomcat 8 之后，cookie支持中文数据

   4. cookie获取范围多大？

      * 假设在一个tomcat服务器中，部署了多个Web项目中cookie能不能共享？
        * 默认情况下cookie不能共享
        * setPath(String path):设置cookie的获取范围。默认情况下，设置当前的虚拟目录
          * 如果要共享，则可以将path设置为“ / ”
      * 不同的tomcat服务器间cookie共享问题？
        * setDomain(String path):如果设置以及域名相同，那么多个服务器之间cookie可以共享
          * setDomain(".baidu.com"), 那么tieba.baidu.com和news.baidu.com中cookie可以共享

   5. cookie的特点和作用

      1. cookie存储数据在客户端浏览器
      2. 浏览器对于单个cookie的大小有限制（4kb）以及 对同一个域名下的总cookie数量也有限制（20个)

      * 作用：
        1. cookie一般用于存储少量的不太敏感的数据
        2. 在不登录的情况下，完成服务器对客户端的身份识别

# JSP：入门学习

1. 概念：

   * Java ServerPages：java服务器端页面

     * 可以理解为：一个特殊的页面，其中既可以指定定义HTML标签，又可以定义Java代码

     * 用于简化书写！！！！

2. 原理

   * JSP本质上就是一个Servlet

3. JSP的脚本：JSP定义Java代码的方式

   1. <%   代码    %>:定义的Java代码，在service方法中。service方法中可以定义什么，该脚本就可以定义什么。
   2. <%!	代码   %>:定义的Java代码，在jsp转换后的Java类的成员位置
   3. <%=   代码    %>:定义的Java代码，会输出到页面。输出语句中可以定义什么，该脚本就可以定义什么。

4. JSP的内置对象

   * 在jsp页面中不需要获取和创建，可以直接使用的对象
   * jsp一共有9个内置对象。
   * 先学习3个：
     * request
     * response
     * out:字符输出流对象。可以将数据输出到页面上。和response.getWriter()类似
       * response.getWriter()和out.write()区别：
         * 在tomcat服务器真正给客户端做出相应前，会线找response缓冲区数据，再找out缓冲区数据。
         * response.getWriter()数据输出永远再out.write()之前

# Session

1. 概念：服务器端会话技术，再一次会话的多次请求间共享数据，将数据保存再服务器的对象中。HttpSession

2. 快速入门：

   1. 获取HttpSession对象：

      HttpSession session = request.getSession();

   2. 使用HttpSession对象：

      Object getAttribute(String name)

      void setAttribute(String name, Object value)

      void removeAttribute(String name)

3. 原理：

   * Session的实现是依赖于Cookie的。

4. 细节：

   1. 客户端关闭后，服务器不关闭，两次获取session是否为同一个？

      * 默认情况下。不是

      * 如果需要相同，则可以创建Cookie，键为JSESSIONID，设置最大存活时间，让Cookie持久化保存。

        Cookie c = new Cookie("JDESSIONID",session.getId());

        ​		c.setMaxAge(60*60);

        ​		response.addCookie(c);

   2. 客户端不关闭，服务器关闭后，两次获取的session是同一个吗？

      * 不是同一个，但是要确保数据不丢失
        * session的钝化：
          * 再服务器正常关闭之前，将session对象系列化到硬盘上
        * session的活化：
          * 在服务器启动后，将session文件转化为内存中的session对象即可。

   3. session的失效时间？
      1.服务器关闭

      2.session对象调用invalidate().

      3.session默认失效时间   30分钟

      ​		选择性配置修改

      ​		<session-config>

      ​			<session-timeout>30</session-timeout>

      ​		</session-config>

   5.session的特点

   ​	1.session 用于存储一次会话的多次请求的数据，存在服务器端

   ​	2.session 可以存储任意类型，任意大小的数据

   ​	session与Cookie的区别：

   ​		1.session存储数据在服务器端，Cookie在客户端

   ​		2.session没有数据大小限制，Cookie有

   ​		3.session数据相对安全，Cookie相对不安全
# 1.Http协议：响应消息

1. 请求消息：客户端发送给服务器端的数据

   * 数据格式：
     1. 请求行
     2. 请求头
     3. 请求空行
     4. 请求体

2. 响应消息：服务器端发送给客户端的数据

   * 数据格式：

     1. 响应行

        1. 组成：协议/版本 响应状态码 状态码描述

        2. 响应状态码：服务器告诉客户端浏览器本次请求和响应的一个状态。

           1. 状态码都是3位数字

           2. 分类：

              1. 1xx：服务器接收客户端消息，但没有接收完成，等待一段时间后，发送1xx多状态

              2. 2xx：成功 

                 代表：200

              3. 3xx：重定向。

                 代表：302（重定向），304（访问缓存）

              4. 4xx：客户端错误。

                 代表：

                 ​	404:（请求访问路径没有对应的资源）

                 ​	405：（请求方式没有响应的doXxx方法）

              5. 5xx：服务器端错误。代表：500（服务器内部出现异常）

     2. 响应头

        1. 格式：头名称：值
   2. 常见的响应头：
           1. Content-type:服务器告诉客户端本次响应体数据格式以及编码格式（content/内容、目录）
               2. Content-disposition:服务器告诉客户端以什么格式打开响应体数据 (disposition/处置)
              * 值：
                * in-line:默认值，当前页面内打开
                * attachment ; filename=xxx :以附件形式打开响应体。文件下载 (attachment/附件)
     
     3. 响应空行
     
     4. 响应体：传输的数据

# 2.Response对象

* 功能：设置响应消息
  1. 设置响应行
     1. 格式：HTTP/1.1 200 ok
     2. 设置状态码：setStatus(int sc) （status/状态，地位）
  2. 设置响应头:setHeader(String name, String value)
  3. 设置响应体:
     * 使用步骤：
       1. 获取输出流
          * 字符输出流：PrintWriter getWriter()
          * 字节输出流：ServletOutputStream getOutputStream()
       2. 使用输出流，将数据输出到客户端浏览器

* 案例：

  1. 完成 重定向

     * 重定向：资源跳转的方式

     * 代码实现：

       1：设置状态码为302(一般不用)

       ​		response.setStatus(302);

       ​		设置响应头location

       ​		response.setHeader("location","/Text1");（location/位置地点)

       2:简单的重定向方法

       ​	response.sendRedirect("/Text2");(send/发送)(redirect/重新寄送)

     ### 例子：

     ```Java
     @WebServlet("/Text4")
     public class Text4_Servlet extends HttpServlet {
     	@Override
         protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         //        super.doPost(req, resp);
         System.out.println("访问：Text4");
         /*//1.设置状态码为302
         resp.setStatus(302);
         //2.设置响应头location
         resp.setHeader("location","/Text5");*/
         resp.sendRedirect("/Servlet/Text5");
         }
         @Override
         protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         this.doPost(req,resp);
         }
         }
     ```

     * 重定向的特点：redirect
       1. 地址栏发生变化
       2. 重定向可以访问其他站点（服务器）的资源
       3. 重定向是两次请求。不能使用request对象来共享数据
     * 转发的特点：forward
       1. 转发地址栏不变
       2. 转发只能访问当前服务器下的资源
       3. 转发是一次请求，可以使用request对象来共享数据
     *  路径分类：
       1. 相对路径：通过相对路径不可以确定唯一资源
          * 如：./index.html
          * 不已/开头，以. 开头路径
          * 规则：找到当前资源和目标资源之间的相对位置关系
       2. 绝对路径：通过绝对路径可以确定唯一资源
          * 如：http://localhost/servlet/text2   /servlet/text2
          * 以/开头的路径
          * 规则：判断西医的路径是给谁的？判断请求将来从哪儿发出
            * 给客户端浏览器使用：需要加虚拟目录（项目的访问路径）
              * 建议虚拟目录动态获取：request.getContextPath()
              * <a>,<form>重定向....
            * 给服务器使用：不需要加虚拟目录
              * 转发路径

  2. 服务器输出字符数据到浏览器

     * 步骤：

       1. 获取输出流
       2. 输出数据

     * 注意事项：

       * 乱码问题：

         1. PrintWriter pw = response.getWriter();获取流的默认编码是ISO-8850-1

         2. 设置该流的默认编码

         3. 告诉浏览器响应体使用的编码

            简单的形式，设置编码，是在获取流之前设置

            response.setContentType("text/html;charset=utf-8");

     ### 例子：

     ```Java
     @WebServlet("/Text6")
     public class Text6_Servlet extends HttpServlet {
     	@Override
         protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         //设置编码
         resp.setContentType("text/html;charset=utf-8");
         //获取字节流
         PrintWriter pw = resp.getWriter();
         pw.write("<h1>你好 !</h1>");
         }
         @Override
         protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
         this.doPost(req,resp);
         }
      }
     ```

     

  3. 服务器输出字符数据到浏览器

     * 步骤：
       1. 获取字符输出流
       2. 输出数据

  ## 例子：

  ```Java
  @WebServlet("/Text7")
  public class Text7_Servlet extends HttpServlet {
  	@Override
      protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      resp.setContentType("text/html;charset=utf-8");
      //1.获取字节输出流
      ServletOutputStream sos = resp.getOutputStream();
      //2.输出数据
      sos.write("你好".getBytes("utf-8"));
      }    
      @Override
      protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      this.doGet(req,resp);
      }
    } 
  ```

  4.验证码

  1. 本质：图片
  2. 目的：防止恶意表单注册

  ### 例子：

  ```java
  @WebServlet("/Text8")
  public class Text8_Servlet extends HttpServlet {
      @Override
      protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          int width = 100;
          int heigh = 50;
          //1.创建一个对象，在内存中图片（验证码图片对象）
          BufferedImage image = new BufferedImage(width,heigh,BufferedImage.TYPE_INT_RGB);        
          //2.美化图片        
          //2.1 填充背景色        
          Graphics g = image.getGraphics();//画笔对象
          g.setColor(Color.PINK);//设置画笔颜色
          g.fillRect(0,0,width,heigh);        
          //2.2画边框
          g.setColor(Color.BLUE);
          g.drawRect(0,0,width-1,heigh-1);
          String str = "ASDFGHJKLQWERTYUIOPZXCVBNMqwertyuiopasdfghjklzxcvbnm1234567890";
          //生成随机角标
          Random ran = new Random();
          for (int i = 1 ; i <= 4 ; i++){
              int index = ran.nextInt(str.length());
              //获取字符
              char ch = str.charAt(index);//随机字符
              //2.3写验证码
              g.drawString(ch +"",width/5*i,heigh/2);
          }
          //2.4画干扰线
          g.setColor(Color.GREEN);
          //随机生成坐标点
          for (int i = 0 ; i < 10; i++){
              int x1 = ran.nextInt(width);
              int x2 = ran.nextInt(width);
              int y1 = ran.nextInt(heigh);
              int y2 = ran.nextInt(heigh);
              g.drawLine(x1,y1,x2,y2);
          }        
          //3.将图片输出到页面展示
          ImageIO.write(image,"jpg",resp.getOutputStream());
      }    
      @Override
      protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
          this.doPost(req,resp);
      }
  }
  ```

# 3.ServletContext对象

1. 概念：代表整个web应用，可以和程序的容器（服务器）来通信

2. 获取：

   1. 通过request对象获取
      * request.getServletContext();
   2. 通过HttpServlet获取
      * this.getServletContext();

3. 功能：

   1. 获取MIME类型：

      * MIME类型：在互联网通信过程中第一的一种文件数据类型

        格式： 大类型/小类型      text/html   image/jpg

      * 获取：String getMimeType(String file)

   ### 例子：

   ```Java
   @WebServlet("/Text9")
   public class Text9_Servlet extends HttpServlet {
       @Override
       protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           //通过HttpServlet获取
           ServletContext servletContext = this.getServletContext();
           //定义文件名称
           String filename = "a.jpg";
           //获取MIME类型
           String mimeType = servletContext.getMimeType(filename);        
           System.out.println(mimeType);
       }    
       @Override
       protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
           this.doPost(req,resp);
       }
   }
   ```

   1. 域对象：共享数据

      1. setAttribute(String name,Object value)
      2. getAttribute(String name)
      3. removeAttribute(String name)
         * ServletContext对象范围：所有用户所有请求的数据

   2. 获取文件的真实（服务器）路径

      方法：String getRealPath(String path);

   


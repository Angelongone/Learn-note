# AJAX

### 概念：异步的JavaScript和xml

异步和同步：客户端和服务器端相互通信的基础上

* 同步：客户端必须等待服务器的响应。在等待的过程中不能做其它操作
* 异步：客户端不需要等待服务器端的响应。在服务器处理请求的过程中，客户端可以进行其它的操作。

### Jquery实现方式

1. $.ajax()

   * 语法：$.ajax({键值对})；

     使用$.ajax()发送异步请求

   `$.ajax({`

   ​    `url:"ajaxServlet",//请求路径`

   ​    `type:"POST",//请求方式`

   ​    `//data:"username=jack&age=23",`

   ​    `data:{"username":"jack","age":23},//请求参数`

   ​    `success:function(data){`

   ​      `alert(data);`

   ​    `},//响应成功后的回调函数`

   ​    `error:function(){`

   ​      `alert("出错了....");`

   ​    `},//表示如果请求响应出现错误，会执行的回调函数`

   ​    `dataType:"text"//设置接受到的响应数据的格式`

     `});`

2. $.get()：发送个体请求

   * 语法：$.get(url,[data],[callback],[type])
     * 参数：
       * url:请求路径
       * data:请求参数
       * callback:回调函数
       * type:响应结果的类型

3. $.post()：发送post请求

   `$.post("ajaxServlet",{username:"rose"},function(data){`

   ​    	`alert(data);`

     `},"text");`

# JSON

var p = {"name":"张三","age":23,"gender":"男"}

* ​	json现在多用于存储和交换文本信息的语法
* 进行数据的传输
* json比xml更小，更快，更容易解析

## 语法：

1. 基本规则
   * 数据在名称/值对中：json数据是由键值对构成的
     * 键用引号(单双都行)引起来，也可以不使用引号
     * 值的取值类型：
       1. 数字(整数或浮点数)
       2. 字符串(在双引号中)
       3. 逻辑值(true或false)
       4. 数组(在方括号中)
       5. 对象(在花括号中)
       6. null
   * 数据由逗号分隔：多个键值对由逗号分隔
   * 花括号保存对象：使用{}定义json格式
   * 方括号保存数组：[]
   
2. 获取数据
   1. json对象.键名
   
   2. json对象["键名"]
   
   3. 数组对象[索引]
   
      ``` 获取PS中所有值``` 
   
      ``` for (var i = 0; i < ps.length; i++){``` 
   
      ```var p = ps[i];```
   
      ```for(var key in p){```
   
      ​		```		alert(key + ":" + p[key]);```		
   
      ​	```}```
   
      ```}```
   
3. JSON数据和java对象的相互转换

   * JSON解析器：
     * 常见的解析器：Jsonlib,Gson,fastjson,jackson

   1. JSON转换为java对象

      1. 导入Jackson的相关jar包
      2. 创建Jackson核心对象ObjectMapper
      3. 调用ObjectMapper的相关方法进行转换
         1. readValue(json字符串数据，Class)

   2. java对象转换为JSON

      1. 使用步骤：

         1. 导入Jackson的相关jar包

         2. 创建Jackson核心对象 ObjectMapper

         3. 调用ObjectMapper的相关方法进行转换

            1. 转换方法：

               * writeValue(参数1，obj):

                 * 参数1：
                   * File:将obj对象转换为JSON字符串，并保存到指定的文件中
                   * Writer：将obj对象转换为JSON字符串，并将json数据填充到字符输出流中
                   * OutputStream:将obj对象转换为JSON字符串，并将json数据填充到字节输出流中

                 * writeValueAsSWtring(obj):将对象转为JSON字符串

            2. 注解：

               1. @JsonIgnore:排除属性
               2. @JsonFormat:属性值的格式化

            3. 复杂java对象转换

               1. List：数组
               2. Map：对象格式一致

   3. 校验用户名是否存在

      1. 服务器响应的数据，在客户端使用时，不想当做json数据格式使用
         1. $.get(type):将最后一个参数type指定为“json"
         2. 在服务器端设置MIME类型：
            * response.getContentType("application/json;charset=utf-8");


# 概念

jQuery是一个JavaScript框架。简化js开发

JavaScript框架：本质上就是一些js文件，封装了js的原生代码而已

# 使用

1. 导入jQuery的js文件：导入min.js文件
2. 使用：var div1 = ${"#div1"}  alert(div1.html());

# jQuery对象和js对象区别与转换

1. JQuery对象在操作是，更加方便。
2. JQuery对象和js对象方法不通用的。
3. 两者相互转换
   * jq --> js: jq对象[索引]或者 jq对象.get(索引)
   * js --> jq: $(js 对象)

# 选择器

1. 基本操作：

   1. 事件绑定

      * 绑定按钮

        $("#b1").click(function(){

        ​	alert("abc");

        });

   2. 入口函数

      $(function(){

      });

      * window.onload和$(function)区别
        * windo.onload 只能定义一次，如果定义多次，后边的会将前边的覆盖掉
        * $(function(){}) 可以定义多次

   3. 样式控制：css方法

      1. $("#div1").css("background-color","red");
      2. $("#div1").css("backgroundColor","pink");

2. 分类
   1. 基本选择器
      1. 标签选择器（元素选择器）
         * 语法：$("html标签名") 获取所有匹配标签名称的元素
      2. id选择器
         * 语法：$("#id的属性值") 获取与指定id属性值匹配的元素
      3. 类型选择器
         * 语法：$(".class的属性值") 获取有指定的class属性值匹配的元素
      4. 并集选择器
         * 语法：$("选择器1，选择器2....") 获取多个选择器选中的所有元素
   2. 层级选择器
      1. 后代选择器
         * 语法：$("A B") 选择A元素内的所有B元素
      2. 子选择器
         * 语法： $("A > B") 选择A元素内部的所有B元素
   3. 属性选择器
      1. 属性名称选择器
         * 语法：$("A[属性名]") 包含指定属性的选择器
      2. 属性选择器
         * 语法：$("A[属性名='值']") 包含指定属性等于指定值的选择器
      3. 符合属性选择器
         * 语法：$("A[属性名='值'] [ ] ") 包含多个属性条件的选择器
   4. 过滤选择器
      1. 首元素选择器
         * 语法：:first 获取选择的元素中的第一个元素
      2. 为元素选择器
         * 语法：:last 获取选择器的元素的最后一个元素
      3. 非元素选择器
         * 语法： ：not(selecter) 不包含指定内容的元素
      4. 偶数选择器
         * 语法：:even 偶数 从0开始计数
      5. 奇数选择器
         * 语法：:odd 奇数 从0开始计数
      6. 等于索引选择器
         * 语法：:eq(index) 指定索引元素
      7. 大于索引选择器
         * 语法：:gt(index) 大于指定索引元素
      8. 小于索引选择器
         * 语法：:lt(index) 小于指定索引元素
      9. 标题选择器
         * 语法：:header 获得标题元素，固定写法
   5. 表单过滤选择器
      1. 可用元素选择器
         * 语法：:enabled 获得可用元素
      2. 不可用元素选择器
         * 语法：:disabled 获得不可用元素
      3. 选中选择器
         * 语法：:checked 获得单选/复选框中的元素
      4. 选中选择器
         * 语法：:selected 获得下拉框中的元素

# DOM操作

1. 内容操作

   1. html(): 获取/设置元素的标签体内容  `<a><font>内容</font></a>` ---> ` <font>内容</font>`
   2. text(): 获取/设置元素的标签体纯文本内容  `<a><font>内容</font></a>`  --->  内容
   3. val(): 获取/设置元素的value属性值

2. 属性操作

   1. 通用属性操作

      1. attr(): 获取/设置元素的属性  attr("name")  attr("name","temp")
      2. removerAttr(): 删除属性 
      3. prop(): 获取/设置元素的属性
      4. removeProp(): 删除属性

      * attr和prop区别?
        1. 如果操作的是元素的固有属性，则建议使用prop
        2. 如果操作的是元素自定义的属性，建议使用attr

   2. 对class属性操作

      1. addclass(): 添加class属性值
      2. removerClass(): 删除class属性值
      3. toggleClass(): 切换class属性

3. CRUD操作

   1. append(): 父元素将子元素添加到末尾
      * 对象1.append(对象2): 将对象2添加到对象1元素内部，并且在末尾
   2. prepend(): 父元素将子元素追加到开头
      * 对象1.prepend(对象2)：将对象2添加到对象1元素的内部，并且在开头
   3. appendTo()：
      * 对象1.appendTo(对象2): 将对象1添加到对象2内部，并且在末尾
   4. prependTo()：
      * 对象1.prependTo(对象2)：将对象1添加到对象2内部，并且在开头
   5. after():添加元素到元素后边
      * 对象1.after(对象2)：将对象2添加到对象1后边。对象1和2是兄弟关系
   6. before(): 添加元素到元素前边
      * 对象1.before(对象2)：将对象2添加到对象1前边。对象1和对象2是兄弟关系
   7. insertAfter(): 
      * 对象1.insertAfter(对象2)：将对象2添加到对象1后边。对象1和对象2是兄弟关系
   8. insertBefore(): 
      * 对象1.insertBefore(对象2)：将对象2添加到对象1前边
   9. remove()：移除元素
      * 对象.remove(): 将对象删除掉
   10. empty(): 清空元素的所有后代元素。
       * 对象.empty(): 将对象的后代元素全部清空，但是保留当前队形以及其属性节点

# 案例

1. 表格背景色差：奇数黄，偶数粉红

   * <script>
         $(function(){
             $("tr:odd").css("backgroundColor","pink");
             $("tr:even").css("backgroundColor","yellow");
         });
     </script>

# jQuery高级

1. 动画

   1. 三种方式显示和隐藏元素

      * 参数：
        * speed：动画的速度。三个预定义值(show,mormal,fast)或表示动画时长的毫秒数值（如：100）
        * easing: 用来指定切换效果，默认是“swing”，可用参数“linear”
          * swing:动画执行时效果是 先慢，中间快，最后又慢
          * linear: 动画执行时匀速
        * fn: 在动画完成时执行的函数，每个元素执行一次

      1. 三种方式显示和隐藏元素
         1. show([speed,[easing],[fn]])
         2. hide([speed,[easing],[fn]])
         3. toggle([speed,[easing],[fn]])
      2. 滑动显示和隐藏方式
         1. slideDown([speed],[easing],[fn])
         2. slideUp([speed],[easing],[fn])
         3. slideToggle([speed],[easing],[fn])
      3. 淡入淡出显示和隐藏方式
         1. fadeIn([speed],[easing],[fn])
         2. fadeOut([speed],[easing],[fn])
         3. fadeToggle([speed,[easing],[fn]])

2. 遍历

   1. js的遍历方式

      * for(初始化值；循环结束条件；步长)

   2. jq的遍历方式

      1. jq对象.each(callback)

         1. 语法：
            * jQuery对象.each(function(index，element){});
              * index:就是元素在集合中的索引
              * element：就是集合中的每个元素对象
              * this：集合中的每一个元素对象
         2. 回调函数返回值：
            * true：如果当前function返回为false，则结束循环(break)
            * false：如果当前function返回为true，则结束本次循环，继续下一次循环(continue)

      2. $.each(object,[callback])

      3. for..of: jQuery3.0版本之后提供的方式

         * for(元素对象 of 容器对象）

         $(function(){

         ​    $("li").each(function(){

         ​      alert($(this).html());

         ​    });

           });

           $(function(){

         ​    $("li").each(function(index,element){

         ​      alert(index + " : " + element.innerHTML);

         ​    });

           })

1. 事件绑定
   1. jQuery标准的绑定方式
      * jq对象。事件方法(回调函数)；
   2. on绑定事件/off解除绑定
      * jq对象.on("事件名称"，回调函数)
      * jq对象.off("事件名称")
   3. 事件切换：toggle
      * jq对象.toggle(fn1,fn2...)
      * 点击第一次执行发f1,第二次点击执行fn2,循环
2. 插件: 插件jQuery的功能
   * 实现方式：
     1. $.fn.extend(object)
        * 增强通过jQuery获取的对象的功能 $("#id")
     2. $.extend(object)
        * 增强jQuery对象自身的功能 $/jquery


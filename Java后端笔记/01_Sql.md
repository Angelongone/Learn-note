# Sql分类
	1.DDL(Data Definition Language)数据定义语言：
		用来定义数据库对象：数据库，表，列等。关键字：create，drop，alter等
	2.DML(Data Manipulation Language)数据操作语言
		用来对数据库中表的数据进行增删改。关键字：insert,delete,update等
	3.DCL(Data Control Language)数据控制语言（了解）
		用来定义数据库的访问权限和安全级别，及创建用户。关键字：GRANT，REVOKE等
# DDL：操作数据库，表
	1.操作数据库：CRUD
		1）C(Creact):创建
		  创建数据库：
		  	create database 数据库名称；
		  创建数据库，判断不存在再创建
		  	create database if not exists 数据库名称；
		  创建数据库并指定字符集
		  	create database 数据库名称 character set 字符集名；
		  练习：创建ang数据库 判断是否存在 并指定字符集为utf8
		  	create database if not exists 数据库名称 character set utf8;
		2）R(Retrieve):查询
		  查询所有数据库的名称：
		  	 show databases;
		  查询某个数据库的字符集：查询某个数据库的创建语句
		  	show create database 数据库名称；
		3)U(Update):修改
		  修改数据库的字符集
		  	alter database 数据库名称 character set 字符集名称；
		4)D(Delete):删除
		  删除数据库
		   dorp database 数据库名称；
		  判断数据库是否存在，再删除
		  	dorp database is exists 数据库名称；
		 5）使用数据库
		  查询当前正在使用的数据库名称
		   select database();
		  使用数据库
		  	use 数据库名称 ；
	2.操作表
		1）C:创建
		  语法：
		  	create table 表名(
		  		列名1 数据类型1，
		  		列名2 数据类型2，
		  		...
		  		类名n 数据类型n
		  	);
		  	复制表：
		  		creact table 表名 like 被复制的表名;
		  数据库类型：
		  	1.int :整形类型
		  	  age int,
		  	 2.double:小数类型
		  	   score double(5,2)
		  	  3.date:日期类型，只包含年月日，yyyy-mm-dd
		  	  4.datetime:日期，包含年月日时分秒 yyyy-mm-dd HH:mm:ss
		  	  5.timestamp:时间错类型 包含年月日时分秒 yyyy-MM-dd HH:mm:ss
		  	  	如果将来不给这个字段赋值，或赋值为null,则默认使用当前的系统时间，来自动赋值
		  	  6.varchar:字符串
		  	  	name varchar(20):姓名最大20个字符
		2）查询
		  查询某个数据库中所有的名称
		  	show tables;
		  查询表结构
		  	desc 表名；
		3）修改
		  修改表名
		  	alter table 表名 rename to 新表名；
		  修改表的字符集
		  	alter table 表名 character set 字符集名；
		  添加一列
		    在尾部添加：alter table 表名 add 列名 数据类型；
		    在首位添加：alter table 表名 add 列名 数据类型 first；
		    在指定位置添加：alter table 表名 add 列名 数据类型 after 列名；
		  	
		  修改列名称 类型
		  	alter table 表名 change 列名 新列名 新数据类型；
		  	alter table 表名 modify 列名 新数据类型；
		  删除列
		  	alter table 表名 drop 列名；
		4)删除
		  drop table 表名；
		  drop table if exists 表名;
## DML:增删改表中数据
	1.添加数据：
	  语法：
	    insert into 表名(列名1，列名2....列名n) values(值1，值2....值n);
	   注意：
	    列名和值要一一对应
	    如果表名后，不定义列名，则默认给所有列添加值
	      insert into 表名 values(值1，值2....值n);
	    除了数字类型，其他类型需要使用引号引起来
	 2.删除数据：
	   语法：
	     delete from 表名 [where 条件]
	   注意：
	     如果不加条件，则删除表中所有记录
	     如果要删除所有记录
	       1）delete from 表名；--不推荐使用
	       2）truncate table 表名；--推荐使用
	  3.修改数据：
	    语法：
	      update 表名 set 列名1 = 值1，列名2 = 值2,... [where 条件];
	    注意：
	      如果不加任何条件，则会将表中所有记录全部修改
## DQL：查询表中的记录
select * from 表名；
#### DQL:查询语句
###### 基础查询
	1.语法：
		select 
			字段列表
		from
			表名列名
		where
			条件列名
		group by
			分组字段
		having
			分组之后的条件
		order by
			排序
		limit
			分页限定
			
	2.基础查询
	  1）多字段查询
	    select 字段名1，字段名2...from 表名；
	    注意：
	      如果查询所有字段，则可以使用*来代替字段列表。
	    2）去重复：
	      distinct
	        select distint address from student;
	    3）计算列
	      一般可以使用四则运算一些列的值。（一般只会进行数值型的计算）
	      ifnull（表达式1，表达式2）：null参与的运算，计算结果都为null
	        表达式1 ：那个字段需要判断是否为null
	        如果该字段为null后的替换值。
	          select name,math,english,math + ifnull(english,0) from student;
	      4)起别名：
	        as:as也可以省略
	          select name,math 数学,english 英语,math + ifnull(english,0) 总分 from student;
	 3.条件查询
	   >,<,<=,>=,=,!=,<>
	   between ... and
	     select * from student where age between 20 and 30;
	   in(集合)
	     select * from student where age in (20,21,22);
	   like:模糊查询
	     占位符：
	       _:单个任意字符
	       %：多个任意字符
	     查询姓马的有哪些：select * from student where naem like '马%';
	     查询姓名第二个是化的人：select * from student where name like "_化%"；
	     查询姓名是3个字的：select * from student where name like '___';
	     查询姓名中包含德的人：select * from student where name like '%德%'; 
	   is null
	     select * from student where english is null;
	   and 或 &&
	     select * from student where englis>60 and math>60;
	   or 或 ||
	     select * from student where age=20 or age=18;
	   not 或 !
	   	 select * from student where sex not "女";


	1.排序查询
	  语法：order by 子句；
	    order by 排序字段1 排序方式1，排序字段2 排序方式2.......
	  排序方式：
	    ASC:升序，默认
	    DESC：降序
	  注意：
	    如果有多个排序条件，则当前面的条件值一样时，才会判断第二条件。
	2.聚合函数：将一列数据作为一个整体，进行纵向的计算
	  1.count:计算个数
	    1)一般选择非空的列：主键
	    2）count(*)
	  2.max:计算最大值
	  3.min:计算最小值
	  4.sum:计算和
	  5.avg:计算平均值
	  注意：聚合函数的计算，排除null值
	  	解决方案：
	  	  1）选择不包含非空的列进行计算
	  	  2）IFNULL函数  
	3.分组查询：
	  1.语法：group by 分组字段；
	  2.注意：
	  	1）分组之后查询的字段：分组字段，聚合函数
	  	2）where 和 having 的区别
	  	  where 在分组之前进行限定，如果不满足条件，则不参与分组。having在分组之后进行限定，如果不满足结果，则不会被查询出来
	  	  where后不可以跟聚合函数，having可以进行聚合函数的判断
	  	例如：按照性别分组，分别查询男，女同学的平均分，人数
	  	  		select sex,avg(math),count(id) from seudent group by sex;
	  	  	 按照性别分组，分别查询男女同学的平均分，人数 要求：分数低于70分的人不参与分组
	  	  	 	select sex,avg(math),count(id) from student where math>70 group by sex;
	  	  	按照性别分组，分别查询男女同学的平均分，人数 要求：分数低于70分的人不参与分组，人数要大于2个人
	  	  	  select sex,avg(math),count(id) from students where math > 70 group by sex having count(id) > 2;或用别名代替 as   select sex,avg(math),count(id)  as con from students where math > 70 group by sex having con > 2;
	4.分页查询       
	  1.语法：limit开始的索引，每页查询的条数；
	  2.公式：开始的索引 = （当前的页码 - 1）* 每页显示的条数
	    每页显示3条记录
	      select * from student limit 0,3;  --第1页
	      select * from student limit 3,3;  --第2页
	      select * from student limit 6,3;  --第3页
	  3.limit 是一个MySql"方言"
## 约束
概念：对表中的数据进行限定，保证数据的正确性，有效性和完整性。
分类:

	1.主键约束：primary key
	  1)注意：
	    含义：非空且唯一
	    一张表只能有一个字段为主键
	    主键就是表中记录的唯一标识
	  2）创建表时添加主键约束：
	    create table stu(
	      id int primary key,
	      name varchar(20)
	    ); 
	  3)创建表后添加主键约束：
	    alter table stu modify id int primary key;
	   4)删除主键约束：（需要注意）
	     alter table stu drop primary key;
	2.非空约束：not null
	  1)创建表添加非空约束：
	  	create table stu(
	  	  id int;
	  	  name varchar(20) not null
	  	);
	  	2)创建完表后添加非空约束：
	  		alter table stu modify name varchar(20) not null;
	  	3)删除非空约束
	  	  alter table stu modify name varchar(20);
	3.唯一约束：unique
	  1)创建表时添加唯一约束：
	    create table stu(
	      id int,
	      phone_number varchar(20) unique
	    );
	   2)创建表后添加唯一约束：
	     alter table stu modify phone_number varchar(20) unique;
	   3)删除唯一约束：（需要注意）
	     alter table stu drop index phone_number;
	4,外键约束：foreign key,让表与表产生关系，从而保证数据的正确性。
	  1)在创建表时，可以添加外键：
	    语法：
	      create table 表名(
	        ...
	        外键列
	        constraint 外键名称 foreign key (外键列名) refernces 主表名称(主表列名称)
	      );
	    2)创建表之后，添加外键
	      alter table 表名 add constaint 外键名称 foreign key (外键字段名称) references 主表名称(主表列名称);
	    3)删除表之后，添加外键
	      alter table 表名 drop foreign key 外键名称；
	    4）级联操作
	      1>添加级联操作
	        语法：alter table 表名 add constraint 外键名称
	        		foreign key (外键字段名称) references 主表名称(主表列名称) on update cascade on delete cascade;
	        2>分类：
	          级联更新：on update cascade
	          级联删除：on delete cascade
	5.自动增长：
	  1.概念：如果某一列是数值类型的，使用auto_increment 可以完成值的自动增长；
	  2.创建表时添加自动增长：
	    create table stu(
	      id int primary key auto_increment,
	      name varchar(20)
	    );
	  3.创建表后添加自动增长：
	    alter table stu modify id int auto_increment;
	   4.删除自动增长：
	     alter table stu modify id int;
## 数据库的设计
	1.实现关系：
	  1）一对多（多对一）：
	    如：部门对员工
	    实现方式：在多的一方建立外键，指向一的一方的主键。
	  2）多对多：
	    如：学生和课程
	    实现方式：多对多关系实现需要借助第三张中间表，中间表至少包含两个字段，这两个字段作为第三张表的外键，分别指向两张表的主键
	  3）一对一（了解）：
	    如：身份证对人
	    实现方式：一对一关系实现，可以在任意一方添加唯一外键指向另一方的主键
	2.范式
	  1）分类：
	    第一范式（1NF）：每一列都是不可分割的原子数据项
	    第二范式（2NF）：在第一范式的基础上，非码属性必须完全依赖于码
	      几个概念：
	        1>函数依赖：A-->B 如果通过A属性（属性组）可以确定唯一B属性的值，则称B依赖于A
	          例如：学号-->姓名。 （学号，课程名称）--> 分数
	        2>完全函数依赖：A-->B,如果A是一个属性组，则B属性值的确定需要依赖于A属性组的所有的属性值。
	          如：学号，课程名称-->分数
	        3>部分函数依赖：A-->B ,如果A是一个属性组，则B属性值的确定只需要依赖于A属性组中某些值即可。
	          如：学号，课程 --> 姓名
	        4>传递函数依赖：A-->B ,B-->C,如果通过A属性（属性组）可以确定唯一B属性的值，在通过B属性（属性组）的值可以确定唯一C
	          如：学号-->系名，系名 --> 系主任
	        5>码：如果在一张表中，一个属性或属性组，被其他所有属性所完全依赖，则称这个属性（属性组）为该表的码
	          如：表中的码为：学号，课程名称
	            主属性：码属性组中的所有属性
	            非主属性：除过码属性组的属性
	      第三范式（3NF）：在第三范式基础上，任何非主属性不依赖于其他非主属性（在第二方式的基础上消除传递依赖） 
## 数据库的备份和还原
	命令行：
		语法：
		  备份：mysqldump -u 用户名 -p 密码 数据库的名称 > 保存的路径
		  还原：
		    1.登陆数据库
		    2.创建数据库
		    3.使用数据库
		    4.执行文件。source 文件路径
# 各个对象
## 1.DriverManager:驱动管理对象：
#### 功能：
	1.注册驱动：
		static void registerDriver(Driver driver):注册与给定的驱动程序 DriverManager.
		写代码使用：Class.forName("com.mysql.jdbc.Driver"):
	2.获取数据库连接
		方法：static Connection getConnection(String url,String user,String password)
		参数：
			url:指定连接的路径
				语法：jdbc:mysql://ip地址（域名）：端口号/数据库名称
				例子：jdbc:mysql://localhost:3306/english
				细节：如果连接的是本机MySQL服务器，并且MySQL服务器默认端口3306，则URL可以简写为：jdbc:mysql://数据库名称
			user:用户名
			password:密码
## 2.Connection:数据库连接对象
	1.功能：
		1）获取执行SQL的对象
			Statement createStatement()
			PreparedStatement prepareStatement(String sql)
		2)管理事务：
			开启事务：setAutoCommit(boolen autoCommit):调用该方法设置参数为false，即开启事务
			提交事务：commit()
			滚回事务：rollback()
## 3.Statement:执行SQL的对象
	1.执行SQL
		1）boolean excute(String sql):可以执行任意的SQL    了解
		2）int executeUpdate(String sql):执行DML（insert，update，delete）语句，DDL（create，alter，drop）语句
			*返回值：影响的行数可以通过这个影响的行数判断DML语句是否执行成功  返回值>0的则执行成功，反之 ，则失败
		3）ResultSet executeQuery(String sql):执行DQL（select）语句
## 4.ResultSet:结果集对象
* 结果集对象，封装查询结果
* next():游标向下移动一行
* getxxx(参数)：获取数据
		xxx:带表数据类型    如：int getInt(),String getString()
		参数：
			1）int ：代表列的编号，从1开始   如：getString(1)
			2) String:代表列名称。如：getInt("age")
* 注意：
	* 使用步骤：
			1）游标向下移动一行
			2）判断是否有数据
			3）获取数据
			 //循环获取所有数据
            while (res.next()){
                String word = res.getString("word");
                String name = res.getString("name");
                System.out.println(word + " | " + name);
            }
## 5.PreparedStatement:执行SQL的对象
	1.SQL注入问题：在拼接SQL时，有一些SQL的特殊关键字参与字符串的拼接。会造成安全性问题。
		1）输入用户随便，输入密码：a' or  'a' = 'a
		2）sql:select * from user where username = 'fhdsjkf' and password = 'a' or 'a' = 'a'
	2.解决sql注入问题：使用PreparedStatement对象类解决
	3.预编译的sql:参数使用？作为占位符
	4.步骤：
		1）导入驱动jar包  
		2）注册驱动
		3）获取数据库连接对象Connection
		4）定义sql:
			*注意：sql的参数使用？作为占位符。如：select * from user where username = ? ane password = ?
		5）获取执行sql语句的对象 preparedStatement Connection.PrepareStatemament(String sql)
		6）给？赋值：
			*方法：setXxx(参数1，参数2)
				*参数1：？的位置编号 从1 开始
				*参数2：？的值
				例子pre.setString(1,"Angelong");
		7）执行sql,接受返回结果，不需要传递sql语句
		8）处理结果
		9）释放资源
	5.注意：后期都会使用PreparedStatement来完成增删改查的所有操作
		1）可以防止SQL注入
		2）效率更高
## JDBC控制事务
	1.事务：一个包含多个步骤的业务操作。如果这个业务操作被事务管理，则这个步骤要么同时成功，要么同时失败
	2.操作：
		1）开启事务
		2）提交事务
		3）回滚事务
	3.使用Connection对象来管理事务
		*开启事务：setaAutoCommit(boolean autoCommit):调用该方法设置参数false，即开启事务
		*提交事务：commit()
		*回滚事务：rollback()


# 关于连接数据库时中文编码错误问题
### 原因：
1.编辑器的编码方式
2.数据库的编码方式
3.JDBC的编码方式
### 解决方法：
将所有的编码方式都设置位utf-8;
##### JDBC连接时更爱方式：
	String url="jdbc:mysql://localhost:3306/数据库名称?characterEncoding=utf-8
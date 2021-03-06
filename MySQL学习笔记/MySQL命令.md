# MySQL
## USE 数据库名 : mysql> use RUNOOB;
选择要操作的Mysql数据库，使用该命令后所有Mysql命令都只针对该数据库。

## 表查询
***
* SHOW DATABASES:  mysql> SHOW DATABASES;
> 列出 MySQL 数据库管理系统的数据库列表。

* SHOW TABLES:  mysql> use RUNOOB;
> 显示指定数据库的所有表，使用该命令前需要使用 use 命令来选择要操作的数据库。

* SHOW COLUMNS FROM 数据表:  mysql> SHOW COLUMNS FROM runoob_tbl;
> 显示数据表的属性，属性类型，主键信息 ，是否为 NULL，默认值等其他信息。

* SHOW INDEX FROM 数据表:  mysql> SHOW INDEX FROM runoob_tbl;
> 显示数据表的详细索引信息，包括PRIMARY KEY（主键）。

* SHOW TABLE STATUS LIKE [FROM db_name] [LIKE 'pattern'] \G:
> 
mysql> SHOW TABLE STATUS  FROM RUNOOB;   # 显示数据库 RUNOOB 中所有表的信息
mysql> SHOW TABLE STATUS from RUNOOB LIKE 'runoob%';     # 表名以runoob开头的表的信息
mysql> SHOW TABLE STATUS from RUNOOB LIKE 'runoob%'\G;   # 加上 \G，查询结果按列打印
该命令将输出Mysql数据库管理系统的性能及统计信息。

## 创建数据库
***
* CREATE DATABASE 数据库名;   mysql> create DATABASE RUNOOB;
> create 命令创建数据库

* 终端命令行直接创建数据库 ：
> [root@host]# mysqladmin -u root -p create RUNOOB

## 删除数据库
***
* drop database <数据库名>;   mysql> drop database RUNOOB;
> drop 命令删除数据库

* 终端命令行直接删除数据库 ：
> [root@host]# mysqladmin -u root -p drop RUNOOB

## 创建数据表
***
* 创建MySQL数据表需要以下信息：
> 表名
表字段名
定义每个表字段

* 创建MySQL数据表的SQL通用语法： CREATE TABLE table_name (column_name column_type);
> CREATE TABLE IF NOT EXISTS `runoob_tbl`(
   `runoob_id` INT UNSIGNED AUTO_INCREMENT,
   `runoob_title` VARCHAR(100) NOT NULL,
   `runoob_author` VARCHAR(40) NOT NULL,
   `submission_date` DATE,
   PRIMARY KEY ( `runoob_id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

* 实例解析：
          * 如果你不想字段为 NULL 可以设置字段的属性为 NOT NULL， 在操作数据库时如果输入该字段的数据为NULL ，就会报错。
          >
          1. AUTO_INCREMENT定义列为自增的属性，一般用于主键，数值会自动加1。
          2. PRIMARY KEY关键字用于定义列为主键。 您可以使用多列来定义主键，列间以逗号分隔。
          3. ENGINE 设置存储引擎，CHARSET 设置编码。

* ```mysql
mysql> CREATE TABLE pet (name VARCHAR(20), owner VARCHAR(20),    
           -> species VARCHAR(20), sex CHAR(1), birth DATE, death DATE); 
```

* 通过命令提示符创建表：
```mysql
mysql> use RUNOOB;
Database changed
mysql> CREATE TABLE runoob_tbl(
   -> runoob_id INT NOT NULL AUTO_INCREMENT,
   -> runoob_title VARCHAR(100) NOT NULL,
   -> runoob_author VARCHAR(40) NOT NULL,
   -> submission_date DATE,
   -> PRIMARY KEY ( runoob_id )
   -> )ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
Query OK, 0 rows affected (0.16 sec)
注意：MySQL命令终止符为分号 ; 
注意： -> 是换行符标识，不要复制

************* 删除数据表 *************************************************************8
删除MySQL数据表的通用语法：DROP TABLE table_name ;
mysql> use RUNOOB;
Database changed
mysql> DROP TABLE runoob_tbl
Query OK, 0 rows affected (0.8 sec)

*********** 插入数据 ********************************************************************
INSERT INTO SQL语法：
INSERT INTO table_name ( field1, field2,...fieldN )
                       VALUES
                       ( value1, value2,...valueN );
            
mysql> use RUNOOB;
Database changed
mysql> INSERT INTO runoob_tbl 
    -> (runoob_title, runoob_author, submission_date)
    -> VALUES
    -> ("学习 PHP", "菜鸟教程", NOW());
Query OK, 1 rows affected, 1 warnings (0.01 sec)
mysql> INSERT INTO runoob_tbl
    -> (runoob_title, runoob_author, submission_date)
    -> VALUES
    -> ("学习 MySQL", "菜鸟教程", NOW());
Query OK, 1 rows affected, 1 warnings (0.01 sec)
mysql> INSERT INTO runoob_tbl
    -> (runoob_title, runoob_author, submission_date)
    -> VALUES
    -> ("JAVA 教程", "RUNOOB.COM", '2016-05-06');
Query OK, 1 rows affected (0.00 sec)

读取数据表： select * from runoob_tbl;


要想将文本文件“pet.txt”装载到pet表中，使用这个命令： 
mysql> LOAD DATA LOCAL INFILE '/path/pet.txt' INTO TABLE pet; 

************ 查询 ****************************************************************8
查询数据 SELECT 语法：
SELECT column_name,column_name
FROM table_name
[WHERE Clause]
[LIMIT N][ OFFSET M]

SELECT 语句使用 WHERE 子句从数据表中读取数据的通用语法：
SELECT field1, field2,...fieldN FROM table_name1, table_name2...
[WHERE condition1 [AND [OR]] condition2.....

SQL SELECT WHERE 子句
SELECT * from runoob_tbl WHERE runoob_author='菜鸟教程';

查询区分大小写：
mysql> SELECT * from runoob_tbl WHERE BINARY（由他决定） runoob_author='runoob.com';
Empty set (0.01 sec)

name和birth列： 
mysql> SELECT name, birth FROM pet; 

找出谁拥有宠物，使用这个查询： 
mysql> SELECT owner FROM pet; 

************ 更新 *****************************************************************************
UPDATE 命令修改 MySQL 数据表数据的通用 SQL 语法：
UPDATE table_name SET field1=new-value1, field2=new-value2
[WHERE Clause]

mysql> UPDATE runoob_tbl SET runoob_title='学习 C++' WHERE runoob_id=3;

 编辑文件“pet.txt”改正错误，然后使用DELETE和LOAD DATA清空并重新装载表: ·                
 mysql> DELETE FROM pet; ·                
 mysql> LOAD DATA LOCAL INFILE 'pet.txt' INTO TABLE pet; 
 
 如果你想要验证你对Bowser的生日所做的更改，按下述方法选 择Bowser的记录： 
 mysql> SELECT * FROM pet WHERE name = 'Bowser'; 

************* 删除表中的数据 ******************************************************************
DELETE 语句从 MySQL 数据表中删除数据的通用语法：
DELETE FROM table_name [WHERE Clause]
mysql> DELETE FROM runoob_tbl WHERE runoob_id=3;

SELECT 语句使用 LIKE 子句从数据表中读取数据的通用语法：
SELECT field1, field2,...fieldN 
FROM table_name
WHERE field1 LIKE condition1 [AND [OR]] filed2 = 'somevalue'
mysql> SELECT * from runoob_tbl  WHERE runoob_author LIKE '%COM';

UNION 操作符语法格式：
SELECT expression1, expression2, ... expression_n
FROM tables
[WHERE conditions]
UNION [ALL | DISTINCT]
SELECT expression1, expression2, ... expression_n
FROM tables
[WHERE conditions];
参数
expression1, expression2, ... expression_n: 要检索的列。
tables: 要检索的数据表。
WHERE conditions: 可选， 检索条件。
DISTINCT: 可选，删除结果集中重复的数据。默认情况下 UNION 操作符已经删除了重复数据，所以 DISTINCT 修饰符对结果没啥影响。
ALL: 可选，返回所有结果集，包含重复数据。

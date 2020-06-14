# 外键的使用

> 两种方式增加外键
1. **创建的时候增加**
```mysql
create table student(
sid int primary key auto_increment,
sname varchar(20) not null,
cid int,
foreign key(cid) references class(cid));
```
2. **创建表之后**
```mysql
alter table 表名 add 外键名 foreign key 外键字段 references 父表(主键字段)
```
# 关联查询
## 内连查询
```mysql
select s.sname,c.cname from student s inner join class c on s.cid = c.cid;
```
## 左外连接查询
> 指以左边的表为数据基准，去匹配右边的表的数据，如果匹配到就显示，匹配不到就显示为null。
```mysqzrl
select s.sname,c.cname from student s left outer join class c on s.cid = c.cid;
```
## 右外连接
> 和左外连接相同,只是基准表的位置变化了而已
select s.sname,c.cname form student s right outer join class c on s.cid = c.id;


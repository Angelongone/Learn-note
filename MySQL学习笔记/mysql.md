#检索素据
## 检索单个列
* select prod_name from products;
* 从prod_name中检索products的列
## 检索多个列
* select prod_id,prod_name.prod_price from products;
* 检索三个素据列
## 检索所有列
* select * from products;
* 检索所有的列信息

## 检索不同的行
* select distinct vend_id from products;
* 查询vend_id列的素据(如果出现多次只查询出一条)

## 限制结果
* select prod_name from products limit 5;
* limit 5指示mysql返回不多于5行
* select prod_name from products limit 5,5;
* limit 5,5指示mysql返回从5行开始的5行

# 排序检索数据
## 排序数据
* select prod_name from products order by prod_name;
* 指示mysql对prod_name列以字母顺序排序数据的order by子句

## 按多个列排序
* select prod_id,prod-price,prod_name form products order by prod_price,prod_name;
* 先按照prod_price排序如果相同再按照prod_name排序

## 按照排序方向
* select prod_id,prod_price,prod_name from products order by prod_price desc;
* 按照价格降序排序

# 过滤数据
## 使用where子句
* select prod_name,prod_price from products where prod_price = 2.50;
* 从products表中检索两个列，但不返回所有行，只返回prod_price值为2.50的行
## 检查单个值
* select prod_name,prod_price from products where prod_name = 'fuses';
* 检查prod_name = 'fuses'的行
## 范围值检查
* select prod_name,prod_price from products where prod_price between 5 and 10;
* 使用between查询价格在5到10之间的所有值
## 空值检查
* select prod_name from products where prod_price is null;
# 数据过过滤器
## and操作符
* select prod_id,prod_price,prod_name from products where vend_id = 1003 and prod_price \<= 10;
* 查询供应商1003小于等于10的所有产品
## or操作符
* select prod_name,prod_price from products where vend_id = 1002 or vend_id = 1003
* 查询供应商为1002或者1003的数据
* and优先级高于or
## in操作符
* select prod_name,prod_price from vend_id in (1002,1003) order by prod_name;
* 查询供应商1002和1003制造的所有产品
* 实际作用和or相似，但是更加直观
## not操作符
* select prod_name,prod_price from products where vend_id not in (1002,1003) order by prod_name;
* 查询1002和1003外的供应商



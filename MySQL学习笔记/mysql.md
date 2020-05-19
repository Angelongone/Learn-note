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

## 


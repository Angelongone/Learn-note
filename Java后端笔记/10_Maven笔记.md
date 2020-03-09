# Maven

下载地址： http://archive.apache.org/dist/maven/maven-3/ 

## 环境变量配置：

MAVEN_HOME中配置 /bin文件所在的目录

path中配置：%MAVEN_HOME%\bin

 ## 仓库分三类：

1. 本地仓库
2. 远程仓库【私服】
3. 中央仓库

## 自定义本地仓库位置：

Maven默认设置在c盘中

1. 在Maven目录中有一个conf目录中有一个settings.xml文件中
2. 找到<localRepository>/path/to/local/repo</localRepository>
3. 复制出来并更改里面的路径

## Maven的组成：

1. 核心代码部分
2. 配置文件部分
3. 测试代码部分
4. 测试配置文件

## Maven项目标准目录结构：

src/main/java目录：核心代码部分。

src/main/resources：配置文件部分

src/test/java目录：测试代码部分

src/test/resources目录：测试配置文件。

src/main/webapp目录：页面资源，js,css,图片等等

## Maven命令：

1. mvn clean: 将编译好的信息直接删掉
2. mvn compile：编译命令，放置在target目录下
3. mvn test：测试编译 ，放置在target目录下
4. mvn package：打包命令 
5. mvn install：将文件编译打包，并将打包文件发送到本地仓库 






















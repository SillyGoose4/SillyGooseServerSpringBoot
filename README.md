# SillyGoose Server
服务端为 __Java Servlet__ 程序，采用 __SpringBoot + Mybatis3__ 框架，数据库为MySQL 5.7  
项目使用maven构建与解决依赖
>__框架简介__  
>
>Spring boot ：
>>Spring Boot makes it easy to create stand-alone, production-grade Spring based Applications >>that you can "just run".
>>We take an opinionated view of the Spring platform and third-party libraries so you can get >>started with minimum fuss. Most Spring Boot applications need very little Spring >>configuration.  
>>Detils to see http://spring.io/projects/spring-boot    

>Mybatis :
>>MyBatis is a first class persistence framework with support for custom SQL, stored procedures and advanced mappings. MyBatis eliminates almost all of the JDBC code and manual setting of parameters and retrieval of results. MyBatis can use simple XML or Annotations for configuration and map primitives, Map interfaces and Java POJOs (Plain Old Java Objects) to database records.  
>>Detils to see http://www.mybatis.org/mybatis-3/
### Project Structure

    src/main/java/com/sillygoose/Controller : 控制层，所有的http请求都会经过这个包里代码控制
        src/main/java/com/sillygoose/Mapper ：数据库操作接口
         src/main/java/com/sillygoose/Model ：模型层，与数据库字段一一对应
                 src/main/resources/Mapping : Mybatis 的数据库操作xml文件
                  src/main/resources/static :
               src/main/resources/templates : 放置跳转页面的模板，由于时http服务，很容易做网页
                           applications.yml : 配置文件，树形结构，数据库连接配置在这里更改
### Build
服务端我们已部署至阿里云，本机配置比较麻烦，请参照以下步骤
1. 需部署数据库至本地电脑，数据库为MySQL 5.7版本，  
    __请先下载[SillyGoose.sql](/SillyGoose.sql)文件__  
    创建数据库  

        create database SillyGoose;
        use SillyGoose;

    导入表

        source ${path}/SillyGoose.sql

2. 需更改  `application.yml` 文件里的ip为数据库ip,`user`为本地数据库用户，`password`为该用户的密码
3. 需更改客户端ip为本机ip（在`Client 项目里 app\src\main\java\com\SillyGoose\Utils\OkHttpUnits.java 文件里，将url改为本机即可`）
4. 运行服务端程序

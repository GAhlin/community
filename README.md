## GAVIN的博客系统

## 资料

- [Spring 文档](https://spring.io)
- [Spring Web文档](https://spring.io/guides/gs/serving-web-content/)
- [Spring MVC文档](https://docs.spring.io/spring/docs/5.0.3.RELEASE/spring-framework-reference/web.html#mvc-handlermapping-interceptor)
- [Spring Boot官方文档](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/html/)
- [Maven repository](https://mvnrepository.com/)
- [Mybatis官方文档](https://mybatis.org/mybatis-3/zh/index.html)
- [Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values)
- [菜鸟教程](https://www.runoob.com/mysql/mysql-insert-query.html)
- [Github第三方登录官方教程](https://developer.github.com/apps/)
- [BootStrap官方教程](https://v3.bootcss.com/components/)
- [阿里云OSS Java SDK教程](https://ak-console.aliyun.com/#/)
- [Alibaba Cloud Toolkit Idea一键部署插件](https://help.aliyun.com/product/29966.html)

## 第三方工具
- [Git](https://git-scm.com/download)
- [Lombok](https://www.projectlombok.org/)
- [Swagger UI](https://github.com/swagger-api/swagger-ui)
- [moment：JavaScript日期处理类库](http://momentjs.cn/)
- [Markdown插件](http://editor.md.ipandao.com/)
- [PageHelper分页插件](https://github.com/pagehelper/Mybatis-PageHelper)

##脚本
````
create table user
(
    ID           int auto_increment
        primary key,
    ACCOUNT_ID   varchar(100) null,
    NAME         varchar(50)  null,
    TOKEN        char(36)     null,
    GMT_CREATE   bigint       null,
    GMT_MODIFIED bigint       null,
    bio          varchar(256) null,
    avatar_url   varchar(100) null
);
````
````
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
````
##GAVIN的博客系统

##资料

- [Spring 文档](https://spring.io)
- [Spring Web](https://spring.io/guides/gs/serving-web-content/)
- [Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values)
- [菜鸟教程](https://www.runoob.com/mysql/mysql-insert-query.html)
- [Markdown插件](http://editor.md.ipandao.com/)
- [Spring MVC](https://docs.spring.io/spring/docs/5.0.3.RELEASE/spring-framework-reference/web.html#mvc-handlermapping-interceptor)
- [PageHelper分页插件](https://github.com/pagehelper/Mybatis-PageHelper)

##工具
- [Git](https://git-scm.com/download)
- [Lombok](https://www.projectlombok.org/)
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
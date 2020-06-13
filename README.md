##GAVIN的博客系统

##资料

- [Spring 文档](https://spring.io)
- [Spring Web](https://spring.io/guides/gs/serving-web-content/)
- [Thymeleaf](https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values)
- [菜鸟教程](https://www.runoob.com/mysql/mysql-insert-query.html)
- [Markdown插件](http://editor.md.ipandao.com/)
- [Spring MVC](https://docs.spring.io/spring/docs/5.0.3.RELEASE/spring-framework-reference/web.html#mvc-handlermapping-interceptor)

##工具
- [Git](https://git-scm.com/download)
- [Lombok](https://www.projectlombok.org/)
##脚本
````
CREATE TABLE USER
(
    ID int AUTO_INCREMENT PRIMARY KEY NOT NULL,
    ACCOUNT_ID VARCHAR(100),
    NAME VARCHAR(50),
    TOKEN VARCHAR(36),
    GMT_CREATE BIGINT,
    GMT_MODIFIED BIGINT
);
````
````
mvn flyway:migrate
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
````
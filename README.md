## 阅问社区

## 网站预览
[www.dangelion.club](http://www.dangelion.club)

## 功能列表  
开源论坛、问答系统，现有功能提问、回复、通知、最新、最热、消除零回复、热门标签等功能。功能持续更新中…… 

## 技术栈
|  技术   |  链接   |
| --- | --- |
|  Spring Boot   |  http://projects.spring.io/spring-boot/#quick-start   |
|   MyBatis  |  https://mybatis.org/mybatis-3/zh/index.html   |
|   MyBatis Generator  |  http://mybatis.org/generator/   |
|Lombok| https://www.projectlombok.org |
|Thymeleaf|https://www.thymeleaf.org/doc/tutorials/3.0/usingthymeleaf.html#setting-attribute-values|
|Bootstrap|https://v3.bootcss.com/getting-started/|
|Github OAuth|https://developer.github.com/apps/building-oauth-apps/creating-an-oauth-app/|
|阿里云OSS Java SDK教程|https://ak-console.aliyun.com/#/|
|Bootstrap|https://v3.bootcss.com/getting-started/|

## 第三方工具
- [Git](https://git-scm.com/download)
- [Swagger UI前端接口调试工具](https://github.com/swagger-api/swagger-ui)
- [moment：JavaScript日期处理类库](http://momentjs.cn/)
- [Markdown插件](http://editor.md.ipandao.com/)
- [PageHelper分页插件](https://github.com/pagehelper/Mybatis-PageHelper)
- [Alibaba Cloud Toolkit Idea一键部署插件](https://help.aliyun.com/product/29966.html)

## 脚本
````
mvn -Dmybatis.generator.overwrite=true mybatis-generator:generate
````

## 本地运行手册
1. 安装必备工具  
JDK，Maven
2. 克隆代码到本地
````sh
git clone https://github.com/GAhlin/community.git
````
3. 运行打包命令
````sh
mvn package
````
4. 运行项目
````sh
java -jar target/community-0.0.1-SNAPSHOT.jar
````
5. 访问项目
````sh
http://localhost:8887
````


## 扩展资料
- [Spring 文档](https://spring.io)
- [Spring Web文档](https://spring.io/guides/gs/serving-web-content/)
- [Spring MVC文档](https://docs.spring.io/spring/docs/5.0.3.RELEASE/spring-framework-reference/web.html#mvc-handlermapping-interceptor)
- [Spring Boot官方文档](https://docs.spring.io/spring-boot/docs/2.2.4.RELEASE/reference/html/)
- [Maven repository](https://mvnrepository.com/)
- [菜鸟教程](https://www.runoob.com/mysql/mysql-insert-query.html)
- [Postman](https://chrome.google.com/webstore/detail/coohjcphdfgbiolnekdpbcijmhambjff)

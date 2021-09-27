# 单体项目模板

### 目录详细介绍

```
  |_annotation: 放置项目自定义注解
  |_aspect: 放置切面代码
  |_config: 放置配置类
  |_constant
      |_consist: 放置常量定义
      |_enumration: 放置枚举定义
  |_controller: 放置控制器代码
  |_filter: 放置控制器、拦截器相关代码
  |_dao
  |_model
      |_entity: 放置数据库实体对象定义
      |_dto: 放置数据传输对象定义
      |_query: 数据查询对象，各层接收上层的查询请求
  |_service
      |_intf: 放置业务逻辑接口定义
      |_impl: 放置业务逻辑实际实现
  |_util: 放置工具类和辅助代码
```

```
  |_mapper: 放置mybatis的XML映射文件
  |_static: 放置网页静态资源
      |_css
      |_font
      |_img
      |_js
  |_template: 放置网页模板，例如thymeleaf/freemarker模板等
      |_bottom
      |_header
      |_sidebar
  |_application.yml: 基本配置文件
  |_application-dev.yml: 开发环境配置文件
  |_application-test.yml: 测试环境配置文件
  |_application-prod.yml: 生产环境配置文件
```

### 集成OpenFeign

```
1、 引入包
 <!-- ===================== 引入openfeign begin ===================== -->
        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-openfeign</artifactId>
            <version>3.0.3</version>
        </dependency>

        <!-- 不引用这个会报错-->
        <dependency>
            <groupId>io.github.openfeign</groupId>
            <artifactId>feign-gson</artifactId>
            <version>11.0</version>
        </dependency>

        <dependency>
            <groupId>org.springframework.cloud</groupId>
            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>
            <version>2.2.9.RELEASE</version>
        </dependency>

        <dependency>
            <groupId>io.github.openfeign</groupId>
            <artifactId>feign-httpclient</artifactId>
            <version>11.6</version>
        </dependency>
        <!-- ===================== 引入openfeign end ===================== -->
        
 2、加入yml配置   
feign:
  circuitbreaker:
    # 开启hystrix（feign.hystrix.enabled=true是旧版本的hystrix的配置，
    # 但是试过将spring-cloud-starter-netflix-hystrix的版本降到最低用旧版的配置还是不行）
    enabled: true
  httpclient:
    # 让feign使用Apache HTTPClient做请求，而不是默认的urlConnection
    enabled: true
    # Hystrix的超时时间设置为10秒
    connection-timeout: 10000
    # feign最大连接数
    max-connections: 200
    # feign单个路径的最大连接数
    max-connections-per-route: 50

logging:
  level:
    # 此包下所有class以DEBUG级别输出，打印feign请求信息时需要配置此项
    com.boxuegu.crm: debug  
    
boxuegu:
  url: ${BXG_URL:http://bxg-admin-open-api.boxuegu.com}
  
  3、启动类加上@EnableFeignClients
  
  4、主要代码说明
     com.boxuegu.crm.feign.BxgFeignApi                       配置第三方的请求
     com.boxuegu.crm.feign.fallback.BxgFeignFallbackFactory  熔断工厂类
     com.boxuegu.crm.feign.fallback.BxgFeignHystrix          降级处理类 
     com.boxuegu.crm.interceptor.BxgFeignInterceptor         请求头设置
     com.boxuegu.crm.config.OpenFeignLogConfig               打印请求体信息，最好使用的时候引入，不用就删除  
```
### 集成logback

```
1、特点
springboot默认集成logback，不用额外引入jar包
logback延用了log4j所有语法和规则，熟悉log4j的话学习logback更加轻松，不会也没关系

2、使用默认配置
如果没有特殊要求，可以不做任何配置，直接使用logback，默认级别是INFO，默认级别只会在控制台打印日志信息。

3、在application.yml中配置logback
  （1）、如果只是想配置日志级别和日志文件保存路径的话，直接在application.yml中配置，配置方法如下：
logging:
  level:
    root: info # 根日志，默认级别是info，如果使用info，可以不写这一行
    com.moyundong.dao: debug # 子级别日志，可以指定到自己的某个包
  file: d:/log/springboot-test04.log # 定义日志文件的路径，可以是相对路径也可以是绝对路径,不同版本的springboot配置名可能不同
  默认的日志文件能存储10MB内容，大于10MB就会生成一个压缩文件，然后重新记录，压缩文件有以前的日志信息。
  
  （2）、自定义logback-spring.xml文件，如果想自由定义日志级别、输出格式、备份日志策略等等，
  我们就在src/main/resource下面要创建logback-spring.xml文件进行自定义配置

4、日志级别
ALL > DEBUG > INFO > WARN > ERROR > OFF 级别越低，输出日志越多，最低是ALL，所有都输出。最高是OFF，啥都不输出。
ALL:所有都输出
DEBUG：输出DEBUG及后面所有日志
INFO：输出INFO及后面所有日志
WARN：输出WARN及后面所有日志
ERROR：只输出ERROR级别日志

5、日志分类
项目根日志：全局日志，一个项目只有一个根日志
项目子日志：包级别的日志，一个项目可以有多个子日志。
没有特别指明的地方都用的是根日志规则，有子日志的地方用的是子日志的规则
```
For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.4/maven-plugin/reference/html/#build-image)


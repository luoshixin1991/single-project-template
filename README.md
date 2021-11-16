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
  方式一
  （1）、如果只是想配置日志级别和日志文件保存路径的话，直接在application.yml中配置，配置方法如下：
logging:
  level:
    root: info # 根日志，默认级别是info，如果使用info，可以不写这一行
    com.moyundong.dao: debug # 子级别日志，可以指定到自己的某个包
  file: d:/log/springboot-test04.log # 定义日志文件的路径，可以是相对路径也可以是绝对路径,不同版本的springboot配置名可能不同
  默认的日志文件能存储10MB内容，大于10MB就会生成一个压缩文件，然后重新记录，压缩文件有以前的日志信息。
  
  方式二
   (1)、application.yml中配置如下内容
   logging:
     level:
       # 此包下所有class以DEBUG级别输出
       com.boxuegu.crm: debug
  （2）、自定义logback-spring.xml文件，如果想自由定义日志级别、输出格式、备份日志策略等等，
  我们就在src/main/resource下面要创建logback-spring.xml文件进行自定义配置，这里使用了
  <encoder class="net.logstash.logback.encoder.LogstashEncoder"/>来将日志以json的形式打印到文件，
  所以需要引入以下依赖，否则会报错java.lang.ClassNotFoundException: net.logstash.logback.encoder.LogstashEncoder
        <dependency>
            <groupId>net.logstash.logback</groupId>
            <artifactId>logstash-logback-encoder</artifactId>
            <version>6.6</version>
        </dependency>
        
  (3)、logback提供MDC功能(MDC 的全称是 Mapped Diagnostic Context，映射诊断上下文(MDC))，因此可以方便自定义设置调用链id，
  只需加入类com.boxuegu.crm.filter.ServiceTraceFilter即可，在发生web请求的时候，
  日志中会生成自定义的serviceTraceId，logback-spring.xml文件通过 %X{serviceTraceId} 获取filter中配置的变量, 
  <encoder class="net.logstash.logback.encoder.LogstashEncoder"/>也会将serviceTraceId记录到日志中。
  MDC在异步线程、线程池和dubbo请求中好像会失效，这里因为这样的使用场景少，就不做验证和解决，如需解决可以google
  
  MDC使用场景：MDC 类只包含静态方法。它允许开发人员在诊断上下文中放置信息，这些信息随后可由某些回签组件检索。MDC 按每个线程管理上下文信息。
  通常，在开始为新的客户机请求提供服务时，开发人员将插入相关的上下文信息，如客户机id、客户机的IP地址、请求参数等到 MDC 中。
  如果配置得当，登录组件将自动在每个日志条目中包含此信息


4、日志级别
ALL > DEBUG > INFO > WARN > ERROR > OFF 级别越低，输出日志越多，最低是ALL，所有都输出。最高是OFF，啥都不输出。
ALL:所有都输出
DEBUG：输出DEBUG及后面所有日志
INFO：输出INFO及后面所有日志
WARN：输出WARN及后面所有日志
ERROR：只输出ERROR级别日志

5、日志分类
项目根日志(<root>)：全局日志，一个项目只有一个根日志
项目子日志(<logger>)：包级别的日志，一个项目可以有多个子日志。
没有特别指明的地方都用的是根日志规则，有子日志的地方用的是子日志的规则
```

### 集成redis

```
一、在 springboot 1.5.x版本的默认的Redis客户端是 Jedis实现的，springboot 2.x版本中默认客户端是用 lettuce实现的。

Lettuce 与 Jedis 比较

1、Lettuce 和 Jedis 的都是连接 Redis Server的客户端。

2、Jedis 在实现上是直连 redis server，多线程环境下非线程安全，除非使用连接池，为每个 redis实例增加物理连接。
Lettuce 是 一种可伸缩，线程安全，完全非阻塞的Redis客户端，多个线程可以共享一个RedisConnection,它利用Netty NIO 框架来高效地管理多个连接，从而提供了异步和同步数据访问方式，用于构建非阻塞的反应性应用程序。

二、Lettuce 集成 Redis 服务

1、引入依赖

        <!-- ===================== 引入redis begin ===================== -->
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-data-redis</artifactId>
        </dependency>

        <dependency>
            <groupId>org.apache.commons</groupId>
            <artifactId>commons-pool2</artifactId>
        </dependency>
        <!-- ===================== 引入redis end ===================== -->

2、配置信息

spring:
  redis:
    ################ Redis 基础配置 begin ##############
    # Redis数据库索引（默认为0）
    database: 0
    # Redis服务器地址
    host: ${REDIS_HOST:redis-uat.boxuegu.com}
    # Redis服务器连接端口
    port: 6379
    # Redis服务器连接密码（默认为空）
    password: ${REDIS_PASSWORD:boxuegu}
    # 链接超时时间 单位 ms（毫秒）
    timeout: 10000
    ################ Redis 基础配置 end ##############
    ################ Redis 线程池设置 begin ##############
    lettuce:
      pool:
        # 连接池最大连接数（使用负值表示没有限制） 默认 8
        max-active: 8
        # 连接池最大阻塞等待时间（使用负值表示没有限制） 默认 -1
        max-wait: -1
        # 连接池中的最大空闲连接 默认 8
        max-idle: 8
        # 连接池中的最小空闲连接 默认 0
        min-idle: 0
    ################ Redis 线程池设置 end ##############

3、设置序列化器
com.boxuegu.crm.config.LettuceRedisConfig.redisTemplate

4、如果redis客户端使用JdkSerializationRedisSerializer序列化器，所有要需要序列化的类必须实现Serializable接口，
且最好显示声明serialVersionUID变量；如果使用的是Jackson2JsonRedisSerializer序列化器，则需要序列化的类无需实现Serializable接口。
RedisTemplate默认采用的是JDK的序列化策略，保存的key和value都是采用此策略序列化保存的。序列化类为：JdkSerializationRedisSerializer


5、引入RedisManager操作类com.boxuegu.crm.manager.RedisManager

三、使用springboot的缓存注解

1、配置CacheManager的序列化器和设置过期时间为1小时，org.springframework.cache.CacheManager

2、在启动类上加入@EnableCaching，启用缓存功能

3、使用示例com.boxuegu.crm.service.impl.TestCacheServiceImpl
```

### 集成mybatis plus

```
1、添加依赖
        <!-- ===================== 引入mybatis plus begin ===================== -->
        <dependency>
            <groupId>com.baomidou</groupId>
            <artifactId>mybatis-plus-boot-starter</artifactId>
            <version>3.4.3</version>
        </dependency>

        <dependency>
            <groupId>mysql</groupId>
            <artifactId>mysql-connector-java</artifactId>
            <version>8.0.26</version>
        </dependency>
        <!-- ===================== 引入mybatis plus end ===================== -->

2、数据库配置和连接池配置
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: ${SPRING_DATASOURCE_URL:jdbc:mysql://mysql-uat.boxuegu.com:3307/crm?useUnicode=true&zeroDateTimeBehavior=convertToNull&useAffectedRows=true}
    username: ${SPRING_DATASOURCE_USERNAME:crm}
    password: ${SPRING_DATASOURCE_PASSWORD:uatdjasiasfds}
    # 数据源连接池配置
    hikari:
      # 最大连接数
      maximum-pool-size: 20
      # 最小连接数
      minimum-idle: 5
      # 等待连接池分配连接的最大时长（毫秒），超过这个时长还没可用的连接则发生SQLException， 默认:30秒
      connection-timeout: 10000
      # 创建连接前默认执行的sql语句，如果语句执行失败连接则失败，然后重试连接，默认值无。
      connection-init-sql: "set names utf8mb4;"
      
mybatis-plus:
  global-config:
    db-config:
      # 全局逻辑删除的实体字段名
      logic-delete-field: deleted
      # 逻辑已删除值(默认为 1)
      logic-delete-value: 1
      # 逻辑未删除值(默认为 0)
      logic-not-delete-value: 0 

3、配置分页插件com.boxuegu.crm.config.MybatisPlusConfig.mybatisPlusInterceptor

4、启动类上加@MapperScan扫描所有的mapper类（或者在每个mapper类上加@Mapper注解，但是每个类都加很麻烦）
```

### 映射yml中属性值到bean中

``` 
@ConfigurationProperties(prefix=”xxxx”) 用來获取相同父标签的元素的值，
但是此标签不能将@ConfigurationProperties注解的类注入到ioc容器中,需要用以下方式的任意一种，才能注入到ioc容器中
1、使用@Configuration和@EnableConfigurationProperties注册
2、使用@Component注册
3、启动类上使用@ConfigurationPropertiesScan扫描注册（推荐）

注意：
@ConfigurationProperties注解类可能会有以下提示“spring boot configuration annotation processor notfound in classpath”，
可以引入以下依赖来消除，如果不加这个依赖也可以，不会有任何问题，只是会有这个提示
<dependency>
  <groupId>org.springframework.boot</groupId>
  <artifactId>spring-boot-configuration-processor</artifactId>
  <optional>true</optional>
</dependency>
```

### 打包需要添加的插件

``` 
            <!-- 打包的跳过test -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-surefire-plugin</artifactId>
                <version>3.0.0-M5</version>
                <configuration>
                    <skipTests>true</skipTests>
                </configuration>
            </plugin>

            <!-- 防止springboot自动压缩resource目录下的文件 -->
            <plugin>
                <groupId>org.apache.maven.plugins</groupId>
                <artifactId>maven-resources-plugin</artifactId>
                <version>3.2.0</version>
                <configuration>
                    <encoding>UTF-8</encoding>
                    <nonFilteredFileExtensions>
                        <nonFilteredFileExtension>xlsx</nonFilteredFileExtension>
                    </nonFilteredFileExtensions>
                </configuration>
            </plugin>
```

### Spring Boot Actuator 2.X 集成

``` 
1、添加依赖
        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-web</artifactId>
        </dependency>

        <dependency>
            <groupId>org.springframework.boot</groupId>
            <artifactId>spring-boot-starter-actuator</artifactId>
        </dependency>
        
2、添加配置
management:
  endpoints:
    web:
      exposure:
        # 打开所有的监控点, 可以选择打开部分management.endpoints.web.exposure.exclude=beans,trace
        include: "*"
  endpoint:
    health:
      show-details: always
  server:
    # 为了安全一般都启用独立的端口来访问后端的监控信息
    port: ${ACTUATOR_SERVER_PORT:8081}
    
3、使用
Actuator 默认所有的监控点路径都在/actuator/*, 例如：http://localhost:8081/actuator/health
Actuator 提供了 13 个接口，具体如下所示。

HTTP 方法路径描述

GET/auditevents显示应用暴露的审计事件 (比如认证进入、订单失败)
GET/beans描述应用程序上下文里全部的 Bean，以及它们的关系
GET/conditions就是 1.0 的 /autoconfig ，提供一份自动配置生效的条件情况，记录哪些自动配置条件通过了，哪些没通过
GET/configprops描述配置属性(包含默认值)如何注入Bean
GET/env获取全部环境属性
GET/env/{name}根据名称获取特定的环境属性值
GET/flyway提供一份 Flyway 数据库迁移信息
GET/liquidbase显示Liquibase 数据库迁移的纤细信息
GET/health报告应用程序的健康指标，这些值由 HealthIndicator 的实现类提供
GET/heapdump 一份应用的 JVM 堆信息
GET/httptrace显示HTTP足迹，最近100个HTTP request/repsponse
GET/info获取应用程序的定制信息，这些信息由info打头的属性提供
GET/logfile返回log file中的内容(如果 logging.file 或者 logging.path 被设置)
GET/loggers显示和修改配置的loggers
GET/metrics报告各种应用程序度量信息，比如内存用量和HTTP请求计数
GET/metrics/{name}报告指定名称的应用程序度量值
GET/scheduledtasks展示应用中的定时任务信息
GET/sessions如果我们使用了 Spring Session 展示应用中的 HTTP sessions 信息
POST/shutdown关闭应用程序，要求endpoints.shutdown.enabled设置为true
GET/mappings描述全部的 URI路径，以及它们和控制器(包含Actuator端点)的映射关系
GET/threaddump获取线程活动的快照
```

For further reference, please consider the following sections:

* [Official Apache Maven documentation](https://maven.apache.org/guides/index.html)
* [Spring Boot Maven Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.5.4/maven-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.5.4/maven-plugin/reference/html/#build-image)


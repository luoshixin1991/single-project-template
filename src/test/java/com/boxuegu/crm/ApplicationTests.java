package com.boxuegu.crm;

import com.boxuegu.crm.feign.BxgFeignApi;
import com.boxuegu.crm.manager.RedisManager;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import javax.annotation.Resource;

@Slf4j
@SpringBootTest
class ApplicationTests {
    @Resource
    private BxgFeignApi bxgFeignApi;
    @Resource
    private RedisManager redisManager;

    @Test
    void testFeignApi(){
        log.info("数据：{}", bxgFeignApi.findFirstOrderTransfer("7710af3936ad46b98497f10b9a3aa4bf"));
        log.warn("这是warn");
        log.debug("这是debug");
        log.error("这是error");
        log.trace("这是trace");
    }

    @Test
    void contextLoads() {
        String str = " |_annotation: 放置项目自定义注解\n" +
                "  |_aspect: 放置切面代码\n" +
                "  |_config: 放置配置类\n" +
                "  |_constant\n" +
                "      |_consist: 放置常量定义\n" +
                "      |_enumration: 放置枚举定义\n" +
                "  |_controller: 放置控制器代码\n" +
                "  |_filter: 放置控制器、拦截器相关代码\n" +
                "  |_dao\n" +
                "  |_model\n" +
                "      |_entity: 放置数据库实体对象定义\n" +
                "      |_dto: 放置数据传输对象定义\n" +
                "      |_query: 数据查询对象，各层接收上层的查询请求\n" +
                "  |_service\n" +
                "      |_intf: 放置业务逻辑接口定义\n" +
                "      |_impl: 放置业务逻辑实际实现\n" +
                "  |_util: 放置工具类和辅助代码\n" +
                "  |_mapper: 放置mybatis的XML映射文件\n" +
                "  |_static: 放置网页静态资源\n" +
                "      |_css\n" +
                "      |_font\n" +
                "      |_img\n" +
                "      |_js\n" +
                "  |_template: 放置网页模板，例如thymeleaf/freemarker模板等\n" +
                "      |_bottom\n" +
                "      |_header\n" +
                "      |_sidebar\n" +
                "  |_application.yml: 基本配置文件\n" +
                "  |_application-dev.yml: 开发环境配置文件\n" +
                "  |_application-test.yml: 测试环境配置文件\n" +
                "  |_application-prod.yml: 生产环境配置文件\n" +
                "集成OpenFeign\n" +
                "1、 引入包\n" +
                " <!-- ===================== 引入openfeign begin ===================== -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.cloud</groupId>\n" +
                "            <artifactId>spring-cloud-starter-openfeign</artifactId>\n" +
                "            <version>3.0.3</version>\n" +
                "        </dependency>\n" +
                "\n" +
                "        <!-- 不引用这个会报错-->\n" +
                "        <dependency>\n" +
                "            <groupId>io.github.openfeign</groupId>\n" +
                "            <artifactId>feign-gson</artifactId>\n" +
                "            <version>11.0</version>\n" +
                "        </dependency>\n" +
                "\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.cloud</groupId>\n" +
                "            <artifactId>spring-cloud-starter-netflix-hystrix</artifactId>\n" +
                "            <version>2.2.9.RELEASE</version>\n" +
                "        </dependency>\n" +
                "\n" +
                "        <dependency>\n" +
                "            <groupId>io.github.openfeign</groupId>\n" +
                "            <artifactId>feign-httpclient</artifactId>\n" +
                "            <version>11.6</version>\n" +
                "        </dependency>\n" +
                "        <!-- ===================== 引入openfeign end ===================== -->\n" +
                "        \n" +
                " 2、加入yml配置   \n" +
                "feign:\n" +
                "  circuitbreaker:\n" +
                "    # 开启hystrix（feign.hystrix.enabled=true是旧版本的hystrix的配置，\n" +
                "    # 但是试过将spring-cloud-starter-netflix-hystrix的版本降到最低用旧版的配置还是不行）\n" +
                "    enabled: true\n" +
                "  httpclient:\n" +
                "    # 让feign使用Apache HTTPClient做请求，而不是默认的urlConnection\n" +
                "    enabled: true\n" +
                "    # Hystrix的超时时间设置为10秒\n" +
                "    connection-timeout: 10000\n" +
                "    # feign最大连接数\n" +
                "    max-connections: 200\n" +
                "    # feign单个路径的最大连接数\n" +
                "    max-connections-per-route: 50\n" +
                "\n" +
                "logging:\n" +
                "  level:\n" +
                "    # 此包下所有class以DEBUG级别输出，打印feign请求信息时需要配置此项\n" +
                "    com.boxuegu.crm: debug  \n" +
                "    \n" +
                "boxuegu:\n" +
                "  url: ${BXG_URL:http://bxg-admin-open-api.boxuegu.com}\n" +
                "  \n" +
                "  3、启动类加上@EnableFeignClients\n" +
                "  \n" +
                "  4、主要代码说明\n" +
                "     com.boxuegu.crm.feign.BxgFeignApi                       配置第三方的请求\n" +
                "     com.boxuegu.crm.feign.fallback.BxgFeignFallbackFactory  熔断工厂类\n" +
                "     com.boxuegu.crm.feign.fallback.BxgFeignHystrix          降级处理类 \n" +
                "     com.boxuegu.crm.interceptor.BxgFeignInterceptor         请求头设置\n" +
                "     com.boxuegu.crm.config.OpenFeignLogConfig               打印请求体信息，最好使用的时候引入，不用就删除  \n" +
                "集成logback\n" +
                "1、特点\n" +
                "springboot默认集成logback，不用额外引入jar包\n" +
                "logback延用了log4j所有语法和规则，熟悉log4j的话学习logback更加轻松，不会也没关系\n" +
                "\n" +
                "2、使用默认配置\n" +
                "如果没有特殊要求，可以不做任何配置，直接使用logback，默认级别是INFO，默认级别只会在控制台打印日志信息。\n" +
                "\n" +
                "3、在application.yml中配置logback\n" +
                "  （1）、如果只是想配置日志级别和日志文件保存路径的话，直接在application.yml中配置，配置方法如下：\n" +
                "logging:\n" +
                "  level:\n" +
                "    root: info # 根日志，默认级别是info，如果使用info，可以不写这一行\n" +
                "    com.moyundong.dao: debug # 子级别日志，可以指定到自己的某个包\n" +
                "  file: d:/log/springboot-test04.log # 定义日志文件的路径，可以是相对路径也可以是绝对路径,不同版本的springboot配置名可能不同\n" +
                "  默认的日志文件能存储10MB内容，大于10MB就会生成一个压缩文件，然后重新记录，压缩文件有以前的日志信息。\n" +
                "  \n" +
                "  （2）、自定义logback-spring.xml文件，如果想自由定义日志级别、输出格式、备份日志策略等等，\n" +
                "  我们就在src/main/resource下面要创建logback-spring.xml文件进行自定义配置\n" +
                "\n" +
                "4、日志级别\n" +
                "ALL > DEBUG > INFO > WARN > ERROR > OFF 级别越低，输出日志越多，最低是ALL，所有都输出。最高是OFF，啥都不输出。\n" +
                "ALL:所有都输出\n" +
                "DEBUG：输出DEBUG及后面所有日志\n" +
                "INFO：输出INFO及后面所有日志\n" +
                "WARN：输出WARN及后面所有日志\n" +
                "ERROR：只输出ERROR级别日志\n" +
                "\n" +
                "5、日志分类\n" +
                "项目根日志(<root>)：全局日志，一个项目只有一个根日志\n" +
                "项目子日志(<logger>)：包级别的日志，一个项目可以有多个子日志。\n" +
                "没有特别指明的地方都用的是根日志规则，有子日志的地方用的是子日志的规则\n" +
                "集成redis\n" +
                "一、在 springboot 1.5.x版本的默认的Redis客户端是 Jedis实现的，springboot 2.x版本中默认客户端是用 lettuce实现的。\n" +
                "\n" +
                "Lettuce 与 Jedis 比较\n" +
                "\n" +
                "1、Lettuce 和 Jedis 的都是连接 Redis Server的客户端。\n" +
                "\n" +
                "2、Jedis 在实现上是直连 redis server，多线程环境下非线程安全，除非使用连接池，为每个 redis实例增加物理连接。\n" +
                "Lettuce 是 一种可伸缩，线程安全，完全非阻塞的Redis客户端，多个线程可以共享一个RedisConnection,它利用Netty NIO 框架来高效地管理多个连接，从而提供了异步和同步数据访问方式，用于构建非阻塞的反应性应用程序。\n" +
                "\n" +
                "二、Lettuce 集成 Redis 服务\n" +
                "\n" +
                "1、引入依赖\n" +
                "\n" +
                "        <!-- ===================== 引入redis begin ===================== -->\n" +
                "        <dependency>\n" +
                "            <groupId>org.springframework.boot</groupId>\n" +
                "            <artifactId>spring-boot-starter-data-redis</artifactId>\n" +
                "        </dependency>\n" +
                "\n" +
                "        <dependency>\n" +
                "            <groupId>org.apache.commons</groupId>\n" +
                "            <artifactId>commons-pool2</artifactId>\n" +
                "        </dependency>\n" +
                "        <!-- ===================== 引入redis end ===================== -->\n" +
                "\n" +
                "2、配置信息\n" +
                "\n" +
                "spring:\n" +
                "  redis:\n" +
                "    ################ Redis 基础配置 begin ##############\n" +
                "    # Redis数据库索引（默认为0）\n" +
                "    database: 0\n" +
                "    # Redis服务器地址\n" +
                "    host: ${REDIS_HOST:redis-uat.boxuegu.com}\n" +
                "    # Redis服务器连接端口\n" +
                "    port: 6379\n" +
                "    # Redis服务器连接密码（默认为空）\n" +
                "    password: ${REDIS_PASSWORD:boxuegu}\n" +
                "    # 链接超时时间 单位 ms（毫秒）\n" +
                "    timeout: 10000\n" +
                "    ################ Redis 基础配置 end ##############\n" +
                "    ################ Redis 线程池设置 begin ##############\n" +
                "    lettuce:\n" +
                "      pool:\n" +
                "        # 连接池最大连接数（使用负值表示没有限制） 默认 8\n" +
                "        max-active: 8\n" +
                "        # 连接池最大阻塞等待时间（使用负值表示没有限制） 默认 -1\n" +
                "        max-wait: -1\n" +
                "        # 连接池中的最大空闲连接 默认 8\n" +
                "        max-idle: 8\n" +
                "        # 连接池中的最小空闲连接 默认 0\n" +
                "        min-idle: 0\n" +
                "    ################ Redis 线程池设置 end ##############\n" +
                "\n" +
                "3、设置序列化器\n" +
                "com.boxuegu.crm.config.LettuceRedisConfig.redisTemplate\n" +
                "\n" +
                "4、如果redis客户端使用JdkSerializationRedisSerializer序列化器，所有要需要序列化的类必须实现Serializable接口，\n" +
                "且最好显示声明serialVersionUID变量；如果使用的是Jackson2JsonRedisSerializer序列化器，则需要序列化的类无需实现Serializable接口。\n" +
                "RedisTemplate默认采用的是JDK的序列化策略，保存的key和value都是采用此策略序列化保存的。序列化类为：JdkSerializationRedisSerializer\n" +
                "\n" +
                "\n" +
                "5、引入RedisManager操作类com.boxuegu.crm.manager.RedisManager\n" +
                "\n" +
                "三、使用springboot的缓存注解\n" +
                "\n" +
                "1、配置CacheManager的序列化器和设置过期时间为1小时，org.springframework.cache.CacheManager\n" +
                "\n" +
                "2、在启动类上加入@EnableCaching，启用缓存功能\n" +
                "\n" +
                "3、使用示例com.boxuegu.crm.service.impl.TestCacheServiceImpl";

        while (true) {

            log.info(str);
            log.warn(str);
            log.debug(str);
            log.error(str);
        }

    }
}

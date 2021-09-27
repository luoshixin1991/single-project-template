package com.boxuegu.crm;

import com.boxuegu.crm.feign.BxgFeignApi;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

@Slf4j
@SpringBootTest
class ApplicationTests {

    @Resource
    private BxgFeignApi bxgFeignApi;

    @Test
    void testFeignApi(){
        log.info("数据：{}", bxgFeignApi.findFirstOrderTransfer("7710af3936ad46b98497f10b9a3aa4bf"));
        log.warn("这是warn");
        log.debug("这是debug");
        log.error("这是error");
    }

    @Test
    void contextLoads() {

        while (true){
            log.info("<logger>标签的作用：为不同的包使用不同的log配置。\n" +
                    "如配置了 \n" +
                    "<logger name=\"com.runway\" additivity=\"false\"> \n" +
                    "        <priority value =\"info\"/>    \n" +
                    "        <appender-ref ref=\"activexAppender\" />    \n" +
                    "</logger> \n" +
                    "\n" +
                    "则com.runway包及其子包下的所有类使用的log配置都是info,且输出到activeXAppender.\n" +
                    "其他类使用的log配置都是根logger\n" +
                    "\n" +
                    "logger的默认配置具有继承特性， 即所有的logger配置继承根logger,\n" +
                    "name为“a.b.c\"的logger配置继承name为\"a.b\"的配置, name为\"a.b\"又继承name为\"a\"的logger而配置。。。\n" +
                    "若使用了additivity=\"false\"表示不继承父logger的配置。\n" +
                    "\n" +
                    "\n" +
                    "将logger中的 additivity 属性配置为 false，则这个logger不会将日志流反馈到root中。\n" +
                    "\n" +
                    "可以达到以logger中配置的appender方式来输出日志而其他地方输出的目的，看配置：\n" +
                    "1\n" +
                    "2\n" +
                    "3\n" +
                    "4\n" +
                    "5\n" +
                    "6\n" +
                    "7\n" +
                    "8\n" +
                    "9\n" +
                    "10\n" +
                    "11\n" +
                    "12\n" +
                    "13\n" +
                    "14\n" +
                    "15\n" +
                    "< appender  name = \"DEMO\"  class = \"com.XXXXX.RollingFileAppender\" >\n" +
                    "     < param  name = \"file\"  value = \"${loggingRoot}/xxx.log\"  />\n" +
                    "     < param  name = \"append\"  value = \"true\"  />\n" +
                    "     < param  name = \"encoding\"  value = \"GB2312\"  />\n" +
                    "     < param  name = \"threshold\"  value = \"info\"  />\n" +
                    "     < param  name = \"MaxFileSize\"  value = \"50MB\"  />\n" +
                    "     < param  name = \"MaxBackupIndex\"  value = \"10\"  />\n" +
                    "     < layout  class = \"org.apache.log4j.PatternLayout\" >\n" +
                    "         < param  name = \"ConversionPattern\"  value = \"%d [%X{requestURIWithQueryString}] %-5p %c{2} - %m%n\"  />\n" +
                    "     </ layout >\n" +
                    "</ appender >\n" +
                    "< logger搜索  name = \"XXXX.XXXX.XXXX\"  additivity = \"false\" >\n" +
                    "     < level  value = \"${loggingLevel}\"  />\n" +
                    "     < appender-ref  ref = \"DEMO\"  />\n" +
                    "</ logger >\n" +
                    "root的作用是收集下面所有反馈上来的信息流并根据配置在root中appender进行输出，只要你在looger中配置了additivity=\"false\"，就不会反馈到root中。\n" +
                    "\n" +
                    "\n" +
                    "\n" +
                    "log4j logger\n" +
                    "今天和两个同事讨论Log4j，他们都需要解决一个问题，怎么分开输出Logger。这么讲不清楚，举个例子：\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Java代码  复制代码  收藏代码\n" +
                    "package com.gmail.at.ankyhe.log4jtest;  \n" +
                    "  \n" +
                    "import org.apache.log4j.Logger;  \n" +
                    "  \n" +
                    "public class ClassA {  \n" +
                    "      \n" +
                    "    private static Logger logger = Logger.getLogger(ClassA.class.getName());  \n" +
                    "      \n" +
                    "    public ClassA() {  \n" +
                    "        logger.info(\"ENTER ClassA()\");  \n" +
                    "    }  \n" +
                    "      \n" +
                    "    public void foo() {  \n" +
                    "        logger.info(\"foo()\");  \n" +
                    "        bar();  \n" +
                    "    }  \n" +
                    "      \n" +
                    "    public void bar() {  \n" +
                    "        Logger myLog = Logger.getLogger(\"bar\");  \n" +
                    "        myLog.debug(\"bar()D\");  \n" +
                    "        myLog.info(\"bar()I\");  \n" +
                    "    }  \n" +
                    "  \n" +
                    "}  \n" +
                    "[java]  view plain  copy\n" +
                    "package com.gmail.at.ankyhe.log4jtest;  \n" +
                    "  \n" +
                    "import org.apache.log4j.Logger;  \n" +
                    "  \n" +
                    "public class ClassA {  \n" +
                    "      \n" +
                    "    private static Logger logger = Logger.getLogger(ClassA.class.getName());  \n" +
                    "      \n" +
                    "    public ClassA() {  \n" +
                    "        logger.info(\"ENTER ClassA()\");  \n" +
                    "    }  \n" +
                    "      \n" +
                    "    public void foo() {  \n" +
                    "        logger.info(\"foo()\");  \n" +
                    "        bar();  \n" +
                    "    }  \n" +
                    "      \n" +
                    "    public void bar() {  \n" +
                    "        Logger myLog = Logger.getLogger(\"bar\");  \n" +
                    "        myLog.debug(\"bar()D\");  \n" +
                    "        myLog.info(\"bar()I\");  \n" +
                    "    }  \n" +
                    "  \n" +
                    "}  \n" +
                    " 我希望bar可以输出到一个地方，其他的logger可以输出到一个地方。一般的配置文件如下：\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Xml代码  复制代码  收藏代码\n" +
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>  \n" +
                    "<!DOCTYPE log4j:configuration SYSTEM \"log4j.dtd\">  \n" +
                    "  \n" +
                    "<log4j:configuration xmlns:log4j='http://jakarta.apache.org/log4j/' >  \n" +
                    "  \n" +
                    "  <appender name=\"myConsole\" class=\"org.apache.log4j.ConsoleAppender\">  \n" +
                    "    <layout class=\"org.apache.log4j.PatternLayout\">  \n" +
                    "      <param name=\"ConversionPattern\"    \n" +
                    "        value=\"[%d{dd HH:mm:ss,SSS\\} %-5p] [%t] %c{2\\} - %m%n\" />  \n" +
                    "    </layout>  \n" +
                    "  </appender>  \n" +
                    "  \n" +
                    "  <appender name=\"myFile\" class=\"org.apache.log4j.RollingFileAppender\">    \n" +
                    "    <param name=\"File\" value=\"xml.log\" />  \n" +
                    "    <param name=\"Append\" value=\"false\" />  \n" +
                    "    <param name=\"MaxBackupIndex\" value=\"10\" />  \n" +
                    "    <layout class=\"org.apache.log4j.PatternLayout\">  \n" +
                    "      <param name=\"ConversionPattern\" value=\"%p (%c:%L)- %m%n\" />  \n" +
                    "    </layout>  \n" +
                    "  </appender>  \n" +
                    "  \n" +
                    "  \n" +
                    "  <logger name=\"bar\">  \n" +
                    "    <level value=\"info\" />   \n" +
                    "    <appender-ref ref=\"myConsole\" />   \n" +
                    "  </logger>  \n" +
                    "    \n" +
                    "  <root>  \n" +
                    "    <priority value=\"debug\" />  \n" +
                    "    <appender-ref ref=\"myFile\" />  \n" +
                    "  </root>  \n" +
                    "</log4j:configuration>  \n" +
                    "[xml]  view plain  copy\n" +
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>  \n" +
                    "<!DOCTYPE log4j:configuration SYSTEM \"log4j.dtd\">  \n" +
                    "  \n" +
                    "<log4j:configuration xmlns:log4j='http://jakarta.apache.org/log4j/' >  \n" +
                    "  \n" +
                    "  <appender name=\"myConsole\" class=\"org.apache.log4j.ConsoleAppender\">  \n" +
                    "    <layout class=\"org.apache.log4j.PatternLayout\">  \n" +
                    "      <param name=\"ConversionPattern\"    \n" +
                    "        value=\"[%d{dd HH:mm:ss,SSS\\} %-5p] [%t] %c{2\\} - %m%n\" />  \n" +
                    "    </layout>  \n" +
                    "  </appender>  \n" +
                    "  \n" +
                    "  <appender name=\"myFile\" class=\"org.apache.log4j.RollingFileAppender\">    \n" +
                    "    <param name=\"File\" value=\"xml.log\" />  \n" +
                    "    <param name=\"Append\" value=\"false\" />  \n" +
                    "    <param name=\"MaxBackupIndex\" value=\"10\" />  \n" +
                    "    <layout class=\"org.apache.log4j.PatternLayout\">  \n" +
                    "      <param name=\"ConversionPattern\" value=\"%p (%c:%L)- %m%n\" />  \n" +
                    "    </layout>  \n" +
                    "  </appender>  \n" +
                    "  \n" +
                    "  \n" +
                    "  <logger name=\"bar\">  \n" +
                    "    <level value=\"info\" />   \n" +
                    "    <appender-ref ref=\"myConsole\" />   \n" +
                    "  </logger>  \n" +
                    "    \n" +
                    "  <root>  \n" +
                    "    <priority value=\"debug\" />  \n" +
                    "    <appender-ref ref=\"myFile\" />  \n" +
                    "  </root>  \n" +
                    "</log4j:configuration>  \n" +
                    " 这样会发现一个问题，myLog的输出又会在文件，又会在终端。原因在Log4J官方的Introduction讲的很清楚：\n" +
                    "\n" +
                    "http://logging.apache.org/log4j/1.2/manual.html  搜索Appender Additivity. 我解释一下就是默认情况下bar会集成root的输出。知道了原因，那怎么修改就简单了。\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Xml代码  复制代码  收藏代码\n" +
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>  \n" +
                    "<!DOCTYPE log4j:configuration SYSTEM \"log4j.dtd\">  \n" +
                    "  \n" +
                    "<log4j:configuration xmlns:log4j='http://jakarta.apache.org/log4j/' >  \n" +
                    "  \n" +
                    "  <appender name=\"myConsole\" class=\"org.apache.log4j.ConsoleAppender\">  \n" +
                    "    <layout class=\"org.apache.log4j.PatternLayout\">  \n" +
                    "      <param name=\"ConversionPattern\"    \n" +
                    "        value=\"[%d{dd HH:mm:ss,SSS\\} %-5p] [%t] %c{2\\} - %m%n\" />  \n" +
                    "    </layout>  \n" +
                    "  </appender>  \n" +
                    "  \n" +
                    "  <appender name=\"myFile\" class=\"org.apache.log4j.RollingFileAppender\">    \n" +
                    "    <param name=\"File\" value=\"xml.log\" />  \n" +
                    "    <param name=\"Append\" value=\"false\" />  \n" +
                    "    <param name=\"MaxBackupIndex\" value=\"10\" />  \n" +
                    "    <layout class=\"org.apache.log4j.PatternLayout\">  \n" +
                    "      <param name=\"ConversionPattern\" value=\"%p (%c:%L)- %m%n\" />  \n" +
                    "    </layout>  \n" +
                    "  </appender>  \n" +
                    "  \n" +
                    "  \n" +
                    "  <logger name=\"bar\" additivity=\"false\">  \n" +
                    "    <level value=\"info\" />   \n" +
                    "    <appender-ref ref=\"myConsole\" />   \n" +
                    "  </logger>  \n" +
                    "    \n" +
                    "  <root>  \n" +
                    "    <priority value=\"debug\" />  \n" +
                    "    <appender-ref ref=\"myFile\" />  \n" +
                    "  </root>  \n" +
                    "</log4j:configuration>  \n" +
                    "[xml]  view plain  copy\n" +
                    "<?xml version=\"1.0\" encoding=\"UTF-8\"?>  \n" +
                    "<!DOCTYPE log4j:configuration SYSTEM \"log4j.dtd\">  \n" +
                    "  \n" +
                    "<log4j:configuration xmlns:log4j='http://jakarta.apache.org/log4j/' >  \n" +
                    "  \n" +
                    "  <appender name=\"myConsole\" class=\"org.apache.log4j.ConsoleAppender\">  \n" +
                    "    <layout class=\"org.apache.log4j.PatternLayout\">  \n" +
                    "      <param name=\"ConversionPattern\"    \n" +
                    "        value=\"[%d{dd HH:mm:ss,SSS\\} %-5p] [%t] %c{2\\} - %m%n\" />  \n" +
                    "    </layout>  \n" +
                    "  </appender>  \n" +
                    "  \n" +
                    "  <appender name=\"myFile\" class=\"org.apache.log4j.RollingFileAppender\">    \n" +
                    "    <param name=\"File\" value=\"xml.log\" />  \n" +
                    "    <param name=\"Append\" value=\"false\" />  \n" +
                    "    <param name=\"MaxBackupIndex\" value=\"10\" />  \n" +
                    "    <layout class=\"org.apache.log4j.PatternLayout\">  \n" +
                    "      <param name=\"ConversionPattern\" value=\"%p (%c:%L)- %m%n\" />  \n" +
                    "    </layout>  \n" +
                    "  </appender>  \n" +
                    "  \n" +
                    "  \n" +
                    "  <logger name=\"bar\" additivity=\"false\">  \n" +
                    "    <level value=\"info\" />   \n" +
                    "    <appender-ref ref=\"myConsole\" />   \n" +
                    "  </logger>  \n" +
                    "    \n" +
                    "  <root>  \n" +
                    "    <priority value=\"debug\" />  \n" +
                    "    <appender-ref ref=\"myFile\" />  \n" +
                    "  </root>  \n" +
                    "</log4j:configuration>  \n" +
                    " 唯一的区别是在bar那个logger后面加了一个 additivity=\"false\"。我试了一下，可以按照要求工作。这是xml的配置，一般的property的配置如下：\n" +
                    "\n" +
                    " \n" +
                    "\n" +
                    "Xml代码  复制代码  收藏代码\n" +
                    "log4j.rootLogger=DEBUG, FA  \n" +
                    "log4j.category.bar = INFO, CA  \n" +
                    "log4j.additivity.bar = false  \n" +
                    "  \n" +
                    "#Console Appender  \n" +
                    "  \n" +
                    "log4j.appender.CA=org.apache.log4j.ConsoleAppender  \n" +
                    "log4j.appender.CA.layout=org.apache.log4j.PatternLayout  \n" +
                    "log4j.appender.CA.layout.ConversionPattern=%-4r [%t] %-5p %c %x - %m%n  \n" +
                    "  \n" +
                    "#File Appender  \n" +
                    "log4j.appender.FA=org.apache.log4j.FileAppender  \n" +
                    "log4j.appender.FA.File=property.log  \n" +
                    "log4j.appender.FA.layout=org.apache.log4j.PatternLayout  \n" +
                    "log4j.appender.FA.layout.ConversionPattern=%-4r [%t] %-5p %c %x - %m%n  \n" +
                    "  \n" +
                    "             \n" +
                    "[xml]  view plain  copy\n" +
                    "log4j.rootLogger=DEBUG, FA  \n" +
                    "log4j.category.bar = INFO, CA  \n" +
                    "log4j.additivity.bar = false  \n" +
                    "  \n" +
                    "#Console Appender  \n" +
                    "  \n" +
                    "log4j.appender.CA=org.apache.log4j.ConsoleAppender  \n" +
                    "log4j.appender.CA.layout=org.apache.log4j.PatternLayout  \n" +
                    "log4j.appender.CA.layout.ConversionPattern=%-4r [%t] %-5p %c %x - %m%n  \n" +
                    "  \n" +
                    "#File Appender  \n" +
                    "log4j.appender.FA=org.apache.log4j.FileAppender  \n" +
                    "log4j.appender.FA.File=property.log  \n" +
                    "log4j.appender.FA.layout=org.apache.log4j.PatternLayout  \n" +
                    "log4j.appender.FA.layout.ConversionPattern=%-4r [%t] %-5p %c %x - %m%n  \n" +
                    "  \n" +
                    "\n" +
                    "大鑫不列迭\n" +
                    "关注\n" +
                    "\n" +
                    "2\n" +
                    "\n" +
                    "0\n" +
                    "\n" +
                    "6\n" +
                    "一键三连\n" +
                    "\n" +
                    "专栏目录\n" +
                    "linux下利用/proc进行进程树的打印\n" +
                    "02-02\n" +
                    "在linux下利用c语言实现的进程树的打印，主要通过/proc下的目录中的进程文件，获取status中的进程信息内容，然后利用递归实现进程树的打印\n" +
                    "如何借助log4j把日志写入数据库中\n" +
                    "10-15\n" +
                    "本JavaWeb工程主要演示如何借助log4j把日志写入数据库中，工程中的代码均有详细的注释说明，相信您通过该例子一定能掌握如何借助log4j把日志写入数据库中，欢迎下载。\n" +
                    "\n" +
                    "\n" +
                    "优质评论可以帮助作者获得更高权重\n" +
                    "表情包\n" +
                    "Log4j rootLogger配置_飞上云端看彩虹_log4j root标签\n" +
                    "7-16\n" +
                    "Log4j rootLogger配置 Log4j 根配置语法 指代 把指定级别的日志信息输出到指定的一个或者多个位置 这里我们把INFO层级以及以上的信息输出到Console和File;\n" +
                    "log4j root 和 category (logger)关系_ygd266的专栏\n" +
                    "9-19\n" +
                    "</root> </log4j:configuration> publicStringindex(Model model){ logger.info(\"日志测试\"); returnnull; } 测试结果: 1、不写root标签,只写category标签 输出结果:测试日志 2、写root标签和category,category的属性additivity=\"false...\n" +
                    "使用mybatis-plus报错Invalid bound statement (not found)错误\n" +
                    "09-07\n" +
                    "主要介绍了使用mybatis-plus报错Invalid bound statement (not found)错误，文中通过示例代码介绍的非常详细，对大家的学习或者工作具有一定的参考学习价值，需要的朋友们下面随着小编来一起学习学习吧");
        }

    }
}

package com.boxuegu.crm.property;

import com.boxuegu.crm.model.dto.RocketMqMetaDTO;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * RocketMq属性
 * @ConfigurationProperties(prefix=”xxxx”) 用來获取相同父标签的元素的值，但是此标签不能将RocketMqProperties注入到ioc容器中,需要用
 * 以下方式的任意一种，才能注入到ioc容器中
 * 1、使用@Configuration和@EnableConfigurationProperties注册
 * 2、使用@Component注册
 * 3、在启动类上使用@ConfigurationPropertiesScan扫描注册（推荐）
 *
 *
 *
 * @author lsx
 * @date 2021/10/6 16:55
 */
@Data
@ConfigurationProperties(prefix = "rocket.meta")
public class RocketMqProperties {
    /**
     * 线索消息队列元数据
     */
    private RocketMqMetaDTO clue;
    /**
     * 学员消息队列元数据
     */
    private RocketMqMetaDTO student;
}

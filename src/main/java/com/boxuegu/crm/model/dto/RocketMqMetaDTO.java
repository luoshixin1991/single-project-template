package com.boxuegu.crm.model.dto;

import lombok.Data;

/**
 * RocketMq元数据
 *
 * @author lsx
 * @date 2021/10/6 17:01
 */
@Data
public class RocketMqMetaDTO {
    /**
     * topic
     */
    private String topic;
    /**
     * 生产者
     */
    private String producer;
    /**
     * 消费组
     */
    private String consumer;
    /**
     * 此变量仅用来展示yml中special-note会被映射到specialNote的变量中
     */
    private String specialNote;
}

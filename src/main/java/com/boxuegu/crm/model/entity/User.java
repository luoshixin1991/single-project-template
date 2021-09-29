package com.boxuegu.crm.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * user实体类
 *
 * @author lsx
 * @date 2021/9/28 18:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User implements Serializable {
    private static final long serialVersionUID = 9137203164655384103L;
    /**
     * 用户id
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
}

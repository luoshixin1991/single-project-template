package com.boxuegu.crm.model.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * user实体类
 * @Data相当于@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode这5个注解的合集
 *
 * @author lsx
 * @date 2021/9/28 18:00
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {
    /**
     * 用户id
     */
    private Long id;
    /**
     * 姓名
     */
    private String name;
}

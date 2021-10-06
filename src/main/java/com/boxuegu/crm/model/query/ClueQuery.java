package com.boxuegu.crm.model.query;

import com.boxuegu.crm.common.bean.PageQuery;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 线索分页查询
 * @Data相当于@Getter @Setter @RequiredArgsConstructor @ToString @EqualsAndHashCode这5个注解的合集
 *
 * @author lsx
 * @date 2021/10/6 13:41
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class ClueQuery extends PageQuery {
    /**
     * 线索归属人id
     */
    private Integer belongerId;
}

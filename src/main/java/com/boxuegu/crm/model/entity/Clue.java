package com.boxuegu.crm.model.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * 线索
 *
 * @author lsx
 * @since 2021-09-30
 */
@Data
@TableName("t_clue")
public class Clue implements Serializable {
    private static final long serialVersionUID = -2429575091044727243L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 客户ID
     */
    private Long customerId;

    /**
     * 归属人ID(咨询师ID)
     */
    private Integer belongerId;

    /**
     * 分配人ID(系统或者上级等)
     */
    private Long assignerId;

    /**
     * 转移人，从哪转移来
     */
    private Integer fromId;

    /**
     * 归属时间
     */
    private Long belonged;

    /**
     * 线索创建状态：1.无手机号 2.无效 3.有效(新量) 4.有效(老量)
     */
    private Boolean createStatus;

    /**
     * 线索跟进状态：1.未跟进 2.跟进中 3.付部分款 4.交齐学费 5.成功关单 6.已关闭 7.已退费 8.已报名
     */
    private Integer followStatus;

    /**
     * 意向等级(A.B.C.D.)
     */
    private String intentLevel;

    /**
     * 意向课程ID
     */
    private Integer intentCourseId;

    /**
     * 意向课程名字
     */
    private String intentCourseName;

    /**
     * 下次跟进时间
     */
    private Long nextFollowed;

    /**
     * 当前跟进ID
     */
    private Long currFollowId;

    /**
     * 最新跟进时间
     */
    private Long currFollowed;

    /**
     * 线索来源ID
     */
    private Long clueSourceId;

    /**
     * 操作人
     */
    private String operator;

    /**
     * 创建描述(例子:无效原因等)
     */
    private String createDesc;

    /**
     * 创建方式 1.员工-咨询师 2.员工-非咨询师 3.表单 4.网咨 5 EMS迁移 6 短讯转移
     */
    private Integer createType;

    /**
     * 订单ID
     */
    private String orderId;

    /**
     * 创建人id
     */
    private Integer creatorId;

    /**
     * 报名时间
     */
    private Long signedUpAt;

    /**
     * 报名课程id
     */
    private Integer enrolmentCourseId;

    /**
     * 支付金额
     */
    private Integer paidAmount;

    /**
     * 退费金额
     */
    private Integer refundAmount;

    /**
     * 创建时间
     */
    private Long created;

    /**
     * 更新时间
     */
    private Long updated;

    /**
     * 是否删除
     */
    private Boolean deleted;

    private LocalDateTime updatedAt;

    /**
     * 通信方式:1手机号 2微信 3qq
     */
    private Boolean contactType;

    /**
     * 报名费金额, 单位分
     */
    private Integer depositAmount;

    /**
     * 报名费id
     */
    private String depositId;

    /**
     * 订单实际应付总金额=原价-优惠总额-冲抵金额, 单位分
     */
    private Integer payableAmount;

    /**
     * 报名费退费金额, 单位分
     */
    private Integer depositRefundAmount;

    /**
     * 首次意向学科ID
     */
    private Integer firstIntentSubjectId;

    /**
     * 首次意向学科名字
     */
    private String firstIntentSubjectName;

    /**
     * 当前意向学科id
     */
    private Integer intentSubjectId;

    /**
     * 当前意向学科名称
     */
    private String intentSubjectName;

    /**
     * 线索类型(0 博学谷线索  1 双持线索 2 分享双持)
     */
    private Integer clueType;

    /**
     * 线索是否重复(0 否 1 是)
     */
    private Boolean repeated;

    /**
     * 线下归属人
     */
    private String offlineBelonger;

    /**
     * 公海领取人的ID(咨询师ID)
     */
    private Integer accepterId;
}

package com.boxuegu.crm.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boxuegu.crm.model.dto.CustomerClueDTO;
import com.boxuegu.crm.model.entity.Clue;

/**
 * 线索 Mapper 接口
 *
 * @author lsx
 * @since 2021-09-30
 */
public interface ClueMapper extends BaseMapper<Clue> {
    /**
     * 根据线索归属人id分页查询客户线索信息
     *
     * @param page       分页对象,xml中可以从里面进行取值,传递参数 Page 即自动分页,必须放在第一位(你可以继承Page实现自己的分页对象)
     * @param belongerId 线索归属人id
     * @return 分页对象
     */
    IPage<CustomerClueDTO> findCustomerClues(Page<?> page, Integer belongerId);
}

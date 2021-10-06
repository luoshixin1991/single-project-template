package com.boxuegu.crm.controller;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boxuegu.crm.common.bean.PageQuery;
import com.boxuegu.crm.common.bean.Response;
import com.boxuegu.crm.model.entity.Clue;
import com.boxuegu.crm.model.query.ClueQuery;
import com.boxuegu.crm.service.intf.IClueService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

import static com.boxuegu.crm.common.bean.Response.success;


/**
 * mybatis-plus测试controller
 *
 * @author lsx
 * @date 2021/10/6 11:55
 */
@RestController
public class TestMybatisPlusController {
    @Resource
    private IClueService clueService;

    /**
     * 根据归属人id分页查询线索表
     *
     * @param query
     * @return
     */
    @GetMapping("/clues")
    public Response<IPage<Clue>> page(ClueQuery query) {
        IPage<Clue> page = new Page<>(query.getPageNum(), query.getPageSize());
        QueryWrapper<Clue> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("belonger_id", query.getBelongerId());
        return success(clueService.page(page, queryWrapper));
    }
}

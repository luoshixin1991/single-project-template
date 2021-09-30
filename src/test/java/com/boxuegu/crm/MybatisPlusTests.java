package com.boxuegu.crm;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.boxuegu.crm.mapper.ClueMapper;
import com.boxuegu.crm.model.dto.CustomerClueDTO;
import com.boxuegu.crm.model.entity.Clue;
import com.boxuegu.crm.service.intf.IClueService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
 * TODO: 类说明注释
 *
 * @author lsx
 * @date 2021/9/30 15:26
 */
@Slf4j
@SpringBootTest
public class MybatisPlusTests {
    @Resource
    private ClueMapper clueMapper;
    @Resource
    private IClueService clueService;

    @Test
    public void testFind() {
        Clue clue = clueMapper.selectById(88L);
        log.info("数据：{}", clue);
    }

    @Test
    public void testInsert() {
        Clue clue = Clue.builder()
                .customerId(1L)
                .createStatus(true)
                .followStatus(1)
                .createDesc("测试mybatis-plus")
                .createType(1)
                .created(1L)
                .updated(1L)
                .deleted(false)
                .clueType(1)
                .repeated(true)
                .operator("")
                .build();
        Integer count = clueMapper.insert(clue);
//        Boolean success = clueService.saveBatch(Arrays.asList(clue, clue));
        log.info("新增数量: {}, 自增id: {}", count, clue.getId());
    }

    @Test
    public void test(){
        clueService.removeById(22278508757L);
    }

    /**
     * t_clue单表分页
     */
    @Test
    public void testCluePage() {
        // 设置分页信息
        IPage<Clue> pageSetting = new Page<>(1, 20);
        // 条件构造器
        QueryWrapper<Clue> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("created");
        queryWrapper.like("create_desc", "测试");
        // 分页查询
        IPage<Clue> cluePage = clueMapper.selectPage(pageSetting, queryWrapper);
        log.info(
                "总数：{}, 每页大小：{}, 总页数：{}, 当前页：{}, 数据：{}",
                cluePage.getTotal(),
                cluePage.getSize(),
                cluePage.getPages(),
                cluePage.getCurrent(),
                cluePage.getRecords()
        );
    }

    /**
     * 自定义查询分页
     */
    @Test
    public void testCustomCluePage() {
        // 设置分页信息
        Page<CustomerClueDTO> pageSetting = new Page<>(1, 20);
        IPage<CustomerClueDTO> customerCluePage = clueMapper.findCustomerClues(pageSetting, 93451);
        log.info(
                "总数：{}, 每页大小：{}, 总页数：{}, 当前页：{}, 数据：{}",
                customerCluePage.getTotal(),
                customerCluePage.getSize(),
                customerCluePage.getPages(),
                customerCluePage.getCurrent(),
                customerCluePage.getRecords()
        );
    }
}

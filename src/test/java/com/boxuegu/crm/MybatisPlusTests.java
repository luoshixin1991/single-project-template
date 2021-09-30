package com.boxuegu.crm;

import com.boxuegu.crm.mapper.ClueMapper;
import com.boxuegu.crm.model.entity.Clue;
import com.boxuegu.crm.service.intf.IClueService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

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

    @Test
    public void test(){
        Clue clue = clueMapper.selectById(88L);
        log.info("数据：{}", clue);
    }
}

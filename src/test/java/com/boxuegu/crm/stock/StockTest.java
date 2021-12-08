package com.boxuegu.crm.stock;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 股票相关操作做类
 *
 * @author lsx
 * @date 2021/12/8 14:52
 */
@Slf4j
@SpringBootTest
public class StockTest {

    /**
     * 生成热点板块insert sql
     */
    @Test
    public void generateHotIndustry() {
        int id = 41;
        String[] dataStrArray = {
                "中船系 =   +4.96% =  0= + 14.17%\n" ,
                        "语音技术 =  + 3.53%  =0 = +40.08%\n" ,
                        "元宇宙 =   + 3.51% = 4 = +22.88%\n" ,
                        "NFT概念 = + 3.46% = 2 = +20.15%\n" ,
                        "白酒概念 =  + 3.36%=  3 = +28.47%\n" ,
                        "超级电容 =  + 3.05% = 3=  +61.68%\n" ,
                        "汽车零部件=  + 3.05%= 17= + 37.86%\n" ,
                        "饮料制造 =  +2.97% =  1= + 15.56%\n" ,
                        "PVDF概念= +2.87% =  0 =  -4.35%\n" ,
                        "国防军工=   +2.65%=   4= + 10.94%\n" ,
                        "无线耳机 =  +2.62%= 2= + 19.66%\n" ,
                        "国产航母 =  +2.59%= 1 =+23.94% \n" ,
                        "第三代半导体= +2.56%= 2= +49.78% \n" ,
                        "集成电路概念= +2.54%= 5 =+41.06% \n" ,
                        "新型烟草=   +2.54% =2= + 39.57%\n" ,
                        "卫星导航=   +2.51% =2= + 18.93%\n" ,
                        "无线充电 =  +2.48%= 1= +28.41% \n" ,
                        "华为汽车 =  +2.47%= 3= +78.25% \n" ,
                        "富士康概念=  +2.42%= 1= +22.20% \n" ,
                        "海工装备 =  +2.41% =0= +23.35% "
        };

        StringBuilder sqlStr = new StringBuilder();
        for (String dataStr : dataStrArray) {
            String[] data = dataStr.split("=");
            String sql = "INSERT INTO `t_hot_industry` (`id`, `name`, `increase`, `daily_limit_count`, `increase_year`) "
                    + "VALUES ("
                    + id + ", "
                    + getValue(data[0]) + ", "
                    + getValue(data[1]) + ", "
                    + getValue(data[2]) + ", "
                    + getValue(data[3])
                    + " );";
            sqlStr.append(sql);
            id++;
        }
        log.info(sqlStr.toString());
    }

    private String getValue(String raw) {
        return " '" + raw.replace(" ", "") + "' ";
    }
}

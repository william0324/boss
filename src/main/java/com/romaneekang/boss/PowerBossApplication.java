package com.romaneekang.boss;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.romaneekang.boss.domain.result.PayTypeGroupContent;
import com.romaneekang.boss.mapper.DicPayTypeMapper;
import com.romaneekang.boss.mapper.DicPayWayMapper;
import com.romaneekang.boss.mvc.model.vo.DicItem;
import jakarta.annotation.Resource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@SpringBootApplication
@MapperScan("com.romaneekang.boss.mapper")
public class PowerBossApplication implements CommandLineRunner {
    /**
     * spring容器对象
     */
    @Resource
    private ApplicationContext applicationContext;
    public static List<DicItem> DIC_PAY_WAY_LIST;
    /**
     * 支付方式类型 key是payWayCode,value是类型的所有数据
     */
    public static Map<String, List<DicItem>> DIC_PAY_TYPE_MAP;

    /**
     * 添加分页插件
     */
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL)); // 如果配置多个插件, 切记分页最后添加
        // 如果有多数据源可以不配具体类型, 否则都建议配上具体的 DbType
        return interceptor;
    }

    /**
     * run()方法在spring容器初始化完成执行，run()方法执行时候，spring容器已经初始化完成，此时可以获取到spring容器中的bean
     */
    @Override
    public void run(String... args) throws Exception {
        // 读取dic_pay_way表的数据，缓存到内存中
        DicPayWayMapper dicPayWayMapper = applicationContext.getBean(DicPayWayMapper.class);
        DIC_PAY_WAY_LIST = dicPayWayMapper.selectDicList();
        // 读取dic_pay_type表的数据，缓存到内存中
        DicPayTypeMapper dicPayTypeMapper = applicationContext.getBean(DicPayTypeMapper.class);
        List<PayTypeGroupContent> payTypeGroupContents = dicPayTypeMapper.selectGroupPayWayContent();
        // 得到分组数据 F2F_PAY;条码支付#HUA_BEI_FEN_QI_PAY;花呗分期
        DIC_PAY_TYPE_MAP = payTypeGroupContents
                .stream()
                .collect(Collectors
                        .groupingBy(PayTypeGroupContent::getPayWayCode,
                                Collectors.mapping(payTypeGroupContent -> {
                                    DicItem dicItem = new DicItem();
                                    String[] groupItem = payTypeGroupContent.getContent().split("#");
                                    Arrays.stream(groupItem).forEach(s -> {
                                        String[] payWayItem = s.split(";");
                                        dicItem.setCode(payWayItem[0]);
                                        dicItem.setLabel(payWayItem[1]);
                                    });
                                    return dicItem;
                                },Collectors.toList())));
        System.out.println("DIC_PAY_TYPE_MAP = " + DIC_PAY_TYPE_MAP);
    }

    public static void main(String[] args) {
        SpringApplication.run(PowerBossApplication.class, args);
    }

}

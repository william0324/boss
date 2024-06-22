package com.romaneekang.boss;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.romaneekang.boss.mapper.DicPayWayMapper;
import com.romaneekang.boss.mvc.model.vo.DicItem;
import jakarta.annotation.Resource;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.util.List;

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
        // 读取dic_pay_way标的数据，缓存到内存中
        DicPayWayMapper dicPayWayMapper = applicationContext.getBean(DicPayWayMapper.class);
        DIC_PAY_WAY_LIST = dicPayWayMapper.selectDicList();
    }

    public static void main(String[] args) {
        SpringApplication.run(PowerBossApplication.class, args);
    }

}

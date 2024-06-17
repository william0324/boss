package com.romaneekang.boss;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.romaneekang.boss.mapper")
public class PowerBossApplication {

    public static void main(String[] args) {
        SpringApplication.run(PowerBossApplication.class, args);
    }

}

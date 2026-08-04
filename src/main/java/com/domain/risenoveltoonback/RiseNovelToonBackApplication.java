package com.domain.risenoveltoonback;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
@MapperScan("com.domain.risenoveltoonback.repository.mapper")
public class RiseNovelToonBackApplication {

    public static void main(String[] args) {
        SpringApplication.run(RiseNovelToonBackApplication.class, args);
    }

}

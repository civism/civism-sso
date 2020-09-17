package com.civism.sso;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author : Civism
 * @projectName : civism-sso
 * @E-mail : 695234456@qq.com
 */
@SpringBootApplication
@MapperScan("com.civism.sso.dao")
public class SsoApplication {

    public static void main(String[] args) {
        SpringApplication.run(SsoApplication.class, args);
    }

}

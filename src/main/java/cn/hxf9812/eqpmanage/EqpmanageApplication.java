package cn.hxf9812.eqpmanage;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.hxf9812.eqpmanage.dao")
public class EqpmanageApplication {

    public static void main(String[] args) {
        SpringApplication.run(EqpmanageApplication.class, args);
    }

}

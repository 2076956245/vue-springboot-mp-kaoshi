package wjx.bgs.vuespringbootmpkaoshi;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("wjx.bgs.vuespringbootmpkaoshi.mapper")
public class VueSpringbootMpKaoshiApplication {

    public static void main(String[] args) {
        SpringApplication.run(VueSpringbootMpKaoshiApplication.class, args);
    }

}

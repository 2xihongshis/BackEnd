package cn.org.twotomatoes.monitor;

import cn.org.twotomatoes.monitor.common.RedisMQ;
import cn.org.twotomatoes.monitor.common.TemplateHolder;
import cn.org.twotomatoes.monitor.helper.CountUVHelper;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@Slf4j
@EnableCaching
@EnableScheduling
@ServletComponentScan
@SpringBootApplication
@EnableTransactionManagement
@MapperScan("cn.org.twotomatoes.monitor.mapper")
public class MonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(MonitorApplication.class, args);
        log.info("******** 项目启动成功 ********");

        RedisMQ.setStringRedisTemplate(TemplateHolder.get());
        CountUVHelper.setStringRedisTemplate(TemplateHolder.get());
    }

}

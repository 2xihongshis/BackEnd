package cn.org.twotomatoes.monitor.schedule;

import cn.hutool.core.util.ObjectUtil;
import cn.org.twotomatoes.monitor.entity.PvAndUv;
import cn.org.twotomatoes.monitor.helper.CountUVHelper;
import cn.org.twotomatoes.monitor.service.PvAndUvService;
import cn.org.twotomatoes.monitor.common.RedisMQ;
import cn.org.twotomatoes.monitor.common.RedisMQResult;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;

import static cn.org.twotomatoes.monitor.constant.RedisConstants.PU_UV_KEY_PATTERN;
import static cn.org.twotomatoes.monitor.constant.RedisConstants.URL_MQ_KEY_PREFIX;

/**
 * @author HeYunjia
 */

@Slf4j
@Component
public class CountUVTask {

    @Resource
    private PvAndUvService pvAndUvService;

    /**
     * 保存 redis 中的 uv 数据到 mysql
     * 每一小时执行一次, 在每个零分执行
     */
    @SneakyThrows
    @Scheduled(cron = "0 0 0/1 * * *")
    private void backupUV() {
        log.info("backupUV 执行");
        String time = CountUVHelper.updateTime();
        String key = URL_MQ_KEY_PREFIX + time;

        RedisMQ.createIfAbsent(key);
        RedisMQResult message = RedisMQ.poll(key);
        while (ObjectUtil.isNotNull(message)) {
            String url = (String) message.getValue();

            PvAndUv pvAndUv = new PvAndUv();
            pvAndUv.setTime(new SimpleDateFormat(PU_UV_KEY_PATTERN).parse(time));
            pvAndUv.setPvNum(CountUVHelper.countPV(time, url));
            pvAndUv.setUvNum(CountUVHelper.countUV(time, url));
            pvAndUv.setUrl(url);

            pvAndUvService.save(pvAndUv);

            CountUVHelper.deleteKey(time, url);

            RedisMQ.ack(key, message);
            message = RedisMQ.poll(key);
        }
        RedisMQ.delete(key);
    }
}

package cn.org.twotomatoes.monitor.schedule;

import cn.hutool.core.util.ObjectUtil;
import cn.org.twotomatoes.monitor.common.RedisMQ;
import cn.org.twotomatoes.monitor.common.RedisMQResult;
import cn.org.twotomatoes.monitor.entity.PvAndUv;
import cn.org.twotomatoes.monitor.helper.CountUVHelper;
import cn.org.twotomatoes.monitor.service.PvAndUvService;
import cn.org.twotomatoes.monitor.util.URLUtils;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;

import static cn.org.twotomatoes.monitor.constant.RedisConstants.*;

/**
 * 将 redis 中 pv uv 数据备份到数据库
 *
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
    @Scheduled(cron = "0 10 0/1 * * *")
    private void backupUV() {
        String time = CountUVHelper.updateTime();
        log.info("backupUV 执行, 记录 time: {}", time);
        String key = COUNT_UV_KEY_PREFIX + time + URL_MQ_KEY_PREFIX;

        RedisMQ<String> mq = RedisMQ.getMQByKey(key);
        if (mq == null) return;

        RedisMQResult<String> message = mq.poll();
        while (ObjectUtil.isNotNull(message)) {
            String url = message.getValue();

            PvAndUv pvAndUv = new PvAndUv();
            pvAndUv.setTime(new SimpleDateFormat(PU_UV_KEY_PATTERN).parse(time));
            pvAndUv.setPvNum(CountUVHelper.countPV(time, url));
            pvAndUv.setUvNum(CountUVHelper.countUV(time, url));
            pvAndUv.setUrl(URLUtils.convert(url, true));

            pvAndUvService.save(pvAndUv);

            CountUVHelper.deleteKey(time, url);

            mq.ack(message);
            message = mq.poll();
        }

        mq.delete();
    }
}

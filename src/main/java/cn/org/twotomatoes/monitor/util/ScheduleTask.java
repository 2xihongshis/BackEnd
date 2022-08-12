package cn.org.twotomatoes.monitor.util;

import cn.org.twotomatoes.monitor.entity.PvAndUv;
import cn.org.twotomatoes.monitor.service.PvAndUvService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Set;

import static cn.org.twotomatoes.monitor.util.constant.RedisConstants.PU_UV_KEY_PATTERN;

/**
 * @author HeYunjia
 */

@Slf4j
@Component
public class ScheduleTask {

    @Resource
    private PvAndUvService pvAndUvService;

    /**
     * 保存 redis 中的 uv 数据到 mysql
     * 每一小时执行一次, 在每个零分执行
     */
    @SneakyThrows
    @Scheduled(cron = "0 0 0/1 * * *")
    private void backupUV() {
        Set<String> set = CountUVUtil.getURLSet();
        String oldTime = CountUVUtil.updateTime();
        for (String url : set) {
            Long pv = CountUVUtil.countPV(oldTime, url);
            if (pv == 0) continue;
            Long uv = CountUVUtil.countUV(oldTime, url);
            log.info("保存 pv uv 数据:\n" +
                    "url: {}\n" +
                    "oldTime: {}, pv: {}, uv: {}", url, oldTime, pv, uv);

            PvAndUv pu = new PvAndUv();
            pu.setUrl(url);
            pu.setPvNum(pv);
            pu.setUvNum(uv);
            pu.setTime(new SimpleDateFormat(PU_UV_KEY_PATTERN).parse(oldTime));

            pvAndUvService.save(pu);

            CountUVUtil.deleteKey(oldTime, url);
        }

    }
}

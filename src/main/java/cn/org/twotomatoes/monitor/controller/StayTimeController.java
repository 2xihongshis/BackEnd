package cn.org.twotomatoes.monitor.controller;

import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.StayTime;
import cn.org.twotomatoes.monitor.service.StayTimeService;
import cn.org.twotomatoes.monitor.vo.HourPerDayWithLastWeek;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;
import java.util.LinkedList;
import java.util.List;

/**
 * @author HeYunjia
 */
@Slf4j
@RestController
@CrossOrigin("*")
@Api(tags = "停留时间")
@RequestMapping("stay-time")
public class StayTimeController {

    @Resource
    private StayTimeService stayTimeService;

    @ApiIgnore
    @PostMapping("upload")
    public R<String> uploadStayTime(@RequestBody StayTime stayTime) {
        log.info("上传数据 stayTime: {}", JSONUtil.toJsonStr(stayTime));

        return stayTimeService.uploadStayTime(stayTime);
    }

    @GetMapping("{timestamp}")
    @ApiOperation(value = "返回指定时间前一周的用户在线时长", notes = "概览中间第五个小图")
    public R<List<Long>> stayTimeInfo(@PathVariable("timestamp") Long timestamp) {
        log.info("请求 {} 时间戳的 用户在线时长 数据", timestamp);

        return R.success(new LinkedList<>());
    }

    @GetMapping("bounce-info/{timestamp}")
    @ApiOperation(value = "获取今天及一周前的跳出率数据", notes = "概览中间第四个小图")
    public R<HourPerDayWithLastWeek> bounceInfo(@PathVariable("timestamp") Long timestamp) {
        log.info("请求 {} 时间戳的 用户 数据", timestamp);

        HourPerDayWithLastWeek hourPerDayWithLastWeek = new HourPerDayWithLastWeek();

        return R.success(hourPerDayWithLastWeek);
    }
}

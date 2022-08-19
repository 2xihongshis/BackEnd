package cn.org.twotomatoes.monitor.controller;

import cn.hutool.json.JSONUtil;
import cn.org.twotomatoes.monitor.dto.R;
import cn.org.twotomatoes.monitor.entity.VisitInfo;
import cn.org.twotomatoes.monitor.service.VisitInfoService;
import cn.org.twotomatoes.monitor.vo.*;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.annotation.Resource;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * @author HeYunjia
 */
@Slf4j
@RestController
@CrossOrigin("*")
@Api(tags = "流量监控")
@RequestMapping("visit-info")
public class VisitInfoController {

    @Resource
    private VisitInfoService visitInfoService;

    @ApiIgnore
    @PostMapping("upload")
    public R<String> uploadVisitInfo(@RequestBody VisitInfo visitInfo) {
        log.info("上传数据 visitInfo: {}", JSONUtil.toJsonStr(visitInfo));

        return visitInfoService.uploadVisitInfo(visitInfo);
    }

    @GetMapping("flow/{timestamp}")
    @ApiOperation(value = "获取流量信息", notes = "就是概览里面最上面的一行数据")
    public R<FlowVO> flowData(@PathVariable("timestamp") Long timestamp) {
        log.info("请求 {} 时间戳的流量数据", timestamp);

        return R.success(new FlowVO());
    }

    @GetMapping("user-volume/{timestamp}")
    @ApiOperation(value = "获取用户量数据", notes = "中间的连续一个月的数据")
    public R<VisitVolumeVO> visitVolumeData(@PathVariable("timestamp") Long timestamp) {
        log.info("请求 {} 时间戳的用户量数据", timestamp);

        VisitVolumeVO visitVolumeVO = new VisitVolumeVO();
        visitVolumeVO.addData(new Date(), 10L, 20L);

        return R.success(visitVolumeVO);
    }

    @GetMapping("pv-info/{timestamp}")
    @ApiOperation(value = "获取今天及一周前的 pv", notes = "概览中间第一个小图")
    public R<HourPerDayWithLastWeek> pvInfo(@PathVariable("timestamp") Long timestamp) {
        log.info("请求 {} 时间戳的 pv 数据", timestamp);

        HourPerDayWithLastWeek hourPerDayWithLastWeek = new HourPerDayWithLastWeek();

        return R.success(hourPerDayWithLastWeek);
    }

    @GetMapping("uv-info/{timestamp}")
    @ApiOperation(value = "获取今天及一周前的 uv", notes = "概览中间第二个小图")
    public R<HourPerDayWithLastWeek> uvInfo(@PathVariable("timestamp") Long timestamp) {
        log.info("请求 {} 时间戳的 uv 数据", timestamp);

        HourPerDayWithLastWeek hourPerDayWithLastWeek = new HourPerDayWithLastWeek();

        return R.success(hourPerDayWithLastWeek);
    }

    @GetMapping("user-info/{timestamp}")
    @ApiOperation(value = "获取今天及一周前的新用户数据", notes = "概览中间第三个小图")
    public R<HourPerDayWithLastWeek> newUserInfo(@PathVariable("timestamp") Long timestamp) {
        log.info("请求 {} 时间戳的 用户 数据", timestamp);

        HourPerDayWithLastWeek hourPerDayWithLastWeek = new HourPerDayWithLastWeek();

        return R.success(hourPerDayWithLastWeek);
    }

    @GetMapping("retention-info/{timestamp}")
    @ApiOperation(value = "返回指定时间前一周的用户留存率", notes = "概览中间第六个小图")
    public R<List<Long>> retentionInfo(@PathVariable("timestamp") Long timestamp) {
        log.info("请求 {} 时间戳的 用户留存率 数据", timestamp);

        return R.success(new LinkedList<>());
    }

    @GetMapping("site-visit-info/{timestamp}/{n}")
    @ApiOperation(value = "网站访问前 n", notes = "综合数据第一个图")
    public R<SiteVisitVO> siteVisitInfo(
            @PathVariable("timestamp") Long timestamp,
            @PathVariable("n") Integer n) {
        log.info("请求 {} 时间戳的 网站访问量数据 前 {} 数据", timestamp, n);

        SiteVisitVO siteVisitorVO = new SiteVisitVO();
        siteVisitorVO.setSiteList(new LinkedList<>());
        siteVisitorVO.getSiteList().add(new InfoEntryVO());

        return R.success(siteVisitorVO);
    }

    @GetMapping("source-url-info/{timestamp}/{n}")
    @ApiOperation(value = "来源网站前 n", notes = "综合数据第二个图")
    public R<SourceUrlVO> sourceUrlInfo(
            @PathVariable("timestamp") Long timestamp,
            @PathVariable("n") Integer n) {
        log.info("请求 {} 时间戳的 来源网站 前 {} 数据", timestamp, n);

        SourceUrlVO sourceUrlVO = new SourceUrlVO();
        sourceUrlVO.setSourceList(new LinkedList<>());
        sourceUrlVO.getSourceList().add(new InfoEntryVO());

        return R.success(sourceUrlVO);
    }

    @GetMapping("city-user-info/{timestamp}/{n}")
    @ApiOperation(value = "城市用户前 n", notes = "综合数据第三个图")
    public R<CityUserVo> cityUerInfo(
            @PathVariable("timestamp") Long timestamp,
            @PathVariable("n") Integer n) {
        log.info("请求 {} 时间戳的 城市用户 前 {} 数据", timestamp, n);

        CityUserVo cityUserVo = new CityUserVo();
        cityUserVo.setCityList(new LinkedList<>());
        cityUserVo.getCityList().add(new InfoEntryVO());

        return R.success(cityUserVo);
    }
}

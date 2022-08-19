package cn.org.twotomatoes.monitor.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * 返回用户统计量(每个月的新老用户数据)时使用的对象
 *
 * @author HeYunjia
 */
@Data
@ApiModel("用户访问信息")
public class VisitVolumeVO {

    @ApiModelProperty("列表信息")
    private List<VisitType> infoList;

    /**
     * 添加一条数据
     *
     * @param time 时间
     * @param newUser 新用户
     * @param oldUser 旧用户
     */
    public void addData(Date time, Long newUser, Long oldUser) {
        infoList.add(new VisitType(time, newUser, oldUser));
    }

    public VisitVolumeVO() {
        infoList = new LinkedList<>();
    }

    @Data
    @AllArgsConstructor
    @ApiModel("每天的数据单元")
    private static class VisitType {

        @ApiModelProperty("时间戳")
        private Date timestamp;

        @ApiModelProperty("新用户")
        private Long newUser;

        @ApiModelProperty("旧用户")
        private Long oldUser;
    }
}

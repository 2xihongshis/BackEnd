package cn.org.twotomatoes.monitor.handler;

import cn.org.twotomatoes.monitor.util.Holder;
import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import static cn.org.twotomatoes.monitor.util.Holder.IP_HOLDER;
import static cn.org.twotomatoes.monitor.constant.MysqlFieldConstants.IP;

/**
 * 自动填充 ip 地址
 *
 * @author HeYunjia
 */
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    @Override
    public void insertFill(MetaObject metaObject) {
        metaObject.setValue(IP, Holder.get(IP_HOLDER));
    }

    @Override
    public void updateFill(MetaObject metaObject) {

    }
}

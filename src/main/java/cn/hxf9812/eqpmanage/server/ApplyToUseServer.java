package cn.hxf9812.eqpmanage.server;

import cn.hxf9812.eqpmanage.pojo.Apply;
import cn.hxf9812.eqpmanage.pojo.Msg;

import java.util.List;

/**
 * 使用申请逻辑
 */
public interface ApplyToUseServer {
    /**
     * 添加一条申请请求
     * @param apply
     * @return
     */
    boolean addApply(Apply apply);

    /**
     * 根据apply中申请者信息，查询所有数据
     * @return List<Apply>
     */
    List<Apply> getAllApplyForWhoapply(Apply apply);
}

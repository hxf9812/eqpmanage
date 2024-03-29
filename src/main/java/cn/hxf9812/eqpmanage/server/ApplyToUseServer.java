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

    /**
     * 根据处理人账号，查询所有请求
     * @param apply
     * @return
     */
    List<Apply> getAllApplyForWhoapplyed(Apply apply);

    /**
     * 修改是否成功
     * @param apply
     * @return
     */
    Msg setIsdealed(Apply apply);

    /**
     *  延迟自动归还设备：未实现
     * @param eqp_id
     * @param apply_id
     */
    void returnEqp(int eqp_id,int apply_id);

    /**
     *  清除一条申请记录
     */
    boolean deleteApplyById(int id);
}

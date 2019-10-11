package cn.hxf9812.eqpmanage.server;

import cn.hxf9812.eqpmanage.pojo.EqpLog;
import cn.hxf9812.eqpmanage.pojo.Msg;

/**
 * 这是一个日志处理server接口
 */
public interface EqpLogServer {
    /**
     * 添加一条日志信息
     */
    public Msg addEqpLog(EqpLog eqpLog);
    /**
     * 删除一条日志信息
     */
    public Msg deleteEqpLog(EqpLog eqpLog);
    /**
     * 获取所有日志信息
     */
    public Msg getAllEqpLog();
}

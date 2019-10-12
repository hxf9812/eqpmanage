package cn.hxf9812.eqpmanage.server;

import cn.hxf9812.eqpmanage.pojo.EqpLog;
import cn.hxf9812.eqpmanage.pojo.Msg;

import java.util.List;

/**
 * 这是一个日志处理server接口
 */
public interface EqpLogServer {
    /**
     * 添加一条日志信息
     */
    public boolean addEqpLog(EqpLog eqpLog);
    /**
     * 删除一条日志信息
     */
    public Msg deleteEqpLogById(EqpLog eqpLog);
    /**
     * 获取所有日志信息
     */
    public Msg getAllEqpLog();
    /**
     * 修改机器的损坏次数
     * 无用！
     */
    public boolean updateEqpLogDamagetimeById(int eqpLog_id);
    /**
     * 修改机器的处理状态
     * 需要参数处理状态 日志id
     */
    public  boolean updateRepairedById(int eqpLog_id,int isrepaired);
    /**
     * 找到某个指定id的设备的所有日志中故障次数最大的那个
     */
    public List<EqpLog> getEqpLogByEqpid(int eqpid);
    /**
     * 找到某个指定id的设备的所有日志中故障次数最大的那个
     */
    public EqpLog getEqpLogByEqpidAndTimes(int eqpid);
}

package cn.hxf9812.eqpmanage.server;

import cn.hxf9812.eqpmanage.pojo.Eqp;
import cn.hxf9812.eqpmanage.pojo.User;

import java.util.List;

/**
 * 实验室设备的server接口
 */
public interface EqpServer {
    /**
     * 获取所有的
     * @return List<Eqp>
     */
    List<Eqp> getAllEqp();
    /**
     * 修改一个用户根据id
     */
    boolean modifyEqp(Eqp eqp);
    /**
     * 根据id查询设备
     */
    Eqp getEqpById(int id);
    /**
     * 添加一个设备
     */
    public boolean addEqp(Eqp eqp);
}

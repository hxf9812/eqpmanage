package cn.hxf9812.eqpmanage.server;

import cn.hxf9812.eqpmanage.pojo.Eqp;
import cn.hxf9812.eqpmanage.pojo.Page;
import cn.hxf9812.eqpmanage.pojo.User;
import org.apache.ibatis.annotations.Delete;

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
    List<Eqp> getAllEqpisFree();
    /**
     * 模糊查询所有
     */
    List<Eqp> getAllEqpMatching(Page page);
    /**
     * 修改一个设备根据id
     */
    boolean modifyEqp(Eqp eqp);
    /**
     * 根据id查询设备
     */
    Eqp getEqpById(int id);
    /**
     * 根据id查询用户和其负责人和使用者
     * @param id
     * @return
     */
     Eqp getEqpById_WithUser(int id);
    /**
     * 添加一个设备
     */
    public boolean addEqp(Eqp eqp);
    /**
     *根绝id删除一个设备
     */
    boolean deleteEqp(Eqp eqp);

    /**
     * author hxf
     * @param user
     * @return
     */
    List<Eqp> getEqpByUser(String user);

    /**
     * 归还设备
     */
    boolean returnEqp(int id);
}

package cn.hxf9812.eqpmanage.server;

import cn.hxf9812.eqpmanage.pojo.Declare;
import cn.hxf9812.eqpmanage.pojo.Msg;

import java.util.List;

/**
 * 维修申请逻辑
 */
public interface DeclareServer {
    /**
     * 申请人的信息回显
     */
    public List<Declare> getDeclareByDeclarant(Declare declare);
    /**
     * 管理员的信息回显
     */
    public List<Declare> getDeclareByMaster(Declare declare);
    /**
     * 用户发送申请
     */
    public Msg sendDeclare(Declare declare);
    /**
     * 管理员处理申请
     */
    public Msg handleDeclare(Declare declare);
    /**
     * 管理员认证机器故障
     */
    public Msg declareEnsure(Declare declare);

    /**
     *管理员删除一个用户申请
     */
    public Msg deleteDeclare(Declare declare);
    /**
     * 查找一个申请
     */
    public Declare getDeclareById(int id);

}

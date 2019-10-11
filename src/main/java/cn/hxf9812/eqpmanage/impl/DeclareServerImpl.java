package cn.hxf9812.eqpmanage.impl;

import cn.hxf9812.eqpmanage.dao.DeclareMapper;
import cn.hxf9812.eqpmanage.pojo.Declare;
import cn.hxf9812.eqpmanage.pojo.Eqp;
import cn.hxf9812.eqpmanage.pojo.Msg;
import cn.hxf9812.eqpmanage.server.DeclareServer;
import cn.hxf9812.eqpmanage.server.EqpServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@SuppressWarnings("all")
@Service("declareServer")
public class DeclareServerImpl implements DeclareServer {

    @Autowired
    DeclareMapper declareMapper;
    @Autowired
    EqpServer eqpServer;

    @Override
    public List<Declare> getDeclareByDeclarant(Declare declare) {
        if (declare.getDeclarant()==null){
            return null;
        }
        return declareMapper.getDeclareByDeclarant(declare);
    }

    @Override
    public List<Declare> getDeclareByMaster(Declare declare) {
        if (declare.getMaster()==null){
            return null;
        }
        return declareMapper.getDeclareByMaster(declare);
    }

    @Override
    public Msg sendDeclare(Declare declare) {
        //判断需要的参数是否为空，时间信息不用 ，申报状态 0待处理 1已认证 2已处理
        if ((""+declare.getEqpid())==null||declare.getDeclarant()==null
        ||declare.getContent()==null){
            return Msg.fail("有必须的参数为空！");
        }
        //查询申请维修的设备是否已经被其他人申请
        Declare declareByEqpid = declareMapper.getDeclareByEqpid(declare.getEqpid());
        if(declareByEqpid!=null){
            //已经被人申请
            return Msg.fail("您所申请维修的设备已经被其他人申请");
        }
        //根据id查找是设备是否存在
        Eqp eqp = eqpServer.getEqpById(declare.getEqpid());
        if(eqp==null){
            return Msg.fail("您所申请的设备不存在");
        }
        if (eqp.getStatus()==1){
            //设备处于损坏状态，无法发送损坏
            return Msg.fail("该设备已经损坏,请确认后再试");
        }
        declare.setMaster(eqp.getMaster());
        //设置时间，申报状态
        declare.setDecalredate(new Date());
        declare.setDealstatus(0);
        //添加维修申请
        if (declareMapper.addDeclare(declare)>0){
             return Msg.success("申报成功！请等待管理员处理");
        }else{
            //添加失败
            return Msg.fail("添加失败,原因可能是重复操作,请求可能已经存在");
        }
    }

    @Override
    public Msg handleDeclare(Declare declare) {
        //判断必须参数是否为空 id eqpid
        if ((declare.getId()+"")==null){
            return Msg.fail("被需要的参数为空");
        }
        Declare declareById = declareMapper.getDeclareById(declare.getId());
        if(declareById==null){
            return Msg.fail("无法找到对应的申请内容");
        }
        Eqp eqp = eqpServer.getEqpById(declareById.getEqpid());
        if(eqp==null){
            return Msg.fail("无法找到对应的设备");
        }
        if (declareById.getDealstatus()==1){
            //还没有被认证
            //设为处理完毕
            //把状态改为同意
            declareById.setDealstatus(2);
            if( declareMapper.handleDeclare(declareById)>0){
                //认证成功,修改机器健康状态
                eqp.setStatus(0);
                eqpServer.modifyEqp(eqp);
                return Msg.success("处理成功！");
            }else{
                //处理失败
                return Msg.fail("处理失败，原因可能是因为重复提交数据，或者没有故障认证");
            }
        }else{
            //已经被处理
            return Msg.fail("请求已经被处理过了，或者还未被认证");
        }
    }

    @Override
    public Msg declareEnsure(Declare declare) {
        //判断必须参数是否为空 id eqpid
        if ((declare.getId()+"")==null){
            return Msg.fail("被需要的参数为空");
        }
        Declare declareById = declareMapper.getDeclareById(declare.getId());
        if(declareById==null){
            return Msg.fail("无法找到对应的申请内容");
        }
        //设备状态设为认证成功
        declareById.setDealstatus(1);
        Eqp eqp=new Eqp();
        eqp.setId(declareById.getEqpid());
        //故障认证
        if(declareMapper.handleDeclare(declareById)>0){
            //故障认证成功，把机器改成损坏
            eqp.setStatus(1);
            //更新设备信息
            System.out.println( eqpServer.modifyEqp(eqp));
            return Msg.success("设备认证成功");
        }else{
            return Msg.fail("设备认证失败,原因可能是重复操作");
        }
    }

    @Override
    public Msg deleteDeclare(Declare declare) {
        //判断必须参数是否为空 id eqpid
        if ((declare.getId()+"")==null){
            return Msg.fail("被需要的参数为空！");
        }
        //删除一个申报请求
        if (declareMapper.deleteById(declare.getId())>0){
            return Msg.success("删除成功！");
        }else{
            return Msg.fail("删除失败，原因可能是重复操作！");

        }
    }
}

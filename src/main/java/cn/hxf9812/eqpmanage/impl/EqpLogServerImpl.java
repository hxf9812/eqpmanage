package cn.hxf9812.eqpmanage.impl;

import cn.hxf9812.eqpmanage.dao.EqpLogMapper;
import cn.hxf9812.eqpmanage.pojo.EqpLog;
import cn.hxf9812.eqpmanage.pojo.Msg;
import cn.hxf9812.eqpmanage.server.EqpLogServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@SuppressWarnings("all")
@Service("eqplogServerImpl")
public class EqpLogServerImpl implements EqpLogServer {

    @Autowired
    private EqpLogMapper eqpLogMapper;

    @Override
    public Msg addEqpLog(EqpLog eqpLog) {
        if ((eqpLog.getEqpid()+"").equals("")||(eqpLog.getDamagetime()+"")==null||eqpLog.getCause()==null){
            return Msg.fail("被需要的参数为空");
        }
        if(eqpLogMapper.addEqpLog(eqpLog)>0){
            return Msg.success();
        }else{
            return Msg.fail();
        }
    }

    @Override
    public Msg deleteEqpLog(EqpLog eqpLog) {
        return null;
    }

    @Override
    public Msg getAllEqpLog() {
        return null;
    }
}

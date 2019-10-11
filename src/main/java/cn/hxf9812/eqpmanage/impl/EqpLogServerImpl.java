package cn.hxf9812.eqpmanage.impl;

import cn.hxf9812.eqpmanage.dao.EqpLogMapper;
import cn.hxf9812.eqpmanage.pojo.EqpLog;
import cn.hxf9812.eqpmanage.pojo.Msg;
import cn.hxf9812.eqpmanage.server.EqpLogServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("all")
@Service("eqplogServerImpl")
public class EqpLogServerImpl implements EqpLogServer {

    @Autowired
    private EqpLogMapper eqpLogMapper;

    @Override
    public boolean addEqpLog(EqpLog eqpLog) {
        if ((eqpLog.getEqpid()+"").equals("")||eqpLog.getCause()==null){
            return false;
        }
        if(eqpLogMapper.addEqpLog(eqpLog)>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public Msg deleteEqpLogById(EqpLog eqpLog) {
        Integer i=eqpLog.getId();
        if (i==null){
            return Msg.fail("被必须的参数为空！");
        }
        if(eqpLogMapper.deleteEqpLogById(eqpLog.getId())>0){
            return Msg.success("删除成功!");
        }else{
            return Msg.fail("删除失败,可能是因为重复操作");
        }
    }

    @Override
    public Msg getAllEqpLog() {
        List<EqpLog> logs=eqpLogMapper.getAllEqpLog();
        if (logs.size()!=0){
            return Msg.success("处理成功").add("logs",logs);
        }else{
            return Msg.fail("日志数据为空!");
        }
    }

    @Override
    public boolean updateEqpLogDamagetimeById(int eqpLog_id) {
        //根据id查找日志信息
        EqpLog eqpById = eqpLogMapper.getEqpLogById(eqpLog_id);
        if (eqpById==null){
            return false;
        }
        eqpById.setDamagetime(eqpById.getDamagetime()+1);
        if(eqpLogMapper.updateDamagetimeById(eqpById)>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean updateRepairedById(int eqpLog_id,int isrepaired) {
        //根据id查找日志信息
        EqpLog eqpById = eqpLogMapper.getEqpLogById(eqpLog_id);
        if (eqpById==null){
            return false;
        }
        eqpById.setIsrepaired(isrepaired);
        if(eqpLogMapper.updateRepairedById(eqpById)>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public List<EqpLog> getEqpLogByEqpid(int eqpid) {
        return eqpLogMapper.getEqpLogByEqpid(eqpid);
    }

    @Override
    public EqpLog getEqpLogByEqpidAndTimes(int eqpid) {
        List<EqpLog> logs = eqpLogMapper.getEqpLogByEqpid(eqpid);
        if (logs.size()==0){
            return null;
        }
        EqpLog eqpLog=logs.get(0);
        for (EqpLog log : logs) {
            if (log.getDamagetime()>eqpLog.getDamagetime()){
                eqpLog=log;
            }
        }
        return eqpLog;
    }
}

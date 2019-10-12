package cn.hxf9812.eqpmanage.controller;


import cn.hxf9812.eqpmanage.pojo.EqpLog;
import cn.hxf9812.eqpmanage.pojo.Msg;
import cn.hxf9812.eqpmanage.server.EqpLogServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@CrossOrigin
@Controller
public class EqpLogController {
    @Autowired
    private EqpLogServer server;

    /**
     * 删除一个日志
     * @param eqpLog
     * @return
     */
    @RequestMapping("/deleteEqpLog")
    @ResponseBody
    public Msg deleteEqpLog(@RequestBody EqpLog eqpLog){
        return server.deleteEqpLogById(eqpLog);
    }

    /**
     * 获取所有日志
     * @return
     */
    @RequestMapping("/getAllEqpLog")
    @ResponseBody
    public Msg getAllEqpLog(){
        return server.getAllEqpLog();
    }

    /**
     * 根据某设备id --> Eqpid 获取其所有维修日志
     * @param eqpLog
     * @return
     */
    @RequestMapping("/getAllEqpLogByEqpId")
    @ResponseBody
    public Msg getAllEqpLogByEqpId(@RequestBody EqpLog eqpLog) {
        if (eqpLog.getEqpid()==0){
            return Msg.fail();
        }
        List<EqpLog> eqpLogByEqpid = server.getEqpLogByEqpid(eqpLog.getEqpid());
        if (eqpLogByEqpid==null){
            return Msg.fail("该设备还没有维修日志");
        }
        return Msg.success().add("logsByEqpid",eqpLogByEqpid);
    }
}
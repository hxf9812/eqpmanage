package cn.hxf9812.eqpmanage.controller;


import cn.hxf9812.eqpmanage.pojo.Apply;
import cn.hxf9812.eqpmanage.pojo.Declare;
import cn.hxf9812.eqpmanage.pojo.EqpLog;
import cn.hxf9812.eqpmanage.pojo.Msg;
import cn.hxf9812.eqpmanage.server.DeclareServer;
import cn.hxf9812.eqpmanage.server.EqpLogServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.Date;
import java.util.List;

/**
 * 申报处理：
 * 用户发送申报 申请状态为 0 （待处理） /sendDeclare
 * 管理员认证申报正确无误 申请状态为 1 （已认证） /declareEnsure
 * 管理员认证申报处理申报完毕 申请状态为 2 （已处理） /handleDeclare
 * 管理员删除一个维修申报请求  /declareEnsure
 */
@CrossOrigin
@Controller
public class DeclareController {
    @Autowired
    DeclareServer server;
    @Autowired
    EqpLogServer eqpLogServer;

    /**
     * 用户信息回显
     */
    @RequestMapping("/getDeclareByDeclarant")
    @ResponseBody
    public Msg getDeclareByDeclarant(@RequestBody Declare declare){
        List<Declare> list = server.getDeclareByDeclarant(declare);
        if(list==null){
            return Msg.fail("集合为空");
        }else{
            return Msg.success().add("declareList",list);
        }
    }

    /**
     * 管理员回显信息
     * @param declare
     * @return
     */
    @RequestMapping("/getDeclareByMaster")
    @ResponseBody
    public Msg getDeclareByMaster(@RequestBody Declare declare){
        List<Declare> list = server.getDeclareByMaster(declare);
        if(list==null){
            return Msg.fail("集合为空");
        }else{
            return Msg.success().add("declareList",list);
        }
    }
    /**
     * 用户发送申报请求
     */
    @RequestMapping("/sendDeclare")
    @ResponseBody
    public Msg sendDeclare(@RequestBody Declare declare){
        /**
         * 参数：设备id eqpid
         * 申请人账号 declarant
         * 申请理由 content
         */
       return server.sendDeclare(declare);
    }
    /**
     * 管理员认证请求
     * 认证成功后添加日志
     */
    @RequestMapping("/declareEnsure")
    @ResponseBody
    public Msg declareEnsure(@RequestBody Declare declare){
        /**
         * 参数：请求的declare对象的id
         */
        Msg msg = server.declareEnsure(declare);
        if (msg.isFlag()){
            //认证成功，添加一条日志
            //查找对应declare
            Declare declareById = server.getDeclareById(declare.getId());
            //获取当前故障次数最大的日志信息
            EqpLog eqpLogByEqpidAndTimes = eqpLogServer.getEqpLogByEqpidAndTimes(declareById.getEqpid());
            //获取当前最大damagetime
            int damagetime=0;
            if (eqpLogByEqpidAndTimes!=null){
                damagetime=eqpLogByEqpidAndTimes.getDamagetime();
            }
            //封装日志对象
            EqpLog eqpLog=new EqpLog();
            eqpLog.setEqpid(declareById.getEqpid());
            eqpLog.setDamagedate(declareById.getDecalredate());
            eqpLog.setCause(declareById.getContent());
            eqpLog.setIsrepaired(0);
            //设置故障次数
            eqpLog.setDamagetime(damagetime+1);
            //添加日志
            if(eqpLogServer.addEqpLog(eqpLog)){
                return msg;
            }else{
                msg.setMsg("认证成功，日志添加失败！");
                return msg;
            }
        }else{
            return msg;
        }
    }
    /**
     * 管理员认证完毕后的处理请求
     * 处理完请求后修改日志信息
     */
    @RequestMapping("/handleDeclare")
    @ResponseBody
    public Msg handleDeclare(@RequestBody Declare declare){
        /**
         * 参数：请求的declare对象的id
         */
        Msg msg = server.handleDeclare(declare);
        if (msg.isFlag()){
            //处理成功！
            //修改日志信息
            Declare declareById = server.getDeclareById(declare.getId());
            //获取当前故障次数最大的日志信息
            EqpLog eqpLogByEqpidAndTimes = eqpLogServer.getEqpLogByEqpidAndTimes(declareById.getEqpid());
            if (eqpLogByEqpidAndTimes==null){
                msg.setMsg("未知异常！");
                return msg;
            }
            if(eqpLogServer.updateRepairedById(eqpLogByEqpidAndTimes.getId(),1)){
                return msg;
            }else{
                msg.setMsg("处理成功，但日志更改失败！");
                return msg;
            }
        }else{
            return msg;
        }
    }
    /**
     * 管理员删除一个申请请求
     */
    @RequestMapping("/deleteDeclare")
    @ResponseBody
    public Msg deleteDeclare(@RequestBody Declare declare){
        /**
         * 参数：请求的declare对象的id
         */
        return server.deleteDeclare(declare);
    }
}

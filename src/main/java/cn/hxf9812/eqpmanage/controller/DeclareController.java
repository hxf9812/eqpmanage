package cn.hxf9812.eqpmanage.controller;


import cn.hxf9812.eqpmanage.pojo.Apply;
import cn.hxf9812.eqpmanage.pojo.Declare;
import cn.hxf9812.eqpmanage.pojo.Msg;
import cn.hxf9812.eqpmanage.server.DeclareServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
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
     */
    @RequestMapping("/declareEnsure")
    @ResponseBody
    public Msg declareEnsure(@RequestBody Declare declare){
        /**
         * 参数：请求的declare对象的id
         */
        return server.declareEnsure(declare);
    }
    /**
     * 管理员认证完毕后的处理请求
     */
    @RequestMapping("/handleDeclare")
    @ResponseBody
    public Msg handleDeclare(@RequestBody Declare declare){
        /**
         * 参数：请求的declare对象的id
         */
        return server.handleDeclare(declare);
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

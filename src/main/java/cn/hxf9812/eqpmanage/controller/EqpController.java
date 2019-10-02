package cn.hxf9812.eqpmanage.controller;


import cn.hxf9812.eqpmanage.pojo.Eqp;
import cn.hxf9812.eqpmanage.pojo.Msg;
import cn.hxf9812.eqpmanage.server.EqpServer;
import cn.hxf9812.eqpmanage.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 实验室设备的控制器
 */
@CrossOrigin
@Controller
public class EqpController {

    @Autowired
    private EqpServer eqpServer;

    /**
     * 获取所有数据
     * @return
     */
    @RequestMapping("/getAllEqp")
    @ResponseBody
    public Msg getAlleqp(){
        List<Eqp> allEqp = eqpServer.getAllEqp();
        if (allEqp!=null){
           return Msg.success().add("eqpList",allEqp);
        }else{
           return Msg.fail().add("eqpList",null);
        }
    }
    /**
     * 获取所有数据
     * @return
     */
    @RequestMapping("/getEqpById")
    @ResponseBody
    public Msg getEqpById(@RequestBody Eqp eqp){
        String id =""+eqp.getId();
       if ("".equals(id)||id==null){
           return Msg.fail("id不存在").add("eqp",null);
       }
        Eqp eqpById = eqpServer.getEqpById_WithUser(eqp.getId());
       if(eqpById==null){
           return Msg.fail("用户不存在").add("eqp",null);
       }else{
           return Msg.success().add("eqp",eqpById);
       }
    }

    /**
     * 处理修改用户
     * @param eqp
     * @return
     */
    @RequestMapping("/modifyEqp")
    @ResponseBody
    public Msg modifyEqp(@RequestBody Eqp eqp){
        boolean b = eqpServer.modifyEqp(eqp);
        if (b){
            return Msg.success("修改成功！");
        }else{
            return Msg.fail("由于未知原因修改失败！");
        }
    }
    /**
     * 添加设备
     */
    @RequestMapping("/addEqp")
    @ResponseBody
    public Msg addEqp (@RequestBody Eqp eqp){
        boolean b = eqpServer.addEqp(eqp);
        if (b){
            return Msg.success("添加成功！");
        }else{
            return Msg.fail("添加失败！原因可能是您所填写的管理员并不存在！");
        }
    }

    /**
     * 根据id删除一个设备
     * @param eqp
     * @return
     */
    @RequestMapping("/deleteEqp")
    @ResponseBody
    public Msg deleteEqp (@RequestBody Eqp eqp){
        boolean b = eqpServer.deleteEqp(eqp);
        if (b){
            return Msg.success("添加成功！");
        }else{
            return Msg.fail("添加失败！原因可能是id并不存在！");
        }
    }
}
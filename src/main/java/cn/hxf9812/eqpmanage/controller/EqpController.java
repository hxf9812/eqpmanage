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
    @RequestMapping("/getAlleqp")
    @ResponseBody
    public Msg getAlleqp(){
        List<Eqp> allEqp = eqpServer.getAllEqp();
        if (allEqp!=null){
           return Msg.success().add("EqpList",allEqp);
        }else{
           return Msg.fail().add("EqpList",null);
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
    @RequestMapping("/addaeqp")
    @ResponseBody
    public Msg addEqp (@RequestBody Eqp eqp){
        boolean b = eqpServer.addEqp(eqp);
        if (b){
            return Msg.success("添加成功！");
        }else{
            return Msg.fail("添加失败！原因可能是您所填写的管理员并不存在！");
        }
    }
}

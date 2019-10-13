package cn.hxf9812.eqpmanage.controller;


import cn.hxf9812.eqpmanage.pojo.Eqp;
import cn.hxf9812.eqpmanage.pojo.Msg;
import cn.hxf9812.eqpmanage.server.EqpServer;
import cn.hxf9812.eqpmanage.server.UserServer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
    public Msg getAlleqp(@RequestParam(value = "pn",defaultValue = "1")Integer pn){
        //        这个方法后面紧跟着的查询就是分页查询
        PageHelper.startPage(pn,5);

        List<Eqp> allEqp = eqpServer.getAllEqp();

//        包装查询后的结果   把PageInfo交给页面   传入连续显示的页数
        PageInfo pageInfo=new PageInfo(allEqp,5);
        if (pageInfo!=null){
           return Msg.success().add("eqpList",pageInfo);
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

    /**
     * author hxf
     * 检索用户正在使用的设备
     * @param eqp
     * @return
     */
    @RequestMapping("/getEqpByUser")
    @ResponseBody
    public Msg getEqpByUser(@RequestBody Eqp eqp){
        List<Eqp> list = eqpServer.getEqpByUser(eqp.getUser());
        if(list!=null) {
            return Msg.success().add("eqp",list);
        }
        else return Msg.fail("未找到任何设备");
    }

    /**
     * author hxf
     * 用户归还设备
     * @param eqp
     * @return
     */
    @RequestMapping("/returnEqp")
    @ResponseBody
    public Msg returnEqp(@RequestBody Eqp eqp){
        if(eqpServer.returnEqp(eqp.getId()))
            return Msg.success();
        else return Msg.fail();
    }
}

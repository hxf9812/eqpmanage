package cn.hxf9812.eqpmanage.controller;

import cn.hxf9812.eqpmanage.pojo.Apply;
import cn.hxf9812.eqpmanage.pojo.Msg;
import cn.hxf9812.eqpmanage.pojo.User;
import cn.hxf9812.eqpmanage.server.ApplyToUseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@CrossOrigin
@Controller
public class ApplyToUseController {

    @Autowired
    ApplyToUseServer aserver;

    /**
     * 添加一条数据
     * @param apply
     * @param session
     * @return
     */
    @RequestMapping("/applyaEqp")
    @ResponseBody
    public Msg addApply(@RequestBody Apply apply, HttpSession session){

        if(aserver.addApply(apply)){
           return Msg.success();
        }else{
            return Msg.fail("删除失败有可能机器已经损坏或删除");
        }
    }

    @RequestMapping("/getAllApplyByWhoApply")
    @ResponseBody
    public Msg getAllApplyByWhoApply(@RequestBody Apply apply, HttpSession session){
        /*
         * 需要申请者账号whoapply:Apply whoapply参数
         */
        /**
         * 获取用户帐号
         */
//        User user = (User)session.getAttribute("user");
        //设置账号
//        apply.setWhoapply(user.getAccount());
        //通过前端获取用户账号，并传递过来

        //查询
        List<Apply> list = aserver.getAllApplyForWhoapply(apply);
        if(list==null){
            return Msg.fail().add("ApplysByUser",null);
        }else{
            return  Msg.success().add("ApplysByUser",list);
        }
    }
    @RequestMapping("/getAllApplyByWhoApplyed")
    @ResponseBody
    public Msg getAllApplyByWhoApplyed(@RequestBody Apply apply, HttpSession session){
        /*
        * 需要被申请者账号:Apply whoapplyed参数
        * */
        /**
         * 获取管理员帐号
         */
//        User user = (User)session.getAttribute("user");
        //设置账号
//        apply.setWhoapplyed(user.getAccount());
        //查询
        List<Apply> list = aserver.getAllApplyForWhoapplyed(apply);
        if(list==null){
            return Msg.fail().add("ApplysByMaster",null);
        }else{
            return  Msg.success().add("ApplysByMaster",list);
        }
    }


    @RequestMapping("/modifyIsdealed")
    @ResponseBody
    public Msg modifyIsdealed(@RequestBody Apply apply){
        /**
         * 需要请求apply对象的id:  Apply Id参数
         * 需要请求前端选择的结果: Apply isdealed参数
         * 需要所处理的设备id：Apply  applyeqp参数
         */
//        if(aserver.setIsdealed(apply)){
//            return Msg.success();
//        }else{
//            return Msg.fail();
//        }
        return aserver.setIsdealed(apply);
    }

    @RequestMapping("/deleteApplyById")
    @ResponseBody
    public Msg deleteApplyById(@RequestBody Apply apply){
        if(aserver.deleteApplyById(apply.getId()))
            return Msg.success();
        else return Msg.fail("信息删除失败");
    }
}

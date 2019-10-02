package cn.hxf9812.eqpmanage.controller;

import cn.hxf9812.eqpmanage.pojo.Apply;
import cn.hxf9812.eqpmanage.pojo.Msg;
import cn.hxf9812.eqpmanage.pojo.User;
import cn.hxf9812.eqpmanage.server.ApplyToUseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
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
    public Msg addApply(Apply apply, HttpSession session){
        User user = (User)session.getAttribute("user");
        apply.setWhoapply(user.getAccount());
        if(aserver.addApply(apply)){
           return Msg.success();
        }else{
            return Msg.fail();
        }
    }

    @RequestMapping("/getAllApplyByWhoApply")
    @ResponseBody
    public Msg getAllApplyByWhoApply(Apply apply, HttpSession session){
        /**
         * 获取用户帐号
         */
        User user = (User)session.getAttribute("user");
        //设置账号
        apply.setWhoapply(user.getAccount());
        //查询
        List<Apply> list = aserver.getAllApplyForWhoapply(apply);
        if(list==null){
            return Msg.fail().add("ApplysByWhoApply",null);
        }else{
            return  Msg.success().add("ApplysByWhoApply",list);
        }
    }
}

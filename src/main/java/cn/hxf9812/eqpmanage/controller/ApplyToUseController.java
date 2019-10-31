package cn.hxf9812.eqpmanage.controller;

import cn.hxf9812.eqpmanage.pojo.Apply;
import cn.hxf9812.eqpmanage.pojo.Msg;
import cn.hxf9812.eqpmanage.pojo.Page;
import cn.hxf9812.eqpmanage.pojo.User;
import cn.hxf9812.eqpmanage.server.ApplyToUseServer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        //分页
        PageHelper.startPage(apply.getPageNum(),10);
        List<Apply> list = aserver.getAllApplyForWhoapply(apply);
        PageInfo pageInfo=new PageInfo(list,5);
        if(list==null){
            return Msg.fail().add("ApplysByUser",null);
        }else{
            return  Msg.success().add("ApplysByUser",pageInfo);
        }
    }
    @RequestMapping("/getAllApplyByWhoApplyed")
    @ResponseBody
    public Msg getAllApplyByWhoApplyed(@RequestBody Apply apply, HttpSession session){
        /*
        * 需要被申请者账号:Apply whoapplyed参数
        * */
        PageHelper.startPage(apply.getPageNum(),10);
        List<Apply> list = aserver.getAllApplyForWhoapplyed(apply);
        PageInfo pageInfo=new PageInfo(list,5);
        if(list==null){
            return Msg.fail().add("ApplysByMaster",null);
        }else{
            return  Msg.success().add("ApplysByMaster",pageInfo);
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

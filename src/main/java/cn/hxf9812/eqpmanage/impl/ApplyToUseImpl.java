package cn.hxf9812.eqpmanage.impl;

import cn.hxf9812.eqpmanage.dao.ApplyToUseMapper;
import cn.hxf9812.eqpmanage.dao.EqpMapper;
import cn.hxf9812.eqpmanage.pojo.Apply;
import cn.hxf9812.eqpmanage.pojo.Eqp;
import cn.hxf9812.eqpmanage.pojo.Msg;
import cn.hxf9812.eqpmanage.server.ApplyToUseServer;
import cn.hxf9812.eqpmanage.server.EqpServer;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@SuppressWarnings("all")
@Service("ApplyToUseServer")
public class ApplyToUseImpl implements ApplyToUseServer {

    @Autowired
    private ApplyToUseMapper ampper;
    @Autowired
    private EqpMapper eqpMapper;
    @Autowired
    private EqpServer eqpServer;


    @Override
    public boolean addApply(Apply apply) {
        //申请人为空则添加失败
        if (apply.getWhoapply()==null){
            return false;
        }
        //   没有传入申请机器表示申请失败
        String applyeqp = apply.getApplyeqp()+"";
        if("".equals(applyeqp)||applyeqp==null){
            return false;
        }
        //查询机器是否存在
        Eqp eqp = eqpServer.getEqpById(apply.getApplyeqp());
        if(eqp==null){
            //设备不存在，添加失败
            return false;
        }else{
                if(ampper.addApply(apply)>0){
                    return true;
                }else{
                    return false;
                }
        }
    }


    @Override
    public List<Apply> getAllApplyForWhoapply(Apply apply) {
        //申请人为空则添加失败
        if(apply.getWhoapply()==null){
            return null;
        }
        List<Apply> list = ampper.getAllApplyForWhoapply(apply);
        return list;
    }


    @Override
    public List<Apply> getAllApplyForWhoapplyed(Apply apply) {
        //申请人为空则添加失败
        if(apply.getWhoapplyed()==null){
            return null;
        }
        List<Apply> list = ampper.getAllApplyForWhoapplyed(apply);
        return list;
    }

    @Override
    public boolean setIsdealed(Apply apply) {
        //判断必须的参数是否为空
        String id = apply.getId()+"";
        if("".equals(id)||id==null){
            return false;
        }
        String isdealed = apply.getIsdealed()+"";
        if("".equals(isdealed)||isdealed==null){
            return false;
        }
        String applyeqp = apply.getApplyeqp()+"";
        if("".equals(applyeqp)||applyeqp==null){
            return false;
        }
        /**
         * 根据
         */
        Eqp eqp = eqpServer.getEqpById(apply.getApplyeqp());
        if(eqp==null) {
            //设备不存在，处理失败
            return false;
        }else{
            //设备存在，修改设备的状态码
            if(apply.getIsdealed()==1){
                //修改请求结果
                if(ampper.modifyApply(apply)>0){
                    //同意使用，把机器改为繁忙
                    eqp.setUser(apply.getWhoapply());
                    eqpMapper.modifyUser(eqp);
                    return true;
                }else{
                    return false;
                }
            }else{
                //不同意使用
                //修改请求结果
                if(ampper.modifyApply(apply)>0){
                    return true;
                }else{
                    return false;
                }
            }
        }
    }
}

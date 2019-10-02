package cn.hxf9812.eqpmanage.impl;

import cn.hxf9812.eqpmanage.dao.ApplyToUseMapper;
import cn.hxf9812.eqpmanage.pojo.Apply;
import cn.hxf9812.eqpmanage.pojo.Msg;
import cn.hxf9812.eqpmanage.server.ApplyToUseServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@SuppressWarnings("all")
@Service("ApplyToUseServer")
public class ApplyToUseImpl implements ApplyToUseServer {

    @Autowired
    private ApplyToUseMapper ampper;

    @Override
    public boolean addApply(Apply apply) {
        //申请人为空则添加失败
        if (apply.getWhoapply()==null){
            return  false;
        }
        if(ampper.addApply(apply)>0){
            return true;
        }else{
            return  false;
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
}

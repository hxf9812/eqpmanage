package cn.hxf9812.eqpmanage.impl;

import cn.hxf9812.eqpmanage.dao.ApplyToUseMapper;
import cn.hxf9812.eqpmanage.dao.EqpMapper;
import cn.hxf9812.eqpmanage.pojo.Apply;
import cn.hxf9812.eqpmanage.pojo.Eqp;
import cn.hxf9812.eqpmanage.pojo.Msg;
import cn.hxf9812.eqpmanage.server.ApplyToUseServer;
import cn.hxf9812.eqpmanage.server.EqpServer;
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
    public Msg setIsdealed(Apply apply) {
        //判断必须的参数是否为空
        String id = apply.getId()+"";
        if("".equals(id)||id==null){

            return Msg.fail("被需要的参数为空");
        }
        String isdealed = apply.getIsdealed()+"";
        if("".equals(isdealed)||isdealed==null){
            return Msg.fail("被需要的参数为空");
        }
        String applyeqp = apply.getApplyeqp()+"";
        if("".equals(applyeqp)||applyeqp==null){
            return Msg.fail("被需要的参数为空");
        }
        /**
         * 根据
         */
        Eqp eqp = eqpServer.getEqpById(apply.getApplyeqp());
        if(eqp==null) {
            //设备不存在，处理失败
            return Msg.fail("设备不存在，处理失败！");
        }else{
            //设备存在，修改设备的状态码
            if(apply.getIsdealed()==1){
                //修改请求结果
                if(ampper.modifyApply(apply)>0){
                    //同意使用，把机器改为繁忙
                    eqp.setUser(apply.getWhoapply());
                    eqpMapper.modifyUser(eqp);
                    return Msg.success("同意申请成功！");
                }else{
                    return Msg.fail("同意申请失败！");
                }
            }else{
                //不同意使用
                //修改请求结果
                if(ampper.modifyApply(apply)>0){
                    return Msg.success("拒绝申请成功！");
                }else{
                    return Msg.fail("拒绝申请失败！");
                }
            }
        }
    }


    @Override
    public void returnEqp(int eqp_id,int apply_id) {
//        // 创建一个定长线程池，支持定时及周期性任务执行
//        ScheduledExecutorService scheduledExecutorService =
//                Executors.newScheduledThreadPool(1);
//        // 建立一个延时任务，10秒钟之后执行
//       scheduledExecutorService.schedule(new ReturnEqp(),10,TimeUnit.SECONDS);
//       scheduledExecutorService.shutdown();
    }

    @Override
    public boolean deleteApplyById(int id) {

        if(ampper.deleteApplyById(id)>0)
            return true;
        else return false;

    }
}

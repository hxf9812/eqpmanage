package cn.hxf9812.eqpmanage;


import cn.hxf9812.eqpmanage.dao.ApplyToUseMapper;
import cn.hxf9812.eqpmanage.dao.EqpMapper;
import cn.hxf9812.eqpmanage.pojo.Apply;
import cn.hxf9812.eqpmanage.server.ApplyToUseServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.*;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("all")
public class ApplyTest {
    @Autowired
    ApplicationContext applicationContext=null;
    @Autowired
    ApplyToUseServer aServer;

    @Autowired
    ApplyToUseMapper mapper;
    @Autowired
    EqpMapper eqpMapper;

    /**
     * 发送请求测试
     */
    @Test
    public void testAdd(){
        Apply apply=new Apply();
        apply.setWhoapply("1353328145");
        apply.setWhoapplyed("123456789");
        apply.setApplyeqp(1);
        apply.setIsdealed(0);
        System.out.println(aServer.addApply(apply));
    }

    /**
     * 处理请求测试
     */
    @Test
    public void testSetIsdealed(){
        Apply apply=new Apply();
        apply.setId(4);
        apply.setWhoapply("666");//申请人
        apply.setIsdealed(1);//同意
        apply.setApplyeqp(10);//申请的设备
        System.out.println(aServer.setIsdealed(apply));
    }




    @Test
    public void testSelectForwhoapply(){
        Apply apply=new Apply();
        apply.setWhoapply("1353328145");
        List<Apply> all = aServer.getAllApplyForWhoapply(apply);
        if (all==null){
            System.out.println("集合为空");
        }else{
            for (Apply apply1 : all) {
                System.out.println("每个请求");
                System.out.println(apply1);
                System.out.println(apply1.getApplyUser());
                System.out.println(apply1.getApplyedUser());
                System.out.println(apply1.getApplyedEqp());
            }
        }

    }
    @Test
    public void testSelectForwhoapplyed(){
        Apply apply=new Apply();
        apply.setWhoapplyed("123456789");
        List<Apply> all = aServer.getAllApplyForWhoapplyed(apply);
        if (all==null){
            System.out.println("集合为空");
        }else{
            for (Apply apply1 : all) {
                System.out.println("每个请求");
                System.out.println(apply1);
                System.out.println(apply1.getApplyUser());
                System.out.println(apply1.getApplyedUser());
                System.out.println(apply1.getApplyedEqp());
            }
        }
    }
    @Test
    public void test(){
        aServer.returnEqp(1,8);
    }
}

package cn.hxf9812.eqpmanage;


import cn.hxf9812.eqpmanage.dao.EqpLogMapper;
import cn.hxf9812.eqpmanage.pojo.EqpLog;
import cn.hxf9812.eqpmanage.server.EqpLogServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;

@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("all")
public class logTest {
    @Autowired
    EqpLogMapper eqpLogMapper;
    @Autowired
    EqpLogServer eqpLogServer;
    @Test
    public void testSelectAndAdd(){
        EqpLog eqpLog =new EqpLog();
        eqpLog.setEqpid(100);
        eqpLog.setCause("111");
        eqpLog.setDamagedate(new Date());
        eqpLog.setDamagetime(1000);
        eqpLog.setIsrepaired(1);
        System.out.println(eqpLogServer.addEqpLog(eqpLog));
        System.out.println(eqpLogServer.getAllEqpLog());
    }
    @Test
    public void testdelete(){
        EqpLog eqpLog=new EqpLog();
        eqpLog.setId(4);
        System.out.println(eqpLogServer.deleteEqpLogById(eqpLog));
    }
    @Test
    public void testupdateEqpLogDamagetimeById(){
//        System.out.println(  eqpLogServer.updateEqpLogDamagetimeById(1));
        System.out.println(eqpLogServer.updateRepairedById(2,1));
    }
    @Test
    public void testselect(){
        System.out.println( eqpLogMapper.getEqpLogByEqpid(3));
        System.out.println(eqpLogServer.getEqpLogByEqpidAndTimes(0));
    }
}

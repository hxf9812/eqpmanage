package cn.hxf9812.eqpmanage;


import cn.hxf9812.eqpmanage.dao.EqpLogMapper;
import cn.hxf9812.eqpmanage.pojo.EqpLog;
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
    @Test
    public void testSelectAndAdd(){
        EqpLog eqpLog =new EqpLog();
        eqpLog.setEqpid(100);
        eqpLog.setCause("111");
        eqpLog.setDamagedate(new Date());
        eqpLog.setDamagetime(1000);
        eqpLog.setIsrepaired(1);
        System.out.println(eqpLogMapper.addEqpLog(eqpLog));
        System.out.println(        eqpLogMapper.getAllEqpLog()
        );
    }
    @Test
    public void testdelete(){
        System.out.println(eqpLogMapper.deleteEqpLogById(3));
    }
    @Test
    public void test(){

    }
}

package cn.hxf9812.eqpmanage;


import cn.hxf9812.eqpmanage.dao.UserMapper;
import cn.hxf9812.eqpmanage.pojo.Eqp;
import cn.hxf9812.eqpmanage.pojo.Msg;
import cn.hxf9812.eqpmanage.pojo.User;
import cn.hxf9812.eqpmanage.server.EqpServer;
import cn.hxf9812.eqpmanage.server.UserServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("all")
public class EqpTest {
    @Autowired
    ApplicationContext applicationContext=null;
    @Autowired
    EqpServer eqpServer=null;
    @Autowired
    UserServer userServer=null;

    @Test
    public void testFindAll(){
        List<Eqp> allEqp = eqpServer.getAllEqp();
        Msg eqpList=null;
        if (allEqp!=null){
             eqpList= Msg.success().add("EqpList", allEqp);
        }else{
              eqpList=Msg.fail().add("EqpList",null);
        }
        for (Eqp eqp : allEqp) {
            System.out.println("实验室设备信息:");
            System.out.println((List<Eqp>)eqpList.getExtend().get("EqpList"));
            System.out.println(eqpList.getMsg());
            System.out.println(eqpList.isFlag());
        }
    }
    @Test
    public void testgetEqpById(){
        Eqp eqpById = eqpServer.getEqpById(2);
        System.out.println(eqpById);
    }
    @Test
    public void testUpadte(){
        Eqp eqp=new Eqp();
        eqp.setId(2);
        eqp.setStatus(1);
        System.out.println(eqpServer.modifyEqp(eqp));
    }
    @Test
    public void testadd(){
        Eqp eqp=new Eqp();
        eqp.setMaster("123456789");
        eqp.setIndate(new Date());
        eqp.setUser("987654321");
        eqp.setStatus(0);
        eqp.setBelong("网络工程实验室");
        System.out.println(eqpServer.addEqp(eqp));
    }
}

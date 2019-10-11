package cn.hxf9812.eqpmanage;


import cn.hxf9812.eqpmanage.dao.DeclareMapper;
import cn.hxf9812.eqpmanage.pojo.Declare;
import cn.hxf9812.eqpmanage.server.DeclareServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Date;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("all")
public class DeclareTest {
    @Autowired
    DeclareMapper declareMapper;

    @Autowired
    DeclareServer server;

    @Test
    public void testgetAll1(){
        Declare declare=new Declare();
        declare.setDeclarant("123456789");
        List<Declare> list = declareMapper.getDeclareByDeclarant(declare);
        for (Declare declare1 : list) {
            System.out.println("请求信息");
            System.out.println(declare1);
            System.out.println("申请人");
            System.out.println(declare1.getDeclarantUser());
            System.out.println("管理员");
            System.out.println(declare1.getMasterUser());
            System.out.println("设备");
            System.out.println(declare1.getEqp());

        }
    }

    @Test
    public void testgetAll2(){
        Declare declare=new Declare();
        declare.setMaster("123456789");
        List<Declare> list = declareMapper.getDeclareByMaster(declare);
        for (Declare declare1 : list) {
            System.out.println("请求信息");
            System.out.println(declare1);
            System.out.println("申请人");
            System.out.println(declare1.getDeclarantUser());
            System.out.println("管理员");
            System.out.println(declare1.getMasterUser());
            System.out.println("设备");
            System.out.println(declare1.getEqp());
        }
    }
    @Test
    public void testadd(){
        Declare declare=new Declare();
        declare.setDeclarant("123456789");
        declare.setMaster("1353328145");
        declare.setContent("我给砸了");
        declare.setDecalredate(new Date());
        declare.setEqpid(4);
        declare.setDealstatus(0);
        System.out.println( server.sendDeclare(declare));

    }
    @Test
    public void testentrue(){
        Declare declare=new Declare();
        declare.setId(3);
        System.out.println(server.declareEnsure(declare));
    }
    @Test
    public void testhandle(){
        Declare declare=new Declare();
        declare.setId(3);
        System.out.println(server.handleDeclare(declare));
    }
    @Test
    public void testselect(){
        System.out.println(  declareMapper.getDeclareByEqpid(1));
        System.out.println(  declareMapper.getDeclareById(1));

    }
    @Test
    public void testdelete(){
        System.out.println(declareMapper.deleteById(3));

    }
}

package cn.hxf9812.eqpmanage;


import cn.hxf9812.eqpmanage.dao.UserMapper;
import cn.hxf9812.eqpmanage.pojo.Eqp;
import cn.hxf9812.eqpmanage.pojo.Msg;
import cn.hxf9812.eqpmanage.pojo.User;
import cn.hxf9812.eqpmanage.server.EqpServer;
import cn.hxf9812.eqpmanage.server.UserServer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
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
        PageHelper.startPage(1,5);
        List<Eqp> allEqp = eqpServer.getAllEqp();
        //        包装查询后的结果   把PageInfo交给页面   传入连续显示的页数
        PageInfo pageInfo=new PageInfo(allEqp,5);
        System.out.println(pageInfo);
    }
    @Test
    public void testgetEqpById(){
        Eqp eqpById = eqpServer.getEqpById(1);
        System.out.println(eqpById);
    }
    @Test
    public void testUpadte(){
        Eqp eqp=new Eqp();
        eqp.setId(2);
        eqp.setBelong("java实验室");
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
    @Test
    public void testDelete(){
        Eqp eqp =new Eqp();
        eqp.setId(2);
        if (eqpServer.deleteEqp(eqp)){
            System.out.println("删除成功！");
        }else{
            System.out.println("删除失败！");
        }
        eqp.setId(10);
        if (eqpServer.deleteEqp(eqp)){
            System.out.println("删除成功！");
        }else{
            System.out.println("删除失败！");
        }
    }
    @Test
    public void testGetById(){
        Eqp eqpById_withUser = eqpServer.getEqpById_WithUser(4);
        System.out.println(eqpById_withUser);
        Eqp eqpById = eqpServer.getEqpById(4);
        System.out.println(eqpById);
    }
    @Test
    public void testGetUserById(){
        User userByAccount = userServer.getUserByAccount("56441651451");
        System.out.println(userByAccount);
    }
}

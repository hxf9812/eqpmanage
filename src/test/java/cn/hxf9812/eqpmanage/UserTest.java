package cn.hxf9812.eqpmanage;

import cn.hxf9812.eqpmanage.dao.UserMapper;
import cn.hxf9812.eqpmanage.pojo.Msg;
import cn.hxf9812.eqpmanage.pojo.User;
import cn.hxf9812.eqpmanage.server.UserServer;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("all")
public class UserTest {

    @Autowired
    UserMapper userMapper;

    @Autowired
    UserServer userServer;

    @Test
    public void testSelect(){
        User user = userMapper.getUser("123456789", "123456789");
        System.out.println(user);
        System.out.println("--------------------------");
        List<User> allUser = userMapper.getAllUser();
        for (User user1 : allUser) {
            System.out.println(user1);
        }
        System.out.println("-------------------------");
        User userByAccount = userMapper.getUserByAccount("123456789");
        System.out.println(userByAccount);
    }

    @Test
    public void testAddUser(){
        User user =new User();
        user.setAccount("123456789");
        user.setName("123");
        user.setUserrank(3);
        user.setPassword("132");
        user.setPhone("123");
        Msg register = userServer.register(user);
        System.out.println(register);
    }
}

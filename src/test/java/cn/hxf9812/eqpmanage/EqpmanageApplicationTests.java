package cn.hxf9812.eqpmanage;

import cn.hxf9812.eqpmanage.dao.UserMapper;
import cn.hxf9812.eqpmanage.pojo.User;
import org.assertj.core.api.InputStreamAssert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Blob;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
@SuppressWarnings("all")
public class EqpmanageApplicationTests {

    @Autowired
    ApplicationContext applicationContext=null;
    @Autowired
    UserMapper UserMapper;
    @Test
    public void contextLoads() {
//login Test
//        List<User> l=UserMapper.loginVerify("666","666");
//        if(l.size()>0){
//            System.out.println("成功");
//        }else{
//            System.out.println("失败");
//        }
    }

}

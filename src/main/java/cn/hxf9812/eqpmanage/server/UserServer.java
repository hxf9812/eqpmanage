package cn.hxf9812.eqpmanage.server;

import cn.hxf9812.eqpmanage.pojo.Msg;
import cn.hxf9812.eqpmanage.pojo.User;

import java.util.List;

public interface  UserServer {

   User loginVerify(String account, String password);
   User getUserByAccount(String account);
   boolean modifyUserInfo(String password,String name,String phone,String account);
   List<User> getAllUser();
   List<User> getAllUserMatching(String matching);
   boolean deleteaUser(String account);
   boolean addaUser(String account,String password,int rank,String name,String phone);
   boolean modifyaUser(String password,String name,String phone,int userrank,String account);

   /**
    * 注册方法
    * @param user
    * @return
    */
   Msg register(User user);
}

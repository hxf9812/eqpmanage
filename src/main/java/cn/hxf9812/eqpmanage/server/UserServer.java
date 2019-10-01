package cn.hxf9812.eqpmanage.server;

import cn.hxf9812.eqpmanage.pojo.User;

import java.util.List;

public interface  UserServer {
   User loginVerify(String account, String password);
   User getUserByAccount(String account);
   boolean modifyUserInfo(String password,String name,int phone,String account);
   List<User> getAllUser();
   boolean deleteaUser(String account);
   boolean addaUser(String account,String password,int rank,String name,int phone);
   boolean modifyaUser(String password,String name,int phone,int userrank,String account);
}

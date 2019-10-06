package cn.hxf9812.eqpmanage.impl;

import cn.hxf9812.eqpmanage.dao.UserMapper;
import cn.hxf9812.eqpmanage.pojo.Msg;
import cn.hxf9812.eqpmanage.pojo.User;
import cn.hxf9812.eqpmanage.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@SuppressWarnings("all")
@Service("UserServer")
public class UserServerImpl implements UserServer {

    @Autowired
    UserMapper mapper=null;
    @Override
    public User loginVerify(String account, String password) {
        if(mapper==null) return null;
        User user= mapper.getUser(account,password);
        return user;
    }

    @Override
    public List<User> getAllUser() {
        if(mapper==null) return null;

        List<User> list = mapper.getAllUser();
        return list;
    }

    @Override
    public User getUserByAccount(String account) {
        if(mapper==null) return null;
        User user = mapper.getUserByAccount(account);
        return user;
    }

    @Override
    public boolean modifyUserInfo( String password, String name, String phone,String account) {
        if(mapper==null) return false;

        if(mapper.modifyUserInfo(password,name,phone,account)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean deleteaUser(String account) {
        if(mapper==null) return false;
        if(mapper.deleteaUser(account)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean addaUser(String account, String password, int rank, String name, String phone) {
        if(mapper==null) return false;
        if(mapper.addaUser(account,password,rank,name,phone)>0){
            return true;
        }
        return false;
    }

    @Override
    public boolean modifyaUser(String password, String name, String phone, int userrank, String account) {
        if(mapper==null) return false;
        if(mapper.modifyaUser(password,name,phone,userrank,account)>0){
            return true;
        }
        return false;
    }


    @Override
    public Msg register(User user) {
        if (user.getAccount()==null||user.getName()==null||user.getPassword()==null|| user.getPhone()==null||(user.getUserrank()+"")==null){
            return Msg.fail("参数中有null，接收失败");
        }
        String userAccount = user.getAccount();
        //查找是否重复
        User userByAccount = mapper.getUserByAccount(userAccount);
        if(userByAccount==null){
            //用户不存在，可注册
            int i = mapper.addaUser(user.getAccount(), user.getPassword(), user.getUserrank(), user.getName(), user.getPhone());
            if(i>0){
                return Msg.success("注册成功！");
            }else{
                return Msg.fail("注册失败");
            }
        }else{
            //用户存在，返回错误信息
            return Msg.fail("该学号已存在，请重新输入");
        }
    }
}

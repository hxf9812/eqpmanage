package cn.hxf9812.eqpmanage.controller;

import cn.hxf9812.eqpmanage.pojo.Result;
import cn.hxf9812.eqpmanage.pojo.User;
import cn.hxf9812.eqpmanage.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Set;

@CrossOrigin
@Controller
public class UserController {

    private Result r=new Result();

    @Autowired
    UserServer server=null;
    @RequestMapping("/login")
    @ResponseBody
    public User login(@RequestBody User u){
        if(server==null) return null;
        User user=server.loginVerify(u.getAccount(),u.getPassword());
        return user;
    }
    @RequestMapping("/getAllUser")
    @ResponseBody
    public List<User> getAllUser(){
        if(server==null) return null;
        List<User> list=server.getAllUser();
        return list;
    }
    @RequestMapping("/getUserByAccount")
    @ResponseBody
    public User getUserById(@RequestBody User u){
        if(server==null) return null;
        User user=server.getUserByAccount(u.getAccount());
        return user;
    }
    @RequestMapping("/modifyUserInfo")
    @ResponseBody
    public Result modifyUserInfo(@RequestBody User u){
        if(server==null) return null;
        boolean result=server.modifyUserInfo(u.getPassword(),u.getName(),u.getPhone(),u.getAccount());
        if(result){
           r.setFlag(true);
        }else{
            r.setFlag(false);
        }
        return r;
    }
    @RequestMapping("/deleteaUser")
    @ResponseBody
    public Result deleteaUser(@RequestBody User u){
        if(server==null) return null;
        boolean result=server.deleteaUser(u.getAccount());
        if(result){
            r.setFlag(true);
        }else{
            r.setFlag(false);
        }
        return r;
    }
    @RequestMapping("/addaUser")
    @ResponseBody
    public Result addaUser(@RequestBody User u){
        if(server==null) return null;
        boolean result=server.addaUser(u.getAccount(),u.getPassword(),u.getUserrank(),u.getName(),u.getPhone());
        if(result){
            r.setFlag(true);
        }else{
            r.setFlag(false);
        }
        return r;
    }
    @RequestMapping("/modifyaUser")
    @ResponseBody
    public Result modifyaUser(@RequestBody User u){
        if(server==null) return null;
        boolean result=server.modifyaUser(u.getPassword(),u.getName(),u.getPhone(),u.getUserrank(),u.getAccount());
        if(result){
            r.setFlag(true);
        }else{
            r.setFlag(false);
        }
        return r;
    }
}

package cn.hxf9812.eqpmanage.controller;

import cn.hxf9812.eqpmanage.pojo.Msg;
import cn.hxf9812.eqpmanage.pojo.Page;
import cn.hxf9812.eqpmanage.pojo.Result;
import cn.hxf9812.eqpmanage.pojo.User;
import cn.hxf9812.eqpmanage.server.UserServer;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Set;

@CrossOrigin
@Controller
public class UserController {

    private Result r=new Result();

    @Autowired
    UserServer server=null;
    /**
     * 用户登录
     * @param u
     * @return user对象
     */
    @RequestMapping("/login")
    @ResponseBody
    public User login(HttpSession session, @RequestBody User u){
        if(server==null) return null;
        //用户登录
        User user=server.loginVerify(u.getAccount(),u.getPassword());
        session.setAttribute("user",user);
        return user;
    }

    /**
     * 获取所有用户
     * @return user集合
     */
    @RequestMapping("/getAllUser")
    @ResponseBody
    public Msg getAllUser(@RequestBody Page page){

        PageHelper.startPage(page.getPageNum(),10);
        List<User> list;
        // 模糊查询
        if(page.getMatching()==null || page.getMatching().equals("")) {
            list = server.getAllUser();
        }
        else{
            list = server.getAllUserMatching(page.getMatching());
        }
        PageInfo pageInfo=new PageInfo(list,5);
        if (pageInfo!=null){
            return Msg.success().add("eqpList",pageInfo);
        }else{
            return Msg.fail().add("eqpList",null);
        }
    }

    /**
     * 通过id获取user
     * @param u
     * @return
     */
    @RequestMapping("/getUserByAccount")
    @ResponseBody
    public User getUserById(@RequestBody User u){
        if(server==null) return null;
        User user=server.getUserByAccount(u.getAccount());
        return user;
    }

    /**
     *修改用户信息
     * @param u
     * @return
     */
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

    /**
     * 删除用户通过账号
     * @param u
     * @return
     */
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

    /**
     * 添加用户
     * @param u
     * @return
     */
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

    /**
     * 注册方法
     * @param u
     * @return
     */
    @RequestMapping("/register")
    @ResponseBody
    public Msg register(@RequestBody User u){
        return server.register(u);
    }
    /**
     *
     * @param u
     * @return
     */
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

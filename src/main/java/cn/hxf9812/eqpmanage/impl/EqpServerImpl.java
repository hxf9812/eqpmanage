package cn.hxf9812.eqpmanage.impl;

import cn.hxf9812.eqpmanage.dao.EqpMapper;
import cn.hxf9812.eqpmanage.pojo.Eqp;
import cn.hxf9812.eqpmanage.pojo.User;
import cn.hxf9812.eqpmanage.server.EqpServer;
import cn.hxf9812.eqpmanage.server.UserServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@SuppressWarnings("all")
@Service("EqpServer")
public class EqpServerImpl implements EqpServer {

    @Autowired
    EqpMapper mapper=null;
    
    @Autowired
    UserServer userServer=null;

    /**
     * 获取所有设备数据，并存入user信息
     * @return
     */
    @Override
    public List<Eqp> getAllEqp() {
        List<Eqp> allEqp=null;
        //获取设备集合
        try{
            allEqp = mapper.getAllEqp();
        }catch (Exception e){
            return null;
        }
        //遍历集合设置user对象null
        for (Eqp eqp : allEqp) {
            User master = userServer.getUserByAccount(eqp.getMaster());
            User user=userServer.getUserByAccount(eqp.getUser());
            eqp.setMasterUser(master);
            eqp.setUserUser(user);
        }
        return allEqp;
    }

    /**
     * 修改用户
     * @param eqp
     * @return
     */
    @Override
    public boolean modifyEqp(Eqp eqp) {
        if (mapper==null){return false;}
        Eqp eqpById=null;
        //查询出该用户信息
        try{
            eqpById = mapper.getEqpById(eqp.getId());
        }catch (Exception e){
            return false;
        }
        if (eqpById==null){return false;}
        System.out.println(eqpById);
        //补全为null的空缺
        if(eqp.getMaster()==null){
            eqp.setMaster(eqpById.getMaster());
        }
        if(eqp.getIndate()==null){
            eqp.setIndate(eqpById.getIndate());
        }
        if(eqp.getUser()==null){
            eqp.setUser(eqpById.getUser());
        }
        String status=""+eqp.getStatus();
        if("".equals(status)||status==null){
            eqp.setStatus(eqpById.getStatus());
        }
        if(eqp.getBelong()==null){
            eqp.setBelong(eqpById.getBelong());
        }
        if(mapper.modifyUser(eqp)>0){
            return true;
        }else{
            return false;
        }
    }

    /**
     * 根据一个id查询设备
     * @param id
     * @return
     */
    @Override
    public Eqp getEqpById(int id) {
        Eqp eqpById = mapper.getEqpById(id);
        return eqpById;
    }
    /**
     * 添加一个设备
     */
    public boolean addEqp(Eqp eqp){
        if (mapper==null){return false;}
        //查询管理者是否存在，不存在返回异常
        User userByAccount =null;
        try{
           userByAccount= userServer.getUserByAccount(eqp.getMaster());
        }catch (Exception e){
            e.printStackTrace();
            userByAccount=null;
        }
        //为空返回false
        if (userByAccount==null){return false;}
        //贩毒案是否添加成功
        try{
            if(mapper.addEqp(eqp)>0){
                return true;
            }else{
                return false;
            }
        }catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }
}

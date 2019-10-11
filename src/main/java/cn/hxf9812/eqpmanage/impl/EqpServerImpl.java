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
            eqp.setMasterUser(master);
            //status为1表示正在被使用，则查询被使用者
            if (eqp.getUser()!=null){
                User user=userServer.getUserByAccount(eqp.getUser());
                eqp.setUserUser(user);
            }
        }
        return allEqp;
    }

    /**
     * 修改设备
     * @param eqp
     * @return
     */
    @Override
    public boolean modifyEqp(Eqp eqp) {
        if (mapper==null){return false;}
        //如果id不存在表示查询失败
        String id=""+eqp.getId();
        if("".equals(id)||id==null){return false;}
        Eqp eqpById=null;
        //查询出该用户信息
        eqpById = mapper.getEqpById(eqp.getId());
        //如果设备不存在表示查询失败
        if (eqpById==null){return false;}
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
        if (eqpById==null){return null;}
        return eqpById;
    }

    /**
     * 根据id获取设备并获取关联信息
     * @param id
     * @return
     */
    @Override
    public Eqp getEqpById_WithUser(int id) {
        Eqp eqpById=null;
        //根据id查询用户
        eqpById  = mapper.getEqpById(id);
        if (eqpById==null){return null;}
//        封装负责人和使用者
        User master = null;
        master  =  userServer.getUserByAccount(eqpById.getMaster());
        if (master!=null){   eqpById.setMasterUser(master);}
        //status为1表示正在被使用，则查询被使用者
        if (eqpById.getStatus()==1){
            User user=userServer.getUserByAccount(eqpById.getUser());
            eqpById.setUserUser(user);
        }
        return eqpById;
    }
    /**
     * 添加一个设备
     */
    public boolean addEqp(Eqp eqp){
        if (mapper==null){return false;}
        //查询管理者是否存在，不存在返回false
        User userByAccount = userServer.getUserByAccount(eqp.getMaster());
        //为空返回false
        if (userByAccount==null){return false;}
        //判断是否添加成功
        if(mapper.addEqp(eqp)>0){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public boolean deleteEqp(Eqp eqp) {
        if (mapper==null){return false;}
        //如果id不存在表示查询失败
        String id=""+eqp.getId();
        if("".equals(id)||id==null){return false;}
        Eqp eqpById=null;
        //查询出该用户信息
        eqpById = mapper.getEqpById(eqp.getId());
        //如果设备不存在表示查询失败
        if (eqpById==null){return false;}
        //判断是否添加成功

        if(mapper.deleteEqp(eqp)>0){
            return true;
        }else{
            return false;
        }

    }

    @Override
    public List<Eqp> getEqpByUser(String user) {
        if (mapper==null){return null;}
        if(user.equals("") || user==null){return null;}
        List<Eqp> list=mapper.getEqpByUser(user);
        for(Eqp eqp : list){
            User master=userServer.getUserByAccount(eqp.getMaster());
            eqp.setMasterUser(master);
        }
        return list;
    }
    /**
     * 归还设备
     */
    @Override
    public boolean returnEqp(int id) {
        if (mapper==null){return false;}
        if(mapper.returnEqp(id)>0) return true;
        else return false;
    }
}
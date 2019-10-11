package cn.hxf9812.eqpmanage.dao;

import cn.hxf9812.eqpmanage.pojo.Apply;
import cn.hxf9812.eqpmanage.pojo.Eqp;
import org.apache.ibatis.annotations.*;

import java.util.List;

/**
 * 这是一个实验室设备操作类
 */
public interface EqpMapper {
    /**
     * 获取所有设备
     */
    @Select("select * from eqp")
    List<Eqp> getAllEqp();

    /**
     * 根据id查询一个设备
     */
    @Select("select *from eqp where id = #{id}")
    Eqp getEqpById(int id);
    /**
     * 修改一个用户根据id
     */
    @Update("update eqp set master=#{master},indate=#{indate},user=#{user},status=#{status},belong=#{belong} where id =#{id}")
    int modifyUser (Eqp eqp);
    /**
     * 添加一个设备
     */
    @Insert("insert into eqp(master,indate,user,status,belong) values(#{master},#{indate},#{user},#{status},#{belong})")
    int addEqp(Eqp eqp);

    /**
     *根绝id删除一个设备
     */
    @Delete("Delete from eqp where id = #{id}")
    int deleteEqp(Eqp eqp);

    /**
     * 归还一个设备
     * @return
     */
    @Update("update eqp set user = null where id=#{id}")
    int returnEqp(int id);

}

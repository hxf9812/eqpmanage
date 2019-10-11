package cn.hxf9812.eqpmanage.dao;

import cn.hxf9812.eqpmanage.pojo.EqpLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * 这是一个维修日志信息操作类
 */
public interface EqpLogMapper {
    /**
     * 添加一个维修日志信息
     */
    @Insert("insert into eqplog (eqpid,damagedate,cause,damagetime,isrepaired) values" +
            " (#{eqpid},#{damagedate},#{cause},#{damagetime},#{isrepaired})")
    int addEqpLog(EqpLog eqpLog);
    /**
     * 删除一条日志信息
     */
    @Delete("Delete from eqplog where id = #{id}")
    int deleteEqpLogById(int id);
    /**
     * 获取所有日志信息
     */
    @Select("select * from eqplog")
    List<EqpLog> getAllEqpLog();



    /**
     * 修改日志的损坏次数
     */
    @Update("update eqplog set damagetime=#{damagetime} where id=#{id}")
    int updateDamagetimeById(EqpLog eqpLog);
    /**
     * 修改日志的损坏日期
     */
    @Update("update eqplog set damagedate=#{damagedate}  where id= #{id}")
   int updateDamagedateById(EqpLog eqpLog);
    /**
     * 修改日志是否被使用
     */
    @Update("update eqplog set isrepaired=#{isrepaired} where id =#{id}")
    int updateRepairedById(EqpLog eqpLog);



    /**
     * 根据id查询一个日志
     */
    @Select("select * from eqplog where id = #{id}")
    EqpLog getEqpLogById(int id);
    /**
     * 根据设备id：eqpid查询
     */
    @Select("select * from eqplog where eqpid = #{eqpid}")
    List<EqpLog> getEqpLogByEqpid(int eqpid);
}

package cn.hxf9812.eqpmanage.dao;

import cn.hxf9812.eqpmanage.pojo.EqpLog;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

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
}

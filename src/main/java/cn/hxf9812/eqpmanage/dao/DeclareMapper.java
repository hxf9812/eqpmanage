package cn.hxf9812.eqpmanage.dao;


import cn.hxf9812.eqpmanage.pojo.Declare;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 维修请求处理类
 */
public interface DeclareMapper {


    /**
     * 申请人的信息回显
     */
    @Select("select * from `declare` where declarant = #{declarant}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="eqpid",property="eqpid"),
            @Result(column="declarant",property="declarant"),
            @Result(column="master",property="master"),
            @Result(column="decalredate",property="decalredate"),
            @Result(column="content",property="content"),
            @Result(column="dealstatus",property="dealstatus"),
            @Result(column="eqpid",property="eqp",
                    one=@One(
                            select="cn.hxf9812.eqpmanage.dao.EqpMapper.getEqpById",
                            fetchType= FetchType.LAZY)),
            @Result(column="master",property="masterUser",
                    one=@One(
                            select="cn.hxf9812.eqpmanage.dao.UserMapper.getUserByAccount",
                            fetchType=FetchType.LAZY)),
            @Result(column="declarant",property="declarantUser",
                    one=@One(
                            select="cn.hxf9812.eqpmanage.dao.UserMapper.getUserByAccount",
                            fetchType=FetchType.LAZY))
    })
     List<Declare> getDeclareByDeclarant(Declare declare);
    /**
     * 管理员的信息回显
     */
    @Select("select * from `declare` where master = #{master}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="eqpid",property="eqpid"),
            @Result(column="declarant",property="declarant"),
            @Result(column="master",property="master"),
            @Result(column="decalredate",property="decalredate"),
            @Result(column="content",property="content"),
            @Result(column="dealstatus",property="dealstatus"),
            @Result(column="eqpid",property="eqp",
                    one=@One(
                            select="cn.hxf9812.eqpmanage.dao.EqpMapper.getEqpById",
                            fetchType= FetchType.LAZY)),
            @Result(column="master",property="masterUser",
                    one=@One(
                            select="cn.hxf9812.eqpmanage.dao.UserMapper.getUserByAccount",
                            fetchType=FetchType.LAZY)),
            @Result(column="declarant",property="declarantUser",
                    one=@One(
                            select="cn.hxf9812.eqpmanage.dao.UserMapper.getUserByAccount",
                            fetchType=FetchType.LAZY))
    })
     List<Declare> getDeclareByMaster(Declare declare);
    /**
     * 添加一条申请
     */
    @Insert("insert into `declare` (eqpid,declarant,master,decalredate,content,dealstatus)" +
            "values(#{eqpid},#{declarant},#{master},#{decalredate},#{content},#{dealstatus})")
     int addDeclare(Declare declare);
    /**
     * 处理一条申请
     */
    @Update("update `declare` set dealstatus=#{dealstatus} where id=#{id}")
    int handleDeclare(Declare declare);
    /**
     * 通过id查找一个请求
     */
    @Select("select * from `declare`  where id=#{ id }")
    Declare getDeclareById(int id);
    /**
     * 通过eqpid查找设备
     */
    @Select("select * from `declare`  where eqpid=#{ eqpid }")
     public  Declare getDeclareByEqpid(int eqpid);
     /**
     * 通过eqpid查找设备
     */
    @Delete("delete from `declare` where id=#{id}")
    int  deleteById(int id);
}

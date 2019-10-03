package cn.hxf9812.eqpmanage.dao;

import cn.hxf9812.eqpmanage.pojo.Apply;
import org.apache.ibatis.annotations.*;
import org.apache.ibatis.mapping.FetchType;

import java.util.List;

/**
 * 申请持久层接口
 */
public interface ApplyToUseMapper {
    /**
     * 发出一条申请
     * @return int影响行数
     */
    @Insert("insert into apply (whoapply,whoapplyed,applyeqp,isdealed) " +
            "values(#{whoapply},#{whoapplyed},#{applyeqp},#{isdealed})")
     int addApply(Apply apply);

    /**
     * 根据发出申请者账号查询所有申请
     * @return List<Apply>
     */
    @Select("select * from apply where whoapply = #{whoapply}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="whoapply",property="whoapply"),
            @Result(column="whoapplyed",property="whoapplyed"),
            @Result(column="applyeqp",property="applyeqp"),
            @Result(column="isdealed",property="isdealed"),
            @Result(column="applyeqp",property="applyedEqp",
                    one=@One(
                            select="cn.hxf9812.eqpmanage.dao.EqpMapper.getEqpById",
                            fetchType=FetchType.LAZY)), //fetchType的值表示立即加载，懒加载值为LAZY
            @Result(column="whoapply",property="applyUser",
                    one=@One(
                            select="cn.hxf9812.eqpmanage.dao.UserMapper.getUserByAccount",
                            fetchType=FetchType.LAZY)),
            @Result(column="whoapplyed",property="applyedUser",
                    one=@One(
                            select="cn.hxf9812.eqpmanage.dao.UserMapper.getUserByAccount",
                            fetchType=FetchType.LAZY))
    })
    List<Apply> getAllApplyForWhoapply(Apply apply);

    /**
     * 根据处理人查询
     * @param apply
     * @return
     */
    @Select("select * from apply where whoapplyed = #{whoapplyed}")
    @Results({
            @Result(id=true,column="id",property="id"),
            @Result(column="whoapply",property="whoapply"),
            @Result(column="whoapplyed",property="whoapplyed"),
            @Result(column="applyeqp",property="applyeqp"),
            @Result(column="isdealed",property="isdealed"),
            @Result(column="applyeqp",property="applyedEqp",
                    one=@One(
                            select="cn.hxf9812.eqpmanage.dao.EqpMapper.getEqpById",
                            fetchType=FetchType.LAZY)), //fetchType的值表示立即加载，懒加载值为LAZY
            @Result(column="whoapply",property="applyUser",
                    one=@One(
                            select="cn.hxf9812.eqpmanage.dao.UserMapper.getUserByAccount",
                            fetchType=FetchType.LAZY)),
            @Result(column="whoapplyed",property="applyedUser",
                    one=@One(
                            select="cn.hxf9812.eqpmanage.dao.UserMapper.getUserByAccount",
                            fetchType=FetchType.LAZY))
    })
    List<Apply> getAllApplyForWhoapplyed(Apply apply);

    /**
     * 修改
     * @return
     */
    @Update("update apply set isdealed = #{isdealed} where id=#{id}")
    int modifyApply(Apply apply);
}

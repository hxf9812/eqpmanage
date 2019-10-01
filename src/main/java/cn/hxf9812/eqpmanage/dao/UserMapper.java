package cn.hxf9812.eqpmanage.dao;

import cn.hxf9812.eqpmanage.pojo.User;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.io.InputStream;
import java.util.List;


public interface UserMapper {

    @Select("select * from user where account=#{account} and password=#{password} ")
    User getUser(String account, String password);

    @Select("select * from user where account = #{account}")
    User getUserByAccount(String account);

    @Update("update user set password=#{password},name=#{name},phone=#{phone} where account=#{account} ")
    int modifyUserInfo(String password,String name,int phone,String account);

    @Select("select * from user")
    List<User> getAllUser();

    @Delete("delete from user where account=#{account}")
    int deleteaUser(String account);

    @Insert("insert into user(account,password,userrank,name,phone) values(#{account},#{password},#{userrank},#{name},#{phone})")
    int addaUser(String account,String password,int userrank,String name,int phone);

    @Update("update user set password=#{password},name=#{name},phone=#{phone},userrank=#{userrank} where account=#{account} ")
    int modifyaUser(String password,String name,int phone,int userrank,String account);
}

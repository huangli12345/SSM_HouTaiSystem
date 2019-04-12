package cn.hl.ssm.dao;

import cn.hl.ssm.domain.UserInfo;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IUserDao {

    //查询某个用户
    @Select("select * from users where username=#{username}")
    @Results({
            @Result(id=true,column ="id",property = "id"),
            @Result(column ="username",property = "username"),
            @Result(column ="email",property = "email"),
            @Result(column ="password",property = "password"),
            @Result(column ="phoneNum",property = "phoneNum"),
            @Result(column ="status",property = "status"),
            @Result(column ="id",property = "roles",javaType = java.util.List.class,
            many = @Many(select = "cn.hl.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    public UserInfo findByUsername(String username) throws Exception;

    //查询所有用户
    @Select("select * from users")
    List<UserInfo> findAll()throws Exception;

    //保存一个用户
    @Insert("insert into users (email,username,password,phoneNum,status) values (#{email},#{username},#{password},#{phoneNum},#{status})")
    void save(UserInfo userInfo)throws Exception;

    //查询用户详情
    @Select("select * from users where id = #{id}")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "username",property = "username"),
            @Result(column = "email",property = "email"),
            @Result(column = "password",property = "password"),
            @Result(column = "phoneNum",property = "phoneNum"),
            @Result(column ="status",property = "status"),
            @Result(column = "id",property = "roles",javaType = java.util.List.class,
            many = @Many(select = "cn.hl.ssm.dao.IRoleDao.findRoleByUserId"))
    })
    UserInfo findById(String id)throws Exception;

    @Insert("insert into users_role(userId,roleId) values(#{userId},#{roleId})")
    public void addRoleToUser(@Param("userId") String userId, @Param("roleId") String roleId);

}

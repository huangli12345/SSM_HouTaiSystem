package cn.hl.ssm.dao;

import cn.hl.ssm.domain.Role;
import org.apache.ibatis.annotations.*;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.tools.JavaCompiler;
import java.util.List;

public interface IRoleDao {

    //根据id查询出所有对应角色
    @Select("select * from role where id in (select roleId from users_role where userId=#{userId})")
    @Results({
            @Result(id=true,column = "id",property = "id"),
            @Result(column = "roleName",property = "roleName"),
            @Result(column = "roleDesc",property = "roleDesc"),
            @Result(column = "id",property = "permissions",javaType = java.util.List.class
                    ,many = @Many(select = "cn.hl.ssm.dao.IPermissionDao.findPermissionByRoleId"))
    })
    public List<Role> findRoleByUserId(String userId) throws Exception;

    //查询所有角色
    @Select("select * from role")
     List<Role> findAll()throws Exception;

    //新建一个角色
    @Insert("insert into role (roleName,roleDesc) values( #{roleName},#{roleDesc})")
    void save(Role role)throws Exception;

    //查询一个角色的详情
    @Select("select * from role where id=#{id}")
    @Results({
            @Result(id=true,column="id",property = "id"),
            @Result(column="roleName",property = "roleName"),
            @Result(column="roleDesc",property = "roleDesc"),
            @Result(column="id",property = "permissions",javaType = java.util.List.class
            ,many = @Many(select = "cn.hl.ssm.dao.IPermissionDao.findPermissionByRoleId")),
    })
    Role findById(String id)throws Exception;

    //删除角色
    @Delete("delete from users_role where roleId=#{roleId}")
    void deleteFromUser_RoleByRoleId(String roleId)throws Exception;
    //删除角色
    @Delete("delete from role_permission where roleId=#{roleId}")
    void deleteFromRole_PermissionByRoleId(String roleId)throws Exception;
    //删除角色
    @Delete("delete from role where id=#{roleId}")
    void deleteRoleById(String roleId)throws Exception;
    //查询所有其他角色
    @Select("select * from role where id not in (select roleId from users_role where userId=#{id})")
    List<Role> findOtherRole(String id)throws Exception;

    @Insert("insert into role_permission values (#{permissionId},#{roleId})")
    void addPermissionToRole(@Param("roleId") String roleId, @Param("permissionId")String permissionId)throws Exception;
}

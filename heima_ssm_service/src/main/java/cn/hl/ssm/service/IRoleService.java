package cn.hl.ssm.service;

import cn.hl.ssm.domain.Role;

import java.util.List;

public interface IRoleService {
    //查询所有角色
    List<Role> findAll() throws Exception;

    //新增一个角色
    void save(Role role)throws Exception;

    //查询某个角色的详情
    Role findById(String id)throws Exception;

    //删除角色
    void deleteRoleById(String roleId)throws Exception;

    //查询其他所有角色
    List<Role> findOtherRole(String id)throws Exception;

    //给一个角色添加权限
    void addPermissionToRole(String roleId, String[] permissionIds)throws Exception;
}

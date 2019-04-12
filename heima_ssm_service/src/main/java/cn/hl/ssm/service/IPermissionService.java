package cn.hl.ssm.service;

import cn.hl.ssm.domain.Permission;

import java.util.List;

public interface IPermissionService {
    //查询所有权限
    List<Permission> findAll()throws Exception;

    //新增一个权限
    void save(Permission p)throws Exception;

    //查询一个权限的详情
    Permission findById(String id)throws Exception;

    //删除一个权限
    void deleteById(String id)throws Exception;

    //查询其他所有权限
    List<Permission> findOtherPermission(String id)throws Exception;

}

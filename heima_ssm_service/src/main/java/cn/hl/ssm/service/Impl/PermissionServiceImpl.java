package cn.hl.ssm.service.Impl;

import cn.hl.ssm.dao.IPermissionDao;
import cn.hl.ssm.domain.Permission;
import cn.hl.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("permissionService")
@Transactional
public class PermissionServiceImpl implements IPermissionService {
    @Autowired
    private IPermissionDao permissionDao;

    @Override
    public List<Permission> findAll() throws Exception{
        return permissionDao.findAll();
    }

    @Override
    public void save(Permission p) throws Exception {
        permissionDao.save(p);
    }

    @Override
    public Permission findById(String id) throws Exception {
        return permissionDao.findById(id);
    }

    @Override
    public void deleteById(String id) throws Exception {
        permissionDao.deleteFromRole_PermissionByPermissionId( id);
        permissionDao.deleteById(id);
    }

    @Override
    public List<Permission> findOtherPermission(String id) throws Exception {
        List<Permission> permissionList=permissionDao.findOtherPermission(id);
        return permissionList;
    }

}

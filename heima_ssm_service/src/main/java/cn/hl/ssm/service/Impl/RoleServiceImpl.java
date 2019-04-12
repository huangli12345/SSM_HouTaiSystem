package cn.hl.ssm.service.Impl;

import cn.hl.ssm.dao.IRoleDao;
import cn.hl.ssm.domain.Role;
import cn.hl.ssm.service.IRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("roleService")
@Transactional
public class RoleServiceImpl implements IRoleService {
    @Autowired
    private IRoleDao roleDao;
    @Override
    public List<Role> findAll() throws Exception{
        List<Role> roles=roleDao.findAll();
        return roles;
    }

    @Override
    public void save(Role role)throws Exception {
        roleDao.save(role);
    }

    @Override
    public Role findById(String id) throws Exception {
        return roleDao.findById(id);
    }

    @Override
    public void deleteRoleById(String roleId)throws Exception {
        //从user_role表中删除
        roleDao.deleteFromUser_RoleByRoleId(roleId);
        //从role_permission表中删除
        roleDao.deleteFromRole_PermissionByRoleId(roleId);
        //从role表中删除
        roleDao.deleteRoleById(roleId);
    }

    @Override
    public List<Role> findOtherRole(String id)throws Exception {
        List<Role> roleList=roleDao.findOtherRole(id);
        return roleList;
    }

    @Override
    public void addPermissionToRole(String roleId, String[] permissionIds) throws Exception {

        for (String permissionId : permissionIds) {
            roleDao.addPermissionToRole(roleId,permissionId);
        }
    }

}

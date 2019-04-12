package cn.hl.ssm.service.Impl;

import cn.hl.ssm.dao.IUserDao;
import cn.hl.ssm.domain.Role;
import cn.hl.ssm.domain.UserInfo;
import cn.hl.ssm.service.IUserService;
import cn.hl.ssm.utils.BCryptPasswordEncoderUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserDao userDao;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    //spring-security框架必须要重写的方法
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo=null;
        try {
            userInfo=userDao.findByUsername(username);
        } catch (Exception e) {
            e.printStackTrace();
        }
//        User user=new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),getAuthority(userInfo.getRoles()));

//        User user =new User(userInfo.getUsername(),"{noop}"+userInfo.getPassword(),
//                userInfo.getStatus()==0?false:true,
//                true,true,
//                true,getAuthority(userInfo.getRoles()));

        User user =new User(userInfo.getUsername(),userInfo.getPassword(),
                userInfo.getStatus()==0?false:true,
                true,true,
                true,getAuthority(userInfo.getRoles()));

        return user;
    }

    public List<SimpleGrantedAuthority> getAuthority(List<Role> roles){

        List<SimpleGrantedAuthority> list=new ArrayList<>();
        for (Role role : roles) {
            list.add(new SimpleGrantedAuthority("ROLE_"+role.getRoleName()));
        }

        return list;
    }

    //查询所有用户
    @Override
    public List<UserInfo> findAll()throws Exception {
        return userDao.findAll();
    }

    //保存一个用户
    @Override
    public void save(UserInfo userInfo) throws Exception{
        userInfo.setPassword(bCryptPasswordEncoder.encode(userInfo.getPassword()));
        userDao.save(userInfo);
    }

    //查看用户详情
    @Override
    public UserInfo findById(String id) throws Exception{
        UserInfo userInfo=userDao.findById(id);
        return userInfo;
    }

    @Override
    public void addRoleToUser(String userId, String[] ids) throws Exception {
        for (String id : ids) {
            userDao.addRoleToUser(userId,id);
        }
    }
}

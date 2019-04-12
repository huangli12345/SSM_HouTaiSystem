package cn.hl.ssm.controller;

import cn.hl.ssm.dao.IPermissionDao;
import cn.hl.ssm.domain.Permission;
import cn.hl.ssm.service.IPermissionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/permission")
public class PermissionController {

    @Autowired
    private IPermissionService permissionService;

    //查询所有权限
    @RequestMapping("/findAll.do")
    public ModelAndView findAll()throws Exception{
        List<Permission> permissionList=permissionService.findAll();
        ModelAndView mv=new ModelAndView();
        mv.addObject("permissionList",permissionList);
        mv.setViewName("permission-list");
        return mv;
    }

    //新增权限
    @RequestMapping("/save.do")
    public String save(Permission p)throws Exception{
        permissionService.save(p);
        return "redirect:findAll.do";
    }

    //删除一个权限
    @RequestMapping("deletePermission")
    public String deletePermission(@RequestParam(name="id",required = true) String id)throws Exception{
        permissionService.deleteById(id);
        return "redirect:findAll.do";
    }

}

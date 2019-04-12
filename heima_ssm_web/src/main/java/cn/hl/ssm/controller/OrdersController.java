package cn.hl.ssm.controller;

import cn.hl.ssm.domain.Orders;
import cn.hl.ssm.service.IOrdersService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/orders")
public class OrdersController {

    @Autowired
    private IOrdersService ordersService;

    //查询所有订单--未分页
//    @RequestMapping("/findAll.do")
//    public ModelAndView findAll() throws Exception {
//        List<Orders> ordersList = ordersService.findAll();
//
//        ModelAndView mv=new ModelAndView();
//        mv.addObject("ordersList",ordersList);
//        mv.setViewName("orders-list");
//        return mv;
//    }


    //查询所有订单--分页
    @RequestMapping("/findAll.do")
    @Secured("ROLE_ADMIN")
    public ModelAndView findAll(@RequestParam(name="page",required = true,defaultValue = "1") Integer page,@RequestParam(name="size",required = true,defaultValue = "4") int size)throws Exception{

        List<Orders> ordersList=ordersService.findAll(page,size);

        //把结果集及分页信息？存入PageInfo类
        PageInfo pageInfo=new PageInfo(ordersList);

        //把当前页的PageBean对象modelandview
        ModelAndView mv=new ModelAndView();
        mv.addObject("pageInfo",pageInfo);
        mv.setViewName("orders-page-list");

        return mv;
    }

    //查询订单详情
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam(name="id" ,required=true)String ordersId) throws Exception {
        //查询订单详情信息
        Orders orders=ordersService.findById(ordersId);

        //设置模型和视图
        ModelAndView mv=new ModelAndView();
        mv.addObject("orders",orders);
        mv.setViewName("orders-show");

        return mv;
    }

}

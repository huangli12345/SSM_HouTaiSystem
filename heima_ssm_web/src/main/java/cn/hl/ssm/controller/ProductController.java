package cn.hl.ssm.controller;

import cn.hl.ssm.domain.Product;
import cn.hl.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import java.util.List;

@Controller
@RequestMapping("/product")
public class ProductController {

    @Autowired
    private IProductService productService;

    @RequestMapping("/findAll.do")
    @RolesAllowed("ADMIN")
    public ModelAndView findAll() throws Exception {
        //调用service层方法查询数据
        List<Product> ps=productService.findAll();

        //声明并设置视图
        ModelAndView mv=new ModelAndView();
        mv.addObject("productList",ps);
        mv.setViewName("product-list1");

        return  mv;
    }

    @RequestMapping("/save.do")
    public String save(Product product) throws Exception {
        productService.save(product);
        return "redirect:findAll.do";
    }
}

package cn.hl.ssm.service.Impl;

import cn.hl.ssm.dao.IOrdersDao;
import cn.hl.ssm.domain.Orders;
import cn.hl.ssm.service.IOrdersService;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrdersServiceImpl implements IOrdersService {

    @Autowired
    private IOrdersDao ordersDao;

    @Override
    public List<Orders> findAll(int page,int size) throws Exception {

        //调用分页工具类来分页
        PageHelper.startPage(page,size);

        List<Orders> orders = ordersDao.findAll();
        return orders;
    }

    @Override
    public Orders findById(String ordersId) throws Exception {

        return ordersDao.findById(ordersId);
    }

}

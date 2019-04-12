package cn.hl.ssm.service;

import cn.hl.ssm.domain.Orders;

import java.util.List;

public interface IOrdersService {

//    查询所有订单--未分页
//    public List<Orders> findAll() throws Exception;

//    查询所有订单--分页
    public List<Orders> findAll(int page,int size) throws Exception;

//    查询订单详情
    Orders findById(String ordersId) throws Exception;
}

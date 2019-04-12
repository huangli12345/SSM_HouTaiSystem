package cn.hl.ssm.dao;

import cn.hl.ssm.domain.Traveller;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ITravellerDao {

//    @Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{ordersId})")
@Select("select * from traveller where id in (select travellerId from order_traveller where orderId=#{ordersId})")
    public List<Traveller> findByOrdersId(String ordersId)throws Exception;
}
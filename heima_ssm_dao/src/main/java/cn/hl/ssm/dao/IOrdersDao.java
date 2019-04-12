package cn.hl.ssm.dao;

import cn.hl.ssm.domain.Member;
import cn.hl.ssm.domain.Orders;
import cn.hl.ssm.domain.Product;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface IOrdersDao {


//    @Select("select * from orders where id=#{ordersId}")
//    @Results({
//            @Result(id=true,property = "id",column = "id"),
//            @Result(column = "orderNum",property = "orderNum"),
//            @Result(column = "orderTime",property = "orderTime"),
//            @Result(column = "orderStatus",property = "orderStatus"),
//            @Result(column = "peopleCount",property = "peopleCount"),
//            @Result(column = "payType",property = "payType"),
//            @Result(column = "orderDesc",property = "orderDesc"),
//            //产品信息
//            @Result(column = "productid",property = "product",javaType = Product.class,
//                    one = @One(select = "cn.hl.ssm.dao.IProductDao.findById")),
//            //会员信息
//            @Result(column = "memberid",property = "member",javaType = Member.class,
//                    one = @One(select="cn.hl.ssm.dao.IMemberDao.findById")),
//            //旅客信息
//            @Result(column = "id",property = "travellers",javaType = java.util.List.class,
//                many = @Many(select = "cn.hl.ssm.dao.ITravellerDao.findByOrdersId"))
//
//    })

    @Select("select * from orders where id=#{ordersId}")
    @Results({
            @Result(id = true, property = "id", column = "id"),
            @Result(property = "orderNum", column = "orderNum"),
            @Result(property = "orderTime", column = "orderTime"),
            @Result(property = "orderStatus", column = "orderStatus"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "peopleCount", column = "peopleCount"),
            @Result(property = "payType", column = "payType"),
            @Result(property = "orderDesc", column = "orderDesc"),
            @Result(property = "product", column = "productId", javaType = Product.class, one = @One(select = "cn.hl.ssm.dao.IProductDao.findById")),
            @Result(property = "member",column = "memberId",javaType = Member.class,one = @One(select = "cn.hl.ssm.dao.IMemberDao.findById")),
            @Result(property = "travellers",column = "id",javaType =java.util.List.class, many = @Many(select = "cn.hl.ssm.dao.ITravellerDao.findByOrdersId"))
    })
    public Orders findById(String ordersId)throws Exception;

    @Select("select * from orders")
    @Results({
            @Result(id=true,property = "id",column = "id"),
            @Result(column = "orderNum",property = "orderNum"),
            @Result(column = "orderTime",property = "orderTime"),
            @Result(column = "orderStatus",property = "orderStatus"),
            @Result(column = "peopleCount",property = "peopleCount"),
            @Result(column = "payType",property = "payType"),
            @Result(column = "orderDesc",property = "orderDesc"),
            @Result(column = "productId",property = "product",one = @One(select = "cn.hl.ssm.dao.IProductDao.findById"))
    })
    public List<Orders> findAll() throws Exception;
}

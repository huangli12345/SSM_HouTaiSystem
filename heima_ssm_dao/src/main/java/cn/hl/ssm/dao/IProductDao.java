package cn.hl.ssm.dao;

import cn.hl.ssm.domain.Product;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface IProductDao {

    //查询所有产品
    @Select("select * from product")
    public List<Product> findAll() throws Exception;

    //查询某个产品
    @Select("select * from product where id=#{id}")
    public Product findById(String id)throws Exception;

    //添加一个产品
    @Insert("insert into product(productNum,productName,cityName,departureTime,productPrice,productDesc,productStatus) values(#{productNum},#{productName},#{cityName},#{departureTime},#{productPrice},#{productDesc},#{productStatus})")
    void save(Product product) throws Exception;

}

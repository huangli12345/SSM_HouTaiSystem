package cn.hl.ssm.service;

import cn.hl.ssm.domain.Product;

import java.util.List;

public interface IProductService {

    public List<Product> findAll()throws Exception;

    void save(Product product)throws Exception;
}

package cn.hl.ssm.service.Impl;

import cn.hl.ssm.dao.IProductDao;
import cn.hl.ssm.domain.Product;
import cn.hl.ssm.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional//事务
public class ProductServiceImpl implements IProductService {

    @Autowired
    private IProductDao productDao;


    @Override
    public List<Product> findAll()throws Exception {
        return productDao.findAll();
    }


    @Override
    public void save(Product product) throws Exception {
        productDao.save(product);
    }
}

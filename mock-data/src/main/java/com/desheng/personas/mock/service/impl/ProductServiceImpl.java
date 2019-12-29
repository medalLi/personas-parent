package com.desheng.personas.mock.service.impl;

import com.desheng.personas.mock.dao.CategoryDao;
import com.desheng.personas.mock.dao.ProductDao;
import com.desheng.personas.mock.dao.impl.CategoryDaoImpl;
import com.desheng.personas.mock.dao.impl.ProductDaoImpl;
import com.desheng.personas.mock.pojo.Category;
import com.desheng.personas.mock.pojo.Product;
import com.desheng.personas.mock.service.ProductService;

public class ProductServiceImpl implements ProductService {

    private ProductDao productDao = new ProductDaoImpl();
    private CategoryDao categoryDao = new CategoryDaoImpl();

    @Override
    public Product getRandomProduct() {
        Product product = productDao.getRandomProduct();
        Category category = categoryDao.getCategoryById(product.getCid());
        product.setCategory(category);
        return product;
    }
}

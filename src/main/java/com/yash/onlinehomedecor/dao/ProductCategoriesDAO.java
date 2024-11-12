package com.yash.onlinehomedecor.dao;
/**
 *
 * @author Kushagra Mishra
 */
import com.yash.onlinehomedecor.domain.ProductCategories;
import com.yash.onlinehomedecor.domain.User;

import java.util.List;

public interface ProductCategoriesDAO {
    public void save(ProductCategories pc);
    public void update(ProductCategories product);
    public void delete(ProductCategories product);
    public void delete(Integer id);
    public void findById(Integer id);
    public void findAll(Integer id);
    public void findAll();
    public List<User> findByProperty(String propName, Object propValue);
}

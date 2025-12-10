package com.pluralsight.NorthwindTradersAPI5.dao.Interfaces;

import com.pluralsight.NorthwindTradersAPI5.models.Product;

import java.util.List;

public interface IProductDAO {

    List<Product> getAll();

    Product getById(int productID);

    Product insert(Product product);

    void update(int productID, Product product);

    void delete(int productID, Product product);
}

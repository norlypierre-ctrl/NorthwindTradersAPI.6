package com.pluralsight.NorthwindTradersAPI6.dao.Interfaces;

import com.pluralsight.NorthwindTradersAPI6.models.Product;

import java.util.List;

public interface IProductDAO {

    List<Product> getAll();

    Product getById(int productID);

    Product insert(Product product);

    void update(int productID, Product product);

    void delete(int productID, Product product);
}

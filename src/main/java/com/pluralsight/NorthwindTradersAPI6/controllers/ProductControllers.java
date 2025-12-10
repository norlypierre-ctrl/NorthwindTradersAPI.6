package com.pluralsight.NorthwindTradersAPI6.controllers;

import com.pluralsight.NorthwindTradersAPI6.dao.Interfaces.IProductDAO;
import com.pluralsight.NorthwindTradersAPI6.models.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
public class ProductControllers {
    private final List<Product> products;
    private final IProductDAO productDAO;

    @Autowired
    public ProductControllers(IProductDAO productDAO) {
        this.productDAO = productDAO;
        products = new ArrayList<>();
    }

    @RequestMapping(path = "/Product", method = RequestMethod.GET)
    public List<Product> getProducts() {
        return products;
    }

    @RequestMapping(path = "/Product/{productID}", method = RequestMethod.GET)
    public Product getProducts(@PathVariable int productID) {
        for (Product product : products) {
            if (product.getProductID() == productID) {
                return product;
            }
        }
        return null;
    }

    @RequestMapping(path = "/Product", method = RequestMethod.POST)
    public Product insertProduct(@RequestBody Product product) {
        return productDAO.insert(product);
    }

    @RequestMapping(path = "/Product/{ProductID}", method = RequestMethod.PUT)
    public void updateProduct(@PathVariable int productID,
                              @RequestBody Product product) {
        productDAO.update(productID, product);
    }

    @RequestMapping(path = "/Product/{ProductID}", method = RequestMethod.DELETE)
    public void deleteProduct(@PathVariable int productID,
                              @RequestBody Product product) {
        productDAO.delete(productID, product);
    }
}
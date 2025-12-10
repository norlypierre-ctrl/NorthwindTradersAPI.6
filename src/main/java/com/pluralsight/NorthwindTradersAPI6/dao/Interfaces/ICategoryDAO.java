package com.pluralsight.NorthwindTradersAPI6.dao.Interfaces;

import com.pluralsight.NorthwindTradersAPI6.models.Category;

import java.util.List;

public interface ICategoryDAO {

    List<Category> getAll();

    Category getById(int category);

    Category insert(Category category);

    void update(int categoryID, Category category);

    void delete(int categoryID, Category category);
}

package com.pluralsight.NorthwindTradersAPI5.dao.Interfaces;

import com.pluralsight.NorthwindTradersAPI5.models.Category;

import java.util.List;

public interface ICategoryDAO {

    List<Category> getAll();

    Category getById(int category);

    Category insert(Category category);

    void update(int categoryID, Category category);

    void delete(int categoryID, Category category);
}

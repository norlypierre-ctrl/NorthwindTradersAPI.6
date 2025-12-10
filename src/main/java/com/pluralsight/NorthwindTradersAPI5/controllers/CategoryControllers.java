package com.pluralsight.NorthwindTradersAPI5.controllers;

import com.pluralsight.NorthwindTradersAPI5.dao.Interfaces.ICategoryDAO;
import com.pluralsight.NorthwindTradersAPI5.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


import java.util.ArrayList;
import java.util.List;

@RestController
public class CategoryControllers {
    private final List<Category> categories;
    private final ICategoryDAO categoryDAO;

    @Autowired
    public CategoryControllers(ICategoryDAO categoryDAO) {
        this.categoryDAO = categoryDAO;
        categories = new ArrayList<>();
    }

    @RequestMapping(path = "/Category", method = RequestMethod.GET)
    public List<Category> getCategories() {
        return categories;
    }

    @RequestMapping(path = "/Category/{categoryID}", method = RequestMethod.GET)
    public Category getCategories(@PathVariable int categoryID) {
        for (Category category : categories) {
            if (category.getCategoryID() == categoryID) {
                return category;
            }
        }
        return null;
    }

    @RequestMapping(path = "/Category", method = RequestMethod.POST)
    public Category insertCategory(@RequestBody Category category) {
        return categoryDAO.insert(category);
    }

    @RequestMapping(path = "/Category/{categoryID}", method = RequestMethod.PUT)
    public void updateCategory(@PathVariable int categoryID,
                               @RequestBody Category category) {
        categoryDAO.update(categoryID, category);
    }

    @RequestMapping(path = "/Category/{categoryID}", method = RequestMethod.DELETE)
    public void deleteCategory(@PathVariable int categoryID,
                               @RequestBody Category category) {
        categoryDAO.delete(categoryID, category);
    }
}
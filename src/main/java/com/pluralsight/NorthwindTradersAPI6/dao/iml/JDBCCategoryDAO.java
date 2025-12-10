package com.pluralsight.NorthwindTradersAPI6.dao.iml;

import com.pluralsight.NorthwindTradersAPI6.dao.Interfaces.ICategoryDAO;
import com.pluralsight.NorthwindTradersAPI6.models.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class JDBCCategoryDAO implements ICategoryDAO {

    private final DataSource dataSource;

    @Autowired
    public JDBCCategoryDAO(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    @Override
    public List<Category> getAll() {
        List<Category> categories = new ArrayList<>();
        String getAllQuery = "SELECT * FROM categories";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(getAllQuery);
             ResultSet resultSet = selectStatement.executeQuery()) {

            while (resultSet.next()) {
                int categoryId = resultSet.getInt("category_id");
                categories.add(new Category(categoryId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return categories;
    }

    @Override
    public Category getById(int categoryID) {
        Category category = null;
        String getByIdQuery = "SELECT * FROM Categories WHERE category_id = ?";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement selectStatement = connection.prepareStatement(getByIdQuery)) {

            selectStatement.setInt(1, categoryID);

            try (ResultSet resultSet = selectStatement.executeQuery()) {
                if (resultSet.next()) {

                    category = new Category(categoryID);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    @Override
    public Category insert(Category category) {
        String insertDataQuery =
                "INSERT INTO categories (categoryID) VALUES (?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement insertStatement =
                     connection.prepareStatement(insertDataQuery, Statement.RETURN_GENERATED_KEYS)) {

            insertStatement.setInt(1, category.getCategoryID());
            insertStatement.executeUpdate();

            try (ResultSet generatedKeys = insertStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    category.setCategoryID(generatedKeys.getInt(1));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return category;
    }

    @Override
    public void update(int CategoryID, Category category) {
        String updateDataQuery =
                "UPDATE categories (categoryID) VALUES (?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement updateStatement = connection.prepareStatement(updateDataQuery)) {

            updateStatement.setInt(1, CategoryID);
            updateStatement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void delete(int CategoryID, Category category) {
        String deleteDataQuery =
                "DELETE categories (categoryID) VALUES (?)";

        try (Connection connection = dataSource.getConnection();
             PreparedStatement deleteStatement = connection.prepareStatement(deleteDataQuery)) {

            deleteStatement.setInt(1, CategoryID);
            deleteStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}